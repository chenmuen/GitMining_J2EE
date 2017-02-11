/**
 * Created by chenm on 2016/5/25.
 */
var RepoDAO = require("../dao/RepoDAO");
var ContributorDAO = require("../dao/ContributorDAO");
var CollaboratorDAO = require("../dao/CollaboratorDAO");

var request = require('request');
var EventProxy = require('eventproxy');
var Async = require('async');
var config = require('../config');
var RateLimitUtil = require('../util/rate_limit_util');

function getData(isContributor, page, size) {
    var DAO;
    var searchItem;
    if(isContributor) {
        DAO = ContributorDAO;
        searchItem = "contributors";
    }else {
        DAO = CollaboratorDAO;
        searchItem = "assignees";
    }

    RepoDAO.findAll("stars_count", page, size, function (err, repoListOrg) {
        if(err) {
            console.log(err);
        }
        
        var repoList = new Array;
        repoListOrg.forEach(function (repo) {
            if(isContributor) {
                if(repo.is_contributor_fetched != 1 && repo.is_awe) {
                    repoList.push(repo);
                }
            } else {
                if(repo.is_collaborator_fetched != 1 && repo.is_awe) {
                    repoList.push(repo);
                }
            }
            
        });
        
        var repoListEP = new EventProxy();
        repoListEP.after("repoList", repoList.length, function () {
            console.log("success~~~");
        });

        var time = 0;
        if(isContributor) {
            time = 10000;
        } else {
            time = 1000;
        }
        
        Async.forEachLimit(repoList, 30, function (repo, callback) {
            setTimeout(function () {
                if(isContributor) {
                    getEachConData(repo);
                } else {
                    getEachColData(repo);
                }
                callback();
            }, time);
        });


        function getEachConData(repo) {
            var full_name = repo.full_name;

            var pageEP = new EventProxy();
            pageEP.assign('error', function () {
                console.log(full_name+" fail!!!");
                repoListEP.emit("repoList");
            });

            var contributors = new Array;

            function dealWithContributors() {
                Async.forEachSeries(contributors, function (contributor, callback) {
                    var contributorObj = {repo_full_name: full_name, user_login: contributor.login};
                    DAO.add(contributorObj, function (err) {
                        if(err) {
                            callback(err);
                        } else {
                            callback();
                        }
                    })
                }, function (err) {
                    if(err) {
                        console.log(full_name+" database error" + err);
                    }else {
                        console.log(full_name+" success!!!");
                        repo.contributors_count = contributors.length;
                        repo.is_contributor_fetched = 1;
                        RepoDAO.update(repo, function (err) {
                            if(err) {
                                console.log(err);
                            }
                        });
                    }
                    repoListEP.emit("repoList");
                });
            }
            
            getContributorByPage(1);

            function getContributorByPage(pages) {
                request(config.git_api_base+"repos/"+full_name+"/"+searchItem+"?page="+pages,
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
                            console.log(full_name+": "+error);
                            pageEP.emit("error");
                        }else {
                            if(response.headers['x-ratelimit-remaining'] == 0) {
                                if (RateLimitUtil.changeAccount()) {
                                    console.log(full_name + ' change account');
                                    getEachConData(repo);
                                } else {
                                    console.log(full_name + ' wait~~~');
                                    setTimeout(function () {
                                        getEachConData(repo);
                                    }, 600000);
                                }

                            } else {
                                if(pages == 1) {
                                    if(response.headers['link'] != undefined) {
                                        var link = response.headers['link'].split(',')[1];
                                        var page = parseInt(link.match('page=[0-9]*')[0].substr(5));
                                        pageEP.after('page', page, dealWithContributors);
                                        console.log(full_name+" has page "+ page);
                                        for(var i = 2; i <= page; i++) {
                                            getContributorByPage(i);
                                        }
                                    } else {
                                        pageEP.assign("page", dealWithContributors);
                                    }
                                }

                                if(body.indexOf("Not Found") != -1 || body.indexOf("abuse") != -1) {
                                    console.log(full_name+" Not Found failed~~~");
                                    pageEP.emit("error");
                                    return ;
                                }

                                var joins;
                                try{
                                    joins = JSON.parse(body);
                                }catch (err) {
                                    console.log(full_name+" json error!!" + body);
                                    pageEP.emit("error");
                                    return ;
                                }
                                
                                if(joins.constructor !== Array) {
                                    console.log(full_name+" json error!!" + body);
                                    pageEP.emit("error");
                                    return ;
                                }

                                joins.forEach(function (join) {
                                    contributors.push(join);
                                });
                                pageEP.emit("page");
                            }
                        }
                    }
                );
            }
        }

        function getEachColData(repo) {
            var full_name = repo.full_name;

            request(config.git_api_base+"repos/"+full_name+"/"+searchItem,
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
                        console.log(full_name+": "+error);
                        repoListEP.emit("repoList");
                    }else {
                        if(response.headers['x-ratelimit-remaining'] == 0) {
                            if (RateLimitUtil.changeAccount()) {
                                console.log(full_name + ' change account');
                                getEachColData(repo);
                            } else {
                                console.log(full_name + ' wait~~~');
                                setTimeout(function () {
                                    getEachColData(repo);
                                }, 600000);
                            }

                        } else {
                            var joins;
                            try{
                                joins = JSON.parse(body);
                            }catch (err) {
                                console.log(full_name + " json error!!" + body);
                                repoListEP.emit("repoList");
                                return ;
                            }

                            if(joins.constructor !== Array) {
                                console.log(full_name+" json error!!" + body);
                                repoListEP.emit("repoList");
                                return ;
                            }

                            Async.forEach(joins, function (collaborator, callback) {
                                var colObj = {repo_full_name: full_name, user_login: collaborator.login};
                                DAO.add(colObj, function(err) {
                                    if(err) {
                                        console.log(full_name+" database error "+err);
                                    }
                                    callback();
                                })
                            }, function (err) {
                                repo.collaborators_count = joins.length;
                                repo.is_collaborator_fetched = 1;
                                RepoDAO.update(repo, function (err) {
                                    if(err) {
                                        console.log(full_name+" database error "+err);
                                    }
                                });
                                console.log(full_name+" success!!!");
                                repoListEP.emit("repoList");
                            })
                        }
                    }
                }
            );
        }


    });
}

function getRest(isContributor, page, size) {
    RepoDAO.findAll("stars_count", page, size, function (err, repoListOrg) {
        var repoList = [];
        
        repoListOrg.forEach(function (repo) {
            if (isContributor) {
                if (repo.is_contributor_fetched != 1 && repo.is_awe) {
                    repoList.push(repo);
                }
            } else {
                if (repo.is_collaborator_fetched != 1 && repo.is_awe) {
                    repoList.push(repo);
                }
            }
        });
        console.log(repoList.length);
    });
}



getData(true, 2, 80000);

// getRest(true, 1, 80000);
