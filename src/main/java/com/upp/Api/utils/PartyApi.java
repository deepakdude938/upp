package com.upp.Api.utils;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import com.upp.utils.Property;

public class PartyApi {

	public static void createParty(String dealId) throws Exception {


		String base_Url = Property.getProperty("Dev_base_uri");

//Party api
		RestAssured.baseURI = base_Url;
		String response = given()
				.header("Content-Type", "application/json")
				.header("Authorization", LoginApi.authToken)
				.body(Payload.createParty(dealId)).when()
				.post("mdm/api/party").then()
				.assertThat().statusCode(200)
				.extract()
				.response().asString();
		System.out.println("The Part Api response is "+response);
		JsonPath js = new JsonPath(response);
	

}
}