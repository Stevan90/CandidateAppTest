<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Candidate Management Application</title>
		<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		crossorigin="anonymous">
	</head>

	<body>
	
		<header>
			<nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
			
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Candidate Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Candidates</a></li>
			</ul>
			</nav>
		</header>
		
		<br>
		
		<div class="container col-md-5">
			<div class="card">
				<div class="card-body">
					${errorMessage}
			</div>
		</div>
	</div>
	</body>
</html>