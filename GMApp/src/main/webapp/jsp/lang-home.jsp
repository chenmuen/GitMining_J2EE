<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
    <c:param name="title" value="Home | Languist"/>
</c:import>
${head}

<body>

<div class="app">

    <c:import url="./_topbar.jsp" var="topbar">
        <c:param name="searchType" value="Language"/>
    </c:import>
    ${topbar}

    <c:import url="./_sidebar.jsp" var="sidebar">
        <c:param name="activeItem" value="Lang-Home"/>
    </c:import>
    ${sidebar}

    <div class="main">

        <div class="cover">
            <h1>Hi, Languist!</h1>
            <span>Know your coding path and start.</span>
        </div>

        <div class="content">
            <div class="board">

                <!-- Web -->
                <div class="card grid-1-2 grid-1-1-md">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Wanna Create a Website?
                            <a><i class="fa fa-link" aria-hidden="true"></i></a>
                        </div>
                        <div class="card-content card-grids" id="put_weblist">
                            <%--HTML CSS JavaScript PHP Java--%>
                            <%--<c:forEach items="${listWeb}" var="item">--%>
                                <%--<div class="card grid-1-3 grid-1-2-lg grid-1-3-md grid-1-2-sm-min grid-1-1-xs">--%>
                                    <%--<a class="card-lang-s card-blue">--%>
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
                    </div>
                </div>
                <!-- Web End -->

                <!-- App -->
                <div class="card grid-1-2 grid-1-1-md">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Wanna Bulid an App?
                            <a><i class="fa fa-link" aria-hidden="true"></i></a>
                        </div>
                        <div class="card-content card-grids" id="put_applist">
                            <%--Objective-C Swift Java JavaScript C#--%>
                            <%--<c:forEach items="${listApp}" var="item">--%>
                                <%--<div class="card grid-1-3 grid-1-2-lg grid-1-3-md grid-1-2-sm-min grid-1-1-xs">--%>
                                    <%--<a class="card-lang-s card-blue">--%>
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
                    </div>
                </div>
                <!-- App End -->

                <!-- Trends -->
                <div class="card grid-2-3 grid-1-1-md">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Trends
                            <a><i class="fa fa-link" aria-hidden="true"></i></a>
                        </div>
                        <div class="card-content">
                            <div id="ranking-chart" style="height: 400px;">
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Trends End -->

                <!-- Hall of Fame -->
                <div class="card grid-1-3 grid-1-1-md">
                    <div class="card-wrapper">
                        <div class="card-title">Hall of Fame</div>
                        <div class="card-list card-grids">
                            <div class="grid-1-1 grid-1-2-md grid-1-1-sm-min" id="put_fame1">
                                <%-- Hall of Fame 1~5 --%>
                                <%--<c:forEach items="${listHallOfFame1}" var="item">--%>
                                    <%--<a href="#" class="ranking-item">--%>
                                        <%--<span class="rank"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i></span>--%>
                                        <%--<span>${item.name}</span>--%>
                                        <%--<span class="pull-right">${item.year}</span>--%>
                                    <%--</a>--%>
                                <%--</c:forEach>--%>
                            </div>
                            <div class="grid-1-1 grid-1-2-md grid-1-1-sm-min" id="put_fame2">
                                <%-- Hall of Fame 6~10 --%>
                                <%--<c:forEach items="${listHallOfFame2}" var="item">--%>
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
                <!-- Hall of Fame End -->

                <!-- Quote -->
                <div class="card grid-1-1">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Quote
                            <a><i class="fa fa-link" aria-hidden="true"></i></a>
                        </div>
                        <div class="card-content">
                            <div class="card-quote">
                                <p class="quote-content">"${quoteContent}"</p>
                                <c:if test="${!empty quoteFrom}"><p class="quote-from">${quoteFrom}</p></c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Quote End -->

                <!-- Lang Set -->
                <div class="card grid-1-4 grid-1-2-lg grid-1-1-sm-min">
                    <div class="card-wrapper">
                        <div class="card-title">Objective-Oriented</div>
                        <div class="card-list" id="put_oriented">
                            <%--<c:forEach items="${listObjectiveOriented}" var="item">--%>
                                <%--<a href="#" class="ranking-item">--%>
                                    <%--<span class="rank">${item.rank}</span>--%>
                                    <%--<span>${item.name}</span>--%>
                                    <%--<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>${item.rating}%</span>--%>
                                <%--</a>--%>
                            <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>

                <div class="card grid-1-4 grid-1-2-lg grid-1-1-sm-min">
                    <div class="card-wrapper">
                        <div class="card-title">Functional</div>
                        <div class="card-list" id="put_functionals">
                            <%--<c:forEach items="${listFunctional}" var="item">--%>
                                <%--<a href="#" class="ranking-item">--%>
                                    <%--<span class="rank">${item.rank}</span>--%>
                                    <%--<span>${item.name}</span>--%>
                                    <%--<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>${item.rating}%</span>--%>
                                <%--</a>--%>
                            <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>

                <div class="card grid-1-4 grid-1-2-lg grid-1-1-sm-min">
                    <div class="card-wrapper">
                        <div class="card-title">Imperative</div>
                        <div class="card-list" id="put_imperatives">
                            <%--<c:forEach items="${listImperative}" var="item">--%>
                                <%--<a href="#" class="ranking-item">--%>
                                    <%--<span class="rank">${item.rank}</span>--%>
                                    <%--<span>${item.name}</span>--%>
                                    <%--<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>${item.rating}%</span>--%>
                                <%--</a>--%>
                            <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>

                <div class="card grid-1-4 grid-1-2-lg grid-1-1-sm-min">
                    <div class="card-wrapper">
                        <div class="card-title">Structured</div>
                        <div class="card-list" id="put_structured">
                            <%--<c:forEach items="${listStructured}" var="item">--%>
                                <%--<a href="#" class="ranking-item">--%>
                                    <%--<span class="rank">${item.rank}</span>--%>
                                    <%--<span>${item.name}</span>--%>
                                    <%--<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>${item.rating}%</span>--%>
                                <%--</a>--%>
                            <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>
                <!-- Lang Set End -->
            </div>
        </div>
    </div>
</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}
<script src="assets/js/lang-home.js"></script>

</body>
</html>