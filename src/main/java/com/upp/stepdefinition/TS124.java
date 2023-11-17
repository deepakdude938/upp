package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.pagemodules.Account.AccountAmendment;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.PartyDetails.Party_Accounts;

import io.cucumber.java.en.Then;

public class TS124 {
	
	public LoginAPI_UPP login_UPP;
	public AccountAmendment am;
	public Party_Accounts pa;
	
	public TS124() {
		login_UPP = new LoginAPI_UPP();
		am= new AccountAmendment();
		pa=new Party_Accounts();
	}
	
	@Then("Hit AccountAmmendment_UpdateCreditorLookUpKeys_Api {string}")
	public void hit_AccountAmmendment_UpdateCreditorLookUpKeys_Api(String tsid) throws IOException, Exception {
		login_UPP.loginToUpp();
		am.call_AccountAmmendment_UpdateCreditorLookUpKeys_Api(tsid);
	}
	
	@Then("Validate CreditorLookUpKeys doesnt changed {string}")
	public void validate_CreditorLookUpKeys_doesnt_changed(String tsid) throws Exception {
	   pa.validateCreditorLookUpKeysDoesntChanged(tsid);
	}

}
