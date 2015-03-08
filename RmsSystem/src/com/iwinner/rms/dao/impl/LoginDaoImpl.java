package com.iwinner.rms.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.dao.LoginDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.utils.HibernateUtils;

public class LoginDaoImpl implements LoginDaoIF {

	public Users getUserDetails(String username) throws DaoException {
    Users user=new Users();
    Session session=HibernateUtils.getSession();
    Query query=session.createQuery(RMSConstants.SELECT_USER_QUERY);
    query.setString("USERNAME",username);
    List<Users> listOfUser=query.list();
    for(Users userInfo:listOfUser){
    	try {
			BeanUtils.copyProperties(user,userInfo);
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
    }
    System.out.println(user.toString());
		return user;
	}
	public boolean loginVerify(String username,String password) throws DaoException {
		Session session=HibernateUtils.getSession();
		boolean loginVerifyValue=false;
		Query query=session.createQuery(RMSConstants.LOGIN_VERIFY_QUERY);
		query.setString("USERNAME", username);
		query.setString("PASSWORD", password);
		Long checkValue=(Long)query.uniqueResult();
		if(checkValue.intValue()==1){
			loginVerifyValue=true;
		}else{
			loginVerifyValue=false;
		}
		return loginVerifyValue;
	}
	
	public Integer lastLogin(String username)throws DaoException{
		Integer lastLogin=RMSConstants.LAST_LOGIN_PROCESSING;
		Session session=HibernateUtils.getSession();
		Users user=(Users)session.get(Users.class,username);
		user.setLastLogin(new Timestamp(new Date().getTime()));
		return lastLogin;
	}
	
	public String forgotPassword(String username,String password)throws DaoException{
		String FORGOT_PASSWORD=RMSConstants.FORGOT_PASSWORD_PROCESSING;
		Session session=HibernateUtils.getSession();
		session.beginTransaction().begin();
		Users user=(Users)session.get(Users.class,username);
		if(user!=null){
			user.setExpirationDate(new Date());
			user.setLastModifiedTime(new Timestamp(new Date().getTime()));
			user.setPastPasswords(user.getPastPasswords());
			user.setPassword(password);
			session.update(user);
			session.beginTransaction().commit();
			FORGOT_PASSWORD=RMSConstants.FORGOT_PASSWORD_CHANGED;
		}else{
			FORGOT_PASSWORD=RMSConstants.FORGOT_PASSWORD_INVALID_USER;
		}
		
		return FORGOT_PASSWORD;
	}
	
	public String resetPassword(String username,String password)throws DaoException{
		String RESET_PASSWORD=RMSConstants.RESET_PASSWORD_PROCESSING;
		Session session=HibernateUtils.getSession();
		session.beginTransaction().begin();
		Users user=(Users)session.get(Users.class,username);
		try{
			user.setExpirationDate(new Date());
			user.setLastModifiedTime(new Timestamp(new Date().getTime()));
			user.setPastPasswords(user.getPastPasswords());
			user.setPassword(password);
			session.update(user);
			session.beginTransaction().commit();
			RESET_PASSWORD=RMSConstants.RESET_PASSWORD_CHANGED;
		}catch(Exception sql){
			session.beginTransaction().rollback();
			RESET_PASSWORD=RMSConstants.RESET_PASSWORD_ERROR;
		}
		return RESET_PASSWORD;
	}
	public static boolean userNameVerify(String username) throws DaoException {
		Session session=HibernateUtils.getSession();
		boolean loginVerifyValue=false;
		Query query=session.createQuery(RMSConstants.SELECT_USER_NAME_CHECK);
		query.setString("USERNAME", username);
		Long checkValue=(Long)query.uniqueResult();
		if(checkValue.intValue()==1){
			loginVerifyValue=true;
		}else{
			loginVerifyValue=false;
		}
		return loginVerifyValue;
	}
	
	public static void main(String[] args) {
		LoginDaoImpl ld=new LoginDaoImpl();
		boolean bs;
		try {
			bs = ld.loginVerify("admin", "a1a3afa9fad72527c39ca8eca09f43");
			System.out.println(bs);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}
	
}
