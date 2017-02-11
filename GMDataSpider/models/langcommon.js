/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('langcommon', {
		id: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			primaryKey: true,
			autoIncrement: true
		},
		lang1: {
			type: DataTypes.STRING,
			allowNull: false
		},
		lang2: {
			type: DataTypes.STRING,
			allowNull: false
		},
		common_repo_num: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			defaultValue: '0'
		}
	}, {
		tableName: 'langcommon',
		createdAt: false,
		updatedAt: false
	});
};
