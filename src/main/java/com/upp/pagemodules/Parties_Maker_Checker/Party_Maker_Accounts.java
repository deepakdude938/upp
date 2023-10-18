package com.upp.pagemodules.Parties_Maker_Checker;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
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
public class Party_Maker_Accounts extends BaseClass {

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
	public Party_Maker_Accounts() {

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

	
	public void PartyMaker_Accounts(String TSID,ICallback icallback) throws Exception
	{
		od.parties_AccountsTab.click();
		op.PartyMaker_partyAccountsAddButton.click();	
		Thread.sleep(2000);
		jsClick.click(op.PartyMaker_PaymentSystem);
		Thread.sleep(1000);
		String paymentInstrumentdata=externalData.getFieldData(TSID, "Parties-Maker", "Accounts-Payment System");
		 By paymentInstrument = By.xpath("(//div[contains(text(),'"+paymentInstrumentdata+"')])[1]");
		 applyExplicitWaitsUntilElementVisible(paymentInstrument,5);
		 driver.findElement(paymentInstrument).click();
		icallback.handleCallback("PARTIES_MAKER_PAYMENT_INSTRUMENT",paymentInstrumentdata);
		
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_OKButton, Duration.ofSeconds(5));
		op.PartyMaker_OKButton.click();
	}


	public void editPartyAccount(String TSID) throws Exception, IOException {
		op.parties_Icon.click();
		op.parties_SummaryButton.click();
		String partyName=externalData.getFieldData(TSID, "Party", "Party Name");
		op.parties_SearchBox.sendKeys(partyName);
		op.parties_SearchButton.click();
		Thread.sleep(1000);
		op.parties_editPartyButton.click();
		try {
			op.parties_OkButton.click();
		}
		catch(Exception n) {
			
		}
		op.partyMaker_Icon.click();
		op.partyMaker_NameTextBox.sendKeys(partyName,Keys.ENTER);
		Thread.sleep(1000);
		op.parties_editPartyButton1.click();
		Thread.sleep(1000);
		od.parties_AccountsTab.click();
		Thread.sleep(2000);
		if(TSID.equals("TS107") || TSID.equals("TS107_1")) {
			Thread.sleep(2000);
			String networkKeyAccount = accountMap.get("Computer");
			WebElement e = driver.findElement(By.xpath("//div[@title='"+networkKeyAccount+"']//following::div[@id='ic-generic-menu-showMenu-v1']"));
			e.click();
		}
		else {
			op.parties_showMenu.click();
		}
		
		op.parties_showMenuEditButton.click();
		
		scroll.scrollInToView(od.parties_paymentTo);
		applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
		od.parties_paymentTo.sendKeys(Keys.CONTROL + "a");
		od.parties_paymentTo.sendKeys(Keys.BACK_SPACE);
		od.parties_paymentTo.sendKeys("ICICI1205");
		if(TSID.equals("TS107") || TSID.equals("TS107_1")) {
			accountMap.put("Computer", "ICICI1205");
		}
		scroll.scrollInToView(od.saveButton);
		od.saveButton.click();
		if(TSID.equals("TS107") || TSID.equals("TS107_1")) {
			op.PartyMaker_YesButton.click();
		}
		op.parties_OkButton.click();
	}
}
