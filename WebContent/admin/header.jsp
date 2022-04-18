<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<div align="center">
	<div>
		<c:if test="${sessionScope.userLogedIn != null}">
			Welcome 
			 <c:out value="${sessionScope.userLogedIn}" />, 
			 <a href="logout">Logout</a><br><br>
			 |
		 </c:if>
		 <c:if test="${sessionScope.userLogedIn == null}">
			 <a href="login_form.jsp">Login</a><br><br>
		 </c:if>
	 </div>
	<div>
		<b>
			<a href="list_users">Users</a> |
			<a href="list_categories">Categories</a> |
			<a href="list_book">Books</a> |
			<a href="list_customer">Customers</a> |
			<a href="reviews">reviews</a> |
			<a href="orders">Orders</a> 
		</b>
	</div>
	<div>
		<c:forEach var="category" items="${categories}" varStatus="status">
				<c:out value="${category.name}" />
		</c:forEach>
	</div>
</div>