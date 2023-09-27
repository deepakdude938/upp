package com.upp.UsersApi;

import static io.restassured.RestAssured.*;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

public class Get_List_of_10Users_API extends BaseClass {
	public static String Username1 = "";
	public static String Username2 = "";
	public static String Username3 = "";

	public static void getListOfUsers() throws Exception {

		RestAssured.baseURI = base_url;
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1")
				.header("X-SC-TrackingId", "123").queryParam("page", 0).queryParam("count", 10)
				.queryParam("sort", "{\"username\": 1}").when().get("idm/api/idam/users").then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("The Get list of Users API response is " + response);
		JsonPath js = new JsonPath(response);
//		Username1 = js.getString("users[0].name");
//		Username2 = js.getString("users[1].name");
//		Username3 = js.getString("users[2].name");

		 int userCount = js.getList("users").size();
		 System.out.println("Size"+userCount);
		 Assert.assertEquals(userCount, 10);


	}

}