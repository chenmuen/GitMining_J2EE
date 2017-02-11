/**
 * Created by chenm on 2016/5/20.
 */

var SequelizeAuto = require('sequelize-auto');
var auto = new SequelizeAuto('gmdbnew', 'root', 'chenmuen', {
    host:"localhost",
    port: 3306,
    dialect: 'mysql',
    tables: ['repo']
});

auto.run(function (err) {
    if (err) throw err;

    console.log(auto.tables); // table list
    console.log(auto.foreignKeys); // foreign key list
});