/**
 * Created by chenm on 2016/6/8.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var LanguageYear = require("../models/languageyearstat")(sequelize, Sequelize);

function LanguageYearDAO() {}

LanguageYearDAO.prototype = new BaseDAO(LanguageYear);

module.exports = new LanguageYearDAO(); 