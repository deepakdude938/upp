package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.Api.utils.LogOutApi;
import com.upp.Api.utils.Payload;
import com.upp.Api.utils.TransactionApi;
import com.upp.InitiationRulesApi.Rule_ParticipantId_OBODetails;
import com.upp.InitiationRulesApi.Rule_Static_OBO;
import com.upp.InitiationRulesApi.Rules_EnrichParty_UD1;
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
import com.upp.pagemodules.ECommerce.ECommerceTransactionVerifier;
import com.upp.pagemodules.Login.LoginAPI_ODP;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Edit_LiveDeal;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Balance_Reporting;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.*;

public class TS37 extends BaseClass {
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
	Rules_EnrichParty_UD1 enrichParty;
	ECommerceTransactionVerifier ecommVerifier;
	public Reports_ExecutionReport execReport;
	Party_Edit_LiveDeal editDeal;
	EditOBOResponsibilty edit;

	public TS37() {

		this.dm = new DashBoard_Module();
		login = new LoginAPI_ODP();
		createAcc = new Create_ODP_Account_Api();
		logout = new Logout_ODP_Api();
		txn = new TransactionApi();
		login_UPP = new LoginAPI_UPP();
		this.ecommVerifier = new ECommerceTransactionVerifier();
		execReport = new Reports_ExecutionReport();
		editDeal = new Party_Edit_LiveDeal();
		 edit=new EditOBOResponsibilty();
	}

	@Then("Edit the live deal.")
	public void edit_the_live_deal() throws Exception {
		System.out.println("Dealid =="+TS06.dealId);
		editDeal.editLiveDeal(TS06.dealId);
	}

	@Given("Run Rule_EnrichParty_UD1 rule using api with given {string}")
	public void run_Rule_EnrichParty_UD1_rule_using_api_with_given(String string) throws Exception {
		login_UPP.loginToUpp();
		enrichParty.Rule_EnrichParty_UD1(string, TS06.dealId, TS06.sourceAccountNo);
	}

	@Given("Veriy transaction from transaction verifier")
	public void veriy_transaction_from_transaction_verifier() throws Exception {
		ecommVerifier.txnVerifier_ApproveDeal(TS06.dealId);
	}

	@Then("Validate transaction in ecommerce report")
	public void validate_transaction_in_ecommerce_report() throws Exception {
		execReport.validateEcommTransactionwithDealId(TS06.dealId);
	}
	
	@Then("Edit the account and select Participant OBO Responsibility with given {string}.")
	public void edit_the_account_and_select_Participant_OBO_Responsibility_with_given(String string) throws Exception {
		 edit.EditParticipantOBOResponsibilty_In_Account(string);
	}

}
