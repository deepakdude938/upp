package com.upp.stepdefinition;

import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.notifications.Notifications;
import com.upp.pagemodules.payment.Payment_Retention;

import io.cucumber.java.en.Then;

public class TS20 {
	Payment_Retention retention;
	public Reports_ExecutionReport re;
	
	public TS20() {
		retention = new Payment_Retention();
		re=new Reports_ExecutionReport();
		
		}

	@Then("Create Retention-Surplus with given {string}")
	public void create_retention_with_given(String TSID) throws Exception {
		retention.createRetentionSurplus(TSID);
	}
	
	@Then("Validate SubInstruction Type as {string} and {string} and {string}")
	public void validate_SubInstruction_Type_as_and_and(String payment, String surplus, String retention1) throws Exception {
		re.checkSubInstructionTypeInExecutionReport(payment,surplus,retention1);
	}
	
}
