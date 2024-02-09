package com.upp.stepdefinition;

import com.upp.Api.utils.CronJobApi;
import com.upp.pagemodules.LiveDeals;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.payment.Payment_BasicDetails;

import io.cucumber.java.en.Then;

public class TS150 {
	public Payment_BasicDetails ins;
	public CronJobApi cron;
	Reports_ExecutionReport report;
	public LoginAPI_UPP loginApi;
	
	public TS150() {
		ins=new Payment_BasicDetails();
		cron=new CronJobApi();
		report=new Reports_ExecutionReport();
	}
		@Then("Create Payments scheduled Instructions with given {string}")
		public void create_Payments_scheduled_Instructions_with_given(String tsid) throws Exception {
			ins.createPayments_BasicDetails(tsid,DealPage.sourceAccountNo,DealPage.toaccountNo);
	
	}
		
		@Then("Validate cron")
		public void validate_cron() throws Exception {
			loginApi.loginToUpp();
		   cron.hitCronJobApi();
		}
		
		@Then("Validate all record status and count the record {string}")
		public void validate_all_record_status_and_count_the_record(String TSID) throws Exception {
		   report.validateRecordStatusAsScheduled(TSID);
		}

		@Then("Validate extra record not created {string}")
		public void validate_extra_record_not_created(String string) throws Exception {
		   report.validateExtraRecordNotCreated(string);
		}

}
