package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.Api.utils.LogOutApi;
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
import com.upp.odp.utils.Fetch_EndToEndId_From_ODP;
import com.upp.odp.utils.Logout_ODP_Api;
import com.upp.odp.utils.ODP_JSON_ASSERTIONS_PAYLOAD;
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

public class TS55 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	LoginAPI_ODP login;
	Create_ODP_Account_Api createAcc;
	Logout_ODP_Api logout;
	LoginAPI_UPP login_UPP;
	LogOutApi logout_UPP;
	Rule_IN_BT rule;
	Reports_ExecutionReport report;
	public static String paymentRefId = "";
	ODP_JSON_ASSERTIONS_PAYLOAD odpRecordEndToEnd;
	Fetch_EndToEndId_From_ODP fetch;

	public TS55() {

		this.dm = new DashBoard_Module();
		login = new LoginAPI_ODP();
		createAcc = new Create_ODP_Account_Api();
		logout = new Logout_ODP_Api();
		login_UPP = new LoginAPI_UPP();
		logout_UPP = new LogOutApi();
		rule=new Rule_IN_BT();
		report=new Reports_ExecutionReport();
		odpRecordEndToEnd=new ODP_JSON_ASSERTIONS_PAYLOAD();
		fetch=new Fetch_EndToEndId_From_ODP();
	}
	

	@And("Call the Rule_IN_BT Api with given {string}.")
	public void call_the_Rule_IN_BT_Api_with_given(String string) throws Exception {
		 endToEndIdRule= rule.Rule_IN_BT_System_Level(TS06.dealId, string);
	}
	
	@Then("Verify in Ecomm Execution Report with given {string}.")
	public void verify_in_Ecomm_Execution_Report_with_given(String string) throws Exception {
		paymentRefId=report.eCommExecutionsReportCommon(endToEndIdAssertion);	
	}
	
	@And("Get the BatchId from Ecomm Payments")
	public void get_the_BatchId_from_Ecomm_Payments() throws Exception {
		batchId= report.getBatchIdFromEcommPayments(paymentRefId);
	}
    
	@And("Verify the Pain File For Rule_IN_BT_SystemLevel")
	public void verify_the_Pain_File_For_Rule_IN_BT_SystemLevel() {
		rule.verify_Rule_IN_BT_System_Level_PainFile(batchId);
	}
	@Then("Create odp json payload file with EndToEndId with given {string}")
	public void create_odp_json_payload_file_with_EndToEndId_with_given(String string) throws Exception {
		odpRecordEndToEnd.Odp_Json_With_EndToEndId(string);
	}
	@Then("Fetch EndToEndId from ODP Record with {string}")
	public void fetch_EndTorEndId_from_ODP_Record_with(String string) throws Exception {
		fetch.fetchEndToEndIdFromODP(string);
	}

}
