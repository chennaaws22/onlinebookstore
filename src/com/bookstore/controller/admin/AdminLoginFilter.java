package com.bookstore.controller.admin;

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


@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("-----------Filtering-----------");
		HttpServletRequest httpServletReq = (HttpServletRequest) request;
		HttpServletResponse httpServletRes = (HttpServletResponse) response;
		HttpSession httpSession = httpServletReq.getSession(false);
		
		boolean isLogedIn = httpSession != null && httpSession.getAttribute("userLogedIn") !=null;
		
		String contextPath = httpServletReq.getContextPath();
		String loginUri = contextPath + "/admin/login";
		boolean isLoginUri = httpServletReq.getRequestURI().equals(loginUri);
		boolean isLoginPage = httpServletReq.getRequestURI().endsWith("login_form.jsp");
		
		if(isLogedIn && (isLoginUri || isLoginPage)) {
			System.out.println("User Aleardy logedin redirecting to admin dashboard");
			httpServletRes.sendRedirect(contextPath + "/admin/");
//			RequestDispatcher rd = request.getRequestDispatcher("/admin/");
//			rd.forward(request, response);
		} else if(isLogedIn || isLoginUri) {
			System.out.println("----------- user loged in go to next filteration");
			chain.doFilter(request, response);
		} else {
			System.out.println("-----------User Not loged in redirecting to login page");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/login_form.jsp");
			rd.forward(request, response);
 		}
		

	}


	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
