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
	<jsp:directive.include file="header.jsp"/>
	
		<h1>${category.name} Books</h1>
		<div align="center">
			<c:forEach var="book" items="${books}" varStatus="status">
				<div style="float:left;display:inline-block">
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