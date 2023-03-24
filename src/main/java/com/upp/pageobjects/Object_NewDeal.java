package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_NewDeal extends BaseClass {

	public Object_NewDeal() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "txt-generic-login-username-v1")
	public WebElement username;

	@FindBy(id = "txt-generic-login-password-v1")
	public WebElement password;

	@FindBy(id = "btn-generic-login-submit-v1")
	public WebElement loginIn;

	@FindBy(xpath = "//label[contains(@class,'deal_icon sidemenu_icon')]")
	public WebElement deal_SideMenuIcon;

	@FindBy(id = "menu-generic-sidemenu-Deals-SM_NEW_DEAL-v1")
	public WebElement newDealButton;

	@FindBy(id = "txt-deals-basicDetails-name-v1")
	public WebElement newDeal;

	@FindBy(xpath = "//select[@id='sel-deals-basicDetails-businessSegment-v1']")
	public WebElement businessSegmentDropDown;

	@FindBy(xpath = "//select[@id='sel-deals-basicDetails-country-v1']")
	public WebElement countryIndiaDropDown;

	@FindBy(xpath = "//input[@id='allowBeneficiaries']")
	public WebElement beneficiariesCheckBox;

	@FindBy(xpath = "//label[text()='Product']/following::select[contains(@id,'sel-deals-basicDetails-product')]")
	public WebElement deal_Product;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[3]")
	public WebElement partyResponsibility;
	
	@FindBy(xpath = "//div[@class='ui-dialog-header']")
	public WebElement responsibilityAttributePopup;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement transactionCategory;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	public WebElement saveButton;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[1]")
	public WebElement deals_ProcessingUnits;

	@FindBy(xpath = "//span[contains(text(),'Select All')]")
	public WebElement deals_selectAll;

	@FindBy(id = "btn-deals-basicDetails-next-v1")
	public WebElement nextBtn;

	@FindBy(xpath = " //select[@id='country']")
	public WebElement country;

	@FindBy(xpath = "(//select[@class='ui-dropdown-select full-border'])[2]")
	public WebElement currency;

	@FindBy(xpath = "//select[@id='accountIdentifierKey']")
	public WebElement physical;

	@FindBy(xpath = "//select[@id='sel-generic-searchBar-searchSelect-v1']")
	public WebElement searchBy;

	@FindBy(id = "sel-generic-searchBar-searchInput-v1")
	public WebElement searchTextBox;

	@FindBy(id = "btn-generic-searchBar-search-v1")
	public WebElement searchButton;

	@FindBy(id = "ic-deals-account-accountDetails-addAccount-v1")
	public WebElement accounts_addAccount;

	// Objects for flexible finding attributes
	@FindBy(xpath = "(//*[@id='txt-deals-basicDetails-addAttribute-attributeValue-v1']//select)[1]")
	public WebElement newDeal_spiltTypeAttribute;
	
	@FindBy(xpath = "(//*[@id='txt-deals-basicDetails-addAttribute-attributeValue-v1']//select)[2]")
	public WebElement newDeal_commissionTypeAttribute;
	
	@FindBy(xpath = "(//*[@id='txt-deals-basicDetails-addAttribute-attributeValue-v1']//input)[2]")
	public WebElement newDeal_percentageCommissionAttribute;
	
	// Objects for ecommerce
	@FindBy(xpath = "//select[@id='sel-deals-basicDetails-status-v1']")
	public WebElement ecommerce_status;
	
	@FindBy(xpath = "(//input[@id='txt-generic-datePicker-input-v1'])[1]")
	public WebElement ecommerce_validFrom;
	
	@FindBy(xpath = "(//input[@id='txt-generic-datePicker-input-v1'])[2]")
	public WebElement ecommerce_validTill;
	
	@FindBy(xpath = "//td[contains(@class,'today')]")
	public WebElement startDate;
	
	@FindBy(xpath = "//td[contains(@class,'today')]/following::td[3]")
	public WebElement endDate;	
	
	@FindBy(xpath = "((//div[@col-id='accountNumber'])[2]")
	public WebElement ecommerceAccount;
	
	//	Objects for Linked Instructions

	@FindBy(xpath = "//div[@title='Linked']/i")
	public WebElement linkedInstruction_linkedBtn;

	@FindBy(id = "btn-deals-accounts-addUpdateAdvice-ok-v1")
	public WebElement linkedInstruction_addAccountBtn;

	@FindBy(id = "txt-deals-instruction-fundTransferBasic-name-v1")
	public WebElement linkedInstruction_basicNameTxt;

	@FindBy(xpath = "//div[text()=' Payment ']")
	public WebElement linkedInstruction_paymentBtn;

	@FindBy(id = "btn-deals-instructions-instructionTypeList-proceed-v1")
	public WebElement linkedInstruction_proccedbtn;

	@FindBy(xpath = "//select[@id='sel-deals-instruction-fundTransferBasic-purpose-v1']")
	public WebElement linkedInstruction_purposeddl;

	@FindBy(xpath = "//ui-autocomplete[@id='sel-deals-instruction-fundTransferBasic-sourceAccount-v1']")
	public WebElement linkedInstruction_SourceAccounttxt;

	@FindBy(xpath = "//ui-autocomplete[@id='sel-deals-instruction-fundTransferBasic-sourceAccount-v1']//input")
	public WebElement linkedInstruction_Accountvalue;

	@FindBy(xpath = "//select[@id='sel-deals-instruction-fundTransferBasic-balanceConsideration-v1']")
	public WebElement linkedInstruction_Balanceddl;

	@FindBy(id = "ic-deals-instruction-fundTransferBasic-next-v1")
	public WebElement linkedInstruction_NextBtn;

	@FindBy(id = "ic-instructions-tabLinkedConfig-next-v1")
	public WebElement linkedInstruction_LinkedConfigNextBtn;
	
	@FindBy(id="ic-instructions-tabSweepIn-next-v1")
	public WebElement linkedInstruction_sweepNext;

	@FindBy(xpath = "//td[contains(@class,' ui-day-today')]")
	public WebElement linkedInstruction_Todaydate;

	@FindBy(id = "txt-generic-datePicker-input-v1")
	public WebElement linkedInstruction_Executiondate;

	@FindBy(xpath = "//select[contains(@id,'scheduleAt')]")
	public WebElement linkedInstruction_schedule;

	@FindBy(xpath = "//select[contains(@id,'holidayAction')]")
	public WebElement linkedInstruction_HolidayAction;

	@FindBy(id = "txt-generic-timePicker-input-v1")
	public WebElement linkedInstruction_TimePicker;

	@FindBy(id = "ic-deals-instruction-fundTransferSchedule-next-v1")
	public WebElement linkedInstruction_ScheduleNextBtn;

	@FindBy(xpath = "//div[@class='ui-col-md-2']//label[text()='Instrument']/following-sibling::ui-autocomplete//input")
	public WebElement linkedInstruction_Instrumentddl;

	@FindBy(id = "payment-amount-txt-v1")
	public WebElement linkedInstruction_Amount;

	@FindBy(xpath = "//ui-autocomplete[@id='sel-deals-basicDetails-processingUnit-v1']//input")
	public WebElement linkedInstruction_ToAccountddl;

	@FindBy(xpath = "//div[@class='ng-tns-c92-334 ng-star-inserted']")
	public WebElement linkedInstruction_ToAccountValue;

	@FindBy(xpath = "//select[@id='payment-beneficiaryCountryOfIncorporation-sel-v1']")
	public WebElement linkedInstruction_Incorporationddl;

	@FindBy(id = "payment-beneficiaryBankBic-txt-v1")
	public WebElement linkedInstruction_BankBic;

	@FindBy(id = "btn-deals-instruction-fundTransferSubInstruction-add-v1")
	public WebElement linkedInstruction_AddBtn;

	@FindBy(xpath = "//div[@title='Summary']//i")
	public WebElement linkedInstruction_Summary;

	@FindBy(id = "btn-deals-summary-submit-v1")
	public WebElement linkedInstruction_Submit;

	@FindBy(xpath = "//button[text()='Yes']")
	public WebElement linkedInstruction_YesBtn;

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement linkedInstruction_OkBtn;

	@FindBy(xpath = "//span[contains(@class,'text-label')]//span")
	public WebElement linkedInstruction_DealId;

	@FindBy(xpath = "(//button[normalize-space()='Yes'])[1]")
	public WebElement payments_DealYesButton;

	@FindBy(xpath = "(//button[normalize-space()='OK'])[1]")
	public WebElement payments_DealOkButton;

	// dealChecker objects

	@FindBy(xpath = "//span[contains(text(),'REF')]")
	public WebElement deals_SummaryRefId;

	@FindBy(xpath = "//a[@href='/deals/DEALCHECKER']")
	public WebElement dealChecker_Button;

	@FindBy(id = "sel-generic-searchBar-searchSelect-v1")
	public WebElement dealChecker_searchSelect;

	@FindBy(xpath = "//input[@id='sel-generic-searchBar-searchInput-v1']")
	public WebElement dealChecker_searchBar;

	@FindBy(id = "btn-generic-searchBar-search-v1")
	public WebElement dealChecker_searchButton;

	@FindBy(id = "ic-generic-menu-showMenu-v1")
	public WebElement dealChecker_showMenu;

	@FindBy(xpath = "//div[contains(text(),'open')]")
	public WebElement dealChecker_Open;

	@FindBy(xpath = "(//input[@id='rad-generic-radio-select-v1'])[1]")
	public WebElement dealChecker_approveAllRadioButton;
	@FindBy(xpath = "(//i[@class='ui-icon ui-icon-note warning'])[1]")
	public WebElement dealChecker_addComments;

	@FindBy(id = "btn-deals-summary-approve-v1")
	public WebElement dealChecker_ApproveButton;

	@FindBy(id = "txt-deals-accounts-addUpdateAdvice-noteTxt-v1")
	public WebElement dealChecker_addNote;

	@FindBy(id = "btn-deals-accounts-addUpdateAdvice-ok-v1")
	public WebElement dealChecker_okCommentbutton;

	// Logout Object
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

	// Parties Object
	@FindBy(xpath = "//div[@title='Parties']/i")
	public WebElement parties_icon;

	@FindBy(id = "btn-parties-parties-getStarted-v1")
	public WebElement parties_GetStarted;

	@FindBy(id = "btn-parties-addLinkParty-addNewParty-v1")
	public WebElement parties_AddnewParty;

	@FindBy(id = "sel-parties-partyBasic-customerId-v1")
	public WebElement parties_CustomerID;

	@FindBy(id = "sel-parties-partyBasic-name-v1")
	public WebElement parties_PartyName;

	@FindBy(id = "txt-generic-autocomplete-input-v1")
	public WebElement parties_Responsibility;

	@FindBy(xpath = "//div[contains(@class,'ui-autocomplete-list-item-div')]//div")
	public WebElement parties_Responsibility_dropdown;

	@FindBy(id = "sel-parties-partyBasic-remarks-v1")
	public WebElement parties_Remarks;

	@FindBy(xpath = "(//span[@class='ui-pseudo-checkbox'])[2]")
	public WebElement parties_eCommerceCheckbox;

	@FindBy(xpath = "(//div[normalize-space()='Commission Plan'])[1]/following-sibling::div//select")
	public WebElement parties_CommissionPlan;

	@FindBy(xpath = "//label[normalize-space()='Document Nature']/following-sibling::ui-dropdown//select")
	public WebElement parties_DocumentNature1;

