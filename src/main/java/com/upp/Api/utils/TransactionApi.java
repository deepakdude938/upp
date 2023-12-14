package com.upp.Api.utils;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

public class TransactionApi extends BaseClass {

	public static String base_Url = Property.getProperty("Dev_base_uri");
	public static ExcelReader externalData;
	public Payload pay;
	String endToEndId;
	public static String response = "";

	public TransactionApi() {
		externalData = new ExcelReader();
		pay = new Payload();
	}

	public static String createTransaction(String dealId, String TSID, String participant1, String participant2)
			throws Exception {
		String response = "";
		String endToEndId = "";

		RestAssured.baseURI = base_url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(Payload.createTransaction(dealId, TSID, participant1, participant2)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();

		System.out.println("The Create Ecomm Api response is " + response);

		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 500) {
			String s[] = response.split("}");
			System.out.println("s1   " + s[1]);
			String x[] = s[1].split(":");
			System.out.println("x1   " + x[1]);
			String y[] = x[1].split("\"");
			System.out.println("y1   " + y[1]);
			endToEndId = y[1];
			System.out.println("The End to end is" + endToEndId);
		}
		if (res.getStatusCode() == 200) {
			JsonPath js = new JsonPath(response);
			endToEndId = js.getString("endToEndId");
			System.out.println("The End to end is" + endToEndId);
		}

		return endToEndId;
	}

	public String createTransaction(String TSID) throws Exception {
		RestAssured.baseURI = base_url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(pay.api_createTransaction(TSID)).when()
				.post("transaction/api/transaction");

		String response = res.then().extract().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		endToEndId = js.getString("endToEndId");
		System.out.println("Status code : " + res.getStatusCode());
		Assert.assertTrue(res.getStatusCode() == 200);
		return endToEndId;
	}

	public void deleteTransaction(String TSID) {
		RestAssured.baseURI = base_url;
		String endpoint = "transaction/api/transaction/" + endToEndId + "/rollback";
		System.out.println(base_url);
		System.out.println(endpoint);
		String res = (String) given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).when().delete(endpoint).then().assertThat()
				.statusCode(200).toString();
	}

	public void updateTransactionStatusAsHold(String TSID, String endId) throws Exception, Exception {

		RestAssured.baseURI = base_url + "transaction/api/transaction/";
		String updatedURL = RestAssured.baseURI + endId + "/statusUpdate ";

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1")
				.header("X-SC-TrackingId", "123").body(pay.api_updateTransactionStatusHold(TSID)).when()
				.put(updatedURL);

		response = res.then().extract().asString();

		int statusCode = res.getStatusCode();
		//System.out.println("Response Status Code: " + statusCode);
		
		Assert.assertTrue(res.getStatusCode() == 200);
	}

	public void updateTransactionStatusAsCancel(String TSID, String endId) throws Exception, Exception {

		RestAssured.baseURI = base_url + "transaction/api/transaction/";
		String updatedURL = RestAssured.baseURI + endId + "/statusUpdate ";

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1")
				.header("X-SC-TrackingId", "123").body(pay.api_updateTransactionStatusCancel(TSID)).when()
				.put(updatedURL);

		response = res.then().extract().asString();

		int statusCode = res.getStatusCode();
		System.out.println("Response Status Code: " + statusCode);
		
		Assert.assertTrue(res.getStatusCode() == 200);
	}
	
	public void updateTransactionStatusAsSchedule(String TSID, String endId) throws Exception, Exception {

		RestAssured.baseURI = base_url + "transaction/api/transaction/";
		String updatedURL = RestAssured.baseURI + endId + "/statusUpdate ";

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).header("Version", "v1")
				.header("X-SC-TrackingId", "123").body(pay.api_updateTransactionStatusSchedule(TSID)).when()
				.put(updatedURL);

		response = res.then().extract().asString();

		int statusCode = res.getStatusCode();
		System.out.println("Response Status Code: " + statusCode);
		
		//Assert.assertTrue(res.getStatusCode() == 200);
	}

	public String createTransactionApi(String TSID) throws IOException, Exception {
		RestAssured.baseURI = base_url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(pay.createTransactionApi(TSID)).when()
				.post("transaction/api/transaction");

		String response = res.then().extract().asString();
		JsonPath js = new JsonPath(response);
		endToEndId = js.getString("endToEndId");
		System.out.println("Status code : " + res.getStatusCode());
		Assert.assertTrue(res.getStatusCode() == 200);
		return endToEndId;
	}
}