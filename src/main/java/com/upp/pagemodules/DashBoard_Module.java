package com.upp.pagemodules;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.ExcelReader;

public class DashBoard_Module extends BaseClass{
	
	public static Object_Deal od ;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;

	public DashBoard_Module() {
		
		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown=new DropDown(driver);

	}

	public void loginToUPP() {
		
		od.username.sendKeys(prop.getProperty("username"));
		od.password.sendKeys(prop.getProperty("password"));
		od.loginIn.click();	
	
	}

	public void createNewDeal(Integer TcId) throws Exception{
	
			 od.deal_SideMenuIcon.click();
			 od.newDealButton.click();
			 od.newDeal.sendKeys(externalData.getFieldData(TcId,"Basic Details","Deal Name"));
			 dropdown.selectByVisibleText(od.businessSegmentDropDown, externalData.getFieldData(TcId,"Basic Details","Business Segment"));
			 dropdown.selectByVisibleText(od.countryIndiaDropDown, externalData.getFieldData(TcId,"Basic Details","Country"));
			 String input = externalData.getFieldData(TcId,"Basic Details","Transactions to non-registered beneficiaries");
			 if((input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes") ) && !od.beneficiariesCheckBox.isSelected()) {
				 od.beneficiariesCheckBox.click();
			 }
			 
			 input = externalData.getFieldData(TcId,"Basic Details","Transaction Categories");
				
			 od.transactionCategory.click();
			//div[@class='ng-tns-c92-5 ui-autocomplete-list-item-option ng-star-inserted']
			 By transaction_Category_Option = By.xpath("//div[contains(@class,'ng-tns-c92-5 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"+input+"']");
			 applyExplicitWaitsUntilElementVisible(transaction_Category_Option, 10);
			 driver.findElement(transaction_Category_Option).click();
			 od.saveButton.click();
			
			 input = externalData.getFieldData(TcId,"Basic Details","Party Responsibilities");
			
			 od.partyResponsibility.click();
			 
			 By party_Responsibility_Option = By.xpath("//div[contains(@class,'ng-tns-c92-6 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"+input+"']");
			 applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 10);
			 driver.findElement(party_Responsibility_Option).click();
			 od.saveButton.click();
	}
	
	
	

}
