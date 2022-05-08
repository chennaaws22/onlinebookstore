<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Store Home</title>
<link href="../css/book_view.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
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
			
			<div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                         <a href="view_book?book_id=${book.bookId}">   <img class="card-img-top" src="data:image/jpg;base64,${book.base64Image}" /></a>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${book.title}</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <c:forTokens items="${book.ratingString}" delims="," var="star">
 											<c:if test='${star  ne "empty"}'>
 												<div class="bi-star-${star}"></div>
 										 	</c:if>
 										 	<c:if test='${star eq  "empty" }'>
 												<div class="bi-star"></div>
 											 </c:if>
 										</c:forTokens>
                                    </div>
                                    <!-- Product price-->
                                    <fmt:setLocale value = "en_us"/>
									<fmt:formatNumber value = "${book.price}" type = "currency"/>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center">
                                	<a class="btn btn-outline-dark mt-auto" href="add_to_cart?bookId=${book.bookId}">Add to cart</a>
                                </div>
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