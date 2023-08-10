package com.upp.stepdefinition;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Budget;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;

import io.cucumber.java.en.Then;

public class TS69 extends BaseClass{

	public Reports_ExecutionReport re;
	public Budget budget;
	
	public TS69() {
		re= new Reports_ExecutionReport();
		budget = new Budget();
	}
	
	@Then("Validate in execution report for scheduled record {string}")
	public void validate_in_execution_report_for_scheduled_record(String tsid) throws Exception {
	   re.validateScheduledStatusforRecord(tsid);
	}
	
	@Then("Validate status in execution report {string}")
	public void validate_status_in_execution_report(String string) throws Exception {
		 re.check_one_Tnx_settled_and_second_Tnx_rejected(string,dealId);
	}
	
	@Then("Verify in Budget tab Utilized Budget Available Budget Amount with given {string}")
	public void verify_in_Budget_tab_Utilized_Budget_Available_Budget_Amount_with_given(String string) throws Exception {
		   budget.edit_Deal_And_Verify_Utilized_Budget_Available_Budget(string, dealId);
	}
}
