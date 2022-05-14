package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;


@WebServlet("/remove_from_cart")
public class RemoveBookFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object cartObject = request.getSession().getAttribute("shoppingCart");
		
		ShoppingCart shoppingCart = (ShoppingCart) cartObject;			
		
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		
		BookDao bookDao = new BookDao();
		Book book = bookDao.get(bookId);
		System.out.println("remove Book from cart with book id :::::::::: " + bookId);
		shoppingCart.removeBook(book);
		String pageRedirect = request.getContextPath().concat("/view_cart");
		response.sendRedirect(pageRedirect);
		
	}

}
