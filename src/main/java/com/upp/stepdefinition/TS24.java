package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.hooks.Hook;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Deal.PriorityDependency;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.Users.UserChecker;
import com.upp.pagemodules.Users.UserMaker;
import com.upp.pagemodules.payment.Balance_Reporting;
import com.upp.pageobjects.Object_NewDeal;

import io.cucumber.java.en.*;

public class TS24 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	public String dealid;
	UserMaker user;
	UserChecker checker;
	String tsid;
	Hook h;

	public TS24() {

		this.dm = new DashBoard_Module();
		this.user = new UserMaker();
		this.checker = new UserChecker();

	}

	@Then("OnBoard the user with given role for {string}")
	public void onboard_the_user_with_given_role_for(String string) throws Exception {
		tsid = string;
		user.onBoardUser(tsid);

	}

	@Then("Approve the same user")
	public void approve_the_same_user() throws Exception {
		checker.approveUsers();
	}

	@Then("login with updated user using {string}")
	public void login_with_updated_user_using(String string) throws Exception {
		checker.login(string);
	}
	
	@Then("Enter URL")
	public void enter_URL() {
		driver.get(url);
	}

	@Then("Clear and close the browser")
	public void Clear_and_close_the_browser() throws Exception {
		Thread.sleep(3000);
		driver.manage().deleteAllCookies();
		Thread.sleep(6000);
		driver.close();
	}

	@Then("Verify user is able to login")
	public void verify_user_is_able_to_login() throws Exception {
		checker.verifyUser();
	}

	@Then("Check responsibility")
	public void check_responsibility() throws Exception {
		checker.verifyResponsibility();
	}
	
	@Then("Deactivate the user using {string}")
	public void deactivate_the_user(String string ) throws Exception {
	  user.inactivateUser(string);
	}

	@Then("Verify user is not  able to login")
	public void verify_user_is_not_able_to_login() {
	   checker.verifyUserNotAbleTologin();
	}

	
	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

		if (callbackid.equalsIgnoreCase("PRODUCT_NAME")) {
			String productName = (String) data;
			if (productName.equalsIgnoreCase("flexible funding")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleFlexibleFundding();
			}
			if (productName.equalsIgnoreCase("1.0")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleoneProduct();
			}

		}

		if (callbackid.equalsIgnoreCase("PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;
			if (paymentInstrument.equalsIgnoreCase("BT")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();

				instrumentHandler.handleBTPaymentInstrument(TSID, DealPage.sourceAccountNo, DealPage.toaccountNo);
			}

		}

	}
}
