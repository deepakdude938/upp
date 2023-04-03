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
	Party_Edit_LiveDeal editDeal;
	Party_Verify_PartyApiAdded partApiAdded;

	

	public TS08(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
		editDeal = new Party_Edit_LiveDeal();
		partApiAdded = new Party_Verify_PartyApiAdded();
		
	}

	@Then("Add the Party using  Api call with given {string}")
	public void add_the_Party_using_Api_call_with_given(String string) throws Exception {
		loginApi.loginToUpp();
		System.out.println(DealPage.dealId);
		transactionApi.createTransaction("REF1680236784142",string);
	}
	
	@Then("Open and Edit the deal")
	public void open_and_Edit_the_deal() throws Exception {
		editDeal.editLiveDeal(DealPage.dealId);
	}


//	@Then("Update the Pary using  Api With given {string}")
//	public void update_the_Pary_using_Api_With_given(String string) throws Exception {
//		partyApi.updateParty(DealPage.dealId,string);
//	    logoutApi.logOut();
//	}

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