//	label[normalize-space()='Document Nature']/following-sibling::ui-dropdown//select

	@FindBy(id = "party-participant-id")
	public WebElement parties_ParticipantId;

	@FindBy(id = "btn-parties-partyBasic-updateParty-v1")
	public WebElement parties_BasicNextButton;

	@FindBy(id = "ic-generic-partyContacts-getStarted-v1")
	public WebElement parties_AddContact;

	@FindBy(id = "txt-generic-partyContacts-name-v1")
	public WebElement parties_ContactName;

	@FindBy(xpath = "(//span[@class='ui-pseudo-checkbox'])[4]")
	public WebElement parties_AuthrorizedSignatoryYes;

	@FindBy(id = "txt-generic-partyContacts-email-v1")
	public WebElement parties_Email;

	@FindBy(id = "btn-generic-partyContacts-addUpdate-v1")
	public WebElement parties_AddButton;

	@FindBy(xpath = "//p[normalize-space()='Accounts']")
	public WebElement parties_AccountsTab;

	@FindBy(id = "btn-generic-partyAccounts-getStarted-v1")
	public WebElement parties_AddAccounts;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement parties_PaymentSystem;

	@FindBy(xpath = "//div[contains(text(),'BT')]")
	public WebElement parties_PaymentSystem_BT;

	@FindBy(id = "payment-beneficiaryBankBic-txt-v1")
	public WebElement parties_beneficiaryBankBic;

	@FindBy(id = "payment-beneficiaryCountry-sel-v1")
	public WebElement parties_BeneficiaryCountry;

	@FindBy(id = "payment-to-txt-v1")
	public WebElement parties_paymentTo;

	@FindBy(id = "payment-beneficiaryCurrency-txt-v1")
	public WebElement parties_beneficiaryCurrency;

	@FindBy(id = "btn-generic-partyAccounts-addUpdate-v1")
	public WebElement parties_partyAccountsAddButton;

	@FindBy(xpath = "//p[normalize-space()='Documents']")
	public WebElement parties_DocumentsTab;

	@FindBy(id = "btn-generic-partyDocuments-getStarted-v1")
	public WebElement parties_AddDocument;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement parties_DocumentType;

	@FindBy(xpath = "//div[contains(text(),'Blueprint')]")
	public WebElement parties_DocumentsType_Blueprint;

	@FindBy(xpath = "(//select[@class='ui-dropdown-select'])[5]")
	public WebElement parties_DocumentNature;

	@FindBy(id = "btn-generic-partyDocuments-addUpdate-v1")
	public WebElement parties_DocumentsAddButton;

	@FindBy(xpath = "//div[contains(text(),'Architect certificate')]")
	public WebElement parties_DocumentsType_Architect_certificate;

	@FindBy(xpath = "//div[@title='Scheduled']/i")
	public WebElement payments_ScheduledInstructionIcon;

	@FindBy(id = "btn-deals-instructions-getStarted-v1")
	public WebElement payments_GetStarted;

	@FindBy(id = "btn-deals-instructions-instructionTypeList-proceed-v1")
	public WebElement payments_Proceed;

	@FindBy(id = "txt-deals-instruction-fundTransferBasic-name-v1")
	public WebElement payments_BasicName;

	@FindBy(xpath = "//select[@id='sel-deals-instruction-fundTransferBasic-purpose-v1']")
	public WebElement payments_Purpose;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[1]")
	public WebElement payments_SourceAccount;

	@FindBy(xpath = "//select[@id='sel-deals-instruction-fundTransferBasic-balanceConsideration-v1']")
	public WebElement payments_BalanceConsideration;

	@FindBy(xpath = "//ui-switch[@id='sw-deals-instruction-fundTransferBasic-split-v1']//span[@class='slider round']")
	public WebElement payments_SplitBalanceSlider;

	@FindBy(xpath = "//ui-switch[@id='sw-deals-instruction-fundTransferBasic-partial-v1']//span[@class='slider round']")
	public WebElement payments_PartialpaymentSlider;

	@FindBy(id = "ic-deals-instruction-fundTransferBasic-next-v1")
	public WebElement payments_NextArrowButtonTransferBasic;
	
	@FindBy(id = "ic-instructions-tabSweepIn-next-v1")
	public WebElement payments_NextArrowButton;
	

	@FindBy(xpath = "//ui-switch[@id='sw-deals-instruction-fundTransferSchedule-repeating-v1']//span[@class='slider round']")
	public WebElement payments_Repeatingslider;

	@FindBy(id = "txt-generic-datePicker-input-v1")
	public WebElement payments_ExecutionDate;

	@FindBy(xpath = "//select[@id='sel-deals-instruction-fundTransferSchedule-scheduleAt-v1']")
	public WebElement payments_ScheduleAt;

	@FindBy(xpath = "//select[@id='sel-deals-instruction-fundTransferSchedule-holidayAction-v1']")
	public WebElement payments_HolidayAction;

	@FindBy(id = "ic-deals-instruction-fundTransferSchedule-next-v1")
	public WebElement payments_NextArrowButtonTransferSchedule;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[4]")
	public WebElement payments_Instrument;

	@FindBy(id = "payment-to-sel-v1")
	public WebElement payments_ToAccountDropdown;

	@FindBy(id = "payment-beneficiaryBankBic-txt-v1")
	public WebElement payments_beneficiaryBankBic;
	
	@FindBy(id = "payment-beneficiaryCountry-sel-v1")
	public WebElement payments_beneficiaryCountry;
	
	@FindBy(xpath = "//div[@class='ui-tab truncate ng-star-inserted']")
	public WebElement payments_SummaryButton;
	
	@FindBy(id = "btn-deals-instructions-summary-simulate-v1")
	public WebElement payments_SimulateButton;
	
	@FindBy(xpath = "(//div[contains(@class,'ui-grid-cell-inner simulate-date ui-text-xs')])[1]//i")
	public WebElement payments_SimulateExecutionStrikeDate;
	
	@FindBy(xpath = "(//div[contains(@class,'ui-grid-cell-inner simulate-date ui-text-xs')])[1]/div[2]")
	public WebElement payments_SimulateExecutionActualDate;
	
	@FindBy(id = "btn-instructions-simulateSchedule-close-v1")
	public WebElement payments_SimulateCloseButton;
	

	@FindBy(id = "payment-beneficiaryCountryOfIncorporation-sel-v1")
	public WebElement payments_beneficiaryCountryOfIncorporationDropdown;

	@FindBy(id = "payment-amount-txt-v1")
	public WebElement payments_Amount;

	@FindBy(id = "btn-deals-instruction-fundTransferSubInstruction-add-v1")
	public WebElement payments_AddSubInstructionButton;

	@FindBy(id = "ic-deals-instruction-fundTransferSubInstruction-next-v1")
	public WebElement payments_NextArrowButtonTransferSubInstruction;

	@FindBy(xpath = "//ui-switch[@id='sw-instructions-tabRetryMechanism-retryEnabled-v1']//span[@class='slider round']")
	public WebElement payments_RetrySlider;

	@FindBy(id = "ic-instructions-tabRetryMechanism-next-v1")
	public WebElement payments_NextArrowButtonRetryMechanism;

	@FindBy(xpath = "//ui-switch[@id='sw-instructions-tabNotification-notificationEnabled-v1']//span[@class='slider round']")
	public WebElement payments_NotificationAlertSlider;

	@FindBy(xpath = "//div[@title='Summary']/i")
	public WebElement payments_DealsummaryIcon;

	@FindBy(id = "btn-deals-summary-submit-v1")
	public WebElement payments_DealSubmitButton;

