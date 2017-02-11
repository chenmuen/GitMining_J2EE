/**
 * Created by chenm on 2016/5/24.
 */
var Repo = require("../models/repo");
var RepoDAO = require("../dao/RepoDAO");
var LanguageDAO = require("../dao/LanguageDAO");
var SingleLanguageDAO = require('../dao/SingleLanguageDAO');
var LanguageYearDAO = require('../dao/LanguageYearDAO');

var request = require('request');
var EventProxy = require('eventproxy');
var Async = require('async');
var config = require('../config');
var RateLimitUtil = require('../util/rate_limit_util');

function getLanguageData(page, size) {
    RepoDAO.findAll("stars_count", page, size, function (err, repoListOrg) {
        if(err) {
            console.log(err);
        }
       
        var repoList = new Array;

        repoListOrg.forEach(function (repo) {
            if(repo.is_language_fetched == 0) {
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
            }, 3000);
        });

        function getEachData(repo) {
            var full_name = repo.full_name;
            
            request(config.git_api_base+"repos/"+full_name+"/languages",
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
                                // console.log("current: " + RateLimitUtil.current);
                                getEachData(repo);
                            } else {
                                console.log(full_name + ' wait~~~');
                                setTimeout(function () {
                                    getEachData(repo);
                                }, 600000);
                            }

                        } else {
                            if(body.indexOf("Not Found") != -1 || body.indexOf("abuse") != -1) {
                                console.log(full_name+" api error");
                                repoListEP.emit("repoList");
                                return ;
                            }

                            var length = 0;
                            for(var key in languages) {
                                length++;
                            }

                            var languageListEP = new EventProxy();
                            languageListEP.after("languageList", length, function(){
                                repo.is_language_fetched = 1;
                                RepoDAO.update(repo, function(err){
                                    if(!err){
                                        console.log(full_name+" success~~~");
                                    }
                                });
                                repoListEP.emit("repoList");
                            });

                            var languages;
                            try{
                                languages = JSON.parse(body);
                            }catch (err) {
                                console.log(full_name+" json error!!" + body);
                                repoListEP.emit("repoList");
                                return ;
                            }
                            for(var key in languages) {
                                var languageObj = {
                                    language: key,
                                    repo_full_name: full_name,
                                    size: languages[key]
                                };
                                var date = new Date(repo.created_at);
                                languageObj.year = date.getFullYear();
                                LanguageDAO.add(languageObj, function (err) {
                                    if(err) {
                                        console.log(full_name+" database error "+err);
                                    }else {
                                        languageListEP.emit("languageList");
                                    }
                                });
                            }
                        }
                    }
                }
            );
        }
    });
}

function getRest(page, size) {
    RepoDAO.findAll("stars_count", page, size, function (err, repoListOrg) {
        if(err) {
            console.log(err);
        }

        var repoList = new Array;

        repoListOrg.forEach(function (repo) {
            if(repo.is_language_fetched == 0) {
                repoList.push(repo);
            }
        });

        console.log(repoList.length);
    });
}

function check() {
    LanguageDAO.getDistinctColoum('repo_full_name', function (err, languageRepos) {
        var list = new Array;
        languageRepos.forEach(function (repo) {
            list.push(repo['repo_full_name']);
        });
        list.forEach(function (repo) {
            RepoDAO.updateColumn('full_name', repo, 'is_language_fetched', 1, function (err) {
                if(err) {
                    console.error(err);
                } else {
                    console.log(repo + " success!!!");
                }
            })
        })
    });


}

