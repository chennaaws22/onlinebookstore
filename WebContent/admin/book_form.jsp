<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Form</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
	<c:if test="${book != null}">
		<h2>Edit book</h2>
	</c:if>
	<c:if test="${book == null}">
		<h2>Create New book</h2>
	</c:if>
		<c:if test="${message != null}">
			<h4 align="center">${message} </h4>
		</c:if>
			<c:if test="${book == null}">
				<form action="create_book" method="post" enctype="multipart/form-data">
			</c:if>
			<c:if test="${book != null}">
				<form action="update_book" method="post" enctype="multipart/form-data">
				<input type="hidden" name="bookId" value="${book.bookId}" />
			</c:if>
			
				Title: <input type="text" name="bookTitle" size="20" value="${book.title}" />
				<br>
				Category:
				<select name="category">
					<c:forEach var="category" items="${categories}">
						<c:if test="${category.categoryId eq book.category.categoryId}">
							<option value="${category.categoryId}" selected>
						</c:if>
						
						<c:if test="${category.categoryId ne book.category.categoryId}">
							<option value="${category.categoryId}">
						</c:if>
							${category.name}
						</option>
					</c:forEach>
				</select>
				<br>
				Author: <input type="text" name="bookAuthor" size="20" value="${book.author}" />
				<br>
				ISBN: <input type="text" name="bookIsbn" size="20" value="${book.isbn}" />
				<br>
				publish Date: <input type="date" name="bookDate" 
				value="<fmt:formatDate pattern='yyyy-MM-dd' value='${book.publishDate}' />" />
				<br>
				Book Image: <input type="file" name="bookImage" id="bookImage" size="20" /><br>
				<img width="80px" height="120pxs" id="thumbnail"  alt="thumbnail"
						src="data:image/jpg;base64,${book.base64Image}"/>
				<br>
				Price: <input type="text" name="bookPrice" size="20" value="${book.price}" />
				<br>
				Description: 
				<textarea class="content" rows="5" cols="50" name="description" id="description">${book.description}</textarea>
				<br><br>
				<c:if test="${book != null}">
					<input type="submit" value="Update">
				</c:if>
				<c:if test="${book == null}">
					<input type="submit" value="Create">
				</c:if>
				
				<input type="button" value="Cancel" onClick="javasctipt:history.go(-1);"> 
			</form>
	</div>	
	<br><br>
	
	<jsp:directive.include file="footer.jsp"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../js/jquery.richtext.min.js"></script>
	
</body>	
<script type="text/javascript">
		$(document).ready(function(){
			$('#bookImage').change(function(){
				showImageThumbnail(this);
			});
			
			$('#description').richText();
		});
		
		function showImageThumbnail(fileInput){
			var file = fileInput.files[0];
			
			var reader = new FileReader();
			
			reader.onload = function(e){
				$('#thumbnail').attr('src', e.target.result)
			};
			
			reader.readAsDataURL(file);
		}
	</script>
</html>