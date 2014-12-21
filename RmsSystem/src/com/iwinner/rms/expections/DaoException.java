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

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable throwable) {
		super(throwable);
	}

	public DaoException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
