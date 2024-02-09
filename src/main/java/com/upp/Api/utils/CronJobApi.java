package com.upp.Api.utils;

import static io.restassured.RestAssured.given;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CronJobApi extends BaseClass{
	
	public CronJobApi() {
		
	}

	public void hitCronJobApi() {
		RestAssured.baseURI = base_url;
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).when().get("/execution/api/v1/scheduler/generateSchedules").then()
				.assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath js = new JsonPath(response);
		
	}
}
