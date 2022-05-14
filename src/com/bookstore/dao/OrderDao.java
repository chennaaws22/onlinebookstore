package com.bookstore.dao;

import java.util.Date;
import java.util.List;

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
	
	@Override
	public void close() {
		super.close();
	}

}
