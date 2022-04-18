package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import com.bookstore.entity.Customer;

public class CustomerDao extends JpaDao<Customer> implements GenericDao<Customer> {

	
	@Override
	public Customer create(Customer customer) {
		Date registerDate = new Date();
		customer.setRegisterDate(registerDate);
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
	
	public void close() {
		super.close();
	}

	public Customer findByEmail(String email) {
		return super.findWithNamedQueryAndParam("Customer.findByEmail", "email", email);
	}
	

}
