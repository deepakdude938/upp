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

public class DealResponsibilityHandler extends BaseClass implements ICallback {
	public static Object_NewDeal od;
	DropDown dropdown;

	public DealResponsibilityHandler() {
		od = new Object_NewDeal();
		dropdown = new DropDown(driver);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

	}

	public void handleMarchant() {
		dropdown.selectByVisibleText(od.parties_CommissionPlan, "Merchant");
	}
	
}
