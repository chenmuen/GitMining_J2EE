<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./_head.jsp" var="head">
    <c:param name="title" value="${language} | Languist"/>
</c:import>
${head}

<body>

<div class="app">

    <c:import url="./_topbar.jsp" var="topbar">
        <c:param name="searchType" value="Language"/>
    </c:import>
    ${topbar}

    <c:import url="./_sidebar.jsp" var="sidebar">
        <c:param name="activeItem" value="Lang"/>
    </c:import>
    ${sidebar}

    <div class="main">
        <!-- Lang -->
        <div class="cover" id="put_info" style="background-image: url('assets/images/cover-${cover}.png')">
            <h1>${language}</h1>
            <span>
                <c:forEach items="${applications}" var="application">
                    ${application}&nbsp;
                </c:forEach>
            </span>
            <div class="lang-stats">
                <div class="lang-stat">
                    <i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>
                    ${repoCount}
                    <span>${language} Repositories</span>
                </div>
                <div class="lang-stat">
                    <i class="fa fa-users fa-fw" aria-hidden="true"></i>
                    ${devCount}
                    <span>${language} Developers</span>
                </div>
                <%--&lt;%&ndash;<div class="lang-stat">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<i class="fa fa-google fa-fw" aria-hidden="true"></i>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;16,110,000,000&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<span>Google Items</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            </div>
            <div class="share">
                <a><i class="fa fa-facebook fa-fw" aria-hidden="true"></i></a>
                <a><i class="fa fa-twitter fa-fw" aria-hidden="true"></i></a>
                <a><i class="fa fa-weibo fa-fw" aria-hidden="true"></i></a>
            </div>
        </div>

        <!-- <div class="tabs">
            <div class="tab active">Beginner</div>
            <div class="tab">Intermediate</div>
            <div class="tab">Master</div>
        </div> -->

        <div class="content">
            <div class="board">
                <div class="card grid-2-3 grid-1-1-sm" id="put_things">
                    <div class="card-wrapper" id="put_wiki">
                        <div class="card-title">
                            Wiki Says
                            <a><i class="fa fa-link" aria-hidden="true"></i></a>
                        </div>
                        <div class="card-content" >
                            <p>${wiki}</p>
                        </div>
                    </div>

                    <div class="card-wrapper">
                        <div class="card-title" id="trend-title">
                            Trends
                            <a><i class="fa fa-link" aria-hidden="true"></i></a>
                        </div>
                        <div class="card-content">
                            <div class="fake-chart" style="height: 220px;" id="language-trends">
                            </div>
                        </div>
                    </div>

                    <div class="card-wrapper">
                        <div class="card-title" id="relation-title">
                            Relation
                            <a><i class="fa fa-link" aria-hidden="true"></i></a>
                        </div>
                        <div class="card-content">
                            <%--<div class="fake-chart" style="height: 220px;" id="language-relations">--%>
                                <div class="fake-chart" style="height: 440px;" id="language-relations">
                            </div>
                        </div>
                    </div>

                    <div class="card-wrapper" id="put_related_repos">
                        <%--<div class="card-title">--%>
                            <%--Featured Recommendation--%>
                            <%--<a><i class="fa fa-link" aria-hidden="true"></i></a>--%>
                        <%--</div>--%>
                        <%--<div class="card-content" id="put_related_repos">--%>
                            <%--<c:forEach items="${listRepo}" var="item">--%>
                                <%--<a class="item" href="#">--%>
                                    <%--<div class="item-title">${item.fullName}</div>--%>
                                    <%--<div class="item-line item-number">--%>
                                        <%--<span><i class="fa fa-star" aria-hidden="true"></i>${item.stars}</span>--%>
                                        <%--<span><i class="fa fa-code-fork" aria-hidden="true"></i>${item.forks}</span>--%>
                                    <%--</div>--%>
                                    <%--<div class="item-line line-2">--%>
                                        <%--${item.description}--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</c:forEach>--%>
                        <%--</div>--%>
                    </div>
                </div>

                <div class="card grid-1-3 grid-1-1-sm" id="put_helloWorld_quote">
                    <c:if test="${!empty helloWorld}">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Hello World!
                            <a><i class="fa fa-play" aria-hidden="true"></i></a>
                        </div>
                        <div class="card-content">
                            <div class="card-terminal">
                            </div>
                        </div>
                    </div>
                    </c:if>

                    <c:if test="${!empty quoteContent}">
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
                    </c:if>

                    <c:if test="${!empty websites}">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Helpful Websites
                        </div>
                        <div class="card-content">
                            <c:forEach items="${websites}" var="web">
                            <a class="card-web web-${web.img}" href="${web.link}">
                                <div class="web-img">
                                    <img src="assets/images/web-${web.img}.png" srcset="assets/images/web-${web.img}@2x.png 2x">
                                </div>
                            </a>
                            </c:forEach>
                        </div>
                    </div>
                    </c:if>

                    <c:if test="${!empty courseName}">
                    <div class="card-wrapper">
                        <div class="card-title">
                            Brilliant MOOC
                            <a href="${courseUrl}"><i class="fa fa-link" aria-hidden="true"></i></a>
                        </div>
                        <div class="card-content">
                            <a href="${courseUrl}" class="card-course">
                                <div class="course-header" style="background-image: url('assets/images/cover-course-${courseCover}.png')">
                                    <h4>${courseName}</h4>
                                </div>
                                <div class="course-footer">
                                    <div class="course-web"><img src="assets/images/web-course-coursera.png" srcset="assets/images/web-course-coursera@2x.png 2x"></div>
                                    <div class="course-uni"><img src="assets/images/uni-${courseUni}.png"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    </c:if>

                    <div class="card-wrapper">
                        <div class="card-title" id="put_top_title">
                            Top ${language} Developers
                            <a><i class="fa fa-link" aria-hidden="true"></i></a>
                        </div>
                        <div class="card-list" id="put_related_users">
                            <%--<c:forEach items="${listTopDev}" var="item">--%>
                                <%--<a class="ranking-item" href="#">--%>
                                    <%--<span class="avatar"><img src="/assets/images/avatar-32.png"></span>--%>
                                    <%--<span>${item.rank}. ${item.name}</span>--%>
                                    <%--<span class="pull-right"><i class="fa fa-star fa-fw" aria-hidden="true"></i>${item.stars}</span>--%>
                                <%--</a>--%>
                            <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Lang End -->
    </div>
</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}
<script src="assets/js/lang.js"></script>

<%--<script>--%>
    <%--var hw = '${helloWorld}'.replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/\n/g, "<br/>").replace(/\s/g, '&nbsp;');--%>
    <%--$('.card-terminal').html(hw);--%>
<%--</script>--%>

</body>
</html>