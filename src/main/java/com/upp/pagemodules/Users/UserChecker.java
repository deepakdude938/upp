package com.upp.pagemodules.Users;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_User;
import com.upp.stepdefinition.DealPage;
import com.upp.pageobjects.Object_Login;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import com.upp.utils.SwitchWindow;

import org.testng.Assert;

public class UserChecker extends BaseClass {

	public static Object_User ou;
	public static Object_NewDeal ol;
	public static Properties prop;
	DropDown dropdown;
	public static ExcelReader externalData;
	SwitchWindow windows;
	public static JavascriptClick jsClick;

	public UserChecker() {
		jsClick = new JavascriptClick(driver);
		ou = new Object_User();
		dropdown = new DropDown(driver);
		ol = new Object_NewDeal();
		externalData = new ExcelReader();
		windows = new SwitchWindow(driver);
		prop = new Properties();
	}

	public void approveUsers() throws Exception {
		ou.userChecker.click();
		Thread.sleep(3000);
		ou.userChecker_menu.click();
		Thread.sleep(3000);
		ou.userChecker_approve.click();
		applyExplicitWaitsUntilElementClickable(ou.userChecker_approveMessage, Duration.ofSeconds(5));
		ou.userChecker_approveMessage.sendKeys("Ok approve");
		Thread.sleep(3000);
		ou.userchecker_Yes.click();
		Thread.sleep(3000);
		ou.userMaker_ok.click();
		Thread.sleep(4000);
	}

	public void login(String TSID) throws Exception {
		System.out.println("Updated user login");
		Thread.sleep(3000);
		
		// driver.manage().deleteAllCookies();
		// driver.navigate().refresh();
		String userNameKey = externalData.getFieldData(TSID, "Users", "UserName");
		String pwdKey = externalData.getFieldData(TSID, "Users", "Password");
		applyExplicitWaitsUntilElementClickable(ol.username, Duration.ofSeconds(35));
		ol.username.sendKeys(userNameKey);
		ol.password.sendKeys(pwdKey);
		System.out.println("before loginsucessful");
		ol.loginIn.click();
		System.out.println("loginsucessful");
		Thread.sleep(5000);
	}

	public void verifyUser() throws Exception {
		Thread.sleep(3000);
		String actualUrl = driver.getCurrentUrl();
		String expected[] = actualUrl.split(".com");
		String expectedUrl = expected[0] + ".com/dashboard";
		Assert.assertEquals(expectedUrl, actualUrl);
	}

	public void loginWithUSerAddedUsingAPI(String username) throws Exception {
		System.out.println("Updated user login");
		Thread.sleep(3000);
		//int usercount1=3;

		String excelFilePath = System.getProperty("user.dir")
				+ "//src//main//resources//UserList.xlsx";
	
        
        //String username = externalData.getFieldData(excelFilePath, "Sheet", "UserName",usercount2);
        //String password = externalData.getFieldData(excelFilePath, "Sheet", "Password",usercount2);
		applyExplicitWaitsUntilElementClickable(ol.username, Duration.ofSeconds(35));
		ol.username.sendKeys(username);
		ol.password.sendKeys(username);
		System.out.println("before loginsucessful");
		ol.loginIn.click();
		System.out.println("loginsucessful");
		Thread.sleep(5000);
	}
	
	public void verifyResponsibility() throws Exception {
		int flag=0;
		Thread.sleep(2000);
		ol.deal_SideMenuIcon.click();
		Thread.sleep(2000);
		int size = ou.user_dealList.size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			System.out.println("Found = " + ou.user_dealList.get(i).getText());
			String dealList = ou.user_dealList.get(i).getText();
		
			if ((dealList.equalsIgnoreCase("Deal Checker"))) {
				flag = 1;
			} else {
				flag = 0;
			}
			
		}
		Assert.assertEquals(flag, 1);
	}

	public void verifyUserNotAbleTologin() {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualUrl = driver.getCurrentUrl();
		String expected[] = actualUrl.split(".com");
		String expectedUrl = expected[0] + ".com/login";
		Assert.assertEquals(expectedUrl, actualUrl);
	}

}
