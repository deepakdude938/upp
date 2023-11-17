package com.upp.stepdefinition;

import java.net.MalformedURLException;
import java.time.Duration;

import com.upp.Api.utils.TransactionApi;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Account.AccountAmendment;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.PartyDetails.Party_Accounts;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;

import io.cucumber.java.en.Then;

public class TS131 extends BaseClass{
	
	public LoginAPI_UPP login_UPP;
	public AccountAmendment am;
	public static Object_Parties op;
	public static Object_NewDeal od;
	public static TransactionApi ta;
	
	public TS131() {
		login_UPP = new LoginAPI_UPP();
		am= new AccountAmendment();
		op=new Object_Parties();
		od = new Object_NewDeal();
		ta=new TransactionApi();
	}

	@Then("Hit NewAccountwithSameCreditorLookUpKeys_Api {string}")
	public void hit_NewAccountwithSameCreditorLookUpKeys_Api(String TSID) throws Exception {
		login_UPP.loginToUpp();
	   am.callNewAccountwithSameCreditorLookUpKeys_Api(TSID);
	}
	
	@Then("Call Account_Ammendment_NewAccount2_API {string}")
	public void call_Account_Ammendment_NewAccount2_API(String TSID) throws Exception {
		login_UPP.loginToUpp();
		   am.Account_Ammendment_NewAccount2_API(TSID);
	}
	
	@Then("Hit NewFourthAccount_Api {string}")
	public void hit_NewFourthAccount_Api(String TSID) throws Exception {
		login_UPP.loginToUpp();
		   am.Account_Ammendment_NewFourthAccount_API(TSID);
	}
	
	@Then("Create Transaction Api for {string}")
	public void create_Transaction_Api_for(String TSID) throws Exception {
		login_UPP.loginToUpp();
		endToEndId=	 ta.createTransactionApi(TSID);
		System.out.println(endToEndId);
	}
	
	@Then("Open account list from party")
	public void open_account_list_from_party() throws Exception {
		applyExplicitWaitsUntilElementClickable(op.parties_PartyTab, Duration.ofSeconds(20));
		op.parties_PartyTab.click();
		op.parties_editPartyButton.click();
		try {
			od.parties_AccountsTab.click();
		}
		catch(Exception e) {
			handleElementClickException(od.parties_AccountsTab);
		}
		Thread.sleep(2000);
	}
	
}
