package com.upp.Api.utils;

import static io.restassured.RestAssured.*;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.Property;

public class TransactionApi extends BaseClass {

	public static String base_Url = Property.getProperty("Dev_base_uri");

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


}