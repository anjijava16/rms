package com.iwinner.rms.service;

import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.model.Users;

/**
 * 
 * It is HomeInformationServiceIF 
 * @author anjaiah@iwinner.com
 * @version 1.0
 *
 *
 */
public interface HomeInformationServiceIF {
	public Users profieInformation(String username)throws ServiceException;
}
