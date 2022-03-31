package com.bookstore.controller.admin.user;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.admin.BaseServlet;
import com.bookstore.entity.Users;
import com.bookstore.service.UsersServices;


@WebServlet("/admin/create_user")
public class CreateUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	  public CreateUserServlet() {
	        super();
	        
	        
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		UsersServices usersServices = new UsersServices(entityManager,
				request, response);
		usersServices.createUser();
		request.setAttribute("message", "New User Created Successfully");
		usersServices.showUsersListTable();
}

}