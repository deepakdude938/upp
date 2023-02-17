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
	
	@FindBy(id="txt-deals-instruction-fundTransferBasic-name-v1")
	public WebElement linkedInstruction_basicNameTxt;
	
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
	
	@FindBy(xpath = "//span[contains(@class,'text-label')]//span")
	public WebElement linkedInstruction_DealId;
	
	
	@FindBy(xpath="(//button[normalize-space()='Yes'])[1]")
	public WebElement payments_DealYesButton;
	
	@FindBy(xpath="(//button[normalize-space()='OK'])[1]")
	public WebElement payments_DealOkButton;
	
	//dealChecker objects
	
	@FindBy(xpath="//span[contains(text(),'REF')]")
	public WebElement deals_SummaryRefId;
	
	@FindBy(xpath="//a[@href='/deals/DEALCHECKER']")
	public WebElement dealChecker_Button;
    	
	@FindBy(id="sel-generic-searchBar-searchSelect-v1")
	public WebElement dealChecker_searchSelect;
	
	@FindBy(xpath="//input[@id='sel-generic-searchBar-searchInput-v1']")
	public WebElement dealChecker_searchBar;
	
	@FindBy(id="btn-generic-searchBar-search-v1")
	public WebElement dealChecker_searchButton;
	
	@FindBy(id="ic-generic-menu-showMenu-v1")
	public WebElement dealChecker_showMenu;
	
	@FindBy(xpath="//div[contains(text(),'open')]")
	public WebElement dealChecker_Open;
	
	@FindBy(xpath="(//input[@id='rad-generic-radio-select-v1'])[1]")
	public WebElement dealChecker_approveAllRadioButton;
	@FindBy(xpath="(//i[@class='ui-icon ui-icon-note warning'])[1]")
	public WebElement dealChecker_addComments;
	
	@FindBy(id="btn-deals-summary-approve-v1")
	public WebElement dealChecker_ApproveButton;
	
	@FindBy(id="txt-deals-accounts-addUpdateAdvice-noteTxt-v1")
	public WebElement dealChecker_addNote;
	
	@FindBy(id="btn-deals-accounts-addUpdateAdvice-ok-v1")
	public WebElement dealChecker_okCommentbutton;
	
	//Logout Object
	@FindBy(xpath = "//i[contains(@class,'logout ')]")
	public WebElement logout;
	
//	Transaction Maker objects
	@FindBy(xpath = "//label[contains(text(),' TRANSACTIONS ')]")
	public WebElement TxnMaker_Transaction;
	
	@FindBy(xpath = "//a[@href='/transactions/TRANSACTIONMAKER']")
	public WebElement TxnMaker_TrasactionMaker;
	
	@FindBy(xpath = "((//div[@aria-rowindex='2'])[2]//input)[2]")
	public WebElement TxnMaker_searchDealId;
	
	@FindBy(xpath = "//div[@class='ag-pinned-left-cols-container']//div[@class='ui-checkbox']//span")
	public WebElement TxnMaker_txnCheckbox;
	
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement TxnMaker_submitBtn;
	
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement TxnMaker_okBtn;
	
//	Transaction Checker objects
	@FindBy(xpath = "//label[contains(text(),' TRANSACTIONS ')]")
	public WebElement TxnChecker_Transaction;
	
	@FindBy(xpath = "//a[@href='/transactions/TRANSACTIONCHECKER']")
	public WebElement TxnChecker_TrasactionChecker;
	
	@FindBy(xpath = "((//div[@aria-rowindex='2'])[2]//input)[2]")
	public WebElement TxnChecker_searchDealId;
	
	@FindBy(xpath = "//div[@class='ag-pinned-left-cols-container']//div[@class='ui-checkbox']//span")
	public WebElement TxnChecker_txnCheckbox;
	
	@FindBy(xpath = "(//div[contains(@class,' ag-cell-first-right-pinned')]//i)[3]")
	public WebElement TxnChecker_comment;
	
	@FindBy(id = "txt-deals-accounts-addUpdateAdvice-noteTxt-v1")
	public WebElement TxnChecker_note;
	
	@FindBy(id = "btn-deals-accounts-addUpdateAdvice-ok-v1")
	public WebElement TxnChecker_ok;
	
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement TxnChecker_submitBtn;
	
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement TxnChecker_okBtn;
	
}

