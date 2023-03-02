package com.upp.handlers;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

import callbackinterfaces.ICallback;

public class EcommerceHandler extends BaseClass {
	public static Object_NewDeal od;
	DropDown dropdown;
	public ExcelReader externalData;

	public EcommerceHandler() {
		od = new Object_NewDeal();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
	}

	public void handleEcommerce(String TSID) throws Exception {
		od.parties_eCommerceCheckbox.click();
		od.parties_ParticipantId.sendKeys(externalData.getFieldData(TSID, "Party", "Participant Id"));
		od.parties_BasicNextButton.click();
		dropdown.selectByVisibleText(od.ecommerce_status, "Active");
		od.ecommerce_validFrom.click();
		od.startDate.click();
		od.ecommerce_validTill.click();
		od.endDate.click();
		od.ecommerceAccount.click();
	}

}