//	@FindBy(xpath="(//button[normalize-space()='Yes'])[1]")
//	public WebElement payments_DealYesButton;
//	
//	@FindBy(xpath="(//button[normalize-space()='OK'])[1]")
//	public WebElement payments_DealOkButton;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-search'])[3]")
	public WebElement partyResponsibilityinput;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-search'])[1]")
	public WebElement deals_ProcessingUnitsSearch;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-search'])[2]")
	public WebElement transactionCategoryInput;
	
	@FindBy(xpath="//button[normalize-space()='Save']")
	public List< WebElement> basicDetails_SaveButton_List;
	
	@FindBy(xpath="//div[@title='Logout']")
	public WebElement logOutIcon;
	
	@FindBy(xpath="(//a[normalize-space()='Deal Checker'])[1]")
	public WebElement dealChecker_Button1;
	
	@FindBy(xpath="//a[contains(text(),'Live Deals')]")
	public WebElement liveDealIcon;
    
	@FindBy(xpath="(//div[normalize-space()='edit'])[1]")
	public WebElement deal_EditIcon;
	
	@FindBy(xpath="(//span[@class='ui-pseudo-checkbox'])[1]")
	public WebElement parties_LinkPartyCheckboxIcon;
	
	@FindBy(id="btn-parties-partyContactList-addPartyContact-v1")
	public WebElement parties_ConatctPlusIcon;
	
	@FindBy(id="btn-parties-partyAccountList-createPartyAccount-v1")
	public WebElement parties_AccountPlusIcon;
	
	@FindBy(xpath="(//button[@id='btn-parties-partyDocument-createPartyDocument-v1'])[1]")
	public WebElement parties_DocumentsPlusIcon;
	
	@FindBy(xpath ="(//button[@id='btn-parties-linkParty-add-v1'])[1]")
	public WebElement parties_addPartyPlusIcon;
	
	@FindBy(xpath ="(//span[@class='slider round'])[4]")
	public WebElement payments_SweepInSlider;
	
	@FindBy(xpath ="(//i[@id='ic-instructions-tabSweepIn-next-v1'])[1]")
	public WebElement payments_SweepinNextButton;
	@FindBy(xpath="//select[@id='sel-deals-addUpdateBudget-frequency-v1']")
	public WebElement budget_Interval;
	
	@FindBy(xpath="//select[@id='sel-deals-addUpdateBudget-duration-v1']")
	public WebElement budget_Duration;
	
	@FindBy(id="btn-deals-addUpdateBudget-add-v1")
	public WebElement budget_AddButton;
	
	
	@FindBy(id="txt-deals-addUpdateBudget-allocatedAmount-v1")
	public WebElement budget_allocatedAmount;
	
	@FindBy(id="tab-deals-budgetWizard-v1")
	public WebElement budget_BudgetIcon;
	
	@FindBy(id="btn-deals-budgetGroup-add-v1")
	public WebElement budget_CreateBudget;
	
	@FindBy(id="txt-deals-addBudgetGroup-name-v1")
	public WebElement budget_AddBudgetName;
	
	@FindBy(id="txt-generic-autocomplete-input-v1")
	public WebElement budget_BudgetSourceAccount;
	
	@FindBy(id="btn-deals-addBudgetGroup-save-v1")
	public WebElement budget_AddBudget;
	
	@FindBy(xpath="//div[@id='btn-deals-budgetDetail-addBudget-v1']//i")
	public WebElement budget_budgetDetailsAddBudget;
	
	@FindBy(id="txt-deals-addUpdateBudget-purpose-v1")
	public WebElement budget_Purpose;
	
	
	@FindBy(xpath="//label[normalize-space()='Budget Carry forward']/following::span[@class='slider round']")
	public WebElement budget_budgetCarryForward;
	
	@FindBy(id="txt-generic-destinationSearch-search-v1")
	public WebElement budget_budgetDestination;
	
	@FindBy(xpath = "//input[@id='txt-generic-timePicker-input-v1']")
	public WebElement payments_ScheduleTime;
	
	@FindBy(id = "txt-generic-autocomplete-input-v1")
	public WebElement payments_BudgetPurpose;
	
	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[5]")
	public WebElement payments_budgetPurpose;
	
	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[6]")
	public WebElement payments_ToAccountInputBox;
	
	@FindBy(xpath = "(//div[normalize-space()='LTTest'])[1]")
	public WebElement parties_Account_LTTest;
	
	@FindBy(id = "payment-accountType-txt-v1")
	public WebElement parties_Account_accountType;
	
	@FindBy(id = "payment-beneficiaryCountryOfIncorporation-txt-v1")
	public WebElement parties_Account_beneficiaryCountryOfIncorporation;
	
	@FindBy(id = "payment-beneficiaryCountry-txt-v1")
	public WebElement parties_Account_beneficiaryCountry;
	
	@FindBy(id = "payment-werwer-txt-v1")
	public WebElement parties_Account_werwer;
	
	@FindBy(id = "payment-trteo-txt-v1")
	public WebElement parties_Account_trteo;
	
	@FindBy(id ="payment-amount-txt-v1")
	public WebElement parties_Accounts_Amount;
	
	@FindBy(id ="payment-beneficiaryCountryOfIncorporation-sel-v1")
	public WebElement parties_Accounts_beneficiaryCountryOfIncorporation;
	
	@FindBy(xpath ="//div[contains(text(),'BT_IN')]")
	public WebElement parties_PaymentSystem_BT_IN;
	
	@FindBy(id="payment-paymentType-sel-v1")
	public WebElement parties_PaymentType;
	
	@FindBy(id="payment-beneficiaryBankIfscCode-txt-v1")
	public WebElement parties_Accounts_beneficiaryBankIfscCode;
	
	@FindBy(id="payment-beneficiaryName-txt-v1")
	public WebElement parties_Accounts_beneficiaryName;
	
	@FindBy(id="payment-accountOrIban-sel-v1")
	public WebElement parties_Accounts_accountOrIban;
	
	@FindBy(id="payment-beneficiaryAddressLine1-txt-v1")
	public WebElement parties_Accounts_beneficiaryAddressLine1;
	
	@FindBy(id="payment-senderPop-txt-v1")
	public WebElement payments_senderPop;
	
	@FindBy(xpath ="//div[contains(text(),'LT_IN')]")
	public WebElement parties_PaymentSystem_LT_IN;
	
}
