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

		<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

			<div class="container">
				<h3 class="text-center">List of Candidates</h3>
				<hr>
				
				<div class="container text-left">

					<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Candidate</a>
					<a href="<%=request.getContextPath()%>/download" class="btn btn-success">Download CSV</a>
				</div>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>JMBG</th>
							<th>Birth Year</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Note</th>
							<th>Employed</th>
							<th>Modification Date</th>
							<th>Actions</th>
						</tr>
					</thead>
					
					<tbody>
					<!--   for (Todo todo: todos) {  -->
						<c:forEach var="candidat" items="${listCandidates}">

							<tr>
								<td><c:out value="${candidat.id}" /></td>
								<td><c:out value="${candidat.firstName}" /></td>
								<td><c:out value="${candidat.lastName}" /></td>
								<td><c:out value="${candidat.jmbg}" /></td>
								<td><c:out value="${candidat.birthYear}" /></td>
								<td><c:out value="${candidat.email}" /></td>
								<td><c:out value="${candidat.phone}" /></td>
								<td><c:out value="${candidat.note}" /></td>
								
								<c:if test="${candidat.employed}">
									<td><c:out value="Employed" /></td>
								</c:if>
								<c:if test="${!candidat.employed}">
									<td><c:out value="Not Employed" /></td>
								</c:if>
								
								<td><c:out value="${candidat.modificationDate}" /></td>
								<td><a href="edit?id=<c:out value='${candidat.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${candidat.id}' />">Delete</a></td>
							</tr>
						</c:forEach>
					<!-- } -->
					</tbody>

				</table>
			</div>
		</div>
	</body>
</html>