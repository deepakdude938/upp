package com.upp.pagemodules.PartyDetails;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import callbackInterfaces.ICallback;

public class Party_Accounts extends BaseClass {

	public static Object_NewDeal od;
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

	public Party_Accounts() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		op = new Object_Parties();

	}

	public void Create_Party_Accounts(String TSID, ICallback icallback) throws Exception {

		Thread.sleep(4000);
		try {
			od.parties_AccountsTab.click();
			System.out.println("Account tab open");
		}
		catch(Exception e) {
			handleElementClickException(od.parties_AccountsTab);
		}
		try {
		od.parties_AddAccounts.click();
	}
	catch(Exception e) {
		Thread.sleep(1000);
		handleElementClickException(od.parties_AddAccounts1);
	}
		applyExplicitWaitsUntilElementClickable(od.parties_PaymentSystem, Duration.ofSeconds(5));
		od.parties_PaymentSystem.click();
		String paymentInstrument = externalData.getFieldData(TSID, "Party", "Accounts-Payment System");
		icallback.handleCallback("DEAL_PARTY_ACCONT_PAYMENT_INSTRUMENT", paymentInstrument);

	}

	public void verifyPartyAccountGotUpdated() throws Exception {
		applyExplicitWaitsUntilElementClickable(op.parties_PartyTab, Duration.ofSeconds(20));
		op.parties_PartyTab.click();
		op.parties_editPartyButton.click();
		try {
			od.parties_AccountsTab.click();
		}
		catch(Exception e) {
			handleElementClickException(od.parties_AccountsTab);
		}
		Thread.sleep(2000);
		ArrayList<String> accounts = new ArrayList<>();
		for(WebElement i : od.parties_AccountsList) {
			String a = i.getText();
			System.out.println(a);
			accounts.add(a);
			
		}
		Assert.assertTrue(accounts.contains("ICICI1205"));
	}

	public void OffBoardTheAccount(String TSID) throws Exception {
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
		WebElement acc = driver.findElement(By.xpath("//div[@title='ICICI1205']/../../..//div[@id='ic-generic-menu-showMenu-v1']"));
		acc.click();
		op.parties_showMenuDeleteButton.click();
		op.PartyMaker_YesButton.click();
		Thread.sleep(2000);
		op.parties_OkButton.click();
	}

	public void verifyAccountIsOffBoarded() throws Exception {
		
		applyExplicitWaitsUntilElementClickable(op.parties_PartyTab, Duration.ofSeconds(20));
		op.parties_PartyTab.click();
		op.parties_editPartyButton.click();
		try {
			od.parties_AccountsTab.click();
		}
		catch(Exception e) {
			handleElementClickException(od.parties_AccountsTab);
		}
		Thread.sleep(2000);
		ArrayList<String> accounts = new ArrayList<>();
		for(WebElement i : od.parties_AccountsList) {
			String a = i.getText();
			System.out.println(a);
			accounts.add(a);
		}
		System.out.println(accounts.size());
	boolean status=	accounts.contains("ICICI1205");
		System.out.println(status);
		
	}

	public void attemptToOffboardAccount(String TSID) throws Exception, IOException {
		SoftAssert assert1=new SoftAssert();
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
		WebElement acc = driver.findElement(By.xpath("//div[@title='ICICI1205']/../../..//div[@id='ic-generic-menu-showMenu-v1']"));
		acc.click();
		op.parties_showMenuDeleteButton.click();
		Thread.sleep(2000);
		op.PartyMaker_YesButton.click();
		Thread.sleep(2000);
		
	
		boolean	 status = isWebElementDisplayed(op.PartyMaker_deleteMessage);
		assert1.assertFalse(status);
		 status = isWebElementDisplayed(op.parties_ErrorMessage);
		 assert1.assertTrue(status);
		 assert1.assertAll("Error message not getting.Party is offboarding");
	}
}
