package com.upp.pagemodules.Transactions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.upp.PaymentRulesApi.SSHConnection;
import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Transactions;
import com.upp.stepdefinition.DealPage;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
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
    public static int totalRecord;
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

	public static void checkSubInstructionTypeInExecutionReport(String payment, String surplus) throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		applyExplicitWaitsUntilElementClickable(tm.reports_ExecutionReport, Duration.ofSeconds(6));
		jsClick.click(tm.reports_ExecutionReport);
		applyExplicitWaitsUntilElementClickable(tm.reports_DealId, Duration.ofSeconds(5));
		tm.reports_DealId.sendKeys(dealId);
		Thread.sleep(4000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SubInstructionType, tm.reports_horizontalWindow1,
				10, 1000);
		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
			Thread.sleep(1000);
		}
		Assert.assertTrue(subInstruction.contains(payment));
		Assert.assertTrue(subInstruction.contains(surplus));
	}

	public void ExecutionReport(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		try {
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatus, tm.reports_horizontalWindow1, 9, -1000);
		}
		catch(Exception e) {
		}
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(40));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		
		if(TSID.equals("TS110") || TSID.equals("TS79")) {
			
			if (ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled") || ScroeStatus.equalsIgnoreCase("Settled")) {
				// System.out.println("Waiting for Transaction to be triggered");
				TimeUnit.SECONDS.sleep(3);
				String ScroeStatusafter = tm.reports_ScroeStatus.getText();
				if (ScroeStatusafter.equalsIgnoreCase("triggered")) {
					// System.out.println("Transaction is triggered");

				} else {
					// System.out.println("Transaction is not yet triggered");
				}
			}
			else {
				Assert.assertFalse(true,"Scroe status didnt found");
			}
		}
		else {
		
		if (ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled")) {
			// System.out.println("Waiting for Transaction to be triggered");
			TimeUnit.SECONDS.sleep(3);
			String ScroeStatusafter = tm.reports_ScroeStatus.getText();
			if (ScroeStatusafter.equalsIgnoreCase("triggered")) {
				// System.out.println("Transaction is triggered");

			} else {
				// System.out.println("Transaction is not yet triggered");
			}
		}
		else {
			Assert.assertFalse(true,"Scroe status didnt found");
		}
		}
	}

	public void Verify_Status_And_Amount_eCommExecutionsReport(String EndToEndId) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
//		applyExplicitWaitsUntilElementClickable(tm.reports_searchBox, Duration.ofSeconds(5));
//		tm.reports_searchBox.sendKeys("eComm Executions");
		Thread.sleep(2000);
		scroll.scrollInToView(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(6));
		jsClick.click(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_End_To_End_common, Duration.ofSeconds(5));
		Thread.sleep(3000);
		System.out.println("EndtoEndId" + EndToEndId);
		tm.reports_End_To_End_common.sendKeys(EndToEndId);
		applyExplicitWaitsUntilElementClickable(tm.reports_FirstTxnStatus, Duration.ofSeconds(40));
		String status = tm.reports_FirstTxnStatus.getText();
		System.out.println("The status is :" + status);
		Assert.assertEquals(status, "Scheduled");
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_Amount, tm.reports_horizontalWindow1, 9, 1000);
		Thread.sleep(3000);
		By Amount1 = By.xpath("//div[normalize-space()='400']");
		applyExplicitWaitsUntilElementVisible(Amount1, 3);
		String amount1 = driver.findElement(Amount1).getText();
		System.out.println("the amount1 is:" + amount1);

		By Amount2 = By.xpath("//div[normalize-space()='600']");
		applyExplicitWaitsUntilElementVisible(Amount2, 3);
		String amount2 = driver.findElement(Amount2).getText();
		System.out.println("the amount2 is:" + amount2);

		if (!(amount1.equalsIgnoreCase("400"))) {
			Assert.fail("Amount is mismatched");
		}

		if (!(amount2.equalsIgnoreCase("600"))) {
			Assert.fail("Amount is mismatched");
		}
	}

	public void commonmethodExecReport(String TSID, String DealId) throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		applyExplicitWaitsUntilElementClickable(tm.reports_ExecutionReport, Duration.ofSeconds(10));
		jsClick.click(tm.reports_ExecutionReport);
		applyExplicitWaitsUntilElementClickable(tm.reports_DealId, Duration.ofSeconds(40));
		tm.reports_DealId.sendKeys(dealId);
