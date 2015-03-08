package com.iwinner.rms.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

 public static Timestamp getDateInTimestamp(String date){
	 Timestamp timestamp=null;
	 try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		 Date parsedDate = (Date)dateFormat.parse(date);// 15-Jan-2015 10:42:15
		   timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    //System.out.println(timestamp);
		}catch(Exception e){//this generic but you can control another types of exception
			e.printStackTrace();
		}
	 return timestamp;
 }
	public static Date expireDate() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 60);
		Date ds = cal.getTime();
		return ds;
	}

 public static void main(String[] args) {
	 Date date=expireDate();
	 System.out.println(date);
	//System.out.println(getDateInTimestamp("15-Jan-2015 10:42:15")+" ====>>>>");
}
}
