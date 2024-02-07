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
import com.upp.pagemodules.payment.Payment_Retry;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.*;

public class TS149 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	Reports_ExecutionReport report;
	Payment_Retry retry;
	
	

	public TS149() {

		this.dm = new DashBoard_Module();
		
		report=new Reports_ExecutionReport();
		retry=new Payment_Retry();
      
	}

	@Then("Verify in execution Report One Tnx is Rejected and Reschudled date with same day {string}")
	public void verify_in_execution_Report_One_Tnx_is_Rejected_and_Reschudled_date_with_same_day(String string) throws Exception {
		report.check_one_Tnx_Rejected_and_RescheduledDate_SameDay(string, dealId);   
	}
	
}
