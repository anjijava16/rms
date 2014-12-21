package com.iwinner.rms.service;

import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.model.Audit;

public interface AuditStartUpServiceIF {
public void saveVistiorInfo(Audit vistor)throws ServiceException;
}
