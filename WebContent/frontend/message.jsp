<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br>
	<div align="center">
	
		<h3>${message}</h3>
		
		<a href="login">Login Customer</a>
	</div>
	
	<br><br>
</body>
</html>