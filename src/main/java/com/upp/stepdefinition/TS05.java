package com.upp.stepdefinition;

import com.upp.base.BaseClass;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.deal.DealAccountCreator;

import callbackinterfaces.ICallback;
import io.cucumber.java.en.Then;

public class TS05 extends BaseClass  implements ICallback{
	
	
	
	public DashBoard_Module dm;
	public String tsid;
	public static String sourceAccountNo = "";
	public static String toAccountNo = "";
//	public static String dealId = "";

	public TS05(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
	}
	
	
	
	@Then("Create a Budget with given {string}")
	public void create_a_Budget_with_given(String TSID) throws Exception, Exception {
	   dm.createBudget(TSID,DealPage.sourceAccountNo,DealPage.toAccountNo);
	}
	
	
	@Then("Create Payments in the scheduled Instructions with given {string}")
	public void create_Payments_in_the_scheduled_Instructions_with_given(String string) throws Exception {
		
	 dealId=dm.createBudget_Payments(string,DealPage.sourceAccountNo,DealPage.toAccountNo);
		
	  
	}
	
	
	@Override
	public  void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub
		
		if(callbackid.equalsIgnoreCase("PRODUCT_NAME")) {
			String productName = (String) data;
			if(productName.equalsIgnoreCase("flexible funding")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleFlexibleFundding();
			}
			if(productName.equalsIgnoreCase("1.0")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleoneProduct();
			}

		}
		
		if(callbackid.equalsIgnoreCase("PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;
			if(paymentInstrument.equalsIgnoreCase("BT")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler=new TransactionMaker_PaymentInstrumentHandler();
				
			instrumentHandler.handleBTPaymentInstrument("TS05",DealPage.sourceAccountNo,DealPage.toAccountNo);
			}
			
		}
		
	}
}

//	@Then("Create two Accounts with given {string}")
//	public void create_two_Accounts_with_given(String string) throws Exception {
//		DealAccountCreator accountCreator = new DealAccountCreator();
//		sourceAccountNo = accountCreator.createNewAccount(string);
//		toAccountNo = accountCreator.createNewAccount(string);
//	}

