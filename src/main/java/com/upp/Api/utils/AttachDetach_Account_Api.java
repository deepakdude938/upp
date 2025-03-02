package com.upp.Api.utils;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;

import com.upp.InitiationRulesApi.Payload;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_Parties;
import com.upp.utils.ExcelReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AttachDetach_Account_Api extends BaseClass{

	public static ExcelReader externalData;
	public Payload pay;
	public static String response = "";
	public Object_Deal od;
	public Object_Parties op;
	
	
	public AttachDetach_Account_Api(){
		externalData = new ExcelReader();
		pay = new Payload();
		od=new Object_Deal();
		op=new Object_Parties();
	}

	public void attachAccountApi(String TSID) throws Exception {
		
//		String ActualErrorMessage = externalData.getFieldData(TSID, "API Testcases", "Response Message");
//		String ActualErrorCode = externalData.getFieldData(TSID, "Initiation Rules", "Response Error Code");
		String participantId = externalData.getFieldData(TSID, "Party", "Participant Id");
		RestAssured.baseURI = base_url;
		String endpoint = "/mdm/api/party/";
		String endpoint1 = "/attachAccount";
		String finalEndpoint= endpoint+dealId+"/"+dealId+"-"+participantId+endpoint1;
		System.out.println(finalEndpoint);
		System.out.println(pay.api_AttachAccount(TSID));
		String res = given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(pay.api_AttachAccount(TSID))
				.when()
				.post(finalEndpoint).then().extract().asString();
		System.out.println(res);
		
//			String	res1 = (String) given().header("Content-Type", "application/json")
//				.header("Authorization", LoginAPI_UPP.authToken)
//				.body(pay.api_AttachAccount(TSID))
//				.when()
//				.post(finalEndpoint)
//				.then()
//				.assertThat().statusCode(200).toString();
		
	}

	public void verifyVirtualAccountPresentInAccount(String tSID) throws Exception {
		od.account_AccountTab.click();
		Thread.sleep(3000);
		List<WebElement> ol=	od.account_AccountNoList;
		ArrayList<String> accountNoList= new ArrayList<>();
		for(WebElement q : ol) {
			String account = q.getText().trim();
			System.out.println(account);
			accountNoList.add(account);
		}
		Assert.assertTrue(accountNoList.contains(virtual_Account_Number));
	}

	public void verifyVirtualAccountPresentInParty(String TSID) throws InterruptedException, MalformedURLException {
		op.parties_PartyTab.click();
		Thread.sleep(1000);
		try {
		op.parties_EditParty.click();
		}
		catch(Exception e) {
			handleElementClickException(op.parties_EditParty);
		}
		op.parties_Ecommerce_Config_Tab.click();
		
		
		Thread.sleep(2000);
		System.out.println(virtual_Account_Number);
//		WebElement s = driver.findElement(By.xpath("//span[@title='"+virtual_Account_Number+"']/../../..//div[@role='presentation']//div//div/div[contains(@class,'ag-checkbox-input-wrapper')]"));
		WebElement s = driver.findElement(By.xpath("//span[normalize-space()='"+virtual_Account_Number+"']/../..//input"));
//		applyExplicitWaitsUntilElementClickable(s, Duration.ofSeconds(15));
		String checked = s.getAttribute("aria-label");
		System.out.println(checked);
		if(checked.contains("Press Space to toggle row selection (checked)")) {
			Assert.assertTrue("Account no is checked", true);
		}
		else {
			Assert.assertTrue("Account no is not checked", false);
		}
	}

	public void detachAccountApi(String TSID) throws Exception, IOException {
//		https://sit.upp.datanimbus.com/mdm/api/party/REF1691988547939/REF1691988547939-debtorparty/detachAccount
		String participantId = externalData.getFieldData(TSID, "Party", "Participant Id");
		RestAssured.baseURI = base_url;
		String endpoint = "/mdm/api/party/";
		String endpoint1 = "/detachAccount";
		String finalEndpoint= endpoint+dealId+"/"+dealId+"-"+participantId+endpoint1;
		
		String res = (String) given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(pay.api_DetachAccount(TSID))
				.when()
				.put(finalEndpoint)
				.then()
				.assertThat().statusCode(200).toString();
	}

	public void verifyVirtualAccountisDisableInParty(String tSID) throws Exception {
		
		op.parties_PartyTab.click();
		try {
		op.parties_EditParty.click();
		}
		catch(Exception e) {
			handleElementClickException(op.parties_EditParty);
		}
		op.parties_Ecommerce_Config_Tab.click();
		
		
		Thread.sleep(2000);
//		WebElement s = driver.findElement(By.xpath("//span[@title='"+virtual_Account_Number+"']/../../..//div[@role='presentation']//div//div/div[contains(@class,'ag-checkbox-input-wrapper')]"));
		WebElement s = driver.findElement(By.xpath("//span[normalize-space()='"+virtual_Account_Number+"']/../..//input"));
//		applyExplicitWaitsUntilElementClickable(s, Duration.ofSeconds(15));
		String checked = s.getAttribute("aria-label");
		System.out.println(checked);
		if(checked.contains("Press Space to toggle row selection (unchecked)")) {
			Assert.assertTrue("Account no is not checked", true);
		}
		else {
			Assert.assertTrue("Account no is checked", false);
		}
	}

	public void attachPhysicalAccountApi(String TSID) throws Exception, IOException {
		
		String participantId = externalData.getFieldData(TSID, "Party", "Participant Id");
		RestAssured.baseURI = base_url;
		String endpoint = "/mdm/api/party/";
		String endpoint1 = "/attachAccount";
		String finalEndpoint= endpoint+dealId+"/"+dealId+"-"+participantId+endpoint1;
		System.out.println(finalEndpoint);
		pay.api_AttachPhysicalAccount(TSID);
	
		String res = (String) given().header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_UPP.authToken)
				.body(pay.api_AttachPhysicalAccount(TSID))
				.when()
				.post(finalEndpoint)
				.then()
				.assertThat().statusCode(200).toString();
	}

	public void verifyPhysicalAccountPresentInAccount(String tSID) throws Exception {
		
		od.account_AccountTab.click();
		Thread.sleep(3000);
		List<WebElement> ol=	od.account_AccountNoList;
		ArrayList<String> accountNoList= new ArrayList<>();
		for(WebElement q : ol) {
			String account = q.getText().trim();
			System.out.println(account);
			accountNoList.add(account);
		}
		Assert.assertTrue(accountNoList.contains(physical_Account_Number));
	}

	public void verifyPhysicalAccountPresentInParty(String tSID) throws Exception {
		op.parties_PartyTab.click();
		try {
		op.parties_EditParty.click();
		}
	catch(Exception e) {
		handleElementClickException(op.parties_EditParty);
	}
		op.parties_Ecommerce_Config_Tab.click();
		Thread.sleep(2000);
		System.out.println(physical_Account_Number);
//		WebElement s = driver.findElement(By.xpath("//span[@title='"+physical_Account_Number+"']/../../..//div[@role='presentation']//div//span//div"));
//		String checked = s.getAttribute("class");
//		if(checked.contains("ag-hidden")) {
//			Assert.assertTrue("Account no is not checked", false);
//		}
		
		WebElement s = driver.findElement(By.xpath("//span[normalize-space()='"+physical_Account_Number+"']/../..//input"));
//		applyExplicitWaitsUntilElementClickable(s, Duration.ofSeconds(15));
		String checked = s.getAttribute("aria-label");
		System.out.println(checked);
		if(checked.contains("Press Space to toggle row selection (checked)")) {
			Assert.assertTrue("Account no is checked", true);
		}
		else {
			Assert.assertTrue("Account no is not checked", false);
		}
	}
}
