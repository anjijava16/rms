package com.iwinner.rms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.dao.LoginDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.DaoFactory;
import com.iwinner.rms.form.Credentials;
import com.iwinner.rms.helper.IdGenerator;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.service.ValidationServiceIF;

public class ValidationServiceImpl implements ValidationServiceIF {

	public static Logger logger = Logger.getLogger(ValidationServiceImpl.class);
	private LoginDaoIF loginDaoIF = null;

	public Integer verifyLoginStatus(Credentials creds) throws ServiceException {
		Integer usernameAndPasswordVerification = RMSConstants.USERNAME_PASSWORD_VERIFICATION_PROCESSING;
		loginDaoIF = DaoFactory.getLoginDaoFactory();
		try {
			boolean userChecking = loginDaoIF.loginVerify(creds.getUsername(),
					creds.getPassword());
			if (userChecking == true) {
				usernameAndPasswordVerification = RMSConstants.USERNAMEANDPASSWORD_CORRECT;
			} else if (userChecking == false) {
				usernameAndPasswordVerification = RMSConstants.USERNAMEANDPASSWORD_INCORRECT;
			}
		} catch (DaoException e) {
		}
		return usernameAndPasswordVerification;
	}

	public Integer accountStataus(String username) throws ServiceException {
		Integer accountStatusProcessing = RMSConstants.ACCOUNT_STATUS_PROCESSING;
		loginDaoIF = DaoFactory.getLoginDaoFactory();
		try {
			Users user = loginDaoIF.getUserDetails(username);
            if(user!=null){
			if (user.getAccountStatus().equals(RMSConstants.ACCOUNT_ACTIVE)) {
				accountStatusProcessing = RMSConstants.ACCOUNT_ACTIVE_ID;
			} else if (user.getAccountStatus().equals(
					RMSConstants.ACCOUNT_IN_ACTIVE)) {
				accountStatusProcessing = RMSConstants.ACCOUNT_IN_ACTIVE_ID;
			} else if (user.getAccountStatus().equals(
					RMSConstants.ACCOUNT_DISABLE)) {
				accountStatusProcessing = RMSConstants.ACCOUNT_DISABLE_ID;
			}
            }else{
            	accountStatusProcessing = RMSConstants.ACCOUNT_DISABLE_ID;
            }
		} catch (DaoException e) {
		}
		return accountStatusProcessing;
	}

	public Integer checkPasswordExpireOrNot(String username)
			throws ServiceException {
		Integer passwordExpirationProcessing = RMSConstants.PASSWORD_VERFICATION_PROCESSING;
		loginDaoIF = DaoFactory.getLoginDaoFactory();
		try {
			Users user = loginDaoIF.getUserDetails(username);
			Date today = new Date();
			if (today.compareTo(user.getExpirationDate()) < 0) {
				passwordExpirationProcessing = RMSConstants.PASSWORD_NOT_EXPIRE;
				logger.debug("Today Date is Lesser than my Date");
			} else if (today.compareTo(user.getExpirationDate()) > 0) {
				passwordExpirationProcessing = RMSConstants.PASSWORD_EXPIRE;
				logger.debug("Today Date is Greater than my date");
			}
		} catch (DaoException e) {
		}

		return passwordExpirationProcessing;
	}

	public Integer userRole(String username) throws ServiceException {
		Integer userRoleProcessing = RMSConstants.ACCOUNT_STATUS_PROCESSING;
		loginDaoIF = DaoFactory.getLoginDaoFactory();

		try {
			Users user = loginDaoIF.getUserDetails(username);
			if (RMSConstants.ADMIN.equals(user.getRole())) {
				userRoleProcessing = RMSConstants.ADMIN_ID;
			} else if (RMSConstants.NORAM_USER.equals(user.getRole())) {
				userRoleProcessing = RMSConstants.NORAM_USER_ID;
			} else {
				userRoleProcessing = RMSConstants.IT_USER_ID;
			}
		} catch (DaoException e) {
		}
		System.out.println("USEROLE:::>>>>>" + userRoleProcessing);
		return userRoleProcessing;
	}

	public static void main(String[] args) {
		ValidationServiceImpl vS = new ValidationServiceImpl();
		try {
			Integer id = vS.checkPasswordExpireOrNot("admin");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String forgotPassword(String username) throws ServiceException {
		String FORGOT_PASSWORD = RMSConstants.FORGOT_PASSWORD_PROCESSING;
		String generatePassword = IdGenerator.randId();
		loginDaoIF = DaoFactory.getLoginDaoFactory();
		try {
			FORGOT_PASSWORD = loginDaoIF.forgotPassword(username,
					generatePassword);
		} catch (DaoException e) {
		}
		return FORGOT_PASSWORD;
	}

	public String resetPassword(String username, String password)
			throws ServiceException {
		String RESET_PASSWORD = RMSConstants.RESET_PASSWORD_PROCESSING;
		loginDaoIF = DaoFactory.getLoginDaoFactory();
		try {
			RESET_PASSWORD = loginDaoIF.forgotPassword(username, password);
		} catch (DaoException e) {
			RESET_PASSWORD = RMSConstants.RESET_PASSWORD_ERROR;
		}
		return RESET_PASSWORD;
	}
	public Map<Integer,String> checkUserCreds(Map<String, String> userInfo)throws ServiceException{
		
		Map<Integer,String> checkUserCreds=new HashMap<Integer,String>();
		ValidationServiceImpl vServiceImpl=new ValidationServiceImpl();
		
		String username=userInfo.get("username");
		String password=userInfo.get("password");
		Credentials creds=new Credentials(username,password);
		
		Integer checkUserCode=vServiceImpl.verifyLoginStatus(creds);
		Integer accountStataus=0;
		Integer userRole=0;
		if(checkUserCode==RMSConstants.USERNAMEANDPASSWORD_CORRECT){
			checkUserCreds.put(10,"Correct");	
		}else{
			checkUserCreds.put(10,"Invalid");
		}
		if(checkUserCode==RMSConstants.USERNAMEANDPASSWORD_CORRECT){
			accountStataus=	vServiceImpl.accountStataus(username);
			if(accountStataus==RMSConstants.ACCOUNT_ACTIVE_ID){
				checkUserCreds.put(20,"Account is active state");
			}else if(accountStataus==RMSConstants.ACCOUNT_IN_ACTIVE_ID){
				checkUserCreds.put(21,"Account is In active state");
			}else if(accountStataus==RMSConstants.ACCOUNT_DISABLE_ID){
				checkUserCreds.put(22,"Account is disable state");
			}
		}
		if(checkUserCode==RMSConstants.USERNAMEANDPASSWORD_CORRECT && accountStataus==RMSConstants.ACCOUNT_ACTIVE_ID){
			userRole=vServiceImpl.userRole(username);
			if(userRole==RMSConstants.ADMIN_ID){
				checkUserCreds.put(30,"ADMIN");
			}else if(userRole==RMSConstants.NORAM_USER_ID){
				checkUserCreds.put(31,"Normal");
			}else if(userRole==RMSConstants.IT_USER_ID){
				checkUserCreds.put(33,"IT");
			}
		}
		return checkUserCreds;
	}
}
