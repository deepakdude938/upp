package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.LogOutApi;
import com.upp.Api.utils.PartyApi;
import com.upp.Api.utils.TransactionApi;
import com.upp.base.BaseClass;
import com.upp.handlers.CommonDocumentTypeHandler;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.CommonResponsibilityHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.handlers.DealPartyAccount_PaymentInstrumentHandler;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.handlers.EcommerceHandler;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.BasicDetails;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.Login.LoginToApplication;
import com.upp.pagemodules.Parties.Party_Edit_LiveDeal;
import com.upp.pagemodules.Parties.Party_Verify_PartyApiAdded;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.document.AddDealWithDocument;
import com.upp.pagemodules.document.DocumentChecker;
import com.upp.pagemodules.document.DocumentMaker;
import com.upp.pagemodules.document.RequiredDocumentSchedule;
import com.upp.pagemodules.payment.Payment_Alerts;
import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS22 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	public String tsid;
	Payment_Alerts alerts;
	Reports_ExecutionReport report;

	public TS22(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
		this.alerts = new Payment_Alerts();
		this.report = new Reports_ExecutionReport();

	}

	@Given("Add Alert for payment with given {string}")
	public void add_Alert_for_payment_with_given(String string) throws Exception {
		tsid = string;
		alerts.createPaymentsAlert(tsid);
	}

	@Given("Check the Transaction staus and instruction type in execution report with given {string}")
	public void check_the_Transaction_staus_and_instruction_type_in_execution_report_with_given(String string)
			throws Exception {
		System.out.println("deal id = " + TS06.dealId);
		report.checkStatusAndInstructionType(string, TS06.dealId);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {

		new CommonProductHandler().handleProduct(callbackid, data);
		new CommonResponsibilityHandler().handleResponsibility(callbackid, data);
		new CommonDocumentTypeHandler().handleDocumentType(callbackid, data, tsid);

		if (callbackid.equalsIgnoreCase("ecommerce")) {
			String responsibility = (String) data;
			if (responsibility.equalsIgnoreCase("Y")) {
				EcommerceHandler ecommerce = new EcommerceHandler();
				ecommerce.handleEcommerce(tsid);
			}
		}

		if (callbackid.equalsIgnoreCase("DEAL_PARTY_ACCONT_PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;

			if (paymentInstrument.equalsIgnoreCase("BT")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_BT_PaymentInstrument(tsid);

			}

			if (paymentInstrument.equalsIgnoreCase("LTTest")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_LTTest_PaymentInstrument(tsid);

			}

			if (paymentInstrument.equalsIgnoreCase("BT_IN")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handleBT_IN_PaymentInstrument(tsid);

			}

			if (paymentInstrument.equalsIgnoreCase("LT_IN")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_LT_IN_PaymentInstrument(tsid);

			}
		}
	}

}
