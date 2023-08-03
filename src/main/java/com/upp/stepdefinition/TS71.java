package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.pagemodules.Budget;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;

import io.cucumber.java.en.Then;

public class TS71 {

	public Budget budget;

	public TS71() {
		budget = new Budget();
	}

//	@Then("Validate in execution report for scheduled record {string}")
//	public void validate_in_execution_report_for_scheduled_record(String tsid) throws Exception {
//	   re.validateScheduledStatusforRecord(tsid);
//	}

	@Then("Create a Budget for halfYearly with given {string}")
	public void create_a_Budget_for_halfYearly_with_given(String string) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		budget.CreateBudget_Purpose_HalfYearly(string, DealPage.sourceAccountNo, DealPage.toaccountNo);
	}

}
