package com.upp.pagemodules.Users;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import org.apache.bcel.generic.Select;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

public class UserMaker extends BaseClass {

	public static Object_User ou;
	public static Properties prop;
	DropDown dropdown;
	public static ExcelReader externalData;
	public static JavascriptClick jsClick;
	public static Object_NewDeal ol;

	public UserMaker() {
		jsClick = new JavascriptClick(driver);
		ou = new Object_User();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		ol = new Object_NewDeal();
	}

	public void onBoardUser(String TSID) throws Exception {
		ou.usersTab.click();
		ou.userMaker.click();
		Thread.sleep(5000);
		ou.userMaker_directory.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ou.userMaker_search.click();
		Thread.sleep(5000);
		ou.userMaker_search.sendKeys(externalData.getFieldData(TSID, "Users", "UserName"));
		ou.userMaker_searchIcon.click();
		try {
			ou.userMaker_addIcon.click();
			String processingUnit = "Select All";
			By processingunitButton = By.xpath("//span[text()='" + processingUnit + "']");
			applyExplicitWaitsUntilElementVisible(processingunitButton, 10);
			driver.findElement(processingunitButton).click();

		} catch (Exception e) {
			ou.userMaker_editIcon.click();
			ou.userMaker_remove.click();
		}

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
		Thread.sleep(3000);
		ou.userMaker_ok.click();
		Thread.sleep(3000);
	}

	public void inactivateUser(String TSID) throws Exception {
		ou.usersTab.click();
		ou.userMaker.click();
		ou.userMaker_directory.click();
		try {
			Thread.sleep(5000);
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
}
