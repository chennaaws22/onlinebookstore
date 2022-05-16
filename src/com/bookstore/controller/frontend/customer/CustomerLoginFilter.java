package com.bookstore.controller.frontend.customer;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
	private static final  String[] requiredUrls = {
			"update_profile", "view_profile", "write_review", "checkout","place_order"
			,"view_my_order", "view_my_order_detail"
	}; 
   
    public CustomerLoginFilter() {
    }


	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		boolean isLogedIn  = session != null && session.getAttribute("customerLoggedIn") != null;
		String currentPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		String requestURL = httpRequest.getRequestURL().toString();
		if(currentPath.startsWith("/admin/")) {
			System.out.println("path starts with admin");
			chain.doFilter(request, response);
			return;
		}
		
		if(!isLogedIn && isRequiredUrl(currentPath)) {
			System.out.println("Customer not logedIn");
			String queryString = httpRequest.getQueryString();
			if(queryString != null) {
				requestURL = requestURL.concat("?").concat(queryString);
			}
			httpRequest.getSession().setAttribute("redirectingURL", requestURL);
			String page = "frontend/login.jsp";
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			RequestDispatcher rd = httpRequest.getRequestDispatcher(page);
			rd.forward(request, response);
//			httpResponse.sendRedirect(page);
			
		}else {
			chain.doFilter(request, response);
		}
		
	}
	
	private boolean isRequiredUrl(String urlRequest) {
		for(String url : requiredUrls) {
			if(urlRequest.contains(url)) {
				return true;
			}
		}
		return false;
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
