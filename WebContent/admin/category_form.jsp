<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category Form</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
	<c:if test="${category != null}">
		<h2>Edit category</h2>
	</c:if>
	<c:if test="${category == null}">
		<h2>Create New Category</h2>
	</c:if>
		<c:if test="${message != null}">
			<h4 align="center">${message} </h4>
		</c:if>
			<c:if test="${category == null}">
				<form action="create_category" method="post">
			</c:if>
			<c:if test="${category != null}">
				<form action="edit_category" method="post">
				<input type="hidden" name="categoryId" value="${category.categoryId}" />
			</c:if>
			
				Name: <input type="text" name="categoryName" size="20" value="${category.name}" />
				
				<c:if test="${category != null}">
					<input type="submit" value="Update">
				</c:if>
				<c:if test="${category == null}">
					<input type="submit" value="Create">
				</c:if>
				
				 <input type="button" value="Cancel" onClick="javasctipt:history.go(-1);"> 
			</form>
	</div>	
	<br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>