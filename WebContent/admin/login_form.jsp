<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br/> <br/> <br/>	

		<div align="center">
				<c:if test="${message != null}">
					<c:out value="${message}" />
				</c:if>
				<h3>Admin Login</h3>

				<form action="admin/login" method="post">
					Email: &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;   <input type="text" name="email" size="20" />
					<br><br>
					Password:&nbsp;  <input type="password" name="password" size="20"  />
					<br><br>
					<input type="submit" value="Login"> 
				</form>
		</div>	
	<br><br>
</body>
</html>