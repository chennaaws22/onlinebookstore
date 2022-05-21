package com.bookstore.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.ShoppingCart;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.CustomerDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.UsersDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.Users;


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

	public void viewMyOrder() throws ServletException, IOException {
		HttpSession httpSession = this.request.getSession();
		String customerLoggedInEmail = (String) httpSession.getAttribute("customerLoggedIn");
	
		Customer customer = new CustomerDao().findByEmail(customerLoggedInEmail);
		Integer customerId = customer.getCustomerId();
		List<BookOrder> customerBookOrders = orderDao.listByCustomer(customerId);			
		this.request.setAttribute("customerBookOrders", customerBookOrders);
		this.response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
		System.out.println("redirecting to my orders..................");
		this.redirectingServices.redirectTo("frontend/my_order.jsp");
		
	}

	public void viewMyOrderDetail() throws ServletException, IOException {
		HttpSession httpSession = this.request.getSession();
		String customerLoggedInEmail = (String) httpSession.getAttribute("customerLoggedIn");
		int orderId = Integer.parseInt(this.request.getParameter("orderId"));
		Customer customer = new CustomerDao().findByEmail(customerLoggedInEmail);
		if(customer == null) {
			this.redirectingServices.redirectToWithMessage("frontend/message.jsp", "could't find customer with this Id");
			return;
		}
			
		BookOrder bookOrder = orderDao.getByOrderAndCustomer(orderId, customer.getCustomerId());
		System.out.println("Found Customer and order with id " + customer.getCustomerId() +" " + orderId);
		if(bookOrder != null) {
			System.out.println("Redirecing to order detail page");
			this.request.setAttribute("bookOrder", bookOrder);
			this.response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
					
			this.redirectingServices.redirectTo("frontend/my_order_detail.jsp");
		} else {
			this.redirectingServices.redirectToWithMessage("frontend/message.jsp", "You must be owner to this order to vie");
		}
	
	}

	public void showEditAdminOrder() throws ServletException, IOException {
		if(!isAdminLogedIn()) {
			this.redirectingServices.redirectToWithMessage("/admin/", "please, log in first");
			return;
		}
		
		Integer orderId = Integer.parseInt(this.request.getParameter("orderId"));
	
		HttpSession session = this.request.getSession();
		Object isPendingAddingBook = session.getAttribute("isNewBookAddedPending");
		System.out.println("is pending book" + isPendingAddingBook);
		if(isPendingAddingBook == null) {
			
			BookOrder bookOrder = orderDao.get(orderId);
			if(bookOrder == null) {
				this.redirectingServices.redirectToWithMessage("message.jsp", "There is no order with this id " + orderId);
				return;
			}
			session.setAttribute("orderInAdmin", bookOrder);	
			System.out.println("Session book Order added");

		} else {
			session.removeAttribute("isNewBookAddedPending");
			System.out.println("removed from session flag");

		}
		
		this.redirectingServices.redirectTo("edit_order.jsp");
	}

	
	public void editOrderAdmin() throws ServletException, IOException {
		if(!isAdminLogedIn()) {
			this.redirectingServices.redirectToWithMessage("/admin/", "please, log in first");
			return;
		}	
		
		HttpSession session = this.request.getSession();
		BookOrder bookOrder = (BookOrder) session.getAttribute("orderInAdmin");
		
		String recipientName = this.request.getParameter("recipientName");
		String recipientPhone = this.request.getParameter("recipientPhone");
		String shippingAddress = this.request.getParameter("shippingAddress");
		String orderStatus = this.request.getParameter("orderStatus");
		
		String[] bookIds = this.request.getParameterValues("bookId");
		
		Map<Integer, Integer> bookQuantitesMap = new HashMap<>();
		
		for(String bookId: bookIds) {
			int bookIdInt = Integer.parseInt(bookId);
			System.out.println("Parsed ID integer " + bookId);

			int bookQuantityInt = Integer.parseInt(this.request.getParameter("bookQuantity"+bookId));
			System.out.println("Parsed ID integer " + bookQuantityInt);

			bookQuantitesMap.put(bookIdInt, bookQuantityInt);		
		}
				
		float total = 0;
		Iterator orderDetailsIterator = bookOrder.getOrderDetails().iterator();
		while(orderDetailsIterator.hasNext()) {
			OrderDetail orderDetailIterator = (OrderDetail) orderDetailsIterator.next();
			int bookId = orderDetailIterator.getBook().getBookId();
			int newQuantity = bookQuantitesMap.get(bookId);
			orderDetailIterator.setQuantity(newQuantity);
			
			float price = orderDetailIterator.getBook().getPrice();
			
			float subtotal = newQuantity * price;
			orderDetailIterator.setSubtotal(subtotal);
			
			total += subtotal;
		}
		
		bookOrder.setRecipientName(recipientName);
		bookOrder.setRecipientPhone(recipientPhone);
		bookOrder.setShippingAddress(shippingAddress);
		bookOrder.setStatus(orderStatus);
		bookOrder.setTotal(total);
		
		System.out.println("updating Order.............................");			
		BookOrder updatedBookOrder = orderDao.update(bookOrder);
		this.redirectingServices.redirectToWithMessage("/admin/list_order", "Order Updated Successfully");
			
	}
	
	
	private boolean isAdminLogedIn() {
		boolean isAdminLogedIn = false;
		
		HttpSession httpSession = this.request.getSession();
		String adminLoggedInEmail = (String) httpSession.getAttribute("userLogedIn");
		UsersDao usersDao = new UsersDao();
		Users admin = usersDao.findByEmail(adminLoggedInEmail);
		if(admin != null) {
			isAdminLogedIn = true;
		}
		
		return isAdminLogedIn;
	}

	public void showAddAdminBookForm() throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		List<Book> books = bookDao.listAll();
		
		this.request.setAttribute("books", books);
		
		this.redirectingServices.redirectTo("admin_add_book_form.jsp");
		
		
		
	}
	
	public void addBookToAdminOrder() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(this.request.getParameter("book"));
		Integer quantity = Integer.parseInt(this.request.getParameter("bookQuantity"));
		
		BookDao bookDao = new BookDao();
		Book book = bookDao.get(bookId);
		
		BookOrder order = (BookOrder) this.request.getSession().getAttribute("orderInAdmin");
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setBook(book);
		orderDetail.setQuantity(quantity);
		orderDetail.setSubtotal(book.getPrice() * quantity);
		orderDetail.setBookOrder(order);
		
		order.setTotal(order.getTotal() + orderDetail.getSubtotal());

		order.getOrderDetails().add(orderDetail);
		
		this.request.setAttribute("book", book);
		this.request.getSession().setAttribute("isNewBookAddedPending", true);
		this.redirectingServices.redirectTo("order_success.jsp");
	}

	public void removeBookFromAdminOrder() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(this.request.getParameter("bookId"));
		
		BookOrder order = (BookOrder) this.request.getSession().getAttribute("orderInAdmin");

		for(OrderDetail od : order.getOrderDetails()) {
			if(od.getBook().getBookId() == bookId) {
				float newTotal = order.getTotal() - od.getSubtotal();
				order.getOrderDetails().remove(od);
				order.setTotal(newTotal);
				this.redirectingServices.redirectTo("edit_order.jsp");
				return;
			}
		}
		
		this.redirectingServices.redirectTo("edit_order.jsp");	
	}
}
