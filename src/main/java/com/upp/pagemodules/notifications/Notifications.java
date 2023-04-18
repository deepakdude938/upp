package com.upp.pagemodules.notifications;


import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.ScrollTypes;

public class Notifications extends BaseClass{
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static ScrollTypes scroll;

	public Notifications() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
	}
	
	

	public void updateContacts() throws Exception {
		
		od.notifications_NotificationIcon.click();
		od.notifications_SuccessfulScheduledKebabMenu.click();
		od.notifications_EditButton.click();
		od.notifications_EnabledSlider.click();
		od.notifications_Mode.click();
		od.notifications_EmailMode.click();
		od.notifications_pickContact.click();
		
		for(WebElement contact : od.notifications_ContactList) {
			contact.click();
		}
		
		od.notifications_UpdateButton.click();
		od.notifications_UpdateNotification.click();
		Thread.sleep(1000);
		scroll.scrollInsideWindow1(od.notifications_Scroller, 500);
	}
}
