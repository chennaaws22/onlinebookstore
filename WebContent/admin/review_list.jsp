<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Reviews - Admin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<jsp:directive.include file="header.jsp"/>

	<c:if test="${message != null}">
		<h4 class="text-center alert alert-success my-2" >${message}</h4>
	</c:if>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col text-center mb-2">
				<h3 class="text-center my-3">Reviews Management</h3>
			</div>
		</div>
	<div class="row">
		<div class="col ">
		<table  class="table table-bordered ">
			<tr>
				<th scope="col">Index</th>
				<th scope="col">Id</th>
				<th scope="col">Book</th>
				<th scope="col">Rating</th>
				<th scope="col">Headline</th>
				<th scope="col">Customer</th>
				<th scope="col">Review On</th>
				<th scope="col">Actions</th>	
			</tr>
			<c:forEach var="review" items="${reviews}" varStatus="status">
				<tr>
					<th scope="row">${status.index + 1}</th>
					<td>${review.reviewId}</td>
					<td>${review.book.title}</td>
					<td>${review.rating}</td>
					<td>${review.headline}</td>
					<td>${review.customer.fullname}</td>
					<td>${review.reviewTime}</td>
					<td><a class="btn btn-sm btn-primary" href="edit_review?reviewId=${review.reviewId}">Edit</a> 
					<a class="btn btn-sm btn-danger" href="delete_review?reviewId=${review.reviewId}" onClick="confirmDelete(${review.reviewId})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
	</div>
	<br><br>
	<jsp:directive.include file="footer.jsp"/>
	
	<script>
		function confirmDelete(reviewId){
			if(confirm(`Sure to delete review with id ` + reviewId)){
				window.location = "delete_review?reviewId=" + reviewId;
			}else{
				window.location = "list_reviews";

			}
		}
	</script>
	
	
</body>
</html>