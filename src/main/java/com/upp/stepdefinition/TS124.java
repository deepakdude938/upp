package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.pagemodules.Account.AccountAmendment;
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.cucumber.java.en.Then;

public class TS124 {
	
	public LoginAPI_UPP login_UPP;
	public AccountAmendment am;
	
	public TS124() {
		login_UPP = new LoginAPI_UPP();
		am= new AccountAmendment();
	}
	
	@Then("Hit AccountAmmendment_UpdateCreditorLookUpKeys_Api {string}")
	public void hit_AccountAmmendment_UpdateCreditorLookUpKeys_Api(String tsid) throws IOException, Exception {
		login_UPP.loginToUpp();
		am.call_AccountAmmendment_UpdateCreditorLookUpKeys_Api(tsid);
	}

}
