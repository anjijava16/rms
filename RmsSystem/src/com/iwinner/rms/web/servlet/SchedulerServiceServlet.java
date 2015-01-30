package com.iwinner.rms.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iwinner.rms.scheduler.SchedulerTaskList;

/**
 * Servlet implementation class SchedulerServiceServlet
 */
public class SchedulerServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		
	public void init(ServletConfig config) throws ServletException {
		long interval = Long.parseLong(config.getInitParameter("interval")) * 60 * 1000;

		SchedulerTaskList action = new SchedulerTaskList();
		Timer timer = new Timer();
		timer.schedule(action, new Date(), interval);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request,response);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
