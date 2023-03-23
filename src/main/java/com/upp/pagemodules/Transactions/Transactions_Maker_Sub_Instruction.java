package com.upp.pagemodules.Transactions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

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

	}

	public void Transaction_Maker_Sub_Instruction(String TSID, ICallback icallback) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.transactions_instruments, Duration.ofSeconds(20));
		tm.transactions_instruments.click();
		String paymentInstrumentdata = externalData.getFieldData(TSID, "Txn Maker", "Sub Instruction - Instrument");
		By paymentInstrument = By.xpath("(//div[contains(text(),'" + paymentInstrumentdata + "')])[1]");
		applyExplicitWaitsUntilElementVisible(paymentInstrument, 3);
		driver.findElement(paymentInstrument).click();
		// tm.transactions_Instrument.click();
		System.out.println("the to data is " + externalData.getFieldData(TSID, "Txn Maker", "to"));
		icallback.handleCallback("PAYMENT_INSTRUMENT", paymentInstrumentdata);
	}

	public void Non_Registered_Beneficiary(String toaccount) {
		int flag = 0;
		By toAccount = By.xpath("//span[contains(text(),'" + toaccount + "')]");

		if (driver.findElement(toAccount).isDisplayed()) {
			flag=1;
		}
		Assert.assertEquals(flag,1);
		od.payments_NextArrowButtonTransferSubInstruction.click();
	}
	

}
