<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>

<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="favicon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<!-- Material Design fonts -->
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700">
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/icon?family=Material+Icons">

<!-- Bootstrap Material Design -->
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-material-design.min.css">
<link rel="stylesheet" type="text/css" href="css/ripples.min.css">

<!-- Material Design for Bootstrap -->
<script src="js/material.min.js"></script>
<script src="js/ripples.min.js"></script>

<!-- CookieBar -->
<link rel="stylesheet" href="css/jquery.cookiebar.css" type="text/css" />
<script type="text/javascript" src="js/jquery.cookiebar.js"></script>

<script>
	$(document).ready(function() {
		$.cookieBar({
			message : '<spring:message code="master.page.cookie" />',
			acceptText : '<spring:message code="master.page.acceptcookie" />'
		});
	});
</script>

<!-- Retoques -->
<link rel="stylesheet" type="text/css" href="css/style.css" />

<script>
	$(function() {
		$.material.init();
	});
</script>

<script type="text/javascript">
	function relativeRedir(loc) {
		var b = document.getElementsByTagName('base');
		if (b && b[0] && b[0].href) {
			if (b[0].href.substr(b[0].href.length - 1) == '/'
					&& loc.charAt(0) == '/')
				loc = loc.substr(1);
			loc = b[0].href + loc;
		}
		window.location.replace(loc);
	}

	function hiddenOrShow() {
		var elemento = document.getElementById("alerta");
		if (elemento.style.display == "none") {
			elemento.style.display = "block";
		} else {
			elemento.style.display = "none";
		}
	}
</script>

<title><tiles:insertAttribute name="title" ignore="true" /></title>

</head>
<body>
	<div class="container">

		<div class="page-header" id="banner">
			<div class="row" style="display: flex; align-items: center;">
				<div class="col-sm-3">
					<a href="#"><img width="100%" src="images/logo.png"
						alt="Acme - Six Pack" /></a>
				</div>
				<div class="col-sm-9 text-right">
					<h1>
						<a style="text-decoration: none;" href="#">Pintacme</a>
					</h1>
					<h2>
						<tiles:insertAttribute name="title" />
					</h2>
				</div>
			</div>
		</div>

		<tiles:insertAttribute name="header" />

		<div class="bs-docs-section clearfix">
			<div class="row row-centered">
				<tiles:insertAttribute name="body" />
				<jstl:if test="${message != null}">
					<div class="alert alert-dismissible alert-danger">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<spring:message code='${message}' />
					</div>
				</jstl:if>
			</div>
		</div>
	</div>

	<footer>
		<div class="container">
			<div id="footer-content" class="text-center">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</footer>

</body>
</html>