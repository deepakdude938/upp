package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.LoginToApplication;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Transactions.Transactions_Checker;
import com.upp.pagemodules.Transactions.Transactions_Maker_SearchTransactionAndSubmit;
import com.upp.pagemodules.Transactions.Transactions_Verifier;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS04 extends BaseClass {
	DashBoard_Module dm;
	public String dealId;
	Transactions_Maker_SearchTransactionAndSubmit txnsearch;
	Transactions_Checker txnChecker;
	Transactions_Verifier txnVerifier;

	public TS04(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
		this.txnsearch = new Transactions_Maker_SearchTransactionAndSubmit();
		this.txnChecker = new Transactions_Checker();
		this.txnVerifier = new Transactions_Verifier();
	}

	@Then("create Linked Instruction Payment with given  {string}.")
	public void create_Linked_Instruction_Payment_with_given(String TSID) throws Exception {
		String sourceAccount = new DealPage(dm).sourceAccountNo;
		String toAccount = new DealPage(dm).toaccountNo;
		System.out.println("Changes = " + TSID);
		dealId = dm.createNewLinkedAccount(TSID, sourceAccount, toAccount);

	}

//	@Then("approve the deal from the deal checker")
//	public void approve_the_deal_from_the_deal_checker() throws Exception {
//		dm.approveDealFromDealChecker(dealId);
//	}

	@Then("Submit the deal to transaction checker")
	public void submit_the_deal_to_transaction_checker() throws Exception {
		txnsearch.txnMaker_SubmitDeal(dealId);
	}

	@Then("Submit the deal to transaction verifier")
	public void submit_the_deal_to_transaction_verifier() {
		txnChecker.txnChecker_SubmitDeal(dealId);
	}

	@Then("Transaction verifier approve deal")
	public void transaction_verifier_approve_deal() {
		txnVerifier.txnVerifier_ApproveDeal(dealId);

	}

}
