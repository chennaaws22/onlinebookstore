package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDaoTest {
	private static ReviewDao reviewDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDao();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

	@Test
	public void testCreateReview() {
		Book book = new Book();
		book.setBookId(40);
		
		Customer customer = new Customer();
		customer.setCustomerId(32);
		
		Review review = new Review();
		review.setRating(4);
		review.setHeadline("Review for Effective Java");
		review.setComment("good book");
		review.setBook(book);
		review.setCustomer(customer);
		review = reviewDao.create(review);
		assertTrue(review.getReviewId() > 0);
	}
	
	@Test
	public void testGetReview() {
		Review review = reviewDao.get(17);
		assertTrue(review.getReviewId() == 17);
	}

	@Test
	public void testCount() {
		long reviewsNum = reviewDao.count();
		assertTrue(reviewsNum == 3);
	}
	
	@Test
	public void testListAll() {
		List<Review> reviews = reviewDao.listAll();
		reviews.forEach(System.out::println);
		assertTrue(reviews.size() == 3);
	}
	
	@Test
	public void testUpdate() {
		Integer reviewId = 17;
		Review review = reviewDao.get(reviewId);
		review.setRating(2);
		review = reviewDao.update(review);
		
		assertTrue(review.getRating() == 2);
	}
	
	@Test
	public void testDelete() {
		Integer reviewId = 17;
		reviewDao.delete(reviewId);
		
		Review review = reviewDao.get(reviewId);
		assertNull(review);
		
	}
	
	@Test
	public void testCountByBook() {
		Integer bookId = 40;
		long reviewsCount = reviewDao.countByBook(bookId);
		assertEquals(reviewsCount, 1);
	}
	
	@Test
	public void testFindByCustomerAndBook() {
		Integer customerId = 27;
		Integer bookId = 40;
		
		Review review = reviewDao.findByCustomerAndBook(customerId, bookId);
		
		assertNotNull(review);
	}
	
	@Test
	public void testFindByCustomerAndBookNull() {
		Integer customerId = 27;
		Integer bookId = 22;
		
		Review review = reviewDao.findByCustomerAndBook(customerId, bookId);
		
		assertNull(review);
	}
}
