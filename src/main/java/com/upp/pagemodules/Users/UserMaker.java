package com.upp.pagemodules.Users;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

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

	public UserMaker() {

		ou = new Object_User();
		dropdown = new DropDown(driver);

	}

	public void onBoardUser() {
		ou.usersTab.click();
		ou.userMaker.click();
		ou.userMaker_directory.click();
		ou.userMaker_search.click();
		ou.userMaker_search.sendKeys("user348");
		ou.userMaker_searchIcon.click();
		try {
			ou.userMaker_addIcon.click();
			String processingUnit = "Select All";
			By processingunitButton = By.xpath("//span[text()='" + processingUnit + "']");
			applyExplicitWaitsUntilElementVisible(processingunitButton, 10);
		} catch (Exception e) {
			ou.userMaker_editIcon.click();
			ou.userMaker_processingunit.click();
//			By processingunitButton1 = By.xpath("//span[text()='" + "Select All" + "']");
//			applyExplicitWaitsUntilElementVisible(processingunitButton1, 10);
			String processingUnit = "Select All";
			By processingunitButton = By.xpath("//span[text()='" + processingUnit + "']");
			applyExplicitWaitsUntilElementVisible(processingunitButton, 10);
		}

		ou.userMaker_phoneNumberTxt.clear();
		ou.userMaker_phoneNumberTxt.sendKeys("9890986754");
		dropdown.selectByVisibleText(ou.userMaker_country, "India");
		dropdown.selectByVisibleText(ou.userMaker_Role, " Deal Maker ");
		ou.userMaker_onboard.click();
	}

}
