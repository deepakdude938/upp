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

public class Rule_OBO_Participant_Enrich extends BaseClass {
	public static String response = "";

	public static ExcelReader externalData;
	public static String endToEndId="";

	public static String Rule_OBO_Participant_Enrich_Api(String dealId, String TSID) throws Exception {

		externalData = new ExcelReader();

		RestAssured.baseURI = base_url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.Rule_OBO_Participant_Enrich(dealId, TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("TheRule_OBO_Participant_Enrich response is " + response);

		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 200) {
			JsonPath js = new JsonPath(response);
			endToEndId=js.getString("endToEndId");
		}
		else
		{
			Assert.fail("Status code should be 200");
		}
        return endToEndId;
	}

}