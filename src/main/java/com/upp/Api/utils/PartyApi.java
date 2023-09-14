package com.upp.Api.utils;

import static io.restassured.RestAssured.*;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.Property;

public class PartyApi extends BaseClass {

	public static String partyRefId = "";
	public static String partyId = "";

	public static void createParty(String dealId, String TSID) throws Exception {

// Create Party api
		RestAssured.baseURI = base_url;
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.createParty(dealId, TSID)).when()
				.post("mdm/api/party").then()
				// .assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println("The Party Api response is " + response);
		JsonPath js = new JsonPath(response);
		partyRefId = js.getString("partyRefId");
		partyId = js.getString("partyId");
		System.out.println("The Party ref id is " + partyRefId);
		System.out.println("The Party id is " + partyId);

	}

	public static void getParty(String dealId, String TSID) throws Exception {
//Get Party Api

		RestAssured.baseURI = base_url;
		String response = given().header("Content-Type", "application/json")
				.queryParam("filter", "{\"name\":\"" + partyRefId + "\"}")
				.header("Authorization", LoginAPI_UPP.authToken).when().get("mdm/api/v1/partyList").then()
				// .assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println("The get Party Api response is " + response);
		JsonPath js = new JsonPath(response);
		
		String dealid_Response = js.getString("data[0].refId");
		String partyname = js.getString("data[0].name");
		String particpantId=js.getString("data[0].participantId");
		String remarks=js.getString("data[0].remarks");
		String country=js.getString("data[0].country");
		String EcommerceEnabled=js.getString("data[0].eCommerceEnabled");
		
		System.out.println("The deal id in response body " + dealid_Response);
		System.out.println("The party name in response body " + partyname);
		System.out.println("The participantId in response body " + particpantId);
		System.out.println("The remarks in response body " + remarks);
		System.out.println("The country in response body " + country);
		System.out.println("The EcommerceEnabled in response body " + EcommerceEnabled);
		
		Assert.assertEquals(dealId, dealid_Response);
		Assert.assertEquals(partyRefId, partyname);
		Assert.assertEquals(partyRefId, particpantId);
		Assert.assertEquals(remarks, "Test11");
		Assert.assertEquals(country, "IN");
		Assert.assertEquals(EcommerceEnabled, "true");

	}

	public static void updateParty(String dealId, String TSID) throws Exception {

		String putPath = "mdm/api/party/" + partyId;
		RestAssured.baseURI = base_url;
		System.out.println("path = " + putPath);
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.updateParty(dealId, TSID)).when()
				.put(putPath).then()
				// .assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println("The Update Party Api response is " + response);
		JsonPath js = new JsonPath(response);

	}

	public static void createPartyUsingExcel(String dealId, String TSID) throws Exception {

		// Create Party api
		RestAssured.baseURI = base_url;
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.createPartyUsingExcel(dealId, TSID))
				.when().post("mdm/api/party").then()
				// .assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println("The Party Api response is " + response);
		JsonPath js = new JsonPath(response);
		partyRefId = js.getString("partyRefId");
		partyId = js.getString("partyId");
		System.out.println("The Party ref id is " + partyRefId);
		System.out.println("The Party id is " + partyId);

	}

	public static void updateParty1(String dealId, String TSID) throws Exception {

		String putPath = "mdm/api/party/" + "DA002";
		RestAssured.baseURI = base_url;
		System.out.println("path = " + putPath);
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.updateParty(dealId, TSID)).when()
				.put(putPath).then()
				// .assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println("The Update Party Api response is " + response);
		JsonPath js = new JsonPath(response);

	}
	
	public static void DeleteParty(String dealId, String TSID) throws Exception {

		String putPath = "mdm/api/party/" + partyId;
		RestAssured.baseURI = base_url;
		System.out.println("path = " + putPath);
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).when()
				.delete(putPath).then()
			    .assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println("The Delete Party Api response is " + response);
		JsonPath js = new JsonPath(response);

	}
	public static void DeleteParty_In_Deal_Draft_State(String dealId, String TSID) throws Exception {

		String putPath = "mdm/api/party/" + partyId;
		RestAssured.baseURI = base_url;
		System.out.println("path = " + putPath);
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).when()
				.delete(putPath).then()
			    .assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println("The Delete Party Api response is " + response);
		JsonPath js = new JsonPath(response);

	}

}