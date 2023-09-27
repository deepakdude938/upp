package com.upp.Api.utils;

import static io.restassured.RestAssured.given;
import com.upp.InitiationRulesApi.Payload;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Create_Tx_OBO_Both_And_Ultimate_Debtor extends BaseClass{
	public static ExcelReader externalData;
	public Payload pay;
	public static String response = "";
	
	public Create_Tx_OBO_Both_And_Ultimate_Debtor(){
		externalData = new ExcelReader();
		pay = new Payload();
	}

	public void callTxOboAndUltimateDebtor(String TSID) throws Exception {
		
		RestAssured.baseURI = base_url;		
     	Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(pay.createTxOboAndUltimateDebtor(TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 400) {
			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			System.out.println(ExpectederrorMessage);
		}
	}
}
