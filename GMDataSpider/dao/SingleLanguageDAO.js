/**
 * Created by chenm on 2016/6/3.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var SingleLanguage = require("../models/singlelanguage")(sequelize, Sequelize);

function SingleLanguageDAO() {}

SingleLanguageDAO.prototype = new BaseDAO(SingleLanguage);

module.exports = new SingleLanguageDAO(); 