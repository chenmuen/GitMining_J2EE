/**
 * Created by chenm on 2016/6/2.
 */
var Async = require("async");
var request = require("request");
var config = require('./config');
var RepoDAO = require('./dao/RepoDAO');
var RateLimitUtil = require('./util/rate_limit_util');


RepoDAO.findAll("stars_count", 1, 1000, function (err, reposOrg) {
    var repoList = reposOrg;

    Async.forEachLimit(repoList, 30, function (repo, callback) {
        setTimeout(function () {
            getEachData(repo, 0, 1);
            callback();
        }, 5000);
    });

    function getEachData(repo, pullBase, pullPage) {
        var full_name = repo.full_name;

        console.log("current: " + RateLimitUtil.current);

        request(config.git_api_base + "repos/" + full_name + "/pulls?page=" + pullPage,
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
                if (RateLimitUtil.changeAccount()) {
                    console.log(full_name + ' change account');
                    // console.log("current: " + RateLimitUtil.current);
                    getEachData(repo, pullBase, pullPage);
                } else {
                    console.log(full_name + ' wait~~~');
                    setTimeout(function () {
                        getEachData(repo, pullBase, pullPage);
                    }, 600000);
                }

            }
        )
    }
});