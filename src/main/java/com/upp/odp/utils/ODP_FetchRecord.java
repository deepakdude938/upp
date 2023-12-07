package com.upp.odp.utils;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.utils.Property;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ODP_FetchRecord extends BaseClass{
	
	public ODP_FetchRecord() {
		
	}

	public void verifyEmailIdFromODP(String TSID) throws IOException {
		
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
				
				String url ="api/c/acache/email?filter={\"refId\":\""+dealId+"\"}";
//				Response res = given()
//						.header("Content-Type", "application/json")
//						.header("Authorization", authToken).when()
//						.get(url);
//				int statusCode = res.getStatusCode();
				
				RestAssured.baseURI = base_Url;
				String response_account = given()
						.header("Content-Type", "application/json")
						.header("Authorization", authToken)
						.when()
						.get(url).then()
						.assertThat()
						.statusCode(200).extract()
						.response().asString();
				System.out.println("-----------");
				System.out.println(response_account);
				System.out.println("-----------");
				JsonPath js1 = new JsonPath(response_account);
//				dealId = js1.getString("dealRefId");
//				String dealId = js1.getString("dealId");
			
//				System.out.println("Deal Ref Id is Assertion"+super.dealId);
//				System.out.println("Deal Id is Assertion"+dealId);
				
				RestAssured.baseURI = base_Url;
				String response_LogOut = given()
						.header("Content-Type", "application/json")
						.header("Authorization", authToken)
						.when().delete("api/a/rbac/logout").then()
						.assertThat().statusCode(200)
						.extract().response().asString();
	
		
	}

}
