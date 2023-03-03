package com.upp.pagemodules;


import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

public class DashBoard_Module extends BaseClass{
	
	public static Object_NewDeal od ;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int rowNum;
	public static OdpApi odpAccount;
	public static AccountDetails accDetails;
	public static ScrollTypes scroll;
	public static JavascriptClick jsClick;
	public static DateUtils dateutil;


	public DashBoard_Module() {
		
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown=new DropDown(driver);
		odpAccount=new OdpApi();
		accDetails=new AccountDetails();
		scroll=new ScrollTypes(driver);
		jsClick = new JavascriptClick(driver);
		dateutil = new DateUtils();

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

	}
	

	public String createNewAccount(String TSID) throws Exception {
		
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
		return accountNo;

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

	public void createBudget(String TSID, String sourceAccountNo, String toAccountNo) throws Exception, IOException {
		 od.budget_BudgetIcon.click();	
		 od.budget_CreateBudget.click();
		 od.budget_AddBudgetName.sendKeys(externalData.getFieldData(TSID,"Budget","BudgetName"));
		 od.budget_BudgetSourceAccount.sendKeys(sourceAccountNo);
		 By sourceAccountNoDropDown=By.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and contains(normalize-space(),'"+sourceAccountNo+"')]");
		 applyExplicitWaitsUntilElementVisible(sourceAccountNoDropDown, 10);
		 driver.findElement(sourceAccountNoDropDown).click();
		 od.budget_AddBudget.click();
		 od.budget_budgetDetailsAddBudget.click();
		 od.budget_Purpose.sendKeys( externalData.getFieldData(TSID,"Budget","Purpose"));
		 Thread.sleep(1000);
		 System.out.println(toAccountNo);
		 od.budget_budgetDestination.sendKeys(toAccountNo);
		
		 By destination = By.xpath("//div[@id='grid-generic-destinationSearch-panelClick-v1']//li[contains(text(),'"+toAccountNo+"')]");
		 applyExplicitWaitsUntilElementVisible(destination, 10);
		 driver.findElement(destination).click();
		 dropdown.selectByVisibleText(od.budget_Interval, externalData.getFieldData(TSID,"Budget","Interval"));
		 dropdown.selectByIndex(od.budget_Duration, 2);
		 od.budget_allocatedAmount.sendKeys(externalData.getFieldData(TSID,"Budget","Allocated Budget Amount"));
		 od.budget_AddButton.click();
	}

