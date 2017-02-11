<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
	<c:param name="title" value="Repo Evolution | Languist"/>
</c:import>
${head}

<body>

<div class="app">

	<c:import url="./_topbar.jsp" var="topbar">
		<c:param name="searchType" value="Repo"/>
	</c:import>
	${topbar}

	<c:import url="./_sidebar.jsp" var="sidebar">
		<c:param name="activeItem" value="Repo-Evolution"/>
	</c:import>
	${sidebar}

	<div class="main">

		<div class="header">
			<h1>Evolution of ${owner} / ${name}</h1>
			<div class="item-line">
				<span><i class="fa fa-star" aria-hidden="true"></i>${stars}</span>
				<span><i class="fa fa-code-fork" aria-hidden="true"></i>${forks}</span>
				<span><i class="fa fa-users" aria-hidden="true"></i>${contributors}</span>
			</div>
			<p>${description}</p>
		</div>
		<div class="content">
			<div class="board">
				<div class="card grid-1-1">
					<div class="card-wrapper">
						<!-- <div class="card-title">2012</div> -->
						<div class="card-content">
							<div id=""></div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}

<script>

</script>

</body>
</html>