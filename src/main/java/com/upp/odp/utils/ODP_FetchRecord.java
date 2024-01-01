package com.upp.odp.utils;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;

import com.upp.base.BaseClass;
import com.upp.utils.Property;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ODP_FetchRecord extends BaseClass{
	
	public ODP_FetchRecord() {
		
	}

	public void verifyEmailIdFromODP(String TSID) throws IOException {
		
		List<String> emailList = List.of("shiva12@test.com", "eimailsetup@gmail.com");
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
				
				String url ="api/c/acache/email";
				String filterValue = "{\"refId\":\""+dealId+"\"}";
				System.out.println(base_Url);
				System.out.println(url);
				RestAssured.baseURI = base_Url;
				String response_account = given()
						.header("Content-Type", "application/json")
						.header("Authorization", authToken)
						.when().queryParam("filter", filterValue)
						.get(url).then()
						.assertThat()
						.statusCode(200).extract()
						.response().asString();
				System.out.println(response_account);
				JsonPath js1 = new JsonPath(response_account);
				  List<String> bccValues = js1.getList("[0].bcc");
				Assert.assertEquals(emailList,bccValues);
				
				RestAssured.baseURI = base_Url;
				String response_LogOut = given()
						.header("Content-Type", "application/json")
						.header("Authorization", authToken)
						.when().delete("api/a/rbac/logout").then()
						.assertThat().statusCode(200)
						.extract().response().asString();
	
	}
}
