<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
	<c:param name="title" value="Repos Analysis | Languist"/>
</c:import>
${head}

<body>

<div class="app">

	<c:import url="./_topbar.jsp" var="topbar">
		<c:param name="searchType" value="Repo"/>
	</c:import>
	${topbar}

	<c:import url="./_sidebar.jsp" var="sidebar">
		<c:param name="activeItem" value="Repo-Analysis"/>
	</c:import>
	${sidebar}

	<div class="main">
		<div class="header">
			<h1>Repos Analysis</h1>
			<div class="options">
			</div>
		</div>
		<div class="content">
			<div class="list">
				<div class="item item-grids">
					<div class="item-title grid-1-1">Star-Score Scatter Diagram</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>General</div>
						<div style="height: 420px;" id="star-score"></div>
						<div>alpha = 0.7</div>
					</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>Sample</div>
						<div style="height: 420px;" id="star-score-sample"></div>
						<div>P = 4.2780; F = 0.0185</div>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="list">
				<div class="item item-grids">
					<div class="item-title grid-1-1">Fork-Score Scatter Diagram</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>General</div>
						<div style="height: 420px;" id="fork-score"></div>
						<div>alpha = 0.7</div>
					</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>Sample</div>
						<div style="height: 420px;" id="fork-score-sample"></div>
						<div>P = 4.2780; F = 0.0185</div>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="list">
				<div class="item item-grids">
					<div class="item-title grid-1-1">Contributor-Score Scatter Diagram</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>General</div>
						<div style="height: 420px;" id="contributor-score"></div>
						<div>alpha = 0.7</div>
					</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>Sample</div>
						<div style="height: 420px;" id="contributor-score-sample"></div>
						<div>P = 4.2780; F = 0.0185</div>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="list">
				<div class="item item-grids">
					<div class="item-title grid-1-1">Issue-Score Scatter Diagram</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>General</div>
						<div style="height: 420px;" id="issue-score"></div>
						<div>alpha = 0.7</div>
					</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>Sample</div>
						<div style="height: 420px;" id="issue-score-sample"></div>
						<div>P = 4.2780; F = 0.0185</div>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="list">
				<div class="item item-grids">
					<div class="item-title grid-1-1">Size-Score Scatter Diagram</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>General</div>
						<div style="height: 420px;" id="size-score"></div>
						<div>alpha = 0.7</div>
					</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>Sample</div>
						<div style="height: 420px;" id="size-score-sample"></div>
						<div>P = 4.2780; F = 0.0185</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}
<%--<script type="text/javascript">--%>
	<%--var starForkDataAll = ${starForkDataAll};--%>
	<%--var starForkFormatter = '${starForkFormatter}';--%>
	<%--var starForkXName = '${starForkXName}';--%>
	<%--var starForkYName = '${starForkYName}';--%>
	<%--var starForkStartC = ${starForkStartCoord};--%>
	<%--var starForkEndC = ${starForkEndCoord};--%>
	<%--var starForkXMax = ${starForkXMax};--%>
	<%--var starForkYMax = ${starForkYMax};--%>
<%--</script>--%>
<script src="/assets/js/repo-analysis.js"></script>

</body>
</html>