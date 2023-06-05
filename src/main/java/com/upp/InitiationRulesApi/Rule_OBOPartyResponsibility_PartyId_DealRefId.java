package com.upp.InitiationRulesApi;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

public class Rule_OBOPartyResponsibility_PartyId_DealRefId {
	public static String response = "";

	public static String base_Url = Property.getProperty("Dev_base_uri");

	public static ExcelReader externalData;

	public static void Rule_OBOPartyResponsibility_PartyId_DealRefid(String dealId, String TSID) throws Exception {

		externalData = new ExcelReader();

		String ActualErrorMessage = externalData.getFieldData(TSID, "Initiation Rules", "Response Message");

		RestAssured.baseURI = base_Url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.Rule_OBOPartyResponsibility_PartyId_DealRefId(dealId, TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("Rule_OBOPartyResponsibility_PartyId_DealRefId" + response);

		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 400) {
			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			Assert.assertEquals(ExpectederrorMessage, ActualErrorMessage);

		}

	}

}