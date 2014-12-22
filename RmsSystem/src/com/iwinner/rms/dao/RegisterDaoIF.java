package com.iwinner.rms.dao;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Users;

public interface RegisterDaoIF {

	public Integer registerUser(Users user)throws DaoException;
	
}
