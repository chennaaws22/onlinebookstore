<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Store Home</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>	
	<jsp:directive.include file="header.jsp"/><br>
	<c:if test="${message != null}">
		<h4 align="center">${message}</h4>
	</c:if>
	
	<div class="container">
	<h1 align="center" class="mb-4">Welcome to Book Store Home Page!</h1>
		<h3 class="mb-4"><b>New Books</b></h3>
		<c:forEach var="book" items="${books}" varStatus="status">
		   <c:if test="${status.index % 6 == 0}">
				<div class="row">
			</c:if>
			<div class="col-2">
				<div class="card" style="width: 10rem;">
			  		<img src="data:image/jpg;base64,${book.base64Image}"  class="card-img-top">
			  		<div class="card-body">
			  		${book.title}
			   		 <b class="card-text">
			   		 	<fmt:setLocale value = "en_us"/>
						<b><fmt:formatNumber value = "${book.price}" type = "currency"/></b></b>
			  		</div>
				</div>
			</div>
				<c:if test="${(status.index + 1) % 6 == 0}">
					</div>
				</c:if>
			</c:forEach>	
		
		</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>