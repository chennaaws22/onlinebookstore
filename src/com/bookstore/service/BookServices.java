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

public class BookServices {
	private EntityManager entityManager;
	private BookDao bookDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public BookServices(EntityManager entityManager, 
			HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		
		bookDao = new BookDao(this.entityManager);
	}
	
	public List<Book> listBooks() {
		return bookDao.listAll();
		
	}
	
	public void showBookTable() throws ServletException, IOException {
		this.request.setAttribute("books", this.listBooks());
		
		String listPage = "../admin/book_list.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
}
