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

public class UserChecker extends BaseClass {

	public static Object_User ou;
	public static Properties prop;
	DropDown dropdown;

	public UserChecker() {

		ou = new Object_User();
		dropdown = new DropDown(driver);

	}

	public void approveUsers() {
		ou.userChecker.click();
		System.out.println(ou.userChecker_user.getText());
		
	}
}
