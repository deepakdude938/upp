package com.upp.pagemodules.Deal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_Deal;
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

public class DealAccountCreator extends BaseClass {

	public static Object_Deal od;
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

	public DealAccountCreator() {

		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();

	}

	public String createNewAccount(String TSID) throws Exception {

		odpAccount.createAccount(TSID);
		accDetails = odpAccount.popelmnt(OdpApi.stack1);
		System.out.println("the account no is" + accDetails.getAccno());
		String accountNo = accDetails.getAccno();
		dropdown.selectByVisibleText(od.country, externalData.getFieldData(TSID, "Accounts", "Country"));
		dropdown.selectByVisibleText(od.currency, externalData.getFieldData(TSID, "Accounts", "Currency"));
		String physicalYesOrNo = externalData.getFieldData(TSID, "Accounts", "Physical");
		if (physicalYesOrNo.equalsIgnoreCase("Yes")) {
			dropdown.selectByVisibleText(od.physical, "Physical");
		} else {
			dropdown.selectByVisibleText(od.physical, " Virtual ");
		}
		od.searchTextBox.sendKeys(accountNo);
		od.searchButton.click();
		applyExplicitWaitsUntilElementClickable(od.accounts_addAccount, Duration.ofSeconds(5));
		od.accounts_addAccount.click();
		Thread.sleep(2000);
		return accountNo;
	}

}
