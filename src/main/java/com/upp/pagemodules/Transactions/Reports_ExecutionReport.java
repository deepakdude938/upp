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

public class Reports_ExecutionReport extends BaseClass {

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

	public Reports_ExecutionReport() {

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

	public void ExecutionReport(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		if (ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled")) {
			System.out.println("Waiting for Transaction to be triggered");
			TimeUnit.SECONDS.sleep(3);
			String ScroeStatusafter = tm.reports_ScroeStatus.getText();
			if (ScroeStatusafter.equalsIgnoreCase("triggered")) {
				System.out.println("Transaction is triggered");

			} else {
				System.out.println("Transaction is not yet triggered");
			}
		}

	}

	public void eCommExecutionsReport(String EndToEndId, String DealId) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		applyExplicitWaitsUntilElementClickable(tm.reports_searchBox, Duration.ofSeconds(5));
		tm.reports_searchBox.sendKeys("eComm Executions");
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutions, Duration.ofSeconds(6));
		jsClick.click(tm.reports_eCommExecutions);
		applyExplicitWaitsUntilElementClickable(tm.reports_endToendId, Duration.ofSeconds(5));
		tm.reports_endToendId.sendKeys(EndToEndId);

		scroll.scrollHorizontalInsideWindow(tm.reports_horizontalWindow, 1800);

		Thread.sleep(3000);

		By Amount1 = By.xpath("//div[normalize-space()='925']");
		applyExplicitWaitsUntilElementVisible(Amount1, 3);
		String amount1 = driver.findElement(Amount1).getText();
		System.out.println("the amount1 is:" + amount1);

		By Amount2 = By.xpath("//div[normalize-space()='75']");
		applyExplicitWaitsUntilElementVisible(Amount2, 3);
		String amount2 = driver.findElement(Amount2).getText();
		System.out.println("the amount2 is:" + amount2);

		By Status = By.xpath("//div[normalize-space()='Scheduled']");
		applyExplicitWaitsUntilElementVisible(Status, 3);
		String status = driver.findElement(Status).getText();
		System.out.println("the status is:" + status);

		if (!(status.equalsIgnoreCase("Scheduled"))) {
			Assert.fail("Transaction is not scheduled");
		}

		if (!(amount1.equalsIgnoreCase("925"))) {
			Assert.fail("Amount is mismatched");
		}

		if (!(amount2.equalsIgnoreCase("75"))) {
			Assert.fail("Amount is mismatched");
		}
	}

	public void commonmethodExecReport(String TSID, String DealId) throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		applyExplicitWaitsUntilElementClickable(tm.reports_searchBox, Duration.ofSeconds(5));
		tm.reports_searchBox.sendKeys("Execution Report");
		applyExplicitWaitsUntilElementClickable(tm.reports_ExecutionReport, Duration.ofSeconds(6));
		jsClick.click(tm.reports_ExecutionReport);
		applyExplicitWaitsUntilElementClickable(tm.reports_DealId, Duration.ofSeconds(5));
		tm.reports_DealId.sendKeys(DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_SubmitButton, Duration.ofSeconds(5));
		jsClick.click(tm.reports_SubmitButton);
		Thread.sleep(2000);

	}

	public void checkInstructionTypePayment_Retention(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		if (!(ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled"))) {
			Assert.fail("Transaction not scheduled");
		}

		scroll.scrollHorizontalInsideWindow(tm.reports_horizontalWindow, 3800);

		Thread.sleep(1000);

		String instructionType1 = tm.reports_instructiontype_payment.getText();
		String instructionType2 = tm.reports_instructiontype_retention.getText();

		if (!((instructionType1.equalsIgnoreCase("Payment"))|| (instructionType1.equalsIgnoreCase("Retention")))) {
			Assert.fail("it should be Payment instruction type");
		}

		if (!(instructionType2.equalsIgnoreCase("Retention"))) {
			Assert.fail("it should be Retention instruction type");
		}
	}
	
	public void checkStatusTriggeredOrSettled(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
			
			if(!((ScroeStatus.equalsIgnoreCase("Triggered")) || ScroeStatus.equalsIgnoreCase("Settled"))) {
				
                 Assert.fail("The transaction is not triggered or settled");
			} 
		}
	
	public void checkStatusRejected(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
			
			if(!(ScroeStatus.equalsIgnoreCase("Rejected"))) {
				
                 Assert.fail("The transaction is not Rejected");
			} 
		}

	}


