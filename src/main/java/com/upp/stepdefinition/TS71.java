package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.pagemodules.Budget;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Payment_SubInstruction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TS71 {

	public Budget budget;
	Payment_SubInstruction instru;
	
	public TS71() {
		budget = new Budget();
		instru = new Payment_SubInstruction();
	}

//	@Then("Validate in execution report for scheduled record {string}")
//	public void validate_in_execution_report_for_scheduled_record(String tsid) throws Exception {
//	   re.validateScheduledStatusforRecord(tsid);
//	}

	@Given("Create Payments in the Scheduled Instructions for halfYearly with given {string}")
	public void create_Payments_in_the_Scheduled_Instructions_for_halfYearly_with_given(String string)
			throws Exception {
		budget.createBudget_Payments_ForPuposeBudget(string, DealPage.sourceAccountNo, DealPage.toaccountNo);
	}

	@Then("Create a Budget for halfYearly with given {string}")
	public void create_a_Budget_for_halfYearly_with_given(String string) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		budget.CreateBudget_Purpose_HalfYearly(string, DealPage.sourceAccountNo, DealPage.toaccountNo);
	}

	@Then("Create Payment_SubInstruction in the scheduled Instructions for Budget with given {string}")
	public void create_Payment_SubInstruction_in_the_scheduled_Instructions_for_Budget_with_given(String string) throws Exception {
		instru.createPayments_Subinstruction_For_PurposeBudget(string,DealPage.sourceAccountNo,DealPage.toaccountNo);
	}

}