//		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
//		Thread.sleep(6500);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 9, 1000);
		jsClick.click(tm.cancelIcon);
		try {
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatusRecordFirst, Duration.ofSeconds(30));
		}
		catch(Exception e) {
		}

	}

	public void checkInstructionTypePayment_Retention(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		if (!(ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled"))) {
			Assert.fail("Transaction not scheduled");
		}

		// scroll.scrollHorizontalInsideWindow(tm.reports_horizontalWindow, 3800);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SubInstructions_Type,
				tm.reports_horizontalWindow1, 10, 1000);
		Thread.sleep(1000);
		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
		}
		Assert.assertTrue(subInstruction.contains("Payment"));
		Assert.assertTrue(subInstruction.contains("Retention"));
	}
	
	public void checkInstructionTypeRetention_Surplus(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(20));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		if (!(ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled"))) {
			Assert.fail("Transaction not scheduled");
		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SubInstructions_Type,
				tm.reports_horizontalWindow1, 10, 1000);
		Thread.sleep(1000);
		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
		}
		Assert.assertTrue(subInstruction.contains("Surplus"));
		Assert.assertTrue(subInstruction.contains("Retention"));
	}

	public void eCommExecutionsReportToCheckTransactionStatus(String EndToEndId, String DealId) throws Exception {
		int flag = 0;
		System.out.println("Waiting for 3 minutes for Transactions to be triggered");
		TimeUnit.MINUTES.sleep(3);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		// applyExplicitWaitsUntilElementClickable(tm.reports_searchBox,
		// Duration.ofSeconds(5));
		// tm.reports_searchBox.sendKeys("eComm Executions");
		Thread.sleep(2000);
		scroll.scrollInToView(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(6));
		jsClick.click(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_endToendId, Duration.ofSeconds(5));
		Thread.sleep(3000);
		tm.reports_endToendId.sendKeys(EndToEndId);
		System.out.println("Added end to end id");
		Thread.sleep(3000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, 1000);
		jsClick.click(tm.cancelIcon);
		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_FirstTxnStatus, tm.reports_horizontalWindow1, 10,
				-1000);
		Thread.sleep(1000);
		String txn1 = tm.reports_FirstTxnStatus.getText();
		String txn2 = tm.reports_SecondTxnStatus.getText();
		System.out.println("Test1" + txn1);
		System.out.println("Test2" + txn2);
		if (txn1.equalsIgnoreCase("HOLD") || txn1.equalsIgnoreCase("Scheduled")) {
			System.out.println("Test1");
			if (txn2.equalsIgnoreCase("HOLD") || txn2.equalsIgnoreCase("Scheduled")) {
				System.out.println("Test2");
				flag = 1;
			}
		}
		Assert.assertEquals(flag, 1);
		// Assert.assertEquals(txn2, "Scheduled");
	}

	public void checkBothTransactionStatusIsScheduled(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,tm.reports_horizontalWindow1, 8, 1000);
		try {
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		}
		catch(Exception c) {
			Thread.sleep(5000);
			applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		}
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);

		if (!((ScroeStatus.equalsIgnoreCase("Scheduled")) && (ScroeStatus2ndrow.equalsIgnoreCase("Scheduled")))) {
			Assert.fail("The transaction is not Scheduled");
		}
	}
	
	public void checkBothTransactionStatusIsScheduledOrSettled(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, dealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,tm.reports_horizontalWindow1, 8, 1000);
		try {
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		}
		catch(Exception c) {
			Thread.sleep(5000);
			applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		}
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);

	if(!(ScroeStatus.equalsIgnoreCase("Scheduled") || ScroeStatus.equalsIgnoreCase("Settled"))) {
		Assert.fail("Scroe status is niether Scheduled nor Settled");
	}
	if(!(ScroeStatus2ndrow.equalsIgnoreCase("Scheduled") || ScroeStatus.equalsIgnoreCase("Settled"))) {
		Assert.fail("Scroe status is niether Scheduled nor Settled");
	}
	}
	
	public void checkBothTransactionStatusIsSettled(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, dealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);

		if (!((ScroeStatus.equalsIgnoreCase("Settled")) && (ScroeStatus2ndrow.equalsIgnoreCase("Settled")))) {
			Assert.fail("The transaction is not Settled");
		}
	}

	public void checkStatus_AwaitingForDependant(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus2ndRow, Duration.ofSeconds(30));
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);

		if (!(ScroeStatus2ndrow.equalsIgnoreCase("Awaiting for Dependant"))) {

			Assert.fail("The transaction status should be Awaiting for Dependant ");
		}

	}

	public void checkSubInstructionTypeInExecutionReport(String payment, String surplus, String retention, String tsid)
			throws Exception {
		
		commonmethodExecReport(tsid, dealId);

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SubInstructionType, tm.reports_horizontalWindow1,
				10, 1000);
		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
		}

		Assert.assertTrue(subInstruction.contains(payment));
		Assert.assertTrue(subInstruction.contains(surplus));
		Assert.assertTrue(subInstruction.contains(retention));
	}

	public void checkStatusAndInstructionType(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String status = tm.reports_ScroeStatus.getText();

		if (!(status.equalsIgnoreCase("Scheduled"))) {

			Assert.fail("Transaction not scheduled");

		}

		// scroll.scrollHorizontalInsideWindow(tm.reports_horizontalWindow, 4000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_InstructionName, tm.reports_horizontalWindow1, 5,
				2000);

		String instructionname = tm.reports_InstructionName.getText();

		System.out.println(instructionname);
		Assert.assertTrue(instructionname.equalsIgnoreCase("Alert"));
	}

	public void checkStatusAndInstructionTypeAsBalanceReport(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String status = tm.reports_ScroeStatus.getText();

		if (!(status.equalsIgnoreCase("Scheduled"))) {
			Assert.fail("Transaction not scheduled");
		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_InstructionName, tm.reports_horizontalWindow1,
				10, 1000);
		String instructionname = tm.reports_InstructionName.getText();

		System.out.println(instructionname);
		Assert.assertTrue(instructionname.equalsIgnoreCase("Balance Reporting"));

	}

	public void ExecutionReportForBulkUpload(String TSID, String DealId, String times) throws Exception {

		int flag = 0;
		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(tm.reports_RecordStatus.size());
		// for (WebElement record : tm.reports_RecordStatus) {
		if (ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled")) {
			TimeUnit.MINUTES.sleep(3);
			driver.navigate().refresh();
			applyExplicitWaitsUntilElementClickable(tm.reports_DealId, Duration.ofSeconds(5));
			tm.reports_DealId.sendKeys(DealId);
			Thread.sleep(3000);
			String ScroeStatusafter = tm.reports_ScroeStatus.getText();
			if (ScroeStatusafter.equalsIgnoreCase("Settled")) {
				flag = 1;

			} else {
				flag = 0;
			}
			Assert.assertEquals(flag, 1);
		}

	}

	public void validateEcommTransaction() throws Exception {

		commonEcommExecution();
		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_dealIDText, tm.reports_horizontalWindow1, 10,
				-1000);

		Thread.sleep(1000);
		applyExplicitWaitsUntilElementVisible(tm.reports_EcommRecordStatus, Duration.ofSeconds(10));
		for (WebElement record : tm.reports_EcommRecordStatus) {

			Assert.assertEquals(record.getText(), "Scheduled");

		}
	}
	
	public void validateEcommTransactionScheduledOrTriggered() throws Exception {

		commonEcommExecution();
		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_dealIDText, tm.reports_horizontalWindow1, 10,
				-1000);

		Thread.sleep(1000);
		applyExplicitWaitsUntilElementVisible(tm.reports_EcommRecordStatus, Duration.ofSeconds(10));
		String ecomStatus = tm.reports_EcommRecordStatus1.getText();
		System.out.println(ecomStatus);
		if(ecomStatus.equals("Scheduled")  || ecomStatus.equals("Triggered") ) {
		}
		else {
			Assert.fail("Status is not triggered or scheduled");
		}

	}
	
	private void commonEcommExecution() throws MalformedURLException, InterruptedException {
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		tm.reports_ReportsIcon.click();
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		tm.reports_ReportsInternal.click();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(6));
		tm.reports_eCommExecutionsList.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_dealId1, Duration.ofSeconds(5));
		System.out.println(dealId);
		tm.reports_dealId1.sendKeys(dealId);
		Thread.sleep(2000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, 1000);
		jsClick.click(tm.cancelIcon);

	}

	public void validateEcommTransactionwithDealId(String deal) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		Thread.sleep(2000);
		scroll.scrollInToView(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(6));
		jsClick.click(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_dealId1, Duration.ofSeconds(5));
		System.out.println("Deal in roprt= " + deal);
		tm.reports_dealId1.sendKeys(deal);
		Thread.sleep(2000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, 1000);
		jsClick.click(tm.cancelIcon);
		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, -1000);
		applyExplicitWaitsUntilElementVisible(tm.reports_EcommRecordStatus, Duration.ofSeconds(20));
		String record = tm.reports_EcommRecordStatus.get(0).getText();
		System.out.println("Record = " + record);

	}

	public void validateSplitFixedAmountInExecutionReport(String TSID) throws Exception {
		commonmethodExecReport(TSID, dealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_OriginalAmountColoumnName,
				tm.reports_horizontalWindow1, 10, 1000);
		Thread.sleep(1000);
		String originalAmount = tm.reports_OriginalAmountValue.getText();
		String expectedOriginalAmount = (int) Float.parseFloat(externalData.getFieldData(TSID, "Scheduled", "value"))
				+ "";
		if (TSID.equals("TS82")) {
			expectedOriginalAmount = "600";
		}
		Assert.assertEquals(originalAmount, expectedOriginalAmount);
	}

	public void check_Original_amount_and_Trnasferinfo_as_percentage(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String amount1 = externalData.getFieldData(TSID, "Scheduled", "Amount");
		float amount = Float.parseFloat(amount1);
		int amount_int = (int) amount;
		int amount2 = 100 - amount_int;
		String strNumberamount2 = Integer.toString(amount2);
		String strNumberamount1 = Integer.toString(amount_int);

		System.out.println("amount1 :" + strNumberamount1);
		System.out.println("amount2 :" + strNumberamount2);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String status = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();

		if (!(status.equalsIgnoreCase("Scheduled"))) {
			Assert.fail("Transaction should be scheduled");
		}

		if (!(ScroeStatus2ndrow.equalsIgnoreCase("Scheduled"))) {
			Assert.fail("Transaction should be scheduled");
		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_OriginalAmountColumn,
				tm.reports_horizontalWindow1, 8, 1000);

		Thread.sleep(500);

		ArrayList<String> originalAmount = new ArrayList();
		for (WebElement iu : tm.reports_OriginalAmount) {

			originalAmount.add(iu.getText());
		}
		Assert.assertTrue(originalAmount.contains(strNumberamount1));
		Assert.assertTrue(originalAmount.contains(strNumberamount2));
	}

	public String eCommExecutionsReportCommon(String EndToEndId) throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		Thread.sleep(2000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_eCommExecutionsList,
				tm.reports_horizontalWindow1, 8, 1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(30));
		jsClick.click(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_End_To_End_common, Duration.ofSeconds(5));
		Thread.sleep(3000);
		System.out.println("Test = " + EndToEndId);
		tm.reports_End_To_End_common.sendKeys(EndToEndId);
		Thread.sleep(9000);
		String status = tm.reports_FirstTxnStatus.getText();
		System.out.println("The status is:" + status);
		String paymentRefId = tm.ecommPaymentLink.getText();
		System.out.println("The Payment Ref id:" + paymentRefId);
		return paymentRefId;

	}

	public String getBatchIdFromEcommPayments(String paymentRefId) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		Thread.sleep(2000);
		scroll.scrollInToView(tm.reports_eCommPaymentsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommPaymentsList, Duration.ofSeconds(6));
		jsClick.click(tm.reports_eCommPaymentsList);
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementVisible(tm.ecommPayments_PaymentId, Duration.ofSeconds(10));
		tm.ecommPayments_PaymentId.sendKeys(paymentRefId);
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementVisible(tm.ecommBatch, Duration.ofSeconds(10));
		String batchId = tm.ecommBatch.getText();
		System.out.println("The batch id is:" + batchId);

		return batchId;
	}

	public void validateInExecutionReport(String TSID) throws Exception {

		commonmethodExecReport(TSID, dealId);
		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		ArrayList<String> scroeStatus = new ArrayList();
		for (WebElement iu : tm.reports_ScroeStatusRecords) {

			scroeStatus.add(iu.getText().trim());
			Assert.assertEquals(iu.getText().trim(), "Scheduled");
		}
		Assert.assertEquals(scroeStatus.size(), 2);

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SubInstructionType, tm.reports_horizontalWindow1,
				10, 1000);
		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
			System.out.println(iu.getText());
		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_OriginalAmountColumnName,
				tm.reports_horizontalWindow1, 10, 1000);
		try {
			System.out.println(tm.reports_OriginalAmountColumnName.isDisplayed());
		} catch (Exception e) {
			System.out.println("reversing scrolling");
			ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_OriginalAmountColumnName,
					tm.reports_horizontalWindow1, 10, -1000);
		}
		ArrayList<String> originalAmount = new ArrayList();
		for (WebElement iu : tm.reports_OriginalAmountRecords) {

			originalAmount.add(iu.getText());
			System.out.println(iu.getText());
		}

		LinkedHashMap<String, String> expectedOriginalAmount = new LinkedHashMap();
		expectedOriginalAmount.put("Payment", "100");
		expectedOriginalAmount.put("Retention", "50000");

		LinkedHashMap<String, String> actualOriginalAmount = new LinkedHashMap();
		actualOriginalAmount.put(subInstruction.get(0), originalAmount.get(0));
		actualOriginalAmount.put(subInstruction.get(1), originalAmount.get(1));

		boolean t = actualOriginalAmount.equals(expectedOriginalAmount);
		Assert.assertTrue(t);

	}

	public void eCommExecutionsReport_Status(String EndToEndId) throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		Thread.sleep(2000);
		scroll.scrollInToView(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(6));
		jsClick.click(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_End_To_End_common, Duration.ofSeconds(5));
		Thread.sleep(3000);
		tm.reports_End_To_End_common.sendKeys(EndToEndId);
		Thread.sleep(3000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, 1000);
		jsClick.click(tm.cancelIcon);

		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_dealIDText, tm.reports_horizontalWindow1, 10,
				-1000);
		Thread.sleep(2000);
		String status = tm.reports_FirstTxnStatus.getText();
		System.out.println("The status is:" + status);
		Assert.assertEquals(status, "Scheduled");

	}

	public void validateassertionInExecutionReport(String TSID) throws Exception {
		commonmethodExecReport(TSID, dealId);
		System.out.println(dealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		ArrayList<String> scroeStatus = new ArrayList();
		for (WebElement iu : tm.reports_ScroeStatusRecords) {

			scroeStatus.add(iu.getText().trim());
			Assert.assertEquals(iu.getText().trim(), "Settled");
		}
		Assert.assertEquals(scroeStatus.size(), 2);
		
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SubInstructionType, tm.reports_horizontalWindow1,
				10, 1000);
		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
			System.out.println(iu.getText());
		}


		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SettledAmountColumnName,
				tm.reports_horizontalWindow1, 10, 1000);
		ArrayList<String> settledAmount = new ArrayList();
		for (WebElement iu : tm.reports_SettledAmountRecords) {

			settledAmount.add(iu.getText());
			System.out.println(iu.getText());
		}

		
		LinkedHashMap<String, String> expectedSettledAmount = new LinkedHashMap();
		expectedSettledAmount.put("Payment", "39000.12");
		expectedSettledAmount.put("Retention", "50000");

		LinkedHashMap<String, String> actualSettledAmount = new LinkedHashMap();
		actualSettledAmount.put(subInstruction.get(0), settledAmount.get(0));
		actualSettledAmount.put(subInstruction.get(1), settledAmount.get(1));

		boolean t = expectedSettledAmount.equals(actualSettledAmount);
		Assert.assertTrue(t);

	}

	public void validateScheduledStatusforRecord(String TSID) throws Exception {
		commonmethodExecReport(TSID, dealId);

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		try {
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(40));
		}
		catch(Exception e) {
		}
		ArrayList<String> scroeStatus = new ArrayList();
		System.out.println(tm.reports_ScroeStatusRecords.size());
		for (WebElement iu : tm.reports_ScroeStatusRecords) {
			System.out.println(iu.getText().trim());
			scroeStatus.add(iu.getText().trim());
			Assert.assertEquals(iu.getText().trim(), "Scheduled");
		}
	}

	public void verify_Rule_IN_LT_PenddingStatus(String paymentRefId) throws Exception {
		System.out.println("Inside ecomm reprot");
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		Thread.sleep(2000);
		scroll.scrollInToView(tm.reports_eCommPaymentsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommPaymentsList, Duration.ofSeconds(6));
		jsClick.click(tm.reports_eCommPaymentsList);
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementVisible(tm.ecommPayments_PaymentId, Duration.ofSeconds(10));
		tm.ecommPayments_PaymentId.sendKeys(paymentRefId);
		Thread.sleep(3000);
		String ActualResult = tm.ecommPaymentStatus.getText();
		String ExcpectedResult = "Pending";
		Assert.assertEquals(ActualResult, ExcpectedResult);

	}

	public void check_Triggered_or_Settled_Status(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		try {
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(40));
		}
		catch(Exception e) {
		}
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);

		if ((ScroeStatus.equalsIgnoreCase("Triggered")) || (ScroeStatus.equalsIgnoreCase("Settled"))) {

			System.out.println("Transaction succesfully settled or triggered");
		} else {
			Assert.fail("Transaction Staus should be either settled or triggered");
		}

	}

	public void check_one_Tnx_settled_and_second_Tnx_rejected(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, dealId);
		System.out.println(dealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		try {
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		}
		catch(Exception e) {
		}
		ArrayList<String> scroeStatus = new ArrayList();
		for (WebElement iu : tm.reports_ScroeStatusRecords) {
			scroeStatus.add(iu.getText().trim());
		}
		Assert.assertTrue(scroeStatus.contains("Rejected"));
		Assert.assertTrue(scroeStatus.contains("Settled"));

	}
	
	public void check_one_Tnx_scheduled_and_second_Tnx_Cancelled(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		System.out.println(DealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		ArrayList<String> scroeStatus = new ArrayList();
		for (WebElement iu : tm.reports_ScroeStatusRecords) {
			scroeStatus.add(iu.getText().trim());
		}
		Assert.assertTrue(scroeStatus.contains("Scheduled"));
		Assert.assertTrue(scroeStatus.contains("Cancelled"));

	}
	
	public void check_one_Tnx_Rejected_and_second_Tnx_Rescheduled(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		System.out.println(DealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		ArrayList<String> scroeStatus = new ArrayList();
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		for (WebElement iu : tm.reports_ScroeStatusRecords) {
			scroeStatus.add(iu.getText().trim());
		}
		Assert.assertTrue(scroeStatus.contains("Rescheduled"));
		Assert.assertTrue(scroeStatus.contains("Rejected"));

	}
	
	public void check_one_Tnx_Rejected_and_second_Tnx_Rescheduled_and_RescheduledDate(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		System.out.println(DealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		ArrayList<String> scroeStatus = new ArrayList();
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		for (WebElement iu : tm.reports_ScroeStatusRecords) {
			scroeStatus.add(iu.getText().trim());
		}
		Assert.assertTrue(scroeStatus.contains("Rescheduled"));
		Assert.assertTrue(scroeStatus.contains("Rejected"));
		
		String rescheduleddate=	tm.reports_RescheduledDate.getText();
		String date[]=rescheduleddate.split(" ");
		String rescheduleddate1=date[0];
		String ExcpectedRescheduledDate=dateutil.getCurrentDate_HashFormat();
		Assert.assertEquals(rescheduleddate1, ExcpectedRescheduledDate);

	}

	public void check_one_Tnx_Rejected_and_second_Tnx_Rescheduled_and_RescheduledDate_SameDay(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		System.out.println(DealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		ArrayList<String> scroeStatus = new ArrayList();
		for (WebElement iu : tm.reports_ScroeStatusRecords) {
			scroeStatus.add(iu.getText().trim());
		}
		Assert.assertTrue(scroeStatus.contains("Rescheduled"));
		Assert.assertTrue(scroeStatus.contains("Rejected"));
		
		String rescheduleddate=	tm.reports_RescheduledDate.getText();
		String date[]=rescheduleddate.split(" ");
		String rescheduleddate1=date[0];
		String ExcpectedRescheduledDate=dateutil.getCurrentDate_HashFormatToday();
		Assert.assertEquals(rescheduleddate1, ExcpectedRescheduledDate);

	}


	public void ExecutionReportAwaitingTransaction(String TSID, String DealId) throws Exception {
		String timem = dateTime.getTimeAfterMins(waitingTime);
//		long wait = Long.parseLong(timem);
		TimeUnit.MINUTES.sleep(8);
		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		Assert.assertEquals(ScroeStatus, "Awaiting for Dependant");
	}

	public void ExecutionReportForSchedule(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		Assert.assertEquals(ScroeStatus, "Scheduled");

	}

	public void Check_Both_Tnx_Rejected_Verify_Error_Message(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);

		if (!((ScroeStatus.equalsIgnoreCase("Rejected")) && (ScroeStatus2ndrow.equalsIgnoreCase("Rejected")))) {

			Assert.fail("The transaction should be rejected");
		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.Reports_Errors, tm.reports_horizontalWindow1, 10, 1000);

		By ErrorMessageA = By.xpath(
				"//div[starts-with(text(),'amount 400000.00 to be transferred is not available in source account ')]");
		if (!(driver.findElement(ErrorMessageA).isDisplayed())) {

			Assert.fail("Wrong Error Message displayed");
		}

		By ErrorMessageB = By.xpath("//div[contains(text(),'Rejected due to dependent instruction')]");
		if (!(driver.findElement(ErrorMessageB).isDisplayed())) {

			Assert.fail("Wrong Error Message displayed");
		}

	}

	public void check_Original_amount_addupto_100_percentage(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String amount1 = externalData.getFieldData(TSID, "Scheduled", "Amount");
		float amount = Float.parseFloat(amount1);
		int amount_int = (int) amount;
		int amount2 = 100 - amount_int;
		String strNumberamount2 = Integer.toString(amount2);
		String strNumberamount1 = Integer.toString(amount_int);

		System.out.println("amount1 :" + strNumberamount1);
		System.out.println("amount2 :" + strNumberamount2);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String status = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();

		if (!((status.equalsIgnoreCase("Settled")) || (status.equalsIgnoreCase("Triggered")))) {

			Assert.fail("Transaction should be scheduled or Settled Or Triggered");

		}
		if (!((ScroeStatus2ndrow.equalsIgnoreCase("Settled")) || (ScroeStatus2ndrow.equalsIgnoreCase("Triggered")))) {

			Assert.fail("Transaction should be scheduled or Settled Or Triggered");

		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_OriginalAmountColumn,
				tm.reports_horizontalWindow1, 8, 1000);

		Thread.sleep(500);

		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_OriginalAmount) {

			subInstruction.add(iu.getText());
		}
		Assert.assertTrue(subInstruction.contains(strNumberamount1));
		Assert.assertTrue(subInstruction.contains(strNumberamount2));
	}

	public void validateTriggeredOrSetteledForMultipleRecords(String TSID) throws Exception {
		commonmethodExecReport(TSID, dealId);

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		System.out.println(tm.reports_ScroeStatusRecords.size() + " Records");
		for (WebElement iu : tm.reports_ScroeStatusRecords) {
			System.out.println(iu.getText().trim());
			Assert.assertEquals(iu.getText().trim(), "Settled");
		}

	}

	public void validateSplitFixedAmounts(String TSID) throws Exception {
		commonmethodExecReport(TSID, dealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
				tm.reports_horizontalWindow1, 8, 1000);
		ArrayList<String> scroeStatus = new ArrayList();
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(40));
		for (WebElement iu : tm.reports_ScroeStatusRecords) {

			scroeStatus.add(iu.getText().trim());
			Assert.assertEquals(iu.getText().trim(), "Scheduled");
		}
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_OriginalAmountColumn,
				tm.reports_horizontalWindow1, 8, 1000);

		Thread.sleep(500);

		ArrayList<String> originalAmount = new ArrayList();
		for (WebElement iu : tm.reports_OriginalAmount) {

			originalAmount.add(iu.getText());
			System.out.println(iu.getText());
		}
		Assert.assertTrue(originalAmount.contains("200"));
		Assert.assertTrue(originalAmount.contains("300"));
	}

	public void checkRecordIsNotPresentINEcommExecution(String endToEndId) throws Exception {

		driver.navigate().refresh();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.reports_End_To_End_common, Duration.ofSeconds(5));
		Thread.sleep(3000);
		tm.reports_End_To_End_common.sendKeys(endToEndId);
		Thread.sleep(3000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, 1000);
		jsClick.click(tm.cancelIcon);
