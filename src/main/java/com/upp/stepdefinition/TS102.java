package com.upp.stepdefinition;

import com.upp.pagemodules.Account.AccountAmendment;
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.cucumber.java.en.Then;

public class TS102 {
	public LoginAPI_UPP login_UPP;
	public AccountAmendment am;
	
	public TS102() {
		login_UPP = new LoginAPI_UPP();
		am= new AccountAmendment();
	}
	
	@Then("Call the Account Ammendment Api {string}")
	public void call_the_Account_Ammendment_Api(String TSID) throws Exception {
		login_UPP.loginToUpp();
		am.callAccountAmendment(TSID);
	}
}
