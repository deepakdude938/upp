package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.Api.utils.LogOutApi;
import com.upp.InitiationRulesApi.Rule_Non_OBO;
import com.upp.InitiationRulesApi.Rule_OBODetails_Null_OBO;
import com.upp.InitiationRulesApi.Rule_OBOParticipant_OBO_Info_Not_Null;
import com.upp.InitiationRulesApi.Rule_OBOPartyResponsibility_PartyId;
import com.upp.InitiationRulesApi.Rule_OBOPartyResponsibility_PartyId_DealRefId;
import com.upp.InitiationRulesApi.Rule_OBO_Participant_Enrich;
import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Account.EditOBOResponsibilty;
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

public class TS62 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	LoginAPI_ODP login;
	Create_ODP_Account_Api createAcc;
	Logout_ODP_Api logout;
	LoginAPI_UPP login_UPP;
	LogOutApi logout_UPP;
	EditOBOResponsibilty edit;
	Rule_OBO_Participant_Enrich rule;
	public static String endId="";
	Reports_ExecutionReport report;
	LogOutApi logoutUPPApi;

	public TS62() {

		this.dm = new DashBoard_Module();
		login = new LoginAPI_ODP();
		createAcc = new Create_ODP_Account_Api();
		logout = new Logout_ODP_Api();
		login_UPP = new LoginAPI_UPP();
		logout_UPP = new LogOutApi();
	    edit=new EditOBOResponsibilty();
	    rule=new Rule_OBO_Participant_Enrich();
	    logoutUPPApi=new LogOutApi();
	    report=new Reports_ExecutionReport();

	}
	@And("Call the Rule_OBO_Participant_Enrich with given {string}.")
	public void call_the_Rule_OBO_Participant_Enrich_with_given(String string) throws Exception {
		endId = rule.Rule_OBO_Participant_Enrich_Api(TS06.dealId, string);
	}
	
	@Then("Edit the account and select OBO Responsibility with given {string}.")
	public void edit_the_account_and_select_OBO_Responsibility_with_given(String string) throws Exception {
	   edit.EditOBOResponsibilty_In_Account(string);
	}
	
	@Then("Verify in EcommExecution the status")
	public void verify_in_EcommExecution_the_status() throws Exception {
	    report.eCommExecutionsReport_Status(endId);
	}
	
	@Given("Logout of UPP through api")
	public void logout_of_UPP_through_api() throws Exception {
		logoutUPPApi.logOut();
	}

}
