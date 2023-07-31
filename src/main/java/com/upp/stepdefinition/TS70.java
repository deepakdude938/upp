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

	@Then("Create odp json payload file for TS70 with given {string}")
	public void create_odp_json_payload_file_for_TS70_with_given(String string) throws Exception {
		payload.Odp_Json_for_TS70(string);
	}
	@Then("Verify Tnx Status as Triggered or settled with given {string}")
	public void verify_Tnx_Status_as_Triggered_with_given(String string) throws Exception {
	    report.check_Triggered_or_Settled_Status(string,dealId);
	}

}
