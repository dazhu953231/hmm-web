package com.hmm.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static long currentTimeSecs() {
		return System.currentTimeMillis() / 1000L;
	}
	
	public static String TimeMillisToDateTime(Long timeMillis){
		if(timeMillis==null){
			return null;
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
		Date date = new Date();
		date.setTime(timeMillis*1000);
		return sf.format(date);
	}
	
}
