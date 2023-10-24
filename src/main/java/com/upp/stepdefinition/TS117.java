package com.upp.stepdefinition;

import java.io.IOException;
import com.upp.base.BaseClass;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Maker_Accounts;
import callbackInterfaces.ICallback;
import io.cucumber.java.en.Then;

public class TS117 extends BaseClass  implements ICallback{
	Party_Maker_Accounts pma;
	String TSID;

	public TS117() {
		pma=new Party_Maker_Accounts();

	}
	
	@Then("Try to add account with same Creditor look up keys {string}")
	public void try_to_add_account_with_same_Creditor_look_up_keys(String TSID) throws Exception {
		this.TSID=TSID;
	   pma.addPartyMaker_Accounts(TSID,this);
	}
	
	@Then("Click on Party-Maker Tab {string}")
	public void click_on_Party_Maker_Tab(String TSID) throws IOException, Exception {
	 pma.clickOnPartyMakerTab(TSID);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		
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
