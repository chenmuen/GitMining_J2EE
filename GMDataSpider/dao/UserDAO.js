/**
 * Created by chenm on 2016/5/21.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var User = require("../models/user")(sequelize,Sequelize);
var BaseDAO = require("./BaseDAO");

function UserDAO() {}

UserDAO.prototype = new BaseDAO(User);

module.exports = new UserDAO();