package com.upp.odp.utils;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import  com.upp.odp.utils.Payload;
import com.upp.pagemodules.Login.LoginAPI_ODP;
import com.upp.utils.Property;

public class EditAccountApi {


	public static void editAccount(String TSID,String accountno,int amount) throws Exception {

		String base_Url = Property.getProperty("Odp_base_uri");


		
//Edit account Api
		
		String path="api/c/XCRO6-DIY/accounts/"+accountno;
	
			RestAssured.baseURI = base_Url;
			String response = given().log().all()
					.header("Content-Type", "application/json")
					.header("Authorization",LoginAPI_ODP.OdpauthToken)
					.body(Payload.editAccount(TSID, amount,accountno)).when()
					.put(path).then().log().all()
					//.assertThat().statusCode(200)
					.extract()
					.response().asString();
			System.out.println("The Edit account response is "+response);
		


//logout api
		RestAssured.baseURI = base_Url;
		String response_LogOut = given()
				.header("Content-Type", "application/json")
				.header("Authorization",LoginAPI_ODP.OdpauthToken)
				.when().delete("api/a/rbac/logout").then()
				.assertThat().statusCode(200)
				.extract().response().asString();

		System.out.println(response_LogOut);

	}



}
