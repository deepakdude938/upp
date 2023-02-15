package com.upp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class datef {
	public static void main(String[] argv) throws Exception {
		SimpleDateFormat df= new SimpleDateFormat("hh.mm aa");
		
		String dateString = df.format(new Date()).toString();
		System.out.println(dateString);
		Date d = df.parse(dateString); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, 10);
		 String newTime = df.format(cal.getTime());
		 System.out.println(newTime);
	}
}
