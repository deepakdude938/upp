package com.upp.pagemodules.payment;


import java.time.Duration;
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
		od.payments_SimulateButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_SimulateExecutionStrikeDate, Duration.ofSeconds(20));
		String strikeDate = od.payments_SimulateExecutionStrikeDate.getText();
		String actualDate = od.payments_SimulateExecutionActualDate.getText();
		LocalDate now = new LocalDate();
	    LocalDate friday = now.withDayOfWeek(DateTimeConstants.FRIDAY);
	    LocalDate saturday = now.withDayOfWeek(DateTimeConstants.SATURDAY);
	    String day =friday.toString().split("[/-]")[2];
		String sat= saturday.toString().split("[/-]")[2];
		 
		Assert.assertEquals(day, strikeDate.split(" ")[0].split("-")[0]);
	    Assert.assertEquals(actualDate.split(" ")[0].split("-")[0], sat);
	}
}
