package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.Fetch_All_Roles_API;
import com.upp.Api.utils.Get_List_of_Users_API;
import com.upp.Api.utils.Get_User_By_UserName_API;
import com.upp.Api.utils.LogOutApi;

import com.upp.Api.utils.PartyApi;
import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;

import callbackInterfaces.ICallback;

import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Edit_LiveDeal;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Verify_Delete_Party_Api;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Verify_PartyApiAdded;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Verify_Update_Party_API;
import com.upp.pagemodules.Users.Verify_User_By_User_Name;
import com.upp.pagemodules.Users.Verify_User_Roles_Available;

import io.cucumber.java.en.*;

public class TS96 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
//	public static String dealId = "";
	public static String TSID = "";
	public static String TnxId = "";
	Fetch_All_Roles_API rolesApi;
	Verify_User_Roles_Available verify_roles;

	public TS96() {

		this.dm = new DashBoard_Module();
        verify_roles=new Verify_User_Roles_Available();
        rolesApi=new Fetch_All_Roles_API();
	}

	@Then("Call the Fetch_all_roles_API")
	public void call_the_Fetch_all_roles_API() throws Exception {
		rolesApi.fetch_All_Roles_API();
	}

	@Then("Verify the first {int} roles present in UI with given {string}.")
	public void verify_the_first_roles_present_in_UI_with_given(Integer int1, String string) throws Exception{
		verify_roles.verify_User_Roles_Available_IN_UI(string);
	}
}
