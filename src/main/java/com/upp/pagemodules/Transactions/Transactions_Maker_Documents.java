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
public class Transactions_Maker_Documents extends BaseClass {

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
	public Transactions_Maker_Documents() {

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

	
	public void Transactions_Maker_Documents(String TSID) throws Exception
	{
		 scroll.scrollInToView(tm.transactions_DocumentTypeDropdown);
		 applyExplicitWaitsUntilElementClickable(tm.transactions_DocumentTypeDropdown,Duration.ofSeconds(5));
		 dropdown.selectByVisibleText(tm.transactions_DocumentTypeDropdown,externalData.getFieldData(TSID,"Txn Maker","Documents-Document Type"));
		 dropdown.selectByVisibleText(tm.transactions_FileType,externalData.getFieldData(TSID,"Txn Maker","File Type"));
		 tm.transactions_EnterUrl.sendKeys(externalData.getFieldData(TSID,"Txn Maker","URL"));
		 tm.transactions_Description.sendKeys(externalData.getFieldData(TSID,"Txn Maker","Description"));
		 tm.transactions_DocumentsAddButton.click();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		 
	}
	


}
