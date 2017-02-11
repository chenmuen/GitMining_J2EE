<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
	<c:param name="title" value="Repo | Languist"/>
</c:import>
${head}

<body>

<div class="app">

	<c:import url="./_topbar.jsp" var="topbar">
		<c:param name="searchType" value="Repo"/>
	</c:import>
	${topbar}

	<c:import url="./_sidebar.jsp" var="sidebar">
		<c:param name="activeItem" value="Repo-Repo"/>
	</c:import>
	${sidebar}

	<div class="main">
		<div class="header" id="put_header">
			<%--<h1><a>${owner}</a> / ${name}</h1>--%>
			<%--<p>${description}</p>--%>
			<%--<p>Updated on ${updateTime}</p>--%>
			<%--<div class="input-url">--%>
				<%--<input type="text" value="${cloneUrl}">--%>
				<%--<a><i class="fa fa-clipboard fa-fw" aria-hidden="true"></i></a>--%>
				<%--<a><i class="fa fa-github fa-fw" aria-hidden="true"></i></a>--%>
			<%--</div>--%>
		</div>
		<div class="content">
			<div class="board" id="put_component">
				<div class="card grid-1-5 grid-1-1-sm grid-1-3-md">
					<div class="card-wrapper">
						<div class="card-title">Statistics</div>
						<div class="card-content" style="height: 292px">
							<div class="stats" id="put_stats">
								<%--<div class="stat">--%>
									<%--<i class="fa fa-star fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">${stars}</div><br>--%>
									<%--<div class="stat-key">STARS</div>--%>
								<%--</div>--%>
								<%--<div class="stat">--%>
									<%--<i class="fa fa-code-fork fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">${forks}</div><br>--%>
									<%--<div class="stat-key">FORKS</div>--%>
								<%--</div>--%>
								<%--<div class="stat">--%>
									<%--<i class="fa fa-users fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">${contributors}</div><br>--%>
									<%--<div class="stat-key">CONTRIBUTORS</div>--%>
								<%--</div>--%>
								<%--<div class="stat">--%>
									<%--<i class="fa fa-eye fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">${subscribers}</div><br>--%>
									<%--<div class="stat-key">SUBSCRIBERS</div>--%>
								<%--</div>--%>
								<%--<div class="stat">--%>
									<%--<i class="fa fa-wrench fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">${collaborators}</div><br>--%>
									<%--<div class="stat-key">COLLABORATORS</div>--%>
								<%--</div>--%>
							</div>
						</div>
					</div>
				</div>

				<%--雷达图score--%>
				<div class="card grid-2-5 grid-1-1-sm grid-2-3-md">
					<div class="card-wrapper">
						<div class="card-title">Score</div>
						<div class="card-content">
							<div style="height: 292px" id="repoScore">
							</div>
						</div>
					</div>
				</div>

				<%--饼图language--%>
				<div class="card grid-2-5 grid-1-1-sm grid-1-1-md">
					<div class="card-wrapper">
						<div class="card-title">Language</div>
						<div class="card-content">
							<div style="height: 292px" id="repoLan">
							</div>
						</div>
					</div>
				</div>
				<%--折线图addition and deletion--%>
				<%--<div class="card grid-1-1">--%>
					<%--<div class="card-wrapper">--%>
						<%--<div class="card-title">Repository's Addition and Deletion</div>--%>
						<%--<div class="card-content">--%>
							<%--<div style="height: 350px" id="repoAD">--%>
							<%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>

				<span id="ad_punch"></span>

				<%--punch card--%>
				<%--<div class="card grid-1-1">--%>
					<%--<div class="card-wrapper">--%>
						<%--<div class="card-title">Repository's Punch Card</div>--%>
						<%--<div class="card-content">--%>
							<%--<div style="height: 350px" id="repoPC">--%>
							<%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
				<span id="punch_com"></span>

				<%--different users' commit--%>
				<%--<div class="card grid-1-1">--%>
					<%--<div class="card-wrapper" id="put_dif_com">--%>
						<%--<div class="card-title">Different Members' Commit</div>--%>
						<%--<div class="card-content grids">--%>
							<%--<c:forEach items="${memName}" var="name">--%>
								<%--<div style="height: 300px" id="${name}" class="grid-1-3 grid-1-1-sm grid-1-2-md"></div>--%>
							<%--</c:forEach>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
				<span id="com_relate"></span>
				<%--<div class="card grid-1-2 grid-1-1-sm">--%>
					<%--<div class="card-wrapper" id="put_related_tags">--%>
						<%--<div class="card-title">--%>
							<%--Related Repositories by Tags--%>
						<%--</div>--%>
						<%--<div class="card-content">--%>
							<%--<c:forEach items="${relatedsByTags}" var="item">--%>
								<%--<div class="item">--%>
									<%--<a class="item-title">${item.fullName}</a>--%>
									<%--<div class="item-line">--%>
										<%--<span><i class="fa fa-star fa-fw" aria-hidden="true"></i>${item.stars}</span>--%>
										<%--<span><i class="fa fa-code-fork fa-fw" aria-hidden="true"></i>${item.forks}</span>--%>
									<%--</div>--%>
								<%--</div>--%>
							<%--</c:forEach>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>

				<%--<div class="card grid-1-2 grid-1-1-sm">--%>
					<%--<div class="card-wrapper" id="put_related_owners">--%>
						<%--<div class="card-title">--%>
							<%--Related Repositories by Owner--%>
						<%--</div>--%>
						<%--<div class="card-content">--%>
							<%--<c:forEach items="${relatedsByOwner}" var="item">--%>
								<%--<div class="item">--%>
									<%--<a class="item-title">${item.fullName}</a>--%>
									<%--<div class="item-line">--%>
										<%--<span><i class="fa fa-star fa-fw" aria-hidden="true"></i>${item.stars}</span>--%>
										<%--<span><i class="fa fa-code-fork fa-fw" aria-hidden="true"></i>${item.forks}</span>--%>
									<%--</div>--%>
								<%--</div>--%>
							<%--</c:forEach>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>

			</div>
		</div>
	</div>

</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}

<%--<script type="text/javascript">--%>
<%--//	雷达图--%>
	<%--var scoreIndicator = ${scoreIndicator};--%>
	<%--var scoreValue = ${values};--%>
<%--//饼图 language--%>
	<%--var lanName = ${lanName};--%>
	<%--var lanData = ${lanData};--%>

<%--//Addition and Deletion--%>
	<%--var adDate = ${adDate};--%>
	<%--var aData = ${aData};--%>
	<%--var dData = ${dData};--%>
<%--//punch card--%>
	<%--var punchData = ${punchData};--%>
<%--//different members' commits--%>
	<%--var memName = ${memName};--%>
	<%--var memCDate = ${memCDate};--%>
	<%--var memData = ${memData};--%>
<%--</script>--%>
<script src="assets/js/clipboard.js"></script>
<script src="assets/js/repo.js"></script>

</body>
</html>