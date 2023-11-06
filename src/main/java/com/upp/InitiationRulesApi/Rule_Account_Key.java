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

public class Rule_Account_Key extends BaseClass {
	public static String response = "";


	public static ExcelReader externalData;

	public static String Rule_Account_Key_Api(String dealId, String TSID) throws Exception {

		externalData = new ExcelReader();

		RestAssured.baseURI = base_url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.Rule_With_Account_Key(dealId, TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("The Rule_Account_Key response is " + response);

		System.out.println("the status code is" + res.getStatusCode());

		JsonPath js=new JsonPath(response);
		String EndToEndId=js.get("endToEndId");
		System.out.println("The EndToEnd id is:"+EndToEndId);
		
		return EndToEndId;
	}
}