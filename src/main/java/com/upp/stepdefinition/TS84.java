package com.upp.stepdefinition;

import java.io.IOException;
import com.upp.Api.utils.AttachDetach_Account_Api;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import io.cucumber.java.en.Then;

public class TS84 {
		public AttachDetach_Account_Api api;
		public LoginAPI_UPP login_UPP;
		public DealBasicDetailCreators dc;
		
	public TS84(){
		api=new AttachDetach_Account_Api();
		login_UPP = new LoginAPI_UPP();
		dc=new DealBasicDetailCreators();
	}
	
	@Then("Call the Attach Account Api for {string}")
	public void call_the_Attach_Account_Api(String TSID) throws Exception {
		login_UPP.loginToUpp();
		api.attachAccountApi(TSID);
	}
	
	@Then("Edit the deal {string}")
	public void edit_the_deal(String string) throws Exception {
	  dc.editDeal();
	}
	
	@Then("Verify Virtual account present in Account Tab {string}")
	public void verify_Virtual_account_present_in_Account_Tab(String TSID) throws Exception {
	    api.verifyVirtualAccountPresentInAccount(TSID);
	}

	@Then("Verify Virtual Account present in Party {string}")
	public void verify_Virtual_Account_present_in_Party(String TSID) throws Exception {
		api.verifyVirtualAccountPresentInParty(TSID);
	}

	@Then("Call the Detach Account Api for {string}")
	public void call_the_Detach_Account_Api_for(String TSID) throws IOException, Exception {
	  api.detachAccountApi(TSID);
	}
	
	@Then("Verify Virtual Account is disable in Party {string}")
	public void verify_Virtual_Account_is_not_disable_in_Party(String TSID) throws Exception {
		api.verifyVirtualAccountisDisableInParty(TSID);
	}
	
}
