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
import com.upp.pagemodules.Transactions.Transactions_Maker_Bulkupload;
import com.upp.pagemodules.payment.Payment;
import com.upp.pagemodules.payment.Payment_Retention;

import io.cucumber.java.en.*;

public class TS27 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	public String dealid;
	Transactions_Maker_Bulkupload bulkUpload;
	Reports_ExecutionReport report;


	public TS27() {

		this.dm = new DashBoard_Module();
		bulkUpload = new Transactions_Maker_Bulkupload();
		this.report = new Reports_ExecutionReport();
	}

	
	@Then("Bulk upload the transaction")
	public void bulk_upload_the_transaction() throws Exception {
		System.out.println("source Account = "+DealPage.sourceAccountNo);
		System.out.println("des Account = "+DealPage.toaccountNo);
		 
	//	bulkUpload.bulkUpload(DealPage.sourceAccountNo,DealPage.toaccountNo);
		bulkUpload.bulkUpload("1466443356","9854716055");
	}

	@Then("Approve multiple transactions from Transaction Checker with given {string}")
	public void approve_multiple_transactions_from_Transaction_Checker_with_given(String string) throws Exception {
		System.out.println(TS06.dealId);
		bulkUpload.approveAllTransaction("REF1682497237686");
	}
	
	@Then("Approve multiple transaction from Transaction Verifier with given {string}")
	public void approve_multiple_transaction_from_Transaction_Verifier_with_given(String string) throws Exception {
	    bulkUpload.approveAllTransactionByVerifier("REF1682497237686");
	}
	
	@Then("Check the Transaction status in execution report with given {string}")
	public void check_the_Transaction_status_in_execution_report_with_given(String string) throws Exception {
	   report.ExecutionReport(string,"REF1682497237686");
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
