<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
    <c:param name="title" value="Languages | Languist"/>
</c:import>
${head}

<body>

<div class="app">

    <c:import url="./_topbar.jsp" var="topbar">
        <c:param name="searchType" value="Language"/>
    </c:import>
    ${topbar}

    <c:import url="./_sidebar.jsp" var="sidebar">
        <c:param name="activeItem" value="Lang-List"/>
    </c:import>
    ${sidebar}

    <div class="main">
        <!-- List -->
        <div class="header">
            <h1>Languages<span class="btn-filter on"><i class="fa fa-filter" aria-hidden="true"></i>Filter</span></h1>
            <div class="filters-wrapper">
                <div class="filters-section" id="put_categories">
                    <%--<h2>Category: </h2>--%>
                    <%--<c:forEach items="${categories}" var="category">--%>
                    <%--<a class="filter${category.equals(currentCategory) ? ' active' : ''}">${category}</a>--%>
                    <%--</c:forEach>--%>
                </div>
                <div class="filters-section" id="put_applications">
                    <%--<h2>Application: </h2>--%>
                    <%--<c:forEach items="${applications}" var="application">--%>
                    <%--<a class="filter${application.equals(currentApplication) ? ' active' : ''}">${application}</a>--%>
                    <%--</c:forEach>--%>
                </div>
            </div>
            <div class="options" id="put_options">
                <%--<a class="option active">ALL</a>--%>
                <%--<a class="option">REPOSITORIES ▼</a>--%>
                <%--<a class="option">DEVELOPERS ▼</a>--%>
            </div>
        </div>
        <div class="content">
            <div class="board" id="put_list">
                <%--<c:forEach items="${list}" var="item">--%>
                <%--<div class="card grid-1-5 grid-1-4-lg grid-1-3-md grid-1-2-sm-min grid-1-1-xs">--%>
                    <%--<a class="card-lang card-blue">--%>
                        <%--<div class="card-lang-top">--%>
                            <%--<h3>${item.name}</h3>--%>
                            <%--<hr>--%>
                        <%--</div>--%>
                        <%--<div class="card-lang-bottom">--%>
                            <%--<div><i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>${item.repoCount}<span>Repositories</span></div>--%>
                            <%--<div><i class="fa fa-users fa-fw" aria-hidden="true"></i>${item.devCount}<span>Developers</span></div>--%>
                        <%--</div>--%>
                    <%--</a>--%>
                <%--</div>--%>
                <%--</c:forEach>--%>
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
        <!-- List End -->
    </div>
</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}
<script src="assets/js/lang-list.js"></script>



</body>
</html>