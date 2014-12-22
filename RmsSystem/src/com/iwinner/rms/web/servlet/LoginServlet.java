package com.iwinner.rms.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request,response);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(username!=null&&username.trim().length()!=0&&password!=null&&username.trim().length()!=0){

			request.getSession().setAttribute("userName",username);
			request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request,	response);

		}
			else{
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,	response);
			}
	}
}
