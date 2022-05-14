package com.bookstore.controller.frontend;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;

public class ShoppingCartTest {
	private static Map<Book, Integer> cartItems;
	private static ShoppingCart shoppingCart;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shoppingCart = new ShoppingCart();
		Book book1 = new Book(1);
		shoppingCart.addBook(book1);
		shoppingCart.addBook(book1);
		Book book2 = new Book(2);
		
		shoppingCart.addBook(book2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddBookItem() {
		Book book1 = new Book(1);
		Book book2 = new Book(2);
		
		shoppingCart.addBook(book2);
		
		cartItems = shoppingCart.getCartItems();
		Integer quantity = cartItems.get(book1);
		
		
		assertEquals(new Integer(2), quantity);
	}

	
	@Test
	public void testremoveBookItem() {
		
		shoppingCart.removeBook(new Book(2));
		
		cartItems = shoppingCart.getCartItems();
		Integer quantity = cartItems.get(new Book(2));
		
		assertNull(quantity);
	}

	@Test
	public void testGetTotalQuantity() {
		Book book1 = new Book(1);
		shoppingCart.addBook(book1);
		
		
		int totalQuantity = shoppingCart.getTotalQuantity();
		
		assertTrue(totalQuantity == 4);
	}
	
	@Test
	public void testGetTotalAmount() {
		ShoppingCart cart = new ShoppingCart();
		Book book3 = new Book(3);
		book3.setPrice(10);
		cart.addBook(book3);
		
		
		assertEquals(10.0f , cart.getTotalAmount(), 0.0f);
	}
	
	@Test
	public void testUpdateCartItems() {
		ShoppingCart cart = new ShoppingCart();
		
		
		cart.addBook(new Book(1));
		cart.addBook(new Book(2));
		
		Book book1 = new Book(1);
		Book book2 = new Book(2);
		Book[] books = {book1, book2};
		int[] quantities = {3, 4};
		
		cart.updateCartItemsQuantity(books, quantities);
		
		assertEquals(7, cart.getTotalQuantity());
		
		
	
		
	}
	
}
