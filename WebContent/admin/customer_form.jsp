<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Form</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
	<c:if test="${customer != null}">
		<h2>Edit Customer</h2>
	</c:if>
	<c:if test="${customer == null}">
		<h2>Create New Customer</h2>
	</c:if>
		<c:if test="${message != null}">
			<h4 align="center">${message} </h4>
		</c:if>
			<c:if test="${customer == null}">
				<form action="create_customer" method="post" enctype="multipart/form-data">
			</c:if>
			<c:if test="${customer != null}">
				<form action="edit_customer" method="post" enctype="multipart/form-data">
				<input type="hidden" name="customerId" value="${customer.customerId}" />
			</c:if>
			
				Full Name: <input type="text" name="fullName" size="20" value="${customer.fullname}" />
				<br>
				Email: <input type="text" name="email" size="20" value="${customer.email}" />
				<br>
				password: <input type="text" name="password" size="20" value="${customer.password}" />
				<br>
				<br>
				Customer Image: <input type="file" name="customerImage" id="customerImage" size="20" /><br>
				<img width="80px" height="120pxs" id="thumbnail"  alt="thumbnail"
						src="data:image/jpg;base64,${customer.base64Image}"/>
						<br>
				Phone: <input type="text" name="phone" size="20" value="${customer.phone}" />
				<br>
				City: <input type="text" name="city" size="20" value="${customer.city}">
				<br>
				Country: <input type="text" name="country" size="20" value="${customer.country}" />
				<br>
				Address: <input type="text" name="address" size="100" value="${customer.address}" />	
							<br>
				ZIP Code: <input type="text" name="zipcode" size="5" value="${customer.zipcode}" />
							<br>
				<c:if test="${customer != null}">
					<input type="submit" value="Update">
				</c:if>
				<c:if test="${customer == null}">
					<input type="submit" value="Create">
				</c:if>
				
				<input type="button" value="Cancel" onClick="javasctipt:history.go(-1);"> 
			</form>
	</div>	
	<br><br>
	
	<jsp:directive.include file="footer.jsp"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
</body>	
<script type="text/javascript">
		$(document).ready(function(){
			$('#customerImage').change(function(){
				showImageThumbnail(this);
			});
			
		});
		
		function showImageThumbnail(fileInput){
			var file = fileInput.files[0];
			
			var reader = new FileReader();
			
			reader.onload = function(e){
				$('#thumbnail').attr('src', e.target.result)
			};
			
			reader.readAsDataURL(file);
		}
	</script>
</html>