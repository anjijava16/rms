package com.iwinner.rms.dao;

import java.util.List;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.UserRole;
import com.iwinner.rms.model.Users;

public interface RegisterDaoIF {

	public Integer registerUser(Users user)throws DaoException;
	public List<String> getUserNames()throws DaoException;
	public List<UserRole> getUserRole()throws DaoException;
	
}
