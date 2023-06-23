package com.upp.InitiationRulesApi;

import static io.restassured.RestAssured.given;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
		pay = new Payload();
	}

	public void runRule_dealRefId_V3_UD(String TSID, String dealId) throws Exception, IOException {

		String ActualErrorMessage = externalData.getFieldData(TSID, "Initiation Rules", "Response Message");
		String ActualErrorCode = externalData.getFieldData(TSID, "Initiation Rules", "Response Error Code");

		RestAssured.baseURI = base_Url;
		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken).body(pay.rule_ParticipantOBO(TSID, dealId)).when()
				.post("transaction/api/transaction");
		response = res.then().extract().asString();

		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(response);
		JSONObject jsonObject = (JSONObject) object;
		JSONArray er = (JSONArray) jsonObject.get("errors");
		Assert.assertEquals(res.getStatusCode(), 400);
		int errorCount = 0;
		
		for (int i = 0; i < er.size(); i++) {
			JSONObject obj = (JSONObject) er.get(i);
			String code = (String) obj.get("code");
			String message = (String) obj.get("message");
			if (ActualErrorCode.equalsIgnoreCase(code) && ActualErrorMessage.equalsIgnoreCase(message)
					&& res.getStatusCode() == 400) {
				errorCount++;
				break;
			}
		}
		if (errorCount == 0) {
			Assert.assertTrue(false);
		}
	}
}
