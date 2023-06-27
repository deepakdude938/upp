package com.upp.PaymentRulesApi;

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

public class Rule_IN_BT {
	public static String response = "";

	public static String base_Url = Property.getProperty("Dev_base_uri");

	public static ExcelReader externalData;

	public static void Rule_Non_OBO_Api(String dealId, String TSID) throws Exception {

		externalData = new ExcelReader();


		RestAssured.baseURI = base_Url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.Rule_IN_BT(dealId, TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("The Rule_IN_BT response is " + response);

		System.out.println("the status code is" + res.getStatusCode());

	}

}