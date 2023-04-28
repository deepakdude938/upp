package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Deal.PriorityDependency;
import com.upp.pagemodules.DealLifeCycle.CloseLiveDeal;
import com.upp.pagemodules.DealLifeCycle.LifeCycleChecker;
import com.upp.pagemodules.DealLifeCycle.LifeCycleMaker;
import com.upp.pagemodules.DealLifeCycle.VerifyClosedStatusforDealId;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Balance_Reporting;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.*;

public class TS23 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	public String dealid;
	CloseLiveDeal dl;
	LifeCycleMaker lm;
	LifeCycleChecker lc;
	VerifyClosedStatusforDealId status;

	public TS23() {

		this.dm = new DashBoard_Module();
		dl = new CloseLiveDeal();
		lm = new LifeCycleMaker();
		lc = new LifeCycleChecker();
		status= new VerifyClosedStatusforDealId();

	}

	@Then("Close the deal from the Live Deal Section with given {string}")
	public void close_the_deal_from_the_Live_Deal(String string) throws Exception {
		dl.closeLiveDeal(TS06.dealId);

	}

	@Then("Create Lifecycle maker workitem with given {string}")
	public void create_Lifecycle_maker_workitem_with_given(String string) throws Exception {
		lm.CreateLifecycleMakerWorkitem(TS06.dealId, string);
	}

	@Given("Approve the Workitem from Dealchecker with given {string}")
	public void approve_the_Workitem_from_Dealchecker_with_given(String string) throws Exception {
		lc.Approve_From_LifecycleChecker(TS06.dealId);
	}

	@Then("Verify the deal Status as Closed")
	public void verify_the_deal_Status_as_Closed() throws Exception {
		
		status.Verify_Closed_Status_For_DealId(TS06.dealId);
	}
}
