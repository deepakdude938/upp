package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.CommonResponsibilityHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.handlers.DealPartyAccount_PaymentInstrumentHandler;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.handlers.EcommerceHandler;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.BasicDetails;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Login.LoginToApplication;
import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class DealPage extends BaseClass implements ICallback {
	DashBoard_Module dm;
	public String tsid;
	public static String sourceAccountNo = "1";
	public static String toaccountNo = "2";
	public static String dealId = "";

	public DealPage(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
	}

	@Given("Open browser and enter url")
	public void open_browser_and_enter_url() {
		driver.get(prop.getProperty("QAUrl"));
	}

	@Then("Login to the application as {string}")
	public void login_to_the_application_as(String users) throws Exception {
		//new LoginToApplication().login(users, prop);
		LoginToApplication login=new LoginToApplication();
		login.login(users, prop);
	}

	@Then("Create new deal with basic details with given {string}.")
	public void create_new_deal_with_basic_details_with_given(String TSID) throws Exception {
		BasicDetails createDeal = new BasicDetails();
		createDeal.createDealBasicDetails(TSID, this);
	}

	@Then("Create two Accounts with given {string}")
	public void create_two_Accounts_with_given(String string) throws Exception {
		
		DealAccountCreator accountCreator = new DealAccountCreator();
		sourceAccountNo = accountCreator.createNewAccount(string);
		toaccountNo = accountCreator.createNewAccount(string);
	}

	@Then("Create Parties in the Parties Tab with given {string}")
	public void create_Parties_in_the_Parties_Tab_with_given(String TSID) throws IOException, Exception {
		tsid = TSID;
		DealPartiesCreator crator = new DealPartiesCreator();
		crator.createParties(TSID, this);
	}
	

	@Then("Logout from Application")
	public void logout_from_Application() throws Exception {
	    dm.logout();
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
		
		if(callbackid.equalsIgnoreCase("DEAL_PARTY_ACCONT_PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;
			
			if(paymentInstrument.equalsIgnoreCase("BT")) {
				DealPartyAccount_PaymentInstrumentHandler handler=new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_BT_PaymentInstrument(tsid);
				
			}
			
			if(paymentInstrument.equalsIgnoreCase("LTTest")) {
				DealPartyAccount_PaymentInstrumentHandler handler=new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_LTTest_PaymentInstrument(tsid);
				
			}
			
			if(paymentInstrument.equalsIgnoreCase("BT_IN")) {
				DealPartyAccount_PaymentInstrumentHandler handler=new DealPartyAccount_PaymentInstrumentHandler();
				handler.handleBT_IN_PaymentInstrument(tsid);
				
			}
			
			if(paymentInstrument.equalsIgnoreCase("LT_IN")) {
				DealPartyAccount_PaymentInstrumentHandler handler=new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_LT_IN_PaymentInstrument(tsid);
				
			}
		}
	}

}
