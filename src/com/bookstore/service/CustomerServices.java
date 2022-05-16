package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.ReviewDao;
import com.bookstore.entity.Book;
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
			ReviewDao reviewDao = new ReviewDao();
			long reviews = reviewDao.countByCustomer(customerId);
			if(reviews != 0) {
				String message = "Could not delete customer with ID " + customerId + " because he/she posted reviews for books'.";
				this.request.setAttribute("message", message);
				showCustomerTable();
				return;
			} 
			
			long customerByOrderCount = customerDao.countByOrder(customerId);
			if(customerByOrderCount != 0) {
				String message = "Could not delete customer with ID " + customerId + " because he/she ordered books'.";
				this.request.setAttribute("message", message);
				showCustomerTable();
				return;
			}
			
			customerDao.delete(customerId);
			String message = "customer deleted successfully";
			this.request.setAttribute("message", message);
			showCustomerTable();
			
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
			HttpSession session = request.getSession(false);
			this.request.getSession().setAttribute("customerLoggedIn", email);
			this.request.getSession().setAttribute("customerName", customerDao.findByEmail(email).getFullname());
			Object objRedirectURL = session.getAttribute("redirectingURL");
			if(objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				this.request.getSession().removeAttribute("redirectingURL");
				this.response.sendRedirect(redirectURL);
			}else {
				showCustomerProfile();
			}
			
		}else {
			System.out.println("------Customer not loged correct");
			redirectingServices.redirectToWithMessage("frontend/login.jsp", "Customer not loged in");
		}
	}

	public void showCustomerProfile() throws ServletException, IOException {
			String customerLogedInEmail = (String)this.request.getSession().getAttribute("customerLoggedIn");
			Customer customer = customerDao.findByEmail(customerLogedInEmail);
			if(customer != null) {
				this.request.setAttribute("customer", customer);
				System.out.println("Redirecting to customer profile");
				redirectingServices.redirectTo("frontend/customer_profile.jsp");
			} else {
				redirectingServices.redirectToWithMessage("frontend/message.jsp","Customer Email not found");
			}
	}
	
	public void updateCustomerProfile() throws IOException, ServletException {
		String customerLogedInEmail = (String)this.request.getSession().getAttribute("customerLoggedIn");

		Customer customer = customerDao.findByEmail(customerLogedInEmail);
		System.out.println("customer address from database --->  " + customer.getAddress());
		if(customer != null) {
			System.out.println("Setting customer value ");

			setCustomerValueFromParam(customer);
		
			customerDao.update(customer);
			this.request.setAttribute("message", "Cusotmer updated successfully");
			showCustomerProfile();
		}
	}
	
	
	private void setCustomerValueFromParam(Customer customer) throws IOException, ServletException {
		String fullName = this.request.getParameter("fullname");
		String email = this.request.getParameter("email");
		String password = this.request.getParameter("password");
		String city = this.request.getParameter("city");
		String country = this.request.getParameter("country");
		String address = this.request.getParameter("address");
		String phone = this.request.getParameter("phone");
		String zipcode = this.request.getParameter("zipcode");
		
		if(fullName != null && !(fullName.equals("")) ) {
			customer.setFullname(fullName);
		}
		
		if(email != null && !(email.equals("")) ) {
			customer.setEmail(email);
		}
		
		if(password != null && !(password.equals("")) ) {
			String password2 = this.request.getParameter("password2");
			if(password.equals(password2)) {
				String hashedPassword = HashGenerator.generateMD5(password);
				customer.setPassword(hashedPassword);
			} else {
				this.request.setAttribute("message", "passwords not equal");
				showCustomerProfile();
			}
		}
		
		if(city != null && !(city.equals("")) ) {
			customer.setCity(city);
		}
		if(country != null && !(country.equals("")) ) {
			customer.setCountry(country);
		}
		if(address != null && !(address.equals("")) ) {
			customer.setAddress(address);
		}
		if(phone != null && !(phone.equals("")) ) {
			customer.setPhone(phone);
		}
		if(zipcode != null && !(zipcode.equals("")) ) {
			customer.setZipcode(zipcode);
		}
		
		getImageBytesFromRequest(customer);
	}
	private void getImageBytesFromRequest(Customer customer) throws IOException, ServletException {
		Part part = this.request.getPart("customerImage");	
		System.out.println("Checking part #########");
		if(part != null && part.getSize() > 0) {
			System.out.println("###part exist #########");
			int partSize = (int) part.getSize();
			byte[] imageBytes = new byte[partSize];
			System.out.println("the type of file is ---------> " + part.getContentType());
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			customer.setImage(imageBytes);
		}
	}


}
