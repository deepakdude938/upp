package com.upp.stepdefinition;

import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Payment;
import com.upp.pagemodules.payment.Payment_Retention;

import io.cucumber.java.en.Then;

public class TS144 {

	public Reports_ExecutionReport re;
	public Payment pm;
	public TS144() {
		re = new Reports_ExecutionReport();
		this.pm = new Payment();
	}
	
	@Then("Verify Split Fixed Amount Execution Report {string}")
	public void verify_Split_Fixed_Amount_Execution_Report(String string) throws Exception {
		 re.verifySplitFixedAmounts(string);
	}
}
