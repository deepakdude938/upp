package com.upp.pagemodules.document;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Document;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;
import com.upp.pageobjects.Object_Transactions;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import freemarker.template.utility.DateUtil;
import java.text.SimpleDateFormat;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import callbackInterfaces.ICallback;

public class RequiredDocumentSchedule extends BaseClass {

	public static Object_Document od;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static Object_NewDeal on;

	public RequiredDocumentSchedule() {
		on = new Object_NewDeal();
		od = new Object_Document();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);

	}

	public void createWorkItem(String dealid) throws Exception {
		applyExplicitWaitsUntilElementClickable(on.deal_SideMenuIcon, Duration.ofSeconds(15));
		on.deal_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(on.liveDealIcon, Duration.ofSeconds(15));
		on.liveDealIcon.click();
		applyExplicitWaitsUntilElementClickable(on.dealChecker_searchSelect, Duration.ofSeconds(25));
		dropdown.selectByVisibleText(on.dealChecker_searchSelect, "Deal Id");
		applyExplicitWaitsUntilElementClickable(on.dealChecker_searchBar, Duration.ofSeconds(15));
		on.dealChecker_searchBar.sendKeys(dealid);
		Thread.sleep(4000);
		on.dealChecker_searchButton.click();
		Thread.sleep(3000);
		od.DocumentTracker.click();
		Thread.sleep(3000);
		try {
		od.requiredDoc_requiredDocSchedule.click();
		}
		catch(Exception e) {
			handleElementClickException(od.requiredDoc_requiredDocSchedule);
		}
		applyExplicitWaitsUntilElementClickable(od.requiredDoc_dealId, Duration.ofSeconds(20));
		od.requiredDoc_dealId.sendKeys(dealid);
		Thread.sleep(4000);
		od.requiredDoc_createItem.click();
		Thread.sleep(4000);
		od.requiredDoc_OkBtn.click();
		Thread.sleep(2000);
		String status = od.requiredDoc_docStatus.getText();
		System.out.println(status);
		Assert.assertEquals(status, "Scheduled");
	}
}
