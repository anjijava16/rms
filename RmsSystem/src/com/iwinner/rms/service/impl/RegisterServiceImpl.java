package com.iwinner.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.iwinner.rms.dao.RegisterDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.DaoFactory;
import com.iwinner.rms.model.UserRole;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.service.RegisterServiceIF;

public class RegisterServiceImpl implements RegisterServiceIF {
	private static RegisterDaoIF registerDaoIF=null;
	public List<String> getUserNames() throws ServiceException {
		registerDaoIF=DaoFactory.getRegisterDao();
		List<String> listOfUserNames=new ArrayList<String>();
		try {
			listOfUserNames = registerDaoIF.getUserNames();
		} catch (DaoException e) {
		}
		return listOfUserNames;
	}

	public List<UserRole> getUserRole() throws ServiceException {
		List<UserRole> listOfRoles=new ArrayList<UserRole>();
		registerDaoIF=DaoFactory.getRegisterDao();
		try {
			listOfRoles=registerDaoIF.getUserRole();
		} catch (DaoException e) {
		}
		return listOfRoles;
	}

	public Integer registerUser(Users user) throws ServiceException {
		Integer registerUserCode=00;
		registerDaoIF=DaoFactory.getRegisterDao();
		try {
			registerUserCode=registerDaoIF.registerUser(user);
		} catch (DaoException e) {
			
		}
		return registerUserCode;
	}
}
