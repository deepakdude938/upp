package com.upp.stepdefinition;

import com.upp.InitiationRulesApi.InstructedControlAmountRegression;
import com.upp.InitiationRulesApi.Rule_dealRefId_V3_UD;
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.cucumber.java.en.Then;

public class TS66 {
	public InstructedControlAmountRegression rule;
	public LoginAPI_UPP login_UPP;
	
	public TS66() {
		rule=new InstructedControlAmountRegression();
		login_UPP = new LoginAPI_UPP();
	}
	
	
	@Then("Run Instructed Control Amount using api with given {string}")
	public void run_Instructed_Control_Amount_using_api_with_given(String TSID) throws Exception {
		login_UPP.loginToUpp();
		rule.instructedControlAmount(TSID);
	}

}
