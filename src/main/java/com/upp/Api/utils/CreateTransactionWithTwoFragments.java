package com.upp.Api.utils;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import com.upp.InitiationRulesApi.Payload;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_Parties;
import com.upp.utils.ExcelReader;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateTransactionWithTwoFragments extends BaseClass{
	public static ExcelReader externalData;
	public Payload pay;
	public static String response = "";
	
	public CreateTransactionWithTwoFragments(){
		externalData = new ExcelReader();
		pay = new Payload();
	}

	public void callTxnWithTwoFragments(String TSID) throws Exception, IOException {
		
		String ActualErrorMessage = externalData.getFieldData(TSID, "API Testcases", "Response Message");
		RestAssured.baseURI = base_url;		
     	Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(pay.createTransaction(TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 400) {
			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			System.out.println(ExpectederrorMessage);
			Assert.assertEquals(ExpectederrorMessage, ActualErrorMessage);
		}
	}
}
