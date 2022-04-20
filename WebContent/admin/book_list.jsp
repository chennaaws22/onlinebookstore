<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Books - Admin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<jsp:directive.include file="header.jsp"/>

	<c:if test="${message != null}">
		<h4 align="center">${message}</h4>
	</c:if>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col text-center mb-2">
				<h3 class="text-center">Books Management</h3>
				<a class="btn btn-success" href="create_book">Create new Book</a>
			</div>
		</div>
	<div class="row">
	<div class="col ">
		<table  class="table table-bordered ">
			<tr>
				<th scope="col">Index</th>
				<th scope="col">Id</th>
				<th scope="col">Image</th>
				<th scope="col">Title</th>
				<th scope="col">Author</th>
				<th scope="col">Category</th>
				<th scope="col">Price</th>
				<th scope="col">PublishDate</th>
				<th scope="col">Actions</th>	
			</tr>
			<c:forEach var="book" items="${books}" varStatus="status">
				<tr>
					<th scope="row">${status.index}</td>
					<td>${book.bookId}</td>
					<td><img src="data:image/jpg;base64,${book.base64Image}" width="80" height="100"/></td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.category.name}</td>
					<td>${book.price}</td>
					<td>${book.publishDate}</td>
					<td><a class="btn btn-sm btn-primary mb-1" href="edit_book?bookId=${book.bookId}">Edit</a> 
					<br/><a class="btn btn-sm btn-danger" href="delete_book?bookId=${book.bookId}" onClick="confirmDelete(${book.bookId})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
	</div>
	<br><br>
	<jsp:directive.include file="footer.jsp"/>
	
	<script>
		function confirmDelete(bookId){
			if(confirm(`Sure to delete book with id ` + bookId)){
				window.location = "delete_book?bookId=" + bookId;
			}
		}
	</script>
	
	
</body>
</html>