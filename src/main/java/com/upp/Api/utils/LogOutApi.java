package com.upp.Api.utils;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.Property;

public class LogOutApi extends BaseClass{

	public static void logOut() throws Exception {




//Logout Api
		RestAssured.baseURI = base_url;
		String response_LogOut = given()
				.header("Content-Type", "application/json")
				.header("Authorization",LoginAPI_UPP.authToken)
				.when().post("idm/api/v1/logout").then()
				.assertThat().statusCode(200)
				.extract().response().asString();

		System.out.println("The Logout Api response is "+response_LogOut);

}
}