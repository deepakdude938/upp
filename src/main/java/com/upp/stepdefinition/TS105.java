package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Payment;
import com.upp.pagemodules.payment.Payment_BasicDetails;
import com.upp.pagemodules.payment.Payment_Notification;
import com.upp.pagemodules.payment.Payment_Retry;
import com.upp.pagemodules.payment.Payment_Schedule;
import com.upp.pagemodules.payment.Payment_SubInstruction;

import io.cucumber.java.en.*;

public class TS105 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	Payment_BasicDetails basic;
	Payment_Schedule schedule;
	Payment_SubInstruction inx;
	Payment_Retry retry;
	Payment_Notification notify;
	Reports_ExecutionReport report;

	public TS105() {

		this.dm = new DashBoard_Module();
		basic = new Payment_BasicDetails();
		schedule = new Payment_Schedule();
		inx = new Payment_SubInstruction();
		retry = new Payment_Retry();
		notify = new Payment_Notification();
		report=new Reports_ExecutionReport();
	}

	
	@Then("Create payment_BasicDetails in the scheduled Instructions with given {string}")
	public void create_payment_BasicDetails_in_the_scheduled_Instructions_with_given(String string) throws Exception {
		basic.createPayments_BasicDetails(string,DealPage.AccountNo1,DealPage.toaccountNo);
	}

	@Then("Create payment_Schedule in the scheduled Instructions with given {string}")
	public void create_payment_Schedule_in_the_scheduled_Instructions_with_given(String string) throws Exception {
		schedule.createPayments_Schedule(string,DealPage.AccountNo1,DealPage.toaccountNo);
	}

	@Then("Create payment_SubInstruction in the scheduled Instructions with given {string}")
	public void create_payment_SubInstruction_in_the_scheduled_Instructions_with_given(String string) throws Exception {
		inx.createPayments_Subinstruction_without_Party_Added(string,DealPage.AccountNo1,DealPage.toaccountNo);
	}
	
	@Then("Check the Transaction status of all {int} Transactions is Scheduled in execution report with given {string}")
	public void check_the_Transaction_status_of_all_Transactions_is_Scheduled_in_execution_report_with_given(Integer int1, String string) throws Exception {
	    report.check_All_6_Transaction_StatusIsScheduled(string,TS06.dealId);
	}

	@Then("Check the Transaction status of all {int} Transactions is Settled\\/Triggered and Settled amount in execution report with given {string}")
	public void check_the_Transaction_status_of_all_Transactions_is_Settled_Triggered_and_Settled_amount_in_execution_report_with_given(Integer int1, String string) throws Exception{
	    report.check_Triggered_or_Settled_Status_For_All_6_Transactions_and_Settled_Amount(string,dealId);
	   
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
