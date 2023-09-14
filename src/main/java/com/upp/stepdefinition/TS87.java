package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.AttachDetach_Account_Api;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.cucumber.java.en.Then;

public class TS87 {
		public AttachDetach_Account_Api api;
		public LoginAPI_UPP login_UPP;
		public DealBasicDetailCreators dc;
		
	public TS87(){
		api=new AttachDetach_Account_Api();
		login_UPP = new LoginAPI_UPP();
		dc=new DealBasicDetailCreators();
	}
	
	@Then("Call the Attach Physical Account Api for {string}")
	public void call_the_Attach_Account_Api(String TSID) throws Exception {
		login_UPP.loginToUpp();
		api.attachPhysicalAccountApi(TSID);
	}
	
	
	@Then("Verify Physical account present in Account Tab {string}")
	public void verify_Physical_account_present_in_Account_Tab(String TSID) throws Exception {
		 api.verifyPhysicalAccountPresentInAccount(TSID);
	}

	@Then("Verify Physical Account present in Party {string}")
	public void verify_Physical_Account_present_in_Party(String TSID) throws Exception {
		api.verifyPhysicalAccountPresentInParty(TSID);
	}
	
}
