/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('languageyearstat', {
		language: {
			type: DataTypes.STRING,
			allowNull: false,
			primaryKey: true
		},
		year: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			primaryKey: true
		},
		repo_num: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			defaultValue: '0'
		},
		user_num: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			defaultValue: '0'
		}
	}, {
		tableName: 'languageyearstat',
		createdAt: false,
		updatedAt: false
	});
};
