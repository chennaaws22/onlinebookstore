<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
     
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order details</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
		<jsp:directive.include file="header.jsp"/>
		<div class="container">
			<h5 class="my-2  text-center">Details of order Id: ${bookOrder.orderId}</h5>
			<div class="row">
				<div class="col-3">
				</div>
				<div class="col-6">
				
				<table class="table table-sm table-bordered">
		
			    <tr>
			      <th >Ordered By</th>
			       <td >${bookOrder.customer.fullname}</td>
			    </tr>
			    <tr>
			      <th >Book Copies</th>
			      <td >${bookOrder.numBookCopies}</td>
			    </tr>
			    <tr>
			      <th >Total Amount</th>
			      <td ><fmt:formatNumber value = "${bookOrder.total}" type = "currency"/></td>
			    </tr>
			    <tr>
			      <th >Recepient Name</th>
			      <td >${bookOrder.recipientName}</td>
			    </tr>
			    <tr>
			      <th >Recepient Phone</th>
				<td >${bookOrder.recipientPhone}</td>
			    </tr>
			    <tr>
			      <th >Ship To</th>
			      <td >${bookOrder.shippingAddress}</td>
			    </tr>
			    <tr>
			      <th>Payment Method</th>
			      <td >${bookOrder.paymentMethod}</td>
			    </tr>
			     <tr>
			      <th >Order Status</th>
			      <td >${bookOrder.status}</td>
			    </tr>
			     <tr>
			      <th >Order Date</th>
			      <td >${bookOrder.orderDate}</td>
			    </tr>
			</table>
			
				</div>
				<div class="col-3">
				</div>
			</div>
			<div class="row">
			<div class="col-2">
			</div>
			<div class="col-8">
			<h5 class="text-center">Books in Order</h5>
				<table class="table table-sm table-bordered">
					  <caption>
					  	<a class="btn btn-sm btn-danger" href="delete_order?orderId=${bookOrder.orderId}">Delete Order</a> 
					  	<a class="btn btn-sm btn-primary" href="edit_order?orderId=${bookOrder.orderId}">Edit Order</a> 
					  </caption>
					  <thead>
					    <tr>
					      <th scope="col">#</th>
					      <th scope="col">Book Title</th>
					      <th scope="col">Author</th>
					      <th scope="col">Price</th>
					      <th scope="col">Qunantity</th>
					      <th scope="col">Sub Total</th>
					    </tr>
					  </thead>
					  <tbody>
					  <c:forEach var="orderDetail" items="${bookOrder.orderDetails}" varStatus="status">
						    <tr>
						      <th scope="row">${status.index}</th>
						      <td>${orderDetail.book.title}</td>
						      <td>${orderDetail.book.author}</td>
						      <td><fmt:formatNumber value = "${orderDetail.book.price}" type = "currency"/></td>
						      <td>${orderDetail.quantity}</td>
						      <td><fmt:formatNumber value = "${orderDetail.subtotal}" type = "currency"/></td>
						    </tr>
					   </c:forEach>
					   		<tr>
					   			<th scope="row" class="text-right" colspan="4"> Total</th>
					   			<td>${bookOrder.numBookCopies}</td>
					   			<td><fmt:formatNumber value = "${bookOrder.total}" type = "currency"/></td>
					   		</tr>
					  </tbody>
					</table>
					</div>
				<div class="col-2">
				</div>
			</div>
		</div>
		
		<jsp:directive.include file="footer.jsp"/>
		
</body>

</html>