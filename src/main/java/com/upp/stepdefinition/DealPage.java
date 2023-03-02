package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.CommonResponsibilityHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.handlers.EcommerceHandler;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.LoginToApplication;
import com.upp.pagemodules.deal.DealAccountCreator;
import com.upp.pagemodules.deal.DealBasicDetailCreators;
import com.upp.pagemodules.deal.DealPartiesCreator;
import com.upp.utils.SwitchWindow;

import callbackinterfaces.ICallback;
import io.cucumber.java.en.*;

public class DealPage extends BaseClass implements ICallback {
	DashBoard_Module dm;
	public String tsid;
	public static String sourceAccountNo = "1";
	public static String toAccountNo = "2";
	public static String dealId = "";

	public DealPage(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
	}

	@Given("Open browser and enter url")
	public void open_browser_and_enter_url() {
		driver.get(prop.getProperty("QAUrl"));
	}

	@Then("Login to the application as {string}")
	public void login_to_the_application_as(String users) {
		new LoginToApplication().login(users, prop);
	}

	@Then("Create new deal with basic details with given {string}.")
	public void create_new_deal_with_basic_details_with_given(String TSID) throws Exception {
		tsid = TSID;
		System.out.println(tsid);
		DealBasicDetailCreators createDeal = new DealBasicDetailCreators();
		createDeal.createDealBasicDetails(TSID, this);
	}
	
	@Then("Create new Account with given {string}")
	public void create_new_Account_with_given(String TSID) throws Exception {
	   dm.createNewAccount(TSID);
	}
	
//	@Then("Create a Budget with given {string}")
//	public void create_a_Budget_with_given(String TSID) throws Exception, Exception {
//	   dm.createBudget(TSID,sourceAccountNo,toAccountNo);
//	}

	@Then("Create two Accounts with given {string}")
	public void create_two_Accounts_with_given(String string) throws Exception {
		DealAccountCreator accountCreator = new DealAccountCreator();
		sourceAccountNo = accountCreator.createNewAccount(string);
		toAccountNo = accountCreator.createNewAccount(string);
	}

	@Then("Create Parties in the Parties Tab with given {string}")
	public void create_Parties_in_the_Parties_Tab_with_given(String TSID) throws IOException, Exception {
		DealPartiesCreator crator = new DealPartiesCreator();
		crator.createParties(TSID, this);
	}
	
	@Then("Approve the deal from the deal checker")
	public void approve_the_deal_from_the_deal_checker() throws Exception {
	   dm.approveDealFromDealChecker(dealId);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {

		new CommonProductHandler().handleProduct(callbackid, data);
		new CommonResponsibilityHandler().handleResponsibility(callbackid, data);

		if (callbackid.equalsIgnoreCase("ecommerce")) {
			String responsibility = (String) data;
			if (responsibility.equalsIgnoreCase("Y")) {
				EcommerceHandler ecommerce = new EcommerceHandler();
				ecommerce.handleEcommerce(tsid);
			}
		}
	}

}
