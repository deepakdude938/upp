package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.Api.utils.LogOutApi;
import com.upp.InitiationRulesApi.Rule_Non_OBO;
import com.upp.InitiationRulesApi.Rule_Non_OBO_Virtual;
import com.upp.InitiationRulesApi.Rule_OBOParticipant_OBO_Info_Null_OBO_Virtual;
import com.upp.InitiationRulesApi.Rule_Static_OBO_Virtual;
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
import com.upp.odp.utils.Create_ODP_Virtual_Account_Api;
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

public class TS83 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	LoginAPI_ODP login;
	Create_ODP_Account_Api createAcc;
	Logout_ODP_Api logout;
	LoginAPI_UPP login_UPP;
	LogOutApi logout_UPP;
	Create_ODP_Virtual_Account_Api virtual;
	DealAccountCreator acc;
	Rule_OBOParticipant_OBO_Info_Null_OBO_Virtual rule_virtual;

	public TS83() {

		this.dm = new DashBoard_Module();
		login = new LoginAPI_ODP();
		createAcc = new Create_ODP_Account_Api();
		logout = new Logout_ODP_Api();
		login_UPP = new LoginAPI_UPP();
		logout_UPP = new LogOutApi();
		virtual = new Create_ODP_Virtual_Account_Api();
		acc = new DealAccountCreator();
		rule_virtual = new Rule_OBOParticipant_OBO_Info_Null_OBO_Virtual();

	}

	@Then("Call the Rule_ParticipantId_OBODetails_Virtual Api with given {string}.")
	public void call_the_Rule_ParticipantId_OBODetails_Virtual_Api_with_given(String string) throws Exception {
		System.out.println("Test case ID = "+string);
		rule_virtual.Rule_OBOParticipant_OBO_Info_Null_OBO_Virtual_Api(TS06.dealId,string,TS75.virtual_Account_Number);
	}

}
