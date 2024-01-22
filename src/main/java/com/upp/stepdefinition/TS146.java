package com.upp.stepdefinition;

import com.upp.pagemodules.payment.Retention_Surplus;

import io.cucumber.java.en.Then;

public class TS146 {
	
	public Retention_Surplus rs;
	
	public TS146() {
		rs=new Retention_Surplus();
	}
	
	@Then("Create Retention-Surplus in scheduled Instruction with given {string}")
	public void create_Retention_Surplus_in_scheduled_Instruction_with_given(String tsid) throws Exception {
	 rs.createRetentionSurplus(tsid);
	}
}
