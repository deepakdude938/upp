package com.upp.InitiationRulesApi;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Rule_Map_VA_PA_ParticipantId extends BaseClass{

	public static String response = "";
	public ExcelReader externalData;

	public void rule_Map_VA_PA_ParticipantId_Virtual_Api(String dealId,String TSID,String virtualaccount) throws Exception {

		externalData = new ExcelReader();

		String ActualErrorMessage = externalData.getFieldData(TSID, "Initiation Rules", "Response Message");
		RestAssured.baseURI = base_url;
		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.Rule_Non_OBO_Virtual(dealId,TSID,virtualaccount)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println( response);
		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 400) {
			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			Assert.assertEquals(ExpectederrorMessage, ActualErrorMessage);

		}
	}
}
