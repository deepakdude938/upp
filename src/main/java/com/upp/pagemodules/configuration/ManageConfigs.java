package com.upp.pagemodules.configuration;

import java.io.IOException;
import java.util.UUID;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_Configuration;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.ScrollTypes;

public class ManageConfigs extends BaseClass{
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static ScrollTypes scroll;
	public Object_Configuration config;

	
	public ManageConfigs(){
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		config=new Object_Configuration();
	}

	public void createProduct(String tSID) throws Exception {
		
		config.configurationButton.click();
		config.configuration_ManageConfigs.click();
		config.configuaration_Product.click();
		config.configurationProduct_AddNew.click();
		config_productName =externalData.getFieldData(tSID, "Configuration", "Name")+UUID.randomUUID().toString().substring(0, 4);
		config.configurationProduct_Name.sendKeys(config_productName);
		config.configurationProduct_Description.sendKeys(externalData.getFieldData(tSID, "Configuration", "Description"));
		
		String input_SelectTab = externalData.getFieldData(tSID, "Configuration", "Select Tabs");
		config.configurationProduct_SelectTabs.click();
		By selectTabs = By.xpath("//label[normalize-space()='Select Tabs']//following::span[@class='ng-tns-c92-1 ui-autocomplete-list-item-option' and normalize-space()='"+input_SelectTab+"']");
		applyExplicitWaitsUntilElementVisible(selectTabs, 10);
		driver.findElement(selectTabs).click();
		config.configurationProduct_SelectTabsText.click();
		
		String input_ScheduleInstructionType = externalData.getFieldData(tSID, "Configuration", "Schedule Instruction Types");
		config.configurationProduct_ScheduleInstructionType.click();
		By scheduleInstructionType= By.xpath("//label[normalize-space()='Schedule Instruction Types']//following::span[@class='ng-tns-c92-2 ui-autocomplete-list-item-option' and normalize-space()='"+input_ScheduleInstructionType+"']");
		applyExplicitWaitsUntilElementVisible(scheduleInstructionType, 10);
		driver.findElement(scheduleInstructionType).click();
		config.configurationProduct_ScheduleInstructionTypeText.click();
		
		String input_LinkedInstructionType = externalData.getFieldData(tSID, "Configuration", "Linked Instruction Types");
		config.configurationProduct_LinkedInstructionTypes.click();
		By linkedInstructionInstructionType= By.xpath("//label[normalize-space()='Linked Instruction Types']//following::div[@class='ng-tns-c92-3 ui-autocomplete-list-item-option ng-star-inserted' and normalize-space()='"+input_LinkedInstructionType+"']");
		applyExplicitWaitsUntilElementVisible(linkedInstructionInstructionType, 10);
		driver.findElement(linkedInstructionInstructionType).click();
		config.configurationProduct_LinkedInstructionTypesText.click();
		
		String input_FeeInstructionType = externalData.getFieldData(tSID, "Configuration", "Fee Instruction Types");
		config.configurationProduct_FeeInstructionTypes.click();
		By feeInstructionInstructionType= By.xpath("//label[normalize-space()='Fee Instruction Types']//following::span[@class='ng-tns-c92-4 ui-autocomplete-list-item-option' and normalize-space()='"+input_FeeInstructionType+"']");
		applyExplicitWaitsUntilElementVisible(feeInstructionInstructionType, 10);
		driver.findElement(feeInstructionInstructionType).click();
		config.configurationProduct_FeeInstructionTypesText.click();
		
		String input_Notifications = externalData.getFieldData(tSID, "Configuration", "Notifications");
		config.configurationProduct_Notifications.click();
		By configurationProduct_Notifications= By.xpath("//label[normalize-space()='Notifications']//following::div[@class='ng-tns-c92-5 ui-autocomplete-list-item-option ng-star-inserted' and normalize-space()='"+input_Notifications+"']");
		applyExplicitWaitsUntilElementVisible(configurationProduct_Notifications, 10);
		driver.findElement(configurationProduct_Notifications).click();
		config.configurationProduct_NotificationsText.click();
		config.configurationProduct_TransactionPurpose.sendKeys(externalData.getFieldData(tSID, "Configuration", "Transaction Purpose"));
		Thread.sleep(1000);
		config.configurationProduct_Submit.click();
		}

	public void approveProduct() {
		
		config.configurationProduct_AdminChecker.click();
		By product = By.xpath("//div[contains(text(),'"+config_productName +"')]/../../..//i[@title='Edit']") ;
		System.out.println(config_productName);
		applyExplicitWaitsUntilElementVisible(product, 5);
		driver.findElement(product).click();
		config.configurationProduct_Comment.sendKeys("Approved");
		config.configurationProduct_Approve.click();
		config.configurationProduct_OkButton.click();
	}
}
