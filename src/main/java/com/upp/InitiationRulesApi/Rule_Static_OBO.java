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

public class Rule_Static_OBO {
	public static String response = "";

	public static String base_Url = Property.getProperty("Dev_base_uri");

	public static ExcelReader externalData;

	public static void Rule_Non_OBO_Api(String dealId, String TSID) throws Exception {

		externalData = new ExcelReader();

		String ActualErrorMessage = externalData.getFieldData(TSID, "Initiation Rules", "Response Message");

		RestAssured.baseURI = base_Url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.Rule_Non_OBO(dealId, TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("The Rule_Non_OBO response is " + response);

		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 400) {
			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			Assert.assertEquals(ExpectederrorMessage, ActualErrorMessage);

		}

	}

	public static void Rule_Static_OBO_Api(String TSID, String dealId, String participant1) throws Exception {
		externalData = new ExcelReader();

		String ActualErrorMessage = externalData.getFieldData(TSID, "Initiation Rules", "Response Message");

		RestAssured.baseURI = base_Url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.rule_static_obo(TSID, dealId, participant1)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("The Rule_Non_OBO response is " + response);

		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 400) {
			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			Assert.assertEquals(ExpectederrorMessage, ActualErrorMessage);
		}
	}

}