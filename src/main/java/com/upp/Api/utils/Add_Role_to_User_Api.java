package com.upp.Api.utils;

import static io.restassured.RestAssured.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

public class Add_Role_to_User_Api extends BaseClass {

	public static ExcelReader externalData;
	public static void Add_Role_To_User_API(String TSID) throws Exception {
		externalData = new ExcelReader();
		String Username=externalData.getFieldData(TSID,"API Testcases","UserName");
		String putPath="idm/api/idam/users/"+Username+"/role";
		RestAssured.baseURI = base_url;
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1")
				.header("X-SC-TrackingId", "123").body(Payload.Add_Role_To_User_Api(TSID)).when().put(putPath).then().assertThat()
				.statusCode(200).extract().response().asString();
		System.out.println("The Add_Role_To_User_API response is " + response);
		
		JsonPath js = new JsonPath(response);
		String Actualmessage=js.getString("message");
		System.out.println("Message is:"+Actualmessage);
		Assert.assertEquals(Actualmessage,"User roles updated successfully");
		
		
		
	

	}

}