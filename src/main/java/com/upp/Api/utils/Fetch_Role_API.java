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

public class Fetch_Role_API extends BaseClass {

	public static void fetch_Role_API() throws Exception {

		RestAssured.baseURI = base_url;
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1")
				.header("X-SC-TrackingId", "123").when().get("idm/api/idam/roles/DEALMAKER").then().assertThat()
				.statusCode(200).extract().response().asString();
		System.out.println("The Fetch Role_ API response is " + response);
		
		JsonPath js = new JsonPath(response);
		String Rolename = js.getString("_id");
		System.out.println("The Rolename is:"+Rolename);
		Assert.assertEquals(Rolename,"DEALMAKER");
	

	}

}