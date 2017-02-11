/**
 * Created by chenm on 2016/5/21.
 */
var config = require('./config');

var Sequelize = require("sequelize");
var sequelize = new Sequelize(config['database'], config['user'], config['pass'], {
    host: config['host'],
    dialect: config['dialect'],
    pool: {
        max: 5,
        min: 0,
        idle: 10000
    },
    logging: false
});

module.exports = sequelize;