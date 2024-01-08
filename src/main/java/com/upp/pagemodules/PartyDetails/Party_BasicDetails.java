package com.upp.pagemodules.PartyDetails;



import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
import com.upp.handlers.EcommerceHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;
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
public class Party_BasicDetails extends BaseClass {

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
	public static Object_Parties op;
	DealPartiesHandler partyHandler = new DealPartiesHandler();
	public String responsibilities;
	public String ecommerce;
	public Party_BasicDetails() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		op=new Object_Parties();

	}

	
	public void Create_Party_BasicDetails(String TSID, ICallback icallback) throws Exception
	{
		od.parties_AddnewParty.click();
		partyName = externalData.getFieldData(TSID, "Party", "Customer Id")+"_"+DateUtils.getCurrentDateTime();
		od.parties_CustomerID.sendKeys(partyName);
		od.parties_PartyName.sendKeys(externalData.getFieldData(TSID, "Party", "Party Name")+"_"+DateUtils.getCurrentDateTime());
		
//		od.parties_CustomerID.sendKeys(partyName);
//		od.parties_PartyName.sendKeys(partyName);
		
		responsibilities = externalData.getFieldData(TSID, "Party", "Responsibility");
		od.parties_Responsibility.sendKeys(responsibilities);
		By party_Responsibility_Option = By.xpath("//div[contains(text(),'" + responsibilities + "')]");
		applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 15);
		driver.findElement(party_Responsibility_Option).click();
//		click(party_Responsibility_Option);
		
		od.parties_Remarks.sendKeys(externalData.getFieldData(TSID, "Party", "Remarks"));
		Thread.sleep(1000);
		icallback.handleCallback("RESPONSIBILITIES", responsibilities);
		Thread.sleep(1000);
		ecommerce = externalData.getFieldData(TSID, "Party", "eCommerce Party-checkbox");
		System.out.println(ecommerce);
		if (ecommerce.equalsIgnoreCase("Y")) {
			new EcommerceHandler().handleEcommerce(TSID);
		} else {
			od.parties_BasicNextButton.click();

		}
	    if(TSID.equals("TS97_2")) {
	    	od.parties_backButtton.click();
	    }
	}
}
