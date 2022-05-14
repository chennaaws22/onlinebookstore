package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.entity.BookOrder;


public class OrderServices {
	private OrderDao orderDao;
	private RedirectingServices redirectingServices;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		redirectingServices = new RedirectingServices(this.request, this.response);
		orderDao = new OrderDao();
		
	}
	
	public void showOrderTable() throws ServletException, IOException {
		List<BookOrder> bookOrders = orderDao.listAll();
		
		this.request.setAttribute("bookOrders", bookOrders);
		
		this.redirectingServices.redirectTo("/admin/order_list.jsp");
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		int orderId = Integer.parseInt(this.request.getParameter("orderId"));
		BookOrder bookOrder = orderDao.get(orderId);
		
		if(bookOrder != null) {
			System.out.println("Redirecing to order detail page");
			this.request.setAttribute("bookOrder", bookOrder);
			this.redirectingServices.redirectTo("/admin/order_detail.jsp");
		} else {
			this.redirectingServices.redirectToWithMessage("/admin/message.jsp", "couldn't find order to view");
		}
		
	}
	
	public void deleteBookOrder() throws ServletException, IOException {
		int orderId = Integer.parseInt(this.request.getParameter("orderId"));
		orderDao.delete(orderId);
		this.showOrderTable();

	}
	
}
