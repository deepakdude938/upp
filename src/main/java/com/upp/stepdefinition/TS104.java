package com.upp.stepdefinition;

import java.io.IOException;

import org.openqa.selenium.ElementClickInterceptedException;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Checker;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Maker_Accounts;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Maker_Summary;
import com.upp.pagemodules.PartyDetails.Party_Accounts;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.Then;

public class TS104 extends BaseClass{
	public Party_Maker_Accounts pm;
	public static Object_NewDeal od;
	public Party_Maker_Summary pms;
	public Party_Checker pc;
	public Party_Accounts pa;
	public TS104() {
		pm = new Party_Maker_Accounts();
		od = new Object_NewDeal();
		pms=new Party_Maker_Summary();
		pc=new Party_Checker();
		pa = new Party_Accounts();
	}
	
	@Then("Click Document Tab")
	public void click_Document_Tab() throws InterruptedException {
		try {
			od.parties_DocumentsTab.click();
		}
		catch(ElementClickInterceptedException  h) {
			Thread.sleep(3000);
			handleElementClickException(od.parties_DocumentsTab);
		}
		
	}
	
	@Then("Edit party account with given {string} from Party Maker tab")
	public void edit_party_account_with_given_from_Party_Maker_tab(String TSID) throws IOException, Exception {
	  pm.editPartyAccount(TSID);
	 
	}
	
	@Then("Submit Party with given {string}")
	public void submit_Party_with_given(String string) throws Exception {
	  pms.PartyMaker_Summary(string);
	}

	@Then("Approve the Party with given {string}")
	public void approve_the_Party_with_given(String string) throws Exception {
		pc.PartyChecker(string);
	}

	@Then("Verify Account is updated")
	public void verify_Account_is_updated() throws Exception {
	    pa.verifyPartyAccountGotUpdated();
	}
	
	@Then("Offboard Account from Party Maker {string}")
	public void offboard_Account_from_Party_Maker(String TSID) throws Exception {
	   pa.OffBoardTheAccount(TSID);
	}

	@Then("Verify Account is offboarded in Party")
	public void verify_Account_is_offboarded_in_Party() {
	    pa.verifyAccountIsOffBoarded();
	}

}
