package com.iwinner.rms.factory;

import com.iwinner.rms.dao.AuditStartUpDaoIF;
import com.iwinner.rms.dao.AuditStartUpDaoImpl;
import com.iwinner.rms.dao.LoginDaoIF;
import com.iwinner.rms.dao.LoginDaoImpl;

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
	static {
		startUpDaoIF = new AuditStartUpDaoImpl();
		loginDaoIF=new LoginDaoImpl();
	}

	public static AuditStartUpDaoIF getStartUpDao() {
		return startUpDaoIF;
	}
	public static LoginDaoIF getLoginDaoFactory() {
		return loginDaoIF;
	}
}
