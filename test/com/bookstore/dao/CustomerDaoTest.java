package com.bookstore.dao;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDaoTest {
	private static CustomerDao customerDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao = new CustomerDao();
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}
	
	@Test 
	public void testCustomerByOrderCount() {
		Integer customerId = 28;
		
		long counts = customerDao.countByOrder(customerId);
		
		assertTrue(counts == 1);
	}
	
	@Test
	public void testCreateCustomer() throws ParseException {
		Customer customer = new Customer();
		customer.setFullname("test");
		customer.setEmail("test@gmail.com");
		customer.setCountry("Egypt");
		customer.setCity("ZAGAZIG");
		customer.setAddress("asdsfsg");
		customer.setPassword("123456");
		customer.setPhone("123456789");
		customer.setZipcode("4321");
		
		Customer createdCustomer = customerDao.create(customer);
		
		assertTrue(createdCustomer.getEmail().equals(customer.getEmail()) );
	}
	
	@Test
	public void testGetCustomer() {
		Integer id = 11;
		Customer c = customerDao.get(id);
		assertEquals(c.getCustomerId(), id);
	}
	
	@Test
	public void testUpdateCusotmer() {
		Customer c = customerDao.get(11);
		String fullName = "mohamed shaker 2";
		c.setFullname(fullName);
		customerDao.update(c);
		assertTrue(c.getFullname().equals(fullName));
	}

	
	@Test
	public void testDeleteCustomer() {
		Integer id = 11;
		customerDao.delete(id);
		Customer c = customerDao.get(id);
		
		assertNull(c);
	}
	
	
	@Test
	public void testListAll() {
		List<Customer> customers = customerDao.listAll();
		customers.forEach(c -> System.out.println(c.getEmail()));
		 
		assertTrue(customers.size() > 0);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "mohamedshaker@gmail.com";
		Customer customer = customerDao.findByEmail(email);
		
		assertTrue(customer.getEmail().equals(email));
	}
	
	@Test
	public void testCountAll() {
		long count = customerDao.count();
		
		assertEquals(count, 2);
		
	}
	
}


