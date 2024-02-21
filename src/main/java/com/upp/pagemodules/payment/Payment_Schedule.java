package com.upp.pagemodules.payment;

import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.stepdefinition.DealPage;
import com.upp.stepdefinition.TS105;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class Payment_Schedule extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public String toAccountNo = "";
	public static DateUtils dateutil;
	public static String day = "";
	public static JavascriptClick js;

	public Payment_Schedule() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		dateutil = new DateUtils();
		js = new JavascriptClick(driver);
	}

	public void createPayments_Schedule(String TSID, String sourceAccountno, String toaccountNo, ICallback iCallback) throws Exception {

		String InstructionType = externalData.getFieldData(TSID, "Scheduled", "Select Instruction Type");
		boolean repeating = commonutils.isElementDisplayed(od.payment_Frequency1, 2);
		if (((externalData.getFieldData(TSID, "Scheduled", "Schedule - Repeating")).equalsIgnoreCase("Y")|| (externalData.getFieldData(TSID, "Scheduled", "Schedule - Repeating")).equalsIgnoreCase("Yes"))) {
			String afterMonth = externalData.getFieldData(TSID, "Scheduled", "End Date");
			if(!repeating) {
			od.payments_Repeatingslider.click();
			}
			String frequency = externalData.getFieldData(TSID, "Scheduled", "Frequency");
			dropdown.selectByVisibleText(od.payment_Frequency1,frequency);
			iCallback.handleCallback("FREQUENCY", frequency);
		
			java.time.LocalDate today = java.time.LocalDate.now();
			java.time.LocalDate futureDate = today.plusMonths(4);
			java.time.LocalDate lastDayOfMonth = futureDate.withDayOfMonth(futureDate.lengthOfMonth());
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
	        String formattedDate = lastDayOfMonth.format(formatter);
			System.out.println(formattedDate);
			od.payments_endDate.click();
			for (int i = 0; i < (int)Double.parseDouble(afterMonth); i++) {
				od.payments_nextButton.click();
			}
			By excecutionDay = By.xpath(
					"//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"
							+ formattedDate + "']");
			driver.findElement(excecutionDay).click();

			Thread.sleep(1000);
		}
		else {
			if(repeating) {
				if(commonutils.isElementDisplayed(od.payments_endDate, 10)) {
					od.payments_Repeatingslider.click();
				}
				}
		}
//		if(!TSID.equals("TS152")) {
//		// Repeating slider is enabled by default so have to disbale the slider
//		if (((externalData.getFieldData(TSID, "Scheduled", "Split")).equalsIgnoreCase("Y")
//				|| (externalData.getFieldData(TSID, "Scheduled", "Split")).equalsIgnoreCase("Yes"))) {
//			System.out.println("inside------------- Split and Repeat");
//			Thread.sleep(1000);
//			js.click(od.payments_Repeatingslider);
//		}
//		}

		if (((externalData.getFieldData(TSID, "Scheduled", "Sweep in")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Sweep in")).equalsIgnoreCase("Yes"))) {
			od.payments_SweepInSlider.click();
		}
		if (commonutils.isElementDisplayed(od.payments_SweepinNextButton, 1)) {
			od.payments_SweepinNextButton.click();
		}
		applyExplicitWaitsUntilElementClickable(od.payments_ExecutionDate, Duration.ofSeconds(20));
		od.payments_ExecutionDate.click();

		if (InstructionType.equalsIgnoreCase("Payment - Retention")) {
			day = dateutil.getDay();
			int day_int = Integer.parseInt(day) + 2;
			day = Integer.toString(day_int);
		}

		if (InstructionType.equalsIgnoreCase("Payment")) {
			day = dateutil.getDay();
			if(TSID.equalsIgnoreCase("TS126_1"))
			{
				int day_int = Integer.parseInt(day) + 1;
				day = Integer.toString(day_int);
			}
		}

		By excecutionDay = By.xpath(
				"//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"
						+ day + "']");

		Thread.sleep(1000);
		try {
			driver.findElement(excecutionDay).click();
		} catch (Exception e) {
			if (Integer.parseInt(DateUtils.getDay()) >= 29) {
				excecutionDay = By.xpath("//td[contains(@class,'ui-calendar-outFocus') and normalize-space()='2'] ");
				driver.findElement(excecutionDay).click();
			}
		}
		
		applyExplicitWaitsUntilElementClickable(od.payments_ScheduleAt, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.payments_ScheduleAt,externalData.getFieldData(TSID, "Scheduled", "Schedule At"));
		Thread.sleep(2000);
		if ((TSID.equalsIgnoreCase("TS105")) || (TSID.equalsIgnoreCase("TS108")) || (TSID.equalsIgnoreCase("TS110")) || (TSID.equalsIgnoreCase("TS113")) || (TSID.equalsIgnoreCase("TS122_1")) || (TSID.equalsIgnoreCase("TS126_1")) || (TSID.equalsIgnoreCase("TS137")) || (TSID.equalsIgnoreCase("TS140")) || (TSID.equalsIgnoreCase("TS141")) || (TSID.equalsIgnoreCase("TS142")) ) {

			dropdown.selectByVisibleText(od.payments_TimeZone, "Asia/Calcutta (GMT+05:30)");
			String time = dateutil.getTimeAfterMins(10);
			od.payments_ScheduleTime.clear();
			od.payments_ScheduleTime.sendKeys(time);
//			Thread.sleep(3000);
		}
		
		else if (externalData.getFieldData(TSID, "Scheduled", "Schedule At").trim().equalsIgnoreCase("At specific time")) {

			String time = dateutil.getTimeAfterMins(5);
			System.out.println(time);
			od.payments_ScheduleTime.clear();
			od.payments_ScheduleTime.sendKeys(time);
		}
		dropdown.selectByVisibleText(od.payments_HolidayAction,externalData.getFieldData(TSID, "Scheduled", "Holiday Action"));
		applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonTransferSchedule, Duration.ofSeconds(20));
		od.payments_NextArrowButtonTransferSchedule.click();
	}
}
