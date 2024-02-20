package com.upp.pagemodules.Deal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Payment;
import com.upp.pageobjects.Object_Transactions;
import com.upp.stepdefinition.DealPage;
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
import com.upp.utils.CommonUtils;

public class DealEntitlements extends BaseClass {

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
	public static CommonUtils commonutils;
	public Object_Payment op;
	public static Object_Transactions tm;

	public DealEntitlements() {

		od = new Object_NewDeal();
		op = new Object_Payment();
		tm = new Object_Transactions();
		externalData = new ExcelReader();
	}

	public void createDealEntitlements(String TSID) throws Exception {
		od.entitlementsTab.click();
		od.add_Dealentitlements.click();
		Thread.sleep(3000);
		od.currency.click();
		String currency1 = externalData.getFieldData(TSID, "Entitlements", "Currency");
		od.currency.sendKeys(currency1, Keys.ENTER);
		// dropdown.selectByValue(od.currency, " INR ");

		od.rangeFrom.sendKeys(externalData.getFieldData(TSID, "Entitlements", "Range From"));
		od.initiatingContact.click();

		applyExplicitWaitsUntilElementClickable(op.Alerts_contactCheckBox, Duration.ofSeconds(10));
		op.Alerts_contactCheckBox.click();
		op.Alerts_contactUpdate.click();
		od.authorzingContact.click();
		
		applyExplicitWaitsUntilElementClickable(op.Alerts_contactCheckBox, Duration.ofSeconds(10));
		op.Alerts_contactCheckBox.click();
		op.Alerts_contactUpdate.click();
		od.addEntitlements.click();
	}

	public void verifyEntitlementsInTransaction(String TSID, String DealId, String sourceAccno) throws Exception {
		int flag = 0;
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_TransactionIcon, Duration.ofSeconds(15));
		click(tm.transactions_TransactionIcon);
		Thread.sleep(3000);
		click(tm.transactions_TransactionMaker);
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_AddNewButon, Duration.ofSeconds(15));
		jsClick.click(tm.transactions_AddNewButon);
		applyExplicitWaitsUntilElementClickable(tm.transactions_DealId, Duration.ofSeconds(20));
		tm.transactions_DealId.sendKeys(DealId);
		By transactions_DealId = By.xpath("//div[contains(text(),'" + DealId + "')]");
		try {
		driver.findElement(transactions_DealId).click();
		}
		catch(Exception e) {
			Thread.sleep(3000);
			transactions_DealId = By.xpath("//div[contains(text(),'" + DealId + "')]");
			driver.findElement(transactions_DealId).click();
		}
		tm.transactions_SourceAccNo.sendKeys(sourceAccno);
		By transactions_SouceAccno = By.xpath("//div[contains(text(),'" + sourceAccno + "')]");
		try {
		driver.findElement(transactions_SouceAccno).click();
		}
		catch(Exception r) {
			Thread.sleep(3000);
			transactions_SouceAccno = By.xpath("//div[contains(text(),'" + sourceAccno + "')]");
			driver.findElement(transactions_SouceAccno).click();
		}
		jsClick.click(tm.transactions_SubmitButton);
		Thread.sleep(2000);
		try {
		tm.entitlementsIcon.click();
		}
				catch(Exception e) {
					click(tm.entitlementsIcon);
				}
		Thread.sleep(2000);
		if (TSID.equalsIgnoreCase("TS54")) {
			click(tm.entitlements_Account);
			Thread.sleep(1000);
		}
		click(tm.contactDetails);

		String contacts = externalData.getFieldData(TSID, "Party", "Email");
		if (tm.initiatingContact.getText().contains(contacts)) {
			flag = 1;
		}
		Assert.assertEquals(flag, 1);
	}

	public void addAccountEntitlements(String TSID) throws Exception {
		String sourceAccno = DealPage.sourceAccountNo;
		od.entitlementsTab.click();
		od.add_Dealentitlements.click();
		od.entitlements_Account.click();
		od.add_Dealentitlements.click();
		od.entitlements_addAccount.click();
		Thread.sleep(1000);
		By transactions_SouceAccno = By.xpath("//div[contains(text(),'" + sourceAccno + "')]");
		driver.findElement(transactions_SouceAccno).click();
		od.rangeFrom.sendKeys(externalData.getFieldData(TSID, "Entitlements", "Range From"));
		od.initiatingContact.click();

		applyExplicitWaitsUntilElementClickable(op.Alerts_contactCheckBox, Duration.ofSeconds(10));
		op.Alerts_contactCheckBox.click();
		op.Alerts_contactUpdate.click();

		od.authorzingContact.click();
		applyExplicitWaitsUntilElementClickable(op.Alerts_contactCheckBox, Duration.ofSeconds(10));
		op.Alerts_contactCheckBox.click();
		op.Alerts_contactUpdate.click();
		od.addEntitlements.click();
	}
}