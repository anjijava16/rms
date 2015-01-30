package com.iwinner.rms.service;

import java.util.List;

import com.iwinner.rms.expections.ServiceException;

public interface HomePageServiceIF {
	public List<String> getUsersInformation() throws ServiceException;
}
