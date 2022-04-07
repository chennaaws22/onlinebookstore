<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>	
	<h1 align="center">Welcome to Book Store Home Page!</h1>
	<jsp:directive.include file="header.jsp"/><br>
	<c:if test="${message != null}">
		<h4 align="center">${message}</h4>
	</c:if>
	<br><br>
	<h3 align="center">New Books</h3>
	<div align="center">
			<c:forEach var="book" items="${books}" varStatus="status">
				<div style="float:left;display:inline-block;margin:20px;">
					<img src="data:image/jpg;base64,${book.base64Image}" width="80" height="120"/>
					<br><br>
					${book.title}
					<br>
					<fmt:setLocale value = "en_us"/>
					<b><fmt:formatNumber value = "${book.price}" type = "currency"/></b>
				</div>
			</c:forEach>	
		</div>
	
</body>
</html>