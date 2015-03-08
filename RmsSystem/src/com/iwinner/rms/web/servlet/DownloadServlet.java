package com.iwinner.rms.web.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.model.ItemInfo;
import com.iwinner.rms.service.ItemServiceIF;
import com.iwinner.rms.service.UserItemServiceIF;
import com.iwinner.rms.utils.ReportUtils;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ItemServiceIF itemServiceIF = null;
	private static UserItemServiceIF userItemServiceIF=null;
	public DownloadServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		String requestName = request.getRequestURI();
		String uri = requestName.substring(requestName.lastIndexOf("/") + 1,requestName.length());
		if(uri.equals(RMSConstants.DOWNLOAD_REPORT)){
		String downloadDirectory = RMSConstants.DOWNLOAD_SAMPLE_REPORT;
		try {
			// PrintWriter out=response.getWriter();
			response.setContentType("APPLICATION/OCTET-STREAM");
			 String fileName="Report.xls";
			ReportUtils rU = new ReportUtils();
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
			try {
				listOfInfo = itemServiceIF.viewAllItems();
			} catch (ServiceException e) {
			}
			response.setHeader("Content-Disposition", "attachment; filename=\""	+ fileName+ "\"");
			OutputStream out = response.getOutputStream();
			try {
				HSSFWorkbook wb = ReportUtils.buildExcel(
						RMSConstants.headersInformation, listOfInfo);
				wb.write(out);
			} catch (Exception e) {
			}
			out.flush();
			out.close();
			out.close();
		} catch (IOException e) {
		}
		}
		if(uri.equals(RMSConstants.INDIVAL_REPORT)){
			try {
				
				// PrintWriter out=response.getWriter();
				String username=request.getParameter("itemPersonName");
				response.setContentType("APPLICATION/OCTET-STREAM");
				 String fileName="Report.xls";
				userItemServiceIF=ServiceFactory.getUserItemInfo();
				List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
				try {
					listOfInfo = userItemServiceIF.viewAllUserItems(username);
				} catch (ServiceException e) {
				}
				response.setHeader("Content-Disposition", "attachment; filename=\""	+ fileName+ "\"");
				OutputStream out = response.getOutputStream();
				try {
					HSSFWorkbook wb = ReportUtils.buildExcel(
							RMSConstants.headersInformation, listOfInfo);
					wb.write(out);
				} catch (Exception e) {
				}
				out.flush();
				out.close();
				out.close();
			} catch (IOException e) {
				}
			}
		
	}
}
