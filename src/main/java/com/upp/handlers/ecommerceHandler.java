package com.upp.handlers;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pageobjects.Object_Deal;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class ecommerceHandler extends BaseClass {
	public static Object_Deal od;
	DropDown dropdown;
	public ExcelReader externalData;
	public static JavascriptClick jsClick;
	DashBoard_Module dm = new DashBoard_Module();

	public ecommerceHandler() {
		od = new Object_Deal();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
		jsClick = new JavascriptClick(driver);
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

		String sorceAccount = new DealPage(dm).sourceAccountNo;
		String toAccount = new DealPage(dm).toaccountNo;
		By account1 = By.xpath("//span[@title='" + sorceAccount + "']");
		driver.findElement(account1).click();
		By account2 = By.xpath("//span[@title='" + toAccount + "']");
				od.ecommerceSave.click();
	}

}
