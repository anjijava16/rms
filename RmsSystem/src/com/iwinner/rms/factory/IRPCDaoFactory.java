package com.iwinner.rms.factory;

import com.iwinner.rms.dao.AuditStartUpDaoIF;
import com.iwinner.rms.dao.AuditStartUpDaoImpl;

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
public class IRPCDaoFactory {
	private static AuditStartUpDaoIF startUpDaoIF = null;
	static {
		startUpDaoIF = new AuditStartUpDaoImpl();
	}

	public static AuditStartUpDaoIF getStartUpDao() {
		return startUpDaoIF;
	}
}
