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
	public static String partyRefId="";
	public static String partyId="";
	public static void createTransaction(String dealId,String TSID) throws Exception {

// Create Party api
		RestAssured.baseURI = base_Url;
		String response = given()
				.header("Content-Type", "application/json")
				.header("Authorization",LoginAPI_UPP.authToken)
				.body(Payload.createTransaction(dealId,TSID)).when()
				.post("transaction/api/transaction").then()
				//.assertThat().statusCode(200)
				.extract()
				.response().asString();
//		System.out.println("The Party Api response is "+response);
//		JsonPath js = new JsonPath(response);
//		partyRefId=js.getString("partyRefId");
//		partyId=js.getString("partyId");
//		System.out.println("The Party ref id is "+partyRefId);
//		System.out.println("The Party id is "+partyId);
	

}
	public static void getParty(String dealId,String TSID) throws Exception{
//Get Party Api
		
		RestAssured.baseURI = base_Url;
		String response = given()
				.header("Content-Type", "application/json")
				.queryParam("filter","{\"name\":\""+partyRefId+"\"}")
				.header("Authorization",LoginAPI_UPP.authToken)
				.when()
				.get("mdm/api/v1/partyList").then()
				//.assertThat().statusCode(200)
				.extract()
				.response().asString();
		System.out.println("The get Party Api response is "+response);
		JsonPath js = new JsonPath(response);
		String dealid_Response=js.getString("data[0].refId");
		System.out.println("The deal id in response body "+dealid_Response);
		Assert.assertEquals(dealId,dealid_Response);
		
	}
   
	public static void updateParty(String dealId,String TSID) throws Exception{
		
		String putPath="mdm/api/party/"+partyId;
		RestAssured.baseURI = base_Url;
		String response = given()
				.header("Content-Type", "application/json")
				.header("Authorization",LoginAPI_UPP.authToken)
				.body(Payload.updateParty(dealId,TSID)).when()
				.put(putPath).then()
				//.assertThat().statusCode(200)
				.extract()
				.response().asString();
		System.out.println("The Update Party Api response is "+response);
		JsonPath js = new JsonPath(response);
		
	}
	
}