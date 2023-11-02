package com.upp.pagemodules.payment;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

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

	public void createPayments_Schedule(String TSID, String sourceAccountno, String toaccountNo) throws Exception {

		String InstructionType = externalData.getFieldData(TSID, "Scheduled", "Select Instruction Type");

		if (((externalData.getFieldData(TSID, "Scheduled", "Schedule - Repeating")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Schedule - Repeating")).equalsIgnoreCase("Yes"))) {

			od.payments_Repeatingslider.click();
			dropdown.selectByVisibleText(od.payment_Frequency1,
					externalData.getFieldData(TSID, "Scheduled", "Frequency"));
		}

		// Repeating slider is enabled by default so have to disbale the slider
		if (((externalData.getFieldData(TSID, "Scheduled", "Split")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Split")).equalsIgnoreCase("Yes"))) {
			System.out.println("inside------------- Split and Repeat");
			Thread.sleep(1000);
			js.click(od.payments_Repeatingslider);
		}

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
		dropdown.selectByVisibleText(od.payments_ScheduleAt,
				externalData.getFieldData(TSID, "Scheduled", "Schedule At"));
		if (externalData.getFieldData(TSID, "Scheduled", "Schedule At").trim().equalsIgnoreCase("At specific time")) {

			String time = dateutil.getTimeAfterMins(5);

			od.payments_ScheduleTime.clear();
			od.payments_ScheduleTime.sendKeys(time);
		}
		dropdown.selectByVisibleText(od.payments_HolidayAction,
				externalData.getFieldData(TSID, "Scheduled", "Holiday Action"));

		if ((TSID.equalsIgnoreCase("TS105")) || (TSID.equalsIgnoreCase("TS108")) || (TSID.equalsIgnoreCase("TS110")) || (TSID.equalsIgnoreCase("TS113")) || (TSID.equalsIgnoreCase("TS122_1")) ) {

			dropdown.selectByVisibleText(od.payments_TimeZone, "Asia/Calcutta (GMT+05:30)");
			String time = dateutil.getTimeAfterMins(10);

			od.payments_ScheduleTime.clear();
			od.payments_ScheduleTime.sendKeys(time);
			Thread.sleep(3000);

		}

		applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonTransferSchedule, Duration.ofSeconds(20));
		od.payments_NextArrowButtonTransferSchedule.click();
	}
}
