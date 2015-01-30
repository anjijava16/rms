package com.iwinner.rms.utils;

import java.util.Map;

import com.iwinner.rms.constants.RMSConstants;

public class ValidateUserUtils {

	public static boolean validateUserIdAndPWD(Map<String, String> userInfo) {
		String username = userInfo.get(RMSConstants.USERNAME);
		String password = userInfo.get(RMSConstants.PASSWORD);
		if (username != null && username.trim().length() != 0
				&& password != null && password.trim().length() != 0) {
			return true;
		}
		return false;
	}

	public static String validateUser(String username) {
		String usernameMessage = RMSConstants.EMPTY_MESSAGE;
		if (username == null) {
			usernameMessage = RMSConstants.USERNAME_NULL;
		}
		if (username.trim().length() == 0) {
			usernameMessage = RMSConstants.USERNAME_EMPTY;

		}
		return usernameMessage;
	}

	public static String validatePassword(String password) {
		String passwordMessage = RMSConstants.EMPTY_MESSAGE;
		if (password == null) {
			passwordMessage=RMSConstants.PASSWORD_NULL;
		}
		if (password.trim().length() == 0) {
			passwordMessage=RMSConstants.PASSWORD_EMPTY;
		}

		return passwordMessage;
	}
}
