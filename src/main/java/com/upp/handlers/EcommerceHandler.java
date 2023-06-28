package com.upp.handlers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

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
		String debitorFalg;
		new Actions(driver).moveToElement(od.parties_eCommerceCheckbox);
		od.parties_eCommerceCheckbox.click();
		StringBuffer StrBuffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = random.nextInt(CHAR_LIST.length());
			;
			char ch = CHAR_LIST.charAt(number);
			StrBuffer.append(ch);
		}
			String PraticipantId = externalData.getFieldData(TSID, "Party", "Participant Id");
			System.out.println(PraticipantId);
			od.parties_ParticipantId.sendKeys(PraticipantId);
			od.parties_BasicNextButton.click();
			dropdown.selectByVisibleText(od.ecommerce_status, externalData.getFieldData(TSID, "Party", "Party_Status"));

		// od.ecommerce_validFrom.click();
//		od.startDate.click();

		// String status=externalData.getFieldData(TSID,"Party","Party_Status");
		// dropdown.selectByVisibleText(od.ecommerce_status, status);
		// od.ecommerce_validFrom.click();
		// od.startDate.click();
		debitorFalg = externalData.getFieldData(TSID, "Party", "Debit Accounts");
		System.out.println(debitorFalg);
		if (debitorFalg.equalsIgnoreCase("Yes") || debitorFalg.equalsIgnoreCase("Y")) {
			String hiddenClass = od.accountNumbers.getAttribute("class");
			System.out.println(hiddenClass);
			if (!(hiddenClass.contains("ag-hidden"))) {
				jsClick.click(od.ecommerceFirstAccount);
				Thread.sleep(2000);
				System.out.println("First = " + od.accountNumbers.getAttribute("class"));
			} else {
				System.out.println("Secound = " + od.ecommerceSecondAccount.isSelected());
				jsClick.click(od.ecommerceSecondAccount);
				Thread.sleep(2000);
			}
		}
		// od.ecommerce_validTill.click();
		// od.endDate.click();
		jsClick.click(od.ecommerceSave);
		System.out.println("save button click");
		Thread.sleep(2000);

	}

}
