package com.upp.handlers;

import java.time.Duration;

import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Transactions;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class Deal_Level_Rule_Handler extends BaseClass  implements ICallback {
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	DropDown dropdown;

	public static ScrollTypes scroll;
	public static JavascriptClick jsClick;
	public static Object_Transactions tm ;

	public Deal_Level_Rule_Handler() {
		od = new Object_NewDeal();
		dropdown = new DropDown(BaseClass.driver);
		scroll=new ScrollTypes(driver);
		jsClick=new JavascriptClick(driver);
		tm=new Object_Transactions();
		externalData = new ExcelReader();
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

	}

	public void handle_Rule_IN_BT_DealLevel(String TSID) throws Exception {
		
		System.out.println("inside handle_Rule_IN_BT_DealLevel");
		
		scroll.scrollInToView(od.contextualize_Icon);
		applyExplicitWaitsUntilElementClickable(od.contextualize_Icon, Duration.ofSeconds(15));
		od.contextualize_Icon.click();

		applyExplicitWaitsUntilElementClickable(od.contextualize_Dropdown, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.contextualize_Dropdown,"Payment Profile");
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_AddButton, Duration.ofSeconds(5));
		od.contextualize_AddButton.click();
		
		Thread.sleep(1000);
		jsClick.click(od.contextualize_RightArrow);
		
		Thread.sleep(1000);
		jsClick.click(od.contextualize_RightArrow);
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_BT, Duration.ofSeconds(5));
		od.contextualize_BT.click();
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_ChargeBearer, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.contextualize_ChargeBearer,"SHA");
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.contextualize_DoneButton, Duration.ofSeconds(5));
	    od.contextualize_DoneButton.click();
	    Thread.sleep(1000);
		
	}
	

	public void handle_Rule_IN_LT_DealLevel(String TSID) throws Exception {
		
		System.out.println("inside handle_Rule_IN_LT_DealLevel");
		
		scroll.scrollInToView(od.contextualize_Icon);
		applyExplicitWaitsUntilElementClickable(od.contextualize_Icon, Duration.ofSeconds(15));
		od.contextualize_Icon.click();

		applyExplicitWaitsUntilElementClickable(od.contextualize_Dropdown, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.contextualize_Dropdown,"Payment Profile");
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_AddButton, Duration.ofSeconds(5));
		od.contextualize_AddButton.click();
		
		Thread.sleep(1000);
		jsClick.click(od.contextualize_RightArrow);
		
		Thread.sleep(1000);
		jsClick.click(od.contextualize_RightArrow);
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_LT, Duration.ofSeconds(5));
		od.contextualize_LT.click();
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_PaymentType, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.contextualize_PaymentType,"RTGS");
		Thread.sleep(1000);
		
		applyExplicitWaitsUntilElementClickable(od.contextualize_ChargeBearer, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.contextualize_ChargeBearer,"SHA");
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.contextualize_DoneButton, Duration.ofSeconds(5));
	    od.contextualize_DoneButton.click();
	    Thread.sleep(1000);
		
	}

}
