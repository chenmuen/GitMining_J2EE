/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('commit', {
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
		week: {
			type: DataTypes.BIGINT,
			allowNull: false
		},
		addtion: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		deletion: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		commits: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		}
	}, {
		tableName: 'commit',
		createdAt: false,
		updatedAt: false
	});
};
