package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.pagemodules.ECommerce.Rules_Party_EnrichDebtor;
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.cucumber.java.en.Then;

public class TS30 {

	public Rules_Party_EnrichDebtor enrich;
	public LoginAPI_UPP loginApi;
			public TS30() {
				enrich = new Rules_Party_EnrichDebtor();
			}
			
			@Then("Login to UPP through api")
			public void login_to_UPP_through_api() throws Exception {
				loginApi.loginToUpp();
			}
			
			
			
			@Then("Update json for Party_EnrichDebtor rule api {string}")
			public void update_json_for_Party_EnrichDebtor_rule_api(String TSID) throws Exception {
				enrich.updateJsonFilePartyEnrichDebtorRule(TSID);
			}
			
			@Then("Call Party_EnrichDebtor rule api")
			public void call_Party_EnrichDebtor_rule_api() throws Exception {
			   enrich.partyEnrichDebtorRule();
			}
			
			@Then("Verify transaction should be present at Ecomm Transaction Verifier que")
			public void verify_transaction_should_be_present_at_Ecomm_Transaction_Verifier_que() throws Exception {
				enrich. verify_transaction_should_be_present_at_Ecomm_Transaction_Verifier_que();			}
			
}
