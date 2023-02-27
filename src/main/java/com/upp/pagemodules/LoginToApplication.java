package com.upp.pagemodules;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_Login;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

public class LoginToApplication {

	public static Object_Login ol;
	public static Properties prop;

	public LoginToApplication() {

		ol = new Object_Login();

	}

	public void login(String userType, Properties prop) {

		// userType can be deal_maker, deal_cherk, txn_maker, txn_checker, txn_verifier
		String userNameKey = userType + ".username";
		String pwdKey = userType + ".password";

		String userName = prop.getProperty(userNameKey);
		String password = prop.getProperty(pwdKey);

		ol.username.sendKeys(userName);
		ol.password.sendKeys(password);
		ol.loginIn.click();

	}

}
