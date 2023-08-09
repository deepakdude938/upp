package com.upp.InitiationRulesApi;

import static io.restassured.RestAssured.given;
import java.time.Duration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pageobjects.Object_Ecommerce;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Rules_Party_EnrichDebtor extends BaseClass{
	
	public static ExcelReader externalData;
	String modifiedJsonString ="";
	String response = "";
	public	Object_Ecommerce ecom;
	public Payload pl;

	public Rules_Party_EnrichDebtor(){
		externalData=	 new ExcelReader();
		ecom=new Object_Ecommerce();
		pl=new Payload();
	}
	
	public void partyEnrichDebtorRule() throws Exception {

		RestAssured.baseURI = base_url;
		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(pl.modifiedJsonString).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("the status code is" + res.getStatusCode());
		System.out.println(response);
	}

	public void verify_transaction_should_be_present_at_Ecomm_Transaction_Verifier_que() throws Exception {
		
		ecom.ecommerce_SideMenuIcon.click();
		Thread.sleep(1000);
		ecom.ecommerce_txnVerifier.click();
		ecom.ecommerce_dealIdInline.sendKeys(dealId,Keys.ENTER);
		applyExplicitWaitsUntilElementVisible(	ecom.ecommerce_dealIdInline, Duration.ofSeconds(10));
		String txnIdText =ecom.ecommerce_TxnIdRecord.getText();
		System.out.println(txnIdText);
		JSONParser parser = new JSONParser();
		 JSONObject jsonObject = (JSONObject) parser.parse(response);
		 String txnId=	 (String) jsonObject.get("workItemId");
		 Assert.assertEquals(txnIdText, txnId);
		
	}
}
