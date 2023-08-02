package com.upp.stepdefinition;

import com.upp.pagemodules.Transactions.Reports_ExecutionReport;

import io.cucumber.java.en.Then;

public class TS69 {

	public Reports_ExecutionReport re;
	
	public TS69() {
		re= new Reports_ExecutionReport();
	}
	
	
	@Then("Validate in execution report for scheduled record {string}")
	public void validate_in_execution_report_for_scheduled_record(String tsid) throws Exception {
	   re.validateScheduledStatusforRecord(tsid);
	}
	
	@Then("Validate status in execution report {string}")
	public void validate_status_in_execution_report(String string) {
	   
	}
}
