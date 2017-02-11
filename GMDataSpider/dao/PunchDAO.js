/**
 * Created by chenm on 2016/6/2.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var Punch = require("../models/punch")(sequelize, Sequelize);

function PunchDAO() {}

PunchDAO.prototype = new BaseDAO(Punch);

module.exports = new PunchDAO();