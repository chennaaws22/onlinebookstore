<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body>	
	<jsp:directive.include file="header.jsp"/>
		<c:set var="cart" value="${sessionScope['shoppingCart']}"/>
	
		<c:if test="${message != null}">
				<h4 style="color:red;" align="center">${message}</h4>
		</c:if>
		<c:if test="${cart.totalItems == 0}">
				<h4 style="color:red;" align="center">There is no any item in the cart</h4>
		</c:if>
		
	<c:if test="${cart.totalItems > 0}">
		<div class="container-fluid">
			<div class="row">
				<div class="col text-center mb-2">
					<h3 class="text-center">Shopping Cart</h3>
				</div>
			</div>
			<div class="row">
			<div class="col ">
			<form action="update_cart" method="post"> 
				<table  class="table table-bordered ">
					<tr>
						<th scope="col">No</th>
						<th scope="col">Book</th>
						<th scope="col">Price</th>
						<th scope="col">Quantity</th>
						<th scope="col">Subtotal</th>
						<th scope="col">Clear cart</th>
					</tr>
					<c:forEach var="item" items="${cart.cartItems}" varStatus="status">
					
						<tr>
							<input type="hidden" name="bookId" value="${item.key.bookId}"/>
							<th scope="row">${status.index}</th>
							<td><img src="data:image/jpg;base64,${item.key.base64Image}" width="80" height="100"/>${book.title}</td>
							
							<td>
								<fmt:setLocale value = "en_us"/>
								<fmt:formatNumber value = "${item.key.price}" type = "currency"/>
							</td>
							<td><input type="text" name="quantity${item.key.bookId}" value="${item.value}" /></td>
							<td><fmt:setLocale value = "en_us"/>
								<fmt:formatNumber value = "${item.value * item.key.price}" type = "currency"/></td>
							<td><a class="btn btn-sm btn-primary mb-1" href="remove_from_cart?bookId=${item.key.bookId}">Remove</a> </td>
						</tr>
					</c:forEach>
				</table>
				
				<input class="btn btn-primary" type="submit" value="Update" />
			</form>
			<a href="clear_cart" class="btn btn-danger">Clear Cart</a>
			</div>
			</div>
		</div>
	</c:if>
			
			
			
		
		
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
		
</body>
</html>