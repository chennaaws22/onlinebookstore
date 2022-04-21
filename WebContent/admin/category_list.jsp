<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage category - Admin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
	
	
	</div>
	<br>
	<c:if test="${message != null}">
		<h4 align="center">${message} </h4>
	</c:if>
	<div class="container-fluid">
		<div class="row">
			<div class="col text-center mb-1">
				<h2 class="mb-2">Category Management</h2>
		<a class="btn btn-success" href="category_form.jsp">create new category</a>
	<div align="center">
		<table class="table table-borderd">
			<tr>
				<th scope="col">Index</th>
				<th scope="col">Id</th>
				<th scope="col">Name</th>
				<th scope="col">Actions</th>
				
			</tr>
			<c:forEach var="category" items="${categories}" varStatus="status">
				<tr>
					<th scope="row">${status.index}</td>
					<td>${category.categoryId}</td>
					<td>${category.name}</td>
					<td><a class="btn btn-sm btn-primary mb-1" href="edit_category?categoryId=${category.categoryId}">Edit</a> 
					  <br> <a class="btn btn-sm btn-danger" href="delete_category?categoryId=${category.categoryId}" onClick="confirmDelete(${category.categoryId})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	
	</div>
</div>
</div>
</div>
	<jsp:directive.include file="footer.jsp"/>
	
	<script>
		function confirmDelete(categoryId){
			if(confirm(`Sure to delete category with id ` + categoryId)){
				window.location = "delete_category?userId=" + categoryId;
			}
		}
	</script>
	
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
</body>
</html>