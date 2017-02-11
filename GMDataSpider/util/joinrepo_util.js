/**
 * Created by chenm on 2016/6/3.
 */
var ContributorDAO = require("../dao/ContributorDAO");
var CollaboratorDAO = require("../dao/CollaboratorDAO");
var RepoDAO = require("../dao/RepoDAO");
var UserDAO = require("../dao/UserDAO");
var JoinDAO = require("../dao/JoinDAO");

var EventProxy = require("eventproxy");
var Async = require("async");

function addJoin() {
    var repoList = [];
    var userList = [];

    var ep = new EventProxy();
    ep.all("repo", "user", function () {
        // Async.forEachLimit(repoList, 50, function (repo, callback) {
        //     setTimeout(function () {
        //         dealRepo(repo);
        //         callback();
        //     }, 5000);
        // });
        //
        // function dealRepo (repo) {
        //     
        //
        //     ContributorDAO.findByColumn("repo_full_name", repo, function (err,contributors) {
        //         Async.forEach(contributors, function (contributor,cb) {
        //             if(contains(userList, contributor.user_login)) {
        //                 var join = {user_login: contributor.user_login, repo_full_name: repo};
        //                 JoinDAO.add(join, function (err) {
        //                     if(err) {
        //                         console.log(err);
        //                     }
        //                     cb();
        //                 })
        //             }
        //         }, function (err) {
        //             repoEP.emit("con");
        //         });
        //     });
        //    
        //     CollaboratorDAO.findByColumn("repo_full_name", repo, function (err,collaborators) {
        //         Async.forEach(collaborators, function (collaborator, cb) {
        //             if(contains(userList, collaborator.user_login)) {
        //                 var join = {user_login: collaborator.user_login, repo_full_name: repo};
        //                 JoinDAO.add(join, function (err) {
        //                     if(err) {
        //                         console.log (err);
        //                     }
        //                     cb();
        //                 })
        //             }
        //         }, function (err) {
        //             repoEP.emit("col");
        //         })
        //     });
        // }


        Async.forEachLimit(userList, 100, function (user, callback) {
            setTimeout(function () {
                var login = user;
                var repoEP = new EventProxy();
                repoEP.all("col", "con", function () {
                    console.log(login + " success!!!");
                    callback();
                });

                ContributorDAO.findByColumn("user_login", login, function (err, contributors) {
                    Async.forEach(contributors, function (contributor, cb) {
                        var join = {user_login: contributor.user_login, repo_full_name: contributor.repo_full_name};
                        JoinDAO.add(join, function (err) {
                            if (err) {
                                console.log(err);
                            }
                            cb();
                        })
                    }, function (err) {
                        repoEP.emit("con");
                    });
                });

                CollaboratorDAO.findByColumn("user_login", login, function (err, collaborators) {
                    Async.forEach(collaborators, function (collaborator, cb) {
                        var join = {user_login: collaborator.user_login, repo_full_name: collaborator.repo_full_name};
                        JoinDAO.add(join, function (err) {
                            if (err) {
                                console.log(err);
                            }
                            cb();
                        })
                    }, function (err) {
                        repoEP.emit("col");
                    });
                });
            }, 1000);
        }, function (err) {
            console.log("success!!!");
        })
    });

    // RepoDAO.findAll("stars_count", 1, 40000, function (err, repos) {
    //     repos.forEach(function (repo) {
    //         repoList.push((repo.full_name));
    //     });
    //     ep.emit("repo");
    // });

    ep.emit("repo");

    UserDAO.findAll("login", 2, 20000, function (err, users) {
        users.forEach(function (user) {
            userList.push(user.login);
        });
        // userList = users;
        console.log(userList.length);
        ep.emit("user");
    });
}

function contains(arr, obj) {
    var i = arr.length;
    while (i--) {
        if (arr[i] == obj) {
            return true;
        }
    }
    return false;
}

addJoin();