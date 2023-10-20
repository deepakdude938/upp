package com.upp.stepdefinition;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.upp.pagemodules.Parties_Maker_Checker.Party_Maker_Accounts;

import io.cucumber.java.en.Then;

public class TS114 {

	public Party_Maker_Accounts pm;
	
	public TS114() {
		pm = new Party_Maker_Accounts();
	}
	
	@Then("Edit party CreditorLookUpKeys with given {string} from Party Maker tab")
	public void edit_party_CreditorLookUpKeys_with_given_from_Party_Maker_tab(String TSID) throws Exception, IOException, InterruptedException {
	  pm.editPartyAccountCreditorLookUpKeys(TSID);
	}
}
