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

public class TS145 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	public String dealid;
	CloseLiveDeal dl;
	LifeCycleMaker lm;
	LifeCycleChecker lc;
	VerifyClosedStatusforDealId status;

	public TS145() {

		this.dm = new DashBoard_Module();
		dl = new CloseLiveDeal();
		lm = new LifeCycleMaker();
		lc = new LifeCycleChecker();
		status= new VerifyClosedStatusforDealId();

	}

	@And("Reject the Workitem from Dealchecker with given {string}")
	public void reject_the_Workitem_from_Dealchecker_with_given(String string) throws Exception {
		lc.Reject_From_LifecycleChecker(TS145.dealId);
	}

	@Then("Deal Lifecycle Maker should have the rejected deal")
	public void verify_the_rejected_deal_in_Lifecycle_maker() throws Exception {
		lm.verify_rejDeal_LifecycleMaker(TS145.dealId);
	}
	
}
