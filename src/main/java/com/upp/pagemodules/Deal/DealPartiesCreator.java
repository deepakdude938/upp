package com.upp.pagemodules.Deal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;
import freemarker.template.utility.DateUtil;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DealPartiesCreator extends BaseClass {

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
	DealPartiesHandler partyHandler = new DealPartiesHandler();

	public DealPartiesCreator() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();

	}

	public void createParties(String TSID, ICallback icallback) throws Exception, IOException {
		applyExplicitWaitsUntilElementClickable(od.parties_icon, Duration.ofSeconds(20));
		od.parties_icon.click();
		od.parties_GetStarted.click();
		String partyHandle = externalData.getFieldData(TSID, "Party", "Add a new Party");
		System.out.println(partyHandle);
		if (partyHandle.equalsIgnoreCase("Yes")) {
			partyHandler.handleAddNewParty(TSID, icallback);
		} else {
			partyHandler.handleLinkedExistingParty(TSID);
		}
	}

	public void createPeatiesWith1DebitorAnd2Creditor(String TSID, ICallback icallback) throws Exception {
		createParties(TSID, icallback);
		od.accountBackbtn.click();
		createParties(TSID, icallback);
	}

}
