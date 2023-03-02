package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_NewDeal extends BaseClass{
	
	public Object_NewDeal() {
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
	
	@FindBy(xpath = "(//*[@id='txt-deals-basicDetails-addAttribute-attributeValue-v1']//select)[1]")
	public WebElement newDeal_spiltTypeAttribute;
	
	@FindBy(xpath = "(//*[@id='txt-deals-basicDetails-addAttribute-attributeValue-v1']//select)[2]")
	public WebElement newDeal_commissionTypeAttribute;
	
	@FindBy(xpath = "(//*[@id='txt-deals-basicDetails-addAttribute-attributeValue-v1']//input)[2]")
	public WebElement newDeal_percentageCommissionAttribute;
	
	@FindBy(xpath="//select[@id='sel-deals-basicDetails-businessSegment-v1']")
	public WebElement businessSegmentDropDown;
	
	@FindBy(xpath="//select[@id='sel-deals-basicDetails-product-v1']")
	public WebElement basicDetails_ProductDropDown;	
	
	@FindBy(xpath="//select[@id='sel-deals-basicDetails-country-v1']")
	public WebElement countryIndiaDropDown;
	
	@FindBy(xpath = "//label[text()='Product']/following::select[contains(@id,'sel-deals-basicDetails-product')]")
	public WebElement deal_Product;
	
	@FindBy(xpath="//input[@id='allowBeneficiaries']")
	public WebElement beneficiariesCheckBox;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[3]")
	public WebElement partyResponsibility;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-search'])[3]")
	public WebElement partyResponsibilityinput;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement transactionCategory;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-search'])[2]")
	public WebElement transactionCategoryInput;
	
	@FindBy(xpath="//button[normalize-space()='Save']")
	public WebElement saveButton;
	
	@FindBy(xpath="//button[normalize-space()='Save']")
	public List< WebElement> basicDetails_SaveButton_List;
	
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
	public WebElement addAccountButton;
	
	@FindBy(id="tab-deals-partyWizard-v1")
	public WebElement parties_icon;
	
	@FindBy(id="//ui-checkbox[@formcontrolname='eCommerceEnabled']")
	public WebElement parties_ECommercePartyCheckbox;
	
	
	
	@FindBy(id="btn-parties-parties-getStarted-v1")
	public WebElement parties_GetStarted;
	
	@FindBy(id="btn-parties-addLinkParty-addNewParty-v1")
	public WebElement parties_AddnewParty;
	
	@FindBy(id="sel-parties-partyBasic-customerId-v1")
	public WebElement parties_CustomerID;
	
	@FindBy(id="sel-parties-partyBasic-name-v1")
	public WebElement parties_PartyName;
	
	@FindBy(id="txt-generic-autocomplete-input-v1")
	public WebElement parties_Responsibility;
	
	@FindBy(xpath="//div[contains(@class,'ui-autocomplete-list-item-div')]//div")
	public WebElement parties_Responsibility_dropdown;
	
	@FindBy(id="sel-parties-partyBasic-remarks-v1")
	public WebElement parties_Remarks;
	
	@FindBy(xpath="(//span[@class='ui-pseudo-checkbox'])[2]")
	public WebElement parties_eCommerceCheckbox;
	
	@FindBy(xpath="(//div[normalize-space()='Commission Plan'])[1]/following-sibling::div//select")
	public WebElement parties_CommissionPlan;
	
	@FindBy(xpath="//label[normalize-space()='Document Nature']/following-sibling::ui-dropdown//select")
	public WebElement parties_DocumentNature1;
	
//	label[normalize-space()='Document Nature']/following-sibling::ui-dropdown//select
	
	@FindBy(id="party-participant-id")
	public WebElement parties_ParticipantId;
	
	@FindBy(id="btn-parties-partyBasic-updateParty-v1")
	public WebElement parties_BasicNextButton;
	
	@FindBy(id="ic-generic-partyContacts-getStarted-v1")
	public WebElement parties_AddContact;
	
	@FindBy(id="txt-generic-partyContacts-name-v1")
	public WebElement parties_ContactName;
	
	@FindBy(xpath="(//span[@class='ui-pseudo-checkbox'])[4]")
	public WebElement parties_AuthrorizedSignatoryYes;
	
	@FindBy(id="txt-generic-partyContacts-email-v1")
	public WebElement parties_Email;
	
	@FindBy(id="btn-generic-partyContacts-addUpdate-v1")
	public WebElement parties_AddButton;
	
	@FindBy(xpath="//p[normalize-space()='Accounts']")
	public WebElement parties_AccountsTab;
	
	@FindBy(id="btn-generic-partyAccounts-getStarted-v1")
	public WebElement parties_AddAccounts;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement parties_PaymentSystem;
	
	@FindBy(xpath="//div[contains(text(),'BT')]")
	public WebElement parties_PaymentSystem_BT;
	
	@FindBy(id="payment-beneficiaryBankBic-txt-v1")
	public WebElement parties_beneficiaryBankBic;
	
	@FindBy(id="payment-beneficiaryCountry-sel-v1")
	public WebElement parties_BeneficiaryCountry;
	
	@FindBy(id="payment-to-txt-v1")
	public WebElement parties_paymentTo;
	
	@FindBy(id="payment-beneficiaryCurrency-txt-v1")
	public WebElement parties_beneficiaryCurrency;
	
	@FindBy(id="btn-generic-partyAccounts-addUpdate-v1")
	public WebElement parties_partyAccountsAddButton;
	
	@FindBy(xpath="//p[normalize-space()='Documents']")
	public WebElement parties_DocumentsTab;
	
	@FindBy(id="btn-generic-partyDocuments-getStarted-v1")
	public WebElement parties_AddDocument;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement parties_DocumentType;
	
	@FindBy(xpath="//div[contains(text(),'Blueprint')]")
	public WebElement parties_DocumentsType_Blueprint;
	
	@FindBy(xpath="(//select[@class='ui-dropdown-select'])[5]")
	public WebElement parties_DocumentNature;
	
	@FindBy(id="btn-generic-partyDocuments-addUpdate-v1")
	public WebElement parties_DocumentsAddButton;
	
	@FindBy(xpath = "//div[contains(text(),'Architect certificate')]")
	public WebElement parties_DocumentsType_Architect_certificate;
	
	@FindBy(id = "txt-generic-datePicker-input-v1")
	public WebElement payments_ExecutionDate;
	
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
	
	@FindBy(xpath="//select[@id='sel-deals-addUpdateBudget-frequency-v1']")
	public WebElement budget_Interval;
	
	@FindBy(xpath="//select[@id='sel-deals-addUpdateBudget-duration-v1']")
	public WebElement budget_Duration;
	
	@FindBy(id="btn-deals-addUpdateBudget-add-v1")
	public WebElement budget_AddButton;
	
	
	@FindBy(id="txt-deals-addUpdateBudget-allocatedAmount-v1")
	public WebElement budget_allocatedAmount;
	
	@FindBy(id = "ic-deals-account-accountDetails-addAccount-v1")
	public WebElement accounts_addAccount;
	
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
	
	@FindBy(xpath = "(//div[@col-id='accountNumber'])[2]")
	public WebElement ecommerceAccount;

	

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

	@FindBy(xpath = "//ui-switch[@id='sw-deals-instruction-fundTransferSchedule-repeating-v1']//span[@class='slider round']")
	public WebElement payments_Repeatingslider;

	@FindBy(xpath = "//select[@id='sel-deals-instruction-fundTransferSchedule-scheduleAt-v1']")
	public WebElement payments_ScheduleAt;
	
	@FindBy(xpath = "//input[@id='txt-generic-timePicker-input-v1']")
	public WebElement payments_ScheduleTime;

	@FindBy(xpath = "//select[@id='sel-deals-instruction-fundTransferSchedule-holidayAction-v1']")
	public WebElement payments_HolidayAction;

	@FindBy(id = "ic-deals-instruction-fundTransferSchedule-next-v1")
	public WebElement payments_NextArrowButtonTransferSchedule;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[4]")
	public WebElement payments_Instrument;
	
	@FindBy(id = "txt-generic-autocomplete-input-v1")
	public WebElement payments_BudgetPurpose;
	
	@FindBy(id = "payment-to-sel-v1")
	public WebElement payments_ToAccountDropdown;
	
	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[6]")
	public WebElement payments_ToAccountInputBox;

	@FindBy(id = "payment-beneficiaryBankBic-txt-v1")
	public WebElement payments_beneficiaryBankBic;

	@FindBy(id = "payment-beneficiaryCountryOfIncorporation-sel-v1")
	public WebElement payments_beneficiaryCountryOfIncorporationDropdown;

	@FindBy(id = "payment-amount-txt-v1")
	public WebElement payments_Amount;

	@FindBy(id = "btn-deals-instruction-fundTransferSubInstruction-add-v1")
	public WebElement payments_AddSubInstructionButton;

	@FindBy(id = "ic-deals-instruction-fundTransferSubInstruction-next-v1")
	public WebElement payments_NextArrowButtonTransferSubInstruction;
	
	@FindBy(id = "payment-beneficiaryCurrency-txt-v1")
	public WebElement payments_BeneficiaryCurrency;
	

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
	
	@FindBy(xpath = "(//button[normalize-space()='Yes'])[1]")
	public WebElement payments_DealYesButton;

	@FindBy(xpath = "(//button[normalize-space()='OK'])[1]")
	public WebElement payments_DealOkButton;
	
	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[5]")
	public WebElement payments_budgetPurpose;

	
	
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
	
	@FindBy(xpath = "/html[1]/body[1]/xcro-wrapper[1]/xcro-protected[1]/ui-overlay-pane[1]/div[1]/div[2]/div[3]/div[1]/div[6]/div[1]/a[1]")
	public WebElement dealChecker_Button1;
	

	
	
	
	
	
	
	
	
	
	
	
	
}
