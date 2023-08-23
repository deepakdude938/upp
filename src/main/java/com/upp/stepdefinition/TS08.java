package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.LogOutApi;
import com.upp.Api.utils.PartyApi;
import com.upp.Api.utils.TransactionApi;
import com.upp.base.BaseClass;
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
import com.upp.pagemodules.Parties_Maker_Checker.Party_Edit_LiveDeal;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Verify_PartyApiAdded;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS08 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	public String tsid;
	public static String sourceAccountNo = "1";
	public static String toaccountNo = "2";
	public static String dealId = "";
	LoginAPI_UPP loginApi;
	TransactionApi transactionApi;
	LogOutApi logoutApi;
	PartyApi partyApi;
	Party_Edit_LiveDeal editDeal;
	Party_Verify_PartyApiAdded partApiAdded;
	Reports_ExecutionReport report;
	public static String endToEndId = "";

	public TS08(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
		editDeal = new Party_Edit_LiveDeal();
		partApiAdded = new Party_Verify_PartyApiAdded();
		report = new Reports_ExecutionReport();

	}

	@Then("Add the Party using  Api call with given {string}")
	public void add_the_Party_using_Api_call_with_given(String string) throws Exception {
		loginApi.loginToUpp();
		System.out.println("deal id =" + TS07.dealId);
		partyApi.createPartyUsingExcel(TS07.dealId, string);
		// System.out.println(DealPage.dealId);

	}

	@Then("Add the transaction using  Api call with given {string} and {string} and {string}")
	public void add_the_transaction_using_Api_call_with_given_and_and(String string, String participant1,
			String participant2) throws Exception {
//		loginApi.loginToUpp();
		endToEndId = transactionApi.createTransaction(TS07.dealId, string, participant1, participant2);
	}

	@Then("Edit the live deal")
	public void edit_the_live_deal() throws Exception {
		editDeal.editLiveDeal(TS07.dealId);
	}

	@Then("Verify the Transaction status in eComm Executions Report")
	public void verify_the_Transaction_status_in_eComm_Executions_Report() throws Exception {
		report.eCommExecutionsReportToCheckTransactionStatus(endToEndId, TS07.dealId);
		logoutApi.logOut();
	}

	@Then("Update the Pary Api using given {string}")
	public void update_the_Pary_Api_using_given(String string) throws Exception {
		loginApi.loginToUpp();
		System.out.println("deal id =" + TS07.dealId);
		partyApi.updateParty(TS07.dealId, string);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {

		new CommonProductHandler().handleProduct(callbackid, data);
		new CommonResponsibilityHandler().handleResponsibility(callbackid, data);

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
