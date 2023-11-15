package com.upp.pagemodules.Transactions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_NewDeal;
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

public class Future_dated_Adhoc_Tnx extends BaseClass {

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
	public static Object_Transactions tm;
	String day;
	CommonUtils util;

	public Future_dated_Adhoc_Tnx() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		tm = new Object_Transactions();
		util=new CommonUtils(driver);

	}

	public void Common_Method_Future_dated_adhoc_Transactions(String TSID, String DealId) throws Exception {
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon, Duration.ofSeconds(120));
		tm.transactions_TransactionIcon.click();
		Thread.sleep(3000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionFutureDated, Duration.ofSeconds(120));
		tm.transactions_TransactionFutureDated.click();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_future_dated_DealId, Duration.ofSeconds(120));
		tm.transactions_future_dated_DealId.sendKeys(DealId);
		Thread.sleep(5000);
		


	}

	public void Verify_both_futured_Tnxs_are_present(String TSID, String DealId) throws Exception {
		Common_Method_Future_dated_adhoc_Transactions(TSID,DealId);
		
		applyExplicitWaitsUntilElementVisible(tm.transactions_future_dated_SCHD, Duration.ofSeconds(10));
		for (WebElement record : tm.transactions_future_dated_SCHD) {
			System.out.println(record.getText());
			Assert.assertEquals(record.getText(), "SCHD");

		}


	}
	
	public void edit_deal_do_nothing(String TSID, String DealId) throws Exception {
		 applyExplicitWaitsUntilElementClickable(od.deal_SideMenuIcon,Duration.ofSeconds(15));
		 od.deal_SideMenuIcon.click();
		 applyExplicitWaitsUntilElementClickable(od.liveDealIcon,Duration.ofSeconds(15));
		 od.liveDealIcon.click();
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_searchSelect,Duration.ofSeconds(25));
		 dropdown.selectByVisibleText(od.dealChecker_searchSelect,"Deal Id");
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_searchBar,Duration.ofSeconds(15));
		 od.dealChecker_searchBar.sendKeys(DealId);
		 Thread.sleep(700);
		 od.dealChecker_searchButton.click();
		 Thread.sleep(8000);
		 od.dealChecker_searchButton.click();
		 Thread.sleep(8000);
		 applyExplicitWaitsUntilElementClickable( od.dealChecker_showMenu,Duration.ofSeconds(30));
		 od.dealChecker_showMenu.click();
		 applyExplicitWaitsUntilElementClickable(od.deal_EditIcon,Duration.ofSeconds(20));
		 od.deal_EditIcon.click();
		 Thread.sleep(3000);
		 if(util.isElementDisplayed(od.deal_Edit_Yes_Button,2))
		 {
			 od.deal_Edit_Yes_Button.click();
			 Thread.sleep(3000);
		 }
		}
	
	public void cancel_One_Transaction_in_future_adhoc_Tnx(String TSID, String DealId) throws Exception {
		Common_Method_Future_dated_adhoc_Transactions(TSID,DealId);
		
		applyExplicitWaitsUntilElementVisible(tm.transactions_future_dated_Checkbox1, Duration.ofSeconds(10));
		tm.transactions_future_dated_Checkbox1.click();
		applyExplicitWaitsUntilElementVisible(tm.transactions_future_dated_Cancel1, Duration.ofSeconds(10));
		tm.transactions_future_dated_Cancel1.click();
		Thread.sleep(3000);
		applyExplicitWaitsUntilElementVisible(tm.transactions_future_dated_Proceed, Duration.ofSeconds(10));
		tm.transactions_future_dated_Proceed.click();
		

	}
	
	public void approve_the_tnx_from_tnx_checker(String TSID, String DealId) throws Exception {
	
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon, Duration.ofSeconds(120));
		jsClick.click(tm.transactions_TransactionIcon);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionChecker, Duration.ofSeconds(120));
		jsClick.click(tm.transactions_TransactionChecker);
		applyExplicitWaitsUntilElementClickable(tm.transactions_Checker_DealID, Duration.ofSeconds(100));
		tm.transactions_Checker_DealID.sendKeys(DealId);
		Thread.sleep(3000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_Checker_Checkbox1, Duration.ofSeconds(100));
		tm.transactions_Checker_Checkbox1.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionEditButton, Duration.ofSeconds(125));
		tm.transactions_TransactionEditButton.click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_SummaryTab, Duration.ofSeconds(100));
		tm.transactions_SummaryTab.click();
		Thread.sleep(3000);
		
		applyExplicitWaitsUntilElementClickable(tm.transactions_Checker_Add_comments, Duration.ofSeconds(120));
		scroll.scrollInToView(tm.transactions_Checker_Add_comments);
		tm.transactions_Checker_Add_comments.sendKeys("ok");
		applyExplicitWaitsUntilElementClickable(tm.transactions_Checker_SubmitButton, Duration.ofSeconds(120));
		tm.transactions_Checker_SubmitButton.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_YesButton, Duration.ofSeconds(120));
		tm.transactions_YesButton.click();

		applyExplicitWaitsUntilElementClickable(tm.transactions_Ok, Duration.ofSeconds(120));
		tm.transactions_Ok.click();

		
		
		
		
		

	}

	
}

