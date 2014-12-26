package com.iwinner.rms.utils;

import java.util.Map;

import com.iwinner.rms.constants.RMSConstants;

public class ValidateUserUtils {

	public static boolean validateUserIdAndPWD(Map<String,String> userInfo){
		String username=userInfo.get(RMSConstants.USERNAME);
		String password=userInfo.get(RMSConstants.PASSWORD);
		if(username!=null&&username.trim().length()!=0 &&password!=null&&username.trim().length()!=0){
			return true;
		}
		return false;
	}
}
