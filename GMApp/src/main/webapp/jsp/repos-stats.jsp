<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
	<c:param name="title" value="Repos Statistics | Languist"/>
</c:import>
${head}

<body>

<div class="app">

	<c:import url="./_topbar.jsp" var="topbar">
		<c:param name="searchType" value="Repo"/>
	</c:import>
	${topbar}

	<c:import url="./_sidebar.jsp" var="sidebar">
		<c:param name="activeItem" value="Repo-Statistics"/>
	</c:import>
	${sidebar}

	<div class="main">
		<div class="header">
			<h1>Repos Statistics</h1>
			<div class="options">
			</div>
		</div>
		<div class="content">
			<div class="list">
				<div class="item">
					<div class="item-title">Repositories' Languages</div>
					<div style="height: 500px;" id="repoLan"></div>
				</div>
				<div class="item">
					<div class="item-title">Repositories' Create Time</div>
					<div style="height: 500px;" id="repoCT"></div>
				</div>
				<div class="item">
					<div class="item-title">Repositories' Forks</div>
					<div style="height: 500px;" id="repoFork"></div>
				</div>
				<div class="item">
					<div class="item-title">Repositories' Stars</div>
					<div style="height: 500px;" id="repoStar"></div>
				</div>
				<div class="item">
					<div class="item-title">Repositories' Contributions</div>
					<div style="height: 500px;" id="repoContr"></div>
				</div>
				<div class="item">
					<div class="item-title">Repositories' Collaborators</div>
					<div style="height: 500px;" id="repoColla"></div>
				</div>

			</div>
		</div>
	</div>

</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}

<script src="assets/js/repos-stats.js"></script>

</body>
</html>