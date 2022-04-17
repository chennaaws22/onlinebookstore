package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Category;

public class CategoryDaoTest {
	private static CategoryDao categoryDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDao = new CategoryDao();
	}	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDao.close();
	}
	
	
	
	@Test
	public void testCreateCategory() {
		Category category = new Category("Marketing");
		Category savedCategory = categoryDao.create(category);
		
		assertTrue(savedCategory != null && savedCategory.getCategoryId() > 0);
	}
	
	@Test
	public void testUpdateCategory() {
		Category category = new Category("Java Core");
		category.setCategoryId(12);
		Category updatedCategory = categoryDao.update(category);
		
		assertEquals(category.getName(), updatedCategory.getName());
	}
	
	@Test
	public void testGetCategory() {
		Category category = categoryDao.get(13);
		
		assertNotNull(category);
	}
	
	@Test
	public void testDeleteCategory() {
		Integer categoryId = 13;
		
		categoryDao.delete(categoryId);
		Category deletedCategory = categoryDao.get(categoryId);
		
		assertNull(deletedCategory);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNotExistCategory() {
		categoryDao.delete(13);
		
	}
	
	@Test
	public void testListAll() {
		List<Category> categories = categoryDao.listAll();
		categories.forEach(c -> System.out.println(c.getName()));
		assertTrue(categories.size() > 0);
		
	}
	
	@Test
	public void testFindByName() {
		Category category = categoryDao.findByName("Java Core");
		
		assertNotNull(category);
	}
	
	@Test
	public void testFindByNameNotFound() {
		Category category = categoryDao.findByName("Java Corefdfd");
		
		assertNull(category);
	}

	@Test
	public void testCount() {
		long categoryCount = categoryDao.count();
		assertTrue(categoryCount == 2);
	}
	
	
}
