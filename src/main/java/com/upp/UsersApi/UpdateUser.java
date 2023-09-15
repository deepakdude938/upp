package com.upp.UsersApi;

import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import com.upp.UsersApi.Payload;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

public class UpdateUser extends BaseClass {
	public static String response = "";

	public static ExcelReader externalData;

	public static String updateUsers(String TSID, Properties prop) throws Exception {

		externalData = new ExcelReader();
		String newName;

		RestAssured.baseURI = base_url + "idm/api/idam/users/";

		System.out.println(base_url);
		String excelFilePath = System.getProperty("user.dir") + "//src//main//resources//UserList.xlsx";

		String filePath = System.getProperty("user.dir") + "//src//test//resources//usercount.properties";

		try (FileInputStream fis = new FileInputStream(filePath)) {
			Properties properties = new Properties();
			properties.load(fis);

			// Get the current count from the properties file
			usercount2 = Integer.parseInt(properties.getProperty("usercount1", "0"));
			System.out.println("Count = " + usercount2);
			// Increment the count
			// usercount2++;
		} catch (IOException e) {
			e.printStackTrace();
		}

		newName = externalData.getFieldData(excelFilePath, "Sheet", "UserName", usercount2);

		String updatedURL = RestAssured.baseURI + newName;

		System.out.println("updated url" + updatedURL);

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1")
				.header("X-SC-TrackingId", "123").body(Payload.UpdateUser(TSID, prop)).when().put(updatedURL);

		response = res.then().extract().asString();

		int statusCode = res.getStatusCode();
		// Replace "keyName" with the actual key in the JSON response

		System.out.println("Response Status Code: " + statusCode);
		// Assert.assertEquals(statusCode, 200);
//		
		return newName;

	}

	
	public static String updateUsersAttribute(String TSID, Properties prop) throws Exception {

		externalData = new ExcelReader();
		String newName;

		RestAssured.baseURI = base_url + "idm/api/idam/users/";

		System.out.println(base_url);
		String excelFilePath = System.getProperty("user.dir") + "//src//main//resources//UserList.xlsx";

		String filePath = System.getProperty("user.dir") + "//src//test//resources//usercount.properties";

		try (FileInputStream fis = new FileInputStream(filePath)) {
			Properties properties = new Properties();
			properties.load(fis);

			// Get the current count from the properties file
			usercount2 = Integer.parseInt(properties.getProperty("usercount1", "0"));
			System.out.println("Count = " + usercount2);
			// Increment the count
			// usercount2++;
		} catch (IOException e) {
			e.printStackTrace();
		}

		newName = externalData.getFieldData(excelFilePath, "Sheet", "UserName", usercount2);

		String updatedURL = RestAssured.baseURI + newName;

		System.out.println("updated url" + updatedURL);

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1")
				.header("X-SC-TrackingId", "123").body(Payload.UpdateUser(TSID, prop)).when().put(updatedURL);

		response = res.then().extract().asString();

		int statusCode = res.getStatusCode();
		// Replace "keyName" with the actual key in the JSON response

		System.out.println("Response Status Code: " + statusCode);
		// Assert.assertEquals(statusCode, 200);
//		
		return newName;

	}
}