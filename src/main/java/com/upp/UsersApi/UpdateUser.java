package com.upp.UsersApi;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.*;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import com.upp.UsersApi.Payload;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

public class UpdateUser extends BaseClass {
	public static String response = "";

	public static ExcelReader externalData;

	public static String updateUsers(String TSID, Properties prop) throws Exception {

		externalData = new ExcelReader();

		RestAssured.baseURI = base_url;

		System.out.println(base_url);

		Response res = RestAssured.given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1")
				.header("X-SC-TrackingId", "123").body(Payload.CreateUser(TSID, prop)).when()
				.post("idm/api/idam/users");

		response = res.then().extract().asString();

		int statusCode = res.getStatusCode();
		// Replace "keyName" with the actual key in the JSON response

		System.out.println("Response Status Code: " + statusCode);
		String specificValue = res.jsonPath().get("username");
		
		System.out.println("The  response is " + response);
		System.out.println("The username " + specificValue);
		Assert.assertEquals(statusCode, 200);
//		
		//String specificValue ="manisha3";
		
		return specificValue;
	}

}