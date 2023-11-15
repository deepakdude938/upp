package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.Api.utils.LogOutApi;
import com.upp.InitiationRulesApi.Rule_Non_OBO;
import com.upp.InitiationRulesApi.Rule_With_Partial_PaymentInfoDetails;
import com.upp.InitiationRulesApi.Rule_Without_PaymentInfoDetails;
import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Holiday;
import com.upp.pagemodules.configuration.PU_AdminChecker;
import com.upp.pagemodules.configuration.ProcessingUnit;
import com.upp.pagemodules.configuration.Verify_PU_Added;

import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.odp.utils.Create_ODP_Account_Api;
import com.upp.odp.utils.Logout_ODP_Api;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Deal.PriorityDependency;
import com.upp.pagemodules.DealLifeCycle.CloseLiveDeal;
import com.upp.pagemodules.DealLifeCycle.LifeCycleChecker;
import com.upp.pagemodules.DealLifeCycle.LifeCycleMaker;
import com.upp.pagemodules.DealLifeCycle.VerifyClosedStatusforDealId;
import com.upp.pagemodules.Login.LoginAPI_ODP;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.Transactions.Future_dated_Adhoc_Tnx;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Balance_Reporting;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.*;

public class TS130 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	Reports_ExecutionReport report;
	Holiday holiday;
	Future_dated_Adhoc_Tnx future;
	

	public TS130() {

		this.dm = new DashBoard_Module();
		
		report=new Reports_ExecutionReport();
        holiday=new Holiday();
        future=new Future_dated_Adhoc_Tnx();
	}

	

	@Then("Verify In Future Dated Adhoc Transactions are available with given {string}")
	public void verify_In_Future_Dated_Adhoc_Transactions_are_available_with_given(String string) throws Exception {
	   // future.Verify_both_futured_Tnxs_are_present(string,TS06.dealId);
	    future.Verify_both_futured_Tnxs_are_present(string,TS06.dealId);
	}
	@Then("Edit the Live deal and dont make any changes with given {string}")
	public void edit_the_Live_deal_and_dont_make_any_changes(String string) throws Exception {
	   future.edit_deal_do_nothing(string,TS06.dealId);
	}
	@Then("Cancel one Transaction In Future Dated Adhoc Transaction  with given {string}")
	public void cancel_one_Transaction_In_Future_Dated_Adhoc_Transaction_with_given(String string) throws Exception {
	  future.cancel_One_Transaction_in_future_adhoc_Tnx(string,TS06.dealId);
	}
	
	@Then("Approve the Transaction from Tnx Checker with given {string}")
	public void approve_the_Transaction_from_Tnx_Checker_with_given(String string)throws Exception {
		future.approve_the_tnx_from_tnx_checker(string,dealId);
	}
	@Then("Verify in execution Report One Tnx is Scheduled and other Cancelled with given {string}")
	public void verify_in_execution_Report_One_Tnx_is_Scheduled_and_other_Cancelled_with_given(String string) throws Exception {
	   report.check_one_Tnx_scheduled_and_second_Tnx_Cancelled(string,dealId);
	}
}
