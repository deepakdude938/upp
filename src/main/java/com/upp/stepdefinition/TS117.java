package com.upp.stepdefinition;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import com.upp.base.BaseClass;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Maker_Accounts;
import com.upp.pageobjects.Object_Parties;
import callbackInterfaces.ICallback;
import io.cucumber.java.en.Then;

public class TS117 extends BaseClass  implements ICallback{
	Party_Maker_Accounts pma;
	String TSID;
	public static Object_Parties op;
	
	public TS117() {
		pma=new Party_Maker_Accounts();
		op=new Object_Parties();
	}
	
	@Then("Add account from Party Maker {string}")
	public void add_account_from_Party_Maker(String TSID) throws IOException, Exception {
		this.TSID=TSID;
		   pma.addPartyMaker_Accounts(TSID,this);
	}
	
	@Then("Click on Party-Maker Tab {string}")
	public void click_on_Party_Maker_Tab(String TSID) throws IOException, Exception {
	 pma.clickOnPartyMakerTab(TSID);
	}
	
	@Then("Verify Account should not be created")
	public void verify_Account_should_not_be_created() {
		
		boolean assert1=	isWebElementDisplayed(op.parties_ErrorMessage1);
		assertTrue(assert1);
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