//			jsClick.click(tm.cancelIcon);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_dealIDText, tm.reports_horizontalWindow1, 10,
				-1000);
		List<WebElement> recordList = driver.findElements(By.xpath(
				"//div[@col-id='End to End ID' and contains(@class,'ag-cell-not-inline-editing') and normalize-space()='"
						+ endToEndId + "']"));
		Assert.assertTrue(recordList.size() == 0);
	}

	public void verifyTriggeredStatusInExecutionReport(String tSID) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		tm.reports_ReportsIcon.click();
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		tm.reports_ReportsInternal.click();
//		applyExplicitWaitsUntilElementClickable(tm.reports_searchBox, Duration.ofSeconds(5));
//		tm.reports_searchBox.sendKeys("eComm Executions");
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(6));
		tm.reports_eCommExecutionsList.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_dealId1, Duration.ofSeconds(5));
		System.out.println(dealId);
		tm.reports_dealId1.sendKeys(dealId);
		Thread.sleep(2000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, 1000);
		jsClick.click(tm.cancelIcon);

		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_dealIDText, tm.reports_horizontalWindow1, 10,
				-1000);

		Thread.sleep(1000);
		applyExplicitWaitsUntilElementVisible(tm.reports_EcommRecordStatus, Duration.ofSeconds(10));
		for (WebElement record : tm.reports_EcommRecordStatus) {

			Assert.assertEquals(record.getText(), "Triggered");

		}

	}

	public void Verify_Source_AccNo_In_eCommExecutionsReport(String EndToEndId) throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		Thread.sleep(2000);
		scroll.scrollInToView(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(6));
		jsClick.click(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_End_To_End_common, Duration.ofSeconds(5));
		Thread.sleep(3000);
		System.out.println("Test = " + EndToEndId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 9, 1000);
		jsClick.click(tm.cancelIcon);
		tm.reports_End_To_End_common.sendKeys(EndToEndId);
		Thread.sleep(4000);
		applyExplicitWaitsUntilElementClickable(tm.reports_FirstTxnStatus, Duration.ofSeconds(30));
		String status = tm.reports_FirstTxnStatus.getText();
		System.out.println("The status is:" + status);

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.Reports_Source_Acc_No_Col, tm.reports_horizontalWindow1,
				10, 1000);
		try {
		applyExplicitWaitsUntilElementVisible(tm.Reports_Source_Acc_No_First, Duration.ofSeconds(10));
	}
	catch(Exception e) {
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.Reports_Source_Acc_No_Col, tm.reports_horizontalWindow1,
				10, -1000);
	}
		
		String accno = tm.Reports_Source_Acc_No_First.getText();
		System.out.println("The Account Number is:" + accno);
		System.out.println("The Deal Page Account Number is:" + DealPage.AccountNo1);
		Assert.assertEquals(accno, DealPage.AccountNo1);

	}

	public void check_All_3_Transaction_StatusIsScheduled(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		String ScroeStatus3rdrow = tm.reports_ScroeStatus3rdRow.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);
		System.out.println(" ScroeStatus3rdrow " + ScroeStatus3rdrow);

		if (!((ScroeStatus.equalsIgnoreCase("Scheduled")) && (ScroeStatus2ndrow.equalsIgnoreCase("Scheduled"))
				&& ((ScroeStatus3rdrow.equalsIgnoreCase("Scheduled"))))) {

			Assert.fail("The transaction is not Scheduled");
		}

	}

	public void check_Triggered_or_Settled_Status_For_All_3_Transactions(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		String ScroeStatus3rdrow = tm.reports_ScroeStatus3rdRow.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);
		System.out.println(" ScroeStatus3rdrow " + ScroeStatus3rdrow);

		if ((ScroeStatus.equalsIgnoreCase("Triggered")) || (ScroeStatus.equalsIgnoreCase("Settled"))) {

			System.out.println("Transaction succesfully settled or triggered");
		} else {
			Assert.fail("Transaction Staus should be either settled or triggered");
		}

		if ((ScroeStatus2ndrow.equalsIgnoreCase("Triggered")) || (ScroeStatus2ndrow.equalsIgnoreCase("Settled"))) {

			System.out.println("Transaction succesfully settled or triggered");
		} else {
			Assert.fail("Transaction Staus should be either settled or triggered");
		}

		if ((ScroeStatus3rdrow.equalsIgnoreCase("Triggered")) || (ScroeStatus3rdrow.equalsIgnoreCase("Settled"))) {

			System.out.println("Transaction succesfully settled or triggered");
		} else {
			Assert.fail("Transaction Staus should be either settled or triggered");
		}
	}

	public void check_All_6_Transaction_StatusIsScheduled(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		String ScroeStatus3rdrow = tm.reports_ScroeStatus3rdRow.getText();
		String ScroeStatus4throw = tm.reports_ScroeStatus4thRow.getText();
		String ScroeStatus5throw = tm.reports_ScroeStatus5thRow.getText();
		String ScroeStatus6throw = tm.reports_ScroeStatus6thRow.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);
		System.out.println(" ScroeStatus3rdrow " + ScroeStatus3rdrow);
		System.out.println(" ScroeStatus4throw " + ScroeStatus4throw);
		System.out.println(" ScroeStatus5throw " + ScroeStatus5throw);
		System.out.println(" ScroeStatus6throw " + ScroeStatus6throw);

		if (!((ScroeStatus.equalsIgnoreCase("Scheduled")) && (ScroeStatus2ndrow.equalsIgnoreCase("Scheduled"))
				&& ((ScroeStatus3rdrow.equalsIgnoreCase("Scheduled")))
				&& ((ScroeStatus4throw.equalsIgnoreCase("Scheduled")))
				&& ((ScroeStatus5throw.equalsIgnoreCase("Scheduled")))
				&& ((ScroeStatus6throw.equalsIgnoreCase("Scheduled"))))) {

			Assert.fail("The transaction is not Scheduled");
		}

	}

	public void check_Triggered_or_Settled_Status_For_All_6_Transactions_and_Settled_Amount(String TSID, String DealId)
			throws Exception {

		commonmethodExecReport(TSID, DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		String ScroeStatus3rdrow = tm.reports_ScroeStatus3rdRow.getText();
		String ScroeStatus4throw = tm.reports_ScroeStatus4thRow.getText();
		String ScroeStatus5throw = tm.reports_ScroeStatus5thRow.getText();
		String ScroeStatus6throw = tm.reports_ScroeStatus6thRow.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);
		System.out.println(" ScroeStatus3rdrow " + ScroeStatus3rdrow);
		System.out.println(" ScroeStatus4throw " + ScroeStatus4throw);
		System.out.println(" ScroeStatus5throw " + ScroeStatus5throw);
		System.out.println(" ScroeStatus6throw " + ScroeStatus6throw);

		if ((ScroeStatus.equalsIgnoreCase("Triggered")) || (ScroeStatus.equalsIgnoreCase("Settled"))) {

			System.out.println("Transaction succesfully settled or triggered");
		} else {
			Assert.fail("Transaction Staus should be either settled or triggered");
		}

		if ((ScroeStatus2ndrow.equalsIgnoreCase("Triggered")) || (ScroeStatus2ndrow.equalsIgnoreCase("Settled"))) {

			System.out.println("Transaction succesfully settled or triggered");
		} else {
			Assert.fail("Transaction Staus should be either settled or triggered");
		}

		if ((ScroeStatus3rdrow.equalsIgnoreCase("Triggered")) || (ScroeStatus3rdrow.equalsIgnoreCase("Settled"))) {

			System.out.println("Transaction succesfully settled or triggered");
		} else {
			Assert.fail("Transaction Staus should be either settled or triggered");
		}
		if ((ScroeStatus4throw.equalsIgnoreCase("Triggered")) || (ScroeStatus4throw.equalsIgnoreCase("Settled"))) {

			System.out.println("Transaction succesfully settled or triggered");
		} else {
			Assert.fail("Transaction Staus should be either settled or triggered");
		}
		if ((ScroeStatus5throw.equalsIgnoreCase("Triggered")) || (ScroeStatus5throw.equalsIgnoreCase("Settled"))) {

			System.out.println("Transaction succesfully settled or triggered");
		} else {
			Assert.fail("Transaction Staus should be either settled or triggered");
		}
		if ((ScroeStatus6throw.equalsIgnoreCase("Triggered")) || (ScroeStatus6throw.equalsIgnoreCase("Settled"))) {

			System.out.println("Transaction succesfully settled or triggered");
		} else {
			Assert.fail("Transaction Staus should be either settled or triggered");
		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SettledAmount, tm.reports_horizontalWindow1, 10,
				1000);

		ArrayList<String> settledAmountrecords = new ArrayList();
		for (WebElement iu : tm.reports_SettledAmountList) {

			settledAmountrecords.add(iu.getText());

		}
		Assert.assertTrue(settledAmountrecords.contains("4200"));
		Assert.assertTrue(settledAmountrecords.contains("1650"));

		System.out.println("The settled amount status records are:" + settledAmountrecords.toString());
	}

	public void commonmethodBudget_Utilization_Report(String TSID, String DealId) throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
