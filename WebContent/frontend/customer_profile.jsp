<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>customer profile</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
	<jsp:directive.include file="header.jsp"/><br>
	<div class="container rounded bg-white  mb-1">
		<div class="row">
			<div class="d-flex justify-content-between align-items-center mb-1">
		       <h4 class="text-right">Customer Profile</h4>
		     </div>
		     <c:if test="${message != null}">
				<h4 class="alert alert-success" align="center">${message} </h4>
			</c:if>
	    </div>
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="data:image/png;base64, ${customer.base64Image}"><span class="font-weight-bold">${cusomer.fullname}</span><span class="text-black-50">${customer.email}</span><span> </span></div>
        </div>
       
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
              <form action="update_profile" method="post" enctype="multipart/form-data">
                <div class="row mt-3">
                    <div class="col-md-12"><label class="labels">Full Name</label><input type="text" name="fullname" class="form-control" value="${customer.fullname}"></div>
                    <div class="col-md-7"><label class="labels">Email</label><input type="text" readonly	 class="form-control"  value="${customer.email}"></div> <div class="col-md-5 mt-4">cannot be updated</div>
                    <div class="col-md-12"><label class="labels">Phone</label><input type="text" name="phone" class="form-control"  value="${customer.phone}"></div>
                    <div class="col-md-12"><label class="labels">Address</label><input type="text" name="address" class="form-control"  value="${customer.address}"></div>
                    <div class="col-md-12"><label class="labels">ZIP Code</label><input type="text" name="zipcode" class="form-control"  value="${customer.zipcode}"></div>
                      <div class="row mt-3">
                    	<div class="col-md-6"><label class="labels">Country</label><input type="text" name="country" class="form-control"  value="${customer.country}"></div>
                   	 	<div class="col-md-6"><label class="labels">City</label><input type="text" name="city" class="form-control" value="${customer.city}" ></div>
                	</div>
                	<div class="col-md-12"><hr></div>
                	 <div class="col-md-12"><p>leave password empty if you won't change it</p> </div>
                	
                    <div class="col-md-12"><label class="labels">password</label><input name="password" type="text" class="form-control"> </div>
                    <div class="col-md-12"><label class="labels">password again</label><input name="password2"  type="text" class="form-control"></div>
                </div>
               
                <div class="mt-1 text-center"><input class="btn btn-primary profile-button" type="submit" value="Update Profile"></div>
                </form>
           	 </div>
        </div>
      
  <!--       <div class="col-md-4">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center experience"><span>Edit Experience</span><span class="border px-3 p-1 add-experience"><i class="fa fa-plus"></i>&nbsp;Experience</span></div><br>
                <div class="col-md-12"><label class="labels">Experience in Designing</label><input type="text" class="form-control" placeholder="experience" value=""></div> <br>
                <div class="col-md-12"><label class="labels">Additional Details</label><input type="text" class="form-control" placeholder="additional details" value=""></div>
            </div>
        </div>
      --> 
    </div>
    
</div>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>