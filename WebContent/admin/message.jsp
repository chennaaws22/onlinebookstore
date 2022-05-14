<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br>
	<div align="center">
	
		<h3 class="text-danger">${message}</h3>
		
		<a href="user_form.jsp">create new user</a>
	</div>
	
	<br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>