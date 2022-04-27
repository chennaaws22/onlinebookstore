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
		<c:if test="${message != null}">
				<h4 style="color:red;" align="center">${message}</h4>
		</c:if>
			<section class="py-1">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="data:image/jpg;base64,${book.base64Image}" width="10%"/></div>
                    <div class="col-md-6">
                       <!--   <div class="small mb-1">SKU: BST-498</div> -->
                        <h1 class="display-5 fw-bolder">${book.title}</h1>
                        <div class="fs-5 mb-5">
                        	<fmt:setLocale value = "en_us"/>
                         <!--   <span class="text-decoration-line-through">$45.00</span> -->
                            <span><fmt:formatNumber value = "${book.price}" type = "currency"/></span>
                        </div>
                        
                        <p class="lead">${book.description}</p>
                        <div class="d-flex">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
                        </div>
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
                    </div>
                </div>	
            </div>
        </section>
	
<section style="background-color: #f7f6f6;">
  <div class="container my-1 py-5 text-dark">
    <div class="row d-flex justify-content-center">
      <div class="col-md-12 col-lg-10 col-xl-8">
        <div class="d-flex justify-content-between align-items-center mb-4">
          <h4 class="text-dark mb-0">Customer Reviews</h4>
        </div>
        
	<c:forEach var="review" items="${book.reviews}" >
        <div class="card mb-3">
          <div class="card-body">
            <div class="d-flex flex-start">
              <img class="rounded-circle shadow-1-strong me-3"
                src="data:image/jpg;base64,${review.customer.base64Image}" alt="avatar" width="40"
                height="40" />
              <div class="w-100">
                <div class="d-flex justify-content-between align-items-center mb-3">
                  <h6 class="text-primary fw-bold mb-0">
                  <div class="d-flex justify-content-center small text-warning mb-2">
                   	       		<c:forTokens items="${review.stars}" delims="," var="star">
	 								<c:if test='${star  eq "fill" }'>
	 									<div class="bi-star-fill"></div>
	 								</c:if>
	 								<c:if test='${star eq  "empty" }'>
	 									<div class="bi-star"></div>
	 								</c:if>
 								</c:forTokens>
                  		  </div>
                 	 <div>
                  	  ${review.customer.fullname}
                    <div>
                    <div class="text-dark ms-2">
                   		 
                  		  
                    <p>${review.comment}</p>
                    </div>
                  </h6>
                         
                  <p class="mb-0">${review.reviewTime}</p>
                </div>
             
              </div>
            </div>
          </div>
        </div>
	</c:forEach>

      </div>
    </div>
  </div>
</section>
			
			
			
			
		
		
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
		
</body>
</html>