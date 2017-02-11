/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('contributorcommits', {
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
		contributor_login: {
			type: DataTypes.STRING,
			allowNull: false
		},
		total: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		}
	}, {
		tableName: 'contributorcommits',
		createdAt: false,
		updatedAt: false
	});
};
