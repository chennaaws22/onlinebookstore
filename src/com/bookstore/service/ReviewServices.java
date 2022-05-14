package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CustomerDao;
import com.bookstore.dao.ReviewDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewServices {
	private ReviewDao reviewDao;
	private RedirectingServices redirectingServices;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		reviewDao = new ReviewDao();
		redirectingServices = new RedirectingServices(request, response);
		this.request = request;
		this.response = response;
	}
	
	public void showReviewTable() throws ServletException, IOException {
		List<Review> reviews = reviewDao.listAll();
		this.request.setAttribute("reviews", reviews);
		redirectingServices.redirectTo("review_list.jsp");
	}
	
	public void showReviewForm() throws ServletException, IOException {
	    Integer reviewId = Integer.parseInt(this.request.getParameter("reviewId"));
		Review review = reviewDao.get(reviewId);
		
		if(review != null) {
			this.request.setAttribute("review", review);
			redirectingServices.redirectTo("review_form.jsp");
		}else{
			redirectingServices.redirectToWithMessage("message.jsp", "There is no review with this Id");	
		}	
	}
	
	public void editReview() throws ServletException, IOException {
	    Integer reviewId = Integer.parseInt(this.request.getParameter("reviewId"));
	    String headline = this.request.getParameter("headline");
	    String comment = this.request.getParameter("comment");
	    
	    Review review = reviewDao.get(reviewId);
	    review.setHeadline(headline);
	    review.setComment(comment);
	    reviewDao.update(review);
	    this.request.setAttribute("message", "Review Updated Successfully");
	    showReviewTable();
	    
	}
	
	public void deleteReview() throws ServletException, IOException {
	    Integer reviewId = Integer.parseInt(this.request.getParameter("reviewId"));
	    Review review = reviewDao.get(reviewId);
		
		if (review != null) {
			reviewDao.delete(reviewId);
			String message = "The review has been deleted successfully.";
			this.request.setAttribute("message", message);
			showReviewTable();
			
		} else {
			String message = "Could you find review with ID " + reviewId
					+ ", or it might have been deleted by another admin";
			this.request.setAttribute("message", message);
			showReviewTable();
			
		}
	    
	}

	public void showCustomerReviewForm() throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		Integer bookId = Integer.parseInt(this.request.getParameter("bookId"));
		Book book = bookDao.get(bookId);
		this.request.setAttribute("book", book);
		
		String customerEmail = (String) this.request.getSession().getAttribute("customerLoggedIn");
		CustomerDao customerDao = new CustomerDao();
		Customer customer = customerDao.findByEmail(customerEmail);
		
		String reviewPage = "";
		if(book != null & customer != null) {
			Review review = reviewDao.findByCustomerAndBook(customer.getCustomerId(), bookId);
			if(review != null) {
				this.request.setAttribute("review", review);
				reviewPage = "frontend/customer_review_form_read_only.jsp";
			} else {
				reviewPage = "frontend/customer_review_form.jsp";
			}
		}

		System.out.println("Redirecting to review Customer Form");
		redirectingServices.redirectTo(reviewPage);
		
	}
		
	
	public void submitReview() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(this.request.getParameter("bookId"));
		Integer rating = Integer.parseInt(this.request.getParameter("rating"));
		String headline = this.request.getParameter("headline");
		String comment = this.request.getParameter("comment");
		
		CustomerDao customerDao = new CustomerDao();
		String customerEmail = (String)this.request.getSession().getAttribute("customerLoggedIn");
		Customer customer = customerDao.findByEmail(customerEmail);
		
		BookDao bookDao = new BookDao();
		Book book = bookDao.get(bookId);
		
		Review review = new Review();
		review.setBook(book);
		review.setCustomer(customer);
		review.setHeadline(headline);
		review.setComment(comment);
		review.setRating(rating);
		
		reviewDao.create(review);
		
		redirectingServices.redirectToWithMessage("/", "Your Review Created Successfully");
		
		
	}
	
}
	

