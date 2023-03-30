package com.upp.pagemodules.ECommerce;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

	public void txnVerifier_ApproveDeal(String dealId) {
		// TODO Auto-generated method stub

		ecomm.ecommerce_txnVerifier.click();
		ecomm.ecommerce_TxnDealSearch.sendKeys(dealId);
		ecomm.ecommerce_comment.click();
		ecomm.ecommerce_note.sendKeys("Ok approve");
		ecomm.ecommerce_txnok.click();
		ecomm.ecommerce_txnCheckbox.click();
		ecomm.ecommerce_submitBtn.click();		
//		try {
			if (ecomm.ecommerce_warning.isDisplayed()) {
				ecomm.ecommerce_submitBtn.click();
			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		ecomm.ecommerce_yesBtn.click();
		ecomm.ecommerce_okBtn.click();
	
	}
}
