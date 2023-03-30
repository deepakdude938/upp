package com.upp.stepdefinition;

import com.upp.base.BaseClass;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.Budget;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Deal.DealAccountCreator;
import callbackInterfaces.ICallback;
import io.cucumber.java.en.Then;

public class TS05 extends BaseClass  implements ICallback{
	
	
	
	public Budget dm;
	public String tsid;
	public static String sourceAccountNo = "";
	public static String toAccountNo = "";
	public DashBoard_Module dm1;


	public TS05() {
		this.dm = new Budget();
		this.dm1 = new DashBoard_Module();

	}
	
	@Then("Create a Budget with given {string}")
	public void create_a_Budget_with_given(String TSID) throws Exception, Exception {
	   dm.createBudget(TSID,DealPage.sourceAccountNo,DealPage.toaccountNo);
	}
	
	
	@Then("Create Payments in the Scheduled Instructions with given {string}")
	public void create_Payments_in_the_scheduled_Instructions_with_given(String string) throws Exception {
		
	 dealId=dm.createBudget_Payments(string,DealPage.sourceAccountNo,DealPage.toaccountNo);
}
	
	@Then("Approve the deal from the deal checker")
	public void approve_the_deal_from_the_deal_checker() throws Exception {

		dm1.approveDealFromDealChecker_Old(dealId);

	}
	
	
	@Override
	public  void handleCallback(String callbackid, Object data) throws Exception {
	
		
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
				
			instrumentHandler.handleBTPaymentInstrument("TS05",DealPage.sourceAccountNo,DealPage.toaccountNo);
			}
			
		}
		
	}
}

