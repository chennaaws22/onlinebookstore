package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Category;

public class CategoryServices {
	private CategoryDao categoryDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		categoryDao = new CategoryDao();
	}
	
	
	public List<Category> listAll(){
		return categoryDao.listAll();
	}
	
	public void showCategoryTable() throws ServletException, IOException {
		List<Category> categories = this.listAll();
		
		request.setAttribute("categories", categories);
		
		String listPage = "../admin/category_list.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);

		requestDispatcher.forward(request, response);
	}
	
	
	public void showCategoryForm() throws ServletException, IOException {
		String createCategoryPage = "../admin/category_form.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(createCategoryPage);
		
		requestDispatcher.forward(request, response);
	}
	
	public void createCategory() throws ServletException, IOException {
		String categoryName = this.request.getParameter("categoryName");
		Category existingCategory = categoryDao.findByName(categoryName);
		
		if(existingCategory != null) {
			request.setAttribute("message", "Category already exists! choose another name.");
			this.showCategoryForm();
		} else {
			Category createdCategory = categoryDao.create(new Category(categoryName));
			this.request.setAttribute("message", "Category with name: " + createdCategory.getName() +" created successfully");
			this.showCategoryTable();
		}
		
	}
	
	public void showEditCategoryForm() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(this.request.getParameter("categoryId"));
		
		Category category = categoryDao.find(Category.class, categoryId);
		
		if(category != null) {
			request.setAttribute("category", category);
			this.showCategoryForm();
		} else {
			
			this.showErrorPage("there is no category with this Id");
		}
	}
	
	public void updateCategory() throws ServletException, IOException {
		Integer categroyId = Integer.parseInt(this.request.getParameter("categoryId"));
		String categoryName = this.request.getParameter("categoryName");
		
		Category categoryById = categoryDao.get(categroyId);
		Category categoryByName = categoryDao.findByName(categoryName);
		
		if(categoryByName != null && categoryByName.getCategoryId() != categoryById.getCategoryId()) {
			showErrorPage("this category Name already exist. try with different one");
		} else {
			categoryById.setName(categoryName);
			categoryDao.Update(categoryById);
			this.request.setAttribute("message", "Category with name: " + categoryById.getName() +" created successfully");
			this.showCategoryTable();
		}
	}
	
	public void showErrorPage(String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
		rd.forward(request, response);
		
	}

	public void deleteCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
		Category category = categoryDao.find(Category.class, categoryId);
		if(category != null) {
			categoryDao.delete(categoryId);
			this.request.setAttribute("message", "category deleted successfully");
			showCategoryTable();
		}else {
			showErrorPage("There is no category with this Id to delete");
		}
	}
	
	
}
