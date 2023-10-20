package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.AttachDetach_Account_Api;
import com.upp.Api.utils.TransactionApi;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;

import io.cucumber.java.en.Then;

public class TS111 extends BaseClass{
		public LoginAPI_UPP login_UPP;
		public DealBasicDetailCreators dc;
		public TransactionApi ta;
		public Reports_ExecutionReport re;
//		public String endToEndId;
		
	public TS111(){
		login_UPP = new LoginAPI_UPP();
		dc=new DealBasicDetailCreators();
		ta=new TransactionApi();
		re=new Reports_ExecutionReport();
	}
	
	@Then("Call the Create Transaction Api for TS111 {string}")
	public void call_the_Create_Transaction_Api_for_TS111(String string) throws Exception {
		login_UPP.loginToUpp();
		endToEndId=	 ta.createTransaction(string);
		System.out.println(endToEndId);
	}

	
	@Then("Call the update transaction status as hold for {string}")
	public void call_the_update_transaction_status_as_hold_for(String string) throws Exception {
		//login_UPP.loginToUpp();
		 ta.updateTransactionStatusAsHold(string,endToEndId);
	}

	@Then("Verify transaction hold status in ecomm report {string}")
	public void verify_transaction_hold_status_in_ecomm_report(String string) throws Exception {
		 re.verifyHoldStatusInExecutionReport(string);
	}
}
