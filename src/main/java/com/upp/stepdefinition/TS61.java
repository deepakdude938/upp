package com.upp.stepdefinition;

import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Payment_Retention;
import com.upp.pagemodules.payment.Retention;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TS61 {

	public Retention retention;
	public Reports_ExecutionReport re;
	public TS61() {
		retention = new Retention();
		re=new Reports_ExecutionReport();
		
		}
	
	@Then("Create Retention in the Scheduled Instructions with given {string}")
	public void create_Retention_in_the_Scheduled_Instructions_with_given(String tsid) throws Exception {
	    retention.createRetention(tsid);
	}
	
	@Then("Create json payload file {string}")
	public void create_json_payload_file(String TSID) throws Exception {
		retention.createJsonFile(TSID);
	}
	
	@Given("Fetch record from ODP with {string}")
	public void fetch_record_from_ODP_with(String TSID) throws Exception {
	   retention.fetchInputsFromODP(TSID);
	}
	
	@Then("Validate in execution report {string}")
	public void validate_in_execution_report(String TSID) throws Exception {
	   re.validateInExecutionReport(TSID);
	}
	
}
