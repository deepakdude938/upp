package com.upp.pagemodules.Users;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import org.apache.bcel.generic.Select;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.upp.Api.utils.Get_List_of_Users_API;
import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_User;
import com.upp.pageobjects.Object_Login;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

public class Verify_User_By_User_Name extends BaseClass {

	public static Object_User ou;
	public static Properties prop;
	DropDown dropdown;
	public static ExcelReader externalData;
	public static JavascriptClick jsClick;
	public static Object_NewDeal ol;
	ScrollTypes scroll;
	ExcelReader excel;
	CommonUtils util;

	public Verify_User_By_User_Name() {
		jsClick = new JavascriptClick(driver);
		ou = new Object_User();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		ol = new Object_NewDeal();
		excel = new ExcelReader();
		scroll = new ScrollTypes(driver);
		util=new CommonUtils(driver);
	}

	public void verify_User_IN_UI(String TSID) throws Exception {
	
		scroll.scrollInToView(ou.usersTab);
		applyExplicitWaitsUntilElementClickable(ou.usersTab, Duration.ofSeconds(20)); 
		ou.usersTab.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker, Duration.ofSeconds(20)); 
		ou.userMaker.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker_directory, Duration.ofSeconds(20)); 
		ou.userMaker_directory.click();
		Thread.sleep(2000);
		ou.userMaker_search.click();
		Thread.sleep(3000);
		ou.userMaker_search.sendKeys("vaishnavi");
		applyExplicitWaitsUntilElementClickable(ou.userMaker_searchIcon, Duration.ofSeconds(20)); 
		ou.userMaker_searchIcon.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker_editIcon, Duration.ofSeconds(20)); 
	    jsClick.click(ou.userMaker_editIcon);
	    applyExplicitWaitsUntilElementVisible(ou.usermaker_email, Duration.ofSeconds(20)); 
		String email = ou.usermaker_email.getAttribute("value");
		System.out.println("Email is = " + email);
		String phone = ou.usermaker_phone.getAttribute("value");
		System.out.println("Phone number is = " + phone);
		String role = ou.usermaker_role.getAttribute("value");
	
		System.out.println("Role name is = " + role);
		Assert.assertEquals(email,"vaishnavi@appveen.com");
		Assert.assertEquals(phone,"9876543210");
		Assert.assertEquals(role,"TRANSACTIONMAKER");
	   if(!(util.isElementDisplayed(ou.usermaker_active, 2))) {
			   
		   System.out.println("User Active should be displayed");
	   }
	   

	}

	public void verify_3_UserDetails_IN_UI(String TSID) throws Exception {
		
		scroll.scrollInToView(ou.usersTab);
		applyExplicitWaitsUntilElementClickable(ou.usersTab, Duration.ofSeconds(20)); 
		ou.usersTab.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker, Duration.ofSeconds(20)); 
		ou.userMaker.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker_directory, Duration.ofSeconds(20)); 
		ou.userMaker_directory.click();
		Thread.sleep(2000);
		ou.userMaker_search.click();
		Thread.sleep(3000);
//		ou.userMaker_search.sendKeys(Get_List_of_Users_API.Username1);
//		applyExplicitWaitsUntilElementClickable(ou.userMaker_searchIcon, Duration.ofSeconds(20)); 
//		ou.userMaker_searchIcon.click();
//		applyExplicitWaitsUntilElementClickable(ou.userMaker_editIcon, Duration.ofSeconds(20)); 
//	    jsClick.click(ou.userMaker_editIcon);
//	    
//	    applyExplicitWaitsUntilElementVisible(ou.user_Username, Duration.ofSeconds(20)); 
//		String ActualUsername1 = ou.user_Username.getAttribute("value");
//		System.out.println("Username1 is = " + ActualUsername1);
//		Assert.assertEquals(ActualUsername1,Get_List_of_Users_API.Username1);
//	    ou.user_CloseIcon.click();
		
		scroll.scrollInToView(ou.usersTab);
		applyExplicitWaitsUntilElementClickable(ou.usersTab, Duration.ofSeconds(20)); 
		ou.usersTab.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker, Duration.ofSeconds(20)); 
		ou.userMaker.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker_directory, Duration.ofSeconds(20)); 
		ou.userMaker_directory.click();
		Thread.sleep(2000);
		ou.userMaker_search.click();
		Thread.sleep(3000);
		ou.userMaker_search.sendKeys(Get_List_of_Users_API.Username2);
		applyExplicitWaitsUntilElementClickable(ou.userMaker_searchIcon, Duration.ofSeconds(20)); 
		ou.userMaker_searchIcon.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker_editIcon, Duration.ofSeconds(20)); 
	    jsClick.click(ou.userMaker_editIcon);
	    
	    applyExplicitWaitsUntilElementVisible(ou.user_Username, Duration.ofSeconds(20)); 
		String ActualUsername2 = ou.user_Username.getAttribute("value");
		System.out.println("Username2 is = " + ActualUsername2);
		Assert.assertEquals(ActualUsername2,Get_List_of_Users_API.Username2);
		ou.user_CloseIcon.click();
		
		scroll.scrollInToView(ou.usersTab);
		applyExplicitWaitsUntilElementClickable(ou.usersTab, Duration.ofSeconds(20)); 
		ou.usersTab.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker, Duration.ofSeconds(20)); 
		ou.userMaker.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker_directory, Duration.ofSeconds(20)); 
		ou.userMaker_directory.click();
		Thread.sleep(2000);
		ou.userMaker_search.click();
		Thread.sleep(3000);
		ou.userMaker_search.sendKeys(Get_List_of_Users_API.Username3);
		applyExplicitWaitsUntilElementClickable(ou.userMaker_searchIcon, Duration.ofSeconds(20)); 
		ou.userMaker_searchIcon.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker_editIcon, Duration.ofSeconds(20)); 
	    jsClick.click(ou.userMaker_editIcon);
	    
	    applyExplicitWaitsUntilElementVisible(ou.user_Username, Duration.ofSeconds(20)); 
		String ActualUsername3 = ou.user_Username.getAttribute("value");
		System.out.println("Username3 is = " + ActualUsername3);
		Assert.assertEquals(ActualUsername3,Get_List_of_Users_API.Username3);
		ou.user_CloseIcon.click();
		
	

	}

}
