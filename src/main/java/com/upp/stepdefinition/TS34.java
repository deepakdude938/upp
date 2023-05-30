package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.Api.utils.LogOutApi;
import com.upp.Api.utils.Payload;
import com.upp.Api.utils.TransactionApi;
import com.upp.InitiationRulesApi.Rule_ParticipantId_OBODetails;
import com.upp.InitiationRulesApi.Rule_Static_OBO;
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

public class TS34 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	LoginAPI_ODP login;
	Create_ODP_Account_Api createAcc;
	Logout_ODP_Api logout;
	Payload payload;
	TransactionApi txn;
	LoginAPI_UPP login_UPP;
	LogOutApi logout_UPP;
	Rule_ParticipantId_OBODetails participantObo;

	public TS34() {

		this.dm = new DashBoard_Module();
		login = new LoginAPI_ODP();
		createAcc = new Create_ODP_Account_Api();
		logout = new Logout_ODP_Api();
		txn = new TransactionApi();
		login_UPP = new LoginAPI_UPP();
	}

	@Given("Run ParticipantId-OBO Details  rule using api with given {string}")
	public void run_ParticipantId_OBO_Details_rule_using_api_with_given(String string) throws Exception {
		login_UPP.loginToUpp();
		//participantObo.Rule_ParticipantId_OBODetails_Api(string, TS06.dealId, TS06.sourceAccountNo);
		participantObo.Rule_ParticipantId_OBODetails_Api(string, "REF1685363326759", "8345785391");
	}

}
