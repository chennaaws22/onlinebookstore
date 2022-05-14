<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>book review</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
</head>
<body>
	<jsp:directive.include file="header.jsp"/><br>

<div class="container p-5">
    <div class="row mt-4">
      <div class="col-md-6 offset-md-3">
        <h2 class="text-center text-dark m-5"> You already wrote a review for this book</h2>
	    <div class="card mb-3" style="max-width:800px;">
			<div class="row g-0">
		    	<div class="col-md-4">
		      		<img  src="data:image/jpg;base64,${book.base64Image}" class="img-fluid rounded-start mt-5 ml-2">
		    	</div>
		    	<div class="col-md-8">
		     	 <div class="card-body">
			        <div class="card border-success mb-3" style="max-width: 700px;">
			  		<div class="card-header bg-transparent border-success">${book.title}</div>
			  		<div class="card-body text-success">
		    		<form>	
						<div id="rateYo"></div>
						<input type="hidden"  name="bookId" readonly value="${book.bookId}">
			    		<div class="mb-3">
			  				<label for="headline" class="form-label">Headline</label>
			  				<input type="text" class="form-control" readonly id="headline" value="${review.headline}" placeholder="summary of the review">
						</div>
						<div class="mb-3">
		  					<label for="comment" class="form-label">Comment</label>
		  					<textarea class="form-control" readonly id="comment" rows="3">${review.comment}</textarea>
						</div>
		    		</form>
		 		 </div>
		 		 <div class="card-footer bg-transparent border-success">customer: ${sessionScope.customerName}</div>
			</div>
	      </div>
	    </div>
  </div>
</div>

      </div>
    </div>
   </div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
	
</body>


<script>
	$(document).ready(function(){
		  $("#rateYo").rateYo({
			    fullStar: true,
				readOnly:true,
				rating: ${review.rating}
			    	
			    }); 
	});
</script>
</html>