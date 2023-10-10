package com.upp.pagemodules.Transactions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Transactions_Maker_Sub_Instruction extends BaseClass {

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
	CommonUtils util;

	public Transactions_Maker_Sub_Instruction() {

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

	public void Non_Registered_Beneficiary(String toaccount) throws InterruptedException {
		int flag = 0;
		By toAccount;
		try {
			toAccount= By.xpath("//span[@class='truncate ng-star-inserted']");
			if (driver.findElement(toAccount).isDisplayed()) {
				flag = 1;
			}
		} catch (Exception e) {
			Thread.sleep(3000);
			toAccount= By.xpath("//span[@class='truncate ng-star-inserted']");
			if (driver.findElement(toAccount).isDisplayed()) {
				flag = 1;
			}
			
		}

		Assert.assertEquals(flag, 1);
		od.payments_NextArrowButtonTransferSubInstruction.click();
	}

	public void Transaction_Maker_Sub_Instruction(String TSID, ICallback icallback) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.transactions_Instrument, Duration.ofSeconds(15));
		tm.transactions_Instrument.click();
		String paymentInstrumentdata = externalData.getFieldData(TSID, "Txn Maker", "Sub Instruction - Instrument");
		System.out.println("Excel data in payment = "+paymentInstrumentdata);
		By paymentInstrument = By.xpath("(//div[contains(text(),'" + paymentInstrumentdata + "')])[1]");
		applyExplicitWaitsUntilElementVisible(paymentInstrument, 3);
		driver.findElement(paymentInstrument).click();
		System.out.println("the to data is " + externalData.getFieldData(TSID, "Txn Maker", "to"));
		icallback.handleCallback("PAYMENT_INSTRUMENT", paymentInstrumentdata);
		if(util.isElementDisplayed(od.deal_Edit_Yes_Button,2))
		 {
			 od.deal_Edit_Yes_Button.click();
		 }
	}

}
