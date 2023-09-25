package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.CreateTransactionWithTwoFragments;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.cucumber.java.en.Then;

public class TS97 extends BaseClass {
	public LoginAPI_UPP login_UPP;
	public CreateTransactionWithTwoFragments txn;
	public TS97() {
		login_UPP = new LoginAPI_UPP();
		txn=new CreateTransactionWithTwoFragments();
	}

	@Then("Call the Create tx with two fragments for {string}")
	public void call_the_Create_tx_with_two_fragments_for(String TSID) throws IOException, Exception {
		login_UPP.loginToUpp();
	  txn.callTxnWithTwoFragments(TSID);
	}
}
