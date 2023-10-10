package com.upp.pagemodules.Account;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import com.upp.InitiationRulesApi.Payload;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AccountAmendment extends BaseClass{

	public static ExcelReader externalData;
	public Payload pay;
	public static String response = "";
	
	public AccountAmendment() {
		externalData = new ExcelReader();
		pay = new Payload();
	}

	public void callAccountAmendment(String TSID) throws InvalidFormatException, IOException {
	
		RestAssured.baseURI = base_url;		
     	Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(pay.accountAmendment(TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("the status code is" + res.getStatusCode());

	}
}
