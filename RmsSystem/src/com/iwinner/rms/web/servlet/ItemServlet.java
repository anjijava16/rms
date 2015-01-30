package com.iwinner.rms.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.model.ItemInfo;
import com.iwinner.rms.service.ItemServiceIF;

public class ItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static ItemServiceIF itemServiceIF = null;

	public ItemServlet() {
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

		String uri = requestName.substring(requestName.lastIndexOf("/") + 1,requestName.length());
		if(uri.equals(RMSConstants.ITEM_ADD)){
			request.getRequestDispatcher("/WEB-INF/jsp/addItem.jsp").forward(request,response);
		}
		ItemInfo itemInfo = new ItemInfo();
	    String gettingDate="10-JAN-15";
	    Date date=new Date(gettingDate);
    	itemInfo.setDate(new Date());
		itemInfo.setItemName("3 Carries");
		itemInfo.setItemTakenPerson("anji");
		itemInfo.setPersonsWith("ALL");
		itemInfo.setPrice(6f);
		itemInfo.setPurchasePlace("Ap Bhavan");
		itemInfo.setTakenTime(new Timestamp(new Date().getTime()));
		itemInfo.setTakingPlace("Ap Bhanva");
		itemInfo.setUpdatedBy("anji");
		itemInfo.setUsername("anji");
	
		
		System.out.println("RequestName is :::>>>>" + requestName
				+ " URI=====>>>>   " + uri);
		// httpReq.getRequestURI()
	}

	public static void main(String args[]) {

		 String gettingDate="10-JAN-15";
		    Date date=new Date(gettingDate);
		   System.out.println(date);
		itemServiceIF = ServiceFactory.getItemServiceFactory();
		String addItem = null;
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setDate(date);
		itemInfo.setItemName("3 Carries");
		itemInfo.setItemTakenPerson("delete");
		itemInfo.setPersonsWith("ALL");
		itemInfo.setPrice(6f);
		itemInfo.setPurchasePlace("Ap Bhavan");
		itemInfo.setTakenTime(new Timestamp(new Date().getTime()));
		itemInfo.setTakingPlace("Ap Bhanva");
		itemInfo.setUpdatedBy("delete");
		itemInfo.setUsername("anji");
		try {
			addItem = itemServiceIF.addItem(itemInfo);
		} catch (ServiceException e) {
		
		}
		System.out.println("AddItem info" + addItem);
		
		// getItemInfo();
		// viewItemInfo();
		// viewAllItems();
		// getIUsertemInfo();
		// deleteUserItemInfo();
		// viewUserItemInfo();
		//viewAllUserItems();

	}

	public static void deleteItemInfo() {
		itemServiceIF = ServiceFactory.getItemServiceFactory();
		int delete = 0;
		try {
			delete = itemServiceIF.deleteItemId(19);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(delete);
	}

	public static void viewItemInfo() {
		ItemInfo item = new ItemInfo();
		itemServiceIF = ServiceFactory.getItemServiceFactory();
		try {
			item = itemServiceIF.viewItemInfo(17);
			System.out.println(item.getItemName() + "  "
					+ item.getItemTakenPerson() + "  " + item.getPersonsWith()
					+ "  " + item.getTakenTime());
		} catch (ServiceException e) {
		}
	}

	public static void viewAllItems() {
		List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
		itemServiceIF = ServiceFactory.getItemServiceFactory();
		try {
			listOfInfo = itemServiceIF.viewAllItems();
			for (ItemInfo itemInfo : listOfInfo) {
				System.out.println(itemInfo.getItemName() + " "
						+ itemInfo.getItemTakenPerson() + "  "
						+ itemInfo.getTakenTime());
			}
		} catch (ServiceException e) {
		}
	}

	public static void getIUsertemInfo() {
		String addItem = RMSConstants.ADD_USER_ITEM_PROCESSING;
		itemServiceIF = ServiceFactory.getItemServiceFactory();
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setDate(new Date());
		itemInfo.setItemName("Bread");
		itemInfo.setItemTakenPerson("ramu");
		itemInfo.setPersonsWith("ans,KSR");
		itemInfo.setPrice(10.23f);
		itemInfo.setPurchasePlace("SpiMall");
		itemInfo.setTakenTime(new Timestamp(new Date().getTime()));
		itemInfo.setTakingPlace("roadNo2");
		itemInfo.setUpdatedBy("admin");

		itemInfo.setUsername("admin");
		try {
			addItem = itemServiceIF.addUserItem(itemInfo, "ramu");
		} catch (ServiceException e) {
		}

		System.out.println("AddItem info" + addItem);
	}

	public static void deleteUserItemInfo() {

		itemServiceIF = ServiceFactory.getItemServiceFactory();
		int delete = 0;
		try {
			delete = itemServiceIF.deleteUserItemId(19, "abcs");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(delete);
	}

	public static void viewUserItemInfo() {
		ItemInfo item = new ItemInfo();
		itemServiceIF = ServiceFactory.getItemServiceFactory();
		try {
			item = itemServiceIF.viewUserItemInfo(19, "ramu");
			System.out.println(item.getItemName() + "  "
					+ item.getItemTakenPerson() + "  " + item.getPersonsWith()
					+ "  " + item.getTakenTime());
		} catch (ServiceException e) {
		}
	}

	public static void viewAllUserItems() {
		List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
		itemServiceIF = ServiceFactory.getItemServiceFactory();
		try {
			listOfInfo = itemServiceIF.viewAllUserItems("ramu");
			for (ItemInfo itemInfo : listOfInfo) {
				System.out.println(itemInfo.getItemName() + " "
						+ itemInfo.getItemTakenPerson() + "  "
						+ itemInfo.getTakenTime());
			}
		} catch (ServiceException e) {
		}
	}
}
