package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	private void redirectTo(String page) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(this.request, this.response);
	}
}
