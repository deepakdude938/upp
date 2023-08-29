package com.upp.stepdefinition;

import com.upp.InitiationRulesApi.Rule_Map_VA_PA_ParticipantId;
import com.upp.pagemodules.payment.Payment_SubInstruction;

import io.cucumber.java.en.Then;

public class TS82 {
	Payment_SubInstruction ps;
	
	public TS82(){
		ps=new Payment_SubInstruction();
	}
	
	@Then("Edit subinstruction {string}")
	public void edit_subinstruction(String TSID) {
	  ps.editSubInstruction(TSID);
	}

}
