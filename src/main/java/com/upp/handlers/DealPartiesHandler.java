package com.upp.handlers;

import java.time.Duration;

import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_Deal;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import callbackInterfaces.ICallback;

public class DealPartiesHandler extends BaseClass {
	public static Object_Deal od;
	DropDown dropdown;
	public static ExcelReader externalData;
	public static ScrollTypes scroll;
	public static DateUtils dateutil;
	public String responsibilities;
	public String ecommerce;

	public DealPartiesHandler() {
		od = new Object_Deal();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
	}

	public void handleAddNewParty(String TSID, ICallback icallback) throws Exception {
		od.parties_AddnewParty.click();
		od.parties_CustomerID.sendKeys(externalData.getFieldData(TSID, "Party", "Customer Id"));
		od.parties_PartyName.sendKeys(externalData.getFieldData(TSID, "Party", "Party Name"));
		od.parties_Responsibility.click();
		od.parties_Responsibility_dropdown.click();
		od.parties_Remarks.sendKeys(externalData.getFieldData(TSID, "Party", "Remarks"));
		responsibilities = externalData.getFieldData(TSID, "Basic Details", "Party Responsibilities");
		icallback.handleCallback("RESPONSIBILITIES", responsibilities);
		ecommerce = externalData.getFieldData(TSID, "Party", "eCommerce Party-checkbox");
		if (ecommerce.equalsIgnoreCase("Y")) {
			new ecommerceHandler().handleEcommerce(TSID);
		} else {
			od.parties_BasicNextButton.click();
			od.parties_AddContact.click();
			od.parties_ContactName.sendKeys(externalData.getFieldData(TSID, "Party", "Contact Name"));
			if ((externalData.getFieldData(TSID, "Party", "Authorised signatory-check box")).equalsIgnoreCase("Y")) {
				od.parties_AuthrorizedSignatoryYes.click();
			}
			od.parties_Email.sendKeys(externalData.getFieldData(TSID, "Party", "Email"));
			od.parties_AddButton.click();
			od.parties_AccountsTab.click();
			od.parties_AddAccounts.click();
			applyExplicitWaitsUntilElementClickable(od.parties_PaymentSystem, Duration.ofSeconds(5));
			od.parties_PaymentSystem.click();
			applyExplicitWaitsUntilElementClickable(od.parties_PaymentSystem_BT, Duration.ofSeconds(5));
			od.parties_PaymentSystem_BT.click();
			applyExplicitWaitsUntilElementClickable(od.parties_beneficiaryBankBic, Duration.ofSeconds(5));
			od.parties_beneficiaryBankBic.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Bank Bic"));
			applyExplicitWaitsUntilElementClickable(od.parties_BeneficiaryCountry, Duration.ofSeconds(5));
			dropdown.selectByVisibleText(od.parties_BeneficiaryCountry,
					externalData.getFieldData(TSID, "Party", "Beneficiary Country"));
			applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
			scroll.scrollInToView(od.parties_paymentTo);
			od.parties_paymentTo.sendKeys(externalData.getFieldData(TSID, "Party", "To"));
			scroll.scrollInToView(od.parties_beneficiaryCurrency);
			od.parties_beneficiaryCurrency.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Currency"));
			applyExplicitWaitsUntilElementClickable(od.parties_partyAccountsAddButton, Duration.ofSeconds(5));
			od.parties_partyAccountsAddButton.click();
			applyExplicitWaitsUntilElementClickable(od.parties_DocumentsTab, Duration.ofSeconds(5));
			od.parties_DocumentsTab.click();
			od.parties_AddDocument.click();
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
				applyExplicitWaitsUntilElementClickable(od.payments_ExecutionDate, Duration.ofSeconds(5));
				od.payments_ExecutionDate.click();
				String day = dateutil.getDay();
				By excecutionDay = By.xpath("//a[contains(text(),'" + day + "')]");
				applyExplicitWaitsUntilElementVisible(excecutionDay, 5);
				driver.findElement(excecutionDay).click();
				od.parties_DocumentsAddButton.click();
			}
		}
	}

	public void handleLinkedExistingParty(String TSID) {

	}
}