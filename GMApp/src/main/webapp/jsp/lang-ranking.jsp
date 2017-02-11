<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
    <c:param name="title" value="Languages Ranking | Languist"/>
</c:import>
${head}

<body>

<div class="app">

    <c:import url="./_topbar.jsp" var="topbar">
        <c:param name="searchType" value="Language"/>
    </c:import>
    ${topbar}

    <c:import url="./_sidebar.jsp" var="sidebar">
        <c:param name="activeItem" value="Lang-Ranking"/>
    </c:import>
    ${sidebar}

    <div class="main">
        <!-- Ranking -->
        <div class="cover">
            <h1>Language Ranking</h1>
            <span>Ranking in, trends out</span>
        </div>
        <div class="content">
            <div class="board">

                <div class="card grid-1-1">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Language Trends
                        </div>
                        <div class="card-content">
                            <div id="ranking-chart" style="height: 320px;">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card grid-3-8 grid-1-1-sm">
                    <div class="card-wrapper">
                        <div class="card-title">Ranking 1~10</div>
                        <div class="card-list" id="put_rank1">
                            <%--<c:forEach items="${listRanking1}" var="item">--%>
                                <%--<a href="#" class="ranking-item">--%>
                                    <%--<span class="rank">${item.rank}</span>--%>
                                    <%--<span>${item.name}</span>--%>
                                    <%--<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>${item.rating}%</span>--%>
                                <%--</a>--%>
                            <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>

                <div class="card grid-3-8 grid-1-1-sm">
                    <div class="card-wrapper">
                        <div class="card-title">Ranking 11~20</div>
                        <div class="card-list" id="put_rank2">
                            <%--<c:forEach items="${listRanking2}" var="item">--%>
                                <%--<a href="#" class="ranking-item">--%>
                                    <%--<span class="rank">${item.rank}</span>--%>
                                    <%--<span>${item.name}</span>--%>
                                    <%--<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>${item.rating}%</span>--%>
                                <%--</a>--%>
                            <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>

                <div class="card grid-2-8 grid-1-1-sm">
                    <div class="card-wrapper">
                        <div class="card-title">Hall of Fame</div>
                        <div class="card-list" id="put_listHall">
                            <%--<c:forEach items="${listHallOfFame}" var="item">--%>
                                <%--<a href="#" class="ranking-item">--%>
                                    <%--<span class="rank"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i></span>--%>
                                    <%--<span>${item.name}</span>--%>
                                    <%--<span class="pull-right">${item.year}</span>--%>
                                <%--</a>--%>
                            <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- Ranking End -->
    </div>
</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}
<script src="assets/js/lang-ranking.js"></script>



</body>
</html>