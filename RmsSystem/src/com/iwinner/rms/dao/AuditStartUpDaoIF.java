package com.iwinner.rms.dao;

import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.Audit;

public interface AuditStartUpDaoIF {
public void peristVistiorInfo(Audit vistiorInfo)throws DaoException;
}
