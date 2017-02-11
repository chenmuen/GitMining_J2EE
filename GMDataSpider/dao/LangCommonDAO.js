/**
 * Created by chenm on 2016/6/9.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var LangCommon = require("../models/langcommon")(sequelize, Sequelize);

function LangCommonDAO() {}

LangCommonDAO.prototype = new BaseDAO(LangCommon);



module.exports = new LangCommonDAO();