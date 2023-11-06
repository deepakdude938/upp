package com.upp.handlers;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.Property;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class DealResponsibilityHandler extends BaseClass implements ICallback {
	public static Object_Deal od;
	DropDown dropdown;

	public DealResponsibilityHandler() {
		od = new Object_Deal();
		dropdown = new DropDown(driver);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

	}

	public void handleMarchant() {
		System.out.println("Merchant callback");
	}

	public void handleAcquiree() throws Exception {
		Thread.sleep(1000);
		od.party_basic_details_acquiree.sendKeys("Test");
		od.party_basic_details_acquiree.clear();
		od.party_basic_details_acquiree.sendKeys("Test");
		Thread.sleep(500);
		od.party_basic_details_acquiree_dasfField.sendKeys("2");
	}

	public void handleAutomationAttributes() throws Exception {

		od.party_basic_details_automationAttribute_partyname.sendKeys("Party");
		od.party_basic_details_automationAttribute_panno.sendKeys("12345543");
		od.party_basic_details_automationAttribute_dob.click();
		String day = DateUtils.getDay();
		By excecutionDay = By.xpath(
				"//td[contains(@class,'ui-day') and not(contains(@class,'ui-calendar-invalid')) and not(contains(@class,'ui-calendar-outFocus')) and normalize-space()='"
						+ day + "']");
		applyExplicitWaitsUntilElementVisible(excecutionDay, 5);
		driver.findElement(excecutionDay).click();
		Thread.sleep(1000);
		object = new ArrayList();
		for (WebElement a : od.party_basic_details_ResponsibilityAttributes) {
			object.add(a.getText().trim());
		}
		object.sort(null);
	}

	public void handleBuyer() throws Exception {
		Thread.sleep(1000);
		System.out.println("Inside buyer");
		od.party_basic_details_buyer_field.clear();
		od.party_basic_details_buyer_field.sendKeys("Buyer");
		Thread.sleep(1000);
		od.party_basic_details_buyer.click();
	}

	public void handleSeller() throws Exception {
		Thread.sleep(1000);
		System.out.println("Inside seller");
		od.party_basic_details_buyer_field.clear();
		od.party_basic_details_buyer_field.sendKeys("Seller");
		Thread.sleep(1000);
		od.party_basic_details_seller.click();
	}
}
