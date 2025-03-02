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

public class Transactions_Verifier extends BaseClass {

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

	public Transactions_Verifier() {

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

	public void TransactionsVerifier(String TSID, String TnxId) throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon, Duration.ofSeconds(15));
		jsClick.click(tm.transactions_TransactionIcon);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionVerifier, Duration.ofSeconds(25));
		jsClick.click(tm.transactions_TransactionVerifier);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIdSearchBox, Duration.ofSeconds(10));
		tm.transactions_TransactionIdSearchBox.sendKeys(TnxId);
		Thread.sleep(3000);
//		applyExplicitWaitsUntilElementInvisible(tm.transactions_Loader, 200);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionEditButton, Duration.ofSeconds(200));
		tm.transactions_TransactionEditButton.click();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_SummaryTab, Duration.ofSeconds(10));
		tm.transactions_SummaryTab.click();
		if(!TSID.equals("TS158") && !TSID.equals("TS159") && !TSID.equals("TS161")) {
		Thread.sleep(2000);
		String amount = externalData.getFieldData(TSID, "Txn Maker", "Amount");
		By AmountID = By.xpath("(//span[@class='ng-star-inserted'][contains(text(),'" + amount + "')])[1]");
		applyExplicitWaitsUntilElementVisible(AmountID, 2);
		String amount_checker = driver.findElement(AmountID).getText();
		System.out.println("This is the amount from checker" + amount_checker);

		if (!(amount_checker.contains(amount))) {
			System.out.println("amount doesn't match");
			assertTrue(false);

		}
		}
		applyExplicitWaitsUntilElementClickable(tm.transactions_Checker_Add_comments, Duration.ofSeconds(3));
		scroll.scrollInToView(tm.transactions_Checker_Add_comments);
		tm.transactions_Checker_Add_comments
				.sendKeys(externalData.getFieldData(TSID, "Txn Verifier", "Summary - Add your comments here"));
		applyExplicitWaitsUntilElementClickable(tm.transactions_ApproveButton, Duration.ofSeconds(3));
		tm.transactions_ApproveButton.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_YesButton, Duration.ofSeconds(10));
		tm.transactions_YesButton.click();

		applyExplicitWaitsUntilElementClickable(tm.transactions_Ok, Duration.ofSeconds(10));
		tm.transactions_Ok.click();

	}

	public void txnVerifier_ApproveDeal(String dealId) throws Exception {
		// TODO Auto-generated method stub
		//od.TxnChecker_Transaction.click();
		try {
			tm.transactions_TransactionIcon.click();
		}catch (Exception e) {
			handleElementClickException(tm.transactions_TransactionIcon);
		}
		applyExplicitWaitsUntilElementInvisible(od.TxnChecker_TransactionProgress, 100);
		try {
			tm.transactions_TransactionVerifier.click();	
		}catch (Exception e) {
			handleElementClickException(tm.transactions_TransactionVerifier);
		}
		//TimeUnit.MINUTES.sleep(2);

		od.TxnChecker_searchDealId.sendKeys(dealId);
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementInvisible(od.TxnChecker_TransactionProgress, 100);
		try {
			od.TxnChecker_comment.click();
		}catch (Exception e) {
			handleElementClickException(od.TxnChecker_comment);
		}
		Thread.sleep(2000);
		od.TxnChecker_note.sendKeys("Ok");
		od.TxnChecker_ok.click();
	}

	
	public void TransactionsVerifier1(String TSID, String TnxId) throws Exception {

		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon, Duration.ofSeconds(15));
		jsClick.click(tm.transactions_TransactionIcon);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionVerifier, Duration.ofSeconds(25));
		jsClick.click(tm.transactions_TransactionVerifier);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIdSearchBox, Duration.ofSeconds(10));
		tm.transactions_TransactionIdSearchBox.sendKeys(TnxId);
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionEditButton, Duration.ofSeconds(40));
		tm.transactions_TransactionEditButton.click();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_SummaryTab, Duration.ofSeconds(10));
		tm.transactions_SummaryTab.click();
		Thread.sleep(2000);
//		String amount = externalData.getFieldData(TSID, "Txn Maker", "Amount");
//		By AmountID = By.xpath("(//span[@class='ng-star-inserted'][contains(text(),'" + amount + "')])[1]");
//		applyExplicitWaitsUntilElementVisible(AmountID, 2);
//		String amount_checker = driver.findElement(AmountID).getText();
//		System.out.println("This is the amount from checker" + amount_checker);
//
//		if (!(amount_checker.contains(amount))) {
//			System.out.println("amount doesn't match");
//			assertTrue(false);
//
//		}
		applyExplicitWaitsUntilElementClickable(tm.transactions_Checker_Add_comments, Duration.ofSeconds(3));
		scroll.scrollInToView(tm.transactions_Checker_Add_comments);
		tm.transactions_Checker_Add_comments
				.sendKeys(externalData.getFieldData(TSID, "Txn Verifier", "Summary - Add your comments here"));
		applyExplicitWaitsUntilElementClickable(tm.transactions_ApproveButton, Duration.ofSeconds(3));
		tm.transactions_ApproveButton.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_YesButton, Duration.ofSeconds(10));
		tm.transactions_YesButton.click();

		applyExplicitWaitsUntilElementClickable(tm.transactions_Ok, Duration.ofSeconds(10));
		tm.transactions_Ok.click();

	}

}
