package com.upp.stepdefinition;

import java.io.IOException;

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

import io.cucumber.java.en.*;

public class TS85 extends BaseClass {
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
	Party_Edit_LiveDeal editDeal;
	Party_Verify_Delete_Party_Api delete;

	public TS85() {

		this.dm = new DashBoard_Module();
		editDeal = new Party_Edit_LiveDeal();
		partyApi=new PartyApi();
		delete=new Party_Verify_Delete_Party_Api();
	}


	@Then("Call the Delete Party API with given {string}")
	public void call_the_Delete_Party_API_with_given(String string) throws Exception {
	partyApi.DeleteParty(TS06.dealId, string);
	}
	
	@Then("Edit the Deal and Verify in UI no Parties are added")
	public void edit_the_Deal_and_Verify_in_UI_no_Parties_are_added() throws Exception {
	    delete.Verify_Delete_PartyApi_In_UI(TS06.dealId);
	}


}
