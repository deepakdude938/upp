package com.upp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_Login extends BaseClass {

	public Object_Login() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "txt-generic-login-username-v1")
	public WebElement username;

	@FindBy(id = "txt-generic-login-password-v1")
	public WebElement password;

	@FindBy(id = "btn-generic-login-submit-v1")
	public WebElement loginIn;

}
