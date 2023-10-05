package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.Transactions.Transactions_Checker;
import com.upp.pagemodules.Transactions.Transactions_Maker_BasicDetails;
import com.upp.pagemodules.Transactions.Transactions_Maker_Documents;
import com.upp.pagemodules.Transactions.Transactions_Maker_Sub_Instruction;
import com.upp.pagemodules.Transactions.Transactions_Maker_Summary;
import com.upp.pagemodules.Transactions.Transactions_Verifier;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import io.cucumber.java.en.*;

public class TS06 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
	public static String dealId = "";
	public static String TSID = "";
	public static String TnxId = "";
	Transactions_Maker_Sub_Instruction tm_sub;
	Transactions_Maker_Documents tm_doc;
	Transactions_Maker_Summary tm_sum;
	Transactions_Maker_BasicDetails tm_BasicDetails;
	Transactions_Checker tc;
	Transactions_Verifier tv;
	Reports_ExecutionReport execReport;

	public TS06() {

		this.dm = new DashBoard_Module();
		this.tm_BasicDetails = new Transactions_Maker_BasicDetails();
		this.tm_sub = new Transactions_Maker_Sub_Instruction();
		this.tm_doc = new Transactions_Maker_Documents();
		this.tm_sum = new Transactions_Maker_Summary();
		this.tc = new Transactions_Checker();
		this.tv = new Transactions_Verifier();
		this.execReport = new Reports_ExecutionReport();
	}

	@Then("submit the deal")
	public void submit_the_deal() throws Exception {
		// Write code here that turns the phrase above into concrete actions
		dealId = dm.submitDeal();
		BaseClass.dealId=dealId;
	}

	@Then("approve the deal from the deal checker common method")
	public void approve_the_deal_from_the_deal_checker() throws Exception {
		dm.approveDealFromDealChecker_Old(dealId);
	}

	@Then("logout of the application")
	public void logout_of_the_application() throws Exception {
		dm.logOutOld();
	}

	@Given("Create a Transaction from Transaction Maker with given {string}")
	public void create_a_Transaction_from_Transaction_Maker(String string) throws Exception {

		TSID = string;
		tm_BasicDetails.Transactions_Maker_BasicDetails(string, TS06.dealId, DealPage.sourceAccountNo);
		tm_sub.Transaction_Maker_Sub_Instruction(string, this);
		tm_doc.Transactions_Maker_Documents(string);
		TS06.TnxId = tm_sum.Transaction_Maker_Summary();

	}

	@Then("Approve the transaction from Transaction Checker with given {string}")
	public void approve_the_transaction_from_Transaction_Checker(String string) throws Exception {
		
		tc.TransactionsChecker(string, TS06.TnxId);
	}

	@Then("Approve the transaction from Transaction Verifier with given {string}")
	public void approve_the_transaction_from_Transaction_Verifier_with_given(String string) throws Exception {
		
		tv.TransactionsVerifier(string, TS06.TnxId);
	}

	@Then("Check the Transaction staus in execution report with given {string}")
	public void check_the_Transaction_staus_in_execution_report_with_given(String string) throws Exception {

		execReport.ExecutionReport(string, TS06.dealId);

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

			if (paymentInstrument.equalsIgnoreCase("BT_UAE")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();

				instrumentHandler.handleBT_UAEPaymentInstrument(TSID, DealPage.sourceAccountNo, DealPage.toaccountNo);
			}

			if (paymentInstrument.equalsIgnoreCase("LT_IN")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();
				instrumentHandler.handleLT_INPaymentInstrumentForAdhocTransaction(TSID, DealPage.sourceAccountNo,
						DealPage.toaccountNo);
			}
		}

	}
}
