package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import com.upp.pagemodules.LoginToApplication;
import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.Transactions.Transactions_Maker_Verifier_Checker;
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
	LoginToApplication userLogin;
	public static String TSID = "";
	Transactions_Maker_Verifier_Checker tm;
	public static String TnxId = "";

	public TS06(Transactions_Maker_Verifier_Checker tm) {

		this.tm = new Transactions_Maker_Verifier_Checker();
		this.dm = new DashBoard_Module();
	}

	@Then("Create deal with basic details with given {string}.")
	public void create_deal_with_basic_details_with_given(String string) throws Exception {

		dm.createNewDeal_Old(string);
	}

	@Then("submit the deal")
	public void submit_the_deal() throws Exception {
		// Write code here that turns the phrase above into concrete actions
		dealId = dm.submitDeal();
	}

	@Then("approve the deal from the deal checker for TS06")
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
		TnxId = tm.createTransactionFromTransactionMaker(string, DealPage.dealId, DealPage.toaccountNo, this);
		// TnxId=tm.createTransactionFromTransactionMaker(string,"REF1676642967004","5523872419",this);
	}

	@Then("Approve the transaction from Transaction Checker with given {string}")
	public void approve_the_transaction_from_Transaction_Checker(String string) throws Exception {
		tm.approveTransactionFromChecker(string, TnxId);
	}

	@Then("Approve the transaction from Transaction Verifier with given {string}")
	public void approve_the_transaction_from_Transaction_Verifier_with_given(String string) throws Exception {
		tm.approveTransactionFromVerifier(string, TnxId);
	}

	@Then("Check the Transaction staus in execution report with given {string}")
	public void check_the_Transaction_staus_in_execution_report_with_given(String string) throws Exception {
		tm.checkTnxStatusFromExecutionReport(string, DealPage.dealId);

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

				instrumentHandler.handleBTPaymentInstrument(TSID, sourceAccountNo, toaccountNo);
			}

		}

	}
}
