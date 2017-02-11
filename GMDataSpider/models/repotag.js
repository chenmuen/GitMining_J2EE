/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('repotag', {
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
		tag_name: {
			type: DataTypes.STRING,
			allowNull: false
		}
	}, {
		tableName: 'repotag',
		createdAt: false,
		updatedAt: false
	});
};
