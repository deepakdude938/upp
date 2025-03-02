package com.upp.pagemodules.Deal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.Create_ODP_Account_Api;
import com.upp.odp.utils.Create_ODP_Virtual_Account_Api;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;
import freemarker.template.utility.DateUtil;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DealAccountCreator extends BaseClass {

	public static Object_Deal od;
	public static Object_NewDeal od1;
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
	Create_ODP_Account_Api odp_account_api;

	public DealAccountCreator() {

		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		odp_account_api=new Create_ODP_Account_Api();
		od1 = new Object_NewDeal();

	}

	public String createNewAccount(String TSID) throws Exception {

		odpAccount.createAccount(TSID);
		accDetails = odpAccount.popelmnt(OdpApi.stack1);
		System.out.println("the account no is" + accDetails.getAccno());
		String accountNo = accDetails.getAccno();
		try {
		accountList.add(accountNo);
		}
		catch(Exception c) {
		}
		applyExplicitWaitsUntilElementClickable(od.country, Duration.ofSeconds(70));
		dropdown.selectByVisibleText(od.country, externalData.getFieldData(TSID, "Accounts", "Country"));
		dropdown.selectByVisibleText(od.currency, externalData.getFieldData(TSID, "Accounts", "Currency"));

		String physicalYesOrNo = externalData.getFieldData(TSID, "Accounts", "Physical");
		if (physicalYesOrNo.equalsIgnoreCase("Yes")) {
			dropdown.selectByVisibleText(od.physical, "Physical");
		} else {
			dropdown.selectByVisibleText(od.physical, "Virtual");
		}
		applyExplicitWaitsUntilElementClickable(od.searchTextBox, Duration.ofSeconds(5));
		od.searchTextBox.sendKeys(accountNo);
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.searchButton, Duration.ofSeconds(15));
		od.searchButton.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.accounts_addAccount, Duration.ofSeconds(45));
		try {
			od.accounts_addAccount.click();
		}
		catch(Exception e) {
			handleElementClickException(od.accounts_addAccount);
		}
		
		By account = By.xpath("//table[@class='acc-tbl ng-star-inserted']//tr//td[normalize-space()='"+accountNo+"']");
		try {
			applyExplicitWaitsUntilElementVisible(account, 5);
		}
		catch(Exception n) {
			od.accounts_addAccount.click();
			applyExplicitWaitsUntilElementVisible(account, 5);
		}

		return accountNo;
	}
	
	public String createNewAccount_ODP_From_ExcelSheet(String TSID) throws Exception {
		
		accDetails = odpAccount.popelmnt(Create_ODP_Account_Api.stack1);
		System.out.println("the account no is " + accDetails.getAccno());
		
		String accountNo = accDetails.getAccno();
		physical_Account_Number=accountNo;
		applyExplicitWaitsUntilElementClickable(od.country, Duration.ofSeconds(70));
		dropdown.selectByVisibleText(od.country, externalData.getFieldData(TSID, "Accounts", "Country"));
		dropdown.selectByVisibleText(od.currency, externalData.getFieldData(TSID, "Accounts", "Currency"));
		String physicalYesOrNo = externalData.getFieldData(TSID, "Accounts", "Physical");
		if (physicalYesOrNo.equalsIgnoreCase("Yes")) {
			dropdown.selectByVisibleText(od.physical, "Physical");
		} else {
			dropdown.selectByVisibleText(od.physical, "Virtual");
		}

		applyExplicitWaitsUntilElementClickable(od.searchTextBox, Duration.ofSeconds(5));
		od.searchTextBox.sendKeys(accountNo);
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.searchButton, Duration.ofSeconds(5));
		od.searchButton.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.accounts_addAccount, Duration.ofSeconds(45));
		try {
			od.accounts_addAccount.click();
		}
		catch(Exception e) {
			handleElementClickException(od.accounts_addAccount);
		}
		Thread.sleep(2000);
		return accountNo;
	}
	
public String OnBoard_Virtual_Account(String TSID) throws Exception {
		
		accDetails = odpAccount.popelmnt(Create_ODP_Virtual_Account_Api.stack1);
		System.out.println("the account no is" + accDetails.getAccno());
		
		String accountNo = accDetails.getAccno();
		applyExplicitWaitsUntilElementClickable(od.country, Duration.ofSeconds(70));
		dropdown.selectByVisibleText(od.country, externalData.getFieldData(TSID, "Accounts", "Country"));
		dropdown.selectByVisibleText(od.currency, externalData.getFieldData(TSID, "Accounts", "Currency"));
		String physicalYesOrNo = externalData.getFieldData(TSID, "Accounts", "Physical");
		if (physicalYesOrNo.equalsIgnoreCase("Yes")) {
			dropdown.selectByVisibleText(od.physical, "Physical");
		} else {
			dropdown.selectByVisibleText(od.physical, "Virtual");
		}
		
		applyExplicitWaitsUntilElementClickable(od.searchTextBox, Duration.ofSeconds(5));
		od.searchTextBox.sendKeys(accountNo);
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.searchButton, Duration.ofSeconds(5));
		od.searchButton.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.accounts_addAccount, Duration.ofSeconds(45));
		try {
			od.accounts_addAccount.click();
		}
		catch(Exception e) {
			handleElementClickException(od.accounts_addAccount);
		}
		Thread.sleep(2000);
		return accountNo;
	}

	public void addContextualize() throws Exception {
		applyExplicitWaitsUntilElementClickable(od.accounts_Contextualize, Duration.ofSeconds(5));
		od.accounts_Contextualize.click();
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_Dropdown, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.contextualize_Dropdown,"Payment Profile");
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_AddButton, Duration.ofSeconds(5));
		od.contextualize_AddButton.click();
		
		Thread.sleep(1000);
		jsClick.click(od.contextualize_RightArrow);
		
		Thread.sleep(1000);
		jsClick.click(od.contextualize_RightArrow);
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_LT, Duration.ofSeconds(5));
		od1.contextualize_LT.click();
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_PaymentType, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.contextualize_PaymentType,"IBFT");
		Thread.sleep(1000);
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_ChargeBearer, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.contextualize_ChargeBearer,"BEN");
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od1.contextualize_DoneButton, Duration.ofSeconds(5));
	    od1.contextualize_DoneButton.click();
	    od1.account_OK_Button.click();
	    
	    Thread.sleep(1000);
		
	}
