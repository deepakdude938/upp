package com.upp.pagemodules.Transactions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
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
		Thread.sleep(3000);
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
//		applyExplicitWaitsUntilElementClickable(tm.reports_searchBox, Duration.ofSeconds(5));
//		tm.reports_searchBox.sendKeys("Execution Report");
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.reports_ExecutionReport, Duration.ofSeconds(6));
		jsClick.click(tm.reports_ExecutionReport);

		applyExplicitWaitsUntilElementClickable(tm.reports_DealId, Duration.ofSeconds(40));
		tm.reports_DealId.sendKeys(DealId);
		Thread.sleep(5000);
		jsClick.click(tm.cancelIcon);
		Thread.sleep(5000);

	}

	public void checkInstructionTypePayment_Retention(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		if (!(ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled"))) {
			Assert.fail("Transaction not scheduled");
		}

		// scroll.scrollHorizontalInsideWindow(tm.reports_horizontalWindow, 3800);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_InstructionName, tm.reports_horizontalWindow1, 5,
				2000);

		Thread.sleep(1000);

		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
		}
		Assert.assertTrue(subInstruction.contains("Payment"));
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
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);

		if (!((ScroeStatus.equalsIgnoreCase("Scheduled")) && (ScroeStatus2ndrow.equalsIgnoreCase("Scheduled")))) {

			Assert.fail("The transaction is not Scheduled");
		}

	}

	public void checkStatus_AwaitingForDependant(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
		String ScroeStatus2ndrow = tm.reports_ScroeStatus2ndRow.getText();
		System.out.println(" ScroeStatus2ndrow " + ScroeStatus2ndrow);

		if (!(ScroeStatus2ndrow.equalsIgnoreCase("Awaiting for Dependant"))) {

			Assert.fail("The transaction status should be Awaiting for Dependant ");
		}

	}

	public static void checkSubInstructionTypeInExecutionReport(String payment, String surplus, String retention)
			throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
