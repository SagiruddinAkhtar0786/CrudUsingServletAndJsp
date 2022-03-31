<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User list page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand">User
					Management App</a>
			</div>

			<ul class="navbar-nav">
				<li><a href=" <%=request.getContextPath()%>/list "
					class="nav-link">User</a></li>

			</ul>
		</nav>
	</header>
	
	<div class="row">
		<div class="container">
			<h3 class="text-center">List Of users</h3>
			<hr>
			
			<div class="container text-left">
				<a href=" <%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
			</div>
			
			<br>
			
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>NAME</th>
						<th>EMAIL</th>
						<th>COUNTRY</th>
						<th>ACTIONS</th>
						
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="user" items="${listUser}">
					
					<tr>
						<td> <c:out value="${user.id }"/> </td>
						<td> <c:out value="${user.name }"></c:out> </td>
						<td> <c:out value="${user.email }"></c:out> </td>
						<td> <c:out value="${user.country }"></c:out> </td>
						<td> <a href="edit?id=<c:out value='${user.id }'/> ">Edit</a>
						&nbsp; &nbsp; &nbsp; &nbsp;
							<a href="delete?id=<c:out value='${user.id }'/> ">Delete</a>
						 </td>
					</tr>
					
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>