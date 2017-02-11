<div class="sidebar">
	<div class="logo">Languist</div>
	<div class="logo-sm">Lg</div>

	<div class="nav">
		<div class="nav-section">
			<div class="nav-title">LANG<span>UAGE</span></div>
			<a href="lang-home" class="nav-item${param.activeItem.equals('Lang-Home') ? ' active' : ''}">
				<i class="fa fa-home fa-fw" aria-hidden="true"></i>
				<span>Home</span>
			</a>
			<a href="lang-list" class="nav-item${param.activeItem.equals('Lang-List') ? ' active' : ''}">
				<i class="fa fa-terminal fa-fw" aria-hidden="true"></i>
				<span>Languages</span>
			</a>
			<a href="lang-trends" class="nav-item${param.activeItem.equals('Lang-Trends') ? ' active' : ''}">
				<i class="fa fa-line-chart fa-fw" aria-hidden="true"></i>
				<span>Trends</span>
			</a>
			<a href="lang-relation" class="nav-item${param.activeItem.equals('Lang-Relation') ? ' active' : ''}">
				<i class="fa fa-exchange fa-fw" aria-hidden="true"></i>
				<span>Relation</span>
			</a>
			<%--<a href="lang-analyze" class="nav-item${param.activeItem.equals('Lang-Analysis') ? ' active' : ''}">--%>
				<%--<i class="fa fa-magic fa-fw" aria-hidden="true"></i>--%>
				<%--<span>Analysis</span>--%>
			<%--</a>--%>
			<a href="lang-rank" class="nav-item${param.activeItem.equals('Lang-Ranking') ? ' active' : ''}">
				<i class="fa fa-trophy fa-fw" aria-hidden="true"></i>
				<span>Ranking</span>
			</a>
		</div>
		<div class="nav-section">
			<div class="nav-title">REPO<span>SITORY</span></div>
			<a href="repos" class="nav-item${param.activeItem.equals('Repo-Repositories') ? ' active' : ''}">
				<i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>
				<span>Repositories</span>
			</a>
			<a href="repos-statistic" class="nav-item${param.activeItem.equals('Repo-Statistics') ? ' active' : ''}">
				<i class="fa fa-bar-chart fa-fw" aria-hidden="true"></i>
				<span>Statistics</span>
			</a>
			<%--<a href="repos-analyze" class="nav-item${param.activeItem.equals('Repo-Analysis') ? ' active' : ''}">--%>
				<%--<i class="fa fa-magic fa-fw" aria-hidden="true"></i>--%>
				<%--<span>Analysis</span>--%>
			<%--</a>--%>
			<a href="repo-rank" class="nav-item${param.activeItem.equals('Repo-Ranking') ? ' active' : ''}">
				<i class="fa fa-trophy fa-fw" aria-hidden="true"></i>
				<span>Ranking</span>
			</a>
		</div>
		<div class="nav-section">
			<div class="nav-title">USER</div>
			<a href="users" class="nav-item${param.activeItem.equals('User-Users') ? ' active' : ''}">
				<i class="fa fa-users fa-fw" aria-hidden="true"></i>
				<span>Users</span>
			</a>
			<a href="users-statistic" class="nav-item${param.activeItem.equals('User-Statistics') ? ' active' : ''}">
				<i class="fa fa-pie-chart fa-fw" aria-hidden="true"></i>
				<span>Statistics</span>
			</a>
			<%--<a href="user-analysis" class="nav-item${param.activeItem.equals('User-Analysis') ? ' active' : ''}">--%>
				<%--<i class="fa fa-magic fa-fw" aria-hidden="true"></i>--%>
				<%--<span>Analysis</span>--%>
			<%--</a>--%>
			<a href="user-rank" class="nav-item${param.activeItem.equals('User-Ranking') ? ' active' : ''}">
				<i class="fa fa-trophy fa-fw" aria-hidden="true"></i>
				<span>Ranking</span>
			</a>
		</div>
	</div>
</div>