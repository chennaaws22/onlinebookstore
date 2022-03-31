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
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
	<c:if test="${user != null}">
		<h2>Edit User</h2>
	</c:if>
	<c:if test="${user == null}">
		<h2>Create New User</h2>
	</c:if>
		
			<c:if test="${user == null}">
				<form action="create_user" method="post">
			</c:if>
			<c:if test="${user != null}">
				<form action="edit_user" method="post">
				<input type="hidden" name="userId" value="${user.userId}" />
			</c:if>
			
				Email: &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;   <input type="text" name="email" size="20" value="${user.email}" />
				<br>
				Full Name: <input type="text" name="fullName" size="20" value="${user.fullName}" />
				<br>
				Password:&nbsp;  <input type="password" name="password" size="20"  value="${user.password}" />
				<br><br>
				<input type="submit" value="Create"> <input type="button" value="Cancel" onClick="javasctipt:history.go(-1);"> 
			</form>
	</div>	
	<br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>