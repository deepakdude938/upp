package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import com.upp.pagemodules.LoginToApplication;
import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import io.cucumber.java.en.*;

public class TS03 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	LoginToApplication userLogin;
	public static String TSID = "";
	public static String TnxId="";
	public String dealid;
public TS03() {

		this.dm=new DashBoard_Module();
	}

	@Then("Create Payments in the scheduled Instructions with given {string}")
	public void create_Payments_in_the_scheduled_Instructions_with_given(String string) throws Exception {

		dealid = dm.createPayments(string, DealPage.sourceAccountNo, DealPage.toaccountNo);

	}

@Then("Create Payments in the scheduled Instructions with given {string}")
public void create_Payments_in_the_scheduled_Instructions_with_given(String string) throws Exception {
	
	dm.createPayments(string,DealPage.sourceAccountNo,DealPage.toaccountNo);
    //dm.createPayments(string,"Trial123","Trail1234");
}

	

//	@Then("Create new deal with basic details with given {string}.")
//	public void create_new_deal_POC_with_basic_details_with_given(String TSID) throws  Exception {
//		
//		DealBasicDetailCreators createDeal = new  DealBasicDetailCreators();
//		createDeal.createDealBasicDetails(TSID, this);
//	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

		if (callbackid.equalsIgnoreCase("PRODUCT_NAME")) {
			String productName = (String) data;
			if (productName.equalsIgnoreCase("flexible funding")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleFlexibleFundding();
			}
			if (productName.equalsIgnoreCase("1.0")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleoneProduct();
			}

		}

		if (callbackid.equalsIgnoreCase("PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;
			if (paymentInstrument.equalsIgnoreCase("BT")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();

				instrumentHandler.handleBTPaymentInstrument(TSID, DealPage.sourceAccountNo, DealPage.toaccountNo);
			}

		}

	}
}
