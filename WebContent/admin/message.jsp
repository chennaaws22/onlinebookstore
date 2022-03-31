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
		
		<a href="user_form.jsp">create new user</a>
	</div>
	
	<br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>