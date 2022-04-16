package com.bookstore.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectingServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public RedirectingServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public void redirectTo(String page) throws ServletException, IOException {
		request.getRequestDispatcher(page).forward(this.request, this.response);
	}
	
	public void redirectToWithMessage(String page, String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher(page).forward(this.request, this.response);
	}
	
	public void showErrorPage(String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		redirectTo("message.jsp");
		
	}
	
	
}
