package com.upp.InitiationRulesApi;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

public class Rule_OBOParticipant_OBO_Info_Null_OBO_Virtual extends BaseClass {
	public static String response = "";
	public ExcelReader externalData;

	public void Rule_OBOParticipant_OBO_Info_Null_OBO_Virtual_Api( String TSID,String dealId, String virtualaccount) throws Exception {

		externalData = new ExcelReader();

		String ExpectederrorMessage = externalData.getFieldData(TSID, "Initiation Rules", "Response Message");

		RestAssured.baseURI = base_url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(Payload.rule_ParticipantId_OBODetails_Virtual( TSID,dealId, virtualaccount)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("The Rule_Non_OBO_Virtual response is " + response);

		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 400) {
			JsonPath js = new JsonPath(response);
			String ActualErrorMessage = js.getString("errors[0].message");
			Assert.assertEquals(ExpectederrorMessage, ActualErrorMessage);

		}
	}
}