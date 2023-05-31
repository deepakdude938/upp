package com.upp.InitiationRulesApi;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.testng.Assert;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Rule_dealRefId_V3_UD {

	public static String response = "";
	public static String base_Url = Property.getProperty("Dev_base_uri");
	public static ExcelReader externalData;
	public Payload pay;

	public Rule_dealRefId_V3_UD() {
		externalData = new ExcelReader();
		pay =new Payload();
	}

	public void runRule_dealRefId_V3_UD(String TSID, String dealId) throws Exception, IOException {
	
		String ActualErrorMessage = externalData.getFieldData(TSID, "Initiation Rules", "Response Message");

		RestAssured.baseURI = base_Url;
     	Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(pay.rule_ParticipantOBO(TSID,dealId)).when()
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
