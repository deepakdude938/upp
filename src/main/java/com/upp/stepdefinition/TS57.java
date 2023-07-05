package com.upp.stepdefinition;

import com.upp.pagemodules.Parties_Maker_Checker.Party_Checker;

import io.cucumber.java.en.Then;

public class TS57 {
	public Party_Checker pc;
	public TS57() {
		pc=new Party_Checker();
	}
	
	@Then("Verify responsibility attributes")
	public void verify_responsibility_attributes() throws Exception {
	    pc.verifyResponsibilityAttributes();
	}
}
