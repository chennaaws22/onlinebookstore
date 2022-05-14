<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Customers - Admin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col text-center mb-2">
			<h2>Customers Management</h2>
			<a class="btn btn-success"  href="create_customer">Create new Customer</a>
		</div>
	</div>
	<br>
	<c:if test="${message != null}">
		<h4 class="text-danger text-center">${message}</h4>
	</c:if>
	
	
		<table class="table table-bordered">
			<tr>
				<th scope="col">Index</th>
				<th scope="col">Id</th>
				<th scope="col">Image</th>
				<th scope="col">Full Name</th>
				<th scope="col">Email</th>
				<th scope="col">City</th>
				<th scope="col">Country</th>
				<th scope="col">Register Date</th>
				<th scope="col">ZIP</th>
				<th scope="col">Actions</th>	
			</tr>
			<c:forEach var="customer" items="${customers}" varStatus="status">
				<tr>
					<th scope="row">${status.index}</th>
					<td>${customer.customerId}</td>
					<td><img src="data:image/png;base64, ${customer.base64Image}" width="80" height="80" /></td>
					
					<td>${customer.fullname}</td>
					<td>${customer.email}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td>${customer.registerDate}</td>
	                <td>${customer.zipcode}</td>
					<td><a class="btn btn-sm btn-primary" href="edit_customer?customerId=${customer.customerId}">Edit</a> 
					  <a class="btn btn-sm btn-danger"  href="delete_customer?customerId=${customer.customerId}" onClick="confirmDelete(${customer.customerId})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	
	</div>
	<br><br>
	<jsp:directive.include file="footer.jsp"/>
	
	<script>
		function confirmDelete(customerId){
			if(confirm(`Sure to delete customer with id ` + customerId)){
				window.location = "delete_customer?customerId=" + customerId;
			}
		}
	</script>
	
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
</body>
</html>