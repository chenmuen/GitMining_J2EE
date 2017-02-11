/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('joinrepo', {
		id: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			primaryKey: true,
			autoIncrement: true
		},
		user_login: {
			type: DataTypes.STRING,
			allowNull: false
		},
		repo_full_name: {
			type: DataTypes.STRING,
			allowNull: false
		}
	}, {
		tableName: 'joinrepo',
		createdAt: false,
		updatedAt: false
	});
};
