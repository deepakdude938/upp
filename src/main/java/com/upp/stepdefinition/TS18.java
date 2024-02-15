package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Deal.PriorityDependency;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.*;

public class TS18 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	public String dealid;
	Object_NewDeal od;
	PriorityDependency pd;
	Reports_ExecutionReport report;

	public TS18() {

		this.dm = new DashBoard_Module();
		od = new Object_NewDeal();
		pd=new PriorityDependency();
		report = new Reports_ExecutionReport();
	}

	@Then("Click On Accounts Tab")
	public void click_On_Accounts_Tab() throws Exception {

		applyExplicitWaitsUntilElementClickable(od.accountIcon, Duration.ofSeconds(15));
		od.accountIcon.click();
	}

	@Then("Add Priority dependency with Same Day Dependeny")
	public void add_Priority_dependency_with_Same_Day_Dependeny() throws Exception {
		pd.addSameDayDependency();
	}
	
	@And("check Both Transactions Status is Scheduled {string}")
	public void check_Both_Transactions_Status_is_Scheduled(String TSID) throws Exception {
	 report.checkBothTransactionStatusIsScheduled(TSID,dealId);
	}
	@Then("Check the Execution Report that both Tnx are rejected and verify the Error Message with given {string}")
	public void check_the_Execution_Report_that_both_Tnx_are_rejected_and_verify_the_Error_Message_with_given(String string) throws Exception {
	  report.Check_Both_Tnx_Rejected_Verify_Error_Message(string,dealId);
	}


	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

		if (callbackid.equalsIgnoreCase("PRODUCT_NAME")) {
			String productName = (String) data;
			if (productName.equalsIgnoreCase("flexible funding")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleFlexibleFundding();
			}
			if (productName.equalsIgnoreCase("1.0")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleoneProduct();
			}

		}

		if (callbackid.equalsIgnoreCase("PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;
			if (paymentInstrument.equalsIgnoreCase("BT")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();

				instrumentHandler.handleBTPaymentInstrument(TSID, DealPage.sourceAccountNo, DealPage.toaccountNo);
			}

		}

	}
}