//		applyExplicitWaitsUntilElementClickable(tm.reports_searchBox, Duration.ofSeconds(5));
//		tm.reports_searchBox.sendKeys("Execution Report");
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(tm.reports_ExecutionReport, Duration.ofSeconds(6));
		jsClick.click(tm.reports_ExecutionReport);
		Thread.sleep(2000);
		jsClick.click(tm.cancelIcon);
		Thread.sleep(4000);
		applyExplicitWaitsUntilElementClickable(tm.reports_DealId, Duration.ofSeconds(5));
		tm.reports_DealId.sendKeys(dealId);

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

		String status = tm.reports_ScroeStatus.getText();

		if (!(status.equalsIgnoreCase("Scheduled"))) {

			Assert.fail("Transaction not scheduled");

		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_InstructionName, tm.reports_horizontalWindow1, 5,
				2000);
		String instructionname = tm.reports_InstructionName.getText();

		System.out.println(instructionname);
		Assert.assertTrue(instructionname.equalsIgnoreCase("Balance Reporting"));

	}

	public void ExecutionReportForBulkUpload(String TSID, String DealId, String times) throws Exception {

		int flag = 0;
		commonmethodExecReport(TSID, DealId);
		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		System.out.println(tm.reports_RecordStatus.size());
		// for (WebElement record : tm.reports_RecordStatus) {
		if (ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled")) {
			TimeUnit.MINUTES.sleep(3);
			driver.navigate().refresh();
			applyExplicitWaitsUntilElementClickable(tm.reports_DealId, Duration.ofSeconds(5));
			tm.reports_DealId.sendKeys(DealId);
			String ScroeStatusafter = tm.reports_ScroeStatus.getText();
			if (ScroeStatusafter.equalsIgnoreCase("Settled")) {
				flag = 1;

			} else {
				flag = 0;
			}
			Assert.assertEquals(flag, 1);
		}
		// }

	}

	public void validateEcommTransaction() throws Exception {

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

			Assert.assertEquals(record.getText(), "Scheduled");

		}

	}

	public void validateEcommTransactionwithDealId(String deal) throws Exception {
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
		System.out.println("Record = "+record);
//		for (WebElement record : tm.reports_EcommRecordStatus) {
			Assert.assertEquals(record, "Scheduled");

//		}

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

		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_OriginalAmount) {

			subInstruction.add(iu.getText());
		}
		Assert.assertTrue(subInstruction.contains(strNumberamount1));
		Assert.assertTrue(subInstruction.contains(strNumberamount2));
	}

	public String eCommExecutionsReportCommon(String EndToEndId) throws Exception {

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
		applyExplicitWaitsUntilElementClickable(tm.reports_End_To_End_common, Duration.ofSeconds(5));
		Thread.sleep(3000);
		System.out.println("Test = " + EndToEndId);
		tm.reports_End_To_End_common.sendKeys(EndToEndId);
		Thread.sleep(6000);
		String status = tm.reports_FirstTxnStatus.getText();
		System.out.println("The status is:" + status);
		// Assert.assertEquals(status, "Triggered");
		String paymentRefId = tm.ecommPaymentLink.getText();
		System.out.println("The Payment Ref id:" + paymentRefId);
		return paymentRefId;

	}

	public String getBatchIdFromEcommPayments(String paymentRefId) throws Exception {
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		// applyExplicitWaitsUntilElementClickable(tm.reports_searchBox,
		// Duration.ofSeconds(5));
		// tm.reports_searchBox.sendKeys("eComm Payments");
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

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_OriginalAmountColumnName,
				tm.reports_horizontalWindow1, 10, 1000);
		ArrayList<String> originalAmount = new ArrayList();
		for (WebElement iu : tm.reports_OriginalAmountRecords) {

			originalAmount.add(iu.getText());
			System.out.println(iu.getText());
		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SubInstructionType, tm.reports_horizontalWindow1,
				10, 1000);
		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
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

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SettledAmountColumnName,
				tm.reports_horizontalWindow1, 10, 1000);
		ArrayList<String> settledAmount = new ArrayList();
		for (WebElement iu : tm.reports_SettledAmountRecords) {

			settledAmount.add(iu.getText());
			System.out.println(iu.getText());
		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SubInstructionType, tm.reports_horizontalWindow1,
				10, 1000);
		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
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
		ArrayList<String> scroeStatus = new ArrayList();
		for (WebElement iu : tm.reports_ScroeStatusRecords) {

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
		// applyExplicitWaitsUntilElementClickable(tm.reports_searchBox,
		// Duration.ofSeconds(5));
		// tm.reports_searchBox.sendKeys("eComm Payments");
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
		ArrayList<String> scroeStatus = new ArrayList();
		for (WebElement iu : tm.reports_ScroeStatusRecords) {
			scroeStatus.add(iu.getText().trim());
		}
		Assert.assertTrue(scroeStatus.contains("Rejected"));
		Assert.assertTrue(scroeStatus.contains("Settled"));

	}

	public void ExecutionReportAwaitingTransaction(String TSID, String DealId) throws Exception {
		String timem = dateTime.getTimeAfterMins(waitingTime);
//		long wait = Long.parseLong(timem);
		TimeUnit.MINUTES.sleep(8);
		commonmethodExecReport(TSID, DealId);

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		Assert.assertEquals(ScroeStatus, "Awaiting for Dependant");
	}

	public void ExecutionReportForSchedule(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);
		Assert.assertEquals(ScroeStatus, "Scheduled");

	}

	public void Check_Both_Tnx_Rejected_Verify_Error_Message(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);
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
		for (WebElement iu : tm.reports_ScroeStatusRecords) {

			scroeStatus.add(iu.getText().trim());
			Assert.assertEquals(iu.getText().trim(), "Scheduled");
		}

		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_OriginalAmountColumn,
				tm.reports_horizontalWindow1, 8, 1000);

		Thread.sleep(500);

		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_OriginalAmount) {

			subInstruction.add(iu.getText());
			System.out.println(iu.getText());
		}
		Assert.assertTrue(subInstruction.contains("200"));
		Assert.assertTrue(subInstruction.contains("300"));
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

}
