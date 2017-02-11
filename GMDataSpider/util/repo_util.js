/**
 * Created by chenm on 2016/6/10.
 */

var RepoDAO = require('../dao/RepoDAO');
var UserDAO = require("../dao/UserDAO");
var Async = require("async");

RepoDAO.findAll("stars_count",1,300000, function (err, repos) {
    var list = [];
    Async.forEachLimit(repos, 100, function (repo, callback) {
        var full_name = repo.full_name;
        var login = full_name.split('/')[0];

        setTimeout(function () {
            UserDAO.findOneByColumn("login", login, function (err, user) {
                if(!user) {
                    console.log(full_name);
                    list.push(full_name);
                } else {
                    console.log("found");
                }
                callback();
            });
        }, 1000)
    }, function (err) {
        if(!err) {
            console.log(list.length);
        }
    })
});