/**
 * Created by chenm on 2016/5/25.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var Collaborator = require("../models/collaborator")(sequelize, Sequelize);

function CollaboratorDAO() {}

CollaboratorDAO.prototype = new BaseDAO(Collaborator);

module.exports = new CollaboratorDAO();