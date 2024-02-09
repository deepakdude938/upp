package com.upp.stepdefinition;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Retention_Surplus;

import io.cucumber.java.en.Then;

public class TS146 extends BaseClass{
	
	public Retention_Surplus rs;
	Reports_ExecutionReport report;
	
	public TS146() {
		rs=new Retention_Surplus();
		report = new Reports_ExecutionReport();
	}
	
	@Then("Create Retention-Surplus in scheduled Instruction with given {string}")
	public void create_Retention_Surplus_in_scheduled_Instruction_with_given(String tsid) throws Exception {
	 rs.createRetentionSurplus(tsid);
	}
	
	@Then("Check status and instruction type for Retention_Surplus with given {string}")
	public void check_status_and_instruction_type_for_Retention_Surplus_with_given(String string) throws Exception {
		report.checkInstructionTypeRetention_Surplus(string,dealId);
	}
	
	@Then("Verify Tnx Status as Triggered or settled for all Two Transactions with given {string}")
	public void verify_Tnx_Status_as_Triggered_or_settled_for_all_Two_Transactions_with_given(String string) throws Exception {
		report.checkBothTransactionStatusIsSettled(string, string);
	}
	
}
