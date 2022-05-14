package com.bookstore.controller.frontend;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bookstore.entity.Book;

public class ShoppingCart {

	private Map<Book, Integer> cartItems;
	
	public ShoppingCart() {
		cartItems = new HashMap<>();
	}
	
	public void addBook(Book book) {
		if(cartItems.containsKey(book)) {
			Integer newQuantity = cartItems.get(book) + 1;
			cartItems.put(book, newQuantity);
		} else {
			cartItems.put(book, 1);
		}
	}
	
	public void removeBook(Book book) {
		cartItems.remove(book);
		System.out.println("size of cart Items --------> " + cartItems.size());
	}
	
	public void updateCartItemsQuantity(Book[] books, int[] quantities) {
		int size = books.length;
		for(int i = 0; i < size; i++) {
			Book key = books[i];
			int value = quantities[i];
			cartItems.put(key, value);
		}
		
	}
	
	public int getTotalQuantity() {
		int totalQuantity = 0;
		
		Iterator<Book> iterator  = cartItems.keySet().iterator();
		
		while(iterator.hasNext()){
			Book book = iterator.next();
			totalQuantity += this.cartItems.get(book);
		}
		
		return totalQuantity;
	}
	
	public double getTotalAmount() {
		double totalAmount = 0.0f ;
		
		Iterator<Book> iterator  = cartItems.keySet().iterator();
		
		while(iterator.hasNext()){
			Book book = iterator.next();
			Integer quantity = this.cartItems.get(book);
			totalAmount += book.getPrice() * quantity;
		}
		
		return totalAmount;
	}
	
	public int getTotalItems() {
		return this.cartItems.size();
		
	}
	
	public Map<Book, Integer> getCartItems(){
		return this.cartItems;
	}

	public void clear() {
		cartItems.clear();
		
	}
}
