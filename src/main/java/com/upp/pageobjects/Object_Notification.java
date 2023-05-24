package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_Notification extends BaseClass {

	public Object_Notification() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@title='Notifications']")
	public WebElement notification;
	
	@FindBy(xpath = "//div[contains(@class,'message-card')]//span")
	public WebElement notification_Message;


	
}