//		applyExplicitWaitsUntilElementClickable(tm.reports_searchBox, Duration.ofSeconds(5));
//		tm.reports_searchBox.sendKeys("Execution Report");
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.reports_BUDGET_UTILIZATION_REPORT, Duration.ofSeconds(6));
		jsClick.click(tm.reports_BUDGET_UTILIZATION_REPORT);

		applyExplicitWaitsUntilElementClickable(tm.reports_DealId, Duration.ofSeconds(40));
		tm.reports_DealId.sendKeys(DealId);
		Thread.sleep(8000);

	}

	public void check_Utilized_Budget_in_Budget_Utilization_Report(String TSID, String DealId) throws Exception {

		commonmethodBudget_Utilization_Report(TSID, DealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_Utilized_Budget_ColName,
				tm.reports_horizontalWindow1, 10, 1000);
		applyExplicitWaitsUntilElementVisible(tm.reports_Utilized_Budget_Amount, Duration.ofSeconds(10));
		String UtilzedAmount = tm.reports_Utilized_Budget_Amount.getText();
		System.out.println("The Utlized Budget amount in Budget Utilization report:" + UtilzedAmount);
         
		//temporarily disabled due to decimal point variation
		//Assert.assertEquals(UtilzedAmount,externalData.getFieldData(TSID, "Budget_Utilisation_Report", "Utilized Amount"));

	}
	
	public void check_Utilized_Budget_in_Budget_Utilization_Report_For_Multiple_Tnx(String TSID, String DealId) throws Exception {

		commonmethodBudget_Utilization_Report(TSID, DealId);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_Utilized_Budget_ColName,
				tm.reports_horizontalWindow1, 10, 1000);
		applyExplicitWaitsUntilElementVisible(tm.reports_Utilized_Budget_Amount_List, Duration.ofSeconds(10));
		
		ArrayList<String> Utilized_Budget_records = new ArrayList();
		for (WebElement iu : tm.reports_Utilized_Budget_Amount_List) {

			Utilized_Budget_records.add(iu.getText());

		}

		Assert.assertTrue(Utilized_Budget_records.contains("100"));
		Assert.assertTrue(Utilized_Budget_records.contains("1000"));
		
		System.out.println("The Utilized budget amount status records are:" + Utilized_Budget_records.toString());
	}

	public void eCommExecutionsReport_Status(String endToEndId, String TSID) throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		Thread.sleep(2000);
		scroll.scrollInToView(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(6));
		jsClick.click(tm.reports_eCommExecutionsList);
		applyExplicitWaitsUntilElementClickable(tm.reports_End_To_End_common, Duration.ofSeconds(5));
		Thread.sleep(3000);
		tm.reports_End_To_End_common.sendKeys(endToEndId);
		Thread.sleep(3000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, 1000);
		jsClick.click(tm.cancelIcon);

		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_dealIDText, tm.reports_horizontalWindow1, 10,
				-1000);
		Thread.sleep(2000);
		String status = tm.reports_FirstTxnStatus.getText();
		System.out.println("The status is:" + status);
		Assert.assertEquals(status, "Scheduled");

		if (TSID.equals("TS107") || TSID.equals("TS107_1")) {
			System.out.println(accountMap);
			ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_BeneficiaryAccountNumberColumnName,
					tm.reports_horizontalWindow1, 10, 1000);
			Thread.sleep(2000);
			String account = tm.reports_BeneficiaryAccountNumberValue.getText();
			String networkKeyAccount = accountMap.get("Computer");
			Assert.assertEquals(account, networkKeyAccount);

		}
		else if(TSID.equals("TS123") || TSID.equals("TS123_1")) {
			System.out.println(accountMap);
			ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_BeneficiaryAccountNumberColumnName,
					tm.reports_horizontalWindow1, 10, 1000);
			Thread.sleep(2000);
			String account = tm.reports_BeneficiaryAccountNumberValue.getText();
			String networkKeyAccount = accountMap.get("Computer");
			System.out.println(account);
			System.out.println(networkKeyAccount);
		}
	}

	public void verifyHoldStatusInExecutionReport(String tSID) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		tm.reports_ReportsIcon.click();
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		tm.reports_ReportsInternal.click();
//		applyExplicitWaitsUntilElementClickable(tm.reports_searchBox, Duration.ofSeconds(5));
//		tm.reports_searchBox.sendKeys("eComm Executions");
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(100));
		tm.reports_eCommExecutionsList.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_dealId1, Duration.ofSeconds(100));
		System.out.println(dealId);
		tm.reports_dealId1.sendKeys(dealId);
		Thread.sleep(2000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, 1000);
		jsClick.click(tm.cancelIcon);

		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_dealIDText, tm.reports_horizontalWindow1, 10,
				-1000);
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementVisible(tm.reports_EcommRecordStatus, Duration.ofSeconds(100));
		for (WebElement record : tm.reports_EcommRecordStatus) {
			System.out.println(record.getText());
			Assert.assertEquals(record.getText(), "HOLD");

		}
	}
	
	public void verifyCancelStatusInExecutionReport(String tSID) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		tm.reports_ReportsIcon.click();
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		tm.reports_ReportsInternal.click();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(100));
		tm.reports_eCommExecutionsList.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_dealId1, Duration.ofSeconds(100));
		System.out.println(dealId);
		tm.reports_dealId1.sendKeys(dealId);
		Thread.sleep(2000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, 1000);
		jsClick.click(tm.cancelIcon);

		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_dealIDText, tm.reports_horizontalWindow1, 10,
				-1000);
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementVisible(tm.reports_EcommRecordStatus, Duration.ofSeconds(100));
		for (WebElement record : tm.reports_EcommRecordStatus) {
			System.out.println(record.getText());
			Assert.assertEquals(record.getText(), "Cancelled");

		}
	}
	
	public void verifyScheduleStatusInExecutionReport(String tSID) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		tm.reports_ReportsIcon.click();
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		tm.reports_ReportsInternal.click();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.reports_eCommExecutionsList, Duration.ofSeconds(100));
		tm.reports_eCommExecutionsList.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_dealId1, Duration.ofSeconds(100));
		System.out.println(dealId);
		tm.reports_dealId1.sendKeys(dealId);
		Thread.sleep(2000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.cancelIcon, tm.reports_horizontalWindow1, 10, 1000);
		jsClick.click(tm.cancelIcon);

		Thread.sleep(1000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_dealIDText, tm.reports_horizontalWindow1, 10,
				-1000);
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementVisible(tm.reports_EcommRecordStatus, Duration.ofSeconds(100));
		for (WebElement record : tm.reports_EcommRecordStatus) {
			System.out.println(record.getText());
			Assert.assertEquals(record.getText(), "Scheduled");

		}
	}
	
		private void verifyAndAddDealIdColumn() throws Exception {
			applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
			jsClick.click(tm.reports_ReportsIcon);
			applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
			jsClick.click(tm.reports_ReportsInternal);
			Thread.sleep(2000);
			applyExplicitWaitsUntilElementClickable(tm.reports_ExecutionReport, Duration.ofSeconds(6));
			jsClick.click(tm.reports_ExecutionReport);
			
		boolean b=	isWebElementDisplayed(tm.reports_dealId1);
		if(!b) {
			ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_dealIDText, tm.reports_horizontalWindow1, 10,
					-1000);
			b=isWebElementDisplayed(tm.reports_dealId1);
		}
		
		if(!b) {
			click(tm.reports_setPreference);
			ScrollTypes. scrollVerticalInsideWindowTillWebElementPresent(tm.reports_setPreference_DealId,tm.reports_setPreference_VerticalScrollBar,10,300);
			click(tm.reports_setPreference_DealIdCheckBox);
			}
		}
		
		public void verifySplitFixedAmounts(String TSID) throws Exception {
			commonmethodExecReport(TSID, dealId);
			ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
					tm.reports_horizontalWindow1, 8, 1000);
			ArrayList<String> scroeStatus = new ArrayList();
			for (WebElement iu : tm.reports_ScroeStatusRecords) {

				scroeStatus.add(iu.getText().trim());
				Assert.assertEquals(iu.getText().trim(), "Scheduled");
			}
			ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_OriginalAmountColumn,
					tm.reports_horizontalWindow1, 8, 1000);

			Thread.sleep(500);

			ArrayList<String> originalAmount = new ArrayList();
			for (WebElement iu : tm.reports_OriginalAmount) {

				originalAmount.add(iu.getText());
				System.out.println(iu.getText());
			}
			Assert.assertTrue(originalAmount.contains("200"));
			Assert.assertTrue(originalAmount.contains("300"));
		}
		
		public void moveSetPreferenceColumn(WebDriver driver,String[] preferences,String prefrenceName,int i) throws Exception {
			try {
				List<String> preferenceList = new ArrayList<String>();
				
				driver.findElement(By.xpath("//*[contains(text(), 'Set Preference')]")).click();
		
				Thread.sleep(1000);
				List<WebElement> preferenceElements = driver.findElements(By.xpath("//div[@cdkdraglockaxis='y']"));
				int X =preferenceElements.get(0).getLocation().getX();
				int Y =preferenceElements.get(0).getLocation().getY();
				
				for(WebElement element : preferenceElements) {
			
					preferenceList.add(element.getText());
					if(element.getText().equals(prefrenceName)) {
						System.out.println(element.getText());
						int x=element.getLocation().getX();
						int y=element.getLocation().getY();
						if(x!=X || y!=Y) {
							new Actions(driver).clickAndHold(element).pause(2).perform();
							new Actions(driver).moveToElement(preferenceElements.get(i)).pause(5).perform();
							new Actions(driver).release(preferenceElements.get(i)).pause(10).perform();	
						}
							}
				}
				
				for(int index = 0; index < preferences.length; index++) {
					if(preferenceList.contains(preferences[index])) {
						preferenceElements.get(preferenceList.indexOf(preferences[index])).findElement(By.id("undefined")).click();
					}
				}
				Thread.sleep(10000);
				driver.findElement(By.xpath("//button[contains(text(), 'Apply')]")).click();	
			}
			catch (Exception e) {
				
				throw new Exception("Error while setting and moving preferences column");
			}
		}

		public void validateExtraRecordNotCreated(String string) throws Exception {
			applyExplicitWaitsUntilElementClickable(tm.reports_DealId, Duration.ofSeconds(40));
			tm.reports_DealId.clear();
			tm.reports_DealId.sendKeys(dealId);
			Thread.sleep(2000);
			List<WebElement> allrecordStatus = tm.reports_AllRecordsScroeStatus;	
			System.out.println("After"+allrecordStatus.size());
			Assert.assertEquals(totalRecord, allrecordStatus.size());
		}

		public void validateRecordStatusAsScheduled(String tSID) throws Exception {
			commonmethodExecReport(tSID, dealId);
			Thread.sleep(5000);
			List<WebElement> allrecordStatus = tm.reports_AllRecordsScroeStatus;	
			totalRecord = allrecordStatus.size();
			System.out.println(allrecordStatus.size());
			for(WebElement record : allrecordStatus) {
				System.out.println(record.getText());
			}
		}

		
		public void check_one_Tnx_Rejected_and_RescheduledDate_SameDay(String TSID, String DealId) throws Exception {

			commonmethodExecReport(TSID, DealId);
			System.out.println(DealId);
			ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_ScroeStatusColumnName,
					tm.reports_horizontalWindow1, 8, 1000);
			ArrayList<String> scroeStatus = new ArrayList();
			for (WebElement iu : tm.reports_ScroeStatusRecords) {
				scroeStatus.add(iu.getText().trim());
			}
			
			Assert.assertTrue(scroeStatus.contains("Rejected"));
			
			String rescheduleddate=	tm.reports_RescheduledDate.getText();
			String date[]=rescheduleddate.split(" ");
			String rescheduleddate1=date[0];
			String ExcpectedRescheduledDate=dateutil.getCurrentDate_HashFormatToday();
			Assert.assertEquals(rescheduleddate1, ExcpectedRescheduledDate);

		}
		
		public void check_Both_Tnx_Rejected(String tsid) throws Exception {
			commonmethodExecReport(tsid, dealId);
			applyExplicitWaitsUntilElementClickable( tm.reports_ScroeStatus, Duration.ofSeconds(30));
			String ScroeStatus = tm.reports_ScroeStatus.getText();
			String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
			System.out.println("Scroe status is " + ScroeStatus);
			System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);

			if (!((ScroeStatus.equalsIgnoreCase("Rejected")) && (ScroeStatus2ndrow.equalsIgnoreCase("Rejected")))) {

				Assert.fail("The transaction should be rejected");
			}
		}

		public void checkStatusAndInstructionTypeForRetention(String tsid) throws Exception {

			commonmethodExecReport(tsid, dealId);
			applyExplicitWaitsUntilElementClickable(tm.reports_ScroeStatus, Duration.ofSeconds(30));
			String ScroeStatus = tm.reports_ScroeStatus.getText();
			System.out.println("Scroe status is " + ScroeStatus);
			if (!(ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled"))) {
				Assert.fail("Transaction not scheduled");
			}

			// scroll.scrollHorizontalInsideWindow(tm.reports_horizontalWindow, 3800);
			ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SubInstructions_Type,
					tm.reports_horizontalWindow1, 10, 1000);

			Thread.sleep(1000);

			ArrayList<String> subInstruction = new ArrayList();
			for (WebElement iu : tm.reports_SubInstructions) {

				subInstruction.add(iu.getText());
			}
			Assert.assertTrue(subInstruction.contains("Retention"));
		}
}
