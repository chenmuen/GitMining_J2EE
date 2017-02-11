<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<c:import url="./jsp/_head.jsp" var="head">
    <c:param name="title" value="Languist"/>
</c:import>
${head}

<body>

<div class="index">

<header class="index-header">
	<div class="index-wrapper">	
		<div class="logo">Languist</div>
		<div class="input-search">
			<input type="text" placeholder="Language" id="searchBar">
			<a class="input-addon"><i class="fa fa-search fa-fw" aria-hidden="true" id="normal-search"></i></a>
		</div>
	</div>
</header>

<section class="hi">
	<h1>Hi, Languist</h1>
	<p>Find your path to coding</p>
	<a href="lang-home">Dig it out!</a>
</section>

<section class="features">
	<h2>In Languist, you could get</h2>
	<hr>
	<div class="feature">
		<div class="icon"><i class="fa fa-th fa-fw" aria-hidden="true"></i></div>
		<h3>Language Cards</h3>
		<p>Find what you wanna learn</p>
	</div>
	<div class="feature">
		<div class="icon"><i class="fa fa-line-chart fa-fw" aria-hidden="true"></i></div>
		<h3>Ranking &amp; Trends</h3>
		<p>Know the popular ones</p>
	</div>
	<div class="feature">
		<div class="icon"><i class="fa fa-exchange fa-fw" aria-hidden="true"></i></div>
		<h3>Language Relation</h3>
		<p>See a bigger picture</p>
	</div>
	<div class="feature">
		<div class="icon"><i class="fa fa-pie-chart fa-fw" aria-hidden="true"></i></div>
		<h3>Data Analysis</h3>
		<p>What's behind the data</p>
	</div>
	<div class="feature">
		<div class="icon"><i class="fa fa-github-alt fa-fw" aria-hidden="true"></i></div>
		<h3>Recommendations</h3>
		<p>Learn from awesome projects</p>
	</div>
	<div class="feature">
		<div class="icon"><i class="fa fa-users fa-fw" aria-hidden="true"></i></div>
		<h3>User Presentation</h3>
		<p>Learn from experts</p>
	</div>
</section>

<section class="counts">
	<h2>Data analyzed from</h2>
	<hr>
	<div class="count">
		<div class="count-card">
			<div class="count-wrapper">
				<div class="number">251</div>
				<div class="desc">Languages</div>
			</div>
		</div>
	</div>
	<div class="count">
		<div class="count-card">
			<div class="count-wrapper">
				<div class="number">266K</div>
				<div class="desc">Repositories</div>
			</div>
		</div>
	</div>
	<div class="count">
		<div class="count-card">
			<div class="count-wrapper">
				<div class="number">19.4K</div>
				<div class="desc">Developers</div>
			</div>
		</div>
	</div>
</section>

<section class="quote">
	<div class="quote-wrapper">
		<div class="quote-content">
		Programming is not a zero-sum game. Teaching something to a fellow programmer doesn't take it away from you. I'm happy to share what I can, because I'm in it for the love of programming. 
		</div>
		<div class="quote-from">
			—John Carmack
		</div>
	</div>
</section>

<section class="why">
	<h2>Why we build Languist</h2>
	<hr>
	<p>Languist used to be a project called GitMining, which is our team project for one of our course at university. The data of Languist were collected from GitHub API. By deeply analyzing these data, we came out a lot of results about programming languages' relation and trends, etc. We hope Languist could help those who are standing in front of the gate of coding just like what we used to be or what we are now. Every programming language is a new world. Before entering the mysterious but wonderful world, we hope Languist could give you a hand.</p>
</section>

<section class="come">
	<h1>Glad to meet you, Languist</h1>
	<p>Find your path to coding</p>
	<div class="cta"><a href="lang-home">Dig it out!</a></div>
</section>

<footer class="index-footer">
	<div>A Cross Work, designed &amp; developed with love by Danni, Ray, Moo &amp; Polaris.</div>
	<div>&copy; 2016 Cross Team</div>
	<a href="mailto:hi.pol.chen@gmail.com">Write us a letter:D</a>
</footer>

</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/topbar.js"></script>
<script type="text/javascript">
	var type = 'Language';
</script>
</body>
</html>