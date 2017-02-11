/**
 * Created by chenm on 2016/5/28.
 */
var RepoDAO = require("../dao/RepoDAO");

var request = require('request');
var EventPorxy = require('eventproxy');
var Async = require('async');
var RateLimitUtil = require('../util/rate_limit_util');
var config = require('../config');

function getPullRequest(page, size, isIssue) {
    RepoDAO.findAll("stars_count", page, size, function (err, reposOrg) {
        var repoList = [];
        reposOrg.forEach(function (repo) {
            if ((isIssue?repo.is_issue_fetched:repo.is_pullrequest_fetched) == 0) {
                repoList.push(repo);
            }
        });

        var repoListEP = new EventPorxy();
        repoListEP.after("repoList", repoList.length, function () {
            console.log("success~~~");
        });


        Async.forEachLimit(repoList, 15, function (repo, callback) {
            setTimeout(function () {
                getEachData(repo, 0, 1);
                callback();
            }, 5000);
        });

        function getEachData(repo, pullBase, pullPage) {
            var full_name = repo.full_name;
            var url = "pulls";
            if(isIssue) {
                url = "issues";
            }
            
            request(config.git_api_base + "repos/" + full_name + "/"+url+"?page=" + pullPage ,
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
                        console.log(full_name + ": " + error);
                        repoListEP.emit("repoList");
                    }
                    else
                    {
                        if (response.headers['x-ratelimit-remaining'] == 0) {
                            if(RateLimitUtil.changeAccount()) {
                                console.log(full_name + ' change account');
                                console.log("current: " + RateLimitUtil.current);
                                setTimeout(function () {
                                    getEachData(repo, pullBase, pullPage);
                                }, 20000);
                            } else {
                                console.log(full_name + ' wait~~~');
                                setTimeout(function () {
                                    getEachData(repo, pullBase, pullPage);
                                }, 600000);
                            }

                        } else {
                            var pulls;
                            try {
                                pulls = JSON.parse(body);
                            } catch (err) {
                                console.log(full_name + " json error!!" + body);
                                repoListEP.emit("repoList");
                                return ;
                            }

                            if (pulls.constructor !== Array) {
                                console.log(full_name + " json error!!" + body);
                                repoListEP.emit("repoList");
                                return ;
                            }
                            
                            if (pullBase == 0 && response.headers['link'] != undefined) {
                                var link = response.headers['link'].split(',')[1];
                                var page = parseInt(link.match('page=[0-9]*')[0].substr(5));
                                console.log(full_name+" has "+page+" pages");
                                getEachData(repo, pulls.length, page);
                                
                            } else {
                                // console.log(full_name+" base: " + pullBase);
                                // console.log(full_name+" page: " + pullPage);
                                // console.log(full_name+" length: " + pulls.length);

                                if(isIssue) {
                                    repo.issues_count = pullBase * (pullPage - 1) + pulls.length;
                                    repo.is_issue_fetched = 1;
                                } else {
                                    repo.pullrequests_count = pullBase * (pullPage - 1) + pulls.length;
                                    repo.is_pullrequest_fetched = 1;
                                }
                                RepoDAO.update(repo, function (err) {
                                    if(err) {
                                        console.log(full_name + " database error "+err.message);
                                        repoListEP.emit("repoList");
                                    } else {
                                        console.log(full_name + " success " + (pullBase * (pullPage - 1) + pulls.length) + " !!!");
                                        repoListEP.emit("repoList");
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

function getRest(page, size, isIssue) {
    RepoDAO.findAll("stars_count", page, size, function (err, reposOrg) {
        var repoList = [];
        reposOrg.forEach(function (repo) {
            if ((isIssue?repo.is_issue_fetched:repo.is_pullrequest_fetched) == 0) {
                repoList.push(repo);
            }
        });
        
        console.log(repoList.length);
    });
}

getPullRequest(2, 40000, true);
// getRest(1, 40000, true);

// RateLimitUtil.changeAccount();
// RateLimitUtil.changeAccount();
// console.log(RateLimitUtil.current);