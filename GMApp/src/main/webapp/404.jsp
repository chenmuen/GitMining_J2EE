<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./jsp/_head.jsp" var="head">
    <c:param name="title" value="Languist"/>
</c:import>
${head}

<body>

<div class="index-pnf">

<header class="index-header">
	<div class="index-wrapper">	
		<a class="logo" href="lang-home">Languist</a>
	</div>
</header>

<section class="pnf">
	<div class="index-wrapper">	
	<p>printf("<span>404</span>");</p>
	<p>printf("Page not found");</p>
	<a href="lang-home">Back to Languist</a>
	</div>
</section>

</div>

</body>
</html>