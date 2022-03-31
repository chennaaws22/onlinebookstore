package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDao extends JpaDao<Category> implements GenericDao<Category> {

	public CategoryDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	
	public Category create(Category category) {
		return super.create(category);
	}

	public Category Update(Category category) {
		return super.update(category);
	}
	
	
	@Override
	public Category get(Object id) {
		return super.find(Category.class, id);
	}


	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);
	}


	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}

	public Category findByName(String name) {
		return super.findWithNamedQueryAndParam("Category.findByName", "name", name);
	}

	@Override
	public long count() {
		return super.countAll("Category.countAll");
		
	}
	


	
	
}
