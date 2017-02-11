/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('collaborator', {
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
		user_login: {
			type: DataTypes.STRING,
			allowNull: false
		}
	}, {
		tableName: 'collaborator',
		createdAt: false,
		updatedAt: false
	});
};
