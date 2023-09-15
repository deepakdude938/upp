package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.AttachDetach_Account_Api;
import com.upp.Api.utils.TransactionApi;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.cucumber.java.en.Then;

public class TS88 {
		public LoginAPI_UPP login_UPP;
		public DealBasicDetailCreators dc;
		public TransactionApi ta;
		
	public TS88(){
		login_UPP = new LoginAPI_UPP();
		dc=new DealBasicDetailCreators();
		ta=new TransactionApi();
	}
	
	@Then("Call the Create Transaction Api for {string}")
	public void call_the_Create_Transaction_Api_for(String TSID) throws Exception {
		login_UPP.loginToUpp();
	   ta.createTransaction(TSID);
	}
	
}
