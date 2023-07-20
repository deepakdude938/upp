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
import com.upp.InitiationRulesApi.Rule_OBO_Participant_Enrich1;
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

public class TS64 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	LoginAPI_ODP login;
	Rule_OBO_Participant_Enrich1 enrich1;

	public TS64() {

		this.dm = new DashBoard_Module();
		login = new LoginAPI_ODP();
		enrich1=new Rule_OBO_Participant_Enrich1();
		

	}
	
	@And("Call the Rule_OBO_Participant_Enrich1 with given {string}.")
	public void call_the_Rule_OBO_Participant_Enrich1_with_given(String string) throws Exception {
	  TS62.endId= enrich1.Rule_OBO_Participant_Enrich1_Api(TS06.dealId, string);
	}


	

}
