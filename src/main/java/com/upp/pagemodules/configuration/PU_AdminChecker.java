package com.upp.pagemodules.configuration;

import java.time.Duration;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_Configuration;
import com.upp.pageobjects.Object_DealLifecycle;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.CommonUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import com.upp.utils.DateUtils;

public class PU_AdminChecker extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public JavascriptClick js;
	public static DateUtils dateutil;
	Object_Configuration oc;

	public PU_AdminChecker() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
		oc = new Object_Configuration();

	}

	public void Aprrove_PU_From_AdminChecker() throws Exception {
		
//		applyExplicitWaitsUntilElementClickable(oc.configuration_ProcessingUnitButton, Duration.ofSeconds(5));
//		oc.configuration_ProcessingUnitButton.click();
		applyExplicitWaitsUntilElementClickable(oc.PU_AdminChecker, Duration.ofSeconds(5));
		oc.PU_AdminChecker.click();
		Thread.sleep(1000);
		js.click(oc.PU_EditIcon);
		applyExplicitWaitsUntilElementClickable(oc.PU_Comment, Duration.ofSeconds(5));
		oc.PU_Comment.sendKeys("approved");
		applyExplicitWaitsUntilElementClickable(oc.PU_ApproveButton, Duration.ofSeconds(3));
		oc.PU_ApproveButton.click();
		applyExplicitWaitsUntilElementClickable(oc.PU_OkButton, Duration.ofSeconds(10));
		oc.PU_OkButton.click();
		
		

	}

}
