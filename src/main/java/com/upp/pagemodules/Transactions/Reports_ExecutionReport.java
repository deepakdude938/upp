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
	 public static Object_Transactions tm ;
	public Reports_ExecutionReport() {

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
	
	public void ExecutionReport(String TSID,String DealId) throws Exception
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
