package com.upp.pagemodules.payment;

import java.time.Duration;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.CommonUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

public class Payment_Retention extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public JavascriptClick js ;

	public Payment_Retention() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js=new JavascriptClick(driver);
	}

	public void createRetention(String TSID) throws Exception {

		applyExplicitWaitsUntilElementClickable(od.retention_Tab, Duration.ofSeconds(5));
		od.retention_Tab.click();
		applyExplicitWaitsUntilElementClickable(od.retention_Purpose, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.retention_Purpose, "Retention");
		od.retention_Remarks.sendKeys(externalData.getFieldData(TSID,"PaymentRetention","Remarks"));
		Thread.sleep(1000);
		js.sendKeys(od.retention_Execute,"A few days prior");
	    Thread.sleep(1000);
		js.click(od.retention_FewDaysPrior);
		//od.retention_NoOfDays.sendKeys(externalData.getFieldData(TSID,"PaymentRetention","NumberOfDays"));
		od.retention_NoOfDays.sendKeys("2");
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(od.retention_nextArrowIcon, Duration.ofSeconds(3));
		od.retention_nextArrowIcon.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.Retention_Summary, Duration.ofSeconds(5));
		od.Retention_Summary.click();
		Thread.sleep(1000);
		od.payments_SimulateButton.click();
		Thread.sleep(1000);
		od.Retention_Summary_close.click();
		Thread.sleep(1000);
	}
	
	public void createRetentionSurplus(String TSID) throws Exception {

		applyExplicitWaitsUntilElementClickable(od.retention_Tab, Duration.ofSeconds(5));
		od.retention_Tab.click();
		applyExplicitWaitsUntilElementClickable(od.retention_Purpose, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.retention_Purpose, "Retention");
		od.retention_Remarks.sendKeys(externalData.getFieldData(TSID,"PaymentRetention","Remarks"));
		Thread.sleep(1000);
		js.sendKeys(od.retention_Execute,"A few days prior");
	    Thread.sleep(2000);
		js.click(od.retention_FewDaysPrior);
		System.out.println(externalData.getFieldData(TSID,"PaymentRetention","NumberOfDays"));
		od.retention_NoOfDays.sendKeys("3");
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(od.retention_nextArrowIcon, Duration.ofSeconds(3));
		od.retention_nextArrowIcon.click();
		Thread.sleep(5000);
	}
}
