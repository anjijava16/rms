package com.iwinner.rms.factory;

import com.iwinner.rms.dao.AuditStartUpDaoIF;
import com.iwinner.rms.dao.AuditStartUpDaoImpl;
import com.iwinner.rms.dao.HomeInformationDaoIF;
import com.iwinner.rms.dao.HomeInformationDaoImpl;
import com.iwinner.rms.dao.HomePageDaoIF;
import com.iwinner.rms.dao.HomePageDaoImpl;
import com.iwinner.rms.dao.ItemDaoIF;
import com.iwinner.rms.dao.ItemDaoImpl;
import com.iwinner.rms.dao.LoginDaoIF;
import com.iwinner.rms.dao.LoginDaoImpl;
import com.iwinner.rms.dao.SchedulerServiceDaoIF;
import com.iwinner.rms.dao.SchedulerServiceDaoImpl;
import com.iwinner.rms.dao.UserItemDaoImpl;

/**
 * The class IRPCDaoFactory explain application about it is communicating with
 * Service & DAO layers It is helper class
 * 
 * @author anajaiah@IWinner
 * @version 1.x
 * @see com.iwinner.irpc.factory.IRPCDaoFactory.
 * @since Nov 4th,2014
 * 
 */
public class DaoFactory {
	private static AuditStartUpDaoIF startUpDaoIF = null;
	private static LoginDaoIF loginDaoIF = null;
	private static SchedulerServiceDaoIF schedulerDaoIF=null;
	private static ItemDaoIF  itemDaoIF=null;
	private static UserItemDaoImpl userItemDaoIF=null;
	private static HomeInformationDaoIF homeInformationDaoIF=null;
	private static HomePageDaoIF homePageDaoIF=null;
	
	static {
		startUpDaoIF = new AuditStartUpDaoImpl();
		loginDaoIF=new LoginDaoImpl();
		schedulerDaoIF=new SchedulerServiceDaoImpl();
		itemDaoIF=new ItemDaoImpl();
		userItemDaoIF=new UserItemDaoImpl();
		homeInformationDaoIF=new HomeInformationDaoImpl();
		homePageDaoIF=new HomePageDaoImpl();
	}

	public static AuditStartUpDaoIF getStartUpDao() {
		return startUpDaoIF;
	}
	public static LoginDaoIF getLoginDaoFactory() {
		return loginDaoIF;
	}
	public static SchedulerServiceDaoIF getSchedulerFactory() {
		return schedulerDaoIF;
	}
	
	public static ItemDaoIF getItemFactory() {
		return itemDaoIF;
	}
	public static UserItemDaoImpl getUserItemFactory() {
		return userItemDaoIF;
	}
	public static HomeInformationDaoIF getHomeInformation(){
		return homeInformationDaoIF;
	}
	public static HomePageDaoIF getHomePageDao(){
		return homePageDaoIF;
	}
}
