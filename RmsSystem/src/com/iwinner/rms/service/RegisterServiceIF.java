package com.iwinner.rms.service;

import java.util.List;

import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.model.UserRole;
import com.iwinner.rms.model.Users;

public interface RegisterServiceIF {
	public Integer registerUser(Users user)throws ServiceException;
	public List<String> getUserNames()throws ServiceException;
	public List<UserRole> getUserRole()throws ServiceException;

}
