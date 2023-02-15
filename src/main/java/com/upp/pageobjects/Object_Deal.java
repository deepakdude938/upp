package com.upp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_Deal extends BaseClass{
	
	public Object_Deal() {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="txt-generic-login-username-v1")
	public WebElement username;
	
	@FindBy(id="txt-generic-login-password-v1")
	public WebElement password;
	
	@FindBy(id="btn-generic-login-submit-v1")
	public WebElement loginIn;
	
	@FindBy(xpath="//label[contains(@class,'deal_icon sidemenu_icon')]")
	public WebElement deal_SideMenuIcon;
	
	@FindBy(id="menu-generic-sidemenu-Deals-SM_NEW_DEAL-v1")
	public WebElement newDealButton;
	
	@FindBy(id="txt-deals-basicDetails-name-v1")
	public WebElement newDeal;
	
	@FindBy(xpath="//select[@id='sel-deals-basicDetails-businessSegment-v1']")
	public WebElement businessSegmentDropDown;
	
	@FindBy(xpath="//select[@id='sel-deals-basicDetails-country-v1']")
	public WebElement countryIndiaDropDown;
	
	@FindBy(xpath="//input[@id='allowBeneficiaries']")
	public WebElement beneficiariesCheckBox;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[3]")
	public WebElement partyResponsibility;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement transactionCategory;
	
	@FindBy(xpath="//button[normalize-space()='Save']")
	public WebElement saveButton;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[1]")
	public WebElement deals_ProcessingUnits;

	@FindBy(xpath="//span[@class='ng-tns-c92-7 ui-autocomplete-list-item-option']")
	public WebElement deals_selectAll;
	
	@FindBy(id="btn-deals-basicDetails-next-v1")
	public WebElement nextBtn;
	
	@FindBy(xpath=" //select[@id='country']")
	public WebElement country;
	
	@FindBy(xpath = "(//select[@class='ui-dropdown-select full-border'])[2]")
	public WebElement currency;
	
	@FindBy(xpath ="//select[@id='accountIdentifierKey']")
	public WebElement physical;
	
	@FindBy(xpath="//select[@id='sel-generic-searchBar-searchSelect-v1']")
	public WebElement searchBy;
	
	@FindBy(id="sel-generic-searchBar-searchInput-v1")
	public WebElement searchTextBox;
	
	@FindBy(id="btn-generic-searchBar-search-v1")
	public WebElement searchButton;
	
	@FindBy(id="ic-deals-account-accountDetails-addAccount-v1")
	public WebElement accounts_addAccount;
	
//	Objects for Linked Instructions
	
	@FindBy(xpath = "//div[@title='Linked']/i")
	public WebElement linkedInstruction_linkedBtn;
	
	@FindBy(id="btn-deals-accounts-addUpdateAdvice-ok-v1")
	public WebElement linkedInstruction_addAccountBtn;
	
	@FindBy(xpath="//div[text()=' Payment ']")
	public WebElement linkedInstruction_paymentBtn;
	
	
	@FindBy(id="btn-deals-instructions-instructionTypeList-proceed-v1")
	public  WebElement linkedInstruction_proccedbtn;
	
	@FindBy(xpath="//select[@id='sel-deals-instruction-fundTransferBasic-purpose-v1']")
	public WebElement linkedInstruction_purposeddl;
	
	@FindBy(xpath="//ui-autocomplete[@id='sel-deals-instruction-fundTransferBasic-sourceAccount-v1']")
	public WebElement linkedInstruction_SourceAccounttxt;
	
	@FindBy(xpath="//ui-autocomplete[@id='sel-deals-instruction-fundTransferBasic-sourceAccount-v1']//input")
	public WebElement linkedInstruction_Accountvalue;
	
	@FindBy(xpath="//select[@id='sel-deals-instruction-fundTransferBasic-balanceConsideration-v1']")
	public WebElement linkedInstruction_Balanceddl;
	
	@FindBy(id="ic-deals-instruction-fundTransferBasic-next-v1")
	public WebElement linkedInstruction_NextBtn;	

	@FindBy(id="ic-instructions-tabLinkedConfig-next-v1")
	public WebElement linkedInstruction_LinkedConfigNextBtn;
		
	@FindBy(xpath="//td[contains(@class,' ui-day-today')]")
	public WebElement linkedInstruction_Todaydate;
	
	@FindBy(id="txt-generic-datePicker-input-v1")
	public WebElement linkedInstruction_Executiondate;
	
	@FindBy(xpath="//select[contains(@id,'scheduleAt')]")
	public WebElement linkedInstruction_schedule;
	
	@FindBy(xpath="//select[contains(@id,'holidayAction')]")
	public WebElement linkedInstruction_HolidayAction;
	
	@FindBy(id="txt-generic-timePicker-input-v1")
	public WebElement linkedInstruction_TimePicker;
	
	@FindBy(id="ic-deals-instruction-fundTransferSchedule-next-v1")
	public WebElement linkedInstruction_ScheduleNextBtn;
	
	@FindBy(xpath ="//div[@class='ui-col-md-2']//label[text()='Instrument']/following-sibling::ui-autocomplete//input")
	public WebElement linkedInstruction_Instrumentddl;
	
	@FindBy(id="payment-amount-txt-v1")
	public WebElement linkedInstruction_Amount;
	
	@FindBy(xpath="//ui-autocomplete[@id='sel-deals-basicDetails-processingUnit-v1']//input")
	public WebElement linkedInstruction_ToAccountddl;
	
	
	@FindBy(xpath="//div[@class='ng-tns-c92-334 ng-star-inserted']")
	public WebElement linkedInstruction_ToAccountValue;
	
	@FindBy(xpath = "//select[@id='payment-beneficiaryCountryOfIncorporation-sel-v1']")
	public WebElement linkedInstruction_Incorporationddl;
	
	@FindBy(id = "payment-beneficiaryBankBic-txt-v1")
	public WebElement linkedInstruction_BankBic;
	
	@FindBy(id="btn-deals-instruction-fundTransferSubInstruction-add-v1")
	public WebElement linkedInstruction_AddBtn;
	
	@FindBy(xpath="//div[@title='Summary']//i")
	public WebElement linkedInstruction_Summary;
	
	@FindBy(id = "btn-deals-summary-submit-v1")
	public WebElement linkedInstruction_Submit;
	
	@FindBy(xpath="//button[text()='Yes']")
	public WebElement linkedInstruction_YesBtn;
	
	@FindBy(xpath="//button[text()='OK']")
	public WebElement linkedInstruction_OkBtn;
}

