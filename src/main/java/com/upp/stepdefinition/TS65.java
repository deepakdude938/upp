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
import com.upp.PaymentRulesApi.Rule_IN_LT;
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

public class TS65 extends BaseClass {
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
	Rule_IN_LT rule;
	public String endId = "";
	Reports_ExecutionReport report;
	LogOutApi logoutUPPApi;
	public static String endToEndIdRule = "";
	public static String batchId = "";
	public static String paymentRefId = "";
	public static DealAccountCreator account;

	public TS65() {

		this.dm = new DashBoard_Module();
		login = new LoginAPI_ODP();
		createAcc = new Create_ODP_Account_Api();
		logout = new Logout_ODP_Api();
		login_UPP = new LoginAPI_UPP();
		logout_UPP = new LogOutApi();
		edit = new EditOBOResponsibilty();
		rule = new Rule_IN_LT();
		logoutUPPApi = new LogOutApi();
		report = new Reports_ExecutionReport();
		account = new DealAccountCreator();

	}

	@Given("Add Contextualize in account")
	public void add_Contextualize_in_account() throws Exception {
		account.addContextualize();
	}
	
	@Given("Call the Rule_IN_LT Api with given {string} for account Level.")
	public void call_the_Rule_IN_LT_Api_with_given_for_deal_Level(String string) throws Exception {
		endToEndIdRule = rule.Rule_IN_LT_System_Level(TS06.dealId, string);
		System.out.println(endToEndIdRule);
	}

	@Then("Verify Status in Ecomm Execution Report with given {string} for accountLevel.")
	public void verify_Status_in_Ecomm_Execution_Report_with_given_for_dealLevel(String string) throws Exception {
		System.out.println(endToEndIdRule);
		paymentRefId = report.eCommExecutionsReportCommon(endToEndIdRule);
	}

	@Then("Get the BatchId from payment refID for accountLevel")
	public void get_the_BatchId_from_payment_refID_for_dealLevel() throws Exception {
		batchId = report.getBatchIdFromEcommPayments(paymentRefId);
		System.out.println("Batch Id = " + batchId);
	}

	@Given("Verify the Pain File For Rule_IN_LT_AccountLevel")
	public void verify_the_Pain_File_For_Rule_IN_LT_AccountLevel() {
		String bID = TS60.batchId;
		System.out.println("BId = "+bID);
		rule.verify_Rule_IN_LT_Account_Level_PainFile(bID);
	}
}
