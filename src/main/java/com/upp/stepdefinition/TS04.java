package com.upp.stepdefinition;

import java.io.IOException;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.checkerframework.common.value.qual.StaticallyExecutable;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.handlers.CommonLinkedInstructionInstrument;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.odp.utils.ODP_JSON_ASSERTIONS_PAYLOAD;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.Transactions.Transactions_Checker;
import com.upp.pagemodules.Transactions.Transactions_Maker_SearchTransactionAndSubmit;
import com.upp.pagemodules.Transactions.Transactions_Verifier;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS04 extends BaseClass implements ICallback {
	DashBoard_Module dm;
//	public String dealId;
	Transactions_Maker_SearchTransactionAndSubmit txnsearch;
	Transactions_Checker txnChecker;
	Transactions_Verifier txnVerifier;
	String tsid;
	public static String toAccount, sourceAccount;
	ODP_JSON_ASSERTIONS_PAYLOAD payload;
	Reports_ExecutionReport execReport;

	public TS04(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
		this.txnsearch = new Transactions_Maker_SearchTransactionAndSubmit();
		this.txnChecker = new Transactions_Checker();
		this.txnVerifier = new Transactions_Verifier();
		sourceAccount = new DealPage(dm).sourceAccountNo;
		toAccount = new DealPage(dm).toaccountNo;
		this.execReport = new Reports_ExecutionReport();
		payload = new ODP_JSON_ASSERTIONS_PAYLOAD();
	}

	@Then("create Linked Instruction Payment with given  {string}.")
	public void create_Linked_Instruction_Payment_with_given(String TSID) throws Exception {
		tsid = TSID;
		System.out.println("Changes = " + TSID);
		dealId = dm.createNewLinkedAccount(TSID, sourceAccount, toAccount, this);

	}

	@Then("approve the deal from the deal checker")
	public void approve_the_deal_from_the_deal_checker() throws Exception {
		dm.approveDealFromDealChecker_Old(dealId);
	}

	@Then("Submit the deal to transaction checker")
	public void submit_the_deal_to_transaction_checker() throws Exception {
		txnsearch.txnMaker_SubmitDeal(dealId);
	}

	@Then("Submit the deal to transaction verifier")
	public void submit_the_deal_to_transaction_verifier() throws Exception {
		txnChecker.txnChecker_SubmitDeal(dealId);
	}

	@Then("Transaction verifier approve deal")
	public void transaction_verifier_approve_deal() throws Exception {
		txnVerifier.txnVerifier_ApproveDeal(dealId);

	}
	
	@Then("Go to execution report and check the status it should be schedule for {string}")
	public void go_to_execution_report_and_check_the_status_it_should_be_schedule_for(String string) throws Exception {
		execReport.ExecutionReportForSchedule(string,dealId);
	}

	@Then("Post schedule time ,check the status. it should be awaiting for {string}")
	public void post_schedule_time_check_the_status_it_should_be_awaiting_for(String string) throws Exception {
		execReport.ExecutionReportAwaitingTransaction(string, dealId);
	}
	
	@Then("Create odp json payload file with DealDetails with given {string}")
	public void create_odp_json_payload_file_with_DealDetails_with_given(String string) throws Exception {
		System.out.println("dealid = "+string);
		payload.Odp_Json_With_DealDetails(string,dealId);
	}
	
	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub
		new CommonLinkedInstructionInstrument().handleInstruments(tsid, callbackid, data, toAccount);
	}

}
