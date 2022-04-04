package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	private EntityManager entityManager;
	private BookDao bookDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CategoryDao categoryDao;
	public BookServices(EntityManager entityManager, 
			HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;

		
		bookDao = new BookDao(entityManager);
		categoryDao = new CategoryDao(entityManager);
	}
	
	public List<Book> listBooks() {
		return bookDao.listAll();
		
	}
	
	public void showBookTable() throws ServletException, IOException {
		this.request.setAttribute("books", this.listBooks());
		
		String listPage = "book_list.jsp";
		
		this.redirectTo(listPage);
	}
	
	public void showBookForm() throws ServletException, IOException {
		System.out.println("show book form function");
		List<Category> categories = categoryDao.listAll();
		this.request.setAttribute("categories", categories);
		
		System.out.println(categories.get(0).toString());
		
		System.out.println("redirecting to book form");
		
		String bookFormPage = "../admin/book_form.jsp";
		redirectTo(bookFormPage);
	}
	
	public void createBook() throws IOException, ServletException {
		String title = this.request.getParameter("bookTitle");
		int categoryId = Integer.parseInt(this.request.getParameter("category"));
		String author = this.request.getParameter("bookAuthor");
		String description = this.request.getParameter("description");
		String isbn = this.request.getParameter("bookIsbn");
		float price =Float.parseFloat(this.request.getParameter("bookPrice"));
		
		Category category = categoryDao.get(categoryId);
	
		Book newBook = new Book();
		newBook.setTitle(title);
		newBook.setCategory(category);
		newBook.setAuthor(author);
		newBook.setDescription(description);
		newBook.setIsbn(isbn);
		newBook.setPrice(price);
		newBook.setPublishDate(getPublishDateFromRequest());
		newBook.setImage(getImageBytesFromRequest());
		
		System.out.println("title: " + title);
		System.out.println("categoryId: " + categoryId);
		System.out.println("author: " + author);
		System.out.println("description: " + description);
		System.out.println("isbn: " + isbn);
		System.out.println("price: " + price);
		System.out.println("publish Date: " + getPublishDateFromRequest());
		
		bookDao.create(newBook);

	}
	
	
	private Date getPublishDateFromRequest() {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(this.request.getParameter("bookDate"));
		}catch(ParseException px) {
			System.out.println("Error parsing the Date");
		}
	
		return publishDate;
	}
	
	private byte[] getImageBytesFromRequest() throws IOException, ServletException {
		Part part = this.request.getPart("bookImage");	
		if(part != null && part.getSize() > 0) {
			int partSize = (int) part.getSize();
			byte[] imageBytes = new byte[partSize];
			System.out.println("the typ of file is ---------> " + part.getContentType());
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			return imageBytes;
		}
		return null;
		
	}
	
	private void redirectTo(String page) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(this.request, this.response);
	}
}
