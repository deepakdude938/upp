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

public class ProcessingUnit extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public JavascriptClick js;
	public static DateUtils dateutil;
	Object_Configuration oc;

	public ProcessingUnit() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
		oc = new Object_Configuration();

	}

	public String CreateProcessingUnit(String TSID) throws Exception {
		
        Thread.sleep(3000);
		scroll.scrollInToView(oc.configurationButton);
		applyExplicitWaitsUntilElementClickable(oc.configurationButton, Duration.ofSeconds(15));
		oc.configurationButton.click();
		scroll.scrollInToView(oc.configuration_ManageConfigButton1);
		applyExplicitWaitsUntilElementClickable(oc.configuration_ManageConfigButton1, Duration.ofSeconds(5));
		oc.configuration_ManageConfigButton1.click();
		applyExplicitWaitsUntilElementClickable(oc.configuration_ProcessingUnitButton, Duration.ofSeconds(5));
		oc.configuration_ProcessingUnitButton.click();
		applyExplicitWaitsUntilElementClickable(oc.configuration_AddNewButton, Duration.ofSeconds(5));
		oc.configuration_AddNewButton.click();
		applyExplicitWaitsUntilElementClickable(oc.PU_Name, Duration.ofSeconds(3));

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String UniquePU = "UPP_AUTOMATION" + random;
		System.out.println("The Unique PU is"+UniquePU);
		oc.PU_Name.sendKeys(UniquePU);

		oc.PU_Description.sendKeys(externalData.getFieldData(TSID, "Processing Unit", "Description"));
		oc.PU_Countries.sendKeys(externalData.getFieldData(TSID, "Processing Unit", "Countries"));
		applyExplicitWaitsUntilElementClickable(oc.PU_Countries_Select, Duration.ofSeconds(3));
		oc.PU_Countries_Select.click();
		oc.PU_SubmitButton.click();
		
		return UniquePU;

	}

}
