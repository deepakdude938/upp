package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.Create_Tx_OBO_Both_And_Ultimate_Debtor;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.cucumber.java.en.Then;

public class TS100 extends BaseClass{
	public LoginAPI_UPP login_UPP;
	public Create_Tx_OBO_Both_And_Ultimate_Debtor txn;
	public TS100() {
		login_UPP = new LoginAPI_UPP();
		txn=new Create_Tx_OBO_Both_And_Ultimate_Debtor();
	}
	
	@Then("Call the Create Tx OBO both and Ultimate Debtor using api with given {string}")
	public void call_the_Create_Tx_OBO_both_and_Ultimate_Debtor_using_api_with_given(String TSID) throws Exception {
		login_UPP.loginToUpp();
		endToEndId=   txn.callTxOboAndUltimateDebtor(TSID);
	}

}
