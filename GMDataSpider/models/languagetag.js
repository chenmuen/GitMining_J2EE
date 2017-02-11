/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('languagetag', {
		id: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			primaryKey: true,
			autoIncrement: true
		},
		tag_name: {
			type: DataTypes.STRING,
			allowNull: true
		},
		language: {
			type: DataTypes.STRING,
			allowNull: true
		},
		is_app: {
			type: DataTypes.INTEGER(4),
			allowNull: true
		}
	}, {
		tableName: 'languagetag',
		createdAt: false,
		updatedAt: false
	});
};
