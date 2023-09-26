package com.upp.pagemodules.Users;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.bcel.generic.Select;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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

public class Verify_Role_Is_Added_To_User extends BaseClass {

	public static Object_User ou;
	public static Properties prop;
	DropDown dropdown;
	public static ExcelReader externalData;
	public static JavascriptClick jsClick;
	public static Object_NewDeal ol;
	ScrollTypes scroll;
	ExcelReader excel;
	CommonUtils util;

	public Verify_Role_Is_Added_To_User() {
		jsClick = new JavascriptClick(driver);
		ou = new Object_User();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		ol = new Object_NewDeal();
		excel = new ExcelReader();
		scroll = new ScrollTypes(driver);
		util = new CommonUtils(driver);
	}

	public void verify_Role_Is_Added_To_User(String TSID) throws Exception {

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
		ou.userMaker_search.sendKeys(externalData.getFieldData(TSID,"API Testcases","UserName"));
		applyExplicitWaitsUntilElementClickable(ou.userMaker_searchIcon, Duration.ofSeconds(20));
		ou.userMaker_searchIcon.click();
		applyExplicitWaitsUntilElementClickable(ou.userMaker_editIcon, Duration.ofSeconds(20));
		jsClick.click(ou.userMaker_editIcon);


		scroll.scrollInToView(ou.User_roles_dropdown);
		applyExplicitWaitsUntilElementClickable(ou.User_roles_dropdown, Duration.ofSeconds(20));
		String RoleAdded=ou.User_roles_dropdown.getAttribute("value");
		System.out.println("Role Added is:"+RoleAdded);
		Assert.assertEquals(RoleAdded,"DEALCHECKER");
		

	}

}
