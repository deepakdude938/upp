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

public class TS72 extends BaseClass {

	public Budget budget;
	public String tsid;
	public static String sourceAccountNo = "";
	public static String toAccountNo = "";
	public DashBoard_Module dashboard;
	ODP_JSON_ASSERTIONS_PAYLOAD payload;
	Reports_ExecutionReport report;

	public TS72() {
		budget = new Budget();
		dashboard = new DashBoard_Module();
		payload = new ODP_JSON_ASSERTIONS_PAYLOAD();
		report=new Reports_ExecutionReport();

	}

	@Then("Create Budget_PurposeandDestination_DateRange with given {string}")
	public void create_Budget_PurposeandDestination_DateRange_with_given(String string) throws Exception {
	  budget.CreateBudget_Purpose_And_Destination_With_DateRange(string, DealPage.sourceAccountNo, DealPage.toaccountNo);
	}

}
