package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.AttachDetach_Account_Api;
import com.upp.Api.utils.TransactionApi;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;

import io.cucumber.java.en.Then;

public class TS88 {
		public LoginAPI_UPP login_UPP;
		public DealBasicDetailCreators dc;
		public TransactionApi ta;
		public Reports_ExecutionReport re;
		public String endToEndId;
		
	public TS88(){
		login_UPP = new LoginAPI_UPP();
		dc=new DealBasicDetailCreators();
		ta=new TransactionApi();
		re=new Reports_ExecutionReport();
	}
	
	@Then("Call the Create Transaction Api for {string}")
	public void call_the_Create_Transaction_Api_for(String TSID) throws Exception {
		login_UPP.loginToUpp();
		endToEndId=	 ta.createTransaction(TSID);
		System.out.println(endToEndId);
	}
	
	@Then("Call the Rollback Transaction Api {string}")
	public void call_the_Rollback_Transaction_Api(String TSID) {
		   ta.deleteTransaction(TSID);
	}

	@Then("Validate in execution report for scheduled record using endToEndId {string}")
	public void validate_in_execution_report_for_scheduled_record_using_endToEndId(String string) throws Exception {
	   re.eCommExecutionsReport_Status(endToEndId);
	}
	
	@Then("Validate in execution report record should not be there")
	public void validate_in_execution_report_record_should_not_be_there() throws Exception {
		re.checkRecordIsNotPresentINEcommExecution(endToEndId);
		
	}
	
}
