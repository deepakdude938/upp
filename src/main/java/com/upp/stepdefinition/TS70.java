package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.odp.utils.ODP_JSON_ASSERTIONS_PAYLOAD;
import com.upp.pagemodules.Budget;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;

import io.cucumber.java.en.Then;

public class TS70 extends BaseClass {

	public Budget budget;
	public String tsid;
	public static String sourceAccountNo = "";
	public static String toAccountNo = "";
	public DashBoard_Module dashboard;
	ODP_JSON_ASSERTIONS_PAYLOAD payload;
	Reports_ExecutionReport report;

	public TS70() {
		budget = new Budget();
		dashboard = new DashBoard_Module();
		payload = new ODP_JSON_ASSERTIONS_PAYLOAD();
		report=new Reports_ExecutionReport();

	}

	@Then("Create Budget_Consolidated_Yearly with given {string}")
	public void create_Budget_Consolidated_Yearly_with_given(String string) throws Exception {
		budget.CreateBudget_Consolidated_Yearly(string, DealPage.sourceAccountNo, DealPage.toaccountNo);
	}

	@Then("Create odp json payload file for Budget with given {string}")
	public void create_odp_json_payload_file_for_TS70_with_given(String string) throws Exception {
		payload.Odp_Json_for_Budget(string);
	}
	@Then("Verify Tnx Status as Triggered or settled with given {string}")
	public void verify_Tnx_Status_as_Triggered_with_given(String string) throws Exception {
	    report.check_Triggered_or_Settled_Status(string,dealId);
	}
	@Then("Verify in Budget tab Utilized Budget Amount with given {string}")
	public void verify_in_Budget_tab_Utilized_Budget_Amount_and_Available_Budget_Amount(String string) throws Exception {
	   budget.Edit_Deal_And_Verify_Utilized_Budget(string, dealId);
	}
	@Then("Verify in Report that one Transaction is Settled and the other is Rejected with given {string}")
	public void verify_in_Report_that_one_Transaction_is_Settled_and_the_other_is_Rejected(String string) throws Exception {
		report.check_one_Tnx_settled_and_second_Tnx_rejected(string, dealId);
	}

}
