package com.upp.pagemodules.DealLifeCycle;

import java.time.Duration;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_DealLifecycle;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.CommonUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import com.upp.utils.DateUtils;

public class LifeCycleChecker extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public JavascriptClick js;
	public static DateUtils dateutil;
	Object_DealLifecycle dl;

	public LifeCycleChecker() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
		dl = new Object_DealLifecycle();
	}

	public void Approve_From_LifecycleChecker(String dealId) throws Exception {
		applyExplicitWaitsUntilElementClickable(dl.Deal_Lifecycle, Duration.ofSeconds(5));
		dl.Deal_Lifecycle.click();
		applyExplicitWaitsUntilElementClickable(dl.LifecycleCheckerIcon, Duration.ofSeconds(5));
		dl.LifecycleCheckerIcon.click();
		applyExplicitWaitsUntilElementClickable(dl.LifecycleMaker_Dealid, Duration.ofSeconds(5));
		dl.LifecycleMaker_Dealid.sendKeys(dealId);
		Thread.sleep(1000);
		js.click(dl.LifecycleMaker_submit);
		applyExplicitWaitsUntilElementClickable(dl.AddYourComments, Duration.ofSeconds(5));
		dl.AddYourComments.sendKeys("Ok");
		applyExplicitWaitsUntilElementClickable(dl.Appprove_Button, Duration.ofSeconds(5));
		dl.Appprove_Button.click();
		applyExplicitWaitsUntilElementClickable(dl.Yes_Icon, Duration.ofSeconds(5));
		dl.Yes_Icon.click();
		Thread.sleep(5000);
		
		

	}

}
