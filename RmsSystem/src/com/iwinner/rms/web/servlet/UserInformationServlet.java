package com.iwinner.rms.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.model.UserRole;
import com.iwinner.rms.service.RegisterServiceIF;

public class UserInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RegisterServiceIF registerServiceIF=null;
	public UserInformationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecute(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecute(request, response);
	}

	public void doExecute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String requestName = request.getRequestURI();
		String uri = requestName.substring(requestName.lastIndexOf("/") + 1,requestName.length());
		String userActionInfo=null;
		if (uri.equals(RMSConstants.NEW_USER_ACTION)) {
			request.setAttribute("userAction", "NEW");
			registerServiceIF=ServiceFactory.registerService();
			List<UserRole> listOfUserRoles=new ArrayList<UserRole>();
			try {
				listOfUserRoles=registerServiceIF.getUserRole();
				
			} catch (ServiceException e) {
			}
			request.getSession().setAttribute("userRolL", listOfUserRoles);
			request.getRequestDispatcher("/WEB-INF/jsp/userInfo.jsp").forward(request, response);
			
		}
		if (uri.equals(RMSConstants.SEARCH_ACTION)) {
			request.setAttribute("userAction", "SEARCH");
			request.getRequestDispatcher("/WEB-INF/jsp/userInfo.jsp").forward(
					request, response);
		}
		if (uri.equals(RMSConstants.VIEW_USERS_ACTION)) {
			request.setAttribute("userAction", "VIEW");
			request.getRequestDispatcher("/WEB-INF/jsp/userInfo.jsp").forward(
					request, response);
		}
		if (uri.equals(RMSConstants.DELETE_USER_ACTION)) {
			request.setAttribute("userAction", "DELETE");
			request.getRequestDispatcher("/WEB-INF/jsp/userInfo.jsp").forward(
					request, response);
		}
		if (uri.equals(RMSConstants.UPDATE_USER_ACTION)) {
			request.setAttribute("userAction", "UPDATE");
			request.getRequestDispatcher("/WEB-INF/jsp/userInfo.jsp").forward(
					request, response);
		}
	}

}
