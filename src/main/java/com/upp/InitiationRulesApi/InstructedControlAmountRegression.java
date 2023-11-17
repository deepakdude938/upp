package com.upp.InitiationRulesApi;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.DateUtils;
import com.upp.utils.ExcelReader;
import com.upp.utils.Property;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class InstructedControlAmountRegression extends BaseClass{
	public static String response = "";
//	public static String base_Url = Property.getProperty("Dev_base_uri");
	public static ExcelReader externalData;
	public Payload pay;

	
	public InstructedControlAmountRegression() {
		externalData = new ExcelReader();
		pay = new Payload();
	}


	public void instructedControlAmount(String TSID) throws Exception {
		int errorCount = 0;
		ArrayList<Integer> errorRowNumber= new ArrayList<>();
		String excelFilePath = System.getProperty("user.dir")+Constants.InstructedControlAmount_FILE_PATH;
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");
		int rowCount = externalData.getRowCount(excelFilePath, "Positive");
		System.out.println("debit accnt is "+debitAccount);
		String tomorow = DateUtils.getDate(1) + "T11:28:00Z";
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.accountNumber", debitAccount);
	
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", tomorow);
		jsonContext.set("$.creditTransactionInfo[1].requestedExecutionOn", tomorow);
		jsonContext.set("$.creditTransactionInfo[2].requestedExecutionOn", tomorow);
		jsonContext.set("$.creditTransactionInfo[3].requestedExecutionOn", tomorow);
		  
			for (int rowNumber = 1; rowNumber < rowCount; rowNumber++) {
				
				String payload = pay.rule_InstructedControlAmountRegression(excelFilePath,TSID, payLoadString,rowNumber,jsonContext);
					RestAssured.baseURI = base_url;
					Response res = 
							given()
								.header("Content-Type", "application/json")
								.header("Authorization", LoginAPI_UPP.authToken)
								.body(payload)
							.when()
								.post("transaction/api/transaction");
					response = res.then().extract().asString();

					try {
					Assert.assertEquals(res.getStatusCode(), 200);
					System.out.println("-------------------------"+(rowNumber+1)+"-----------------------------");
					}
					catch(AssertionError e) {
						JSONParser jsonParser = new JSONParser();
						Object object = jsonParser.parse(response);
						JSONObject jsonObject = (JSONObject) object;
						JSONArray er = (JSONArray) jsonObject.get("errors");
						System.err.println("-------------------------"+(rowNumber+1)+"-----------------------------");
						System.out.println(payload);
						System.out.println("fragA = "+Float.parseFloat(externalData.getFieldData(excelFilePath, "Positive", "fragA",rowNumber)));
						System.out.println("fragB = "+Float.parseFloat(externalData.getFieldData(excelFilePath, "Positive", "fragB",rowNumber)));
						System.out.println("fragC = "+Float.parseFloat(externalData.getFieldData(excelFilePath, "Positive", "fragC",rowNumber)));
						System.out.println("fragD = "+Float.parseFloat(externalData.getFieldData(excelFilePath, "Positive", "fragD",rowNumber)));
						System.out.println("instructedControlSum = "+Float.parseFloat(externalData.getFieldData(excelFilePath, "Positive", "instructedControlSum",rowNumber)));
						JSONObject jsonObject1 = (JSONObject) er.get(0);
						String err= (String) jsonObject1.get("message");
						String err1= (String) jsonObject1.get("code");
						System.err.println(err);
						System.err.println(err1);
						errorRowNumber.add(rowNumber+1);
						errorCount++;
					}
					
			}
			if(errorRowNumber.size()>0) {
				System.out.println(errorRowNumber.size());
				System.err.println(errorRowNumber);
			}
			Assert.assertTrue(errorCount==0);
	}
}
