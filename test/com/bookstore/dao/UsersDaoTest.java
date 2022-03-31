package com.bookstore.dao;



import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.*;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UsersDaoTest {

	private static UsersDao usersDao ;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDaoTest.setUpBeforeClass();
		usersDao = new UsersDao(BaseDaoTest.entityManager);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDaoTest.tearDownAfterClass();
	}

	@Test
	public void testCreateUser() {
		Users u2 = new Users();
		u2.setFullName("mohamed 20");
		u2.setEmail("mohamed20@gmail.com");
		u2.setPassword("mohamed20");
		
		Users savedU = usersDao.create(u2);
		
		assertTrue(savedU.getUserId() > 1);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUserNotSetFields() {
		Users u1 = new Users();
		
		Users savedU = usersDao.create(u1);
		
	}
	
	@Test
	public void testGetUserFound() {
		Integer userID = 19;
		Users u1 = usersDao.get(userID);
		System.out.println(u1.toString());
		assertNotNull(u1);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "adel@yahoo.com";
		Users u1 = usersDao.findByEmail(email);
		System.out.println(u1.toString());
		assertNotNull(u1);
	}
	
	@Test
	public void testGetUserNotFound() {
		Integer userID = 1;
		Users u1 = usersDao.get(userID);
		System.out.println(u1.toString());
		assertNull(u1);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 22;
		usersDao.delete(userId);
		Users u = usersDao.get(userId);
		assertNull(u);
		
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNotExistUser() {
		Integer userId = 100;
		usersDao.delete(userId);
	}
	
	@Test
	public void testListAllUsers() {
		List<Users> users = usersDao.listAll();
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void testCount() {
		long usersCount = usersDao.count();
		assertTrue(usersCount == 3);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "mshaker@test.test";
		String password = "password123";
		
		assertTrue(usersDao.checkLogin(email, password));
	}
	
	@Test
	public void testCheckLoginFailure() {
		String email = "mshakedsr@test.test";
		String password = "password123";
		
		assertFalse(usersDao.checkLogin(email, password));
	}
	
	
	
	
	
	
}


