package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.UsersDao;
import com.bookstore.entity.Users;

public class UsersServices {
	private  EntityManager entityManager;
	private static UsersDao usersDao ;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public UsersServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public UsersServices(EntityManager entityManager, 
			HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		
		usersDao = new UsersDao(this.entityManager);
		
	}
	
	public List<Users> listAll(){
		return usersDao.listAll();
	}
	
	public void showUsersListTable() throws ServletException, IOException {
		List<Users> users = this.listAll();
		
		
		request.setAttribute("users", users);
		
		String listPage = "../admin/users_list.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		
		
		requestDispatcher.forward(request, response);
	}
	
	public Users createUser (String email, String fullName, String password) {
		
		return usersDao.create(new Users(email, fullName, password));
	}
	
	public void createUser() throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		if(usersDao.findByEmail(email) != null) {
			request.setAttribute("message", "User Email " + email + " Already Exist!");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			
		}else {
			this.createUser(email, fullName, password);
		}
		
		
	}
	
	public void showEditUserFormWith(Integer userId) throws ServletException, IOException {
		Users user = usersDao.find(Users.class, userId);
			if(user != null) {
				RequestDispatcher rd = this.request.getRequestDispatcher("user_form.jsp");
				user.setPassword(null);
				request.setAttribute("user", user);
				rd.forward(this.request, this.response);
			}else {
				this.request.setAttribute("message", "User not found!");
				RequestDispatcher rd = this.request.getRequestDispatcher("/admin/message.jsp");
				rd.forward(this.request, this.response);
			}
			
		}
	
	public void editUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(this.request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");

		
		Users userById = usersDao.get(userId);
	
		Users userByEmail = usersDao.findByEmail(email);
		
		//check if there is another user with the same email
		if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Could not update user with this email!";
			redirectToWithMessage("/admin/message.jsp", message);
		}else {
			userById.setEmail(email);
			userById.setFullName(fullName);
		
			if(password != null && !password.isEmpty()) {
				String encryptedPassword = HashGenerator.generateMD5(password);
				userById.setPassword(encryptedPassword);
			}
			usersDao.update(userById);
			request.setAttribute("message", "user successfullty updated");
		}
		
	}
	
	public void deleteUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		
		
		if(userId == 1) {
			RequestDispatcher rd = this.request.getRequestDispatcher("/admin/message.jsp");
			String message = "The default admin user account cannot be deleted. " + userId;
			request.setAttribute("message", message);
			rd.forward(this.request, this.response);
		}else {
			if(usersDao.find(Users.class, userId) != null) {
				usersDao.delete(userId);
				request.setAttribute("message", "user with id= " + userId + " deleted successfully");
			} else {
				RequestDispatcher rd = this.request.getRequestDispatcher("/admin/message.jsp");
				String message = "there is no user with id " + userId;
				request.setAttribute("message", message);
				rd.forward(this.request, this.response);
			}
		}
		
	}
	
	public void showLoginForm() throws ServletException, IOException {
		redirectTo("/admin/login_form.jsp");
	}
	
	public void showLoginFormWithMessage(String message) throws ServletException, IOException {
		redirectToWithMessage("/admin/login_form.jsp", message);
	}

	public void loginUser() throws ServletException, IOException {
		String email = this.request.getParameter("email");
		String password = this.request.getParameter("password");
		
		boolean isUserLogedIn = usersDao.checkLogin(email, password);
		
		if(isUserLogedIn) {
			System.out.println("------user is loged correct");
			this.request.getSession().setAttribute("userLogedIn", email);
			
			redirectTo("/admin/");
		}else {
			System.out.println("------user not loged correct");
			
			redirectToWithMessage("/admin/login_form.jsp", "user not loged in");
		}
	}
		
	private void redirectTo(String pageName) throws ServletException, IOException {
		RequestDispatcher rd = this.request.getRequestDispatcher(pageName);
		rd.forward(this.request, this.response);
	}
	
	private void redirectToWithMessage(String pageName, String message) throws ServletException, IOException {
		RequestDispatcher rd = this.request.getRequestDispatcher(pageName);
		this.request.setAttribute("message", message);
		rd.forward(this.request, this.response);
	}
}
