package com.upp.Api.utils;

import static io.restassured.RestAssured.*;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.Property;

public class Get_User_By_UserName_API extends BaseClass {


	public static void getUserByUserName() throws Exception {

		RestAssured.baseURI = base_url;
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1").header("X-SC-TrackingId", "123").when().get("idm/api/idam/users/vaishnavi").then()
				.assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println("The get User Api response is " + response);
		JsonPath js = new JsonPath(response);
         String id=js.getString("_id");
         String status=js.getString("status");
         String countryId=js.getString("countryId");
         System.out.println("The id is " + id);
         System.out.println("The status is " + status);
         System.out.println("The CountryId is " + countryId);
         Assert.assertEquals(id,"vaishnavi");
         Assert.assertEquals(status,"ACTIVE");
         Assert.assertEquals(countryId,"IND");

	}

	

}