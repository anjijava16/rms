package com.iwinner.rms.factory;

import com.iwinner.rms.dao.AuditStartUpDaoIF;
import com.iwinner.rms.dao.HomeInformationDaoIF;
import com.iwinner.rms.dao.HomePageDaoIF;
import com.iwinner.rms.dao.ItemDaoIF;
import com.iwinner.rms.dao.LoginDaoIF;
import com.iwinner.rms.dao.RegisterDaoIF;
import com.iwinner.rms.dao.SchedulerServiceDaoIF;
import com.iwinner.rms.dao.TaskInfoDaoIF;
import com.iwinner.rms.dao.impl.AuditStartUpDaoImpl;
import com.iwinner.rms.dao.impl.HomeInformationDaoImpl;
import com.iwinner.rms.dao.impl.HomePageDaoImpl;
import com.iwinner.rms.dao.impl.ItemDaoImpl;
import com.iwinner.rms.dao.impl.LoginDaoImpl;
import com.iwinner.rms.dao.impl.RegisterDaoImpl;
import com.iwinner.rms.dao.impl.SchedulerServiceDaoImpl;
import com.iwinner.rms.dao.impl.TaskInfoDaoImpl;
import com.iwinner.rms.dao.impl.UserItemDaoImpl;

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
	private static RegisterDaoIF registerDaoIF=null;
	private static TaskInfoDaoIF taskInfoDaoIF=null;
	static {
		startUpDaoIF = new AuditStartUpDaoImpl();
		loginDaoIF=new LoginDaoImpl();
		schedulerDaoIF=new SchedulerServiceDaoImpl();
		itemDaoIF=new ItemDaoImpl();
		userItemDaoIF=new UserItemDaoImpl();
		homeInformationDaoIF=new HomeInformationDaoImpl();
		homePageDaoIF=new HomePageDaoImpl();
		registerDaoIF=new RegisterDaoImpl();
		taskInfoDaoIF=new TaskInfoDaoImpl();
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
	public static RegisterDaoIF getRegisterDao(){
		return registerDaoIF;
	}
	public static TaskInfoDaoIF getTaskInfoDao(){
		return taskInfoDaoIF;
	}
}
