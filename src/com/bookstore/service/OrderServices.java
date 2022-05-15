package com.bookstore.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.ShoppingCart;
import com.bookstore.dao.CustomerDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;


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
	
	
	public void placeOrder() throws ServletException, IOException {
		String recipientName = this.request.getParameter("recipientName");
		String recipientPhone = this.request.getParameter("recipientPhone");
		String city = this.request.getParameter("city");
		String country = this.request.getParameter("country");
		String streetAddress = this.request.getParameter("streetAddress");
		String zipcode = this.request.getParameter("zipcode");
		String paymentMethod = this.request.getParameter("paymentMethod");
		String shippingAddress = city + " " + country + " " + streetAddress;  
		HttpSession httpSession = this.request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute("shoppingCart");
		String customerLoggedInEmail = (String) httpSession.getAttribute("customerLoggedIn");
		Customer customer = new CustomerDao().findByEmail(customerLoggedInEmail);
				
		if(shoppingCart != null && customer != null) {
			BookOrder bookOrder = new BookOrder();
			bookOrder.setCustomer(new Customer(customer.getCustomerId()));
			bookOrder.setRecipientName(recipientName);
			bookOrder.setRecipientPhone(recipientPhone);
			bookOrder.setPaymentMethod(paymentMethod);
			bookOrder.setShippingAddress(shippingAddress);
			
			Map<Book, Integer> shoppingCartItems = shoppingCart.getCartItems();
			Iterator<Book> iterator =shoppingCartItems.keySet().iterator();
			
			while(iterator.hasNext()) {
				Book book = iterator.next();
				Integer quantity = shoppingCartItems.get(book);
				float subtotal = book.getPrice() * quantity;
				
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setBook(book);
				orderDetail.setQuantity(quantity);
				orderDetail.setSubtotal(subtotal);
				orderDetail.setBookOrder(bookOrder);
				
				bookOrder.getOrderDetails().add(orderDetail);
			}
			
			bookOrder.setTotal(shoppingCart.getTotalAmount());
			orderDao.create(bookOrder);
			shoppingCart.clear();
			
			this.redirectingServices.redirectTo("frontend/success_checkout.jsp");
		} 
		
		
		
	}
}
