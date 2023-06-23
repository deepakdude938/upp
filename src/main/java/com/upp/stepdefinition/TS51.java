package com.upp.stepdefinition;

import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Payment_Retention;

import io.cucumber.java.en.Then;

public class TS51 {

	public Reports_ExecutionReport re;

	public TS51() {
		re = new Reports_ExecutionReport();
	}

	@Then("Validate Execution Report {string}")
	public void validate_Execution_Report(String TSID) throws Exception {
		re.validateSplitFixedAmountInExecutionReport(TSID);
	}
}
