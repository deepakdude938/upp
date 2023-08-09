package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.CommonResponsibilityHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.handlers.DealPartyAccount_PaymentInstrumentHandler;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.handlers.Deal_Level_Rule_Handler;
import com.upp.handlers.EcommerceHandler;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.BasicDetails;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Login.LoginToApplication;
import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.utils.ExcelReader;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class DealPage extends BaseClass implements ICallback {
	DashBoard_Module dm;
	public String tsid;
	public static String sourceAccountNo = "1";
	public static String toaccountNo = "2";
	public static String dealId = "";
	public static String AccountNo1 = "1";
	public static String AccountNo2 = "1";
	public static ExcelReader externalData;

	public DealPage(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
		externalData = new ExcelReader();
	}

	@Given("Open browser and enter url")
	public void open_browser_and_enter_url() {
//		driver.get(prop.getProperty("QAUrl"));
		driver.get(url);
	}

	@Then("Login to the application as {string}")
	public void login_to_the_application_as(String users) throws Exception {
		// new LoginToApplication().login(users, prop);
		LoginToApplication login = new LoginToApplication();
		login.login(users, prop);
	}

	@Then("Create new deal with basic details with given {string}.")
	public void create_new_deal_with_basic_details_with_given(String TSID) throws Exception {
		tsid = TSID;
		DealBasicDetailCreators deal = new DealBasicDetailCreators();
		deal.createDealBasicDetails(TSID, this);
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

	@And("Create Account_One From excel sheet with given {string}.")
	public void create_Account_1From_excel_sheet_with_given(String string) throws Exception {
		DealAccountCreator accountCreator = new DealAccountCreator();
		AccountNo1 = accountCreator.createNewAccount_ODP_From_ExcelSheet(string);
	}

	@And("Create Account_Two From excel sheet with given {string}.")
	public void create_Account_2From_excel_sheet_with_given(String string) throws Exception {
		DealAccountCreator accountCreator = new DealAccountCreator();
		AccountNo2 = accountCreator.createNewAccount_ODP_From_ExcelSheet(string);
	}

	@Then("Add Party basic_Details with given {string}.")
	public void add_Party_basic_Details_with_given(String TSID) throws Exception {

		tsid = TSID;
		DealPartiesCreator creator = new DealPartiesCreator();
		creator.createParty_With_BasicDetails(TSID, this);

	}

	@Then("Add Party Contacts with given {string}.")
	public void add_Party_Contacts_with_given(String TSID) throws Exception {
		tsid = TSID;
		DealPartiesCreator creator = new DealPartiesCreator();
		creator.createParty_With_Contacts(TSID, this);
	}

	@Then("Add Party Accounts with given {string}.")
	public void add_Party_Accounts_with_given(String TSID) throws Exception {
		tsid = TSID;
		DealPartiesCreator creator = new DealPartiesCreator();
		creator.createParty_With_Accounts(TSID, this);
	}

	@Then("Add Party Documents with given {string}.")
	public void add_Party_Documents_with_given(String TSID) throws Exception {
		tsid = TSID;
		DealPartiesCreator creator = new DealPartiesCreator();
		creator.createParty_With_Documents(TSID, this);
	}

	@Then("Create One Account with given {string}")
	public void create_One_Account_with_given(String string) throws Exception {

		DealAccountCreator accountCreator = new DealAccountCreator();
		sourceAccountNo = accountCreator.createNewAccount(string);
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

		if (callbackid.equalsIgnoreCase("DEAL_PARTY_ACCONT_PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;

			if (paymentInstrument.equalsIgnoreCase("BT")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_BT_PaymentInstrument(tsid);

			}

			if (paymentInstrument.equalsIgnoreCase("LTTest")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_LTTest_PaymentInstrument(tsid);

			}

			if (paymentInstrument.equalsIgnoreCase("BT_IN")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handleBT_IN_PaymentInstrument(tsid);

			}

			if (paymentInstrument.equalsIgnoreCase("LT_IN")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_LT_IN_PaymentInstrument(tsid);

			}
			if (paymentInstrument.equalsIgnoreCase("SC-PaymentProfile")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_SC_Payment_Profile_PaymentInstrument(tsid);

			}

		}
		if (callbackid.equalsIgnoreCase("DEAL_LEVEL_RULE")) {
			String PaymentRule = tsid + "_DEAL_LEVEL_RULE";
			System.out.println("The Deal Level rule :" + PaymentRule);
			if (PaymentRule.equalsIgnoreCase("TS58_DEAL_LEVEL_RULE")) {
				Deal_Level_Rule_Handler rule_handler = new Deal_Level_Rule_Handler();
				rule_handler.handle_Rule_IN_BT_DealLevel(tsid);
			}else if (PaymentRule.equalsIgnoreCase("TS60_DEAL_LEVEL_RULE")) {
				Deal_Level_Rule_Handler rule_handler = new Deal_Level_Rule_Handler();
				rule_handler.handle_Rule_IN_LT_DealLevel(tsid);
			}else if (PaymentRule.equalsIgnoreCase("TS68_DEAL_LEVEL_RULE")) {
				Deal_Level_Rule_Handler rule_handler = new Deal_Level_Rule_Handler();
				rule_handler.handle_Rule_IN_LT_DealLevel_PenddingStatus(tsid);
			}
			// String account_Payment_System = externalData.getFieldData(tsid, "Party", "Accounts-Payment System");

		}
	}

}
