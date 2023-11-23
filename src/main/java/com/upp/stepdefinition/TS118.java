package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealPartyAccount_PaymentInstrumentHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.Transactions.Transactions_Checker;
import com.upp.pagemodules.Transactions.Transactions_Maker_BasicDetails;
import com.upp.pagemodules.Transactions.Transactions_Maker_Documents;
import com.upp.pagemodules.Transactions.Transactions_Maker_Sub_Instruction;
import com.upp.pagemodules.Transactions.Transactions_Maker_Summary;
import com.upp.pagemodules.Transactions.Transactions_Verifier;
import com.upp.utils.ExcelReader;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.ECommerce.ECommerceTransactionMaker;

import io.cucumber.java.en.*;

public class TS118 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
	public static String toaccountNumber = "Test5678";
	public static String dealId = "";
	public static String TSID = "";
	Transactions_Maker_Sub_Instruction tm_sub;
	Transactions_Maker_Documents tm_doc;
	Transactions_Maker_Summary tm_sum;
	Transactions_Maker_BasicDetails tm_BasicDetails;
	Transactions_Checker tc;
	Transactions_Verifier tv;
	Reports_ExecutionReport execReport;
	public static ExcelReader externalData;
	ECommerceTransactionMaker ecommTxn;

	public TS118() {

		this.dm = new DashBoard_Module();
		this.tm_BasicDetails = new Transactions_Maker_BasicDetails();
		this.tm_sub = new Transactions_Maker_Sub_Instruction();
		this.tm_doc = new Transactions_Maker_Documents();
		this.tm_sum = new Transactions_Maker_Summary();
		this.tc = new Transactions_Checker();
		this.tv = new Transactions_Verifier();
		this.execReport = new Reports_ExecutionReport();
		externalData = new ExcelReader();
		this.ecommTxn = new ECommerceTransactionMaker();

	}

	@Given("Add Transaction Maker Basic Details with given {string}")
	public void add_Transaction_Maker_Basic_Details_with_given(String string) throws Exception {
		tm_BasicDetails.Transactions_Maker_BasicDetails1(string, TS06.dealId, DealPage.toaccountNo);
	}

	@Then("Add Payment Currency and  currency Type  with given {string}")
	public void add_Payment_Currency_and_currency_Type_with_given(String string) throws Exception {
		TSID = string;
		System.out.println("TSID = " + TSID);
		tm_sub.Transaction_Maker_Sub_Instruction(string, this);
	}

	@Then("Add second Payment Currency and  currency Type  with given {string}")
	public void add_second_Payment_Currency_and_currency_Type_with_given(String string) throws Exception {
		TSID = string;
		ecommTxn.Transaction_Maker_Sub_InstructionPayment_Currency(string);
	}

	@Then("Add Payment Currency and  currency Type as Debit currency with given {string}")

	public void add_Payment_Currency_and_currency_Type_as_Debit_currency_with_given(String string) throws Exception {
		TSID = string;
		ecommTxn.Transaction_Maker_Sub_InstructionPayment_Currency(string);

	}

	@Then("Verify total debit currency and payment currency")
	public void verify_total_debit_currency_and_payment_currency() throws Exception {
		tm_sub.verifyDebitTotalCurrency();
	}

	@Then("Add Debit and Payment currency with given {string}")
	public void add_Debit_and_Payment_currency_with_given(String string) throws Exception {
		ecommTxn.addDealAsEcommerceTxnForDebitAndPaymentCurrency(TS07.dealId,string, DealPage.sourceAccountNo, DealPage.toaccountNo,this);
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
				System.out.println("TSID = " + TSID);
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();
				instrumentHandler.handleLT_INPaymentInstrumentForAdhocTransaction(TSID, DealPage.sourceAccountNo,
						DealPage.toaccountNo);
			}
		}

	}
}
