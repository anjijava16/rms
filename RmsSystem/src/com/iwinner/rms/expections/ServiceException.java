package com.iwinner.rms.expections;

/**
 * The class ServiceException
 * 
 * @author anjaiah@IWinner
 * @version 1.x
 * @see com.iwinner.irpc.factory.expections.ServiceException.
 * @since Nov 4th,2014
 * 
 */

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;


	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable throwable) {
		super(throwable);
	}

	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
