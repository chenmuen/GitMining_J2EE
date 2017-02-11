/* jshint indent: 1 */

module.exports = function(sequelize, DataTypes) {
	return sequelize.define('user', {
		login: {
			type: DataTypes.STRING,
			allowNull: false,
			primaryKey: true
		},
		name: {
			type: DataTypes.STRING,
			allowNull: true
		},
		type: {
			type: DataTypes.ENUM('Organization','User'),
			allowNull: false
		},
		avatar_url: {
			type: DataTypes.STRING,
			allowNull: true
		},
		html_url: {
			type: DataTypes.STRING,
			allowNull: true
		},
		followers_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		followings_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		starred_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		subscription_count: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		public_gists: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		public_repo: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		email: {
			type: DataTypes.STRING,
			allowNull: true
		},
		location: {
			type: DataTypes.STRING,
			allowNull: true
		},
		blog: {
			type: DataTypes.STRING,
			allowNull: true
		},
		company: {
			type: DataTypes.STRING,
			allowNull: true
		},
		create_at: {
			type: DataTypes.BIGINT,
			allowNull: false
		},
		is_repo_fetched: {
			type: DataTypes.BIGINT(4),
			allowNull: false
		},
		is_old: {
			type: DataTypes.BIGINT(4),
			allowNull: false
		},
		is_star_fetched: {
			type: DataTypes.BIGINT(4),
			allowNull: false
		},
		is_watch_fetched: {
			type: DataTypes.BIGINT(4),
			allowNull: false
		}
	}, {
		tableName: 'user',
		createdAt: false,
		updatedAt: false
	});
};
