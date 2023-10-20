package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.pagemodules.PartyDetails.Party_Accounts;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.ExcelReader;

import io.cucumber.java.en.Then;

public class TS107 {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public Party_Accounts pa;
	
	public TS107() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		pa = new Party_Accounts();
	}
	
	@Then("Attempt to offboard the account {string}")
	public void attempt_to_offboard_the_account(String TSID) throws IOException, Exception {
	   pa.attemptToOffboardAccount(TSID);
	}
	
	@Then("Hit Back button")
	public void hit_Back_button() {
		od.parties_backButtton.click();
	}
}
