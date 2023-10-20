package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.Budget;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Audit.Verify_Budget_Details;

import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Payment;
import com.upp.pagemodules.payment.Payment_BasicDetails;
import com.upp.pagemodules.payment.Payment_Notification;
import com.upp.pagemodules.payment.Payment_Retry;
import com.upp.pagemodules.payment.Payment_Schedule;
import com.upp.pagemodules.payment.Payment_SubInstruction;

import io.cucumber.java.en.*;

public class TS110 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TnxId = "";
	Verify_Budget_Details vb;
	Reports_ExecutionReport report;
	Budget budget;
	

	public TS110() {

		this.dm = new DashBoard_Module();
		vb=new Verify_Budget_Details();
		report=new Reports_ExecutionReport();
		budget=new Budget();
	}

	

@Then("Wait for {int} mins")
public void wait_for_mins(Integer int1) throws Exception {
 
	Thread.sleep(180000);
}


@Then("Check the Utilized budget amount in Audit_Budget Tab with given {string}")
public void check_the_Utilized_budget_amount_in_Audit_Budget_Tab_with_given(String string) throws Exception {
	System.out.println("the TSID is:"+string);
    vb.verify_Utilized_details_In_AuditTab(TS06.dealId, string);
}

@Then("Check the Utilized budget amount in Budget_Utilization_Report with given {string}")
public void check_the_Utilized_budget_amount_in_Budget_Utilization_Report_with_given(String string) throws Exception {
   report.check_Utilized_Budget_in_Budget_Utilization_Report(string,TS06.dealId);
}

@Then("Edit the deal and Check the Utilized budget amount in BudgetTab with given {string}")
public void edit_the_deal_and_Check_the_Utilized_budget_amount_in_BudgetTab_with_given(String string) throws Exception {
    budget.edit_Deal_And_Verify_Utilized_Budget_Available_Budget_For_GB_Account(string,TS06.dealId);
}

	
}
