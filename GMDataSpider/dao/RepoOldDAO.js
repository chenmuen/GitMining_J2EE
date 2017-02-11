/**
 * Created by chenm on 2016/6/10.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var RepoOld = require('../models/repoold')(sequelize,Sequelize);

function RepoOldDAO() {}

RepoOldDAO.prototype = new BaseDAO(RepoOld);

module.exports = new RepoOldDAO();