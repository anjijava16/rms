package com.iwinner.rms.service.impl;

import org.apache.log4j.Logger;

import com.iwinner.rms.dao.AuditStartUpDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.DaoFactory;
import com.iwinner.rms.model.Audit;
import com.iwinner.rms.service.AuditStartUpServiceIF;

public class AuditStartUpServiceImpl implements AuditStartUpServiceIF {
	public static Logger logger = Logger.getLogger(AuditStartUpServiceImpl.class);
	private AuditStartUpDaoIF startUpDaoIF = null;

	public void saveVistiorInfo(Audit vistor) throws ServiceException {
		logger.info("Enter Into the saveVistiorInfo()");
		startUpDaoIF = DaoFactory.getStartUpDao();
		try {
			startUpDaoIF.peristVistiorInfo(vistor);
		} catch (DaoException e) {
			logger.error("##### Error Into the saveVistiorInfo()"
					+ e.getMessage());
		}
		logger.info("Exit Into the saveVistiorInfo()");
	}
}
