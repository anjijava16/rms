package com.iwinner.rms.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.model.TaskPrioritys;
import com.iwinner.rms.model.TaskType;
import com.iwinner.rms.service.HomePageServiceIF;
import com.iwinner.rms.service.TaskServiceIF;

/**
 * Servlet implementation class NavigationServlet
 */
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HomePageServiceIF homePageServiceIF = null;
    private static TaskServiceIF taskServiceIF=null;
   public static Logger LOGGER=Logger.getLogger(NavigationServlet.class);
    /**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestName = request.getRequestURI();
		String uri = requestName.substring(requestName.lastIndexOf("/") + 1,
				requestName.length());

		if (uri.equals(RMSConstants.REPORT_NAVIGATION)) {
			System.out.println("TESt");
			request.getRequestDispatcher("/WEB-INF/jsp/reportsys.jsp").forward(
					request, response);
		}
		if (uri.equals(RMSConstants.SINGLE_REPORT_NAVIGATION)) {
			// Get HomePage Information
			homePageServiceIF = ServiceFactory.getHomePageServiceIF();
			try {
				List<String> getUsersInformation = homePageServiceIF
						.getUsersInformation();
				request.getSession().setAttribute("userList",
						getUsersInformation);
			} catch (ServiceException e) {
			}
			request.getRequestDispatcher("/WEB-INF/jsp/singleReport.jsp")
					.forward(request, response);
		}
		if (uri.equals(RMSConstants.LOGOUT_ACTION)) {
			request.getSession().removeAttribute("userName");
			request.getSession().invalidate();
			System.out.println("From LogOut");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(
					request, response);
		}  //underOOPS.jsp
		
		if (uri.equals(RMSConstants.UNDER_DEV)) {
			request.getRequestDispatcher("/WEB-INF/jsp/underOOPS.jsp").forward(
					request, response);
		}

	// Navigation TaskInfo Servlet Information 
		if (uri.equals(RMSConstants.ADD_TASK_NAVIGATION)) {
			Integer taskId=RMSConstants.TASK_ID_PROCESSING;
			try{
				// Setting Task Prioritys Values 
				TaskPrioritys taskPrioritys[]=TaskPrioritys.values();
			       List<String> listOF=new ArrayList<String>();
			        for(TaskPrioritys tPS:taskPrioritys){
			        	listOF.add(tPS.getPriority());
			        }
			        LOGGER.debug("***** TaskPrioritys Values [="+listOF+"]"+"***** ");
			        TaskType tType[]=TaskType.values();
			        List<String> listOfTaskType=new ArrayList<String>();
			        for(TaskType type:tType){
			        	listOfTaskType.add(type.getMessage());
			        }
			        LOGGER.debug("***** TaskType Values [="+listOfTaskType+"]"+"***** ");
				taskServiceIF=ServiceFactory.getTaskService();
				 taskId=taskServiceIF.getTaskId()+1;

				 
				 
				 request.setAttribute("taskId", taskId+1);	 
			}catch(Exception e){
			}
				request.getRequestDispatcher("/WEB-INF/jsp/addTaskInfo.jsp").forward(	request, response);
		}
	//http://iwinner.com:2626/RmsSystem/login.do
		
		//mahesh.dhavala@valuelabs.net
	
	}
}
