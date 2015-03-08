package com.iwinner.rms.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.helper.IdGenerator;
import com.iwinner.rms.helper.PasswordEncoder;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.service.RegisterServiceIF;
import com.iwinner.rms.utils.DateUtils;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static RegisterServiceIF registerServiceIF=null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	execute(request,response);
	}
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	execute(request,response);
	}

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestName = request.getRequestURI();
		String uri = requestName.substring(requestName.lastIndexOf("/") + 1,requestName.length());
       if(uri.equals(RMSConstants.FIND_USER_ACTION)){
    	   String username=request.getParameter("username");
    	   String userChecking=RMSConstants.REGISTER_PROCESSING;
    	   registerServiceIF=ServiceFactory.registerService();
    	   try {
			List<String> listOfUsers=registerServiceIF.getUserNames();
			for(String checkingMessage:listOfUsers){
				if(checkingMessage.equals(username)){
					userChecking="User Avaiable,Please try another name";
				}else{
					userChecking="User Not Avaiable,Use this user";
				}
			}
            
    	   } catch (ServiceException e) {
		}
    	   response.getWriter().write(userChecking);     	   
       }//NEW_USER_REGISTRATION
       if(uri.equals(RMSConstants.NEW_USER_REGISTRATION)){
    	   registerServiceIF=ServiceFactory.registerService();
        String username=request.getParameter("username");
    	String   email=request.getParameter("email");
    	String   fullName=request.getParameter("fullName");
    	String phone=request.getParameter("phone");
    	String password=request.getParameter("password");
    	String role=request.getParameter("userRole");
   		Users users = new Users();
   		users.setAccountStatus(RMSConstants.ACCOUNT_ACTIVE);
   		users.setConsecutiveLoginFailures(RMSConstants.CONSUECTIVE_LOGINFAILURES);
   		users.setEmail(email);
   		users.setExpirationDate(DateUtils.expireDate());
   		users.setExpirePassword(RMSConstants.PASSWORD_EXPIRE_TIMES);
   		users.setFullName(fullName);
   		users.setLastLogin(new Timestamp(new Date().getTime()));
   		users.setLastModifiedTime(new Timestamp(new Date().getTime()));
   		users.setLastPasswordChangedDate(new Date());
   		users.setLastUpdatedBy(username);
   		users.setPastPasswords(PasswordEncoder.encodePassword(password));
   		users.setPassword(PasswordEncoder.encodePassword(password));
   		users.setPhone(phone);
   		users.setRole(role);
   		users.setUserComments(RMSConstants.ACCOUNT_CREATION_COMMENTS);
   		users.setUserId(Integer.parseInt(IdGenerator.randId()));
   		users.setUsername(username);
   		Integer registerCodeMessage=0;
   		try {
			registerCodeMessage=registerServiceIF.registerUser(users);
		} catch (ServiceException e) {
			
		}
   		request.getSession().setAttribute("registerCodeMessage", registerCodeMessage);
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
       }
    }
}
