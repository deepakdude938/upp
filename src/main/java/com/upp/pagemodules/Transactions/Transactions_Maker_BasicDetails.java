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

public class Transactions_Maker_BasicDetails extends BaseClass {

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

	public Transactions_Maker_BasicDetails() {

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

	public void Transactions_Maker_BasicDetails(String TSID, String DealId, String sourceAccno) throws Exception {
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon, Duration.ofSeconds(15));
		tm.transactions_TransactionIcon.click();
		Thread.sleep(3000);
		tm.transactions_TransactionMaker.click();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_AddNewButon,Duration.ofSeconds(15));
		jsClick.click(tm.transactions_AddNewButon);
		applyExplicitWaitsUntilElementClickable(tm.transactions_DealId,Duration.ofSeconds(20));
		tm.transactions_DealId.sendKeys(DealId);
		By transactions_DealId = By.xpath("//div[contains(text(),'"+DealId+"')]");
	    driver.findElement(transactions_DealId).click();
	    tm.transactions_SourceAccNo.sendKeys(sourceAccno);
		By transactions_SouceAccno = By.xpath("//div[contains(text(),'"+sourceAccno+"')]");
	    driver.findElement(transactions_SouceAccno).click();
	    jsClick.click(tm.transactions_SubmitButton);
	    od.payments_BasicName.clear();
		od.payments_BasicName.sendKeys(externalData.getFieldData(TSID,"Txn Maker","Name"));
		dropdown.selectByVisibleText(od.payments_Purpose,externalData.getFieldData(TSID,"Txn Maker","Purpose"));
		dropdown.selectByVisibleText(od.payments_BalanceConsideration,externalData.getFieldData(TSID,"Txn Maker","Balance Consideration"));
		Thread.sleep(2000);
		if (((externalData.getFieldData(TSID, "Txn Maker", "Split")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Txn Maker", "Split")).equalsIgnoreCase("Yes"))) {
			od.payments_SplitBalanceSlider.click();
		}
		od.payments_NextArrowButtonTransferBasic.click();

	}

	public void Transactions_Maker_BasicDetails1(String TSID, String DealId, String sourceAccno) throws Exception {
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon1, Duration.ofSeconds(15));
		tm.transactions_TransactionIcon1.click();
		Thread.sleep(3000);
		tm.transactions_TransactionMaker.click();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_AddNewButon,Duration.ofSeconds(15));
		jsClick.click(tm.transactions_AddNewButon);
		applyExplicitWaitsUntilElementClickable(tm.transactions_DealId,Duration.ofSeconds(20));
		tm.transactions_DealId.sendKeys(DealId);
		By transactions_DealId = By.xpath("//div[contains(text(),'"+DealId+"')]");
	    driver.findElement(transactions_DealId).click();
	    tm.transactions_SourceAccNo1.sendKeys(sourceAccno);
		By transactions_SouceAccno = By.xpath("//div[contains(text(),'"+sourceAccno+"')]");
	    driver.findElement(transactions_SouceAccno).click();
	    jsClick.click(tm.transactions_SubmitButton);
	    od.payments_BasicName.clear();
		od.payments_BasicName.sendKeys(externalData.getFieldData(TSID,"Txn Maker","Name"));
		dropdown.selectByVisibleText(od.payments_Purpose,externalData.getFieldData(TSID,"Txn Maker","Purpose"));
		dropdown.selectByVisibleText(od.payments_BalanceConsideration,externalData.getFieldData(TSID,"Txn Maker","Balance Consideration"));
		Thread.sleep(2000);
		if (((externalData.getFieldData(TSID, "Txn Maker", "Split")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Txn Maker", "Split")).equalsIgnoreCase("Yes"))) {
			od.payments_SplitBalanceSlider.click();
		}
		od.payments_NextArrowButtonTransferBasic.click();

	}
}
