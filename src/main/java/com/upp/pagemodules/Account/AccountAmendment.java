package com.upp.pagemodules.Account;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.upp.InitiationRulesApi.Payload;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;
import com.upp.utils.ExcelReader;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AccountAmendment extends BaseClass{

	public static ExcelReader externalData;
	public Payload pay;
	public static String response = "";
	public static Object_Parties op;
	public static Object_NewDeal od;
	
	public AccountAmendment() {
		externalData = new ExcelReader();
		pay = new Payload();
		od = new Object_NewDeal();
		op = new Object_Parties();
	}

	public void callAccountAmendment(String TSID) throws InvalidFormatException, IOException {
//		https://sit.upp.datanimbus.com/mdm/api/party/REF1698644612204-RRRMarketPlace
//		https://qa.upp.datanimbus.com/mdm/api/party/REF1698654419560-RRRMarketPlace
		System.out.println(base_url);
		String url = "mdm/api/party/"+dealId+"-RRRMarketPlace";
		System.out.println(url);
		RestAssured.baseURI = base_url;		
     	Response res = given()
     			.header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(pay.accountAmendment(TSID))
				.when()
				.put(url);

		response = res.then().extract().asString();
		System.out.println(response);
		System.out.println("the status code is " + res.getStatusCode());
		if (res.getStatusCode() == 400) {
			String errorMessage = externalData.getFieldData(TSID, "API Testcases", "Response Message");
			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			System.out.println(ExpectederrorMessage);
			Assert.assertEquals(ExpectederrorMessage, errorMessage);
		}
	}

	public void call_AccountAmmendment_UpdateCreditorLookUpKeys_Api(String TSID) throws Exception, IOException {
		System.out.println(base_url);
		String url = "mdm/api/party/"+dealId+"-RRRMarketPlace";
		System.out.println(url);
		RestAssured.baseURI = base_url;		
     	Response res = given()
     			.header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(pay.accountAmendment_UpdateCreditorLookUpKeys(TSID))
				.when()
				.put(url);

		response = res.then().extract().asString();
		System.out.println("the status code is " + res.getStatusCode());
	}

	public void callAccountOffboardApi(String TSID) throws Exception {
		String url = "mdm/api/party/"+dealId+"-RRRMarketPlace";
		System.out.println(url);
		RestAssured.baseURI = base_url;		
     	Response res = given()
     			.header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(pay.accountAmendment_AccountOffboard(TSID))
				.when()
				.put(url);

		response = res.then().extract().asString();
		System.out.println("the status code is " + res.getStatusCode());
		if(TSID.equals("TS123")||TSID.equals("TS123_1")) {
				String errorMessage = externalData.getFieldData(TSID, "API Testcases", "Response Message");
				JsonPath js = new JsonPath(response);
				String ExpectederrorMessage = js.getString("errors[0].message");
				System.out.println(ExpectederrorMessage);
				Assert.assertEquals(ExpectederrorMessage, errorMessage);
				Assert.assertEquals(res.getStatusCode(), 400);
		}
	}

	public void VerifyAccountIsOffBoarded() throws Exception {
		applyExplicitWaitsUntilElementClickable(op.parties_PartyTab, Duration.ofSeconds(20));
		op.parties_PartyTab.click();
		op.parties_editPartyButton.click();
		try {
			od.parties_AccountsTab.click();
		}
		catch(Exception e) {
			handleElementClickException(od.parties_AccountsTab);
		}
		Thread.sleep(2000);
		ArrayList<String> accounts = new ArrayList<>();
		for(WebElement i : od.parties_AccountsList) {
			String a = i.getText();
			System.out.println(a);
			accounts.add(a);
			
		}
		Assert.assertTrue(!accounts.contains("SBI98765"));
		Assert.assertTrue(accounts.contains("Acc3"));
		
	}

	public void callNewAccountwithSameCreditorLookUpKeys_Api(String TSID) throws Exception {
		
		String url = "mdm/api/party/"+dealId+"-RRRMarketPlace";
		System.out.println(url);
		RestAssured.baseURI = base_url;		
     	Response res = given()
     			.header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(pay.newAccountwithSameCreditorLookUpKeys(TSID))
				.when()
				.put(url);

		response = res.then().extract().asString();
		System.out.println("the status code is " + res.getStatusCode());
		if (res.getStatusCode() == 400) {
			String errorMessage = externalData.getFieldData(TSID, "API Testcases", "Response Message");
			JsonPath js = new JsonPath(response);
			String ExpectederrorMessage = js.getString("errors[0].message");
			System.out.println(ExpectederrorMessage);
			Assert.assertEquals(ExpectederrorMessage, errorMessage);
		}
	}

	public void Account_Ammendment_NewAccount2_API(String TSID) throws Exception{
		
		String url = "mdm/api/party/"+dealId+"-RRRMarketPlace";
		System.out.println(url);
		RestAssured.baseURI = base_url;		
     	Response res = given()
     			.header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(pay.newAccount2(TSID))
				.when()
				.put(url);

		response = res.then().extract().asString();
		System.out.println("the status code is " + res.getStatusCode());
	}

	public void Account_Ammendment_NewFourthAccount_API(String TSID) throws IOException, Exception {
		
		String url = "mdm/api/party/"+dealId+"-RRRMarketPlace";
		System.out.println(url);
		RestAssured.baseURI = base_url;		
     	Response res = given()
     			.header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(pay.newFourthAccount(TSID))
				.when()
				.put(url);

		response = res.then().extract().asString();
		System.out.println("the status code is " + res.getStatusCode());
	}
}
