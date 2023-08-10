package com.upp.handlers;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import callbackInterfaces.ICallback;

public class DealPartiesHandler extends BaseClass {
	public static Object_NewDeal od;
	DropDown dropdown;
	public static ExcelReader externalData;
	public static ScrollTypes scroll;
	public static DateUtils dateutil;
	public String responsibilities;
	public String ecommerce;
	public static Object_Parties op;
	Object_Deal object_deal;
	JavascriptClick js;

	public DealPartiesHandler() {
		od = new Object_NewDeal();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		op = new Object_Parties();
		object_deal = new Object_Deal();
		js=new JavascriptClick(driver);
	}

	public void handleAddNewParty(String TSID, ICallback icallback) throws Exception {
		od.parties_AddnewParty.click();
		od.parties_CustomerID.sendKeys(externalData.getFieldData(TSID, "Party", "Customer Id")+generateRandomString(10));
		od.parties_PartyName.sendKeys(externalData.getFieldData(TSID, "Party", "Party Name")+generateRandomString(10));

		od.parties_Responsibility.click();
		od.parties_Responsibility_dropdown.click();
		od.parties_Remarks.sendKeys(externalData.getFieldData(TSID, "Party", "Remarks"));
		Thread.sleep(1000);
		responsibilities = externalData.getFieldData(TSID, "Party", "Responsibility");
		icallback.handleCallback("RESPONSIBILITIES", responsibilities);
		Thread.sleep(1000);
		ecommerce = externalData.getFieldData(TSID, "Party", "eCommerce Party-checkbox");
		System.out.println(ecommerce);
		if (ecommerce.equalsIgnoreCase("Y")) {
			new EcommerceHandler().handleEcommerce(TSID);
		} else {
			od.parties_BasicNextButton.click();	
		}
		
		try {
		od.parties_AddContact.click();
		}
		catch(Exception e) {
			handleElementClickException(od.parties_AddContact);
		}
		od.parties_ContactName.sendKeys(externalData.getFieldData(TSID, "Party", "Contact Name"));
		if ((externalData.getFieldData(TSID, "Party", "Authorised signatory-check box")).equalsIgnoreCase("Y")) {
			od.parties_AuthrorizedSignatoryYes.click();
		}
		od.parties_Email.sendKeys(externalData.getFieldData(TSID, "Party", "Email"));
		
		try {
			od.parties_AddButton.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_AddButton);
		}
		try {
			od.parties_AccountsTab.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_AccountsTab);
		}

		try {
			od.parties_AddAccounts.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_AddAccounts);
		}
		applyExplicitWaitsUntilElementClickable(od.parties_PaymentSystem, Duration.ofSeconds(15));
		try {
			od.parties_PaymentSystem.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_PaymentSystem);
		}
		String paymentInstrument = externalData.getFieldData(TSID, "Party", "Accounts-Payment System");
		icallback.handleCallback("DEAL_PARTY_ACCONT_PAYMENT_INSTRUMENT", paymentInstrument);
        Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(od.parties_DocumentsTab, Duration.ofSeconds(20));

		try {
			od.parties_DocumentsTab.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_DocumentsTab);
		}
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.parties_AddDocument, Duration.ofSeconds(20));
		try {
			od.parties_AddDocument.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_AddDocument);
		}
		
         Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.parties_DocumentType, Duration.ofSeconds(15));
	     js.click(od.parties_DocumentType);
		
		if (externalData.getFieldData(TSID, "Party", "Document Type").equalsIgnoreCase("Blueprint")) {
			applyExplicitWaitsUntilElementClickable(od.parties_DocumentsType_Blueprint, Duration.ofSeconds(15));
			
			try {
				od.parties_DocumentsType_Blueprint.click();
			} catch (Exception e) {
				handleElementClickException(od.parties_DocumentsType_Blueprint);
			}
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
			applyExplicitWaitsUntilElementClickable(od.payments_Documents_ExecutionDate, Duration.ofSeconds(15));
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

	public void handleLinkedExistingParty(String TSID) throws Exception {

		applyExplicitWaitsUntilElementClickable(op.Party_linkExistingParty, Duration.ofSeconds(5));
		op.Party_linkExistingParty.click();
		op.Party_linkPartyDetails_customerId.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Customer Id"));
		applyExplicitWaitsUntilElementClickable(op.Party_linkPartyDetails_searchButton, Duration.ofSeconds(5));
		op.Party_linkPartyDetails_searchButton.click();
		op.Party_selectPartyCircle.click();
		od.parties_addPartyPlusIcon.click();
		String responsibility = externalData.getFieldData(TSID, "Party", "Responsibility");
		applyExplicitWaitsUntilElementClickable(od.parties_Responsibility, Duration.ofSeconds(5));
		od.parties_Responsibility.click();
		od.parties_Responsibility_dropdown.click();
		Thread.sleep(1000);
		if (responsibility.equalsIgnoreCase("Acquiree")) {
			object_deal.party_basic_details_acquiree.sendKeys("Test");
			object_deal.party_basic_details_acquiree.clear();
			object_deal.party_basic_details_acquiree.sendKeys("Test");
			Thread.sleep(500);
			object_deal.party_basic_details_acquiree_dasfField.sendKeys("2");
		}
		od.parties_BasicNextButton.click();
		applyExplicitWaitsUntilElementClickable(od.parties_AddContact, Duration.ofSeconds(10));
		od.parties_AddContact.click();
		od.parties_LinkPartyCheckboxIcon.click();
		od.parties_ConatctPlusIcon.click();
		Thread.sleep(1500);
		applyExplicitWaitsUntilElementClickable(od.parties_AccountsTab, Duration.ofSeconds(10));
		try {
			od.parties_AccountsTab.click();
		}
		catch(Exception e) {
			handleElementClickException(od.parties_AccountsTab);
		}
		applyExplicitWaitsUntilElementClickable(od.parties_AddAccounts, Duration.ofSeconds(10));
		od.parties_AddAccounts.click();
		od.parties_LinkPartyCheckboxIcon.click();
		od.parties_AccountPlusIcon.click();
		applyExplicitWaitsUntilElementClickable(od.parties_DocumentsTab, Duration.ofSeconds(10));
		try {
			od.parties_DocumentsTab.click();
		
		}
		catch(Exception e) {
			handleElementClickException(od.parties_DocumentsTab);
		}
		applyExplicitWaitsUntilElementClickable(od.parties_AddDocument, Duration.ofSeconds(10));
		od.parties_AddDocument.click();
		od.parties_LinkPartyCheckboxIcon.click();
		od.parties_DocumentsPlusIcon.click();

	}
}