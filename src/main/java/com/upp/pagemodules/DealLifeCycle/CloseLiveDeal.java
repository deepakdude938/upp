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

public class CloseLiveDeal extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public JavascriptClick js;
	public static DateUtils dateutil;
	Object_DealLifecycle dl;

	public CloseLiveDeal() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
		dl = new Object_DealLifecycle();
	}

	public void closeLiveDeal(String dealId) throws Exception {

		applyExplicitWaitsUntilElementClickable(od.deal_SideMenuIcon, Duration.ofSeconds(15));
		od.deal_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(od.liveDealIcon, Duration.ofSeconds(15));
		od.liveDealIcon.click();
		applyExplicitWaitsUntilElementClickable(od.dealChecker_searchSelect, Duration.ofSeconds(25));
		dropdown.selectByVisibleText(od.dealChecker_searchSelect, "Deal Id");
		applyExplicitWaitsUntilElementClickable(od.dealChecker_searchBar, Duration.ofSeconds(15));
		od.dealChecker_searchBar.sendKeys(dealId);
		Thread.sleep(4000);
		od.dealChecker_searchButton.click();
		Thread.sleep(3000);
		applyExplicitWaitsUntilElementClickable(od.dealChecker_showMenu, Duration.ofSeconds(30));
		od.dealChecker_showMenu.click();
		applyExplicitWaitsUntilElementClickable(dl.Close_icon, Duration.ofSeconds(5));
		dl.Close_icon.click();
		applyExplicitWaitsUntilElementClickable(dl.Yes_Icon, Duration.ofSeconds(5));
		dl.Yes_Icon.click();
		applyExplicitWaitsUntilElementClickable(dl.Ok_Icon, Duration.ofSeconds(5));
		dl.Ok_Icon.click();
		Thread.sleep(4000);

	}

}
