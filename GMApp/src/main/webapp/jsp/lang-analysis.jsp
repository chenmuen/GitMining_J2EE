<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
	<c:param name="title" value="Languages Analysis | Languist"/>
</c:import>
${head}

<body>

<div class="app">

	<c:import url="./_topbar.jsp" var="topbar">
		<c:param name="searchType" value="Language"/>
	</c:import>
	${topbar}

	<c:import url="./_sidebar.jsp" var="sidebar">
		<c:param name="activeItem" value="Lang-Analysis"/>
	</c:import>
	${sidebar}

	<div class="main">
		<div class="header">
			<h1>Languages Analysis</h1>
			<div class="options">
			</div>
		</div>
		<div class="content">
			<div class="list">
				<div class="item item-grids">
					<div class="item-title grid-1-1">Star-Fork Scatter Diagram</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>General</div>
						<div style="height: 420px;" id=""></div>
						<div>alpha = 0.7</div>
					</div>
					<div class="grid-1-2 grid-1-1-md">
						<div>Sample</div>
						<div style="height: 420px;" id=""></div>
						<div>P = 4.2780; F = 0.0185</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}

</body>
</html>