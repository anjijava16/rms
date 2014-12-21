package com.iwinner.rms.web.servlet;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * Application Lifecycle Listener implementation class LoggingInitiazer
 * 
 */
public class LoggingInitiazer implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public LoggingInitiazer() {
		// TODO Auto-generated constructor stub
	}

	public void contextInitialized(ServletContextEvent event) {
		  ServletContext context = event.getServletContext();
	        String log4jConfigFile = context.getInitParameter("log4j-config-location");
	        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
	        DOMConfigurator.configure(fullPath);
	}
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
