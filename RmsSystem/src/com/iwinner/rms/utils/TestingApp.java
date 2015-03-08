package com.iwinner.rms.utils;

import java.util.List;

import com.iwinner.rms.dao.impl.HomePageDaoImpl;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.service.HomeInformationServiceIF;
import com.iwinner.rms.service.HomePageServiceIF;

public class TestingApp {
	  private  static HomeInformationServiceIF homeServideIF=null;
	  private static HomePageServiceIF homePageServiceIF=null;
/*	  public static void main(String[] args) {
		  homeServideIF=ServiceFactory.getHomeInformation();
		  Users users=null;
		  try {
			users=homeServideIF.profieInformation("dev");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(users.toString()); 
	}*/

	  public static void main(String[] args) {
			homePageServiceIF=ServiceFactory.getHomePageServiceIF();
			try {
				List<String> getUsersInformation=homePageServiceIF.getUsersInformation();
				for(String users:getUsersInformation){
					System.out.print(users);
				}
			} catch (ServiceException e) {
			}
		}
}
