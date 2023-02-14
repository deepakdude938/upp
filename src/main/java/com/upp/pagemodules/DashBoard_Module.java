package com.upp.pagemodules;


import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;

import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.ExcelReader;
import com.upp.utils.ScrollTypes;

public class DashBoard_Module extends BaseClass{
	
	public static Object_Deal od ;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int rowNum;
	public static OdpApi odpAccount;
	public static AccountDetails accDetails;
	public static ScrollTypes scroll;

	public DashBoard_Module() {
		
		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown=new DropDown(driver);
		odpAccount=new OdpApi();
		accDetails=new AccountDetails();
		scroll=new ScrollTypes(driver);

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
			 dropdown.selectByVisibleText(od.basicDetails_ProductDropDown, externalData.getFieldData(TSID,"Basic Details","Product"));
			 dropdown.selectByVisibleText(od.businessSegmentDropDown, externalData.getFieldData(TSID,"Basic Details","Business Segment"));
			 System.out.println(externalData.getFieldData(TSID,"Basic Details","Business Segment"));
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
			 System.out.println(input);
			 od.transactionCategory.click();
			 od.transactionCategoryInput.sendKeys(input);
			 By transaction_Category_Option = By.xpath("//div[contains(@class,'ng-tns-c92-5 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"+input+"']");
			 applyExplicitWaitsUntilElementVisible(transaction_Category_Option, 10);
			 driver.findElement(transaction_Category_Option).click();
			 if(!od.basicDetails_SaveButton_List.isEmpty())
			 od.saveButton.click();
			
			 input = externalData.getFieldData(TSID,"Basic Details","Party Responsibilities");
			 System.out.println(input);
			 od.partyResponsibility.click();
			 od.partyResponsibilityinput.sendKeys(input);
			 By party_Responsibility_Option = By.xpath("//div[contains(@class,'ng-tns-c92-6 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"+input+"']");
			 applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 10);
			 driver.findElement(party_Responsibility_Option).click();
			 if(!od.basicDetails_SaveButton_List.isEmpty()) {
			 od.saveButton.click();
			 }
			 od.nextBtn.click();
//		    createNewAccount(TSID);
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
		Thread.sleep(1000);
		od.addAccountButton.click();

		}

	public void createDealWithAccountAndParty(String TSID) throws Exception {
//		String dealName = externalData.getFieldData(TSID,"Basic Details","Deal Name");
//		String businessSegMent = externalData.getFieldData(TSID,"Basic Details","Business Segment");
//		String country = externalData.getFieldData(TSID,"Basic Details","Country");
//		
//		createNewDeal1(TSID,dealName,businessSegMent,country);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void createNewDeal1(String TSID,String dealName,String businessSegMent,String Country) throws Exception{
		
		 od.deal_SideMenuIcon.click();
		 od.newDealButton.click();
		 od.newDeal.sendKeys(dealName);
		 dropdown.selectByVisibleText(od.businessSegmentDropDown, businessSegMent);
		 dropdown.selectByVisibleText(od.countryIndiaDropDown, Country);
		 
		 
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
		 od.transactionCategoryInput.sendKeys(input);
		 By transaction_Category_Option = By.xpath("//div[contains(@class,'ng-tns-c92-5 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"+input+"']");
		 applyExplicitWaitsUntilElementVisible(transaction_Category_Option, 10);
		 driver.findElement(transaction_Category_Option).click();
		 od.saveButton.click();
		
		 input = externalData.getFieldData(TSID,"Basic Details","Party Responsibilities");
		 od.partyResponsibility.click();
		 od.partyResponsibilityinput.sendKeys(input);
		 By party_Responsibility_Option = By.xpath("//div[contains(@class,'ng-tns-c92-6 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"+input+"']");
		 applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 10);
		 driver.findElement(party_Responsibility_Option).click();
		 od.saveButton.click();
		 od.nextBtn.click();
	    createNewAccount(TSID);
}

	public void createParties(String TSID) throws Exception, IOException {
			
			od.parties_icon.click();
			od.parties_GetStarted.click();
			od.parties_AddnewParty.click();
			od.parties_CustomerID.sendKeys(externalData.getFieldData(TSID,"Party","Customer Id"));
			od.parties_PartyName.sendKeys(externalData.getFieldData(TSID,"Party","Party Name"));
			od.parties_Responsibility.click();
			od.parties_Responsibility_dropdown.click();
			od.parties_Remarks.sendKeys(externalData.getFieldData(TSID,"Party","Remarks"));
			 dropdown.selectByVisibleText(od.parties_CommissionPlan, "Merchant");
			
		if((externalData.getFieldData(TSID,"Party","eCommerce Party-checkbox")).equalsIgnoreCase("Y")) {
			od.parties_eCommerceCheckbox.click();	
		}
		
		od.parties_ParticipantId.sendKeys(externalData.getFieldData(TSID,"Party","Participant Id"));
		od.parties_BasicNextButton.click();
		od.parties_AddContact.click();
		od.parties_ContactName.sendKeys(externalData.getFieldData(TSID,"Party","Contact Name"));
		
		if((externalData.getFieldData(TSID,"Party","Authorised signatory-check box")).equalsIgnoreCase("Y")) {
			od.parties_AuthrorizedSignatoryYes.click();	
		}
		
		od.parties_Email.sendKeys(externalData.getFieldData(TSID,"Party","Email"));
		od.parties_AddButton.click();
		od.parties_AccountsTab.click();
		od.parties_AddAccounts.click();
		applyExplicitWaitsUntilElementClickable(od.parties_PaymentSystem,Duration.ofSeconds(5));
		od.parties_PaymentSystem.click();
		applyExplicitWaitsUntilElementClickable(od.parties_PaymentSystem_BT,Duration.ofSeconds(5));
		od.parties_PaymentSystem_BT.click();
		applyExplicitWaitsUntilElementClickable(od.parties_beneficiaryBankBic,Duration.ofSeconds(5));
		od.parties_beneficiaryBankBic.sendKeys(externalData.getFieldData(TSID,"Party","Beneficiary Bank Bic"));
		applyExplicitWaitsUntilElementClickable(od.parties_BeneficiaryCountry,Duration.ofSeconds(5));
		 dropdown.selectByVisibleText(od.parties_BeneficiaryCountry, externalData.getFieldData(TSID,"Party","Beneficiary Country"));
		 scroll.scrollInToView(od.parties_paymentTo);
		 od.parties_paymentTo.sendKeys(externalData.getFieldData(TSID,"Party","To"));
		 scroll.scrollInToView(od.parties_beneficiaryCurrency);
		 od.parties_beneficiaryCurrency.sendKeys(externalData.getFieldData(TSID,"Party","Beneficiary Currency"));
		 applyExplicitWaitsUntilElementClickable(od.parties_partyAccountsAddButton,Duration.ofSeconds(5));
		 od.parties_partyAccountsAddButton.click();
		 applyExplicitWaitsUntilElementClickable(od.parties_DocumentsTab,Duration.ofSeconds(5));
		 od.parties_DocumentsTab.click();
		 od.parties_AddDocument.click();
		 applyExplicitWaitsUntilElementClickable(od.parties_DocumentType,Duration.ofSeconds(5));
		 od.parties_DocumentType.click();
		 applyExplicitWaitsUntilElementClickable(od.parties_DocumentsType_Blueprint,Duration.ofSeconds(5));
		 od.parties_DocumentsType_Blueprint.click();
		 System.out.println( externalData.getFieldData(TSID,"Party","Document Nature"));
		 dropdown.selectByVisibleText(od.parties_DocumentNature1, externalData.getFieldData(TSID,"Party","Document Nature"));
		 od.parties_DocumentsAddButton.click();
		 
		
		
		
		
	}

	
	}
