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
        <c:param name="activeItem" value="Lang-Relation"/>
    </c:import>
    ${sidebar}

    <div class="main">
        <!-- Relation -->
        <div class="header">
            <h1>Relation</h1>
            <div class="filters">
                <c:forEach items="${categories}" var="category">
                    <a class="filter${category.equals(currentCategory) ? ' active' : ''}">${category}</a>
                </c:forEach>
            </div>
        </div>
        <div class="content">
            <div class="board">
                <div class="card grid-1-1">
                    <div class="card-wrapper">
                        <div id="relation-chart" style="height: 820px;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Relation End -->
    </div>
</div>

<c:import url="./_script.jsp" var="script"></c:import>
${script}
<%--<script type="text/javascript">--%>
    <%--var categories = ${data.categories};--%>
    <%--var nodes = ${data.relationNodes};--%>
    <%--var links = ${data.relationLinks};--%>
<%--</script>--%>
<script src="assets/js/lang-relation.js"></script>



</body>
</html>