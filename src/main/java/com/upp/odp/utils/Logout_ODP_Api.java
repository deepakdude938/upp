package com.upp.odp.utils;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.odp.utils.Payload;
import com.upp.pagemodules.Login.LoginAPI_ODP;
import com.upp.utils.Property;

public class Logout_ODP_Api {

	public static void LogOutOdpApi() throws Exception {

		String base_Url = Property.getProperty("Odp_base_uri");

//logout api
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = base_Url;
		String response_LogOut = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_ODP.OdpauthToken).when().delete("api/a/rbac/logout").then()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println(response_LogOut);

	}

}
