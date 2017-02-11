/**
 * Created by chenm on 2016/5/25.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var Contributor = require("../models/contributor")(sequelize, Sequelize);

function ContributorDAO() {}

ContributorDAO.prototype = new BaseDAO(Contributor);

module.exports = new ContributorDAO();