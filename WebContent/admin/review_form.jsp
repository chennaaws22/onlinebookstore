<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Review</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>Edit Review</h2>
			<form action="edit_review" method="post">
				<input type="hidden" name="reviewId" value="${review.reviewId}" />
				Book: <h4>${review.book.title}</h4>
				<br>
				Rating: <h4>${review.rating}</h4>
				<br>
				Customer: <h4>${review.customer.fullname}</h4>
				<br>
				Headline: <input type="text" name="headline" size="20" value="${review.headline}" />
				<br>
				comment: <textarea class="content" rows="5" cols="50" name="comment" id="comment">${review.comment}</textarea>
				<br>
				<input type="submit" value="Update"> <input type="button" value="Cancel" onClick="javasctipt:history.go(-1);"> 
	
			</form>
	</div>	
	<br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>