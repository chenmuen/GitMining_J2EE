/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('punch', {
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
		day: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		hour: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		commits: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		}
	}, {
		tableName: 'punch',
		createdAt: false,
		updatedAt: false
	});
};
