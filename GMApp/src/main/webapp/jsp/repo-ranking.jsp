<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
	<c:param name="title" value="Repo Ranking | Languist"/>
</c:import>
${head}

<body>

<div class="app">

	<c:import url="./_topbar.jsp" var="topbar">
		<c:param name="searchType" value="Repo"/>
	</c:import>
	${topbar}

	<c:import url="./_sidebar.jsp" var="sidebar">
		<c:param name="activeItem" value="Repo-Ranking"/>
	</c:import>
	${sidebar}

	<div class="main">

		<div class="header">
			<h1 id="put_title">Ranking of ${currentFilter} Repositories</h1>
			<div class="filters" id="put_filters">
				<c:forEach items="${filters}" var="filter">
				<a class="filter${filter.equals(currentFilter) ? ' active' : ''}">${filter}</a>
				</c:forEach>
			</div>
		</div>
		<div class="content">
			<div class="board">
				<div class="card grid-1-2 grid-1-1-sm">
					<div class="card-wrapper" id="put_list">
						<c:forEach items="${list}" var="item">
						<a  class="ranking-item">
							<span class="avatar"><img src="${item.avatar}"></span>
							<span>${item.rank}. ${item.owner} / ${item.name}</span>
							<%--<span class="hidden-md">${item.owner} / </span>--%>
							<%--<span>${item.name}</span>--%>
							<span class="pull-right"><i class="fa fa-star fa-fw" aria-hidden="true"></i>${item.stars}</span>
						</a>
						<span style="display: none">${item.fullname}</span>
						</c:forEach>
					</div>
				</div>
				<div class="card grid-1-2 grid-1-1-sm">
					<div class="card-wrapper" id="put_list2">
						<c:forEach items="${list2}" var="item">
						<a  class="ranking-item">
							<span class="avatar"><img src="${item.avatar}"></span>
							<span>${item.rank}. ${item.owner} / ${item.name}</span>
							<%--<span class="hidden-md">${item.owner} / </span>--%>
							<%--<span>${item.name}</span>--%>
							<span class="pull-right"><i class="fa fa-star fa-fw" aria-hidden="true"></i>${item.stars}</span>
						</a>
						<span style="display: none">${item.fullname}</span>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

	</div>

</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}

<script src="assets/js/repo-ranking.js"></script>

</body>
</html>