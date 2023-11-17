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

	public void createPayments_Retry(String TSID, String sourceAccountno, String toaccountNo)
			throws Exception {

		od.payments_NextArrowButtonTransferSubInstruction.click();

		if (((externalData.getFieldData(TSID, "Scheduled", "Retry-Enable Auto Retry")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Retry-Enable Auto Retry")).equalsIgnoreCase("Yes"))) {
			od.payments_RetrySlider.click();
		}
		od.payments_NextArrowButtonRetryMechanism.click();
}
	
	public void createPayments_RetryForever(String TSID)
			throws Exception {
		applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonTransferSubInstruction, Duration.ofSeconds(15));
		od.payments_NextArrowButtonTransferSubInstruction.click();
         Thread.sleep(3000);	
         applyExplicitWaitsUntilElementClickable(od.payments_RetrySlider, Duration.ofSeconds(15));
		od.payments_RetrySlider.click();
		 Thread.sleep(1500);
		 
		    applyExplicitWaitsUntilElementClickable(od.payments_RetryType, Duration.ofSeconds(15));
		    od.payments_RetryType.click();
		    Thread.sleep(1500);
		    od.payments_RetryType_Forever.click();
			 Thread.sleep(1500);
			applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonRetryMechanism, Duration.ofSeconds(15));
			od.payments_NextArrowButtonRetryMechanism.click();
			 Thread.sleep(1500);
		}		
}
