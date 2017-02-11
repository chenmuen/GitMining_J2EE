/**
 * Created by chenm on 2016/5/21.
 */
var Sequelize = require("sequelize");
var sequelize = require("../sequelize");
function BaseDAO(ModelTemp) {
    this.Model = ModelTemp;
    return this;
}

BaseDAO.prototype.add = function(obj, callback) {
    var model = this.Model.build(obj);
    model.save().then(function () {
        callback(null);
    }).catch(function(err) {
        callback(err);
    })
};

BaseDAO.prototype.update = function (model, callback) {
    model.save().then(function () {
        callback(null);
    }).catch(function (err) {
        callback(err);
    })
};

BaseDAO.prototype.updateColumn = function (col, val, new_col, new_val, callback) {
    sequelize.query("UPDATE " + this.Model.tableName + " SET " + new_col + "=" + new_val + " WHERE " + col + "='" + val+"'")
        .then(function () {
            callback(null);
        })
        .catch(function (err) {
            callback(err);
        });
};

BaseDAO.prototype.findById = function(id,callback) {
    this.Model.findById(id).then(function (account) {
        callback(account);
    });
};

BaseDAO.prototype.findOneByColumn = function(columnName, columnValue, callback) {
    var whereJson = {};
    whereJson[columnName] = columnValue;

    this.Model.findOne({
        where: whereJson
    }).then(function (model) {
        callback(null, model);
    }).catch(function(err) {
        callback(err, null);
    })
};

BaseDAO.prototype.findByColumn = function (colName, colVal, callback) {
    var whereJson = {};
    whereJson[colName] = colVal;

    this.Model.findAll({
        where: whereJson
    }).then(function (model) {
        callback(null, model);
    }).catch(function(err) {
        callback(err, null);
    })
};

BaseDAO.prototype.removeByColumn = function(columnName, columnValue, callback) {
    this.findOneByColumn(columnName,columnValue, function (err, model) {
        if(err) {
            console.log(err);
        } else {
            model.destroy().then(function () {
                callback();
            }).catch(function (err) {
                callback(err);
            })
        }
    })
};

BaseDAO.prototype.findAll = function (orderColumn, page, size, callback) {
    this.Model.findAll({
        order: orderColumn+" desc",
        limit: size,
        offset: (page-1)*size
    }).then(function (models) {
        callback(null, models);
    }).catch(function (err) {
        callback(err, null);
    })
};

// BaseDAO.prototype.findPropertyByColumn = function (property, column, page, size, callback) {
//     Sequelize.query
// }

BaseDAO.prototype.getDistinctColoum = function (column, callback) {
    sequelize.query("select distinct "+column+" from " + this.Model.getTableName(),
        { type: sequelize.QueryTypes.SELECT})
        .then(function (repos) {
            callback(null, repos);
        });
};

module.exports = BaseDAO;