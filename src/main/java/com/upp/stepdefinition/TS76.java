package com.upp.stepdefinition;

import com.upp.InitiationRulesApi.Rule_Map_VA_PA_ParticipantId;
import com.upp.base.BaseClass;

import io.cucumber.java.en.Then;

public class TS76 extends BaseClass{
	Rule_Map_VA_PA_ParticipantId rule;
	
	
	public TS76(){
		rule=new Rule_Map_VA_PA_ParticipantId();
	}
	
	@Then("Call the Rule_Map_VA_PA_ParticipantId Api with given {string}.")
	public void call_the_Rule_Map_VA_PA_ParticipantId_Api_with_given(String string) throws Exception {
		rule.rule_Map_VA_PA_ParticipantId_Virtual_Api(TS06.dealId,string,virtual_Account_Number);
	}

}
