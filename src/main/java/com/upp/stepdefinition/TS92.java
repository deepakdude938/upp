package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.Delete_User_By_UserName_API;
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
import com.upp.pagemodules.Users.Verify_User_Is_Deleted;

import io.cucumber.java.en.*;

public class TS92 extends BaseClass {
	DashBoard_Module dm;
	DealPage dp;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
//	public static String dealId = "";
	public static String TSID = "";
	public static String TnxId = "";
	LoginAPI_UPP loginApi;
	PartyApi partyApi;
	LogOutApi logoutApi;
	Delete_User_By_UserName_API deleteApi;
	String UserName="";
	Verify_User_Is_Deleted verify_user_deleted;
	public TS92() {

		this.dm = new DashBoard_Module();
		deleteApi=new Delete_User_By_UserName_API();
		verify_user_deleted=new Verify_User_Is_Deleted();
	
	}
	
	@Then("Call the DELETE_USER_BY_USERNAME_API with given {string}.")
	public void call_the_DELETE_USER_BY_USERNAME_API(String string) throws Exception{
	   UserName= deleteApi.deleteUserByUserName(string);
	}

	@Then("Verify User doesnt exist in UI with given {string}.")
	public void verify_User_doesnt_exist_in_UI_with_given(String string) throws Exception {
		verify_user_deleted.verify_User_is_Deleted(UserName);
	}

}
