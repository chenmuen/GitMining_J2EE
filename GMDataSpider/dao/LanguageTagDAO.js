/**
 * Created by chenm on 2016/6/8.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var LanguageTag = require("../models/languagetag")(sequelize, Sequelize);

function LanguageTagDAO() {}

LanguageTagDAO.prototype = new BaseDAO(LanguageTag);

module.exports = new LanguageTagDAO();