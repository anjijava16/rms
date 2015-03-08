package com.iwinner.rms.web.servlet;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.form.Credentials;
import com.iwinner.rms.helper.PasswordEncoder;
import com.iwinner.rms.model.ItemInfo;
import com.iwinner.rms.model.UserRole;
import com.iwinner.rms.service.HomePageServiceIF;
import com.iwinner.rms.service.ItemServiceIF;
import com.iwinner.rms.service.RegisterServiceIF;
import com.iwinner.rms.service.ValidationServiceIF;
import com.iwinner.rms.utils.ValidateUserUtils;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ValidationServiceIF validateServiceIF;
	private static HomePageServiceIF homePageServiceIF=null;
	private static ItemServiceIF itemServiceIF = null;
	private static RegisterServiceIF registerServiceIF=null;
	public LoginServlet() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecute(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecute(request, response);
	}

	public void doExecute(HttpServletRequest request,HttpServletResponse response)throws  ServletException, IOException {
		String userInfoMessage = "";
		
		String username = request.getParameter("username");
		String password = PasswordEncoder.encodePassword(request.getParameter("password"));
		String buttonName = request.getParameter("rmsPortal");
		
		
		Map<String, String> userInfo = new HashMap<String, String>();
		Credentials credentials = new Credentials(username,password);
		validateServiceIF = ServiceFactory.getValidateService();
		if(buttonName.equals(RMSConstants.LOGIN_NAVIGATION)){
		try {
			Integer validateUserAndPWD = validateServiceIF.verifyLoginStatus(credentials);
			if (validateUserAndPWD == RMSConstants.USERNAMEANDPASSWORD_CORRECT) {
				validateUserAndPWD = validateServiceIF.accountStataus(username);
				if (validateUserAndPWD == RMSConstants.ACCOUNT_ACTIVE_ID) {
					validateUserAndPWD = validateServiceIF.checkPasswordExpireOrNot(username);
					if (validateUserAndPWD == RMSConstants.PASSWORD_NOT_EXPIRE) {
						validateUserAndPWD = validateServiceIF.userRole(username);
						if (validateUserAndPWD == RMSConstants.ADMIN_ID) {
							request.getSession().setAttribute("userName", username);
							List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
							itemServiceIF = ServiceFactory.getItemServiceFactory();
							try {
								listOfInfo = itemServiceIF.viewAllItems();
							} catch (ServiceException e) {
							}
							request.getSession().setAttribute("userRole", RMSConstants.ADMIN);
					        request.getSession().setAttribute("listOfItemsInfo", listOfInfo);		
							request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request, response);
							// Get HomePage Information 
							homePageServiceIF=ServiceFactory.getHomePageServiceIF();
							try {
								List<String> getUsersInformation=homePageServiceIF.getUsersInformation();
								request.getSession().setAttribute("userList", getUsersInformation);
							} catch (ServiceException e) {
							}
						} else if (validateUserAndPWD == RMSConstants.NORAM_USER_ID) {
							request.getSession().setAttribute("userName", username);
							List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
							itemServiceIF = ServiceFactory.getItemServiceFactory();
							try {
								listOfInfo = itemServiceIF.viewAllItems();
							} catch (ServiceException e) {
							}
							request.getSession().setAttribute("userRole", RMSConstants.NORAM_USER);
							request.getSession().setAttribute("listOfItemsInfo", listOfInfo);		
							request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request, response);
							
							// Get HomePage Information 
							homePageServiceIF=ServiceFactory.getHomePageServiceIF();
							try {
								List<String> getUsersInformation=homePageServiceIF.getUsersInformation();
								request.getSession().setAttribute("userList", getUsersInformation);
							} catch (ServiceException e) {
							}
						} else if (validateUserAndPWD == RMSConstants.IT_USER_ID) {
							request.getSession().setAttribute("userName", username);
							List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
							itemServiceIF = ServiceFactory.getItemServiceFactory();
							try {
								listOfInfo = itemServiceIF.viewAllItems();
							} catch (ServiceException e) {
							}
							//IT_USER
							request.getSession().setAttribute("userRole", RMSConstants.IT_USER);
					        request.getSession().setAttribute("listOfItemsInfo", listOfInfo);		
							request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request, response);
							
							// Get HomePage Information 
							homePageServiceIF=ServiceFactory.getHomePageServiceIF();
							try {
								List<String> getUsersInformation=homePageServiceIF.getUsersInformation();
								request.getSession().setAttribute("userList", getUsersInformation);
							} catch (ServiceException e) {
							}
						}
					} else if (validateUserAndPWD == RMSConstants.PASSWORD_EXPIRE) {
						request.setAttribute("errorMessage", RMSConstants.PASSWORD_EXPIRE_MESSAGE);
						request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
					}

				} else if (validateUserAndPWD == RMSConstants.ACCOUNT_IN_ACTIVE_ID) {
					request.setAttribute("errorMessage", RMSConstants.PASSWORD_IN_ACTIVE);
					request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				} else {

				}
			} else {
				request.setAttribute("errorMessage", RMSConstants.IN_VALID_CREDS);
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			}

		} catch (ServiceException e) {
		}
		} 
		if(buttonName.equals(RMSConstants.FORGOT_PASSWORD)){
			request.getRequestDispatcher("/WEB-INF/jsp/forgotPassword.jsp").forward(request, response);
		}

		if(buttonName.equals(RMSConstants.NEW_REGISTRATION)){
			registerServiceIF=ServiceFactory.registerService();
			List<UserRole> listOfUserRoles=new ArrayList<UserRole>();
			try {
				listOfUserRoles=registerServiceIF.getUserRole();
				
			} catch (ServiceException e) {
			}
			request.getSession().setAttribute("userRolL", listOfUserRoles);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			return;
		}

	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String buttonName = request.getParameter("rmsPortal");
		Map<String, String> userInfo = new HashMap<String, String>();
		if(buttonName.equals(RMSConstants.LOGIN_NAVIGATION)){
		userInfo.put(RMSConstants.USERNAME, username);
		userInfo.put(RMSConstants.PASSWORD, password);
		boolean userValidation = ValidateUserUtils.validateUserIdAndPWD(userInfo);
		if (userValidation) {
			request.getSession().setAttribute("userName", username);
			List<ItemInfo> listOfInfo = new ArrayList<ItemInfo>();
			itemServiceIF = ServiceFactory.getItemServiceFactory();
			try {
				listOfInfo = itemServiceIF.viewAllItems();
			} catch (ServiceException e) {
			}
	        request.getSession().setAttribute("listOfItemsInfo", listOfInfo);		

			request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request, response);
			
			// Get HomePage Information 
			homePageServiceIF=ServiceFactory.getHomePageServiceIF();
			try {
				List<String> getUsersInformation=homePageServiceIF.getUsersInformation();
				request.getSession().setAttribute("userList", getUsersInformation);
			} catch (ServiceException e) {
			}
			
		} else {
			String validateUserNamePasswordMessage = "";

			validateUserNamePasswordMessage = ValidateUserUtils.validateUser(username);
			if (validateUserNamePasswordMessage != RMSConstants.EMPTY_MESSAGE) {
				request.setAttribute("usernamepasswordVerfication",	validateUserNamePasswordMessage);
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return;
			}
			validateUserNamePasswordMessage = ValidateUserUtils.validatePassword(username);
			if (validateUserNamePasswordMessage != RMSConstants.EMPTY_MESSAGE) {
				request.setAttribute("usernamepasswordVerfication",validateUserNamePasswordMessage);
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			}
		}
		}
		if(buttonName.equals(RMSConstants.NEW_REGISTRATION)){
			registerServiceIF=ServiceFactory.registerService();
			List<UserRole> listOfUserRoles=new ArrayList<UserRole>();
			try {
				listOfUserRoles=registerServiceIF.getUserRole();
				
			} catch (ServiceException e) {
			}
			System.out.println("TEsting");
			System.out.println(listOfUserRoles);
			for(UserRole us:listOfUserRoles){
				System.out.println(us);
			}
			request.getSession().setAttribute("userRolL", listOfUserRoles);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
		}
	
		
	}

	public static void main(String[] args) {
		String username = "admin";
		String password = PasswordEncoder.encodePassword("admin");
		validateServiceIF = ServiceFactory.getValidateService();
		Map<Integer,String> userMap=new HashMap<Integer,String>();
		Map<String,String> userInfo=new HashMap<String,String>();
		userInfo.put("username", "anji");
		userInfo.put("password", PasswordEncoder.encodePassword("anji"));
		try {
			userMap=validateServiceIF.checkUserCreds(userInfo);
		} catch (ServiceException e) {
		}
		System.out.println(userMap);
		
		
		/*String userInfoMessage = "";
		Credentials credentials = new Credentials(username, password);
		validateServiceIF = ServiceFactory.getValidateService();
		try {
			Integer validateUserAndPWD = validateServiceIF.verifyLoginStatus(credentials);
			if (validateUserAndPWD == RMSConstants.USERNAMEANDPASSWORD_CORRECT) {
				validateUserAndPWD = validateServiceIF.accountStataus(username);
				if (validateUserAndPWD == RMSConstants.ACCOUNT_ACTIVE_ID) {
					validateUserAndPWD = validateServiceIF
							.checkPasswordExpireOrNot(username);
					if (validateUserAndPWD == RMSConstants.PASSWORD_NOT_EXPIRE) {
						validateUserAndPWD = validateServiceIF
								.userRole(username);
						if (validateUserAndPWD == RMSConstants.ADMIN_ID) {
							System.out.println("ADMIN");
						} else if (validateUserAndPWD == RMSConstants.NORAM_USER_ID) {
							System.out.println("NoramUserID");
						} else if (validateUserAndPWD == RMSConstants.IT_USER_ID) {
							System.out.println("ITADMIN");
						}
					} else if (validateUserAndPWD == RMSConstants.PASSWORD_EXPIRE) {
					}

				} else if (validateUserAndPWD == RMSConstants.ACCOUNT_IN_ACTIVE_ID) {
					// in active ID
				} else {

				}
			} else {
				// Invalid Creds
			}
			System.out.println("Result" + validateUserAndPWD);

		} catch (ServiceException e) {
		}
*/
		
		
	}}
