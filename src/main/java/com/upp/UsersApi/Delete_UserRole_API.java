package com.upp.UsersApi;

import static io.restassured.RestAssured.*;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

public class Delete_UserRole_API extends BaseClass {

	public static ExcelReader externalData;
	public static String deleteRole(String TSID) throws Exception {
		
		externalData = new ExcelReader();
		String Username = externalData.getFieldData(TSID, "Users", "UserName");
		
		//String Username= externalData.getFieldData(TSID, "API Testcases", "UserName");
		String deletePath="idm/api/idam/users/"+Username+"/role";
		System.out.println(deletePath);
		RestAssured.baseURI = base_url;
		String response = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1").header("X-SC-TrackingId", "123").body(Payload.deleteRole(TSID)).when().delete(deletePath).then()
				//.assertThat().statusCode(200)
				.extract().response().asString();
		
		System.out.println("The Delete User Api response is " + response);
		JsonPath js = new JsonPath(response);
		String Actualmessage=js.getString("message");
		String Expectedmessage="User deleted successfully : "+Username;
        System.out.println("The Error Message is " + Actualmessage);
       // Assert.assertEquals(Actualmessage,Expectedmessage);
        
         return Username;

	}

	

}