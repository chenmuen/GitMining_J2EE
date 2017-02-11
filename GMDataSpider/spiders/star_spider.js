/**
 * Created by chenm on 2016/6/10.
 */
var UserDAO = require("../dao/UserDAO");

var request = require('request');
var EventPorxy = require('eventproxy');
var Async = require('async');
var RateLimitUtil = require('../util/rate_limit_util');
var config = require('../config');

function getStar(page, size, isStar) {
    UserDAO.findAll("login", page, size, function (err, usersOrg) {
        var userList = [];
        usersOrg.forEach(function (user) {
            if (user.is_old == 1 && (isStar?user.is_star_fetched:user.is_watch_fetched) == 0) {
                userList.push(user);
            }
        });
        
        console.log(userList.length);

        var userListEP = new EventPorxy();
        userListEP.after("repoList", userList.length, function () {
            console.log("success~~~");
        });
        
        
        Async.forEachLimit(userList, 30, function (user, callback) {
            setTimeout(function () {
                getEachData(user, 0, 1);
                callback();
            }, 5000);
        });

        function getEachData(user, pullBase, pullPage) {
            var login = user.login;
            var url = "subscriptions";
            if(isStar) {
                url = "starred";
            }

            request(config.git_api_base + "users/" + login + "/"+url+"?page=" + pullPage ,
                {
                    auth: {
                        user: config.git_user[RateLimitUtil.current],
                        pass: config.git_pass[RateLimitUtil.current],
                        sendImmediately: true
                    },
                    headers: {
                        'User-Agent': 'request'
                    }
                },
                function (error, response, body) {
                    if(error)
                    {
                        console.log(login + ": " + error);
                        userListEP.emit("repoList");
                    }
                    else
                    {
                        if (response.headers['x-ratelimit-remaining'] == 0) {
                            if(RateLimitUtil.changeAccount()) {
                                console.log(login + ' change account');
                                console.log("current: " + RateLimitUtil.current);
                                setTimeout(function () {
                                    getEachData(user, pullBase, pullPage);
                                }, 20000);
                            } else {
                                console.log(login + ' wait~~~');
                                setTimeout(function () {
                                    getEachData(user, pullBase, pullPage);
                                }, 600000);
                            }

                        } else {
                            var pulls;
                            try {
                                pulls = JSON.parse(body);
                            } catch (err) {
                                console.log(login + " json error!!" + body);
                                userListEP.emit("repoList");
                                return ;
                            }

                            if (pulls.constructor !== Array) {
                                console.log(login + " json error!!" + body);
                                userListEP.emit("repoList");
                                return ;
                            }

                            if (pullBase == 0 && response.headers['link'] != undefined) {
                                var link = response.headers['link'].split(',')[1];
                                var page = parseInt(link.match('page=[0-9]*')[0].substr(5));
                                console.log(login+" has "+page+" pages");
                                getEachData(user, pulls.length, page);

                            } else {
                                // console.log(full_name+" base: " + pullBase);
                                // console.log(full_name+" page: " + pullPage);
                                // console.log(full_name+" length: " + pulls.length);

                                if(isStar) {
                                    user.starred_count = pullBase * (pullPage - 1) + pulls.length;
                                    user.is_star_fetched = 1;
                                } else {
                                    user.subscription_count = pullBase * (pullPage - 1) + pulls.length;
                                    user.is_watch_fetched = 1;
                                }
                                UserDAO.update(user, function (err) {
                                    if(err) {
                                        console.log(login + " database error "+err.message);
                                        userListEP.emit("repoList");
                                    } else {
                                        console.log(login + " success!!!");
                                        userListEP.emit("repoList");
                                    }
                                })
                            }
                        }
                    }
                }
            )
        }
    })
}

getStar(1,40000, false);
