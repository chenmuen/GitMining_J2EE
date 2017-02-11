/**
 * Created by chenm on 2016/6/3.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var Join = require("../models/joinrepo")(sequelize, Sequelize);

function JoinDAO() {}

JoinDAO.prototype = new BaseDAO(Join);

module.exports = new JoinDAO();