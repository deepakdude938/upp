package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import com.upp.pagemodules.LoginToApplication;
import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.Transactions.Transactions_Maker_Verifier_Checker;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Parties.Parties;

import io.cucumber.java.en.*;

public class TS09 extends BaseClass  implements ICallback{
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
//	public static String dealId = "";
	LoginToApplication userLogin;
	public static String TSID = "";
	Transactions_Maker_Verifier_Checker tm;
	public static String TnxId="";
	Parties ps;
public TS09(Parties ps) {
		
		this.ps=new Parties();
		this.dm=new DashBoard_Module();
	}
	


@Then("Create party from party maker with given {string}")
public void create_party_from_party_maker(String string) throws Exception {
	 TSID=string;
ps.createPartyFromPartyMaker(string,this);
}

@Then("Approve party from party checker with given {string}")
public void approve_party_from_party_checker(String string) throws Exception {
   
 ps.approvePartyFromPartyChecker(string);
 
 System.out.println("the deal id in step def"+TS06.dealId);
}
	
@Then("Edit the Live deal and add Existing Party with given {string}")
public void edit_the_Live_deal_and_add_Existing_Party_with_given(String string) throws Exception {
	ps.add_Existing_Party_with_given_DealId(string,TS06.dealId);
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
