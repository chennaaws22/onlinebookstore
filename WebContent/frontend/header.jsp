<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<div class="container-fluid">
    		<a class="navbar-brand" href="#">Book Store</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/BooKStoreWebsite/">Home</a>
        </li>
   
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Categories
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <c:forEach var="category" items="${categories}" varStatus="status">
	            <li><a class="dropdown-item" href="view_category?categoryId=${category.categoryId}">${category.name}</a></li>
				
				</c:forEach>
          </ul>
        </li>
        <c:if test="${sessionScope.customerLoggedIn == null }" >
	        <li class="nav-item">
	          <a class="nav-link" href="login">Sign In</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="register_customer">Register</a>
	        </li>
        </c:if>
        
        <c:if test="${sessionScope.customerLoggedIn != null }">
	        <li class="nav-item">
	          <a class="nav-link" href="cart">Cart</a>
	        </li>
	         <li class="nav-item">
	          <a class="nav-link" href="orders">MyOrders</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="logout">logout</a>
	        </li>
        </c:if>
      </ul>
      <form class="d-flex" action="search" method="get">
        <input class="form-control me-2" name="keyword" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
