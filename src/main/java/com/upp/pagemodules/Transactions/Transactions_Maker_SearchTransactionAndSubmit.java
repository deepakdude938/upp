package com.upp.pagemodules.Transactions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
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

public class Transactions_Maker_SearchTransactionAndSubmit extends BaseClass {

	public static Object_NewDeal od;
	public static Object_Transactions tm;
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

	public Transactions_Maker_SearchTransactionAndSubmit() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		tm = new Object_Transactions();

	}

	public void txnMaker_SubmitDeal(String dealid) throws Exception {
		System.out.println("deal id in maker = " + dealid);
		// TimeUnit.MINUTES.sleep(waitingTime);
		Thread.sleep(2000);
		tm.transactions_TransactionIcon.click();
		try {
			tm.transactions_TransactionMaker.click();
		} catch (Exception e) {
			handleElementClickException(tm.transactions_TransactionMaker);
		}
		try {
			od.TxnMaker_searchDealId.clear();
			od.TxnMaker_searchDealId.sendKeys(dealid);
		} catch (Exception e) {
			handleElementClickException(od.TxnMaker_searchDealId);
			od.TxnMaker_searchDealId.clear();
			od.TxnMaker_searchDealId.sendKeys(dealid);
		}
		applyExplicitWaitsUntilElementInvisible(od.TxnChecker_TransactionProgress, 100);
		try {
			applyExplicitWaitsUntilElementClickable(od.TxnMaker_txnCheckbox, Duration.ofSeconds(5));
			od.TxnMaker_txnCheckbox.click();
		} catch (Exception e) {
			handleElementClickException(od.TxnMaker_txnCheckbox);
		}
		try {
			od.TxnMaker_submitBtn.click();
			od.TxnMaker_okBtn.click();
		} catch (Exception e) {
			handleElementClickException(od.TxnMaker_submitBtn);
			od.TxnMaker_submitBtn.click();
			od.TxnMaker_okBtn.click();
		}
	}

}
