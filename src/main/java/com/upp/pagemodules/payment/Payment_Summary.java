package com.upp.pagemodules.payment;


import java.time.DayOfWeek;
import java.time.Duration;
import java.time.temporal.TemporalAdjusters;

import org.hamcrest.MatcherAssert;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Assert;
import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;


public class Payment_Summary extends BaseClass{
	
	
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;


	public Payment_Summary() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);	
	}


	public void checkSchedulePaymentforNextBusinessDay() throws Exception {
	
		od.payments_SummaryButton.click();
		Thread.sleep(2000);
		od.payments_SimulateButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_SimulateExecutionStrikeDate, Duration.ofSeconds(20));
		String strikeDate = od.payments_SimulateExecutionStrikeDate.getText();
		String actualDate = od.payments_SimulateExecutionActualDate.getText();
		LocalDate now = new LocalDate();
	    LocalDate friday = now.withDayOfWeek(DateTimeConstants.FRIDAY);
	    LocalDate saturday = now.withDayOfWeek(DateTimeConstants.SATURDAY);
	    LocalDate nextSunday = now.now().plusDays(DateTimeConstants.SUNDAY - now.now().getDayOfWeek());
	    LocalDate nextMonday = null;
		 if (now.now().getDayOfWeek() == DateTimeConstants.MONDAY) {
			 nextMonday =now.now().plusWeeks(1);
	        } else {
	            int daysUntilNextMonday = (DateTimeConstants.MONDAY - now.now().getDayOfWeek() + 7) % 7;
	            nextMonday = now.now().plusDays(daysUntilNextMonday);
	        }
		  LocalDate nextTuesday = null;
			 if (now.now().getDayOfWeek() == DateTimeConstants.TUESDAY) {
				 nextTuesday =now.now().plusWeeks(1);
		        } else {
		            int daysUntilNextTuesday = (DateTimeConstants.TUESDAY - now.now().getDayOfWeek() + 7) % 7;
		            nextTuesday = now.now().plusDays(daysUntilNextTuesday);
		        }
			 
	    String day =friday.toString().split("[/-]")[2];
		String sat= saturday.toString().split("[/-]")[2];
		String nextMon= nextMonday.toString().split("[/-]")[2];
		String nextTue= nextTuesday.toString().split("[/-]")[2];
		String nextSun= nextSunday.toString().split("[/-]")[2];
		 String actualDate1 = actualDate.split(" ")[0].split("-")[0];
		Assert.assertEquals(day, strikeDate.split(" ")[0].split("-")[0]);
		Assert.assertTrue((actualDate1.equals(sat) || actualDate1.equals(nextMon) || actualDate1.equals(nextTue)));
//	    Assert.assertTrue(actualDate.split(" ")[0].split("-")[0]+"".equals(sat) || actualDate.split(" ")[0].split("-")[0]+"".equals( nextSun) || actualDate.split(" ")[0].split("-")[0]+"".equals(nextMon));
	  	}
}
