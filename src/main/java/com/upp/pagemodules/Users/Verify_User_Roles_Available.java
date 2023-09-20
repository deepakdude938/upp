package com.upp.pagemodules.Users;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.upp.Api.utils.Fetch_All_Roles_API;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Verify_User_Roles_Available extends BaseClass {

	public static Object_User ou;
	public static Properties prop;
	DropDown dropdown;
	public static ExcelReader externalData;
	public static JavascriptClick jsClick;
	public static Object_NewDeal ol;
	ScrollTypes scroll;
	ExcelReader excel;
	CommonUtils util;

	public Verify_User_Roles_Available() {
		jsClick = new JavascriptClick(driver);
		ou = new Object_User();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		ol = new Object_NewDeal();
		excel = new ExcelReader();
		scroll = new ScrollTypes(driver);
		util = new CommonUtils(driver);
	}

	public void verify_User_Roles_Available_IN_UI(String TSID) throws Exception {

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
		applyExplicitWaitsUntilElementVisible(ou.usermaker_role, Duration.ofSeconds(20));

		// Create a Select object
		Select dropdown = new Select(ou.usermaker_role);

		// Get all options from the dropdown as List<WebElement>
		List<WebElement> optionElements = dropdown.getOptions();

		// Create an ArrayList to store option texts
		ArrayList<String> optionTexts = new ArrayList<>();

		// Iterate through the option elements and extract their text
		for (WebElement option : optionElements) {
			optionTexts.add(option.getText());
		}
		for (String text : optionTexts) {
			System.out.println(text);
		}
		
		Assert.assertTrue(optionTexts.contains(Fetch_All_Roles_API.RoleName1));
		Assert.assertTrue(optionTexts.contains(Fetch_All_Roles_API.RoleName2));
		Assert.assertTrue(optionTexts.contains(Fetch_All_Roles_API.RoleName3));

	}

}
