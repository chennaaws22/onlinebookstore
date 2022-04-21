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
	<div align="center">
	
		<h2>Customers Management</h2>
		<a href="create_customer">create new Customer</a>
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
				<th>Fullname</th>
				<th>Email</th>
				<th>City</th>
				<th>Country</th>
				<th>Register Date</th>
				<th>ZIP</th>
				<th>Actions</th>	
			</tr>
			<c:forEach var="customer" items="${customers}" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${customer.customerId}</td>
					<td>${customer.fullname}</td>
					<td>${customer.email}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td>${customer.registerDate}</td>
					<td>${customer.zipcode}</td>
					<td><a href="edit_customer?customerId=${customer.customerId}">Edit</a> 
					  | <a href="delete_customer?customerId=${customer.customerId}" onClick="confirmDelete(${customer.customerId})">Delete</a></td>
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