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
public class Transactions_Maker_Summary extends BaseClass {

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
	 public static Object_Transactions tm ;
	public Transactions_Maker_Summary() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		tm=new Object_Transactions();

	}

	
	public String Transaction_Maker_Summary() throws Exception
	{
		
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

	public String Transaction_Maker_Summary1() throws Exception
	{
		
		 scroll.scrollInToView(tm.transactions_ProceedToSummary);
		 applyExplicitWaitsUntilElementClickable(tm.transactions_ProceedToSummary,Duration.ofSeconds(5));
		 tm.transactions_ProceedToSummary.click();
		 scroll.scrollInToView(tm.transactions_SummarySubmitButton);
		 applyExplicitWaitsUntilElementClickable(tm.transactions_SummarySubmitButton,Duration.ofSeconds(5));
		 jsClick.click(tm.transactions_SummarySubmitButton);
		 tm.transactions_Ok.click();
		 Thread.sleep(3000);
		 tm.transactions_SummarySubmitButton.click();
		 applyExplicitWaitsUntilElementClickable(tm.transactions_YesButton,Duration.ofSeconds(15));
		 tm.transactions_YesButton.click();
		 Thread.sleep(2000);
//		 tm.transactions_Ok.click();
//		 Thread.sleep(3000);
//		 tm.transactions_SummarySubmitButton.click();
//		 applyExplicitWaitsUntilElementClickable(tm.transactions_Ok,Duration.ofSeconds(15));
		 String transactionSubmitMessage=tm.transactions_TransactionsId.getText();
		 String transactionMessage[]=transactionSubmitMessage.split(" ");
		 String TnxId=transactionMessage[1];
		 System.out.println("The Tnx id is "+TnxId);
		 tm.transactions_Ok.click();
		 
		return TnxId;
		
	}
}
