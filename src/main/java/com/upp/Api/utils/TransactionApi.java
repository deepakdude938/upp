package com.upp.Api.utils;

import static io.restassured.RestAssured.*;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.Property;

public class TransactionApi {

	public static String base_Url = Property.getProperty("Dev_base_uri");
	
	public static String createTransaction(String dealId,String TSID) throws Exception
	{
		RestAssured.baseURI = base_Url;
		String response = given()
				.header("Content-Type", "application/json")
				.header("Authorization",LoginAPI_UPP.authToken)
				.body(Payload.createTransaction(dealId,TSID)).when()
				.post("transaction/api/transaction").then()
				//.assertThat().statusCode(200)
				.extract()
				.response().asString();
		System.out.println("The Create Ecomm Api response is "+response);
		
		JsonPath js = new JsonPath(response);
		String endToEndId=js.getString("endToEndId");
		System.out.println("The End to end is"+endToEndId);
	
		return endToEndId;
	}	
}