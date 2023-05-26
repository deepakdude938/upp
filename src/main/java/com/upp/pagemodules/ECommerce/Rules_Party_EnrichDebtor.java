package com.upp.pagemodules.ECommerce;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Keys;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.upp.Api.utils.Payload;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pageobjects.Object_Ecommerce;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.DateUtils;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Rules_Party_EnrichDebtor extends BaseClass{
	
	public static ExcelReader externalData;
	public static String base_Url = Property.getProperty("Dev_base_uri");
	String modifiedJsonString ="";
	String response = "";
	public	Object_Ecommerce ecom;
	
	public Rules_Party_EnrichDebtor(){
		externalData=	 new ExcelReader();
		ecom=new Object_Ecommerce();
	}
	
	public void partyEnrichDebtorRule() throws Exception {
	
		RestAssured.baseURI = base_Url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(modifiedJsonString).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("the status code is" + res.getStatusCode());
		System.out.println(response);
	}

	public String updateJsonFilePartyEnrichDebtorRule(String TSID) throws Exception, IOException {
	String jsonEnrichDebtor = externalData.getFieldData(TSID, "Rules", "Payload");
	String platformRefNo= "Party"+generateRandomString(6);
	String tomorow = DateUtils.getDate(1)+"T11:28:00Z";
	DocumentContext jsonContext = JsonPath.parse(jsonEnrichDebtor);
		 jsonContext.set("$.dealRefId",dealId);
		 jsonContext.set("$.paymentInfo.accountNumber",DealPage.toaccountNo);
	     jsonContext.set("$.paymentInfo.platformRefNo", platformRefNo);
	     jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", tomorow);
	     modifiedJsonString = jsonContext.jsonString();
	
		return modifiedJsonString;
	}

	public void verify_transaction_should_be_present_at_Ecomm_Transaction_Verifier_que() throws Exception {
		
		ecom.ecommerce_SideMenuIcon.click();
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
