package com.iwinner.rms.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
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
import com.iwinner.rms.utils.DateUtils;

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
		if (uri.equals(RMSConstants.ITEM_ADD)) {
			request.getRequestDispatcher("/WEB-INF/jsp/addItem.jsp").forward(request, response);
		}
		if (uri.equals(RMSConstants.INSERT_ITEM)) {
			String addItem = null;
			String itemName = request.getParameter("itemName");
			Float itemPrice = Float.parseFloat(request.getParameter("itemPrice"));
			String itemPersonName = request.getParameter("itemPersonName");
			String itemTakenPlace = request.getParameter("itemTakenPlace");
			String personsWith = request.getParameter("personsWith");
			String takenDate = request.getParameter("takenDate");
			String takenTime = request.getParameter("takenTime");
			String comments=request.getParameter("comments");
			// 10-Feb-14 31-Jan-2015 01:30:02

			ItemInfo itemInfo = new ItemInfo();
			String gettingDate = "10-JAN-15";
			Date date = new Date(takenDate);
			itemInfo.setDate(date);
			itemInfo.setItemName(itemName);
			itemInfo.setItemTakenPerson(itemPersonName);
			itemInfo.setPersonsWith(personsWith);
			itemInfo.setPrice(itemPrice);
			itemInfo.setPurchasePlace(itemTakenPlace);
			itemInfo.setTakenTime(DateUtils.getDateInTimestamp(takenTime));
			itemInfo.setTakingPlace(itemTakenPlace);
			itemInfo.setComments(comments);
			itemInfo.setUpdatedBy(itemPersonName);
			itemInfo.setUsername(request.getSession().getAttribute("userName")
					.toString());
			itemInfo.setUpdatedTime(new Timestamp(new Date().getTime()));
			itemServiceIF = ServiceFactory.getItemServiceFactory();

			try {
				addItem = itemServiceIF.addItem(itemInfo);
			} catch (ServiceException e) {
			}
			List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			try {
				listOfInfo = itemServiceIF.viewAllItems();
			} catch (ServiceException e) {
			}
			request.getSession().setAttribute("listOfItemsInfo", listOfInfo);
			request.getSession().setAttribute("addItem", addItem);
			request.getRequestDispatcher("/WEB-INF/jsp/viewItems.jsp").forward(
					request, response);
		}
		if (uri.equals(RMSConstants.ITEM_MODIFY_INSERTION)) {
			Integer updateItem = 0;
			String updateItemMessage = null;
			String itemName = request.getParameter("itemName");
			Float itemPrice = Float.parseFloat(request.getParameter("itemPrice"));
			String itemPersonName = request.getParameter("itemPersonName");
			String itemTakenPlace = request.getParameter("itemTakenPlace");
			String personsWith = request.getParameter("personsWith");
			String takenDate = request.getParameter("takenDate");
			String takenTime = request.getParameter("takenTime");
			String updatedBy=request.getParameter("updatedBy");
			Integer itemId=Integer.parseInt(request.getParameter("itemId"));
			String comments=request.getParameter("comments");
			// 10-Feb-14 31-Jan-2015 01:30:02

			ItemInfo itemInfo = new ItemInfo();
			Date date = new Date(takenDate);
			itemInfo.setItemId(itemId);
			itemInfo.setDate(date);
			itemInfo.setItemName(itemName);
			itemInfo.setItemTakenPerson(itemPersonName);
			itemInfo.setPersonsWith(personsWith);
			itemInfo.setPrice(itemPrice);
			itemInfo.setPurchasePlace(itemTakenPlace);
			itemInfo.setTakenTime(DateUtils.getDateInTimestamp(takenTime));
			itemInfo.setTakingPlace(itemTakenPlace);
			itemInfo.setUpdatedBy(updatedBy);
			itemInfo.setComments(comments);
			itemInfo.setUsername(request.getSession().getAttribute("userName")
					.toString());
			itemInfo.setUpdatedTime(new Timestamp(new Date().getTime()));
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			try {
				updateItem = itemServiceIF.updateItem(itemInfo);
				if (updateItem == RMSConstants.UPDATE_ITAM_SUCCESS) {
					updateItemMessage = RMSConstants.UPDATE_ITEM_SUCCESS_;
				} else if (updateItem == RMSConstants.UPDATE_ITAM_NOT_FOUND) {
					updateItemMessage = RMSConstants.UPDATE_ITEM_NOT_FOUND_;
				} else if (updateItem == RMSConstants.UPDATE_ITAM_FAILED) {
					updateItemMessage = RMSConstants.UPDATE_ITEM_FAILED_;
				}
			} catch (ServiceException e) {
			}

			List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			try {
				listOfInfo = itemServiceIF.viewAllItems();
			} catch (ServiceException e) {
			}
			request.getSession().setAttribute("listOfItemsInfo", listOfInfo);
			request.getSession().setAttribute("updateItemMessage", updateItemMessage);
			request.getRequestDispatcher("/WEB-INF/jsp/editItem.jsp").forward(request, response);
		}
		if (uri.equals(RMSConstants.ITEM_VIEW)) {
			List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			try {
				listOfInfo = itemServiceIF.viewAllItems();
			} catch (ServiceException e) {
			}
			request.getSession().setAttribute("listOfItemsInfo", listOfInfo);
			request.getRequestDispatcher("/WEB-INF/jsp/viewItems.jsp").forward(
					request, response);
		}
		if(uri.equals(RMSConstants.ITEM_DELETE_ID)){
			Integer itemId=Integer.parseInt(request.getParameter("itemId"));
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			String deleteMessage=null;
			try {
				itemId=itemServiceIF.deleteItemId(itemId);
				if(itemId==RMSConstants.DELETE_ITAM_SUCCESS){
					deleteMessage=RMSConstants.DELETE_USER_ITAM_SUCCESS_;
				}else if (itemId==RMSConstants.DELETE_USER_ITAM_NOT_FOUND){
					deleteMessage=RMSConstants.DELETE_USER_ITAM_NOT_FOUND_;
				} else if (itemId==RMSConstants.DELETE_USER_ITAM_FAILED){
					deleteMessage=RMSConstants.DELETE_USER_ITAM_FAILED_;
				}
			} catch (ServiceException e) {
			}
			List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			try {
				listOfInfo = itemServiceIF.viewAllItems();
			} catch (ServiceException e) {
			}
			request.getSession().setAttribute("listOfItemsInfo", listOfInfo);
			request.getRequestDispatcher("/WEB-INF/jsp/deleteItemId.jsp").forward(request, response);
	
		}
		if (uri.equals(RMSConstants.ITEM_ID_INFO)) {
			Integer itemId = Integer.parseInt(request.getParameter("itemId"));
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			ItemInfo itemInfo = new ItemInfo();
			try {
				itemInfo = itemServiceIF.viewItemInfo(itemId);
				request.getSession().setAttribute("itemInfoForID", itemInfo);
				request.getRequestDispatcher(
						"/WEB-INF/jsp/itemIdInforamtion.jsp").forward(request,
						response);
			} catch (ServiceException e) {

			}
		}
		if (uri.equals(RMSConstants.ITEM_EDIT)) {
			Integer itemId = Integer.parseInt(request.getParameter("itemId"));
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			ItemInfo itemInfo = new ItemInfo();
			try {
				itemInfo = itemServiceIF.viewItemInfo(itemId);
				request.getSession().setAttribute("itemInfo", itemInfo);
				request.getRequestDispatcher("/WEB-INF/jsp/modifyItemInfo.jsp")
						.forward(request, response);
			} catch (ServiceException e) {
			}
		}
		if (uri.equals(RMSConstants.ITEM_DELETE)) {

			List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			try {
				listOfInfo = itemServiceIF.viewAllItems();
			} catch (ServiceException e) {
			}
			request.getSession().setAttribute("listOfItemsInfo", listOfInfo);
			List<Integer> listOfIds = new ArrayList<Integer>();
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			try {
				listOfIds = itemServiceIF.getItemIds();
			} catch (ServiceException e) {

			}
			request.getSession().setAttribute("listOfIds", listOfIds);
			request.getRequestDispatcher("/WEB-INF/jsp/deleteItemId.jsp")
					.forward(request, response);
		}
		if (uri.equals(RMSConstants.ITEM_UPDATE)) {
			List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			try {
				listOfInfo = itemServiceIF.viewAllItems();
			} catch (ServiceException e) {
			}
			request.getSession().setAttribute("listOfItemsInfo", listOfInfo);
			request.getRequestDispatcher("/WEB-INF/jsp/editItem.jsp").forward(
					request, response);
		}
	}

	public static void main(String args[]) {

		String gettingDate = "10-JAN-15";
		Date date = new Date(gettingDate);
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
		// viewAllUserItems();

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
