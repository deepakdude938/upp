package com.upp.pagemodules.Transactions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
import java.util.ArrayList;
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

	public static void checkSubInstructionTypeInExecutionReport() throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon, Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal, Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		applyExplicitWaitsUntilElementClickable(tm.reports_searchBox, Duration.ofSeconds(5));
		tm.reports_searchBox.sendKeys("Execution Report");
		applyExplicitWaitsUntilElementClickable(tm.reports_ExecutionReport, Duration.ofSeconds(6));
		jsClick.click(tm.reports_ExecutionReport);
		applyExplicitWaitsUntilElementClickable(tm.reports_DealId, Duration.ofSeconds(5));
		tm.reports_DealId.sendKeys(dealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_SubmitButton, Duration.ofSeconds(5));
		jsClick.click(tm.reports_SubmitButton);
		Thread.sleep(2000);
		ScrollTypes.scrollInsideWindowTillWebElementPresent(tm.reports_SubInstructionType, tm.reports_horizontalWindow1,
				5, 2000);
		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
		}
		Assert.assertTrue(subInstruction.contains("Payment"));
		Assert.assertTrue(subInstruction.contains("Surplus"));
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

	public void eCommExecutionsReport(String EndToEndId) throws Exception {
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

		ArrayList<String> subInstruction = new ArrayList();
		for (WebElement iu : tm.reports_SubInstructions) {

			subInstruction.add(iu.getText());
		}
		Assert.assertTrue(subInstruction.contains("Payment"));
		Assert.assertTrue(subInstruction.contains("Retention"));
	}

	public void checkStatusTriggeredOrSettled(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);

		if (!((ScroeStatus.equalsIgnoreCase("Triggered")) || ScroeStatus.equalsIgnoreCase("Settled"))) {

			Assert.fail("The transaction is not triggered or settled");
		}
	}

	public void checkStatusRejected(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);

		if (!(ScroeStatus.equalsIgnoreCase("Rejected"))) {

			Assert.fail("The transaction is not Rejected");
		}
	}

	public void eCommExecutionsReportToCheckTransactionStatus(String EndToEndId, String DealId) throws Exception {
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
		String txn1 = tm.reports_FirstTxnStatus.getText();
		String txn2 = tm.reports_SecondTxnStatus.getText();
		Assert.assertEquals(txn1, "Hold");
		Assert.assertEquals(txn2, "Scheduled");
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

	public void checkStatusAndInstructionType(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String status = tm.reports_ScroeStatus.getText();

		if (!(status.equalsIgnoreCase("Scheduled"))) {

			Assert.fail("Transaction not scheduled");

		}

		scroll.scrollHorizontalInsideWindow(tm.reports_horizontalWindow, 4000);

		String instructionname = tm.reports_InstructionName.getText();

		System.out.println(instructionname);
		Assert.assertTrue(instructionname.equalsIgnoreCase("Alert"));
	}

	public void checkStatusAndInstructionTypeAsBalanceReport(String TSID, String DealId) throws Exception {

		commonmethodExecReport(TSID, DealId);

		String ScroeStatus = tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is " + ScroeStatus);

		if (!(ScroeStatus.equalsIgnoreCase("Scheduled"))) {
			Assert.fail("Transaction not scheduled");
		}

		scroll.scrollHorizontalInsideWindow(tm.reports_horizontalWindow, 3000);

		String instructionname1 = tm.reports_InstructionName1.getText();
		String instructionname2 = tm.reports_InstructionName2.getText();
		String instructionname3 = tm.reports_InstructionName3.getText();

		Assert.assertTrue(instructionname1.equalsIgnoreCase("Balance Report"));
		Assert.assertTrue(instructionname2.equalsIgnoreCase("Balance Report"));
		Assert.assertTrue(instructionname3.equalsIgnoreCase("Balance Report"));

	}
}
