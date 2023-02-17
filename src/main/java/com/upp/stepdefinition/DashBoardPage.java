package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.utils.SwitchWindow;

import io.cucumber.java.en.*;

public class DashBoardPage extends BaseClass {
	DashBoard_Module dm;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
	public static String dealId = "";

	public DashBoardPage(DashBoard_Module dm) {

		this.dm = new DashBoard_Module();
	}

	@Given("Create new deal with basic details with given {string}.")
	public void create_new_deal_POC_with_basic_details_with_given(String TSID) throws Exception {
		dm.createNewDeal(TSID);
	}

	@Then("Create new Account with given {string}")
	public void create_new_Account_with_given(String TSID) throws Exception {
		dm.createNewAccount(TSID);
	}

	@Then("Create Parties in the Parties Tab with given {string}")
	public void create_Parties_in_the_Parties_Tab_with_given(String TSID) throws IOException, Exception {
		// dm.createParties(TSID);
		System.out.println("Test");
	}

	@Given("User is on LoginPage")
	public void user_is_on_LoginPage() {
		driver.get(prop.getProperty("QAUrl"));
	}

	@Then("Login to the application")
	public void login_to_the_application() {
		String username;
		String password;
		username = prop.getProperty("username");
		password = prop.getProperty("password");
		dm.loginToUPP(username, password);
	}

	@Then("Create two Accounts with given {string}")
	public void create_two_Accounts_with_given(String string) throws Exception {
		sourceAccountNo = dm.createNewAccount(string);
		toaccountNo = dm.createNewAccount(string);

	}

	@Then("create Linked Instruction Payment with given  {string}.")
	public void create_Linked_Instruction_Payment_with_given(String TSID) {
		try {

			dealId = dm.createNewLinkedAccount(TSID, sourceAccountNo, toaccountNo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("approve the deal from the deal checker")
	public void approve_the_deal_from_the_deal_checker() throws Exception {
		dm.approveDealFromDealChecker(dealId);
	}

	@Then("Logout from Application")
	public void logout_from_Application() throws Exception {
		dm.logout();
	}

	@Then("Login to transaction maker")
	public void login_to_transaction_maker() {
		String txnusername;
		String txnpassword;
		txnusername = prop.getProperty("txnusername");
		txnpassword = prop.getProperty("txnpassword");
		dm.loginToUPP(txnusername, txnpassword);
	}

	@Then("Submit the deal to transaction checker")
	public void submit_the_deal_to_transaction_checker() {
		try {
			dm.txnMaker_SubmitDeal(dealId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("Login to transaction checker")
	public void login_to_transaction_checker() {
		String txn_checkerUsername;
		String txn_checkerPassword;
		txn_checkerUsername = prop.getProperty("txn_checkerusername");
		txn_checkerPassword = prop.getProperty("txn_checkerpassword");
		dm.loginToUPP(txn_checkerUsername, txn_checkerPassword);
	}

	@Then("Submit the deal to transaction verifier")
	public void submit_the_deal_to_transaction_verifier() {
		try {
			dm.txnChecker_SubmitDeal(dealId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
