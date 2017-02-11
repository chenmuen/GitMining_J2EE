<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
	<c:param name="title" value="User | Languist"/>
</c:import>
${head}

<body>

<div class="app">

	<c:import url="./_topbar.jsp" var="topbar">
		<c:param name="searchType" value="User"/>
	</c:import>
	${topbar}

	<c:import url="./_sidebar.jsp" var="sidebar">
		<c:param name="activeItem" value="User-User"/>
	</c:import>
	${sidebar}

	<div class="main">
		<div class="header" id="put_header">
			<%--<div class="avatar"><img src="${avatar}"></div>--%>
			<%--<div class="sibling">--%>
				<%--<h1><a>${name}</a></h1>--%>
				<%--<p><i class="fa fa-map-marker fa-fw" aria-hidden="true"></i>${empty location ? "Unknown location" : location}</p>--%>
				<%--<p><i class="fa fa-envelope fa-fw" aria-hidden="true"></i>${empty email ? "Unknown email" : email}</p>--%>
				<%--<p><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Join on ${joinTime}</p>--%>
				<%--<div class="tags">--%>
					<%--<c:forEach items="${languages}" var="language">--%>
					<%--<a class="tag">${language}</a>--%>
					<%--</c:forEach>--%>
				<%--</div>--%>
			<%--</div>--%>
		</div>
		<div class="content">
			<div class="board">
				<div class="card grid-1-5 grid-1-1-sm grid-1-3-md">
					<div class="card-wrapper">
						<div class="card-title">Statistics</div>
						<div class="card-content" style="height: 292px">
							<div class="stats" id="put_stats">
								<%--<div class="stat">--%>
									<%--<i class="fa fa-star fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">${stars}</div><br>--%>
									<%--<div class="stat-key">STARRED</div>--%>
								<%--</div>--%>
								<%--<div class="stat">--%>
									<%--<i class="fa fa-github-alt fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">${repos}</div><br>--%>
									<%--<div class="stat-key">REPOSITORIES</div>--%>
								<%--</div>--%>
								<%--<div class="stat">--%>
									<%--<i class="fa fa-users fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">${followers}</div><br>--%>
									<%--<div class="stat-key">FOLLOWERS</div>--%>
								<%--</div>--%>
								<%--<div class="stat">--%>
									<%--<i class="fa fa-eye fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">${following}</div><br>--%>
									<%--<div class="stat-key">FOLLOWING</div>--%>
								<%--</div>--%>
								<%--<div class="stat">--%>
									<%--<i class="fa fa-code fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">${gists}</div><br>--%>
									<%--<div class="stat-key">GISTS</div>--%>
								<%--</div>--%>
							</div>
						</div>
					</div>
				</div>

				<div class="card grid-2-5 grid-1-1-sm grid-2-3-md">
					<div class="card-wrapper">
						<div class="card-title">Score</div>
						<div class="card-content">
							<div style="height: 292px" id="userScore">
							</div>
						</div>
					</div>
				</div>

				<div class="card grid-2-5 grid-1-1-sm grid-1-1-md">
					<div class="card-wrapper">
						<div class="card-title">Language</div>
						<div class="card-content">
							<div style="height: 292px" id="userLan">
							</div>
						</div>
					</div>
				</div>

				<div class="card grid-1-1">
					<div class="card-wrapper">
						<div class="card-title">User's Contribution</div>
						<div class="card-content">
							<div style="height: 350px" id="userContr">
							</div>
						</div>
					</div>
				</div>

				<div class="card grid-1-1">
					<div class="card-wrapper">
						<div class="card-title">User's Commits</div>
						<div class="card-content">
							<div style="height: 350px" id="userCommit">
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}

<%--<script type="text/javascript">--%>
	<%--//雷达图 score--%>
	<%--var scoreIndicator = ${scoreIndicator};--%>
	<%--var scoreValue = ${values};--%>
	<%--//饼图 language--%>
	<%--var lanName = ${lanName};--%>
	<%--var lanData = ${lanData};--%>
	<%--//柱状图 user's contribution--%>
	<%--var repoName = ${repoName};--%>
	<%--var contrData = ${contrData};--%>
	<%--//折线图 user's commit--%>
	<%--var commitData = ${commitData};--%>
<%--</script>--%>
<script src="assets/js/user.js"></script>

</body>
</html>