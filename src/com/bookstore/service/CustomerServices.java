package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDao;
import com.bookstore.entity.Customer;

public class CustomerServices {
	private RedirectingServices redirectingServices;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerDao customerDao;
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		redirectingServices = new RedirectingServices(this.request, this.response);
		customerDao = new CustomerDao();
		
	}
	
	public void showCustomerTable() throws ServletException, IOException {
		List<Customer> customers = customerDao.listAll();
		this.request.setAttribute("customers", customers);
		redirectingServices.redirectTo("/admin/customer_list.jsp");
	}

	public void showCreateCustomerForm() throws IOException, ServletException {
		redirectingServices.redirectTo("/admin/customer_form.jsp");
	}

	public void createCustomer() throws ServletException, IOException {
		Customer customer = new Customer();
		String email = this.request.getParameter("email");
		Customer customerByEmail = customerDao.findByEmail(email);
		if(customerByEmail != null) {
			String message = "there is existing customer with the email choose another one";
			redirectingServices.redirectToWithMessage("/admin/customer_form.jsp", message);
		} else {
			customer.setEmail(email);
			setCustomerValueFromParam(customer);
			customerDao.create(customer);
			String message = "Customer Created Successfully";
			redirectingServices.redirectToWithMessage("/admin/list_customer", message);
		}

	}
	
	private void setCustomerValueFromParam(Customer customer) {
		String fullName = this.request.getParameter("fullName");
		String email = this.request.getParameter("email");
		String password = this.request.getParameter("password");
		String city = this.request.getParameter("city");
		String country = this.request.getParameter("country");
		String address = this.request.getParameter("address");
		String phone = this.request.getParameter("phone");
		String zipcode = this.request.getParameter("zipcode");
		
		customer.setFullname(fullName);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setCity(city);
		customer.setCountry(country);
		customer.setAddress(address);
		customer.setPhone(phone);
		customer.setZipcode(zipcode);
	}

}
