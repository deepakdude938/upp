package com.upp.stepdefinition;

import com.upp.InitiationRulesApi.Rule_Participant_OBO;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import io.cucumber.java.en.Then;

public class TS35 {

	public Rule_Participant_OBO rule;
	public LoginAPI_UPP login_UPP;
	
	public TS35() {
		rule=new Rule_Participant_OBO();
		login_UPP = new LoginAPI_UPP();
	}
	
	@Then("Run Rule_Participant_OBO using api with given {string}")
	public void run_Rule_Participant_OBO_using_api_with_given(String TSID) throws Exception {
		login_UPP.loginToUpp();
	   rule.runRuleParticipantOBO(TSID,TS06.dealId);
	}
}
