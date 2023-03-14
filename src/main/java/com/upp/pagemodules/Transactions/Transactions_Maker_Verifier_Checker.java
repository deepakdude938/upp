package com.upp.pagemodules.Transactions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
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
public class Transactions_Maker_Verifier_Checker extends BaseClass {

	public static Object_Deal od;
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
	 public static Object_Transactions tm ;
	public Transactions_Maker_Verifier_Checker() {

		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		tm=new Object_Transactions();

	}

	
	public String createTransactionFromTransactionMaker(String TSID,String DealId,String sourceAccno,ICallback icallback) throws Exception
	{
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon,Duration.ofSeconds(15));
		tm.transactions_TransactionIcon.click();
		tm.transactions_TransactionMaker.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_AddNewButon,Duration.ofSeconds(15));
		tm.transactions_AddNewButon.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_DealId,Duration.ofSeconds(10));
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
		if(((externalData.getFieldData(TSID,"Txn Maker","Split")).equalsIgnoreCase("Y") || (externalData.getFieldData(TSID,"Txn Maker","Split")).equalsIgnoreCase("Yes"))){
			od.payments_SplitBalanceSlider.click();
		}
		od.payments_NextArrowButtonTransferBasic.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_Instrument,Duration.ofSeconds(5));
		tm.transactions_Instrument.click();
		String paymentInstrumentdata=externalData.getFieldData(TSID,"Txn Maker","Sub Instruction - Instrument");
	    By paymentInstrument = By.xpath("(//div[contains(text(),'"+paymentInstrumentdata+"')])[1]");
		 applyExplicitWaitsUntilElementVisible(paymentInstrument,3);
		 driver.findElement(paymentInstrument).click();
		 tm.transactions_Instrument.click();
		 System.out.println("the to data is "+externalData.getFieldData(TSID,"Txn Maker","to"));
		 icallback.handleCallback("PAYMENT_INSTRUMENT",paymentInstrumentdata);
	
		 scroll.scrollInToView(tm.transactions_DocumentTypeDropdown);
		 applyExplicitWaitsUntilElementClickable(tm.transactions_DocumentTypeDropdown,Duration.ofSeconds(5));
		 dropdown.selectByVisibleText(tm.transactions_DocumentTypeDropdown,externalData.getFieldData(TSID,"Txn Maker","Documents-Document Type"));
		 dropdown.selectByVisibleText(tm.transactions_FileType,externalData.getFieldData(TSID,"Txn Maker","File Type"));
		 tm.transactions_EnterUrl.sendKeys(externalData.getFieldData(TSID,"Txn Maker","URL"));
		 tm.transactions_Description.sendKeys(externalData.getFieldData(TSID,"Txn Maker","Description"));
		 tm.transactions_DocumentsAddButton.click();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		 scroll.scrollInToView(tm.transactions_ProceedToSummary);
		 applyExplicitWaitsUntilElementClickable(tm.transactions_ProceedToSummary,Duration.ofSeconds(5));
		 tm.transactions_ProceedToSummary.click();
		 scroll.scrollInToView(tm.transactions_SummarySubmitButton);
		 applyExplicitWaitsUntilElementClickable(tm.transactions_SummarySubmitButton,Duration.ofSeconds(5));
		 jsClick.click(tm.transactions_SummarySubmitButton);
		 Thread.sleep(2000);
		 tm.transactions_Ok.click();
		 Thread.sleep(3000);
		 tm.transactions_SummarySubmitButton.click();
		 
		 applyExplicitWaitsUntilElementClickable(tm.transactions_YesButton,Duration.ofSeconds(15));
		 tm.transactions_YesButton.click();
		 
		 applyExplicitWaitsUntilElementClickable(tm.transactions_Ok,Duration.ofSeconds(15));
		 String transactionSubmitMessage=tm.transactions_TransactionsId.getText();
		 String transactionMessage[]=transactionSubmitMessage.split(" ");
		 String TnxId=transactionMessage[1];
		 System.out.println("The Tnx id is "+TnxId);
		 tm.transactions_Ok.click();
		 
		return TnxId;
		
	}
	
	
	public void approveTransactionFromChecker(String TSID,String TnxId) throws Exception
	{
		
	    applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon,Duration.ofSeconds(15));
		jsClick.click(tm.transactions_TransactionIcon);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionChecker,Duration.ofSeconds(25));
		jsClick.click(tm.transactions_TransactionChecker);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionChecker,Duration.ofSeconds(10));
		tm.transactions_TransactionIdSearchBox.sendKeys(TnxId);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionEditButton,Duration.ofSeconds(10));
		tm.transactions_TransactionEditButton.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_SummaryTab,Duration.ofSeconds(10));
	    tm.transactions_SummaryTab.click();
		
		String amount=externalData.getFieldData(TSID,"Txn Maker","Amount");
	    By AmountID = By.xpath("(//span[@class='ng-star-inserted'][contains(text(),'"+amount+"')])[1]");
		 applyExplicitWaitsUntilElementVisible(AmountID,2);
		String amount_checker= driver.findElement(AmountID).getText();
		System.out.println("This is the amount from checker"+amount_checker);
		
		if(!(amount_checker.contains(amount)))
		{
			System.out.println("amount doesn't match");
			assertTrue(false);
			
		}
		applyExplicitWaitsUntilElementClickable(tm.transactions_Checker_Add_comments,Duration.ofSeconds(3));
		scroll.scrollInToView(tm.transactions_Checker_Add_comments);
		tm.transactions_Checker_Add_comments.sendKeys(externalData.getFieldData(TSID,"Txn checker","Summary - Add your comments here"));
		 applyExplicitWaitsUntilElementClickable(tm.transactions_Checker_SubmitButton,Duration.ofSeconds(3));
		tm.transactions_Checker_SubmitButton.click();
		 applyExplicitWaitsUntilElementClickable(tm.transactions_YesButton,Duration.ofSeconds(10));
		 tm.transactions_YesButton.click();
		 
		 applyExplicitWaitsUntilElementClickable(tm.transactions_Ok,Duration.ofSeconds(10));
		 tm.transactions_Ok.click();
		 
	}

	
	public void approveTransactionFromVerifier(String TSID,String TnxId) throws Exception
	{
		
	    applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon,Duration.ofSeconds(15));
		jsClick.click(tm.transactions_TransactionIcon);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionVerifier,Duration.ofSeconds(25));
		jsClick.click(tm.transactions_TransactionVerifier);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIdSearchBox,Duration.ofSeconds(10));
		tm.transactions_TransactionIdSearchBox.sendKeys(TnxId);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionEditButton,Duration.ofSeconds(10));
		tm.transactions_TransactionEditButton.click();
		applyExplicitWaitsUntilElementClickable(tm.transactions_SummaryTab,Duration.ofSeconds(10));
	    tm.transactions_SummaryTab.click();
		
		String amount=externalData.getFieldData(TSID,"Txn Maker","Amount");
	    By AmountID = By.xpath("(//span[@class='ng-star-inserted'][contains(text(),'"+amount+"')])[1]");
		 applyExplicitWaitsUntilElementVisible(AmountID,2);
		String amount_checker= driver.findElement(AmountID).getText();
		System.out.println("This is the amount from checker"+amount_checker);
		
		if(!(amount_checker.contains(amount)))
		{
			System.out.println("amount doesn't match");
			assertTrue(false);
			
		}
		applyExplicitWaitsUntilElementClickable(tm.transactions_Checker_Add_comments,Duration.ofSeconds(3));
		scroll.scrollInToView(tm.transactions_Checker_Add_comments);
		tm.transactions_Checker_Add_comments.sendKeys(externalData.getFieldData(TSID,"Txn Verifier","Summary - Add your comments here"));
		 applyExplicitWaitsUntilElementClickable(tm.transactions_ApproveButton,Duration.ofSeconds(3));
		tm.transactions_ApproveButton.click();
		 applyExplicitWaitsUntilElementClickable(tm.transactions_YesButton,Duration.ofSeconds(10));
		 tm.transactions_YesButton.click();
		 
		 applyExplicitWaitsUntilElementClickable(tm.transactions_Ok,Duration.ofSeconds(10));
		 tm.transactions_Ok.click();
		 
	}

	
	public void checkTnxStatusFromExecutionReport(String TSID,String DealId) throws Exception
	{
		
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsIcon,Duration.ofSeconds(15));
		jsClick.click(tm.reports_ReportsIcon);
		applyExplicitWaitsUntilElementClickable(tm.reports_ReportsInternal,Duration.ofSeconds(5));
		jsClick.click(tm.reports_ReportsInternal);
		applyExplicitWaitsUntilElementClickable(tm.reports_searchBox,Duration.ofSeconds(5));
		tm.reports_searchBox.sendKeys("Execution Report");
		applyExplicitWaitsUntilElementClickable(tm.reports_ExecutionReport,Duration.ofSeconds(6));
		jsClick.click(tm.reports_ExecutionReport);
		applyExplicitWaitsUntilElementClickable(tm.reports_DealId,Duration.ofSeconds(5));
		tm.reports_DealId.sendKeys(DealId);
		applyExplicitWaitsUntilElementClickable(tm.reports_SubmitButton,Duration.ofSeconds(5));
		jsClick.click(tm.reports_SubmitButton);
		Thread.sleep(4000);
		String ScroeStatus=tm.reports_ScroeStatus.getText();
		System.out.println("Scroe status is "+ScroeStatus);
		if(ScroeStatus.equalsIgnoreCase("Pending") || ScroeStatus.equalsIgnoreCase("Scheduled"))
		{
			System.out.println("Waiting for Transaction to be triggered");
			TimeUnit.SECONDS.sleep(5);
			String ScroeStatusafter=tm.reports_ScroeStatus.getText();
			if(ScroeStatusafter.equalsIgnoreCase("triggered"))
			{
				System.out.println("Transaction is triggered");
				
			}
			else
			{
				System.out.println("Transaction is not yet triggered");
			}
		}
		
		
	}
	
	

}
