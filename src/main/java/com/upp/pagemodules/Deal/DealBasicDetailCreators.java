package com.upp.pagemodules.Deal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
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

public class DealBasicDetailCreators extends BaseClass {

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

	public DealBasicDetailCreators() {

		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();

	}

	public static void createDealBasicDetails(String TSID, ICallback icallback) throws Exception {

		od.deal_SideMenuIcon.click();
		od.newDealButton.click();
		od.newDeal.sendKeys(externalData.getFieldData(TSID, "Basic Details", "Deal Name"));
		productName = externalData.getFieldData(TSID, "Basic Details", "Product");
		System.out.println(productName);
		if (!(productName.equals("1.0"))) {
			od.deal_Product.sendKeys(productName);
			dropdown.selectByVisibleText(od.deal_Product, productName);
			// dropdown.selectByValue(od.deal_Product, "T1142");
			icallback.handleCallback("PRODUCT_NAME", productName);
		}
		dropdown.selectByVisibleText(od.businessSegmentDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Business Segment"));
		dropdown.selectByVisibleText(od.countryIndiaDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Country"));
		String input = externalData.getFieldData(TSID, "Basic Details", "Transactions to non-registered beneficiaries");
		if ((input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes")) && !od.beneficiariesCheckBox.isSelected()) {
			od.beneficiariesCheckBox.click();
		}
		String ProcessingUnits = externalData.getFieldData(TSID, "Basic Details", "Processing Units");

		if (!(ProcessingUnits.equalsIgnoreCase("Select All"))) {
			od.deals_ProcessingUnits.click();
			od.deals_selectAll.click();
			od.deals_ProcessingUnitsSearch.sendKeys(ProcessingUnits);
			By ProcessingUnit = By.xpath("//div[contains(text(),'" + ProcessingUnits + "')]");
			driver.findElement(ProcessingUnit).click();
		}
		input = externalData.getFieldData(TSID, "Basic Details", "Transaction Categories");
		od.transactionCategory.sendKeys(input);
		By transaction_Category_Option = By.xpath("//div[contains(text(),'" + input + "')]");
		// applyExplicitWaitsUntilElementVisible(transaction_Category_Option, 10);
		driver.findElement(transaction_Category_Option).click();
		od.saveButton.click();
		input = externalData.getFieldData(TSID, "Basic Details", "Party Responsibilities");
		od.partyResponsibility.sendKeys(input);
		By party_Responsibility_Option = By.xpath("//div[contains(text(),'" + input + "')]");
		// applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 10);
		driver.findElement(party_Responsibility_Option).click();

		try {
			if (od.responsibilityAttributePopup.isDisplayed()) {
				od.saveButton.click();
			}
		} catch (Exception e) {
			System.out.println("Normal flow ");
		}
		od.nextBtn.click();
	}

}
