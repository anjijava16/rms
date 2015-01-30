package com.iwinner.rms.factory;

import com.iwinner.rms.dao.HomeInformationDaoIF;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.model.Users;
import com.iwinner.rms.service.AuditStartUpServiceIF;
import com.iwinner.rms.service.AuditStartUpServiceImpl;
import com.iwinner.rms.service.HomeInformationServiceIF;
import com.iwinner.rms.service.HomeInformationServiceImpl;
import com.iwinner.rms.service.HomePageServiceIF;
import com.iwinner.rms.service.HomePageServiceImpl;
import com.iwinner.rms.service.ItemServiceIF;
import com.iwinner.rms.service.ItemServiceImpl;
import com.iwinner.rms.service.SchedulerServiceIF;
import com.iwinner.rms.service.SchedulerServiceImpl;
import com.iwinner.rms.service.ValidationServiceIF;
import com.iwinner.rms.service.ValidationServiceImpl;

/**
 * The class IRPCServiceFactory explain application about it is communicating
 * with Action & DAO layers It is helper class
 * 
 * @author anjaiah@IWinner
 * @version 1.x
 * @see com.iwinner.irpc.factory.IRPCServiceFactory.
 * @since Nov 4th,2014
 * 
 */
public class ServiceFactory {
	private static AuditStartUpServiceIF startUpServiceIF = null;
	private static ValidationServiceIF validateServiceIF = null;
    private static SchedulerServiceIF schedulerServiceIF=null;
    private static ItemServiceIF itemServiceIF=null;
    private static HomeInformationServiceIF homeInformationServiceIF=null;
    private static HomePageServiceIF homePageServiceIF=null;
    
    
	static {
		startUpServiceIF = new AuditStartUpServiceImpl();
		validateServiceIF = new ValidationServiceImpl();
		schedulerServiceIF=new SchedulerServiceImpl();
		itemServiceIF=new ItemServiceImpl();
		homeInformationServiceIF=new HomeInformationServiceImpl();
		homePageServiceIF=new HomePageServiceImpl();
	}

	public static AuditStartUpServiceIF getStartUpService() {
		return startUpServiceIF;
	}

	public static ValidationServiceIF getValidateService() {
		return validateServiceIF;
	}
	public static SchedulerServiceIF getSchedulerServiceFactory() {
		return schedulerServiceIF;
	}
	public static ItemServiceIF getItemServiceFactory() {
		return itemServiceIF;
	}
	public static HomeInformationServiceIF getHomeInformation(){
		return homeInformationServiceIF;
	}
	public static HomePageServiceIF getHomePageServiceIF(){
		return homePageServiceIF;
	}
}
