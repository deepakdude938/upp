package com.upp.Api.utils;

import static io.restassured.RestAssured.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.Property;

public class Fetch_All_Roles_API extends BaseClass {
	public static String RoleName1 = "";
	public static String RoleName2 = "";
	public static String RoleName3 = "";

	public static void fetch_All_Roles_API() throws Exception {

		String encodedSortValue = URLEncoder.encode("{\"name\":-1}", StandardCharsets.UTF_8.toString());
		RestAssured.baseURI = base_url;
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1")
				.header("X-SC-TrackingId", "123").queryParam("page", 0).queryParam("count", 10)
				.queryParam("sort", encodedSortValue).when().get("idm/api/idam/roles").then().assertThat()
				.statusCode(200).extract().response().asString();
		System.out.println("The Fetch_All_Roles_API response is " + response);
		JsonPath js = new JsonPath(response);
		RoleName1 = js.getString("roles[0].name");
		RoleName2 = js.getString("roles[1].name");
		RoleName3 = js.getString("roles[2].name");
		
		System.out.println("The Rolename1 is:"+RoleName1);
		System.out.println("The Rolename2 is:"+RoleName2);
		System.out.println("The Rolename1 is:"+RoleName3);

	}

}