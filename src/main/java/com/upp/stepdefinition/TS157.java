package com.upp.stepdefinition;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
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
	
			public TS157() {
				ds=new DealBasicDetailCreators();
				tm_BasicDetails = new Transactions_Maker_BasicDetails();
				tm_sub = new Transactions_Maker_Sub_Instruction();
				tm_doc = new Transactions_Maker_Documents();
				tm_sum = new Transactions_Maker_Summary();
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
			tm_sub.Transaction_Maker_Sub_Instruction("TS158_1", new TS06());
			tm_doc.Transactions_Maker_Documents(TSID);
			TS06.TnxId = tm_sum.Transaction_Maker_Summary();
	}

}
