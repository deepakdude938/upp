package com.upp.pagemodules.configuration;

import java.io.IOException;
import java.util.UUID;
import org.openqa.selenium.By;
import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_Configuration;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.DateUtils;
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
		Thread.sleep(2000);
		ScrollTypes.scrollInsideWindow1(config.configuaration_LeftScrollBar, 1000);
		config.configuration_ManageConfigs.click();
		config.configuaration_Product.click();
		config.configurationProduct_AddNew.click();
		config_productName =externalData.getFieldData(tSID, "Configuration", "Name")+"_"+DateUtils.getCurrentDateTime1();
		config.configurationProduct_Name.sendKeys(config_productName);
		config.configurationProduct_Description.sendKeys(externalData.getFieldData(tSID, "Configuration", "Description"));
		
		String input_SelectTab = externalData.getFieldData(tSID, "Configuration", "Select Tabs");
		config.configurationProduct_SelectTabs.click();
		By selectTabs = By.xpath("//label[normalize-space()='Select Tabs']//following::span[contains(@class,'ui-autocomplete-list-item-option') and normalize-space()='"+input_SelectTab+"']");
		//label[normalize-space()='Select Tabs']//following::span[contains(@class,'ui-autocomplete-list-item-option')]
		try {
		driver.findElement(selectTabs).click();
		}
		catch(Exception e) {
			handleElementClickException(driver.findElement(selectTabs));
		}
		config.configurationProduct_SelectTabsText.click();
		Thread.sleep(2000);
		
		ScrollTypes.scrollInsideWindow1(config.configurationProduct_ScrollBar, 100);
		String input_ScheduleInstructionType = externalData.getFieldData(tSID, "Configuration", "Schedule Instruction Types");
		config.configurationProduct_ScheduleInstructionType.click();
		Thread.sleep(1500);
		By scheduleInstructionType= By.xpath("//label[normalize-space()='Schedule Instruction Types']//following::span[contains(@class,'ui-autocomplete-list-item-option') and normalize-space()='"+input_ScheduleInstructionType+"']");
		driver.findElement(scheduleInstructionType).click();
		config.configurationProduct_ScheduleInstructionTypeText.click();
		Thread.sleep(2000);
		
		ScrollTypes.scrollInsideWindow1(config.configurationProduct_ScrollBar, 50);
		String input_LinkedInstructionType = externalData.getFieldData(tSID, "Configuration", "Linked Instruction Types");
		config.configurationProduct_LinkedInstructionTypes.click();
		By linkedInstructionInstructionType= By.xpath("//label[normalize-space()='Linked Instruction Types']//following::div[contains(@class,'ui-autocomplete-list-item-option') and normalize-space()='"+input_LinkedInstructionType+"']");
		driver.findElement(linkedInstructionInstructionType).click();
		config.configurationProduct_LinkedInstructionTypesText.click();
		Thread.sleep(1000);
		
		ScrollTypes.scrollInsideWindow1(config.configurationProduct_ScrollBar, 50);
		String input_FeeInstructionType = externalData.getFieldData(tSID, "Configuration", "Fee Instruction Types");
		config.configurationProduct_FeeInstructionTypes.click();
		By feeInstructionInstructionType= By.xpath("//label[normalize-space()='Fee Instruction Types']//following::span[contains(@class,'ui-autocomplete-list-item-option') and normalize-space()='"+input_FeeInstructionType+"']");
		driver.findElement(feeInstructionInstructionType).click();
		config.configurationProduct_FeeInstructionTypesText.click();
		Thread.sleep(1000);
		
		String input_Notifications = externalData.getFieldData(tSID, "Configuration", "Notifications");
		config.configurationProduct_Notifications.click();
		By configurationProduct_Notifications= By.xpath("//label[contains(text(),'Notifications')]//following::div[contains(text(),'"+input_Notifications.trim()+"')]");
		driver.findElement(configurationProduct_Notifications).click();
		config.configurationProduct_NotificationsText.click();
		Thread.sleep(2000);
		config.configurationProduct_TransactionPurpose.sendKeys(externalData.getFieldData(tSID, "Configuration", "Transaction Purpose"));
		Thread.sleep(2000);
		config.configurationProduct_Submit.click();
		}

	public void approveProduct() throws Exception {
		
		config.configurationProduct_AdminChecker.click();
		Thread.sleep(2000);
		By product = By.xpath("//div[contains(text(),'"+config_productName +"')]/../../..//i[@title='Edit']") ;
		System.out.println(config_productName);
		applyExplicitWaitsUntilElementVisible(product, 5);
		driver.findElement(product).click();
		Thread.sleep(2000);
		config.configurationProduct_Comment.sendKeys("Approved");
		config.configurationProduct_Approve.click();
		config.configurationProduct_OkButton.click();
	}
	
	public void createProductWithTransactionLimit(String tSID) throws Exception {
		
		config.configurationButton.click();
		ScrollTypes.scrollVerticalInsideWindowTillWebElementPresent(config.configuration_ManageConfigs, config.configuaration_LeftScrollBar, 9, 1000);
		click(config.configuration_ManageConfigs);
		config.configuaration_Product.click();
		config.configurationProduct_AddNew.click();
		config_productName =externalData.getFieldData(tSID, "Configuration", "Name")+"_"+DateUtils.getCurrentDateTime1();
		config.configurationProduct_Name.sendKeys(config_productName);
		config.configurationProduct_Description.sendKeys(externalData.getFieldData(tSID, "Configuration", "Description"));
		
		String currency = externalData.getFieldData(tSID, "Configuration", "Transaction Currency");
		System.out.println(currency);
		config.transactionLimit_currency.sendKeys(currency);
		dropdown.selectByVisibleText(config.transactionLimit_currency, currency);
		
		String amount = externalData.getFieldData(tSID, "Configuration", "Transaction Limit");
		System.out.println(amount);
		config.transactionLimit_amount.sendKeys(amount);
		
		String input_SelectTab = externalData.getFieldData(tSID, "Configuration", "Select Tabs");
		config.configurationProduct_SelectTabs.click();
		By selectTabs = By.xpath("(//span[text()='Select All'])[1]");
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementVisible(selectTabs, 10);
		driver.findElement(selectTabs).click();
		config.configurationProduct_SelectTabsText.click();
		Thread.sleep(1000);
		
		String input_ScheduleInstructionType = externalData.getFieldData(tSID, "Configuration", "Schedule Instruction Types");
		config.configurationProduct_ScheduleInstructionType.click();
//		By scheduleInstructionType= By.xpath("//label[normalize-space()='Schedule Instruction Types']//following::span[@class='ng-tns-c93-2 ui-autocomplete-list-item-option' and normalize-space()='"+input_ScheduleInstructionType+"']");
		By scheduleInstructionType= By.xpath("//label[normalize-space()='Schedule Instruction Types']/..//*[contains(@class,'ui-autocomplete-list-item-option') and normalize-space()='"+input_ScheduleInstructionType+"']");
		applyExplicitWaitsUntilElementVisible(scheduleInstructionType, 10);
		driver.findElement(scheduleInstructionType).click();
		config.configurationProduct_ScheduleInstructionTypeText.click();
		Thread.sleep(1000);
		
		String input_LinkedInstructionType = externalData.getFieldData(tSID, "Configuration", "Linked Instruction Types");
		config.configurationProduct_LinkedInstructionTypes.click();
//		By linkedInstructionInstructionType= By.xpath("//label[normalize-space()='Linked Instruction Types']//following::div[@class='ng-tns-c93-3 ui-autocomplete-list-item-option ng-star-inserted' and normalize-space()='"+input_LinkedInstructionType+"']");
		By linkedInstructionInstructionType= By.xpath("//label[normalize-space()='Linked Instruction Types']/..//*[contains(@class,'ui-autocomplete-list-item-option') and normalize-space()='"+input_LinkedInstructionType+"']");
		applyExplicitWaitsUntilElementVisible(linkedInstructionInstructionType, 10);
		driver.findElement(linkedInstructionInstructionType).click();
		config.configurationProduct_LinkedInstructionTypesText.click();
		Thread.sleep(1000);
		
		String input_FeeInstructionType = externalData.getFieldData(tSID, "Configuration", "Fee Instruction Types");
		config.configurationProduct_FeeInstructionTypes.click();
		By feeInstructionInstructionType= By.xpath("//label[normalize-space()='Fee Instruction Types']/..//*[contains(@class,'ui-autocomplete-list-item-option') and normalize-space()='"+input_FeeInstructionType+"']");
		applyExplicitWaitsUntilElementVisible(feeInstructionInstructionType, 10);
		driver.findElement(feeInstructionInstructionType).click();
		config.configurationProduct_FeeInstructionTypesText.click();
		Thread.sleep(1000);
		
		String input_Notifications = externalData.getFieldData(tSID, "Configuration", "Notifications");
		config.configurationProduct_Notifications.click();
		By configurationProduct_Notifications= By.xpath("//label[normalize-space()='Notifications']/..//*[contains(@class,'ui-autocomplete-list-item-option') and normalize-space()='"+input_Notifications+"']");
		applyExplicitWaitsUntilElementVisible(configurationProduct_Notifications, 10);
		driver.findElement(configurationProduct_Notifications).click();
		config.configurationProduct_NotificationsText.click();
		Thread.sleep(1000);
		config.configurationProduct_TransactionPurpose.sendKeys(externalData.getFieldData(tSID, "Configuration", "Transaction Purpose"));
		Thread.sleep(1000);
		config.configurationProduct_Submit.click();
		}
}
