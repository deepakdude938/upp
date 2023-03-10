package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.LogOutApi;
import com.upp.Api.utils.LoginApi;
import com.upp.Api.utils.PartyApi;
import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import com.upp.pagemodules.LoginToApplication;
import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Parties.Party_Edit_LiveDeal;
import com.upp.pagemodules.Parties.Party_Verify_PartyApiAdded;

import io.cucumber.java.en.*;

public class TS11 extends BaseClass  implements ICallback{
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
//	public static String dealId = "";
	LoginToApplication userLogin;
	public static String TSID = "";
	public static String TnxId="";
	LoginApi loginApi;
	PartyApi partyApi;
	LogOutApi logoutApi;
	Party_Edit_LiveDeal editDeal;
	Party_Verify_PartyApiAdded partApiAdded;
public TS11() {
		
		this.dm=new DashBoard_Module();
		editDeal=new Party_Edit_LiveDeal();
		partApiAdded=new Party_Verify_PartyApiAdded();
	}
	


@Then("Add the Party through Api call")
public void add_the_Party_through_Api_call() throws Exception {
	loginApi.loginToUpp();
	partyApi.createParty(TS06.dealId);
	logoutApi.logOut();
}

@Then("Open and Edit the live deal")
public void open_and_Edit_the_live_deal() throws Exception {
  editDeal.editLiveDeal(TS06.dealId);
}

@Then("Verify that Party Api got addded in the live deal")
public void verify_that_Party_Api_got_addded_in_the_live_deal() throws Exception {
	partApiAdded.Verify_PartyApiAdded(TS06.dealId);
}

	@Override
	public  void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub
		
		if(callbackid.equalsIgnoreCase("PRODUCT_NAME")) {
			String productName = (String) data;
			if(productName.equalsIgnoreCase("flexible funding")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleFlexibleFundding();
			}
			if(productName.equalsIgnoreCase("1.0")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleoneProduct();
			}

		}
		
		if(callbackid.equalsIgnoreCase("PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;
			if(paymentInstrument.equalsIgnoreCase("BT")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler=new TransactionMaker_PaymentInstrumentHandler();
				
			instrumentHandler.handleBTPaymentInstrument(TSID,sourceAccountNo,toaccountNo);
			}
			
		}
		
		if(callbackid.equalsIgnoreCase("PARTIES_MAKER_PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;
			if(paymentInstrument.equalsIgnoreCase("BT")) {
				PartyMaker_PaymentInstrumentHandler BThandler=new PartyMaker_PaymentInstrumentHandler();
				BThandler.handlePartyMakerBT_PaymentInstrument(TSID);
				
			}
			
		}
		
		
		
	}
}
