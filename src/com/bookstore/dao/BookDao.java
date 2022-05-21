package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Book;


public class BookDao extends JpaDao<Book> implements GenericDao<Book> {
		public BookDao() {
			super();
		
	}
	
	@Override 
	public Book create(Book b) {
		b.setLastUpdateTime(new Date());
		return super.create(b);
	}
	
	@Override
	public Book update(Book b) {
		b.setLastUpdateTime(new Date());
		return super.update(b);
	}
	
	@Override
	public Book get(Object id) {
		return super.find(Book.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Book.class, id);
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");
	}

	public Book findByTitle(String title) {
		return super.findWithNamedQueryAndParam("Book.findByTitle", "title", title);
	}
	
	public Book findByIsbn(String isbn) {
		return super.findWithNamedQueryAndParam("Book.findByIsbn", "isbn", isbn);
	}
	
	@Override
	public long count() {
		return super.countAll("Book.countAll");
	}
	
	public List<Book> findByCategory(int catId){
		return  super.findWithNamedQueryAndParamList("Book.findByCategory", "catId", catId);
	}
	
	public List<Book> search(String keyword){
		List<Book> books = super.findWithNamedQueryAndParamList("Book.search", "keyword", keyword);
		
		return books;
	}
	
	public List<Book> listNewBooks(){
		return super.findWithNamedQueryLimit("Book.getNewBooks", 4);
	}
	
	public List<Book> listBestSellingBooks(){
		return super.findWithNamedQueryLimit("Book.bestSelling", 4);
	}
	
	public List<Book> listMostFavoriteBooks(){
		return super.findWithNamedQueryLimit("Book.mostFavorite", 4);
	}
	
	public long countByCategory(int categoryId) {
		return super.countWithNamedQueryAndParam("Book.countByCategory", "catId", categoryId);
	}
	
}
