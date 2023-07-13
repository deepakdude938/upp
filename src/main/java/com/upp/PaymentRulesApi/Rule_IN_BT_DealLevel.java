package com.upp.PaymentRulesApi;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

public class Rule_IN_BT_DealLevel {
	public static String response = "";

	public static String base_Url = Property.getProperty("Dev_base_uri");

	public static ExcelReader externalData;
	
	public static String endToEndId;
	
	public static SSHConnection ssh;

	public static String Rule_IN_BT_dealLevel(String dealId, String TSID) throws Exception {

		externalData = new ExcelReader();


		RestAssured.baseURI = base_Url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(Payload.Rule_IN_BT_dealLevel(dealId, TSID)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("The Rule_IN_BT_DealLevel response is " + response);

		System.out.println("the status code is" + res.getStatusCode());
		
		if (res.getStatusCode() == 200) {
			JsonPath js = new JsonPath(response);
			endToEndId = js.getString("endToEndId");
			System.out.println("The End to end is" + endToEndId);
		}

		
		return endToEndId;
	}
	
	public void verify_Rule_IN_BT_Deal_Level_PainFile(String batchId)
	{
		ssh=new SSHConnection();
		ArrayList<String> tagNames = new ArrayList<>(Arrays.asList("Cd","ChrgBr"));
		
		ArrayList<String> ActualResult=ssh.getPainFileDetails(batchId,tagNames);
		ArrayList<String> ExcpectedResult = new ArrayList<>(Arrays.asList("BKTR","SHAR"));

		Assert.assertEquals(ActualResult,ExcpectedResult);
		
	}

}