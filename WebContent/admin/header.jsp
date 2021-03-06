<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<style>
     <%@ include file="../css/bootstrap.min.css"%>
</style>	

	
	<div class="alert alert-success text-center" role="alert">

		<c:if test="${sessionScope.userLogedIn != null}">
			
			  Welcome
			   <c:out value="${sessionScope.userLogedIn}" />, 
				 <a  class="btn btn-sm btn-outline-danger" href="logout">Logout</a><br><br>
		</c:if>
	
	</div>
			
<ul class="nav justify-content-center ">
  <li class="nav-item">
   <a class="nav-link active" aria-current="page" href="list_users">Users</a>
  </li>
   <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="list_categories">Categories</a>
  </li>
 
   <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="list_book">Books</a>
  </li>
 
   <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="list_customer">Customers</a>
  </li>
 
   <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="list_reviews">reviews</a>
  </li>
 <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="list_order">Orders</a>
  </li>
</ul>
