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

public class Party_Documents extends BaseClass {

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

	public Party_Documents() {

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

	public void Create_Party_Document(String TSID, ICallback icallback) throws Exception {
		applyExplicitWaitsUntilElementClickable(od.parties_DocumentsTab, Duration.ofSeconds(20));

		try {
			od.parties_DocumentsTab.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_DocumentsTab);
		}
		try {
			od.parties_AddDocument.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_AddDocument);
		}

		applyExplicitWaitsUntilElementClickable(od.parties_DocumentType, Duration.ofSeconds(5));
		od.parties_DocumentType.click();
		if (externalData.getFieldData(TSID, "Party", "Document Type").equalsIgnoreCase("Blueprint")) {
			applyExplicitWaitsUntilElementClickable(od.parties_DocumentsType_Blueprint, Duration.ofSeconds(5));
			od.parties_DocumentsType_Blueprint.click();
			dropdown.selectByVisibleText(od.parties_DocumentNature1,
					externalData.getFieldData(TSID, "Party", "Document Nature"));
			od.parties_DocumentsAddButton.click();
		}

		if (externalData.getFieldData(TSID, "Party", "Document Type").equalsIgnoreCase("Architect certificate")) {
			applyExplicitWaitsUntilElementClickable(od.parties_DocumentsType_Architect_certificate,
					Duration.ofSeconds(5));
			od.parties_DocumentsType_Architect_certificate.click();
			dropdown.selectByVisibleText(od.parties_DocumentNature1,
					externalData.getFieldData(TSID, "Party", "Document Nature"));
			applyExplicitWaitsUntilElementClickable(od.payments_Documents_ExecutionDate, Duration.ofSeconds(5));
			od.payments_Documents_ExecutionDate.click();
			String day = dateutil.getDay();
			By excecutionDay = By.xpath(
					"//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"
							+ day + "']");
			applyExplicitWaitsUntilElementVisible(excecutionDay, 5);
			driver.findElement(excecutionDay).click();
			od.parties_DocumentsAddButton.click();
		}

	}
}
