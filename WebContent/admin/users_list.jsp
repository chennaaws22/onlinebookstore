	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage users - Admin</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
	
		<h2>Users Management</h2>
		<a href="user_form.jsp">create new user</a>
	</div>
	<br>
	<c:if test="${message != null}">
		<h4 align="center">${message} </h4>
	</c:if>
	<div align="center">
	
		<table border="1" cellPadding="5">
			<tr>
				<th>Index</th>
				<th>Id</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
				
			</tr>
			<c:forEach var="user" items="${users}" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td><a href="edit_user?userId=${user.userId}">Edit</a> | <a href="delete_user?userId=${user.userId}" onClick="confirmDelete(${user.userId})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	
	</div>
	<br><br>
	<jsp:directive.include file="footer.jsp"/>
	
	<script>
		function confirmDelete(userId){
			if(confirm(`Sure to delete user with id ` + userId)){
				window.location = "delete_user?userId=" + usserId;
			}
		}
	</script>
</body>
</html>