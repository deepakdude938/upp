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

public class Rule_OBO_Responsibility_Reject extends BaseClass{
	public static String response = "";

	public static ExcelReader externalData;

	public static void Rule_OBO_Responsibility_reject(String dealId, String TSID) throws Exception {

		externalData = new ExcelReader();

		String ActualErrorMessage = externalData.getFieldData(TSID, "Initiation Rules", "Response Message");

		RestAssured.baseURI = base_url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.Rule_OBO_Responsibility_Reject(dealId, TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("The Rule_OBO_Responsibility_Reject is " + response);

		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 400) {
			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			Assert.assertEquals(ExpectederrorMessage, ActualErrorMessage);

		}
		else
		{
			Assert.fail("Status code should be 200");
		}

	}

}