function getNums() {
    SingleLanguageDAO.findAll("language",1,10000,function (err, languagesOrg) {
        var languages = [];

        languagesOrg.forEach(function (language) {
            if(language.repo_num == 0 || language.user_num == 0) {
                languages.push(language);
            }
        });

        Async.forEachSeries(languages,function (language,callback) {
            getEach(language,callback);

            function getEach(language, callback) {
                var lang = language.language;
                if(lang == 'C++') {
                    lang = 'Cpp';
                } else if(lang == 'C#') {
                    lang = 'CSharp'
                } else if(lang == 'Objective-C++') {
                    lang = 'Objective-C%2B%2B';
                }

                Async.series([
                    function (cb) {
                        request("https://api.github.com/search/repositories?q=language:"+lang.toLowerCase(),
                            {
                                auth: {
                                    user: config.git_user[RateLimitUtil.current+4],
                                    pass: config.git_pass[RateLimitUtil.current+4],
                                    sendImmediately: true
                                },
                                headers: {
                                    'User-Agent': 'request'
                                }
                            },
                            function (error, response, body) {
                                if(error) {
                                    console.log(lang + "network error" + error);
                                }
                                var json;
                                try{
                                    json = JSON.parse(body);
                                }catch (err) {
                                    console.log(lang+" json error!!" + body);
                                    return ;
                                }
                                console.log(lang + " repo " + json.total_count);
                                if(json.total_count != undefined) {
                                    language.repo_num = json.total_count;
                                    cb();
                                }else {
                                    cb(new Error("Not Found"));
                                }
                            }
                        );
                    },
                    function (cb) {
                        request("https://api.github.com/search/users?q=language:"+lang.toLowerCase(),
                            {
                                auth: {
                                    user: config.git_user[RateLimitUtil.current+4],
                                    pass: config.git_pass[RateLimitUtil.current+4],
                                    sendImmediately: true
                                },
                                headers: {
                                    'User-Agent': 'request'
                                }
                            },
                            function (error, response, body) {
                                if(error) {
                                    console.log(lang + "network error" + error);
                                }
                                var json;
                                try{
                                    json = JSON.parse(body);
                                }catch (err) {
                                    console.log(lang+" json error!!" + body);
                                    return ;
                                }
                                console.log(lang + " user " + json.total_count);
                                if(json.total_count != undefined) {
                                    language.user_num = json.total_count;
                                    cb();
                                }else {
                                    cb(new Error("Not Found"));
                                }
                            }
                        );
                    }
                ], function (err) {
                    if(err) {
                        console.log(lang + " fail");
                        callback();
                    } else {
                        SingleLanguageDAO.update(language, function (err) {
                            console.log(lang+" success!!!");
                            callback();
                        })
                    }
                })
            }
            

        })
    })
}

function getYearNums() {
    SingleLanguageDAO.findAll("language",1,10000,function (err, languages) {
        var years = [2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016];

        languages = languages.slice(209, languages.length);

        Async.forEachLimit(languages, 1, function (language,callback) {

            setTimeout(function () {
                Async.map(years, function (year, callback) {
                    getEach(language, callback, year);
                }, function (err, langs) {
                    if(err) {
                        console.log(language.language + " fail");
                        callback();
                    } else {
                        langs.forEach(function (lang) {
                           LanguageYearDAO.add(lang, function (err) {
                               if(err) {
                                   console.log(lang.language + " " +err);
                               }
                           }) 
                        });
                        callback();
                    }
                });
            }, 20000);


            function getEach(language, callback, year) {
                var lang = language.language;
                if(lang == 'C++') {
                    lang = 'Cpp';
                } else if(lang == 'C#') {
                    lang = 'CSharp'
                } else if(lang == 'Objective-C++') {
                    lang = 'Objective-C%2B%2B';
                } else if(lang == 'F#') {
                    lang = 'FSharp';
                }

                var langYear = {language: language.language, year: year};

                Async.series([
                    function (cb) {
                        request("https://api.github.com/search/repositories?q=language:"+lang.toLowerCase()+"+created:"+year+"-01-01.."+year+"-12-31",
                            {
                                auth: {
                                    user: config.git_user[RateLimitUtil.current+1],
                                    pass: config.git_pass[RateLimitUtil.current+1],
                                    sendImmediately: true
                                },
                                headers: {
                                    'User-Agent': 'request'
                                }
                            },
                            function (error, response, body) {
                                if(error) {
                                    console.log(lang + "network error" + error);
                                }
                                var json;
                                try{
                                    json = JSON.parse(body);
                                }catch (err) {
                                    console.log(lang+" json error!!" + body);
                                    return ;
                                }
                                console.log(lang + " " + year + " repo " + json.total_count);
                                if(json.total_count != undefined) {
                                    langYear.repo_num = json.total_count;
                                    cb();
                                }else {
                                    cb(new Error("Not Found"));
                                }
                            }
                        );
                    },
                    function (cb) {
                        request("https://api.github.com/search/users?q=language:"+lang.toLowerCase()+"+created:"+year+"-01-01.."+year+"-12-31",
                            {
                                auth: {
                                    user: config.git_user[RateLimitUtil.current+2],
                                    pass: config.git_pass[RateLimitUtil.current+2],
                                    sendImmediately: true
                                },
                                headers: {
                                    'User-Agent': 'request'
                                }
                            },
                            function (error, response, body) {
                                if(error) {
                                    console.log(lang + "network error" + error);
                                }
                                var json;
                                try{
                                    json = JSON.parse(body);
                                }catch (err) {
                                    console.log(lang+" json error!!" + body);
                                    return ;
                                }
                                console.log(lang + " " + year + " user " + json.total_count);
                                if(json.total_count != undefined) {
                                    langYear.user_num = json.total_count;
                                    cb();
                                }else {
                                    console.log(lang + " " + year + " user " + body);
                                    cb(new Error("Not Found"));
                                }
                            }
                        );
                    }
                ], function (err) {
                    if(err) {
                        callback(err, null);
                    } else {
                        console.log(langYear);
                        callback(null, langYear);
                    }
                })
            }
        })
    })
}

getLanguageData(2, 80000);

// getRest(3,80000);

// getNums();

// getYearNums();

// check();