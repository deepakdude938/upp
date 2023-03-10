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

public class TransactionMaker_PaymentInstrumentHandler extends BaseClass  implements ICallback {
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	DropDown dropdown;

	public static ScrollTypes scroll;
	public static JavascriptClick jsClick;
	public static Object_Transactions tm ;

	public TransactionMaker_PaymentInstrumentHandler() {
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

	public void handleBTPaymentInstrument(String TSID,String sourceAccountno,String toaccountNo) throws Exception {
		
		scroll.scrollInToView(tm.transactions_ToAccountDropdown);
		applyExplicitWaitsUntilElementClickable(tm.transactions_ToAccountDropdown,Duration.ofSeconds(7));
		jsClick.click(tm.transactions_ToAccountDropdown);
		dropdown.selectByVisibleText(tm.transactions_ToAccountDropdown,externalData.getFieldData(TSID,"Txn Maker","to"));
		 System.out.println("the to data is "+externalData.getFieldData(TSID,"Txn Maker","to"));
		 
		 scroll.scrollInToView(od.payments_beneficiaryCountryOfIncorporationDropdown);
		 applyExplicitWaitsUntilElementClickable(od.payments_beneficiaryCountryOfIncorporationDropdown,Duration.ofSeconds(7));
		 od.payments_beneficiaryCountryOfIncorporationDropdown.sendKeys(externalData.getFieldData(TSID,"Txn Maker","Beneficiary Country Of Incorporation"));
		
		 applyExplicitWaitsUntilElementClickable(od.payments_Amount,Duration.ofSeconds(5));
		 scroll.scrollInToView(od.payments_Amount);
		 od.payments_Amount.sendKeys(externalData.getFieldData(TSID,"Txn Maker","Amount"));
		 
		
		 scroll.scrollInToView(od.payments_AddSubInstructionButton);
		 od.payments_AddSubInstructionButton.click();
		 scroll.scrollInToView(od.payments_NextArrowButtonTransferSubInstruction);
		 applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonTransferSubInstruction,Duration.ofSeconds(10));
		 od.payments_NextArrowButtonTransferSubInstruction.click();	
		
	}

	
}
