package com.upp.stepdefinition;


import java.io.IOException;
import com.upp.base.BaseClass;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Payment;
import com.upp.pagemodules.payment.Payment_Summary;
import com.upp.utils.ExcelReader;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.Then;

public class TS15 extends BaseClass implements ICallback{
	
	public Payment pm;
	public Payment_Summary ps;
	public String TSID;
	public static ExcelReader externalData;
	public Reports_ExecutionReport re;
	

	
	public TS15() {
	
		this.pm = new Payment();
		this.ps = new Payment_Summary();
		externalData = new ExcelReader();
		re=new Reports_ExecutionReport();
	
	}

	@Then("Create Payment-Surplus in the Scheduled Instructions with given {string}")
	public void create_Payment_Surplus_in_the_Scheduled_Instructions_with_given(String TSID) throws Exception {
	    this.TSID=TSID;
	    pm.createPaymentsInScheduledInstructionsOnFriday(TSID,DealPage.sourceAccountNo,DealPage.toaccountNo);
	    
	}
	
	@Then("Create Surplus in the Scheduled Instructions with given {string}")
	public void create_Surplus_in_the_Scheduled_Instructions_with_given(String TSID) throws Exception{
	   pm.createPaymentSurplus(TSID);
	}
	
	@Then("Validate SubInstruction Type as {string} and {string}")
	public void validate_SubInstruction_Type_as_and(String payment, String surplus) throws Exception {
		re.checkSubInstructionTypeInExecutionReport(payment,surplus);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		

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

			if (paymentInstrument.equalsIgnoreCase("LT_IN")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();
				String checkbox = externalData.getFieldData(TSID, "Basic Details",
						"Transactions to non-registered beneficiaries");

			}
		}
	}
}
