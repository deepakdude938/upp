package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.upp.Api.utils.LogOutApi;
import com.upp.InitiationRulesApi.Rule_Non_OBO;
import com.upp.InitiationRulesApi.Rule_With_Partial_PaymentInfoDetails;
import com.upp.InitiationRulesApi.Rule_Without_PaymentInfoDetails;
import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Holiday;
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
import com.upp.pagemodules.payment.Balance_Reporting;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.*;

public class TS126 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	Reports_ExecutionReport report;
	Holiday holiday;
	

	public TS126() {

		this.dm = new DashBoard_Module();
		
		report=new Reports_ExecutionReport();
        holiday=new Holiday();
	}

	
	@Then("Create Holiday for GB Account {string}")
	public void create_Holiday_for_GB_Account(String string) throws Exception{
	    holiday.createNewHoliday_For_GB_Account(string);
	}
     
	@Then("Approve Holiday with given {string}")
	public void approve_Holiday_with_given(String string) throws Exception{
	   holiday.approveHoliday_with_TSID(string);
	}
	@Then("Verify in Report that both Transactions are settled with given {string}")
	public void verify_in_Report_that_both_Transactions_are_settled(String string) throws Exception {
	    report.checkBothTransactionStatusIsSettled(string, dealId);
	}
	
	@Then("Delete the Tomorrow Holiday with given {string}")
	public void delete_the_Tomorrow_Holiday_with_given(String string) throws InvalidFormatException, IOException, Exception {
	  holiday.delete_Tomorrow_Holiday(string);
	}

	@Then("Approve Holiday for GB Account with given {string}")
	public void approve_Holiday_for_GB_Account_with_given(String string) throws Exception {
	 holiday.approveHoliday_For_GB_Account(string);
	}
	
}
