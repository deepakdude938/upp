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
import com.upp.pageobjects.Object_Notification;
import com.upp.pageobjects.Object_Transactions;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import freemarker.template.utility.DateUtil;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import callbackInterfaces.ICallback;

public class Transactions_Maker_Bulkupload extends BaseClass {

	public static Object_NewDeal od;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int rowNum;
	public static OdpApi odpAccount;
	public static AccountDetails accDetails;

	public static JavascriptClick jsClick;
	public static int waitingTime = 4;
	public static DateUtils dateutil;
	public static ScrollTypes scroll;
	public static String productName;
	public static Object_Transactions tm;
	public static Object_Notification noti;

	public Transactions_Maker_Bulkupload() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		tm = new Object_Transactions();
		noti = new Object_Notification();
		jsClick = new JavascriptClick(driver);
	}

	public void bulkUpload(String srcAcc, String desAcc, String time) throws Exception {
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniqueTransactionName = "tran" + random;
		String toAccount1 = "'"+desAcc;
		String toAccount2 = "'"+srcAcc;		
		String srcAccount1 = srcAcc;
		String srcAccount2 = desAcc;	
		String uniqueTransactionName2 = "transaction1" + random;
		String excelFilePath = System.getProperty("user.dir")
				+ "//src//main//resources//Bulk_ConventionalTransactions_File.xlsx";
		externalData.writeDataToExcel(excelFilePath, "Sheet", 1, "transactionName", uniqueTransactionName);
		externalData.writeDataToExcel(excelFilePath, "Sheet", 2, "transactionName", uniqueTransactionName2);
		externalData.writeDataToExcel(excelFilePath, "Sheet", 1, "sourceAccount", srcAccount1);
		externalData.writeDataToExcel(excelFilePath, "Sheet", 2, "sourceAccount", srcAccount2);
		externalData.writeDataToExcel(excelFilePath, "Sheet", 1, "scheduleTime", time);
		externalData.writeDataToExcel(excelFilePath, "Sheet", 2, "scheduleTime", time);
		externalData.writeDataToExcel(excelFilePath, "Sheet", 1, "to", toAccount1);
		externalData.writeDataToExcel(excelFilePath, "Sheet", 2, "to", toAccount2);
		Thread.sleep(3000);
		tm.transactions_TransactionIcon1.click();
		Thread.sleep(5000);
		tm.transactions_TransactionMaker.click();
		Thread.sleep(7000);
		try {
			tm.transactionMaker_dealSearch1.click();
		} catch (Exception e) {
			handleElementClickException(tm.transactionMaker_dealSearch1);
		}
		try {
			tm.transactionMaker_bulk.click();
		} catch (Exception e) {
			handleElementClickException(tm.transactionMaker_bulk);
		}
		
		
		tm.transactionMaker_browseButton.sendKeys(excelFilePath);
		Thread.sleep(2000);
		dropdown.selectByVisibleText(tm.transactionMaker_sheetName, "Sheet");
		tm.transactionMaker_uploadButton.click();
		tm.transactions_Ok.click();
		Thread.sleep(8000);
		driver.navigate().refresh();
		Thread.sleep(8000);
		verifytNotification();
		verifyTransaction();
		Thread.sleep(6000);
	}

	public void bulkUpload_GB_Account(String srcAcc, String desAcc, String time) throws Exception {
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniqueTransactionName = "tran" + random;

		String uniqueTransactionName2 = "transaction1" + random;
		String excelFilePath = System.getProperty("user.dir")
				+ "//src//main//resources//BulkUploadAdhocTnx_GBAccount.xlsx";
//		externalData.writeDataToExcel(excelFilePath, "Sheet", 1, "transactionName", uniqueTransactionName);
		externalData.writeDataToExcel(excelFilePath, "Sheet", 2, "transactionName", uniqueTransactionName2);
		externalData.writeDataToExcel(excelFilePath, "Sheet", 1, "sourceAccount", srcAcc);
//		externalData.writeDataToExcel(excelFilePath, "Sheet", 2, "sourceAccount", desAcc);
		externalData.writeDataToExcel(excelFilePath, "Sheet", 1, "scheduleTime", time);
//		externalData.writeDataToExcel(excelFilePath, "Sheet", 2, "scheduleTime", time);
		Thread.sleep(12000);
		// tm.transactions_TransactionIcon1.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon1, Duration.ofSeconds(120));
		jsClick.click(tm.transactions_TransactionIcon1);
		Thread.sleep(12000);
		// tm.transactions_TransactionMaker.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionMaker, Duration.ofSeconds(120));
		jsClick.click(tm.transactions_TransactionMaker);
		Thread.sleep(15000);
		applyExplicitWaitsUntilElementClickable(tm.transactionMaker_bulk, Duration.ofSeconds(120));
		// tm.transactionMaker_bulk.click();
		try {
			jsClick.click(tm.transactionMaker_bulk);
		} catch (Exception e) {
			handleElementClickException(tm.transactionMaker_bulk);
		}
		Thread.sleep(8000);
		tm.transactionMaker_browseButton.sendKeys(excelFilePath);
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactionMaker_sheetName, Duration.ofSeconds(60));
		dropdown.selectByVisibleText(tm.transactionMaker_sheetName, "Sheet");
		applyExplicitWaitsUntilElementClickable(tm.transactionMaker_uploadButton, Duration.ofSeconds(60));
		tm.transactionMaker_uploadButton.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_Ok, Duration.ofSeconds(60));
		tm.transactions_Ok.click();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		verifytNotification();
		verifyTransaction();
		Thread.sleep(8000);
	}

	public void verifytNotification() {
		int flag = 0;
		noti.notification.click();
		String notification = noti.notification_Message.getText();
		if (notification.contains("processing completed")) {
			flag = 1;
		}
		assertEquals(flag, 1);
		noti.notification_Message.click();
	}

	public void verifytNotificationForChecker() {
		int flag = 0;
		noti.notification.click();
		String notification = noti.notification_Message.getText();
		System.out.println("Notification 2 = " + notification);
		/*
		 * if (notification.contains("processing completed")) { flag = 1; }
		 * assertEquals(flag, 1); noti.notification_Message.click();
		 */
	}

	public void verifyTransaction() throws Exception {
		String validTransactionCount = tm.transactionMaker_validRecord.getText();
		System.out.println(validTransactionCount);
		int count = Integer.parseInt(validTransactionCount);
		if (count > 0) {
			tm.transactionMaker_nextBtn.click();
			Thread.sleep(5000);
			try {
				tm.transactionMaker_allRecord.click();
				tm.transactionMaker_submit.click();
			} catch (Exception e) {
				handleElementClickException(tm.transactionMaker_allRecord);
				handleElementClickException(tm.transactionMaker_submit);
			}

		}
		tm.transactions_Ok.click();
		Thread.sleep(5000);
	}

	public void approveAllTransaction(String dealId) throws Exception {
//		TimeUnit.MINUTES.sleep(5);
//		driver.navigate().refresh();
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon, Duration.ofMinutes(2));
		jsClick.click(tm.transactions_TransactionIcon);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionChecker, Duration.ofMinutes(2));
		jsClick.click(tm.transactions_TransactionChecker);
		Thread.sleep(9000);
		applyExplicitWaitsUntilElementClickable(tm.transactionMaker_dealSearch, Duration.ofMinutes(2));
		tm.transactionMaker_dealSearch.sendKeys(dealId);
		Thread.sleep(7000);
		try {
			jsClick.click(tm.transactionMaker_allRecord);

		} catch (Exception e) {
			Thread.sleep(4000);
			handleElementClickException(tm.transactionMaker_allRecord);
		}
		try {
			Thread.sleep(4000);
			jsClick.click(tm.transactionMaker_message);
		} catch (Exception e3) {
			handleElementClickException(tm.transactionMaker_message);
		}
		Thread.sleep(3000);
		od.dealChecker_addNote.sendKeys("Ok approve");
		Thread.sleep(6000);
		tm.transactionMaker_messageOk.click();
		Thread.sleep(3000);
		handleElementClickException(tm.transactionMaker_submit);
		try {
			jsClick.click(tm.transactionMaker_submit);

		} catch (Exception e) {
			Thread.sleep(4000);
		}
		tm.transactions_YesButton.click();
		tm.transactions_Ok.click();
	}

	public void approveAllTransactionByVerifier(String dealId) throws Exception {
//		TimeUnit.MINUTES.sleep(5);
//		driver.navigate().refresh();
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon, Duration.ofMinutes(2));
		jsClick.click(tm.transactions_TransactionIcon);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionVerifier, Duration.ofMinutes(2));
		jsClick.click(tm.transactions_TransactionVerifier);
		Thread.sleep(8000);
		tm.transactionMaker_dealSearch.sendKeys(dealId);
		Thread.sleep(5000);
		try {
			if (tm.noRows.isDisplayed()) {
				System.out.println("Record will displayed in execution report");
			}
		} catch (Exception ee) {
			try {
				tm.transactionMaker_allRecord.click();
				tm.transactionMaker_message.click();
				od.dealChecker_addNote.sendKeys("Ok approve");
				Thread.sleep(3000);
				tm.transactionMaker_messageOk.click();
				Thread.sleep(3000);
			} catch (Exception e) {
				handleElementClickException(tm.transactionMaker_allRecord);
				handleElementClickException(tm.transactionMaker_message);
				od.dealChecker_addNote.sendKeys("Ok approve");
				Thread.sleep(3000);
				tm.transactionMaker_messageOk.click();
				Thread.sleep(3000);
			}
			tm.transactionMaker_submit.click();
			Thread.sleep(3000);
			tm.transactions_YesButton.click();
			tm.transactions_Ok.click();
		}
	}
}
