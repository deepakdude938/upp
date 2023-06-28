package com.upp.stepdefinition;

import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Deal.DealEntitlements;

import io.cucumber.java.en.Then;

public class TS54 {
	DealEntitlements de;
	DashBoard_Module dm;
	public TS54() {

		this.dm = new DashBoard_Module();
		this.de = new DealEntitlements();

	}
	@Then("Add account-entitlements for deal with given {string}")
	public void add_account_entitlements_for_deal_with_given(String TSID) throws Exception {
	   de.addAccountEntitlements(TSID);
	}
	
}
