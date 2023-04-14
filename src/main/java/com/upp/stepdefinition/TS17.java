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
import com.upp.pagemodules.Parties.Party_Edit_LiveDeal;
import com.upp.pagemodules.Parties.Party_Verify_PartyApiAdded;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.document.AddDealWithDocument;
import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS17 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	public String tsid;
	AddDealWithDocument addDoc;

	public TS17(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
		this.addDoc = new AddDealWithDocument();
	}

	@Given("Create document using data given in  {string}")
	public void create_document_using_data_given_in(String string) {
		tsid = string;
		addDoc.addDealWithRequiredDocument();
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
