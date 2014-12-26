package com.iwinner.rms.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iwinner.rms.constants.RMSConstants;
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
	public boolean loginVerify(String username,String password) throws DaoException {
		Session session=HibernateUtils.getSession();
		boolean loginVerifyValue=false;
		Query query=session.createQuery(RMSConstants.LOGIN_VERIFY_QUERY);
		query.setString("USERNAME", username);
		query.setString("PASSWORD", password);
		Long checkValue=(Long)query.uniqueResult();
		System.out.println(checkValue);
		if(checkValue.intValue()==1){
			loginVerifyValue=true;
		}else{
			loginVerifyValue=false;
		}
		return loginVerifyValue;
	}
}
