package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.Api.utils.LogOutApi;
import com.upp.InitiationRulesApi.Rule_Non_OBO;
import com.upp.InitiationRulesApi.Rule_Without_PaymentInfoDetails;
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
import com.upp.pagemodules.Transactions.Transactions_Checker;
import com.upp.pagemodules.Transactions.Transactions_Maker_BasicDetails;
import com.upp.pagemodules.Transactions.Transactions_Maker_Documents;
import com.upp.pagemodules.Transactions.Transactions_Maker_Sub_Instruction;
import com.upp.pagemodules.Transactions.Transactions_Maker_Summary;
import com.upp.pagemodules.Transactions.Transactions_Verifier;
import com.upp.pagemodules.payment.Balance_Reporting;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.*;

public class TS103 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
	public static String TSID = "";
	public static String TnxId = "";
	Transactions_Maker_Sub_Instruction tm_sub;
	Transactions_Maker_Documents tm_doc;
	Transactions_Maker_Summary tm_sum;
	Transactions_Maker_BasicDetails tm_BasicDetails;
	Transactions_Checker tc;
	Transactions_Verifier tv;
	Reports_ExecutionReport execReport;

	public TS103() {

		this.dm = new DashBoard_Module();
		this.tm_BasicDetails = new Transactions_Maker_BasicDetails();
		this.tm_sub = new Transactions_Maker_Sub_Instruction();
		this.tm_doc = new Transactions_Maker_Documents();
		this.tm_sum = new Transactions_Maker_Summary();
		this.tc = new Transactions_Checker();
		this.tv = new Transactions_Verifier();
		this.execReport = new Reports_ExecutionReport();

	}

	@Then("Add {int} Sub Intrsuction with payment Instrument BT_UK,LT_UK,TT_UK with given {string}.")
	public void add_Sub_Intrsuction_with_payment_Instrument_BT_UK_LT_UK_TT_UK_with_given(Integer int1, String string) throws Exception {
		
		
	}

	@Then("Add First Sub Intrsuction with payment Instrument BT_UK with given {string}.")
	public void add_First_Sub_Intrsuction_with_payment_Instrument_BT_UK_with_given(String string) throws Exception {
		TSID = string;
		tm_BasicDetails.Transactions_Maker_BasicDetails(string, TS06.dealId, DealPage.AccountNo1);
		tm_sub.Transaction_Maker_Sub_Instruction(string, this);
	}

	@Then("Add Second Sub Intrsuction with payment Instrument LT_UK with given {string}.")
	public void add_Second_Sub_Intrsuction_with_payment_Instrument_LT_UK_with_given(String string) throws Exception {
		TSID = string;
		tm_sub.Transaction_Maker_Sub_Instruction(string, this);
	}

	@Then("Add Third Sub Intrsuction with payment Instrument TT_UK with given {string}.")
	public void add_Third_Sub_Intrsuction_with_payment_Instrument_TT_UK_with_given(String string) throws Exception {
		TSID = string;
		tm_sub.Transaction_Maker_Sub_Instruction(string, this);
		tm_doc.Transactions_Maker_Documents(string);
		TS06.TnxId = tm_sum.Transaction_Maker_Summary();
	}
	
	@Then("Check the Transaction staus of all {int} Transactions in execution report with given {string}")
	public void check_the_Transaction_staus_of_all_Transactions_in_execution_report_with_given(Integer int1, String string) throws Exception{
		execReport.check_All_3_Transaction_StatusIsScheduled(string,TS06.dealId);
	}
	
	
	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

		

		if (callbackid.equalsIgnoreCase("PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;
			if (paymentInstrument.equalsIgnoreCase("BT")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();

				instrumentHandler.handleBTPaymentInstrument(TSID, DealPage.sourceAccountNo, DealPage.toaccountNo);
			}

			if (paymentInstrument.equalsIgnoreCase("BT_UAE")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();

				instrumentHandler.handleBT_UAEPaymentInstrument(TSID, DealPage.sourceAccountNo, DealPage.toaccountNo);
			}

			if (paymentInstrument.equalsIgnoreCase("LT_IN")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();
				instrumentHandler.handleLT_INPaymentInstrumentForAdhocTransaction(TSID, DealPage.sourceAccountNo,
						DealPage.toaccountNo);
			}
			if (paymentInstrument.equalsIgnoreCase("BT_UK")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();

				instrumentHandler.handle_BT_UK_PaymentInstrument(TSID);
			}
			if (paymentInstrument.equalsIgnoreCase("LT_UK")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();

				instrumentHandler.handle_LT_UK_PaymentInstrument(TSID);
			}
			if (paymentInstrument.equalsIgnoreCase("TT_UK")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();

				instrumentHandler.handle_TT_UK_PaymentInstrument(TSID);
			}
		}

	}
}
