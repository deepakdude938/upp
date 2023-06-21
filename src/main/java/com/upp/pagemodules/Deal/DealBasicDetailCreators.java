package com.upp.pagemodules.Deal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
	public static CommonUtils commonutils;

	public DealBasicDetailCreators() {

		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		commonutils = new CommonUtils(driver);

	}

	public static void createDealBasicDetails(String TSID, ICallback icallback) throws Exception {
		Thread.sleep(3000);
		od.deal_SideMenuIcon.click();
		od.newDealButton.click();
		od.newDeal.sendKeys(externalData.getFieldData(TSID, "Basic Details", "Deal Name"));
		productName = externalData.getFieldData(TSID, "Basic Details", "Product");

		if (!(productName.equals("1.0"))) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			if(TSID.equalsIgnoreCase("TS25")) {
				od.deal_Product.sendKeys(config_productName);
				dropdown.selectByVisibleText(od.deal_Product, config_productName);
			}
			else {
			od.deal_Product.sendKeys(productName);
			dropdown.selectByVisibleText(od.deal_Product, productName);
			}
			// dropdown.selectByValue(od.deal_Product, "T1142");
			icallback.handleCallback("PRODUCT_NAME", productName);
		}
		
		try {
			String endDate=externalData.getFieldData(TSID, "Basic Details", "Ends On");
				if(endDate.equalsIgnoreCase("Default Today")) {
					
				od.basicDetails_EndDate.click();
				String day = dateutil.getDay();
				By excecutionDay = By.xpath(
						"//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"
								+ day + "']");
				applyExplicitWaitsUntilElementVisible(excecutionDay, 5);
				driver.findElement(excecutionDay).click();
				}
			}
			catch(NullPointerException e) {
				
				}
		dropdown.selectByVisibleText(od.businessSegmentDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Business Segment"));

		dropdown.selectByVisibleText(od.countryIndiaDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Country"));

		String input = externalData.getFieldData(TSID, "Basic Details", "Transactions to non-registered beneficiaries");
		if ((input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes"))) {
			od.beneficiariesCheckBox.click();
		}

		String ProcessingUnits = externalData.getFieldData(TSID, "Basic Details", "Processing Units");

		if (!(ProcessingUnits.equalsIgnoreCase("Select All"))) {
			od.deals_ProcessingUnits.click();
			applyExplicitWaitsUntilElementClickable(od.deals_selectAll, Duration.ofSeconds(10));
			od.deals_selectAll.click();
			od.deals_ProcessingUnitsSearch.sendKeys(ProcessingUnits);
			By ProcessingUnit = By.xpath("//div[contains(text(),'" + ProcessingUnits + "')]");
			driver.findElement(ProcessingUnit).click();
		}

		input = externalData.getFieldData(TSID, "Basic Details", "Transaction Categories");
		od.transactionCategory.click();
		od.transactionCategoryInput.sendKeys(input);
		By transaction_Category_Option = By.xpath("(//div[contains(text(),'" + input + "')])[1]");
		// applyExplicitWaitsUntilElementVisible(transaction_Category_Option, 10);
		driver.findElement(transaction_Category_Option).click();
		if (!od.basicDetails_SaveButton_List.isEmpty()) {
			od.saveButton.click();
		}
		input = externalData.getFieldData(TSID, "Basic Details", "Party Responsibilities");
		Thread.sleep(1000);
			od.partyResponsibility.click();
			Thread.sleep(1500);
		od.partyResponsibilityinput.sendKeys(input);
		Thread.sleep(1000);
		By party_Responsibility_Option = By.xpath("//div[contains(text(),'" + input + "')]");
		applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 5);
		driver.findElement(party_Responsibility_Option).click();

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			if (od.responsibilityAttributePopup.isDisplayed()) {
				applyExplicitWaitsUntilElementClickable(od.saveButton, Duration.ofSeconds(10));
				od.saveButton.click();
			}
		} catch (Exception e) {
			System.out.println("Normal flow ");
		}
		
		od.deals_partyResponsibilitiesText.click();

		try {
			input = externalData.getFieldData(TSID, "Basic Details", "Contact");

			if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes")) {

				od.deals_contactConfigure.click();
				String contactName = externalData.getFieldData(TSID, "Basic Details", "Contact-Name");
				System.out.println(contactName);
				od.deals_contactNameTextBox.sendKeys(contactName);
				od.deals_contactNameSearch.click();
				applyExplicitWaitsUntilElementClickable(od.deals_contactCheckBox, Duration.ofSeconds(10));
				od.deals_contactCheckBox.click();
				od.deals_contactUpdate.click();

			}
		} catch (NullPointerException e) {

		}
		applyExplicitWaitsUntilElementClickable(od.nextBtn, Duration.ofSeconds(15));
		od.nextBtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

}
