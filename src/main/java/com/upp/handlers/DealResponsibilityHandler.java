package com.upp.handlers;

import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
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
	
}
