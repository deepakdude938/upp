package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.Budget;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Audit.Verify_Budget_Details;

import callbackInterfaces.ICallback;

import com.upp.handlers.CommonLinkedInstructionInstrument;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.utils.ExcelReader;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.Transactions.Transactions_Checker;
import com.upp.pagemodules.Transactions.Transactions_Maker_SearchTransactionAndSubmit;
import com.upp.pagemodules.payment.Payment;
import com.upp.pagemodules.payment.Payment_BasicDetails;
import com.upp.pagemodules.payment.Payment_Notification;
import com.upp.pagemodules.payment.Payment_Retry;
import com.upp.pagemodules.payment.Payment_Schedule;
import com.upp.pagemodules.payment.Payment_SubInstruction;

import io.cucumber.java.en.*;

public class TS113 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String TnxId = "";
	Verify_Budget_Details vb;
	Reports_ExecutionReport report;
	Budget budget;
	String tsid;
	public static ExcelReader externalData;
	Transactions_Checker txnChecker;
	Transactions_Maker_SearchTransactionAndSubmit txnsearch;
	

	public TS113() {

		this.dm = new DashBoard_Module();
		vb=new Verify_Budget_Details();
		report=new Reports_ExecutionReport();
		budget=new Budget();
		externalData=new ExcelReader();
		txnChecker=new Transactions_Checker();
		txnsearch=new Transactions_Maker_SearchTransactionAndSubmit();
	}

	@Then("Create linked Instruction Payment with given  {string}.")
	public void create_linked_Instruction_Payment_with_given(String TSID) throws Exception {
		tsid = TSID;
		System.out.println("Changes = " + TSID);
		dm.createNewLinkedAccount1(TSID,DealPage.AccountNo1 ,DealPage.AccountNo1 , this);
	}
	@Then("submit the deal to transaction checker")
	public void submit_the_deal_to_transaction_checker() throws Exception {
		Thread.sleep(180000);
		txnsearch.txnMaker_SubmitDeal(TS06.dealId);
	}

	@Then("submit the deal to transaction verifier")
	public void submit_the_deal_to_transaction_verifier() throws Exception {
		txnChecker.txnChecker_SubmitDeal(TS06.dealId);
	}
	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub
		new CommonLinkedInstructionInstrument().handleInstruments(tsid, callbackid, data, externalData.getFieldData(tsid, "Linked", "to"));
	}
	
}
