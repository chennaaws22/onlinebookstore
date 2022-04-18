<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Customers - Admin</title>
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
</body>
</html>