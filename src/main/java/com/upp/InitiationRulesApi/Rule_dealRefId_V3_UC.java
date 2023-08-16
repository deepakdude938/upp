package com.upp.InitiationRulesApi;

import static io.restassured.RestAssured.given;
import java.time.Duration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pageobjects.Object_Ecommerce;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Rule_dealRefId_V3_UC extends BaseClass {
	public static String response = "";
	public static String offerTo1;

	public static String base_Url = Property.getProperty("Dev_base_uri");

	public static ExcelReader externalData;

	public static void Rule_dealRefId_V3_UC2(String TSID, String dealId, String participant1) throws Exception {
		externalData = new ExcelReader();

		String ActualErrorMessage = externalData.getFieldData(TSID, "Initiation Rules", "Response Message");

		RestAssured.baseURI = base_url;

		Response res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(Payload.rule_DealRefId_V3_UC(TSID, dealId, participant1)).when()
				.post("transaction/api/transaction");

		response = res.then().extract().asString();
		System.out.println("The Rule_Non_OBO response is " + response);

		System.out.println("the status code is" + res.getStatusCode());

		if (res.getStatusCode() == 400) {

			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			Assert.assertEquals(ExpectederrorMessage, ActualErrorMessage);
		}
	}
}
