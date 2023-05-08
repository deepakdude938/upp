package com.upp.pagemodules.Login;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Login;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

public class LoginToApplication extends BaseClass {

	public static Object_Login ol;
	public static Properties prop;

	public LoginToApplication() {

		ol = new Object_Login();

	}

	public void login(String userType, Properties prop) throws Exception{

		// userType can be deal_maker, deal_cherk, txn_maker, txn_checker, txn_verifier
		String userNameKey = userType + ".username";
		String pwdKey = userType + ".password";

		String userName = prop.getProperty(userNameKey);
		String password = prop.getProperty(pwdKey);
		applyExplicitWaitsUntilElementClickable(ol.username, Duration.ofSeconds(35));
		ol.username.sendKeys(userName);
		ol.loginIn.click();
		ol.password.sendKeys(password);
		ol.loginIn.click();

	}

}
