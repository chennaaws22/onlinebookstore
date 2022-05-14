package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;
import com.bookstore.entity.Users;

public class CustomerDao extends JpaDao<Customer> implements GenericDao<Customer> {

	
	@Override
	public Customer create(Customer customer) {
		String encryptedPassword = HashGenerator.generateMD5(customer.getPassword());
		Date registerDate = new Date();
		customer.setRegisterDate(registerDate);
		customer.setPassword(encryptedPassword);
		Customer createdCustomer = super.create(customer);
		
		return createdCustomer;
	}
	
	@Override
	public Customer update(Customer customer) {
		Customer updatedCustomer = super.update(customer);
		return updatedCustomer;
	}
	
	@Override
	public Customer get(Object id) {
		Customer foundCustomer = super.find(Customer.class,id);
		return foundCustomer;
	}

	@Override
	public void delete(Object id) {
		super.delete(Customer.class, id);
		
	}

	@Override
	public List<Customer> listAll() {
		List<Customer> customers = super.findWithNamedQuery("Customer.listAll");
		
		return customers;
	}

	@Override
	public long count() {
		long customerCount = super.countAll("Customer.countAll");
		return customerCount;
	}
	
	public long countByOrder(Integer cusotmerId) {
		long customerByOrderCount = super.countWithNamedQueryAndParam("Customer.countByOrders", "customerId", cusotmerId);
		return customerByOrderCount;
	}
	
	public void close() {
		super.close();
	}

	public Customer findByEmail(String email) {
		return super.findWithNamedQueryAndParam("Customer.findByEmail", "email", email);
	}
	
	public boolean checkLogin(String email, String password) {
		Map<String, Object> params = new HashMap<>();
		String encryptedPassword = HashGenerator.generateMD5(password);
		params.put("email", email);
		params.put("password", encryptedPassword);
		
		Customer customer = super.findWithNamedQueryAndParam("Customer.checkLogin", params);
		
		if(customer != null) {
			System.out.println("user found with correct password and email" + encryptedPassword);
			return true;	
		}
		System.out.println("user not found with uncorrect password and email" + encryptedPassword);
		return false;
	}
	

}
