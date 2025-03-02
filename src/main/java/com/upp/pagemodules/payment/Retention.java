package com.upp.pagemodules.payment;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Random;
import com.upp.base.BaseClass;
import com.upp.odp.utils.Payload;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.Property;
import com.upp.utils.ScrollTypes;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Retention extends BaseClass{
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	String sourceAccountno;
	public JavascriptClick jsClick;
	public Retention() {
		
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		jsClick=new JavascriptClick(driver);
		
	}

	public void createRetention(String TSID) throws Exception {
		SoftAssert a = new SoftAssert();
	     sourceAccountno=DealPage.sourceAccountNo;
		if((TSID.equalsIgnoreCase("TS122")))
		{
			sourceAccountno=DealPage.AccountNo1;
		}
		applyExplicitWaitsUntilElementClickable(od.payments_ScheduledInstructionIcon, Duration.ofSeconds(5));
		try {
			od.payments_ScheduledInstructionIcon.click();
		} catch (Exception e) {
			handleElementClickException(od.payments_ScheduledInstructionIcon);
		}
		od.payments_GetStarted.click();
		String InstructionType = externalData.getFieldData(TSID, "Scheduled", "Select Instruction Type");
		By InstructionButton = By
				.xpath("//div[@class='ui-align-left ui-relative ui-inline-block ui-label'][normalize-space()='"
						+ InstructionType + "']");
		applyExplicitWaitsUntilElementVisible(InstructionButton, 10);
		driver.findElement(InstructionButton).click();
		applyExplicitWaitsUntilElementClickable(od.payments_Proceed, Duration.ofSeconds(5));
		od.payments_Proceed.click();
		
		od.retention_basicDetils_Name.clear();
		od.retention_basicDetils_Name.sendKeys(externalData.getFieldData(TSID, "PaymentRetention", "Name"));
		dropdown.selectByVisibleText(od.retention_Purpose, externalData.getFieldData(TSID, "PaymentRetention", "Purpose"));
		od.payments_SourceAccount.sendKeys(sourceAccountno);
		Thread.sleep(1000);
		By sourceaccountselect = By.xpath("//div[contains(text(),'" + sourceAccountno + "')]");
		driver.findElement(sourceaccountselect).click();
		
		String multipleRetention = externalData.getFieldData(TSID, "PaymentRetention", "Multiple Retention");
		if(multipleRetention.equalsIgnoreCase("No")) {
			
			dropdown.selectByVisibleText(od.retention_SpecifyAmountAs, externalData.getFieldData(TSID, "PaymentRetention", "Specify amount as"));
			od.retention_SpecifyAmountValue.clear();
			System.out.println(externalData.getFieldData(TSID, "PaymentRetention", "Amount"));
			od.retention_SpecifyAmountValue.sendKeys(externalData.getFieldData(TSID, "PaymentRetention", "Amount"));
			
		}
		else {
			od.retention_MultipleRetention.click();
		}
		od.retention_nextArrowIcon.click();
		applyExplicitWaitsUntilElementClickable(od.payments_ExecutionDate, Duration.ofSeconds(5));
		od.payments_ExecutionDate.click();
		String	day= DateUtils.getDay();
    	By excecutionDay = By.xpath("//td[contains(@class,'ui-day') and not(contains(@class,'ui-calendar-invalid')) and not(contains(@class,'ui-calendar-outFocus')) and normalize-space()='"+day+"']");
    	applyExplicitWaitsUntilElementVisible(excecutionDay, 5);
		driver.findElement(excecutionDay).click();
		System.out.println(externalData.getFieldData(TSID, "PaymentRetention", "Schedule At"));
		dropdown.selectByVisibleText(od.retention_ScheduleAt,externalData.getFieldData(TSID, "PaymentRetention", "Schedule At"));
		System.out.println(externalData.getFieldData(TSID, "Scheduled", "Holiday Action"));
		dropdown.selectByVisibleText(od.payments_HolidayAction,externalData.getFieldData(TSID, "Scheduled", "Holiday Action"));

		String time = DateUtils.getTimeAfterMins(5);
		od.retention_ScheduleTime.clear();
		od.retention_ScheduleTime.sendKeys(time);
		Thread.sleep(1000);
		dropdown.selectByValue(od.retention_SelectTimezone, "Asia/Calcutta")	;
		Thread.sleep(1000);
		dropdown.selectByVisibleText(od.retention_Execute1,"On scheduled date");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		if(isWebElementDisplayed(od.retention_ScheduleNoOfDays)) {
			od.retention_ScheduleNoOfDays.sendKeys("3");
		}
		
		od.retention_ScheduleNextButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		
		if(multipleRetention.equalsIgnoreCase("Yes")) {
			od.retention_Amount.sendKeys("500");
			od.retention_Narration.sendKeys("SubInstruction 1");
			od.retention_AddSubInstruction.click();
			applyExplicitWaitsUntilElementClickable(od.retention_SubInstructionRow, Duration.ofSeconds(30));
			Thread.sleep(2000);
			
			od.retention_Amount.sendKeys("1000");
			od.retention_Narration.sendKeys("SubInstruction 2");
			od.retention_AddSubInstruction.click();
		}
		applyExplicitWaitsUntilElementClickable(od.retention_SubInstructionNextButton, Duration.ofSeconds(10));
		od.retention_SubInstructionNextButton.click();
		applyExplicitWaitsUntilElementClickable(od.retention_RetryNextButton, Duration.ofSeconds(10));
		od.retention_RetryNextButton.click();
		String notification = externalData.getFieldData(TSID, "PaymentRetention", "Notification Enable");
		if(notification.equals("Y") || notification.equals("Yes")) {
			click(od.retention_NotificationAlert);
		}
		od.retention_Summary.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.retention_SummaryPurpose, Duration.ofSeconds(10));
		String purpose = od.retention_SummaryPurpose.getText();
		a.assertEquals( "Retention",purpose);
		od.retention_BackButton.click();
		a.assertAll();

	}

	public void createJsonFile(String TSID) throws Exception {

		HashMap odpRecord = new HashMap<>();
		odpRecord.put("_id", TSID);
		odpRecord.put("originTcId", TSID);
		odpRecord.put("dealId", dealID_Assertion);
		odpRecord.put("dealRefId", dealId);

		ObjectMapper mapper = new ObjectMapper();
		 HashMap jsonMap1 = new HashMap();
		 odpRecordJson = new ObjectMapper().writeValueAsString(odpRecord);
		  System.out.println(odpRecordJson);
	}

	public void fetchInputsFromODP(String TSID) throws Exception {
		

		
		String base_Url = Property.getProperty("Odp_base_uri");

				RestAssured.baseURI = base_Url;
				String responseLogin = given()
						.header("Content-Type", "application/json")
						.body(Payload.Login()).when()
						.post("api/a/rbac/login").then()
						.assertThat().statusCode(200)
						.extract()
						.response().asString();

				JsonPath js = new JsonPath(responseLogin);
				String token = js.getString("token");
				String authToken = "JWT " + token;
				

				Response res = given()
						.header("Content-Type", "application/json")
						.header("Authorization", authToken).when()
						.get("api/c/acache/testAutomationAssertions/"+TSID);
				int statusCode = res.getStatusCode();
				
				RestAssured.baseURI = base_Url;
				String response_account = given()
						.header("Content-Type", "application/json")
						.header("Authorization", authToken)
						.when()
						.get("api/c/acache/testAutomationAssertions/"+TSID).then()
						.assertThat()
						.statusCode(200).extract()
						.response().asString();
				System.out.println("-----------");
				System.out.println(response_account);
				System.out.println("-----------");
				JsonPath js1 = new JsonPath(response_account);
				dealId = js1.getString("dealRefId");
				String dealId = js1.getString("dealId");
			
				System.out.println("Deal Ref Id is Assertion"+super.dealId);
				System.out.println("Deal Id is Assertion"+dealId);
				
				RestAssured.baseURI = base_Url;
				String response_LogOut = given()
						.header("Content-Type", "application/json")
						.header("Authorization", authToken)
						.when().delete("api/a/rbac/logout").then()
						.assertThat().statusCode(200)
						.extract().response().asString();
	}
	
	public void createRetentionSurplus(String TSID) throws Exception, IOException{

//		applyExplicitWaitsUntilElementClickable(od.retention_Tab, Duration.ofSeconds(5));
//		od.retention_Tab.click();
		click(od.retention_Tab);
		applyExplicitWaitsUntilElementClickable(od.retention_Purpose, Duration.ofSeconds(10));
		dropdown.selectByVisibleText(od.retention_Purpose, "Retention");
		sourceAccountno=DealPage.sourceAccountNo;
		od.payments_SourceAccount.sendKeys(sourceAccountno);
		Thread.sleep(1000);
		System.out.println(sourceAccountno);
		By sourceaccountselect = By.xpath("//div[contains(text(),'" + sourceAccountno + "')]");
		driver.findElement(sourceaccountselect).click();
		od.retention_Remarks.sendKeys(externalData.getFieldData(TSID,"PaymentRetention","Remarks"));
		Thread.sleep(1000);
		dropdown.selectByVisibleText(od.retention_SpecifyAmountAs, externalData.getFieldData(TSID, "PaymentRetention", "Specify amount as"));
		od.retention_SpecifyAmountValue.clear();
		System.out.println(externalData.getFieldData(TSID, "PaymentRetention", "Amount"));
		od.retention_SpecifyAmountValue.sendKeys(externalData.getFieldData(TSID, "PaymentRetention", "Amount"));
//		od.retention_nextArrowIcon.click();
		click(od.retention_nextArrowIcon);
		
		applyExplicitWaitsUntilElementClickable(od.payments_ExecutionDate, Duration.ofSeconds(5));
//		od.payments_ExecutionDate.click();
		click(od.payments_ExecutionDate);
		String	day= DateUtils.getDay();
    	By excecutionDay = By.xpath("//td[contains(@class,'ui-day') and not(contains(@class,'ui-calendar-invalid')) and not(contains(@class,'ui-calendar-outFocus')) and normalize-space()='"+day+"']");
    	applyExplicitWaitsUntilElementVisible(excecutionDay, 10);
		driver.findElement(excecutionDay).click();
		System.out.println(externalData.getFieldData(TSID, "PaymentRetention", "Schedule At"));
		dropdown.selectByVisibleText(od.retention_ScheduleAt,externalData.getFieldData(TSID, "PaymentRetention", "Schedule At"));
		System.out.println(externalData.getFieldData(TSID, "Scheduled", "Holiday Action"));
		dropdown.selectByVisibleText(od.payments_HolidayAction,externalData.getFieldData(TSID, "Scheduled", "Holiday Action"));

		String time = DateUtils.getTimeAfterMins(5);
		od.retention_ScheduleTime.clear();
		od.retention_ScheduleTime.sendKeys(time);
		Thread.sleep(1000);
		dropdown.selectByValue(od.retention_SelectTimezone, "Asia/Calcutta")	;
		Thread.sleep(1000);
		dropdown.selectByVisibleText(od.retention_Execute1,"On scheduled date");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		if(isWebElementDisplayed(od.retention_ScheduleNoOfDays)) {
			od.retention_ScheduleNoOfDays.sendKeys("3");
		}
		
		click(od.retention_ScheduleNextButton);
		click(od.retention_SubInstructionNextButton);
		click(od.retention_RetryNextButton);
//		click(od.retention_Summary);

	}
}
