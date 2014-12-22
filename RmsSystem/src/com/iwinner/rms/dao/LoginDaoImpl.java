package com.iwinner.rms.dao;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Users;

public class LoginDaoImpl implements LoginDaoIF {
	public Users getUserDetails() throws DaoException {
    Users user=new Users();
    
		return user;
	}

	public boolean loginVerify() throws DaoException {
		
		return false;
	}
}
