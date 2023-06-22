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

public class TS52 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	public String dealid;
	Payment_BasicDetails basic;
	Payment_Schedule schedule;
	Payment_SubInstruction inx;
	Payment_Retry retry;
	Payment_Notification notify;
	Reports_ExecutionReport report;

	public TS52() {

		this.dm = new DashBoard_Module();
		basic = new Payment_BasicDetails();
		schedule = new Payment_Schedule();
		inx = new Payment_SubInstruction();
		retry = new Payment_Retry();
		notify = new Payment_Notification();
		report=new Reports_ExecutionReport();
	}

	@Then("Create Payment_BasicDetails in the scheduled Instructions with given {string}")
	public void create_Payment_BasicDetails_in_the_scheduled_Instructions_with_given(String string) throws Exception {
		basic.createPayments_BasicDetails(string,DealPage.sourceAccountNo,DealPage.toaccountNo);
	}

	@Then("Create Payment_Schedule in the scheduled Instructions with given {string}") 
	public void create_Payment_Schedule_in_the_scheduled_Instructions_with_given(String string) throws Exception {
		schedule.createPayments_Schedule(string,DealPage.sourceAccountNo,DealPage.toaccountNo);
	}

	@Then("Create Payment_SubInstruction in the scheduled Instructions with given {string}")
	public void create_Payment_SubInstruction_in_the_scheduled_Instructions_with_given(String string) throws Exception {
	inx.createPayments_Subinstruction(string,DealPage.sourceAccountNo,DealPage.toaccountNo);
	}

	@Then("Create Payment_Retry in the scheduled Instructions with given {string}")
	public void create_Payment_Retry_in_the_scheduled_Instructions_with_given(String string) throws Exception {
	retry.createPayments_Retry(string,DealPage.sourceAccountNo,DealPage.toaccountNo);
	}

	@Then("Create Payment_Notification in the scheduled Instructions with given {string}")
	public void create_Payment_Notification_in_the_scheduled_Instructions_with_given(String string) throws Exception {
     notify.createPayments_Notification(string,DealPage.sourceAccountNo,DealPage.toaccountNo);
	}
	@Then("Verify in Execution Report the Original Amount and Transfer info as percentage with given {string}")
	public void verify_in_Execution_Report_the_Original_Amount_and_Transfer_info(String string) throws Exception {
	   report.check_Original_amount_and_Trnasferinfo_as_percentage(string,TS06.dealId);
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
