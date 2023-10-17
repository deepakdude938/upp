package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.LogOutApi;
import com.upp.Api.utils.PartyApi;
import com.upp.Api.utils.Payload;
import com.upp.Api.utils.TransactionApi;
import com.upp.UsersApi.Delete_UserRole_API;
import com.upp.UsersApi.UpdateUser;
import com.upp.base.BaseClass;
import com.upp.handlers.CommonDocumentTypeHandler;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.CommonResponsibilityHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.handlers.DealPartyAccount_PaymentInstrumentHandler;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.handlers.EcommerceHandler;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.odp.utils.Create_ODP_Account_Api;
import com.upp.odp.utils.Logout_ODP_Api;
import com.upp.pagemodules.BasicDetails;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Login.LoginAPI_ODP;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.Login.LoginToApplication;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Edit_LiveDeal;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Verify_PartyApiAdded;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.Users.UserChecker;
import com.upp.pagemodules.Users.UserMaker;
import com.upp.pagemodules.document.AddDealWithDocument;
import com.upp.pagemodules.document.DocumentChecker;
import com.upp.pagemodules.document.DocumentMaker;
import com.upp.pagemodules.document.RequiredDocumentSchedule;
import com.upp.pagemodules.payment.Payment_Alerts;
import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS109 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public static String TSID = "";
	public static String TnxId = "";
	LoginAPI_ODP login;
	Create_ODP_Account_Api createAcc;
	Logout_ODP_Api logout;
	Payload payload;
	TransactionApi txn;
	LoginAPI_UPP login_UPP;
	LogOutApi logout_UPP;
	Delete_UserRole_API user;
	UserMaker maker;
	String userName;

	public TS109(DashBoard_Module dm) {

		this.dm = new DashBoard_Module();
		login = new LoginAPI_ODP();
		createAcc = new Create_ODP_Account_Api();
		logout = new Logout_ODP_Api();
		txn = new TransactionApi();
		login_UPP = new LoginAPI_UPP();
		maker = new UserMaker();

	}

	@Then("Call the DELETE_USER_ROLE_API with given {string}.")
	public void call_the_DELETE_USER_ROLE_API_with_given(String string) throws Exception {
		login_UPP.loginToUpp();
		userName = user.deleteRole(string);
	}

	@Then("Verify deleted role not displayed {string}.")
	public void verify_deleted_role_not_displayed(String string) throws Exception {
	    maker.verifyDeletedRole(string);
	}
	
	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
