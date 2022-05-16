package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.BookOrder;

public class OrderDao extends JpaDao<BookOrder> implements GenericDao<BookOrder> {

	
	@Override
	public BookOrder create(BookOrder bookOrder) {
		bookOrder.setOrderDate(new Date());
		bookOrder.setShippingAddress("HomeDelivery");
		bookOrder.setStatus("processing");
		return super.create(bookOrder);
	}
	
	@Override
	public BookOrder update(BookOrder bookOrder) {
		
		return super.update(bookOrder);
	}
	
	
	@Override
	public BookOrder get(Object orderId) {
		return super.find(BookOrder.class, orderId);
	}

	@Override
	public void delete(Object orderId) {
		super.delete(BookOrder.class, orderId);
		
	}

	@Override
	public List<BookOrder> listAll() {
		return super.findWithNamedQuery("Order.listAll");
	}

	@Override
	public long count() {
		
		return super.countAll("Order.countAll");
	}
	
	
	public List<BookOrder> listByCustomer(Integer customerId){
		List<BookOrder> bookOrders = super.findWithNamedQueryAndParamList("Order.listByCustomer", "customerId", customerId);
		return bookOrders;
	}
	
	public BookOrder getByOrderAndCustomer(Integer orderId, Integer customerId){
		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		params.put("customerId", customerId);
		
		BookOrder bookOrder = super.findWithNamedQueryAndParam("Order.listByOrderAndCustomer", params);
			
		return bookOrder;
	}
	
	@Override
	public void close() {
		super.close();
	}

}
