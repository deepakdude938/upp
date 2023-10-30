package com.upp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static String getDate(int i) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date1;
		// Date today = calendar.getTime();
		calendar.add(Calendar.DATE, i);
		Date tomorrow = calendar.getTime();
		date1 = sdf.format(tomorrow);
		return date1;
	}

	public static String getTime(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, i);
		Date tomorrow = calendar.getTime();
		String hours = tomorrow.toString();
		return hours;
	}

	public static String getTimeInMin(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, i);
		int tomorrow = calendar.get(Calendar.MINUTE);
		String hours = String.valueOf(tomorrow);
		return hours;
	}

	public static String getTimeInSec(int i) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + 3);
		int tomorrow = cal.get(Calendar.SECOND);
		String hours = String.valueOf(tomorrow);
		return hours;
	}

	public static String getDateAndTime(int i) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-ddHH:mm:ss");
		String date1;
		// Date today = calendar.getTime();
		
//		2023-04-10T16:12:41.440Z"
//		System.out.println(sdf);
		calendar.add(Calendar.DATE, i);
		Date tomorrow = calendar.getTime();
		date1 = sdf.format(tomorrow);
		return date1;
	}

	public boolean timediff(String time1) throws Exception {
		Date date1 = new SimpleDateFormat("dd/MM/yyyyHH:mm:ss").parse(time1);
		System.out.println("\t" + date1);
		/*
		 * Date date2=new SimpleDateFormat("dd/MM/yyyyHH:mm:ss").parse(time2);
		 * System.out.println("\t"+date1);
		 */
		return true;
	}

	public static String getDate1() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		String date1;
		Date today = calendar.getTime();
		// Date tomorrow = calendar.getTime();
		date1 = sdf.format(today);
		return date1;
	}

	public static String getTimeAfterMins(int mins) {
		SimpleDateFormat df = new SimpleDateFormat("hh.mm aa");

		String dateString = df.format(new Date()).toString();
		System.out.println(dateString);
		Date d = null;
		try {
			d = df.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, mins);
		String newTime = df.format(cal.getTime());
		System.out.println(newTime);
		return newTime;

	}
	
	public static String getDay() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("d");
		String date1;
		Date today = calendar.getTime();
		// Date tomorrow = calendar.getTime();
		date1 = sdf.format(today);
		return date1;
	}
	
	public static String getCurrentDateUTC() {
		
		
	    LocalDateTime date= LocalDateTime.now(ZoneOffset.UTC);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String utcdate= date.format(formatter);
	    return utcdate;
	}
	
	public static String getCurrentDate() {
		
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String date1;
		// Date today = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date tomorrow = calendar.getTime();
		date1 = sdf.format(tomorrow);
		return date1;
	}
	
	public static String getCurrentTimeUTC() {
		LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("UTC"));

		LocalDateTime newDateTime = currentDateTime.plusMinutes(1);

		ZonedDateTime zonedDateTime = newDateTime.atZone(ZoneId.of("UTC"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String formattedDateTime = zonedDateTime.format(formatter);
		return formattedDateTime;
	}
	
	public static String getCurrentTimeUTCPlus2Minutes() {
		LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("UTC"));

		LocalDateTime newDateTime = currentDateTime.plusMinutes(2);

		ZonedDateTime zonedDateTime = newDateTime.atZone(ZoneId.of("UTC"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String formattedDateTime = zonedDateTime.format(formatter);
		return formattedDateTime;
	}
	
	public static String getCurrentTimeUTCPlusMInutes(int min) {
		LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("UTC"));

		LocalDateTime newDateTime = currentDateTime.plusMinutes(min);

		ZonedDateTime zonedDateTime = newDateTime.atZone(ZoneId.of("UTC"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h.mm a");
		String formattedDateTime = zonedDateTime.format(formatter);
		return formattedDateTime;
	}
	
	public static String getCurrentDateTime(){
		
		 LocalDateTime currentDateTime = LocalDateTime.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");
	     String formattedDateTime = currentDateTime.format(formatter);
	     
		return formattedDateTime;
			
		}
	
	public static String getCurrentDateTime1(){
		
		 LocalDateTime currentDateTime = LocalDateTime.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");
	     String formattedDateTime = currentDateTime.format(formatter);
	     
		return formattedDateTime;
			
		}
	
	public static String getNextUTCtDate() {
		 LocalDateTime date= LocalDateTime.now(ZoneOffset.UTC);
	    LocalDateTime tomorrow = date.plusDays(1);
	    System.out.println(tomorrow.isAfter(date));
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String utcdate= tomorrow.format(formatter);
	    return utcdate;
	}
	
	public static String getTodayDate() {
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String date1;
		Date tomorrow = calendar.getTime();
		date1 = sdf.format(tomorrow);
		return date1;
	}
	
		public static String getTimeAfterNMinutes(int minutesToAdd) {
	        try {
	            SimpleDateFormat sdfInput = new SimpleDateFormat("hh:mma");
	            SimpleDateFormat sdfOutput = new SimpleDateFormat("hh:mma");

	            Calendar calendar = Calendar.getInstance();
	            String currentTime = sdfInput.format(calendar.getTime());

	            calendar.setTime(sdfInput.parse(currentTime));
	            calendar.add(Calendar.MINUTE, minutesToAdd);

	            return sdfOutput.format(calendar.getTime());
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
    }
	
