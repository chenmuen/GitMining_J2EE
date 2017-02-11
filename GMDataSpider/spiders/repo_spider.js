/**
 * Created by chenm on 2016/5/22.
 */
var User = require("../models/user");
var UserDAO = require("../dao/UserDAO");
var RepoDAO = require("../dao/RepoDAO");

var request = require('request');
var EventPorxy = require('eventproxy');
var Async = require('async');
var config = require('../config');
var RateLimitUtil = require('../util/rate_limit_util');

function getRepoByUser(page, size) {
    UserDAO.findAll('followers_count', page, size, function (err, userListOrg) {
        var userList = new Array;

        userListOrg.forEach(function (user) {
            if (user.is_repo_fetched == 0) {
                userList.push(user);
            }
        });

        var userListEP = new EventPorxy();
        userListEP.after("userList", userList.length, function () {
            console.log("success!!!");
        });

        Async.forEachLimit(userList, 30, function (user, callback) {
            setTimeout(function () {
                getEachData(user);
                callback();
            }, 7500);
        }, function (err) {
            console.log(err);
        });


        function getEachData(user) {
            var login = user.login;

            request("https://api.github.com/users/" + login + "/repos?sort=created", {
                auth: {
                    user: config.git_user[0],
                    pass: config.git_pass[0],
                    sendImmediately: true
                },
                headers: {
                    'User-Agent': 'request'
                }
            }, function (error, response, body) {
                if (error) {
                    console.log(login + ": " + error);
                    userListEP.emit("userList");
                } else {
                    if (response.headers['x-ratelimit-remaining'] == 0) {
                        console.log('wait~~~');
                        setTimeout(function () {
                            getEachData(user);
                        }, 600000);

                    } else {
                        var repoList;
                        try {
                            repoList = JSON.parse(body);
                        } catch (err) {
                            console.log(login + ": json error!!! " + body);
                            userListEP.emit("userList");
                            return;
                        }

                        if (repoList.constructor === Array) {
                            var repoListEP = new EventPorxy();
                            repoListEP.after("repoList", repoList.length, function () {
                                user.is_repo_fetched = 1;
                                UserDAO.update(user, function (err) {
                                    if (!err) {
                                        console.log(login + " success!!!");
                                    }
                                });
                                userListEP.emit("userList");
                            });

                            repoList.forEach(function (repo) {
                                var repoObj = {
                                    full_name: repo['full_name'],
                                    description: repo['description'],
                                    url: repo['html_url'],
                                    clone_url: repo['clone_url'],
                                    subscribers_count: repo['watchers_count'],
                                    forks_count: repo['forks_count'],
                                    stars_count: repo['stargazers_count'],
                                    issues_count: repo['open_issues_count'],
                                    size: repo['size'],
                                    language: (repo['language'] != undefined) ? repo['language'] : 'Unknown',
                                    pullrequests_count: 1,
                                    contributors_count: 1,
                                    collaborators_count: 1,
                                    is_language_fetched: 0,
                                    is_contributor_fetched: 0,
                                    is_collaborator_fetched: 0,
                                    is_pullrequest_fetched: 0
                                };
                                var created_at = repo['created_at'].replace("T", " ").replace("Z", "");
                                var updated_at = repo['updated_at'].replace("T", " ").replace("Z", "");
                                repoObj['created_at'] = (new Date(Date.parse(created_at.replace(/-/g, "/")))).getTime();
                                repoObj['updated_at'] = (new Date(Date.parse(updated_at.replace(/-/g, "/")))).getTime();

                                RepoDAO.add(repoObj, function (err) {
                                    if (err) {
                                        console.log(repoObj.full_name + " database error")
                                    }
                                    repoListEP.emit("repoList");
                                });
                            })
                        } else {
                            console.log(login + " failed!!! " + body);
                            userListEP.emit("userList");
                        }
                    }
                }
            });
        }
    });
}

function getRest(page, size) {
    UserDAO.findAll('followers_count', page, size, function (err, userListOrg) {
        var userList = new Array;

        userListOrg.forEach(function (user) {
            if (user.is_repo_fetched == 0) {
                userList.push(user);
            }
        });

        console.log(userList.length);
    });
}

