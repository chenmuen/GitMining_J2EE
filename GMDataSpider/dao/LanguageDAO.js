/**
 * Created by chenm on 2016/5/24.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");

var BaseDAO = require("./BaseDAO");
var Language = require("../models/language")(sequelize, Sequelize);

function LanguageDAO() {}

LanguageDAO.prototype = new BaseDAO(Language);

LanguageDAO.prototype.countCommonRepo = function (lang1, lang2, callback) {
    var lang1 = lang1.replace("\'", "\'\'");
    var lang2 = lang2.replace("\'", "\'\'");
    sequelize.query("SELECT count(*) FROM `language` lang1 left join `language` lang2 on lang1.repo_full_name = lang2.repo_full_name where  lang1.language = '"+lang1+"' and lang2.language = '"+lang2+"'",{ type: sequelize.QueryTypes.SELECT})
        .then(function (counts) {
            callback(counts[0]['count(*)']);
        })
};

module.exports = new LanguageDAO();
