package com.upp.pagemodules;


import org.openqa.selenium.By;
import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.ExcelReader;

public class DashBoard_Module extends BaseClass{
	
	public static Object_Deal od ;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int rowNum;
	public static OdpApi odpAccount;
	public static AccountDetails accDetails;

	public DashBoard_Module() {
		
		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown=new DropDown(driver);
		odpAccount=new OdpApi();
		accDetails=new AccountDetails();

	}

	public void loginToUPP() {
		
		od.username.sendKeys(prop.getProperty("username"));
		od.password.sendKeys(prop.getProperty("password"));
		od.loginIn.click();	
	
	}

	public void createNewDeal(String TSID) throws Exception{
	
			 od.deal_SideMenuIcon.click();
			 od.newDealButton.click();
			 od.newDeal.sendKeys(externalData.getFieldData(TSID,"Basic Details","Deal Name"));
			 dropdown.selectByVisibleText(od.businessSegmentDropDown, externalData.getFieldData(TSID,"Basic Details","Business Segment"));
			 dropdown.selectByVisibleText(od.countryIndiaDropDown, externalData.getFieldData(TSID,"Basic Details","Country"));
			 String input = externalData.getFieldData(TSID,"Basic Details","Transactions to non-registered beneficiaries");
			 if((input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes") ) && !od.beneficiariesCheckBox.isSelected()) {
				 od.beneficiariesCheckBox.click();
			 }
			  
			 String ProcessingUnits=externalData.getFieldData(TSID,"Basic Details","Processing Units");
				
				if(!(ProcessingUnits.equalsIgnoreCase("Select All")))
				{
					 od.deals_ProcessingUnits.click();
					 od.deals_selectAll.click();
					 By ProcessingUnit = By.xpath("//div[contains(@class,'ng-tns-c92-7 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"+ProcessingUnits+"']");
					 driver.findElement(ProcessingUnit).click();
				}
			 
			 input = externalData.getFieldData(TSID,"Basic Details","Transaction Categories");
			 od.transactionCategory.click();
			 By transaction_Category_Option = By.xpath("//div[contains(@class,'ng-tns-c92-5 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"+input+"']");
			 applyExplicitWaitsUntilElementVisible(transaction_Category_Option, 10);
			 driver.findElement(transaction_Category_Option).click();
			 od.saveButton.click();
			
			 input = externalData.getFieldData(TSID,"Basic Details","Party Responsibilities");
			 od.partyResponsibility.click();
			 By party_Responsibility_Option = By.xpath("//div[contains(@class,'ng-tns-c92-6 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"+input+"']");
			 applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 10);
			 driver.findElement(party_Responsibility_Option).click();
			 od.saveButton.click();
			 od.nextBtn.click();
		    createNewAccount(TSID);
	}
	
	
	public void createNewAccount(String TSID) throws Exception {
		
		odpAccount.createAccount(TSID);
		accDetails=odpAccount.popelmnt(OdpApi.stack1);
		System.out.println("the account no is"+accDetails.getAccno());
		String accountNo = accDetails.getAccno();
		dropdown.selectByVisibleText(od.country,externalData.getFieldData(TSID, "Accounts", "Country"));
		dropdown.selectByVisibleText(od.currency, externalData.getFieldData(TSID, "Accounts", "Currency"));
		String physicalYesOrNo = externalData.getFieldData(TSID, "Accounts", "Physical");
		if (physicalYesOrNo.equalsIgnoreCase("Yes")) {
		dropdown.selectByVisibleText(od.physical, "Physical");
		} 
			else {
			dropdown.selectByVisibleText(od.physical, " Virtual ");
			}

		od.searchTextBox.sendKeys(accountNo);
		od.searchButton.click();

		}
	}
