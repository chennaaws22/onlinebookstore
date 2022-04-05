<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Books - Admin</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
	
		<h2>Books Management</h2>
		<a href="create_book">create new Book</a>
	</div>
	<br>
	<c:if test="${message != null}">
		<h4 align="center">${message}</h4>
	</c:if>
	
	<div align="center">
		<table border="1" cellPadding="5">
			<tr>
				<th>Index</th>
				<th>Id</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>PublishDate</th>
				<th>Actions</th>	
			</tr>
			<c:forEach var="book" items="${books}" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${book.bookId}</td>
					<td><img src="data:image/jpg;base64,${book.base64Image}" width="80" height="100"/></td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.category.name}</td>
					<td>${book.price}</td>
					<td>${book.publishDate}</td>
					<td><a href="edit_book?bookId=${book.bookId}">Edit</a> 
					  | <a href="delete_book?bookId=${book.bookId}" onClick="confirmDelete(${book.bookId})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
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