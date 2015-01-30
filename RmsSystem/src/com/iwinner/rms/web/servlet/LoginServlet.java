package com.iwinner.rms.web.servlet;

import java.io.IOException;

import java.util.HashMap;
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
import com.iwinner.rms.service.ValidationServiceIF;
import com.iwinner.rms.utils.ValidateUserUtils;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ValidationServiceIF validateServiceIF;

	public LoginServlet() {
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

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put(RMSConstants.USERNAME, username);
		userInfo.put(RMSConstants.PASSWORD, password);
		boolean userValidation = ValidateUserUtils.validateUserIdAndPWD(userInfo);
		if (userValidation) {
			request.getSession().setAttribute("userName", username);
			request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request, response);
			
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

	public static void main(String[] args) {
		String username = "admin";
		String password = PasswordEncoder.encodePassword("admin");
		String userInfoMessage = "";
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

	}
}
