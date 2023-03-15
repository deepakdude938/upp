package com.upp.handlers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

public class EcommerceHandler extends BaseClass {
	public static Object_Deal od;
	DropDown dropdown;
	public ExcelReader externalData;
	public static JavascriptClick jsClick;
	public static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	public static final int RANDOM_STRING_LENGTH = 10;

	public EcommerceHandler() {
		od = new Object_Deal();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
		jsClick = new JavascriptClick(driver);
	}

	public void handleEcommerce(String TSID) throws Exception {
		od.parties_eCommerceCheckbox.click();
		StringBuffer StrBuffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = random.nextInt(CHAR_LIST.length());
			;
			char ch = CHAR_LIST.charAt(number);
			StrBuffer.append(ch);
		}
		String PraticipantId = StrBuffer.toString();
		od.parties_ParticipantId.sendKeys("Participant" + PraticipantId);
		// od.parties_BasicNextButton.click();
		dropdown.selectByVisibleText(od.ecommerce_status, "Active");
		od.ecommerce_validFrom.click();
		od.startDate.click();
		// od.ecommerce_validTill.click();
		// od.endDate.click();
		String hiddenClass = od.accountNumbers.getAttribute("class");
		// jsClick.click(od.ecommerceSecondAccount);
		if (hiddenClass.contains("ag-hidden")) {
			jsClick.click(od.ecommerceSecondAccount);
			System.out.println("First = " + od.accountNumbers.getAttribute("class"));
		} else {
			System.out.println("Secound = " + od.ecommerceSecondAccount.isSelected());
			jsClick.click(od.ecommerceFirstAccount);
		}
		od.parties_BasicNextButton.click();

	}

}
