package com.iwinner.rms.dao;

import java.util.List;

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
    List<Users> listOfUser=query.list();
    for(Users user1:listOfUser){
    	
    }
		return user;
	}

	public boolean loginVerify() throws DaoException {
		
		return false;
	}
}
