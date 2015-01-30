package com.iwinner.rms.scheduler;

import java.util.TimerTask;

import com.iwinner.rms.expections.ServiceException;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.service.SchedulerServiceIF;

public class SchedulerTaskList extends TimerTask {
	private SchedulerServiceIF schedulerServiceIF=null;
	public void run() {
		schedulerServiceIF=ServiceFactory.getSchedulerServiceFactory();
		Integer noOfUserInActive;
		Integer noOfUserExpire;
		try {
			noOfUserInActive=schedulerServiceIF.updateNoOfUsersIncative();
			noOfUserExpire=schedulerServiceIF.updatePasswordExpireDays();
			System.out.println(noOfUserInActive+"  "+noOfUserExpire);
		} catch (ServiceException e) {
		}

	}

}
