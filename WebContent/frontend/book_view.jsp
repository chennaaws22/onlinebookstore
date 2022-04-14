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
		
		<div align="center">
			<c:if test="${message != null}">
				<h4 style="color:red;" align="center">${message}</h4>
			</c:if>
				<div style="float:left;display:inline-block">
				
					<img src="data:image/jpg;base64,${book.base64Image}" width="20%" height="30%"/>
					<br><br>
			
					<b>${book.title}</b>
			
					<br>
					<p>${book.description}</p>
					<br>
				
					<fmt:setLocale value = "en_us"/>
					<b><fmt:formatNumber value = "${book.price}" type = "currency"/></b>
				</div>
				
		</div>
</body>
</html>