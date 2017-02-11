<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
   <c:param name="title" value="Users | Languist"/>
</c:import>
${head}

<body>

<div class="app">

<c:import url="./_topbar.jsp" var="topbar">
   <c:param name="searchType" value="User"/>
</c:import>
${topbar}

<c:import url="./_sidebar.jsp" var="sidebar">
   <c:param name="activeItem" value="User-Users"/>
</c:import>
${sidebar}

<div class="main">
	<div class="header">
		<h1>Users<span class="btn-filter on"><i class="fa fa-filter" aria-hidden="true"></i>Filter</span></h1>
		<div class="filters-wrapper">
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
			<%--<a class="option${currentSort == 1 ? ' active' : ''}">FOLLOWERS ▼</a>--%>
			<%--<a class="option${currentSort == 2 ? ' active' : ''}">REPOSITORIES ▼</a>--%>
		</div>
	</div>
	<div class="content">
		<div class="board">
			<div class="card grid-3-4 grid-2-3-md grid-1-1-sm">
				<div class="card-wrapper" id="put_list">
				<%--<c:forEach items="${list}" var="item">--%>
					<%--<div class="item">--%>
						<%--<div class="avatar"><img src="${item.avatar}"></div>--%>
						<%--<div class="sibling">--%>
							<%--<a class="item-title">${item.name}</a>--%>
							<%--<div class="item-line">--%>
								<%--<span><i class="fa fa-users fa-fw" aria-hidden="true"></i>${item.followers}</span>--%>
								<%--<span><i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>${item.repos}</span>--%>
							<%--</div>--%>
							<%--<div class="item-line">--%>
								<%--<c:if test="${!empty item.location}"><span><i class="fa fa-map-marker fa-fw" aria-hidden="true"></i>${item.location}</span></c:if>--%>
								<%--<c:if test="${!empty item.email}"><span><i class="fa fa-envelope fa-fw" aria-hidden="true"></i>${item.email}</span></c:if>--%>
								<%--<span><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Join on ${item.joinTime}</span>--%>
							<%--</div>--%>
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
					<%--<div class="card-content">--%>
					<%--<c:forEach items="${recommendations}" var="item">--%>
						<%--<div class="item">--%>
							<%--<div class="avatar avatar-s"><img src="${item.avatar}"></div>--%>
							<%--<div class="sibling">--%>
								<%--<a class="item-title">${item.name}</a>--%>
								<%--<div class="item-line">--%>
									<%--<span><i class="fa fa-users fa-fw" aria-hidden="true"></i>${item.followers}</span>--%>
									<%--<span><i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>${item.repos}</span>--%>
								<%--</div>--%>
							<%--</div>--%>
						<%--</div>--%>
					<%--</c:forEach>--%>
					<%--</div>--%>
				<%--</div>--%>
				<div class="card-wrapper">
					<div class="card-title">
						Top 5 Users
					</div>
					<div class="card-content" id="put_top5">
					<%--<c:forEach items="${tops}" var="item">--%>
						<%--<div class="item">--%>
							<%--<div class="avatar avatar-s"><img src="${item.avatar}"></div>--%>
							<%--<div class="sibling">--%>
								<%--<a class="item-title">${item.name}</a>--%>
								<%--<div class="item-line">--%>
									<%--<span><i class="fa fa-users fa-fw" aria-hidden="true"></i>${item.followers}</span>--%>
									<%--<span><i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>${item.repos}</span>--%>
								<%--</div>--%>
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

<script src="assets/js/users.js"></script>

</body>
</html>