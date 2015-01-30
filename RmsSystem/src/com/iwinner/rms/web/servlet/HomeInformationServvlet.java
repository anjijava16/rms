package com.iwinner.rms.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.service.HomeInformationServiceIF;

/**
 * Servlet implementation class HomeInformationServvlet
 */
public class HomeInformationServvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HomeInformationServiceIF homeServideIF=null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeInformationServvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	execute(request,response);
	
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	execute(request,response);
	}
    /**
     * 
     * It is HomeInformationServelt it is returning the profile,Help,About information.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	homeServideIF=ServiceFactory.getHomeInformation();
    	String username="";
    	Users users=null;
    	try {
			users=homeServideIF.profieInformation(username);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    	
    	System.out.println(users.toString());
    }

}
