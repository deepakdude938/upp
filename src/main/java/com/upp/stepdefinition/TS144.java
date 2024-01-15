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
	
//	@Then("Create Payments in schedule instruction for {string}")
//	public void create_Payments_in_schedule_instruction_for(String tsid) throws Exception {
//	   pm.createPaymentsScheduleInstruction(tsid,DealPage.sourceAccountNo,DealPage.toaccountNo);
//	}
	
	
//	@Then("Create Payment_SubInstruction for {string}")
//	public void create_Payment_SubInstruction_for(String tsid) throws Exception {
//		   pm.createPaymentsSubInstruction(tsid,DealPage.sourceAccountNo,DealPage.toaccountNo);
//
//	}

//	@Then("Validate Execution Report {string}")
//	public void validate_Execution_Report(String TSID) throws Exception {
//		re.validateSplitFixedAmountInExecutionReport(TSID);
//	}
	
	@Then("Verify Split Fixed Amount Execution Report {string}")
	public void verify_Split_Fixed_Amount_Execution_Report(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
