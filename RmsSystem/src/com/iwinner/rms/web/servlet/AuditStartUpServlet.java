package com.iwinner.rms.web.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.IRPCServiceFactory;
import com.iwinner.rms.helper.IdGenerator;
import com.iwinner.rms.model.Audit;
import com.iwinner.rms.service.AuditStartUpServiceIF;

/**
 * Servlet implementation class StartUpServlet
 */
public class AuditStartUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AuditStartUpServlet.class);

	public AuditStartUpServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Enter Into the StartUpServlet() # doGet()");
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Enter Into the StartUpServlet() # doPost()");
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Enter Into the StartUpServlet() # execute()");
		String savedVistorInfo = "";
		Hashtable<String, String> headerInfo = new Hashtable<String, String>();
		Enumeration<String> headEnum = (Enumeration<String>) request.getHeaderNames();
		while (headEnum.hasMoreElements()) {
			String keyName = headEnum.nextElement();
			String valueName = request.getHeader(keyName);
			headerInfo.put(keyName, valueName);
		}
		InetAddress inetAdd = null;
		try {
			inetAdd = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			
		}
		String hostAndIpAddress[] = inetAdd.toString().split("/");
		String hostName = hostAndIpAddress[0];
		String ipAddress = hostAndIpAddress[1];
		String userAgent = headerInfo.get("user-agent");
		String browserName = "";
		if (userAgent != null && userAgent.contains("Chrome")) {
			browserName = browserName + "Chrome";
		} else if (userAgent != null && userAgent.contains("MSIE")) {
			browserName = browserName + "MSIE";
		} else if (userAgent != null && userAgent.contains("Firefox")) {
			browserName = browserName + "Firefox";
		} else if (userAgent != null) {
			browserName = browserName + "Not Chrome/MSIE/FireFox";
		}
		String osName=System.getProperty("os.name");
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp viewTime = new java.sql.Timestamp(date.getTime());
		
		Audit vForm = new Audit();
		vForm.setHostname(hostName);
		vForm.setIpAddress(ipAddress);
		vForm.setBrowser(browserName);
		vForm.setUserAgent(userAgent);
		vForm.setViewDate(date);
		vForm.setVistorranId(IdGenerator.getId());
		vForm.setViewDateAndTime(viewTime);
		vForm.setOsName(osName);
		AuditStartUpServiceIF startUpServiceIF=IRPCServiceFactory.getStartUpService();
		try {
			startUpServiceIF.saveVistiorInfo(vForm);
		} catch (ServiceException e) {
			logger.error("##### Error Into the execute() "+e.getMessage());
		}

		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,	response);
	}
}
