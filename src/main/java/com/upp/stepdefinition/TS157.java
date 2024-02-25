package com.upp.stepdefinition;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.Transactions.Transactions_Maker_BasicDetails;
import com.upp.pagemodules.Transactions.Transactions_Maker_Documents;
import com.upp.pagemodules.Transactions.Transactions_Maker_Sub_Instruction;
import com.upp.pagemodules.Transactions.Transactions_Maker_Summary;

import io.cucumber.java.en.Then;

public class TS157 extends BaseClass{
	
	public DealBasicDetailCreators ds ;
	Transactions_Maker_Sub_Instruction tm_sub;
	Transactions_Maker_Documents tm_doc;
	Transactions_Maker_Summary tm_sum;
	Transactions_Maker_BasicDetails tm_BasicDetails;
	Reports_ExecutionReport re;
	
			public TS157() {
				ds=new DealBasicDetailCreators();
				tm_BasicDetails = new Transactions_Maker_BasicDetails();
				tm_sub = new Transactions_Maker_Sub_Instruction();
				tm_doc = new Transactions_Maker_Documents();
				tm_sum = new Transactions_Maker_Summary();
				re=new Reports_ExecutionReport();
			}
	
	@Then("Create a Transaction from Live Deals with given {string}")
	public void create_a_Transaction_from_Live_Deals_with_given(String TSID) throws Exception {
		tsid=TSID;
	   ds.createTransactionFromLiveDeal(TSID);
		tm_BasicDetails.Transactions_Maker_BasicDetails(TSID, dealId, DealPage.sourceAccountNo);
		tm_sub.Transaction_Maker_Sub_Instruction(TSID, new TS06());
		tm_doc.Transactions_Maker_Documents(TSID);
		TS06.TnxId = tm_sum.Transaction_Maker_Summary();
	   }

	@Then("Create Two Transaction from Live Deals with given {string}")
	public void create_Two_Transaction_from_Live_Deals_with_given(String TSID) throws Exception {
			tsid=TSID;
		   ds.createTransactionFromLiveDeal(TSID);
			tm_BasicDetails.Transactions_Maker_BasicDetails(TSID, dealId, DealPage.sourceAccountNo);
			tm_sub.Transaction_Maker_Sub_Instruction(TSID, new TS06());
			tm_sub.Transaction_Maker_Sub_Instruction(TSID+"_1", new TS06());
			tm_doc.Transactions_Maker_Documents(TSID);
			TS06.TnxId = tm_sum.Transaction_Maker_Summary();
	}
	
	@Then("Verify Tnx Status as Rejected for all Two Transactions with given {string}")
	public void verify_Tnx_Status_as_Rejected_for_all_Two_Transactions_with_given(String tsid) throws Exception {
	  re.check_Both_Tnx_Rejected(tsid);
	}
	
	@Then("Check Both Transactions Status is Scheduled or Settled {string}")
	public void check_Both_Transactions_Status_is_Scheduled_or_Settled(String TSID) throws Exception {
	 re.checkBothTransactionStatusIsScheduledOrSettled(TSID, dealId);
	}
}
