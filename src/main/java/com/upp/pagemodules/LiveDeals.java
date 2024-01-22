package com.upp.pagemodules;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class LiveDeals extends BaseClass{
	
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int waitingTime = 5;
	
	public LiveDeals() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
	}

	public void liveDealsCheck(String TSID) throws Exception {
		od.deal_SideMenuIcon.click();
		od.liveDealIcon.click();
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
		Thread.sleep(3000);
		
		if(od.liveDeals_DealIdCheck.isDisplayed())
		{
			System.out.println("Newly created deal is present under Live Deals");
		}
		else {
			System.out.println("Newly created deal is not present under Live Deals");
		}
}
}