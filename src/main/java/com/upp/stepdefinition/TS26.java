package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.configuration.PU_AdminChecker;
import com.upp.pagemodules.configuration.ProcessingUnit;
import com.upp.pagemodules.configuration.Verify_PU_Added;

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

public class TS26 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	ProcessingUnit pu;
	PU_AdminChecker ac;
	Verify_PU_Added verify;
	public String  ProcessingUnit;

	public TS26() {

		this.dm = new DashBoard_Module();
		pu=new ProcessingUnit();
		ac=new PU_AdminChecker();
		verify=new Verify_PU_Added();
	}
	
	@Then("Create Processing Unit from Configuration tab with given {string}")
	public void create_Processing_Unit_from_Configuration_tab_with_given(String string) throws Exception {
	    ProcessingUnit=pu.CreateProcessingUnit(string);
	}
	@Then("Approve the PU from Admin Checker")
	public void approve_the_PU_from_Admin_Checker() throws Exception {
	    ac.Aprrove_PU_From_AdminChecker();
	}
	@And("Verify the created PU is available in Basic Details Page with given {string}")
	public void verify_the_created_PU_is_available_in_Basic_Details_Page(String string) throws Exception {
	    verify.Verify_PU_Added_In_BasicDetailsPage(string,ProcessingUnit);
	}
}
