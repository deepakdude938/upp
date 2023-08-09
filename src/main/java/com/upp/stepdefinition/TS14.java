package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.EcommTnxApi;
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
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.cucumber.java.en.*;

public class TS14 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
	public static String dealId = "";
	public static String TSID = "";
	Reports_ExecutionReport execReport;
	public static ExcelReader externalData;
	EcommTnxApi ecommApi;
	LoginAPI_UPP loginApi;
	public static String endToEndId="";
	Reports_ExecutionReport report;
    
	public TS14() {

		this.dm = new DashBoard_Module();
		this.execReport = new Reports_ExecutionReport();
		externalData = new ExcelReader();
		ecommApi=new EcommTnxApi();
		report=new Reports_ExecutionReport();
		
	}

	

	@Then("call the ecommerce transaction api with given {string}")
	public void call_the_ecommerce_transaction_api_with_given(String string) throws Exception {
		
		loginApi.loginToUpp();
		endToEndId=ecommApi.createEcommerceTnx(string);
	}

	@Then("Verify the status and amount in eComm Executions Report")
	public void verify_the_EcommTnx_in_eComm_Executions_Report() throws Exception {
	    report.Verify_Status_And_Amount_eCommExecutionsReport(endToEndId);
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

	}
}
