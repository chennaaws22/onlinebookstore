package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderDaoTest {
	private static OrderDao orderDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDao = new OrderDao();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDao.close();
	}
	
	
	@Test
	public void testDeleteBookOrder() {
		int bookOrderId = 37;
		orderDao.delete(bookOrderId);
		
		BookOrder bookOrder = orderDao.get(bookOrderId);
		
		assertNull(bookOrder);
		
	}
	
	
	@Test
	public void testCountAllOrders() {
		long num = orderDao.count();
		
		assertTrue(num == 3);
	}
	
	@Test
	public void testUpdateBookOrder() {
		Integer orderId = 36;
		BookOrder bookOrder = orderDao.get(orderId);
		
		bookOrder.setStatus("Ready");
		for(OrderDetail od : bookOrder.getOrderDetails()) {
			od.setQuantity(1);
			od.setSubtotal(40.0f);
		}
		BookOrder updatedBookOrder = orderDao.update(bookOrder);
		
		assertEquals(bookOrder.getStatus(), updatedBookOrder.getStatus());

	}
	
	@Test
	public void testListAllOrders() {
		List<BookOrder> bookOrders = orderDao.listAll();
		for(BookOrder bo: bookOrders) {
			System.out.println("bookOrderid: " + bo.getOrderId() + " book customerName: " + bo.getRecipientName());
			System.out.println("bookOrderMethod: " + bo.getPaymentMethod() + " bookOrderDate " + bo.getOrderDate());
			for(OrderDetail od : bo.getOrderDetails()) {
				System.out.println("---------"+ "bookId: " + od.getBook().getBookId() + " book quantities " + od.getQuantity());
			}
		}
		assertTrue(bookOrders.size() == 3);
		
	}
	
	@Test
	public void testGetBookOrder() {
		Integer orderId = 33;
		BookOrder bookOrder = orderDao.get(orderId);
		
		System.out.println(bookOrder.getOrderDetails().toArray());
		
		assertNotNull(bookOrder);
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder bookOrder = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(31);
		
		bookOrder.setCustomer(customer);
		bookOrder.setRecipientName("rabie");
		bookOrder.setPaymentMethod("CashOnDelivery");
		bookOrder.setRecipientPhone("327888");
		bookOrder.setShippingAddress("cairo");
		bookOrder.setTotal(60.0f);
				
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();
		Book book = new Book(47);
		
		orderDetail.setBook(book);
		orderDetail.setBookOrder(bookOrder);
		orderDetail.setQuantity(1);
		orderDetail.setSubtotal(60.0f);
//		
//		OrderDetail orderDetail2 = new OrderDetail();
//		Book book2 = new Book(47);
//		
//		orderDetail2.setBook(book2);
//		orderDetail2.setBookOrder(bookOrder);
//		orderDetail2.setQuantity(3);
//		orderDetail2.setSubtotal(150.0f);
//	
		
		orderDetails.add(orderDetail);
//		orderDetails.add(orderDetail2);
		
		bookOrder.setOrderDetails(orderDetails);
		
		BookOrder createdBookOrder = orderDao.create(bookOrder);
		
		assertTrue(createdBookOrder.getOrderId() > 0);
		
	}
	
	

}
