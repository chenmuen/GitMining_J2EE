<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
	<c:param name="title" value="Repos | Languist"/>
</c:import>
${head}

<body>

<div class="app">

	<c:import url="./_topbar.jsp" var="topbar">
		<c:param name="searchType" value="Repo"/>
	</c:import>
	<%--<form action="/repos" method="post">--%>
		${topbar}
	<%--</form>--%>

	<c:import url="./_sidebar.jsp" var="sidebar">
		<c:param name="activeItem" value="Repo-Repositories"/>
	</c:import>
	${sidebar}

	<div class="main">
		<div class="header">
			<h1>Repositories<span class="btn-filter on"><i class="fa fa-filter" aria-hidden="true"></i>Filter</span></h1>
			<div class="filters-wrapper">
				<%--<div class="filters-section">--%>
				<div class="filters-section" id="put_categories">
					<%--<h2>Category: </h2>--%>
					<%--<c:forEach items="${categories}" var="category">--%>
					<%--<a class="filter${category.equals(currentCategory) ? ' active' : ''}">${category}</a>--%>
					<%--</c:forEach>--%>
				</div>
				<div class="filters-section" id="put_languages">
					<%--<h2>Language: </h2>--%>
					<%--<c:forEach items="${languages}" var="language">--%>
					<%--<a class="filter${language.equals(currentLanguage) ? ' active' : ''}">${language}</a>--%>
					<%--</c:forEach>--%>
				</div>
				<div class="filters-section" id="put_years">
					<%--<h2>Create Time: </h2>--%>
					<%--<c:forEach items="${years}" var="year">--%>
					<%--<a class="filter${year.equals(currentYear) ? ' active' : ''}">${year}</a>--%>
					<%--</c:forEach>--%>
				</div>
			</div>
			<div class="options" id="put_options">
				<%--<a class="option${currentSort == 0 ? ' active' : ''}">ALL</a>--%>
				<%--<a class="option${currentSort == 1 ? ' active' : ''}">STARS ▼</a>--%>
				<%--<a class="option${currentSort == 2 ? ' active' : ''}">FORKS ▼</a>--%>
				<%--<a class="option${currentSort == 3 ? ' active' : ''}">CONTRIBUTORS ▼</a>--%>
			</div>
		</div>
		<div class="content">
			<div class="board">
				<div class="card grid-3-4 grid-2-3-md grid-1-1-sm">
					<div class="card-wrapper" id="put_list">
						<%--<c:forEach items="${list}" var="item">--%>
						<%--<div class="item">--%>
							<%--<a class="item-title">${item.fullName}</a>--%>
							<%--<div class="item-line">--%>
								<%--<span><i class="fa fa-star" aria-hidden="true"></i>${item.stars}</span>--%>
								<%--<span><i class="fa fa-code-fork" aria-hidden="true"></i>${item.forks}</span>--%>
								<%--<span><i class="fa fa-users" aria-hidden="true"></i>${item.contributors}</span>--%>
							<%--</div>--%>
							<%--<div class="item-line">--%>
								<%--${item.description}--%>
							<%--</div>--%>
							<%--<div class="item-line item-line-s">--%>
								<%--Updated on ${item.updateTime}--%>
							<%--</div>--%>
						<%--</div>--%>
						<%--</c:forEach>--%>
					</div>
				</div>
				<div class="card grid-1-4 grid-1-3-md hidden-sm">
					<%--<div class="card-wrapper">--%>
						<%--<div class="card-title">--%>
							<%--Featured Recommendations--%>
						<%--</div>--%>
						<%--<div class="card-content" id="put_recommends">--%>
						<%--<c:forEach items="${recommendations}" var="item">--%>
							<%--<div class="item">--%>
								<%--<a class="item-title">${item.fullName}</a>--%>
								<%--<div class="item-line">--%>
									<%--<span><i class="fa fa-star fa-fw" aria-hidden="true"></i>${item.stars}</span>--%>
									<%--<span><i class="fa fa-code-fork fa-fw" aria-hidden="true"></i>${item.forks}</span>--%>
								<%--</div>--%>
								<%--<div class="item-line line-2">--%>
									<%--${item.description}--%>
								<%--</div>--%>
							<%--</div>--%>
						<%--</c:forEach>--%>
						<%--</div>--%>
					<%--</div>--%>
					<div class="card-wrapper">
						<div class="card-title">
							Top 5 Repositories
						</div>
						<div class="card-content" id="put_top5">
						<%--<c:forEach items="${tops}" var="item">--%>
							<%--<div class="item">--%>
								<%--<a class="item-title">${item.fullName}</a>--%>
								<%--<div class="item-line">--%>
									<%--<span><i class="fa fa-star fa-fw" aria-hidden="true"></i>${item.stars}</span>--%>
									<%--<span><i class="fa fa-code-fork fa-fw" aria-hidden="true"></i>${item.forks}</span>--%>
								<%--</div>--%>
								<%--<div class="item-line">--%>
									<%--${item.description}--%>
								<%--</div>--%>
							<%--</div>--%>
						<%--</c:forEach>--%>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="pages" id="put_pages">
				<%--<a class="page">&lt;</a>--%>
				<%--<c:forEach items="${pages}" var="page">--%>
				<%--<a class="page${page == currentPage ? ' active' : ''}${String.valueOf(page).equals("...") ? ' disable' : ''}">${page}</a>--%>
				<%--</c:forEach>--%>
				<%--<a class="page">&gt;</a>--%>
			</div>
		</div>
	</div>

</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}

<script src="assets/js/repos.js"></script>

</body>
</html>