package com.iwinner.rms.service;

import com.iwinner.rms.expections.ServiceException;

import com.iwinner.rms.form.Credentials;

public interface ValidationServiceIF {
public Integer accountStataus(String username)throws ServiceException;
public Integer verifyLoginStatus(Credentials creds)throws ServiceException;
public Integer checkPasswordExpireOrNot(String username)throws ServiceException;
public Integer userRole(String username)throws ServiceException;
public String forgotPassword(String username)throws ServiceException;
public String resetPassword(String username,String password)throws ServiceException;
}
