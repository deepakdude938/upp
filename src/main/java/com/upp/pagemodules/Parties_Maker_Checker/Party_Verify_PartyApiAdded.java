package com.upp.pagemodules.Parties_Maker_Checker;

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
public class Party_Verify_PartyApiAdded extends BaseClass {

	public static Object_NewDeal od;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int rowNum;
	public static OdpApi odpAccount;
	public static AccountDetails accDetails;
	DateUtils dateTime = new DateUtils();
	public static JavascriptClick jsClick;
	public static int waitingTime = 5;
	public static DateUtils dateutil;
	public static ScrollTypes scroll;
	public static String productName;
	public static Object_Parties op;
	DealPartiesHandler partyHandler = new DealPartiesHandler();
	public Party_Verify_PartyApiAdded() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		op=new Object_Parties();

	}

	public void Verify_PartyApiAdded(String dealId) throws Exception
	{
		 applyExplicitWaitsUntilElementClickable(od.deal_SideMenuIcon,Duration.ofSeconds(15));
		 od.deal_SideMenuIcon.click();
		 applyExplicitWaitsUntilElementClickable(od.liveDealIcon,Duration.ofSeconds(15));
		 od.liveDealIcon.click();
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_searchSelect,Duration.ofSeconds(25));
		 dropdown.selectByVisibleText(od.dealChecker_searchSelect,"Deal Id");
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_searchBar,Duration.ofSeconds(15));
		 od.dealChecker_searchBar.sendKeys(dealId);
		 Thread.sleep(4000);
		 od.dealChecker_searchButton.click();
		 Thread.sleep(3000);
		 applyExplicitWaitsUntilElementClickable(op.Party_PartiesNumber,Duration.ofSeconds(15));
		String partyNumber= op.Party_PartiesNumber.getText();
       System.out.println("Parties number is "+partyNumber);
		if(!(partyNumber.equals("2")))
		{
                Assert.fail();
		}
		else
		{
			System.out.println("Party successfully added through Api");
		}
		
		 applyExplicitWaitsUntilElementClickable( od.dealChecker_showMenu,Duration.ofSeconds(30));
		 od.dealChecker_showMenu.click();
		 applyExplicitWaitsUntilElementClickable(od.deal_EditIcon,Duration.ofSeconds(20));
		 od.deal_EditIcon.click();
		 applyExplicitWaitsUntilElementClickable(od.payments_DealsummaryIcon,Duration.ofSeconds(10));
		 od.payments_DealsummaryIcon.click();
		 Thread.sleep(2000);
		 ScrollTypes.scrollInsideWindowTillWebElementPresent(op.PartyMaker_Status_Inactive,op.Party_status_window, 6,600);
		 applyExplicitWaitsUntilElementClickable(op.PartyMaker_Status_Inactive,Duration.ofSeconds(10));
		String status= op.PartyMaker_Status_Inactive.getText();
		System.out.println("The status is "+status);
		Assert.assertEquals("Inactive",status);
		 

	}

}
