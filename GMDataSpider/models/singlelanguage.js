/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('singlelanguage', {
		language: {
			type: DataTypes.STRING,
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
		},
		wiki: {
			type: DataTypes.TEXT,
			allowNull: false
		},
		wiki_url: {
			type: DataTypes.STRING,
			allowNull: false
		}
	}, {
		tableName: 'singlelanguage',
		createdAt: false,
		updatedAt: false
	});
};
