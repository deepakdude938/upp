package com.upp.pagemodules.Login;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.Api.utils.Payload;
import com.upp.utils.Property;

public class LoginAPI_UPP {
	public static String authToken="";

	public static void loginToUpp() throws Exception {


		String base_Url = Property.getProperty("Dev_base_uri");
	
//Login api
		RestAssured.baseURI = base_Url;
		String responseLogin = given()
				.header("Content-Type", "application/json")
				.body(Payload.loginToUPP()).when()
				.post("idm/api/v1/login").then()
				//.assertThat().statusCode(200)
				.extract()
				.response().asString();
		
		System.out.println("The login Api response is "+responseLogin);
   
		JsonPath js = new JsonPath(responseLogin);

		String token = js.getString("token");

		authToken = "JWT " + token;
}
}