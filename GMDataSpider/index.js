/**
 * Created by chenm on 2016/5/18.
 */
var Sequelize = require("sequelize");
var sequelize = require('./sequelize');
var config = require('./config');

var User = require("./models/user");

var UserDAO = require("./dao/UserDAO");

var request = require('request');

// UserDAO.findOneByColumn('login', 'mojombo', function (model) {
//     console.log(model.get({plain: true}));
// });

// UserDAO.findAll('starred_count',1,50, function (err, users) {
//     // users.forEach(function (user) {
//     //     console.log(user.get({plain: true}));
//     // })
//     if(err) {
//         console.log(err);
//     }else {
//         users.forEach(function (user) {
//
//         })
//     }
// });
//
// var user = {
//     login: 'hhh',
//     followers_count: 10,
//     followings_count: 10,
//     starred_count: 10,
//     subscription_count:10,
//     public_gists: 10,
//     public_repo: 10,
//     create_at:100000000,
//     type: Sequelize.ENUM('User')
// };
//
// UserDAO.add(user, function (err) {
//     if(err) {
//         console.log(err.name);
//     }else {
//         console.log('success');
//     }
// });
//
// request(config['git_api_base'] + "users/mojombo/repos",
//     {
//         auth: {
//             user: 'chenmuen',
//             pass: 'Chenmuen5817281',
//             sendImmediately: true
//         },
//         headers: {
//             'User-Agent': 'request'
//         }
//     },
//     function (error, response, body) {
//         if(error) {
//             console.log(error.name);
//         } else {
//             if(response.headers['x-ratelimit-remaining'] > 0) {
//                 console.log('success');
//             }
//         }
//     }
// );

var created_at = "2014-11-20T06:42:06Z".replace("T", " ").replace("Z","");
var time = (new Date(Date.parse(created_at.replace(/-/g, "/")))).getTime();

console.log(time);
