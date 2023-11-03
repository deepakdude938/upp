package com.upp.stepdefinition;

import com.upp.pagemodules.configuration.ManageConfigs;

import io.cucumber.java.en.Then;

public class TS25 {
	public ManageConfigs config;
	
	public TS25() {
	
		config=new ManageConfigs();
	}

	@Then("Create product from Configuration for {string}")
	public void create_product_from_Configuration_for(String TSID) throws Exception {
		config.createProduct(TSID);
	}
	
	@Then("Approve product")
	public void approve_product() throws Exception {
		config.approveProduct();
	}
	
	
}
