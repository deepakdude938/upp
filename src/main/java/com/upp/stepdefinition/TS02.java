package com.upp.stepdefinition;

import com.upp.base.BaseClass;
import com.upp.pagemodules.DashBoard_Module;

import io.cucumber.java.en.Then;

public class TS02 extends BaseClass{
	public DashBoard_Module dm;
	
	public TS02() {
		this.dm = new DashBoard_Module();
	}

	

	@Then("Submit the deal")
	public void submit_the_deal() throws Exception {
		 dealId=dm.submitDeal();
	}
	
	@Then("Send Introductory Mail")
	public void send_Introductory_Mail() throws Exception {
		 dealId=dm.send_Introductory_Mail();
	}
	
	@Then("Verify Email id In ODP")
	public void verify_Email_id_In_ODP() {
	   
	}
}
