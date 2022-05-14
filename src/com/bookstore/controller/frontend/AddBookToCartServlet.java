package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;


@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object cartObject = request.getSession().getAttribute("shoppingCart");
		
		ShoppingCart shoppingCart = null;
		if(cartObject != null && cartObject instanceof ShoppingCart) {
			shoppingCart = (ShoppingCart) cartObject;
			
		}else {
			shoppingCart = new ShoppingCart();
			 request.getSession().setAttribute("shoppingCart", shoppingCart);
		}
		
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		BookDao bookDao = new BookDao();
		Book book = bookDao.get(bookId);
		
		if(book != null) {
			shoppingCart.addBook(book);
			String pageRedirect = request.getContextPath().concat("/view_cart");
			response.sendRedirect(pageRedirect);
		}
	}

}
