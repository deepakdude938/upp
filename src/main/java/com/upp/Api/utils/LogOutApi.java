package com.upp.Api.utils;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import com.upp.utils.Property;

public class LogOutApi {

	public static void logOut() throws Exception {


		String base_Url = Property.getProperty("Dev_base_uri");

//Logout Api
		RestAssured.baseURI = base_Url;
		String response_LogOut = given()
				.header("Content-Type", "application/json")
				.header("Authorization",LoginApi.authToken)
				.when().post("idm/api/v1/logout").then()
				.assertThat().statusCode(200)
				.extract().response().asString();

		System.out.println("The Logout Api response is "+response_LogOut);

}
}