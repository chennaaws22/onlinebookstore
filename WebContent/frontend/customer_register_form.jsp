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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>Register Customer</h2>
		<c:if test="${message != null}">
			<h4 align="center">${message} </h4>
		</c:if>
				<form action="register_customer" method="post">
				Full Name: <input type="text" name="fullName" size="20"/>
				<br>
				Email: <input type="text" name="email" size="20" />
				<br>
				password: <input type="text" name="password" size="20" />
				<br>
				Phone: <input type="text" name="phone" size="20"/>
				<br>
				City: <input type="text" name="city" size="20" >
				<br>
				Country: <input type="text" name="country" size="20"/>
				<br>
				Address: <input type="text" name="address" size="100" />	
							<br>
				ZIP Code: <input type="text" name="zipcode" size="5" />
							<br>
		
					<input type="submit" value="Register">
				<input type="button" value="Cancel" onClick="javasctipt:history.go(-1);"> 
			</form>
	</div>	
	<br><br>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
					<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
</body>	

</html>