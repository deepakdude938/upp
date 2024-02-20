package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.odp.utils.EditAccountApi;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Login.LoginAPI_ODP;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Payment;
import com.upp.pagemodules.payment.Payment_Retention;

import io.cucumber.java.en.*;

public class TS16 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	public String dealid;
	Payment_Retention retention;
	Reports_ExecutionReport report;
	EditAccountApi editAccount;
	LoginAPI_ODP login_odp;
	Payment payment;
	public TS16() {

		this.dm = new DashBoard_Module();
		retention = new Payment_Retention();
		report = new Reports_ExecutionReport();
		login_odp=new LoginAPI_ODP();
		payment=new Payment();
	}

	@Then("Create Payment_Retention in scheduled Instruction with given {string}")
	public void create_Payment_Retention_in_scheduled_Instruction_with_given(String string) throws Exception {
		payment.createPayments(string, DealPage.sourceAccountNo, DealPage.toaccountNo);
		retention.createRetention(string);

	}

	@Then("check status and instruction type for payment retention with given {string}")
	public void check_status_and_instruction_type_for_payment_retention_with_given(String string) throws Exception {
		report.checkInstructionTypePayment_Retention(string,TS06.dealId);
	}
	
	@Then("Check status and instruction type for retention with given {string}")
	public void check_status_and_instruction_type_for_retention_with_given(String tsid) throws Exception {
		report.checkStatusAndInstructionTypeForRetention(tsid);
	}

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