public String Onboard_account_And_Add_Account_Key(String TSID) throws Exception {
		
		accDetails = odpAccount.popelmnt(Create_ODP_Account_Api.stack1);
		System.out.println("the account no is " + accDetails.getAccno());
		
		String accountNo = accDetails.getAccno();
		physical_Account_Number=accountNo;
		applyExplicitWaitsUntilElementClickable(od.country, Duration.ofSeconds(70));
		dropdown.selectByVisibleText(od.country, externalData.getFieldData(TSID, "Accounts", "Country"));
		dropdown.selectByVisibleText(od.currency, externalData.getFieldData(TSID, "Accounts", "Currency"));
		String physicalYesOrNo = externalData.getFieldData(TSID, "Accounts", "Physical");
		if (physicalYesOrNo.equalsIgnoreCase("Yes")) {
			dropdown.selectByVisibleText(od.physical, "Physical");
		} else {
			dropdown.selectByVisibleText(od.physical, "Virtual");
		}

		applyExplicitWaitsUntilElementClickable(od.searchTextBox, Duration.ofSeconds(5));
		od.searchTextBox.sendKeys(accountNo);
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.searchButton, Duration.ofSeconds(5));
		od.searchButton.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.accounts_addAccount, Duration.ofSeconds(45));
		try {
			od.accounts_addAccount.click();
		}
		catch(Exception e) {
			handleElementClickException(od.accounts_addAccount);
		}
		Thread.sleep(2000);
		
		Thread.sleep(1500);
		
		//edit account and add accountKey code
		scroll.scrollInToView(od.account_edit_icon);
		jsClick.click(od.account_edit_icon);
		Thread.sleep(1500);
		applyExplicitWaitsUntilElementClickable(od.account_key_field, Duration.ofSeconds(5));
		scroll.scrollInToView(od.account_key_field);
		od.account_key_field.sendKeys(externalData.getFieldData(TSID, "Accounts", "Account_Key"));
		
		Thread.sleep(1000);
		try {
			od.accounts_addAccount.click();
		}
		catch(Exception e) {
			handleElementClickException(od.accounts_addAccount);
		}
		Thread.sleep(1500);
		applyExplicitWaitsUntilElementClickable(od.account_OK_Button, Duration.ofSeconds(5));
		od.account_OK_Button.click();
		Thread.sleep(1500);
		return accountNo;
	}
public String Onboard_account_And_Add_Second_Account_Key(String TSID) throws Exception {
	
	accDetails = odpAccount.popelmnt(Create_ODP_Account_Api.stack1);
	System.out.println("the account no is " + accDetails.getAccno());
	
	String accountNo = accDetails.getAccno();
	physical_Account_Number=accountNo;
	applyExplicitWaitsUntilElementClickable(od.country, Duration.ofSeconds(70));
	dropdown.selectByVisibleText(od.country, externalData.getFieldData(TSID, "Accounts", "Country"));
	dropdown.selectByVisibleText(od.currency, externalData.getFieldData(TSID, "Accounts", "Currency"));
	String physicalYesOrNo = externalData.getFieldData(TSID, "Accounts", "Physical");
	if (physicalYesOrNo.equalsIgnoreCase("Yes")) {
		dropdown.selectByVisibleText(od.physical, "Physical");
	} else {
		dropdown.selectByVisibleText(od.physical, "Virtual");
	}

	applyExplicitWaitsUntilElementClickable(od.searchTextBox, Duration.ofSeconds(5));
	od.searchTextBox.sendKeys(accountNo);
	Thread.sleep(1000);
	applyExplicitWaitsUntilElementClickable(od.searchButton, Duration.ofSeconds(5));
	od.searchButton.click();
	Thread.sleep(1000);
	applyExplicitWaitsUntilElementClickable(od.accounts_addAccount, Duration.ofSeconds(45));
	try {
		od.accounts_addAccount.click();
	}
	catch(Exception e) {
		handleElementClickException(od.accounts_addAccount);
	}
	Thread.sleep(2000);
	
	Thread.sleep(1500);
	
	//edit account and add accountKey code
	scroll.scrollInToView(od.account_edit_icon2);
	jsClick.click(od.account_edit_icon2);
	Thread.sleep(1500);
	applyExplicitWaitsUntilElementClickable(od.account_key_field, Duration.ofSeconds(5));
	scroll.scrollInToView(od.account_key_field);
	od.account_key_field.sendKeys(externalData.getFieldData(TSID, "Accounts", "Account_Key"));
	
	Thread.sleep(1000);
	try {
		od.accounts_addAccount.click();
	}
	catch(Exception e) {
		handleElementClickException(od.accounts_addAccount);
	}
	Thread.sleep(1500);
	applyExplicitWaitsUntilElementClickable(od.account_OK_Button, Duration.ofSeconds(5));
	od.account_OK_Button.click();
	Thread.sleep(1500);
	return accountNo;
}
}
