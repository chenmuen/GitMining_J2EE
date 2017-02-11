/**
 * Created by chenm on 2016/6/3.
 */
var LanguageDAO = require("../dao/LanguageDAO");
var SingleDAO = require("../dao/SingleLanguageDAO");
var RepoDAO = require("../dao/RepoDAO");
var LangCommonDAO = require("../dao/LangCommonDAO");
var Async = require("async");

function getSingleLanguage() {
    LanguageDAO.getDistinctColoum("language", function (err, languages) {
        languages.forEach(function (language) {
            var single = {language: language.language};
            SingleDAO.add(single, function (err) {
                if(err) {
                    console.log(err);
                }
                console.log(language.language + " success!!!");
            })
        })
    });
}

function getYear() {
    LanguageDAO.findAll("id", 1, 1000000, function (err, languagesOrg) {
        var languages = [];
        
        languagesOrg.forEach(function (language) {
            if(language.year == 0) {
                languages.push(language);
            }
        });
        
        Async.forEachLimit(languages, 200, function (language, callback) {
            setTimeout(function () {
                getEach(language);
                callback();
            }, 500);
        }, function (err) {
            console.log("success")
        });
        
        function getEach(language) {
            var full_name = language.repo_full_name;
            RepoDAO.findOneByColumn("full_name", full_name, function (err, repo) {
                if(err) {
                    console.log("data err");
                }
                // console.log(repo.created_at);
                var date = new Date(repo.created_at);
                language.year = date.getFullYear();
                // console.log(language.year)
                LanguageDAO.update(language, function (err) {
                    if(err) {
                        console.log("data err");
                    }
                    console.log(language.repo_full_name+" "+language.language);
                })
            })
        }
    })
}

function getRest() {
    LanguageDAO.findAll("id", 1, 1000000, function (err, languagesOrg) {
        var languages = [];

        languagesOrg.forEach(function (language) {
            if (language.year == 0) {
                languages.push(language);
            }
        });
        
        console.log(languages.length);
    });
}

function getConnection() {
    SingleDAO.findAll("language",1, 10000, function (err, languages) {
        Async.forEachSeries(languages, function (lang1, callback) {
            Async.forEach(languages,function (lang2, callback) {
                
                if(lang1.language != lang2.language) {
                    LanguageDAO.countCommonRepo(lang1.language, lang2.language, function (count) {
                        console.log(lang1.language + "--" + lang2.language + " " + count);
                        var langObj = {lang1: lang1.language, lang2: lang2.language, common_repo_num:count};
                        LangCommonDAO.add(langObj, function (err) {
                            if(err) {
                                console.log(lang1.language + "--" + lang2.language + " " + err);
                            } else {
                                console.log(lang1.language + "--" + lang2.language + " success");
                            }
                            callback();
                        })
                    })
                } else {
                    callback();
                }
                
            }, function (err) {
                callback();
            })
        })
    })
}

// getYear();
// getRest();

getConnection();