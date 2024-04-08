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
import com.upp.utils.ExcelReader;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import io.cucumber.java.en.*;

public class TS12 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
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
    
	public TS12() {

		this.dm = new DashBoard_Module();
		this.tm_BasicDetails = new Transactions_Maker_BasicDetails();
		;
		this.tm_sub = new Transactions_Maker_Sub_Instruction();
		this.tm_doc = new Transactions_Maker_Documents();
		this.tm_sum = new Transactions_Maker_Summary();
		this.tc = new Transactions_Checker();
		this.tv = new Transactions_Verifier();
		this.execReport = new Reports_ExecutionReport();
		externalData = new ExcelReader();
	}

	


	@Then("Create Transaction from Transaction Maker and check whether To Field is accepting Non Registered Beneficiary with given {string}")
	public void create_Transaction_from_Transaction_Maker_and_check_whether_To_Field_is_accepting_Non_Registered_Beneficiary_with_given(String string) throws Exception {
		TSID = string;
		tm_BasicDetails.createNewAdhocTransaction(string, dealId, DealPage.sourceAccountNo);
		tm_BasicDetails.Transactions_Maker_BasicDetails(string, TS06.dealId, DealPage.toaccountNo);
//		tm_BasicDetails.Transactions_Maker_BasicDetails(string, "REF1704964855203", "7322661124");
		tm_sub.Transaction_Maker_Sub_Instruction(string, this);
		tm_doc.Transactions_Maker_Documents(string);
		TS06.TnxId = tm_sum.Transaction_Maker_Summary();
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

				instrumentHandler.handleBTPaymentInstrument(TSID,DealPage.sourceAccountNo,DealPage.toaccountNo);
			}
			
			if (paymentInstrument.equalsIgnoreCase("LT_IN")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();
            String checkbox=externalData.getFieldData(TSID, "Basic Details", "Transactions to non-registered beneficiaries");

             if(checkbox.equalsIgnoreCase("N")) {
				instrumentHandler.handleLT_INPaymentInstrumentFor_Non_Registered_Beneficiary_WithCheckbox_Unchecked(TSID,DealPage.sourceAccountNo,DealPage.toaccountNo);
                  }

			}
		}
		
	}
}
