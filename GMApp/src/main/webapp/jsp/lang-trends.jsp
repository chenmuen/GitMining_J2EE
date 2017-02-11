<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
    <c:param name="title" value="Trends | Languist"/>
</c:import>
${head}

<body>

<div class="app${empty language ? '-vh' : ''}">


    <c:if test="${empty language}">
    <div class="topbar">
    </div>
    </c:if>

    <c:if test="${!empty language}">
    <c:import url="./_topbar.jsp" var="topbar">
        <c:param name="searchType" value="Language"/>
    </c:import>
    ${topbar}
    </c:if>

    <c:import url="./_sidebar.jsp" var="sidebar">
        <c:param name="activeItem" value="Lang-Trends"/>
    </c:import>
    ${sidebar}

    <div class="main">

        <c:if test="${empty language}">
        <!-- Trends Search -->
        <div class="cover-vh">
            <div class="mid-wrapper">
                <h1>Trends</h1>
                <span>Find the trends of the language.</span>
                <div class="search grid-1-2 grid-1-1-md">
                    <div class="input-search">
                        <input type="text" placeholder="Language" id="search-trends-bar">
                        <a class="input-addon"><i class="fa fa-search fa-fw" aria-hidden="true" id="trends-search"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Trends Search End -->
        </c:if>

        <c:if test="${!empty language}">
        <!-- Trends -->
        <div class="header">
            <h1>Trends of ${language}</h1>
        </div>
        <div class="content">
            <div class="board">
                <div class="card grid-1-1">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Ranking
                        </div>
                        <div id="ranking-chart" style="height: 320px;">
                        </div>
                    </div>
                </div>

                <div class="card grid-1-1">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Active Repositories
                        </div>
                        <div id="active-repos-chart" style="height: 320px;">
                        </div>
                    </div>
                </div>

                <div class="card grid-1-1">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Total Stars
                        </div>
                        <div id="total-stars-chart" style="height: 320px;">
                        </div>
                    </div>
                </div>

                <div class="card grid-1-1">
                    <div class="card-wrapper">
                        <div class="card-title">
                            New Forks per Repository
                        </div>
                        <div id="new-forks-per-repo-chart" style="height: 320px;">
                        </div>
                    </div>
                </div>

                <div class="card grid-1-1">
                    <div class="card-wrapper">
                        <div class="card-title">
                            New Watchers per Repository
                        </div>
                        <div id="new-watchers-per-repo-chart" style="height: 320px;">
                        </div>
                    </div>
                </div>

                <div class="card grid-1-1">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Open Issues per Repository
                        </div>
                        <div id="open-issue-per-chart" style="height: 320px;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Trends End -->
        </c:if>
    </div>
</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}
<script type="text/javascript">
    var langData = ${ranking.langData};
    var langYear = ${ranking.langYear};
</script>

<script src="assets/js/lang-trends.js"></script>
<script src="assets/js/lang-trends2.js"></script>

</body>
</html>