	public String createBudget_Payments(String TSID,String sourceAccountno,String toAccountNo) throws Exception{

		applyExplicitWaitsUntilElementClickable(od.payments_ScheduledInstructionIcon,Duration.ofSeconds(10));
		od.payments_ScheduledInstructionIcon.click();
		applyExplicitWaitsUntilElementClickable(od.payments_GetStarted,Duration.ofSeconds(5));
		od.payments_GetStarted.click();
		String InstructionType=externalData.getFieldData(TSID,"Scheduled","Select Instruction Type");
		 By InstructionButton = By.xpath("//div[@class='ui-align-left ui-relative ui-inline-block ui-label'][normalize-space()='"+InstructionType+"']");
		 applyExplicitWaitsUntilElementVisible(InstructionButton,10);
		 driver.findElement(InstructionButton).click();
		
		applyExplicitWaitsUntilElementClickable(od.payments_Proceed,Duration.ofSeconds(5));
		od.payments_Proceed.click();
		od.payments_BasicName.clear();
		od.payments_BasicName.sendKeys(externalData.getFieldData(TSID,"Scheduled","Basic Details Name"));
		dropdown.selectByVisibleText(od.payments_Purpose, externalData.getFieldData(TSID,"Scheduled","Purpose"));
		od.payments_SourceAccount.sendKeys(sourceAccountno);
		By sourceaccountselect = By.xpath("//div[contains(text(),'"+sourceAccountno+"')]");
		 driver.findElement(sourceaccountselect).click();

		dropdown.selectByVisibleText(od.payments_BalanceConsideration,externalData.getFieldData(TSID,"Scheduled","Balance Consideration"));
		if(((externalData.getFieldData(TSID,"Scheduled","Split")).equalsIgnoreCase("Y") || (externalData.getFieldData(TSID,"Scheduled","Split")).equalsIgnoreCase("Yes"))){
			od.payments_SplitBalanceSlider.click();
		}
		if(((externalData.getFieldData(TSID,"Scheduled","Partial Payment")).equalsIgnoreCase("Y") || (externalData.getFieldData(TSID,"Scheduled","Partial Payment")).equalsIgnoreCase("Yes"))){
			od.payments_PartialpaymentSlider.click();
		}
		
		od.payments_NextArrowButtonTransferBasic.click();
		
		if(((externalData.getFieldData(TSID,"Scheduled","Schedule - Repeating")).equalsIgnoreCase("Y") || (externalData.getFieldData(TSID,"Scheduled","Schedule - Repeating")).equalsIgnoreCase("Yes"))){
			od.payments_PartialpaymentSlider.click();
		}
		
		applyExplicitWaitsUntilElementClickable(od.payments_ExecutionDate,Duration.ofSeconds(5));
		od.payments_ExecutionDate.click();
		String day=DateUtils.getDay(); 
		By excecutionDay = By.xpath("//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"+day+"']");
			
		applyExplicitWaitsUntilElementVisible(excecutionDay,5);
		driver.findElement(excecutionDay).click();
		applyExplicitWaitsUntilElementClickable(od.payments_ScheduleAt,Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.payments_ScheduleAt, externalData.getFieldData(TSID,"Scheduled","Schedule At"));
	    dropdown.selectByVisibleText(od.payments_HolidayAction, externalData.getFieldData(TSID,"Scheduled","Holiday Action"));
		String time = dateutil.getTimeAfterMins(5);
		od.payments_ScheduleTime.clear();
		od.payments_ScheduleTime.sendKeys(time);
		od.payments_NextArrowButtonTransferSchedule.click();
		applyExplicitWaitsUntilElementClickable(od.payments_Instrument,Duration.ofSeconds(20));
		od.payments_Instrument.click();
		String paymentInstrumentdata=externalData.getFieldData(TSID,"Scheduled","Instrument");
        By paymentInstrument = By.xpath("//div[contains(text(),'"+paymentInstrumentdata+"')]");
		driver.findElement(paymentInstrument).click();
		 
		 String budget= externalData.getFieldData(TSID,"Scheduled","Budget Purpose");
		 od.payments_budgetPurpose.sendKeys(budget);
		
		 By budgetPurpose = By.xpath("//div[contains(@class,'ng-star-inserted') and contains(text(),'"+budget+"')]");
		 applyExplicitWaitsUntilElementVisible(budgetPurpose, 10);
		 driver.findElement(budgetPurpose).click();
		 scroll.scrollInToView(od.payments_ToAccountInputBox);
		 od.payments_ToAccountInputBox.sendKeys(toAccountNo);

		 Thread.sleep(1000);
		 By toAccount = By.xpath("//div[contains(@class,'ng-star-inserted') and contains(text(),'"+toAccountNo+"')]/parent::div/parent::li");
		 applyExplicitWaitsUntilElementVisible(toAccount, 10);
		 driver.findElement(toAccount).click();;
		 scroll.scrollInToView(od.payments_beneficiaryBankBic);
		od.payments_beneficiaryBankBic.sendKeys(externalData.getFieldData(TSID,"Scheduled","Beneficiary Bank Bic"));
		od.payments_beneficiaryCountryOfIncorporationDropdown.sendKeys(externalData.getFieldData(TSID,"Scheduled","Beneficiary Country Of Incorporation"));
		od.payments_Amount.sendKeys(externalData.getFieldData(TSID,"Scheduled","Amount"));
		od.payments_AddSubInstructionButton.click();
		od.payments_NextArrowButtonTransferSubInstruction.click();		
		
		if(((externalData.getFieldData(TSID,"Scheduled","Retry-Enable Auto Retry")).equalsIgnoreCase("Y") || (externalData.getFieldData(TSID,"Scheduled","Retry-Enable Auto Retry")).equalsIgnoreCase("Yes"))){
			od.payments_RetrySlider.click();
		}
		od.payments_NextArrowButtonRetryMechanism.click();
		
		if(((externalData.getFieldData(TSID,"Scheduled","Notification-Notification Alerts")).equalsIgnoreCase("Y") || (externalData.getFieldData(TSID,"Scheduled","Notification-Notification Alerts")).equalsIgnoreCase("Yes"))){
			od.payments_NotificationAlertSlider.click();
		}
		
		 applyExplicitWaitsUntilElementClickable(od.payments_DealsummaryIcon,Duration.ofSeconds(5));
		 od.payments_DealsummaryIcon.click();
		 applyExplicitWaitsUntilElementClickable(od.deals_SummaryRefId,Duration.ofSeconds(5));
	     String dealId=od.deals_SummaryRefId.getText();
	    scroll.scrollInToView(od.payments_DealSubmitButton);
		 applyExplicitWaitsUntilElementClickable(od.payments_DealSubmitButton,Duration.ofSeconds(10));
		 od.payments_DealSubmitButton.click();
		 applyExplicitWaitsUntilElementClickable(od.payments_DealYesButton,Duration.ofSeconds(10));
		 od.payments_DealYesButton.click();
		 applyExplicitWaitsUntilElementClickable(od.payments_DealOkButton,Duration.ofSeconds(10));
		 od.payments_DealOkButton.click();
		 
		return dealId;

	}

	public void approveDealFromDealChecker(String dealId1) throws Exception {
		 applyExplicitWaitsUntilElementClickable(od.deal_SideMenuIcon,Duration.ofSeconds(15));
		 od.deal_SideMenuIcon.click();
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_Button1,Duration.ofSeconds(15));
		 od.dealChecker_Button1.click();
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_searchSelect,Duration.ofSeconds(25));
		 dropdown.selectByVisibleText(od.dealChecker_searchSelect,"Deal Id");
		 od.dealChecker_searchBar.sendKeys(dealId);
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_searchButton,Duration.ofSeconds(25));
		 od.dealChecker_searchButton.click();
		 applyExplicitWaitsUntilElementClickable( od.dealChecker_showMenu,Duration.ofSeconds(40));
		 od.dealChecker_showMenu.click();
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_Open,Duration.ofSeconds(25));
		 od.dealChecker_Open.click();
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_addComments,Duration.ofSeconds(10));
		 jsClick.click(od.dealChecker_addComments);
		 od.dealChecker_addNote.sendKeys("Ok approved");
		 od.dealChecker_okCommentbutton.click();
		 jsClick.click(od.dealChecker_approveAllRadioButton);
		 od.dealChecker_ApproveButton.click();
		 applyExplicitWaitsUntilElementClickable(od.payments_DealYesButton,Duration.ofSeconds(40));
		 od.payments_DealYesButton.click();
		 applyExplicitWaitsUntilElementClickable(od.payments_DealOkButton,Duration.ofSeconds(40));
		 od.payments_DealOkButton.click();
	}
	}
