package com.upp.stepdefinition;

import com.upp.InitiationRulesApi.Rule_dealRefId_V3_UD;
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.cucumber.java.en.Then;

public class TS45 {

	public Rule_dealRefId_V3_UD rule;
	public LoginAPI_UPP login_UPP;
	
	
	public TS45() {
		rule=new Rule_dealRefId_V3_UD();
		login_UPP = new LoginAPI_UPP();
	}
	
	
	@Then("Run Rule_Charge_Bearer_Validation using api with given {string}")
	public void run_Rule_Charge_Bearer_Validation_using_api_with_given(String TSID) throws Exception {
		login_UPP.loginToUpp();
		   rule.runRule_dealRefId_V3_UD(TSID,TS06.dealId);
	}
	
}
