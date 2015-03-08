package com.iwinner.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.iwinner.rms.dao.HomePageDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.DaoFactory;
import com.iwinner.rms.service.HomePageServiceIF;

public class HomePageServiceImpl implements HomePageServiceIF {
	public static Logger logger = Logger.getLogger(HomePageServiceImpl.class);
    private HomePageDaoIF homePageDaoIF=null;

public List<String> getUsersInformation() throws ServiceException {
	homePageDaoIF=DaoFactory.getHomePageDao();
	List<String> listOfUsers=new ArrayList<String>();
	try {
		listOfUsers=homePageDaoIF.getUsersInformation();
	} catch (DaoException e) {
	}
	return listOfUsers;
}
}
