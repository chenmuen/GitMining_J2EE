/**
 * Created by chenm on 2016/5/22.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var Repo = require('../models/repo')(sequelize,Sequelize);

function RepoDAO() {}

RepoDAO.prototype = new BaseDAO(Repo);

module.exports = new RepoDAO();