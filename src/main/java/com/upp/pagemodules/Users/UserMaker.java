package com.upp.pagemodules.Users;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import org.apache.bcel.generic.Select;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_User;
import com.upp.pageobjects.Object_Login;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import junit.framework.Assert;

public class UserMaker extends BaseClass {

	public static Object_User ou;
	public static Properties prop;
	DropDown dropdown;
	public static ExcelReader externalData;
	public static JavascriptClick jsClick;
	public static Object_NewDeal ol;
	ScrollTypes scroll;
	ExcelReader excel;

	public UserMaker() {
		jsClick = new JavascriptClick(driver);
		ou = new Object_User();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		ol = new Object_NewDeal();
		excel = new ExcelReader();
		scroll = new ScrollTypes(driver);
	}

	public void onBoardUser(String TSID) throws Exception {
		String excelFilePath = System.getProperty("user.dir") + "//src//main//resources//upp-automation-testdata.xlsx";
		scroll.scrollInToView(ou.usersTab);
		ou.usersTab.click();
		ou.userMaker.click();
		Thread.sleep(5000);
		ou.userMaker_directory.click();
		Thread.sleep(2000);
		ou.userMaker_search.click();
		Thread.sleep(3000);
		ou.userMaker_search.sendKeys(externalData.getFieldData(TSID, "Users", "UserName"));
		ou.userMaker_searchIcon.click();
		String username = ou.userMaker_userList.getAttribute("title");
		System.out.println("user name = " + username);
		excel.writeDataToExcel(excelFilePath, "Users", 2, "UserName", username);
		excel.writeDataToExcel(excelFilePath, "Users", 2, "Password", username);
		ou.userMaker_search.clear();
		Thread.sleep(1000);
		ou.userMaker_search.click();
		ou.userMaker_search.sendKeys(username);
		Thread.sleep(1000);
		try {
			ou.userMaker_editIcon.click();

		} catch (Exception e) {
			ou.userMaker_addIcon.click();

		}

		ou.usersActive.click();
		ou.userMaker_phoneNumberTxt.clear();
		ou.userMaker_phoneNumberTxt.sendKeys("9890986754");
		ou.userMaker_country.click();
		dropdown.selectByVisibleText(ou.userMaker_country, "India");
		Thread.sleep(2000);
		try {
			String processingUnit = "Select All";
			By processingunitButton = By.xpath("//span[text()='" + processingUnit + "']");
			applyExplicitWaitsUntilElementVisible(processingunitButton, 10);
			driver.findElement(processingunitButton).click();
		} catch (Exception er) {
			ou.userMaker_remove.click();
		}
		ou.userMaker_Role.click();
		dropdown.selectByVisibleText(ou.userMaker_Role, "Deal Maker");
		Thread.sleep(2000);
		ou.userMaker_processingunit.click();
		String processingUnit2 = "Select All";
		By processingunitButton11 = By.xpath("//div[text()='" + processingUnit2 + "']");
		applyExplicitWaitsUntilElementVisible(processingunitButton11, 10);
		driver.findElement(processingunitButton11).click();
		Thread.sleep(2000);
		//ou.userMaker_processingunit.click();
		/*
		 * String processingUnit1 = "Mumbai HO"; By processingunitButton1 =
		 * By.xpath("//div[text()='" + processingUnit1 + "']");
		 * applyExplicitWaitsUntilElementVisible(processingunitButton1, 10);
		 * driver.findElement(processingunitButton1).click();
		 */
		try {
			applyExplicitWaitsUntilElementClickable(ou.userMaker_onboard, Duration.ofSeconds(20));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ou.plusIcon.click();
		Thread.sleep(2000);
		ou.userMaker_Role.click();
		dropdown.selectByVisibleText(ou.userMaker_Role1, "Deal Checker");

		ou.userMaker_processingunit1.click();
		String processingUnitc = "Bangalore CPU";
		By processingunitButtonc = By.xpath("(//div[text()='" + processingUnitc + "'])[2]");
		applyExplicitWaitsUntilElementVisible(processingunitButtonc, 10);
		driver.findElement(processingunitButtonc).click();
		//ou.userMaker_processingunit.click();
		String processingUnitc1 = "Mumbai HO";
		By processingunitButtonc1 = By.xpath("(//div[text()='" + processingUnitc1 + "'])[2]");
		applyExplicitWaitsUntilElementVisible(processingunitButtonc1, 10);
		driver.findElement(processingunitButtonc1).click();
		try {
			applyExplicitWaitsUntilElementClickable(ou.userMaker_onboard, Duration.ofSeconds(20));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ou.userMaker_onboard.click();
		Thread.sleep(3000);
		ou.userMaker_ok.click();
		Thread.sleep(3000);
	}

	public void inactivateUser(String TSID) throws Exception {
		scroll.scrollInToView(ou.usersTab);
		ou.usersTab.click();
		ou.userMaker.click();
		ou.userMaker_directory.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ou.userMaker_search.click();
		ou.userMaker_search.sendKeys(externalData.getFieldData(TSID, "Users", "UserName"));
		ou.userMaker_searchIcon.click();
		try {
			ou.userMaker_addIcon.click();
		} catch (Exception e) {
			ou.userMaker_editIcon.click();
			ou.userMaker_remove.click();
		}
		ou.userchecker_inactive.click();
		ou.userMaker_phoneNumberTxt.clear();
		ou.userMaker_phoneNumberTxt.sendKeys("9890986754");
		dropdown.selectByVisibleText(ou.userMaker_country, "India");
		ou.userMaker_Role.click();
		dropdown.selectByVisibleText(ou.userMaker_Role, "Deal Maker");
		ou.userMaker_processingunit.click();
		String processingUnit = "Bangalore CPU";
		By processingunitButton = By.xpath("//div[text()='" + processingUnit + "']");
		applyExplicitWaitsUntilElementVisible(processingunitButton, 10);
		driver.findElement(processingunitButton).click();
		try {
			applyExplicitWaitsUntilElementClickable(ou.userMaker_onboard, Duration.ofSeconds(20));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ou.userMaker_onboard.click();
		ou.userMaker_ok.click();

	}
	
	public void verifyDeletedRole(String TSID) throws Exception {
		int flag =0;
		String excelFilePath = System.getProperty("user.dir") + "//src//main//resources//upp-automation-testdata.xlsx";
		scroll.scrollInToView(ou.usersTab);
		ou.usersTab.click();
		ou.userMaker.click();
		Thread.sleep(5000);
		ou.userMaker_directory.click();
		Thread.sleep(2000);
		ou.userMaker_search.click();
		Thread.sleep(3000);
		ou.userMaker_search.sendKeys(externalData.getFieldData(TSID, "Users", "UserName"));
		ou.userMaker_searchIcon.click();
		String username = ou.userMaker_userList.getAttribute("title");
		System.out.println("user name = " + username);
		excel.writeDataToExcel(excelFilePath, "Users", 2, "UserName", username);
		excel.writeDataToExcel(excelFilePath, "Users", 2, "Password", username);
		ou.userMaker_search.clear();
		Thread.sleep(1000);
		ou.userMaker_search.click();
		ou.userMaker_search.sendKeys(username);
		Thread.sleep(1000);
		try {
			ou.userMaker_editIcon.click();

		} catch (Exception e) {
			ou.userMaker_addIcon.click();

		}
		
		String role = dropdown.getSelectedValue(ou.userMaker_Role);
		if(!role.equalsIgnoreCase("Deal Maker")) {
			flag =1;
		}

		Assert.assertEquals(flag, 1);
	}
}
