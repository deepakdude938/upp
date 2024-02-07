package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.base.BaseClass;
import com.upp.pagemodules.LiveDeals;
import com.upp.pagemodules.Account.OffboardAccount;

import io.cucumber.java.en.*;

public class TS148 extends BaseClass {
	OffboardAccount oa;
	LiveDeals ld;
	public static String TSID = "";
	public static String TnxId = "";
	public String dealid;

	public TS148() {

		this.oa = new OffboardAccount();
		this.ld = new LiveDeals();
	}

	@Then("Offboard one of the accounts with given {string}")
	public void offboard_Account(String string) throws Exception {
		oa.offboard_Account(string);

	}

}
