package com.upp.handlers;

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

public class DealGroupAttributesHandler extends BaseClass implements ICallback {
	public static Object_NewDeal od;
	DropDown dropdown;

	public DealGroupAttributesHandler() {
		od = new Object_NewDeal();
		dropdown = new DropDown(driver);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

	}

	public void handleFlexibleFundding() {
		String spiltType = "Static ";
		dropdown.selectByVisibleText(od.newDeal_spiltTypeAttribute, spiltType);
		String commissionType = "Percentage ";
		dropdown.selectByVisibleText(od.newDeal_commissionTypeAttribute, commissionType);
		String percentageCommission = "70";
		od.newDeal_percentageCommissionAttribute.sendKeys(percentageCommission);
		System.out.println("call back Flexible funding");
	}

	public void handleoneProduct() {
		System.out.println("call back for product 1");
	}
	
}
