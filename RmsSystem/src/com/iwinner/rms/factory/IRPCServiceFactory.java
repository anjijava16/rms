package com.iwinner.rms.factory;

import com.iwinner.rms.service.AuditStartUpServiceIF;
import com.iwinner.rms.service.AuditStartUpServiceImpl;

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
public class IRPCServiceFactory {
	private static AuditStartUpServiceIF startUpServiceIF = null;
	static {
		startUpServiceIF = new AuditStartUpServiceImpl();
	}

	public static AuditStartUpServiceIF getStartUpService() {
		return startUpServiceIF;
	}
}
