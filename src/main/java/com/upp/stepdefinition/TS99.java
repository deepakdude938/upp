package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.Add_Role_to_User_Api;
import com.upp.Api.utils.Fetch_All_Roles_API;
import com.upp.Api.utils.Fetch_Role_API;
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
import com.upp.pagemodules.Users.Verify_Role_Is_Added_To_User;
import com.upp.pagemodules.Users.Verify_User_By_User_Name;
import com.upp.pagemodules.Users.Verify_User_Roles_Available;

import io.cucumber.java.en.*;

public class TS99 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
//	public static String dealId = "";
	public static String TSID = "";
	public static String TnxId = "";
	Add_Role_to_User_Api api;
	Verify_Role_Is_Added_To_User verify;
	public TS99() {

		this.dm = new DashBoard_Module();
       api=new Add_Role_to_User_Api();
       verify=new Verify_Role_Is_Added_To_User();
	}
   
	@Then("Call the Add Role to a User API with given {string}.")
	public void call_the_Add_Role_to_a_User_API(String string) throws Exception {
	  api.Add_Role_To_User_API(string);
	}

	@Then("Verify the role got added in UI with given {string}.")
	public void verify_the_role_got_added_in_UI_with_given(String string) throws Exception {
	   verify.verify_Role_Is_Added_To_User(string);
	}

	
}
