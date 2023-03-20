package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.CommonResponsibilityHandler;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.handlers.DealPartyAccount_PaymentInstrumentHandler;
import com.upp.handlers.EcommerceHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Transactions.Transactions_Checker;
import com.upp.pagemodules.Transactions.Transactions_Maker_BasicDetails;
import com.upp.pagemodules.Transactions.Transactions_Maker_SearchTransactionAndSubmit;
import com.upp.pagemodules.Transactions.Transactions_Maker_Sub_Instruction;
import com.upp.pagemodules.Transactions.Transactions_Verifier;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS13 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	public String dealId;
	public String tsid;
	Transactions_Maker_SearchTransactionAndSubmit txnsearch;
	Transactions_Checker txnChecker;
	Transactions_Verifier txnVerifier;
	public static String sourceAccount = "";
	public static String toAccount = "User4567";
	Transactions_Maker_Sub_Instruction tm_sub;
	Transactions_Maker_BasicDetails tm_BasicDetails;

	public TS13(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
		this.txnsearch = new Transactions_Maker_SearchTransactionAndSubmit();
		this.txnChecker = new Transactions_Checker();
		this.txnVerifier = new Transactions_Verifier();
		sourceAccount = new DealPage(dm).sourceAccountNo;
		//toAccount = new DealPage(dm).toaccountNo;
		this.tm_BasicDetails = new Transactions_Maker_BasicDetails();
		this.tm_sub = new Transactions_Maker_Sub_Instruction();
	}

	@Then("Create a Transaction for Non Registered Beneficiary user from Transaction Maker with given {string}")
	public void create_a_Transaction_for_Non_Registered_Beneficiary_user_from_Transaction_Maker_with_given(
			String TSID) throws Exception {
		tsid = TSID;
		System.out.println("TS13 steps");
		tm_BasicDetails.Transactions_Maker_BasicDetails(TSID, DealPage.dealId, DealPage.toaccountNo);
		tm_sub.Transaction_Maker_Sub_Instruction(TSID, this);
		tm_sub.Non_Registered_Beneficiary(toAccount);
		
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {

		new CommonProductHandler().handleProduct(callbackid, data);
		new CommonResponsibilityHandler().handleResponsibility(callbackid, data);

		if (callbackid.equalsIgnoreCase("ecommerce")) {
			String responsibility = (String) data;
			if (responsibility.equalsIgnoreCase("Y")) {
				EcommerceHandler ecommerce = new EcommerceHandler();
				ecommerce.handleEcommerce(tsid);
			}
		}

		if (callbackid.equalsIgnoreCase("PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;
			if (paymentInstrument.equalsIgnoreCase("BT")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();
				// instrumentHandler.handleBTPaymentInstrument(tsid, sourceAccountNo,
				// toaccountNo);
			}
			if (paymentInstrument.equalsIgnoreCase("LT_IN")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();
				instrumentHandler.handleLTTestPaymentInstrumentFor_Non_Registered_Beneficiary_WithCheckbox_checked(tsid,
						sourceAccount, toAccount);
			}
		}
	}
}
