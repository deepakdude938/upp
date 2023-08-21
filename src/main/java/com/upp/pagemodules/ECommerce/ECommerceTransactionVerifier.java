package com.upp.pagemodules.ECommerce;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_Ecommerce;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;
import freemarker.template.utility.DateUtil;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ECommerceTransactionVerifier extends BaseClass {

	public static Object_Ecommerce ecomm;
	DropDown dropdown;
	public ExcelReader externalData;
	ScrollTypes scroll;

	public ECommerceTransactionVerifier() {

		ecomm = new Object_Ecommerce();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
		scroll = new ScrollTypes(driver);
	}

	public void txnVerifier_ApproveDeal(String dealId) throws Exception {
		// TODO Auto-generated method stub
		ecomm.ecommerce_SideMenuIcon.click();
		try {
			ecomm.ecommerce_txnVerifier.click();
		} catch (ElementClickInterceptedException e) {
			Thread.sleep(2000);
			handleElementClickException(ecomm.ecommerce_txnVerifier);
		}

//		ecomm.ecommerce_txnVerifier.click();

		ecomm.ecommerce_TxnDealSearch1.clear();
		ecomm.ecommerce_TxnDealSearch1.sendKeys(dealId);
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_AllRecordsCheckBox, Duration.ofSeconds(10));
		try {
			if (ecomm.noRows.isDisplayed()) {
				System.out.println("Record is displayed in ecomm report");
			}
		} catch (Exception ee) {
			try {
				ecomm.ecommerce_AllRecordsCheckBox.click();
			} catch (Exception e) {
				Thread.sleep(2000);
				handleElementClickException(ecomm.ecommerce_AllRecordsCheckBox);
			}
			try {
				ecomm.ecommerce_Allcomment.click();
			} catch (Exception e) {
				handleElementClickException(ecomm.ecommerce_Allcomment);
			}
			ecomm.ecommerce_note.sendKeys("Ok approve");
			ecomm.ecommerce_txnok.click();
			ecomm.ecommerce_submitBtn.click();
			try {
				if (ecomm.ecommerce_warning.isDisplayed()) {
					ecomm.ecommerce_submitBtn.click();
				}
			} catch (Exception e) {

			}
			new Actions(driver).moveToElement(ecomm.ecommerce_yesBtn);
			ecomm.ecommerce_yesBtn.click();
			ecomm.ecommerce_okBtn.click();
		}

	}

	public void approveRecordFromEcommTxnVerifier() throws Exception {
		try {
			ecomm.ecommerce_txnVerifier.click();
		} catch (ElementClickInterceptedException e) {
			Thread.sleep(2000);
			handleElementClickException(ecomm.ecommerce_txnVerifier);
		}

//		ecomm.ecommerce_txnVerifier.click();

		ecomm.ecommerce_TxnDealSearch1.clear();
		ecomm.ecommerce_TxnDealSearch1.sendKeys(dealId);
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_AllRecordsCheckBox, Duration.ofSeconds(10));
		try {
			ecomm.ecommerce_AllRecordsCheckBox.click();
		} catch (Exception e) {
			Thread.sleep(2000);
			handleElementClickException(ecomm.ecommerce_AllRecordsCheckBox);
		}
		try {
			ecomm.ecommerce_Allcomment.click();
		} catch (Exception e) {
			handleElementClickException(ecomm.ecommerce_Allcomment);
		}
		ecomm.ecommerce_note.sendKeys("Ok approve");
		ecomm.ecommerce_txnok.click();
		ecomm.ecommerce_submitBtn.click();
		try {
			if (ecomm.ecommerce_warning.isDisplayed()) {
				ecomm.ecommerce_submitBtn.click();
			}
		} catch (Exception e) {

		}
		new Actions(driver).moveToElement(ecomm.ecommerce_yesBtn);
		ecomm.ecommerce_yesBtn.click();
		ecomm.ecommerce_okBtn.click();

	}
}
