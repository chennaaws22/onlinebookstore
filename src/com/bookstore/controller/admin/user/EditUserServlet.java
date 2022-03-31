package com.bookstore.controller.admin.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.admin.BaseServlet;
import com.bookstore.service.UsersServices;


@WebServlet("/admin/edit_user")
public class EditUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			UsersServices userServices = new UsersServices(entityManager, request, response);
			userServices.showEditUserFormWith(userId);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsersServices userServices = new UsersServices(entityManager, 
				request, response);
		userServices.editUser();
		userServices.showUsersListTable();
	}	

}
