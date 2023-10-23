package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.Api.utils.LogOutApi;
import com.upp.InitiationRulesApi.Rule_Non_OBO;
import com.upp.InitiationRulesApi.Rule_Non_OBO_Virtual;
import com.upp.InitiationRulesApi.Rule_OBODetails_Null_OBO_Virtual;
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
import com.upp.pagemodules.Transactions.Transactions_Checker;
import com.upp.pagemodules.Transactions.Transactions_Verifier;
import com.upp.pagemodules.payment.Balance_Reporting;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.*;

public class TS79 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	Transactions_Checker tc;
	Transactions_Verifier tv;

	public TS79() {

		this.dm = new DashBoard_Module();
		this.tc = new Transactions_Checker();
		this.tv = new Transactions_Verifier();
	}

	@Then("Approve the transaction from Transaction Checker with given {string} for transaction amount greater than limit")
	public void approve_the_transaction_from_Transaction_Checker_with_given_for_transaction_amount_greater_than_limit(
			String string) throws Exception {
		System.out.println("test = "+TS06.TnxId);
		tc.TransactionsChecker1("TS79", TS06.TnxId);
	}
	
	@Then("Approve the transaction from Transaction Verifier for transaction limit with given {string}")
	public void approve_the_transaction_from_Transaction_Verifier_for_transaction_limit_with_given(String string) throws Exception {

		tv.TransactionsVerifier1(string, TS06.TnxId);
	}

}
