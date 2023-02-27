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
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS04 extends BaseClass {
	DashBoard_Module dm;
	public String dealId;

	public TS04(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
	}

	@Then("create Linked Instruction Payment with given  {string}.")
	public void create_Linked_Instruction_Payment_with_given(String TSID) throws Exception {
		String sourceAccount = new DealPage(dm).sourceAccountNo;
		String toAccount  = new DealPage(dm).toaccountNo;	
		dealId = dm.createNewLinkedAccount(TSID, sourceAccount, toAccount);
	}
	
	@Then("approve the deal from the deal checker")
	public void approve_the_deal_from_the_deal_checker() throws Exception {
	   dm.approveDealFromDealChecker(dealId);
	}
	
	@Then("Submit the deal to transaction checker")
	public void submit_the_deal_to_transaction_checker() throws Exception {
		dm.txnMaker_SubmitDeal(dealId);
		
	}


}
