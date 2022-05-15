<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Orders - Admin</title>
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
				<h3 class="text-center">Orders Management</h3>
				
			</div>
		</div>
	<div class="row">
		<div class="col ">
		<table  class="table table-bordered ">
			<tr>
				<th scope="col">Index</th>
				<th scope="col">Order Id</th>
				<th scope="col">Ordered By</th>
				<th scope="col">Book Copies</th>
				<th scope="col">Total</th>
				<th scope="col">Payment Method</th>
				<th scope="col">Status</th>
				<th scope="col">Order Date</th>
				<th scope="col">Actions</th>	
			</tr>
			<c:forEach var="bookOrder" items="${bookOrders}" varStatus="status">
				<tr>
					<th scope="row">${status.index}</th>
					<td>${bookOrder.orderId}</td>
					<td>${bookOrder.customer.fullname}</td>
					<td>${bookOrder.numBookCopies}</td>
					<td>${bookOrder.total}</td>
					<td>${bookOrder.paymentMethod}</td>
					<td>${bookOrder.status}</td>
					<td>${bookOrder.orderDate}</td>
					<td><a class="btn btn-sm btn-primary mb-1 mr-1" href="view_order?orderId=${bookOrder.orderId}">Details</a><a class="btn btn-sm btn-primary mb-1 ml-1" href="edit_book?bookId=${book.bookId}">Edit</a> 
					<br/><a class="btn btn-sm btn-danger" href="delete_order?orderId=${bookOrder.orderId}" onClick="confirmDelete(${book.bookId})">Delete</a></td>
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