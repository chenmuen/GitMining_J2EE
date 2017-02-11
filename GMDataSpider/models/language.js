/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('language', {
		id: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			primaryKey: true,
			autoIncrement: true
		},
		repo_full_name: {
			type: DataTypes.STRING,
			allowNull: false
		},
		language: {
			type: DataTypes.STRING,
			allowNull: false
		},
		size: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		year: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		}
	}, {
		tableName: 'language',
		createdAt: false,
		updatedAt: false
	});
};
