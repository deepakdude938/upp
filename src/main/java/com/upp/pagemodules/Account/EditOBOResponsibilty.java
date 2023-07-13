package com.upp.pagemodules.Account;

import java.net.MalformedURLException;
import java.time.Duration;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
import com.upp.pageobjects.Object_DealLifecycle;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;
import com.upp.utils.CommonUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

import com.upp.utils.DateUtils;

public class EditOBOResponsibilty extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public JavascriptClick js;
	public static DateUtils dateutil;

	public EditOBOResponsibilty() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
	}

	
	public void EditOBOResponsibilty_In_Account(String TSID) throws Exception
	{

		Thread.sleep(1500);
		System.out.println("inside method");
		scroll.scrollInToView(od.account_edit_icon);
		js.click(od.account_edit_icon);
		Thread.sleep(1500);
		scroll.scrollInToView(od.account_obo_repsonsibility);
		applyExplicitWaitsUntilElementClickable(od.account_obo_repsonsibility, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.account_obo_repsonsibility,externalData.getFieldData(TSID, "Party", "Responsibility"));
		Thread.sleep(1000);
		try {
			od.accounts_addAccount.click();
		}
		catch(Exception e) {
			handleElementClickException(od.accounts_addAccount);
		}
		Thread.sleep(1500);
		applyExplicitWaitsUntilElementClickable(od.account_OK_Button, Duration.ofSeconds(5));
		od.account_OK_Button.click();
		Thread.sleep(1500);
	}

}
