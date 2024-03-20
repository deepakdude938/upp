package com.upp.pagemodules.payment;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Payment_Retry extends BaseClass {

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

	public Payment_Retry() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		dateutil = new DateUtils();
		js = new JavascriptClick(driver);
	}

	public void createPayments_Retry(String TSID, String sourceAccountno, String toaccountNo) throws Exception {

		od.payments_NextArrowButtonTransferSubInstruction.click();

		if (((externalData.getFieldData(TSID, "Scheduled", "Retry-Enable Auto Retry")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Retry-Enable Auto Retry")).equalsIgnoreCase("Yes"))) {
			od.payments_RetrySlider.click();

		}
		od.payments_NextArrowButtonRetryMechanism.click();
	}

	public void createPayments_Retry(String TSID) throws Exception {
		applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonTransferSubInstruction,
				Duration.ofSeconds(15));
		od.payments_NextArrowButtonTransferSubInstruction.click();
		Thread.sleep(6000);
		applyExplicitWaitsUntilElementClickable(od.payments_RetrySlider, Duration.ofSeconds(15));
		od.payments_RetrySlider.click();
		Thread.sleep(6000);
		js.click(od.payments_RetryType);

		if(TSID.equalsIgnoreCase("TS137")||TSID.equalsIgnoreCase("TS155"))
		{
		Thread.sleep(3000);
		js.click(od.payments_RetryType_Forever);
		}
		else	if(TSID.equalsIgnoreCase("TS140"))
		{
		Thread.sleep(3000);
		js.click(od.payments_RetryType_TillNextDate);
		}
		else	if(TSID.equalsIgnoreCase("TS141"))
		{
		Thread.sleep(3000);
		js.click(od.payments_RetryType_Custom);
		Thread.sleep(3000);
		od.payments_RetryType_CustomDays.sendKeys("1");
		}
		
		else if(TSID.equalsIgnoreCase("TS142") || TSID.equalsIgnoreCase("TS153") ||  TSID.equalsIgnoreCase("TS167")){
		Thread.sleep(3000);
		js.click(od.payments_RetryType_SameDay);
		Thread.sleep(3000);
			if(TSID.equalsIgnoreCase("TS142")) {
			od.payments_Hours.sendKeys("4");
			}
			else if(TSID.equalsIgnoreCase("TS153")) {
				od.payments_Hours.sendKeys("2");
			}
			else if(TSID.equalsIgnoreCase("TS167")) {
				od.payments_Hours.sendKeys("1");
			}
		}
		Thread.sleep(3000);
		applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonRetryMechanism, Duration.ofSeconds(15));
		od.payments_NextArrowButtonRetryMechanism.click();
		Thread.sleep(3000);
	}
}
