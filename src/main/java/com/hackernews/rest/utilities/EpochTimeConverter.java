package com.hackernews.rest.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EpochTimeConverter {
	
	public static String convertEpochTime(Integer sec) {
		Long milisec = Long.valueOf(sec.longValue());
		Date date = new Date(milisec);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return format.format(date);
	}
	
	public static long calcUserProfileTime(Integer sec) {
		long currentTime = System.currentTimeMillis()/1000;
		long difference_In_Time = (currentTime - sec)*1000;
		long difference_In_Years= (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
		return difference_In_Years;
	}
}
