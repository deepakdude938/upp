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

public class OffboardAccount extends BaseClass{

	public static ExcelReader externalData;
	public Payload pay;
	public static String response = "";
	public static Object_Parties op;
	public static Object_NewDeal od;
	
	public OffboardAccount() {
		externalData = new ExcelReader();
		pay = new Payload();
		od = new Object_NewDeal();
		op = new Object_Parties();
	}
public void offboard_Account(String TSID) throws IOException, Exception {
	od.accounts_offboardAccount.click();
	od.offboardAcct_YesBtn.click();
	Thread.sleep(1000);
	String successMsg = od.offboardAcct_Success.getText();
	System.out.println("Offboard Success Message:"+successMsg);
	//String offboardSucMsg = "Account onboarded successfully";
	od.offboardAcct_OK.click();
	Thread.sleep(3000);
	}
}
