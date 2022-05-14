package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Review;

public class ReviewDao extends JpaDao<Review> implements GenericDao<Review> {

	@Override
	public Review create(Review review) {
		Date reviewTime = new Date();
		review.setReviewTime(reviewTime);
		review = super.create(review);
		return review;
	}
	
	@Override
	public Review get(Object id) {
		return super.find(Review.class, id);
	}
	
	@Override
	public Review  update(Review review) {
		return super.update(review);
	}
	
	@Override
	public void delete(Object id) {
			super.delete(Review.class, id);
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("Review.listAll");
	}
	
	@Override
	public long count() {
		return super.countAll("Review.count");
	}
	
	public long countByBook(Integer bookId) {
		return super.countWithNamedQueryAndParam("Review.countByBook", "bookId", bookId);
	}
	
	public long countByCustomer(Integer customerId) {
		return super.countWithNamedQueryAndParam("Review.countByCustomer", "customerId", customerId);
	}
	
	public void close() {
		super.close();
	}
	
	public Review findByCustomerAndBook(Integer customerId, Integer bookId) {
		Map<String, Object> ids = new HashMap<>();
		ids.put("customerId", customerId);
		ids.put("bookId", bookId);
		
		Review review = super.findWithNamedQueryAndParam("Review.findByCustomerAndBook", ids);
		return review;
	}

}
