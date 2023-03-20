package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;


import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Parties.Party_AddExistingPartyForGivenDealid;
import com.upp.pagemodules.Parties.Party_Checker;
import com.upp.pagemodules.Parties.Party_Maker_Accounts;
import com.upp.pagemodules.Parties.Party_Maker_BasicDetails;
import com.upp.pagemodules.Parties.Party_Maker_Contacts;
import com.upp.pagemodules.Parties.Party_Maker_Documents;
import com.upp.pagemodules.Parties.Party_Maker_Summary;

import io.cucumber.java.en.*;

public class TS09 extends BaseClass  implements ICallback{
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
//	public static String dealId = "";
	public static String TSID = "";
	public static String TnxId="";
	Party_Maker_BasicDetails pm_Basic;
	Party_Maker_Contacts pm_Contacts;
	Party_Maker_Accounts pm_Accounts;
	Party_Maker_Documents pm_Documents;
	Party_Maker_Summary pm_Summary;
	Party_Checker partyChecker;
	Party_AddExistingPartyForGivenDealid addexistingParty;
public TS09() {
		
		this.dm=new DashBoard_Module();
		pm_Basic=new Party_Maker_BasicDetails();
		pm_Contacts=new Party_Maker_Contacts();
		pm_Accounts=new Party_Maker_Accounts();
		pm_Documents=new Party_Maker_Documents();
		pm_Summary=new Party_Maker_Summary();
		partyChecker=new Party_Checker();
		addexistingParty=new Party_AddExistingPartyForGivenDealid();
	}
	


@Then("Create party from party maker with given {string}")
public void create_party_from_party_maker(String string) throws Exception {
	 TSID=string;
	 pm_Basic.PartyMaker_BasicDetails(string);
	 pm_Contacts.PartyMaker_Contacts(string);
	 pm_Accounts.PartyMaker_Accounts(string,this);
	 pm_Documents.PartyMaker_Documents(string);
	 pm_Summary.PartyMaker_Summary(string);
	 
}

@Then("Approve party from party checker with given {string}")
public void approve_party_from_party_checker(String string) throws Exception {
   

	partyChecker.PartyChecker(string);
	
}
	
@Then("Edit the Live deal and add Existing Party with given {string}")
public void edit_the_Live_deal_and_add_Existing_Party_with_given(String string) throws Exception {
	
	addexistingParty.add_Existing_Party_with_given_DealId(string,TS06.dealId);
	
}

@Then("Add a Transaction using Payment with the updated deal with given {string}")
public void add_a_Transaction_using_Payment_with_the_updated_deal_with_given(String string) throws Exception {
	dm.createPayments(string,DealPage.sourceAccountNo,DealPage.toaccountNo);
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
			
			if(paymentInstrument.equalsIgnoreCase("BT_IN")) {
				PartyMaker_PaymentInstrumentHandler BThandler=new PartyMaker_PaymentInstrumentHandler();
				BThandler.handlePartyMakerBT_INPaymentInstrument(TSID);
				
			}
			
		}
		
		
		
	}
}
