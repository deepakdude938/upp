package com.upp.pagemodules;

import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class BasicDetails extends BaseClass{
	
	
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int waitingTime = 5;
	public static String productName;

	public BasicDetails() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
	}
	
	
	public void createDealBasicDetails(String TSID, ICallback icallback) throws Exception {

		od.deal_SideMenuIcon.click();
		od.newDealButton.click();
		od.newDeal.sendKeys(externalData.getFieldData(TSID, "Basic Details", "Deal Name"));
		productName = externalData.getFieldData(TSID, "Basic Details", "Product");
	    dropdown.selectByVisibleText(od.deal_Product, "A");
		
		
		dropdown.selectByVisibleText(od.businessSegmentDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Business Segment"));
		od.deal_Product.sendKeys(productName);
		
		dropdown.selectByVisibleText(od.deal_Product, productName);
		
		// dropdown.selectByValue(od.deal_Product, "T1142");
		icallback.handleCallback("PRODUCT_NAME", productName);
		
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
			By ProcessingUnit = By.xpath(
					"//div[contains(@class,'ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"
							+ ProcessingUnits + "']");
			driver.findElement(ProcessingUnit).click();
		}

		input = externalData.getFieldData(TSID, "Basic Details", "Transaction Categories");
		od.transactionCategory.click();
		 od.transactionCategoryInput.sendKeys(input);
		By transaction_Category_Option = By.xpath(
				"//div[contains(@class,'ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"
						+ input + "']");
		applyExplicitWaitsUntilElementVisible(transaction_Category_Option, 10);
		driver.findElement(transaction_Category_Option).click();
		od.saveButton.click();
		input = externalData.getFieldData(TSID, "Basic Details", "Party Responsibilities");
		od.partyResponsibility.click();
		 od.partyResponsibilityinput.sendKeys(input);
		By party_Responsibility_Option = By.xpath(
				"//div[contains(@class,'ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"
						+ input + "']");
		applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 10);
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
