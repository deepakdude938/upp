package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.Api.utils.LogOutApi;
import com.upp.InitiationRulesApi.Rule_Non_OBO;
import com.upp.InitiationRulesApi.Rule_Without_PaymentInfoDetails;
import com.upp.PaymentRulesApi.Rule_IN_BT;
import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;
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
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Balance_Reporting;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.*;

public class TS101 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	Rule_Without_PaymentInfoDetails rule;
	Reports_ExecutionReport report;
	
	Rule_IN_BT rule1;
	

	public TS101() {

		this.dm = new DashBoard_Module();
		rule=new Rule_Without_PaymentInfoDetails();
		report=new Reports_ExecutionReport();
        rule1=new Rule_IN_BT();
	}

	@Then("Call the Rule_Without_PaymentInfoDetails Api with given {string}.")
	public void call_the_Rule_Without_PaymentInfoDetails_Api_with_given(String string) throws Exception {
		End2EndId=rule.Rule_Without_PaymentInfoDetails_Api(TS06.dealId, string);
	}
    
	@Then("Verify the SourceAccountNumber in Ecomm Executions Report with given {string}.")
	public void verify_the_SourceAccountNumber_in_Ecomm_Executions_Report_with_given(String string) throws Exception {
	 //   report.Verify_Source_AccNo_In_eCommExecutionsReport(endToEndIdRule);
	    report.Verify_Source_AccNo_In_eCommExecutionsReport(End2EndId);
	}
	
	@Then("Verify the AccountNumber Pain File with given {string}.")
	public void verify_the_AccountNumber_Pain_File_with_given(String string) throws Exception {
		rule1.verify_Account_Number_In_PainFile(batchId);
	}

}
