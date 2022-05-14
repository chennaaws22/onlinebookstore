package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;

@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] bookIdsString = request.getParameterValues("bookId");
		int size = bookIdsString.length;
		String[] bookQuantitiesString = new String[size];
		Book[] books = new Book[size];
		BookDao bookDao = new BookDao();
		
		for(int i=0; i < size; i++) {
			int boodIdInt = Integer.parseInt(bookIdsString[i]);
			books[i] = bookDao.get(boodIdInt);
			
			String qnt = request.getParameter("quantity" + bookIdsString[i]);
			bookQuantitiesString[i] = qnt;
		}
		
		int[] bookQuantities = Arrays.stream(bookQuantitiesString).mapToInt(Integer::parseInt).toArray();
		
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		shoppingCart.updateCartItemsQuantity(books, bookQuantities);
		
	
			
		response.sendRedirect(request.getContextPath().concat("/view_cart"));

	}

}
