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
	String day;

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
		
		applyExplicitWaitsUntilElementClickable(od.payments_BasicName, Duration.ofSeconds(120));
		od.payments_BasicName.clear();
		od.payments_BasicName.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Name"));
		Thread.sleep(4000);
		dropdown.selectByVisibleText(od.payments_Purpose, externalData.getFieldData(TSID, "Txn Maker", "Purpose"));
		
		String createTxnFrom = externalData.getFieldData(TSID, "Txn Maker", "CreateTxnFrom");
		if(createTxnFrom.equals("Live Deal")) {
			
			System.out.println(sourceAccno);
			od.payments_SourceAccount.sendKeys(sourceAccno);
			Thread.sleep(1000);
			By sourceaccountselect = By.xpath("//div[contains(text(),'" + sourceAccno + "')]");
			driver.findElement(sourceaccountselect).click();
			
		}
		
		dropdown.selectByVisibleText(od.payments_BalanceConsideration, externalData.getFieldData(TSID, "Txn Maker", "Balance Consideration"));
		Thread.sleep(2000);
		if (((externalData.getFieldData(TSID, "Txn Maker", "Split")).equalsIgnoreCase("Y") || (externalData.getFieldData(TSID, "Txn Maker", "Split")).equalsIgnoreCase("Yes"))) {
			od.payments_SplitBalanceSlider.click();
			dropdown.selectByVisibleText(od.payments_SpecifyAmountAs, externalData.getFieldData(TSID, "Txn Maker", "Specify Amount As"));
			od.payments_SpecifyAmountValue.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Amount_Value"));
		}

		if (TSID.startsWith("TS130")) {
			applyExplicitWaitsUntilElementClickable(tm.transactions_ExecuteLater, Duration.ofSeconds(15));
			tm.transactions_ExecuteLater.click();
			applyExplicitWaitsUntilElementClickable(od.payments_ExecutionDate, Duration.ofSeconds(20));
			od.payments_ExecutionDate.click();
			day = dateutil.getDay();
			int day_int = Integer.parseInt(day) + 1;
			day = Integer.toString(day_int);
			By excecutionDay = By.xpath(
					"//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"
							+ day + "']");

			Thread.sleep(1000);
			try {
				driver.findElement(excecutionDay).click();
			} catch (Exception e) {
				if (Integer.parseInt(DateUtils.getDay()) >= 29) {
					excecutionDay = By.xpath("//td[contains(@class,'ui-calendar-outFocus') and normalize-space()='2'] ");
					driver.findElement(excecutionDay).click();
				}
			}
			dropdown.selectByVisibleText(od.payments_HolidayAction,externalData.getFieldData(TSID, "Txn Maker", "Holiday Action"));
			applyExplicitWaitsUntilElementClickable(od.payments_ScheduleAt, Duration.ofSeconds(5));
			dropdown.selectByVisibleText(od.payments_ScheduleAt,externalData.getFieldData(TSID, "Txn Maker", "Schedule At"));
			if (externalData.getFieldData(TSID, "Txn Maker", "Schedule At").trim().equalsIgnoreCase("At specific time")) {

				String time = dateutil.getTimeAfterMins(5);

				od.payments_ScheduleTime.clear();
				od.payments_ScheduleTime.clear();
				od.payments_ScheduleTime.clear();
				od.payments_ScheduleTime.sendKeys(time);
				Thread.sleep(3000);
			}
		}
		
		od.payments_NextArrowButtonTransferBasic.click();
	}

	public void Transactions_Maker_BasicDetails1(String TSID, String DealId, String sourceAccno) throws Exception {
		Thread.sleep(5000);
		By transactions_DealId;
		System.out.println("Deal id in tm" + DealId);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon1, Duration.ofSeconds(15));
		tm.transactions_TransactionIcon1.click();
		Thread.sleep(3000);
		tm.transactions_TransactionMaker.click();
		Thread.sleep(4000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_AddNewButon, Duration.ofSeconds(15));
		jsClick.click(tm.transactions_AddNewButon);
		Thread.sleep(6000);
		try {
			applyExplicitWaitsUntilElementClickable(tm.transactions_DealId1, Duration.ofSeconds(20));
			jsClick.click(tm.transactions_DealId1);
			tm.transactions_DealId.sendKeys(DealId);
			transactions_DealId = By.xpath("//div[contains(text(),'" + DealId + "')]");
			driver.findElement(transactions_DealId).click();
			System.out.println("Step1");
			jsClick.click(tm.transactions_SourceAccNo1);
			tm.transactions_SourceAccNo1.sendKeys(sourceAccno);
			By transactions_SouceAccno = By.xpath("//div[contains(text(),'" + sourceAccno + "')]");
			Thread.sleep(3000);
			driver.findElement(transactions_SouceAccno).click();
			System.out.println("Step2");
		} catch (Exception e) {
			tm.transactions_TransactionMaker.click();
			Thread.sleep(2000);
			applyExplicitWaitsUntilElementClickable(tm.transactions_AddNewButon, Duration.ofSeconds(15));
			jsClick.click(tm.transactions_AddNewButon);
			System.out.println("Step1");
			applyExplicitWaitsUntilElementClickable(tm.transactions_DealId, Duration.ofSeconds(20));
			tm.transactions_DealId.sendKeys(DealId);
			transactions_DealId = By.xpath("//div[contains(text(),'" + DealId + "')]");
			driver.findElement(transactions_DealId).click();
			System.out.println("Step1");
			jsClick.click(tm.transactions_SourceAccNo1);
			tm.transactions_SourceAccNo1.sendKeys(sourceAccno);
			By transactions_SouceAccno = By.xpath("//div[contains(text(),'" + sourceAccno + "')]");
			Thread.sleep(3000);
			driver.findElement(transactions_SouceAccno).click();
		}
		jsClick.click(tm.transactions_SubmitButton);
		od.payments_BasicName.clear();
		od.payments_BasicName.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Name"));
		dropdown.selectByVisibleText(od.payments_Purpose, externalData.getFieldData(TSID, "Txn Maker", "Purpose"));
		dropdown.selectByVisibleText(od.payments_BalanceConsideration,
				externalData.getFieldData(TSID, "Txn Maker", "Balance Consideration"));
		Thread.sleep(2000);
		if (((externalData.getFieldData(TSID, "Txn Maker", "Split")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Txn Maker", "Split")).equalsIgnoreCase("Yes"))) {
			od.payments_SplitBalanceSlider.click();
		}
		od.payments_NextArrowButtonTransferBasic.click();

	}
	
	public void createNewAdhocTransaction(String TSID, String DealId, String sourceAccno) throws Exception {
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon, Duration.ofSeconds(120));
		click(tm.transactions_TransactionIcon);
		Thread.sleep(13000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionMaker, Duration.ofSeconds(120));
		click(tm.transactions_TransactionMaker);
		Thread.sleep(12000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_AddNewButon, Duration.ofSeconds(120));
		jsClick.click(tm.transactions_AddNewButon);
		Thread.sleep(8000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_DealId, Duration.ofSeconds(120));
		tm.transactions_DealId.sendKeys(DealId);
		Thread.sleep(3000);
		By transactions_DealId = By.xpath("//div[contains(text(),'" + DealId + "')]");
		driver.findElement(transactions_DealId).click();
		Thread.sleep(3000);
		tm.transactions_SourceAccNo.sendKeys(sourceAccno);
		By transactions_SouceAccno = By.xpath("//div[contains(text(),'" + sourceAccno + "')]");
		driver.findElement(transactions_SouceAccno).click();

		jsClick.click(tm.transactions_SubmitButton);

	}
}
