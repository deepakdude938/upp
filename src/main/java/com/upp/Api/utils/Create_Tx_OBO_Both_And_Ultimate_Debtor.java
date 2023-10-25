package com.upp.Api.utils;

import static io.restassured.RestAssured.given;
import org.junit.Assert;
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

	public String callTxOboAndUltimateDebtor(String TSID) throws Exception {
		String endToEndId = "";
		RestAssured.baseURI = base_url;		
     	Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(pay.createTxOboAndUltimateDebtor(TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("Status code : "+res.getStatusCode());
		
		if (res.getStatusCode() == 400) {
			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			System.err.println(ExpectederrorMessage);
		}
		
		if(TSID.equals("TS100")) {
			Assert.assertEquals(res.getStatusCode(), 200);
			String	response = res.then().extract().asString();	
			JsonPath js = new JsonPath(response);
			endToEndId = js.getString("endToEndId");
		}
				return endToEndId;
	}
}