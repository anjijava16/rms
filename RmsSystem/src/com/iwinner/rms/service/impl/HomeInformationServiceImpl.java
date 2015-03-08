package com.iwinner.rms.service.impl;

import org.apache.log4j.Logger;

import com.iwinner.rms.dao.HomeInformationDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.DaoFactory;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.service.HomeInformationServiceIF;

public class HomeInformationServiceImpl implements HomeInformationServiceIF {
	public static Logger logger = Logger.getLogger(HomeInformationServiceImpl.class);
    private HomeInformationDaoIF homeInforamDaoIF=null;
    
	public Users profieInformation(String username) throws ServiceException {
		logger.info("Enter Into the  profieInformation() ");
		homeInforamDaoIF=DaoFactory.getHomeInformation();
		Users users=new Users();
		try {
			users = homeInforamDaoIF.profieInformation(username);
		} catch (DaoException e) {
			e.printStackTrace();
			logger.error("Error into the  profieInformation()"+e.getMessage()+" "+e.toString());
		}
		logger.info("Exit Into the  profieInformation() ");
		return users;
	}
}
