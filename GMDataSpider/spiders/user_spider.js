/**
 * Created by chenm on 2016/6/10.
 */
var RepoOldDAO = require("../dao/RepoOldDAO");
var RepoDAO = require("../dao/RepoDAO");
var UserDAO = require("../dao/UserDAO");

var request = require('request');
var Async = require('async');
var config = require('../config');
var RateLimitUtil = require('../util/rate_limit_util');

function getData(page, size) {
    RepoDAO.findAll('stars_count', page ,size,function (err, repoList) {
        // console.log(repos);
        
        var repos = [];
        
        repoList.forEach(function (repo) {
            if(repo.is_awe == 1 && repo.is_user_fetched == 0) {
                repos.push(repo);
            }
        });

        Async.forEachLimit(repos, 50, function (repo, callback) {
            setTimeout(function () {
                getEachData(repo, callback);
            }, 1000);
        
            function getEachData(repo, callback) {
                var full_name = repo.full_name;
                var login = full_name.split('/')[0];
                request(config.git_api_base+"users/"+login,
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
                        if(error) {
                            console.log(login + ": "+error);
                            callback();
                        }else {
                            if (response.headers['x-ratelimit-remaining'] == 0) {
                                if (RateLimitUtil.changeAccount()) {
                                    console.log(login + ' change account');
                                    // console.log("current: " + RateLimitUtil.current);
                                    getEachData(repo, callback);
                                } else {
                                    console.log(login + ' wait~~~');
                                    setTimeout(function () {
                                        getEachData(repo, callback);
                                    }, 600000);
                                }
                            } else {
                                if (body.indexOf("Not Found") != -1 || body.indexOf("abuse") != -1) {
                                    console.log(login + " api error");
                                    callback();
                                    return;
                                }
        
                                var user;
                                try {
                                    user = JSON.parse(body);
                                } catch (err) {
                                    console.log(login + ": json error!!! " + body);
                                    callback();
                                    return;
                                }
        
                                var userObj = {
                                    login: user.login,
                                    name: user.name,
                                    type: user.type,
                                    avatar_url: user.avatar_url,
                                    html_url: user.html_url,
                                    followers_count: user.followers,
                                    followings_count: user.following,
                                    starred_count: 0,
                                    subscription_count: 0,
                                    public_gists: user.public_gists,
                                    public_repo: user.public_repos,
                                    email: user.email?user.email:"Unknown",
                                    location: user.location?user.location:"Unknown",
                                    blog: user.blog?user.blog:"Unknown",
                                    company: user.company?user.company:"Unknown",
                                    is_repo_fetched: 0,
                                    is_old: 1
                                };

                                var created_at = user['created_at'].replace("T", " ").replace("Z", "");
                                userObj['create_at'] = (new Date(Date.parse(created_at.replace(/-/g, "/")))).getTime();
                                
                                UserDAO.add(userObj, function (err) {
                                    if(err) {
                                        console.log(login + " data err " + err);
                                    } else {
                                        console.log(userObj.login + " success!!!");
                                    }
                                    callback();
                                    repo.is_user_fetched = 1;
                                    RepoDAO.update(repo, function (err) {
                                        if(err) {
                                            console.log(repo + " data error " + err);
                                        }
                                    })
                                })
                            }
                        }
                    }
                );
            }
        
        }, function (err) {
        
        })
    })
}

function getRest(page, size) {
    RepoDAO.findAll('stars_count', page ,size,function (err, repoList) {
        // console.log(repos);

        var repos = [];

        repoList.forEach(function (repo) {
            if (repo.is_awe == 1 && repo.is_user_fetched == 0) {
                repos.push(repo);
            }
        });
        
        console.log(repos.length);
    });
}

// getRest(1,80000);

// getData(2, 80000);