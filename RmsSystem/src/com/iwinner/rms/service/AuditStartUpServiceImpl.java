package com.iwinner.rms.service;

import org.apache.log4j.Logger;

import com.iwinner.rms.dao.AuditStartUpDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.IRPCDaoFactory;
import com.iwinner.rms.model.Audit;

public class AuditStartUpServiceImpl implements AuditStartUpServiceIF {
	public static Logger logger = Logger.getLogger(AuditStartUpServiceImpl.class);
	private AuditStartUpDaoIF startUpDaoIF = null;

	public void saveVistiorInfo(Audit vistor) throws ServiceException {
		logger.info("Enter Into the saveVistiorInfo()");
		startUpDaoIF = IRPCDaoFactory.getStartUpDao();
		try {
			startUpDaoIF.peristVistiorInfo(vistor);
		} catch (DaoException e) {
			logger.error("##### Error Into the saveVistiorInfo()"
					+ e.getMessage());
		}
		logger.info("Exit Into the saveVistiorInfo()");
	}
}
