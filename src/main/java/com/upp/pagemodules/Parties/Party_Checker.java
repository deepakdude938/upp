package com.upp.pagemodules.Parties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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
public class Party_Checker extends BaseClass {

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
	public Party_Checker() {

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

	
	public void PartyChecker(String TSID) throws Exception
	{
		applyExplicitWaitsUntilElementClickable(op.parties_Icon,Duration.ofSeconds(30));
		op.parties_Icon.click();
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_Icon,Duration.ofSeconds(15));
		op.PartyChecker_Icon.click();
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_CustomerID_SearchBox,Duration.ofSeconds(20));
		op.PartyChecker_CustomerID_SearchBox.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Customer Id"));
		Thread.sleep(7000);
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_EditIcon,Duration.ofSeconds(10));
		jsClick.click(op.PartyChecker_EditIcon);
		op.PartyMaker_SummaryTab.click();
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_AddComment,Duration.ofSeconds(10));
		op.PartyChecker_AddComment.sendKeys(externalData.getFieldData(TSID, "Parties-Checker", "Summary - Add your comments here"));
		op.PartyChecker_ApproveButton.click();
		op.PartyMaker_YesButton.click();
		Thread.sleep(4000);
	}
}
