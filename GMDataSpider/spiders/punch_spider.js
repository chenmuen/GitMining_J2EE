/**
 * Created by chenm on 2016/6/2.
 */
var RepoDAO = require("../dao/RepoDAO");
var PunchDAO = require("../dao/PunchDAO");

var request = require('request');
var EventProxy = require('eventproxy');
var Async = require('async');
var RateLimitUtil = require('../util/rate_limit_util');
var config = require('../config');


function getPunch(page, size) {
    RepoDAO.findAll("stars_count", page, size, function (err, reposOrg) {
        var repoList = [];

        reposOrg.forEach(function (repo) {
            if(repo.is_punch_fetched == 0 && repo.is_awe == 1) {
                repoList.push(repo);
            }
        });

        var repoListEP = new EventProxy();
        repoListEP.after("repoList", repoList.length, function () {
            console.log("success~~~");
        });
        
        Async.forEachLimit(repoList, 30, function (repo, callback) {
            setTimeout(function () {
                getEachData(repo);
                callback();
            }, 5000);
        });

        function getEachData(repo) {
            var full_name = repo.full_name;

            request(config.git_api_base + "repos/" + full_name + "/stats/punch_card",
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
                        console.log(full_name + " network error "+err);
                        repoListEP.emit("repoList");
                        return ;
                    }
                    if (response.headers['x-ratelimit-remaining'] == 0) {
                        if (RateLimitUtil.changeAccount()) {
                            console.log(full_name + ' change account');
                            getEachData(repo);
                        } else {
                            console.log(full_name + ' wait~~~');
                            setTimeout(function () {
                                getEachData(repo);
                            }, 600000);
                        }
                    } else {
                        var punches;
                        try {
                            punches = JSON.parse(body);
                        } catch (err) {
                            console.log(full_name + " json error!!" + body);
                            repoListEP.emit("repoList");
                            return ;
                        }

                        if (punches.constructor !== Array) {
                            console.log(full_name + " json error!!" + body);
                            repoListEP.emit("repoList");
                            return ;
                        }

                        var punchList = [];
                        
                        Async.forEach(punches, function (punch,callback) {
                            var punchObj = {
                                repo_full_name: full_name,
                                day: punch[0],
                                hour: punch[1],
                                commits: punch[2]
                            };
                            PunchDAO.add(punchObj, function (err) {
                                if(err) {
                                    console.log(full_name + ' database err' + err);
                                    callback(err);
                                }
                                callback();
                            })
                        }, function (err) {
                            if(err) {
                                PunchDAO.removeByColumn("repo_full_name", full_name, function (err) {
                                    repoListEP.emit("repoList");
                                })
                            } else {
                                repo.is_punch_fetched = 1;
                                RepoDAO.update(repo, function (err) {
                                    console.log(full_name + " success!!!");
                                    repoListEP.emit("repoList");
                                });
                            }
                        })
                    }
                }
            )
        }
    });
}

function getRest(page, size) {
    RepoDAO.findAll("stars_count", page, size, function (err, reposOrg) {
        var repoList = [];

        reposOrg.forEach(function (repo) {
            if (repo.is_punch_fetched == 0 && repo.is_awe == 1) {
                repoList.push(repo);
            }
        });
        console.log(repoList.length);
    })
}

getPunch(2, 80000);
// getRest(1,80000);