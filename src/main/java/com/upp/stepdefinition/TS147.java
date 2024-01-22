package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.base.BaseClass;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.LiveDeals;
import com.upp.pagemodules.DealLifeCycle.CloseLiveDeal;
import com.upp.pagemodules.DealLifeCycle.LifeCycleChecker;
import com.upp.pagemodules.DealLifeCycle.LifeCycleMaker;
import com.upp.pagemodules.DealLifeCycle.VerifyClosedStatusforDealId;


import io.cucumber.java.en.*;

public class TS147 extends BaseClass {
	LiveDeals ld;
	public static String TSID = "";
	public static String TnxId = "";
	public String dealid;

	public TS147() {

		this.ld = new LiveDeals();
	}

	@Then("Check in Live deals for the newly created deal with given {string}")
	public void check_Newly_Created_Deal_Under_Live_Deals(String string) throws Exception {
		ld.liveDealsCheck(string);

	}

}
