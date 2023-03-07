package com.upp.Api.utils;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import com.upp.utils.Property;

public class LoginApi {
	public static String authToken="";

	public static void loginToUpp() throws Exception {


		String base_Url = Property.getProperty("Dev_base_uri");

//Login api
		RestAssured.baseURI = base_Url;
		String responseLogin = given()
				.header("Content-Type", "application/json")
				.body(Payload.loginToUPP()).when()
				.post("idm/api/v1/login").then()
				.assertThat().statusCode(200)
				.extract()
				.response().asString();

		JsonPath js = new JsonPath(responseLogin);

		String token = js.getString("token");

		authToken = "JWT " + token;

}
}