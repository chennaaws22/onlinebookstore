<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage category - Admin</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
	
		<h2>Category Management</h2>
		<a href="category_form.jsp">create new category</a>
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
				<th>Name</th>
				<th>Actions</th>
				
			</tr>
			<c:forEach var="category" items="${categories}" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${category.categoryId}</td>
					<td>${category.name}</td>
					<td><a href="edit_category?categoryId=${category.categoryId}">Edit</a> 
					  | <a href="delete_category?categoryId=${category.categoryId}" onClick="confirmDelete(${category.categoryId})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	
	</div>
	<br><br>
	<jsp:directive.include file="footer.jsp"/>
	
	<script>
		function confirmDelete(categoryId){
			if(confirm(`Sure to delete category with id ` + categoryId)){
				window.location = "delete_category?userId=" + categoryId;
			}
		}
	</script>
</body>
</html>