package com.upp.odp.utils;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_ODP;
import com.upp.stepdefinition.TS06;
import com.upp.utils.Property;

public class Fetch_EndToEndId_From_ODP extends BaseClass{

public void fetchEndToEndIdFromODP(String TSID) throws Exception {
		
		
		String base_Url = Property.getProperty("Odp_base_uri");

				RestAssured.baseURI = base_Url;
				String responseLogin = given()
						.header("Content-Type", "application/json")
						.body(Payload.Login()).when()
						.post("api/a/rbac/login").then()
						.assertThat().statusCode(200)
						.extract()
						.response().asString();

				JsonPath js = new JsonPath(responseLogin);
				String token = js.getString("token");
				String authToken = "JWT " + token;
				

				Response res = given()
						.header("Content-Type", "application/json")
						.header("Authorization", authToken).when()
						.get("api/c/acache/testAutomationAssertions/"+TSID);
				int statusCode = res.getStatusCode();
				
				RestAssured.baseURI = base_Url;
				String response_account = given()
						.header("Content-Type", "application/json")
						.header("Authorization", authToken)
						.when()
						.get("api/c/acache/testAutomationAssertions/"+TSID).then()
						.assertThat()
						.statusCode(200).extract()
						.response().asString();
				System.out.println("-----------");
				System.out.println(response_account);
				System.out.println("-----------");
				JsonPath js1 = new JsonPath(response_account);
				endToEndIdAssertion = js1.getString("endToEndId");
				System.out.println("End To End id from odp"+endToEndIdAssertion);
				
				
				RestAssured.baseURI = base_Url;
				String response_LogOut = given()
						.header("Content-Type", "application/json")
						.header("Authorization", authToken)
						.when().delete("api/a/rbac/logout").then()
						.assertThat().statusCode(200)
						.extract().response().asString();
	}
	
}
