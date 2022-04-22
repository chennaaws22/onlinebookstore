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

	public void showEditCustomerForm() throws ServletException, IOException {
		int customerId = Integer.parseInt(this.request.getParameter("customerId"));
		Customer customer = customerDao.get(customerId);
		this.request.setAttribute("customer", customer);
		
		redirectingServices.redirectTo("/admin/customer_form.jsp");		
	}
	
	public void editCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(this.request.getParameter("customerId"));
		Customer customerById = customerDao.get(customerId);
		if(customerById != null) {
			String email = this.request.getParameter("email");
			Customer customerByEmail = customerDao.findByEmail(email);
		
			System.out.println("------------Updating customer with------------------");
			if(customerByEmail != null && customerByEmail.getCustomerId() != customerById.getCustomerId()) {
				System.out.println("Email form: "  + email + "email database: " + customerByEmail.getEmail() );
				String message = "There is another customer with the same email address";
				redirectingServices.redirectToWithMessage("message.jsp", message);
			} else {
				Customer updatedCustomer =  new Customer(); 
				updatedCustomer.setCustomerId(customerId);
				updatedCustomer.setRegisterDate(customerById.getRegisterDate());
				
				setCustomerValueFromParam(updatedCustomer);
				customerDao.update(updatedCustomer);
				String message = "Customer Updated Successfully";
				redirectingServices.redirectToWithMessage("/admin/list_customer", message);
			}
		}else {
			redirectingServices.redirectToWithMessage("message.jsp", "there is no customer with this id");
		}
	}

	public void deleteCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(this.request.getParameter("customerId"));
		Customer customer = customerDao.get(customerId);
		
		if(customer != null) {
			customerDao.delete(customerId);
			String message = "customer deleted successfully";
			redirectingServices.redirectToWithMessage("/admin/list_customer", message);
		} else {
			String message = "There is no customer with this id to delete";
			redirectingServices.redirectToWithMessage("/admin/list_customer", message);
		}
		
	}

	public void showRegisterCustomerForm() throws ServletException, IOException {
		String registerPage = "frontend/customer_register_form.jsp";
		redirectingServices.redirectTo(registerPage);
	}
	
	public void registerCustomer() throws ServletException, IOException {
		String email = this.request.getParameter("email");
		Customer customerByEmail = customerDao.findByEmail(email);
		
		if(customerByEmail != null) {
			System.out.println("Email form: "  + email + "email database: " + customerByEmail.getEmail() );
			String message = "There is another customer with the same email address";
			redirectingServices.redirectToWithMessage("frontend/customer_register_form.jsp", message);
		} else {
				Customer registerCustomer = new Customer();
				setCustomerValueFromParam(registerCustomer);
				customerDao.create(registerCustomer);
				String message = "Customer Registerd Successfully";
				redirectingServices.redirectToWithMessage("/", message);
			}

	}

	public void showCustomerLogin() throws ServletException, IOException {
		redirectingServices.redirectTo("frontend/login.jsp");
	}

	public void loginCustomer() throws ServletException, IOException {
		String email = this.request.getParameter("email");
		String password = this.request.getParameter("password");
		
		boolean isUserLogedIn = customerDao.checkLogin(email, password);
		
		if(isUserLogedIn) {
			System.out.println("------Customer logeded in correctelly");
			this.request.getSession().setAttribute("customerLoggedIn", email);
			redirectingServices.redirectTo("/");
		}else {
			System.out.println("------Customer not loged correct");
			redirectingServices.redirectToWithMessage("/admin/login.jsp", "user not loged in");
		}
	}

}
