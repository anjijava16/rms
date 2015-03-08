package com.iwinner.rms.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.dao.HomePageDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.utils.HibernateUtils;

public class HomePageDaoImpl implements HomePageDaoIF {
public List<String> getUsersInformation() throws DaoException {
	Session session = HibernateUtils.getSession();
	Query query=session.createQuery(RMSConstants.SELECT_USER_NAME_LIST);
	List<String> listOfUsers=(List<String>)query.list();
	return listOfUsers;
}
}
