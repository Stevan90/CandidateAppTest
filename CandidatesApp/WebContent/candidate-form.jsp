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
					<c:if test="${candidate != null}">
						<form action="update" method="post">
					</c:if>
					<c:if test="${candidate == null}">
						<form action="insert" method="post">
					</c:if>

					<caption>
						<h2>
							<c:if test="${candidate != null}">
            					Edit Candidate
            				</c:if>
							<c:if test="${candidate == null}">
            					Add New Candidate
            				</c:if>
						</h2>
					</caption>

					<c:if test="${candidate != null}">
						<input type="hidden" name="id" value="<c:out value='${candidate.id}' />" />
					</c:if>

					<fieldset class="form-group">
						<label>First Name</label> <input type="text" value="<c:out value='${candidate.firstName}' />" class="form-control" name="firstName" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Last Name</label> <input type="text" value="<c:out value='${candidate.lastName}' />" class="form-control" name="lastName">
					</fieldset>

					<fieldset class="form-group">
						<label>JMBG</label> <input type="text" value="<c:out value='${candidate.jmbg}' />" class="form-control" name="jmbg">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Birth Year</label> <input type="text" value="<c:out value='${candidate.birthYear}' />" class="form-control" name="birthYear">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Email</label> <input type="text" value="<c:out value='${candidate.email}' />" class="form-control" name="email">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Phone</label> <input type="text" value="<c:out value='${candidate.phone}' />" class="form-control" name="phone">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Note</label> <input type="text" value="<c:out value='${candidate.note}' />" class="form-control" name="note">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Employed</label> <input type="text" value="<c:out value='${candidate.employed}' />" class="form-control" name="employed">
					</fieldset>

					<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
	</body>
</html>