function getLanguage() {
    RepoDAO.findAll("stars_count", 1, 40000, function (err, repos) {
        var repoList = [];
        repos.forEach(function (repo) {
            if (repo.language == "") {
                repoList.push(repo);
            }
        });

        console.log(repoList.length);

        Async.forEachLimit(repoList, 30, function (repo, callback) {
            setTimeout(function () {
                getEachData(repo, callback);
            }, 2000);
        }, function (err) {
            if (err) {
                console.log(err);
            } else {
                console.log("success!!!");
            }
        });

        function getEachData(repo, callback) {
            var full_name = repo.full_name;

            request("https://api.github.com/repos/" + full_name, {
                auth: {
                    user: config.git_user[RateLimitUtil.current],
                    pass: config.git_pass[RateLimitUtil.current],
                    sendImmediately: true
                },
                headers: {
                    'User-Agent': 'request'
                }
            }, function (error, response, body) {
                if (error) {
                    console.log(full_name + " network error " + error);
                    callback();
                    return;
                }
                if (response.headers['x-ratelimit-remaining'] == 0) {
                    if (RateLimitUtil.changeAccount()) {
                        console.log(full_name + ' change account');
                        // console.log("current: " + RateLimitUtil.current);
                        getEachData(repo, callback);
                    } else {
                        console.log(full_name + ' wait~~~');
                        setTimeout(function () {
                            getEachData(repo, callback);
                        }, 600000);
                    }
                } else {
                    if (body.indexOf("Not Found") != -1 || body.indexOf("abuse") != -1) {
                        console.log(full_name + " api error");
                        repo.language = "Unknown";

                        RepoDAO.update(repo, function (err) {
                            if (err) {
                                console.log(err);
                            }
                            console.log(full_name + " success!!!");
                            callback();
                        })
                        return ;
                    }

                    var repoJson;
                    try {
                        repoJson = JSON.parse(body);
                    } catch (err) {
                        console.log(full_name + " json error!!" + body);
                        callback();
                        return;
                    }

                    if (repoJson.language) {
                        repo.language = repoJson.language;
                    } else {
                        repo.language = "Unknown";
                    }
                    RepoDAO.update(repo, function (err) {
                        if (err) {
                            console.log(err);
                        }
                        console.log(full_name + " success!!!");
                        callback();
                    })
                }
            });
        }
    })
}

function getAwesomeRepo(language) {
    var years = [2008,2009,2010,2011,2012,2013,2014,2015,2016];
    var pages = [1,2,3,4,5];
    Async.forEachLimit(years, 3, function (year, cb) {
        setTimeout(function () {
            Async.forEach(pages, function (page, callback) {
                getEachData(page, callback, year);
            }, function (err) {
                cb();
            });
        }, 5000);
    }, function (err) {
        console.log(language + " success!!!");
    });
    

    function getEachData(page, cb, year) {
        language = language.toLowerCase();
        request("https://api.github.com/search/repositories?q=language:"+language+"+created:"+year+"-01-01.."+year+"-12-31&page="+page,
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
                if (error) {
                    console.log(language + ' ' + year + " network error " + error);
                    cb();
                    return;
                }

                if (response.headers['x-ratelimit-remaining'] == 0) {
                    if (RateLimitUtil.changeAccount()) {
                        console.log(language + ' ' + year + ' change account');
                        // console.log("current: " + RateLimitUtil.current);
                        getEachData(page, cb);
                    } else {
                        console.log(language + ' ' + year + ' wait~~~');
                        setTimeout(function () {
                            getEachData(page, cb);
                        }, 600000);
                    }
                } else {
                    var jsonObj;
                    try {
                        jsonObj = JSON.parse(body);
                    } catch (err) {
                        console.log(language + ' ' + year + ": json error!!! " + body);
                        cb();
                        return;
                    }

                    if (jsonObj.items) {
                        var repoList = jsonObj.items;
                        var repoListEP = new EventPorxy();
                        repoListEP.after("repoList", repoList.length, function () {
                            console.log(language + ' ' + year + " success!!!");
                            cb();
                        });

                        repoList.forEach(function (repo) {
                            var repoObj = {
                                full_name: repo['full_name'],
                                description: repo['description'],
                                url: repo['html_url'],
                                clone_url: repo['clone_url'],
                                subscribers_count: repo['watchers_count'],
                                forks_count: repo['forks_count'],
                                stars_count: repo['stargazers_count'],
                                issues_count: repo['open_issues_count'],
                                size: repo['size'],
                                language: (repo['language'] != undefined) ? repo['language'] : 'Unknown',
                                pullrequests_count: 1,
                                contributors_count: 1,
                                collaborators_count: 1,
                                is_language_fetched: 0,
                                is_contributor_fetched: 0,
                                is_collaborator_fetched: 0,
                                is_pullrequest_fetched: 0,
                                is_punch_fetched: 0,
                                is_issue_fetched: 0,
                                is_awe: 1
                            };
                            var created_at = repo['created_at'].replace("T", " ").replace("Z", "");
                            var updated_at = repo['updated_at'].replace("T", " ").replace("Z", "");
                            repoObj['created_at'] = (new Date(Date.parse(created_at.replace(/-/g, "/")))).getTime();
                            repoObj['updated_at'] = (new Date(Date.parse(updated_at.replace(/-/g, "/")))).getTime();

                            RepoDAO.add(repoObj, function (err) {
                                if (err) {
                                    console.log(repoObj.full_name + " database error" + err);
                                }
                                repoListEP.emit("repoList");
                            });
                        })
                    } else {
                        console.log(language + ' ' + year  + " failed!!! " + body);
                        cb();
                    }
                }
            }
        );
    }
}

//Java JavaScript Ruby Python C PHP C++
getAwesomeRepo("Visual Basic");

// getRepoByUser(1,5000);

// getRest(2,5000);

// getLanguage();






