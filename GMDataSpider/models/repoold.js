/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('repoold', {
		full_name: {
			type: DataTypes.STRING,
			allowNull: false,
			primaryKey: true
		},
		description: {
			type: DataTypes.TEXT,
			allowNull: true
		},
		url: {
			type: DataTypes.STRING,
			allowNull: false
		},
		clone_url: {
			type: DataTypes.STRING,
			allowNull: false
		},
		subscribers_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			defaultValue: '0'
		},
		forks_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		stars_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		contributors_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		collaborators_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		pullrequests_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		issues_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		size: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		updated_at: {
			type: DataTypes.BIGINT,
			allowNull: false
		},
		created_at: {
			type: DataTypes.BIGINT,
			allowNull: false
		}
	}, {
		tableName: 'repoold',
		createdAt: false,
		updatedAt: false
	});
};
