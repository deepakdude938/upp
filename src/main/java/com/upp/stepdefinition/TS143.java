package com.upp.stepdefinition;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;

import io.cucumber.java.en.Then;

public class TS143 extends BaseClass{
	
	Reports_ExecutionReport report;
	
			public TS143(){
				report=new Reports_ExecutionReport();
			}
	
	@Then("Check the Transaction status of all {string} Transactions is Scheduled in execution report with given {string}")
	public void check_the_Transaction_status_of_all_Transactions_is_Scheduled_in_execution_report_with_given(String string, String tsid) throws Exception {
	   report.check_All_3_Transaction_StatusIsScheduled(tsid, dealId);
	}

}
