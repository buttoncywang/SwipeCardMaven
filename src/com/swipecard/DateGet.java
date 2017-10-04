package com.swipecard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGet {
	public static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String ti = df.format(new Date());
		return ti;
	}

	public static String getDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String ti  = df.format(new Date());
		return ti;
	}
	
	public static String changeTime(Date Time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ti = df.format(Time);
		return ti;
	}

}
