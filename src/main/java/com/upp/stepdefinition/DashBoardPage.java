package com.upp.stepdefinition;


import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.pagemodules.DashBoard_Module;
import io.cucumber.java.en.*;

public class DashBoardPage extends BaseClass{
	DashBoard_Module dm;
	
	public DashBoardPage(DashBoard_Module dm) {
		
		this.dm =new DashBoard_Module ();
	}
	
	@Given("Create new deal with basic details with given {string}.")
	public void create_new_deal_POC_with_basic_details_with_given(String TSID) throws  Exception {
		dm.createNewDeal(TSID);
	}
	
	@Then("Create new Account with given {string}")
	public void create_new_Account_with_given(String TSID) throws Exception {
	   dm.createNewAccount(TSID);
	}

	@Then("Create Parties in the Parties Tab with given {string}")
	public void create_Parties_in_the_Parties_Tab_with_given(String TSID) throws IOException, Exception {
	   dm.createParties(TSID);
	}
		
	@Given("User is on LoginPage")
	public void user_is_on_LoginPage() {
		driver.get(prop.getProperty("QAUrl"));
	}

	@Then("Login to the application")
	public void login_to_the_application() {
		dm.loginToUPP();
	   }
}
