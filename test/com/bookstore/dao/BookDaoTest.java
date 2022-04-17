package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.persistence.*;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDaoTest{
	private static BookDao bookDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDao();
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDao.close();
	}
	
	@Test
	public void testCountAll() {
		long counts = bookDao.count();
		assertTrue(counts == 1);
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> books = bookDao.listNewBooks();
		
		assertEquals(4, books.size());
	}
	
	@Test
	public void testFindBookByTitle() {
		String title = "Java 8 in Action: Lambdas, Streams, and functional-style programming";
		Book book = bookDao.findByTitle(title);
		
		assertEquals(book.getTitle(), title);
	}
	
	
	
	
	@Test
	public void testFindByCategory() {
		int catId = 16;
		
		List<Book> books = bookDao.findByCategory(catId);
		System.out.println(books.get(0).getTitle());
		assertTrue(books.size() > 0);
	}
	
	@Test
	public void testNoFoundBooksByCategory() {
		int catId = 17;
		
		List<Book> books = bookDao.findByCategory(catId);
		assertTrue(books.size() > 0);
	}
	@Test
	public void testFindBookByIsbn() {
		String isbn = "161729120X";
		Book book = bookDao.findByIsbn(isbn);
		System.out.println(book.getTitle());
		assertEquals(book.getIsbn(), isbn);
	}
	@Test
	public void testGetBook() {
		Integer bookId = 39;
		Book book = bookDao.get(bookId);
		assertEquals(bookId, book.getBookId());
	}
	
	@Test
	public void testGetBookFails() {
		Integer bookId = 100;
		Book book = bookDao.get(bookId);
		assertNull(book);
	}
	
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId = 100;
		bookDao.delete(bookId);
		
		assertTrue(true);
		
	}
	
	@Test
	public void testListBooks() {
		List<Book> books = bookDao.listAll();
		assertFalse(books.isEmpty());
	}
	
	@Test
	public void testDeleteBooksuccess() {
		Integer bookId = 38;
		bookDao.delete(bookId);
		
		assertTrue(true);
		
	}
	
	@Test
	public void testSearchAuthor() {
		String keyword = "Joshua";
		List<Book> books = bookDao.search(keyword);
		assertEquals(1, books.size());
	}
	
	@Test 
	public void testSearchBook() {
		String keyword = "Java";
		List<Book> books = bookDao.search(keyword);
		assertEquals(4, books.size());
		
	}
	
	
	@Test
	public void testCreateBook() throws IOException, ParseException {
		Book book = new Book();
		book.setTitle("Java 8 in Action: Lambdas, Streams, and functional-style programming  ");
		book.setAuthor("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
		book.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8");
		book.setPrice(36.72f);
		book.setIsbn("1617291994");
		
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		Date publishDate = dateFormat.parse("2014-08-28");
		book.setPublishDate(publishDate);
		
		String imagePath = "E:\\projects\\Java Servlet, JSP and Hibernate Build eCommerce Website\\books\\Java 8 in Action.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		
		Category category = new Category("Java Core");
		category.setCategoryId(12);
		book.setCategory(category);
		
		Book createdBook = bookDao.create(book);
		
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@Test
	public void testUpdateBook() throws IOException, ParseException {
		Book book = new Book();
		book.setBookId(38);
		book.setTitle("Java 8 in Action");
		book.setAuthor("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
		book.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8");
		book.setPrice(36.72f);
		book.setIsbn("1617291994");
		
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		Date publishDate = dateFormat.parse("2014-08-28");
		book.setPublishDate(publishDate);
		
		String imagePath = "E:\\projects\\Java Servlet, JSP and Hibernate Build eCommerce Website\\books\\Java 8 in Action.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		
		Category category = new Category("Java Programming");
		category.setCategoryId(16);
		book.setCategory(category);
		
		Book updatedBook = bookDao.update(book);
		
		assertEquals(book.getCategory().getName(), updatedBook.getCategory().getName());
		
	}
	
	@Test
	public void TestCountByCategory() {
		long booksCount = bookDao.countByCategory(12);
		
		assertEquals(booksCount, 3);
		
	}
}
