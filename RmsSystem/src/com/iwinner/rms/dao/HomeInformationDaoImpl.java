package com.iwinner.rms.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.utils.HibernateUtils;

public class HomeInformationDaoImpl implements HomeInformationDaoIF {
	public static Logger logger = Logger.getLogger(HomeInformationDaoImpl.class);
	public Users profieInformation(String username) throws DaoException {
		logger.info("Enter Into the profieInformation() ");
		Users users = new Users();
		Session session = HibernateUtils.getSession();
		Query query=session.createQuery(RMSConstants.SELECT_USER_PRTOFILE);
		query.setString("username", username);
		List<Users> listOfUsers=(List<Users>)query.list();
		for(Users usersInfo:listOfUsers){
			try {
				BeanUtils.copyProperties(users,usersInfo);
			} catch (IllegalAccessException e) {
				logger.info("Error  Into the profieInformation() "+e.getMessage());
			} catch (InvocationTargetException e) {
				logger.info("Error  Into the profieInformation() "+e.getMessage());
			}
		}
		logger.info("Exit Into the profieInformation() ");
		return users;
	}
	public List<String> getUsersInformation() throws DaoException {
		Session session = HibernateUtils.getSession();
		return null;
	}
}
