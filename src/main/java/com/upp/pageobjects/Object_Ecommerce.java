package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_Ecommerce extends BaseClass {

	public Object_Ecommerce() {

		PageFactory.initElements(driver, this);
	}
//Object for  E-commerce Maker 

	@FindBy(xpath = "//label[contains(@class,'ecommerce_icon sidemenu_icon')]")
	public WebElement ecommerce_SideMenuIcon;

	@FindBy(xpath = "//a[@href='/ecommerce/transactions/ECOMMERCETRANSACTIONMAKER']")
	public WebElement ecommerce_Txnmaker;

	@FindBy(xpath = "//div[@class='bulk_upload ng-star-inserted']")
	public WebElement ecommerce_bulkUpload;

	@FindBy(xpath = "//div[@class='input-container']/input[@type='file']")
	public WebElement ecommerce_Browse;

	@FindBy(xpath = "//select[@class='ui-dropdown-select']")
	public WebElement ecommerce_selectSheet;

	@FindBy(xpath = "//button[@class='ui-btn-primary ng-star-inserted' and normalize-space()='Upload File']")
	public WebElement ecommerce_UploadFileButton;

	@FindBy(xpath = "//button[normalize-space()='OK']")
	public WebElement ecommerce_OkButton;

	@FindBy(xpath = "//div[@title='Notifications']")
	public WebElement ecommerce_Notifications;

	@FindBy(xpath = "//div[contains(text(),'submitted to checker successfully')]")
	public WebElement ecommerce_NotificationCheckerMessage;

	@FindBy(xpath = "//div[contains(@class,'message-card')]//span")
	public WebElement ecommerce_NotificationMessage;

	@FindBy(xpath = "//button[normalize-space()='Next']")
	public WebElement ecommerce_NextButton;


	//@FindBy(xpath = "(//input[@ref='eFloatingFilterText'])[2]") sit old 
	@FindBy(xpath = "(//input[@ref='eInput'])[10]")	
	public WebElement ecommerce_TxnSearch;

	@FindBy(xpath = "//div[contains(@class,'ui-icon-add')]")
	public WebElement ecommerce_addNewmaker;

	@FindBy(xpath = "//input[@placeholder='Select Deal Id']")
	public WebElement ecommerce_dealId;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement ecommerce_participantIdtxt;

	@FindBy(xpath = "(//li[contains(@id,lbl-generic-autocomplete-listItem)])[2]")
	public WebElement ecommerce_participantId;

	@FindBy(xpath = "//button[normalize-space()='Submit']")
	public WebElement ecommerce_SubmitBtn;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	public WebElement ecommerce_SearchBtn;

	@FindBy(xpath = "//input[@type='radio']")
	public WebElement ecommerce_debitAccount;

	// Object for basic details on ecomm treansaction maker
	@FindBy(xpath = "//select[@id='sel-deals-instruction-fundTransferBasic-purpose-v1']")
	public WebElement ecommerce_purpose;

	@FindBy(xpath = "//input[@id='txt-generic-autocomplete-input-v1']")
	public WebElement ecommerce_participantAccount;

	@FindBy(xpath = "//li[@id='lbl-generic-autocomplete-listItemOption0']//div//div")
	public WebElement ecommerce_firstParticipantAccount;

	@FindBy(xpath = "//select[@id='sel-deals-instruction-fundTransferBasic-balanceConsideration-v1']")
	public WebElement ecommerce_balanceConsideration;

	@FindBy(xpath = "//div[text()=' Save & Continue ']")
	public WebElement ecommerce_saveAndContinue;

	@FindBy(xpath = "//div[contains(text(),'All')]/..//span[@class='ui-pseudo-checkbox']")
	public WebElement ecommerce_AllRecordsCheckBox;

	@FindBy(xpath = "//div[contains(@class,'loader') and not(contains(@class,'xcro-loader'))]")
	public WebElement ecommerce_Loader;

	@FindBy(xpath = "//span[contains(@class,'ui-pseudo-checkbox')]")
	public WebElement unlimitDebtor;

	@FindBy(xpath = "//input[@placeholder='Participant RefId']")
	public WebElement participantRefId;

	@FindBy(xpath = "(//li[@id='lbl-generic-autocomplete-listItemOption0'])[3]")
	public WebElement ref1;

	@FindBy(xpath = "//h2[@id='swal2-title']")
	public WebElement warningMsg;

	@FindBy(xpath = "//div[@class='swal2-header']")
	public WebElement warningMsgPopup;
	
	@FindBy(xpath = "//label[normalize-space()='Deal RefId']")
	public WebElement dealRefId;
		
	@FindBy(xpath = "//div[@formgroupname='ultimateDebtor']//label")
	public List<WebElement> debtorDetails;

	@FindBy(xpath = "//input[contains(@id,'name')]")
	public WebElement name;

	@FindBy(xpath = "//input[contains(@id,'addressLine1')]")
	public WebElement addressline1;
	
	@FindBy(xpath = "//input[contains(@id,'addressLine2')]")
	public WebElement addressline2;
	
	@FindBy(xpath = "//input[contains(@id,'addressLine3')]")
	public WebElement addressline3;
	
	@FindBy(xpath = "//input[contains(@id,'addressLine4')]")
	public WebElement addressline4;
	
	@FindBy(xpath = "//select[@id='sel-ultimate-debtor-country-v1']")
	public WebElement country;
	
	@FindBy(xpath = "//i[@id='ic-generic-inputError-error-v1']")
	public WebElement errorIcon;
	
	@FindBy(xpath = "//xcro-input-error[@class='ng-tns-c484-55 ng-star-inserted']")
	public WebElement errorMessage;
	// Object for Sub instruction on ecomm treansaction maker
	@FindBy(xpath = "//ui-autocomplete[@id='sel-fdt-participant-id-v1']//input[@placeholder='Creditor Participant Id']")
	public WebElement ecommerce_creatorParticipant;

	@FindBy(xpath = "(//li[@id='lbl-generic-autocomplete-listItemOption0']//div//div)[3]")
	public WebElement ecommerce_creatorParticipantIdOpt;

	@FindBy(xpath = "//ui-dropdown[@placeholder='Creditor Account']//select")
	public WebElement ecommerce_creatorAccount;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[4]")
	public WebElement ecommerce_ParticipantId;

	@FindBy(xpath = "(//li[@id='lbl-generic-autocomplete-listItemOption0']//div//div)[4]")
	public WebElement ecommerce_ParticipantIdOpt;

	@FindBy(xpath = "//label[text()='Platform Reference Number']/following-sibling::input")
	public WebElement ecommerce_PlatformRefNumber;

	@FindBy(xpath = "//label[text()='Fragment Platform Reference No']/following-sibling::input")
	public WebElement ecommerce_FragmentPlatformRefNumber;

	@FindBy(xpath = "//label[text()=' to ']/following::input[@id='txt-generic-autocomplete-input-v1']")
	public WebElement ecommerce_toAccount;

	@FindBy(xpath = "(//li[@id='lbl-generic-autocomplete-listItemOption0']//div//div)[5]")
	public WebElement ecommerce_toAccountOpt;

	@FindBy(xpath = "//select[@id='payment-beneficiaryCountryOfIncorporation-sel-v1']")
	public WebElement ecommerce_beneficiaryCountry;

	@FindBy(xpath = "//label[text()=' Beneficiary Bank Bic ']/following-sibling::input")
	public WebElement ecommerce_bankBic;

	@FindBy(id = "payment-amount-txt-v1")
	public WebElement ecommerce_amount;

	@FindBy(xpath = "//input[@placeholder='Select Category']")
	public WebElement ecommerce_txnCategory;

	@FindBy(xpath = "(//div[contains(@class,'si-details-tab')]//div)[2]")
	public WebElement ecommerce_txnSubCategory;

	@FindBy(xpath = "//input[contains(@id,'payment-rkTestNumber-txt-v1')]")
	public WebElement ecommerce_txnSubCategoryValue;

	@FindBy(xpath = "//input[@placeholder='Select Instrument']")
	public WebElement ecommerce_instrument;

	@FindBy(xpath = "//button[@id='btn-deals-instruction-fundTransferSubInstruction-add-v1']")
	public WebElement ecommerce_addSubInstruction;

	@FindBy(id = "ic-deals-instruction-fundTransferSubInstruction-next-v1")
	public WebElement ecommerce_nextSubInstruction;

	// object for document

	@FindBy(xpath = "(//label[text()='Document Type']/following::select)[1]")
	public WebElement ecommerce_docType;

	@FindBy(xpath = "(//label[text()='Document Type']/following::select)[2]")
	public WebElement ecommerce_fileType;

	@FindBy(xpath = "(//textarea[@formcontrolname='description']")
	public WebElement ecommerce_description;

	@FindBy(xpath = "//div[text()='Proceed to Summary']")
	public WebElement ecommerce_proceed;

	@FindBy(xpath = "//button[text()=' submit ']")
	public WebElement ecommerce_submit;

	@FindBy(xpath = "//button[text()='Yes']")
	public WebElement ecommerce_yes;

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement ecommerce_ok;

	// object for ecomm transaction checker

	@FindBy(xpath = "//a[@href='/ecommerce/transactions/ECOMMERCETRANSACTIONCHECKER']")
	public WebElement ecommerce_txnChecker;

//	@FindBy(xpath = "(//div[@aria-colindex='4']//input)[2]")
//	@FindBy(xpath = "(//input[@ref='eFloatingFilterText'])[2]")
	@FindBy(xpath = "(//input[@ref='eInput'])[10]")
	public WebElement ecommerce_TxnDealSearch;

	@FindBy(xpath = "(//input[@ref='eInput'])[10]")
//	@FindBy(css = "xcro-wrapper.theme-wrapper:nth-child(1) xcro-protected.ng-tns-c433-0.ng-star-inserted:nth-child(2) div.ui-wrapper-container.ng-tns-c433-0 div.ui-wrapper-ct.ui-col-md-12.ng-tns-c433-0 div.ui-content-ct.ng-tns-c433-0 div.ui-content.ng-tns-c433-0 xcro-deal-management.ng-star-inserted xcro-transaction-list.ng-star-inserted:nth-child(2) div.ui-section-row div.ui-section-row div.ui-section-row.ng-star-inserted div.ui-section-row div.ui-section-body div.ui-section-container ag-grid-angular.ag-theme-balham.custom-ag-grid-list.list-new.ng-star-inserted div.ag-root-wrapper.ag-layout-normal.ag-ltr div.ag-root-wrapper-body.ag-layout-normal div.ag-root.ag-unselectable.ag-layout-normal div.ag-header.ag-pivot-off div.ag-header-viewport div.ag-header-container div.ag-header-row:nth-child(2) div.ag-header-cell:nth-child(2) div.ag-floating-filter-body div.ag-input-wrapper > input.ag-floating-filter-input")
	public WebElement ecommerce_TxnDealSearch1;

	@FindBy(xpath = "//div[@class='ag-pinned-left-cols-container']//div[@class='ui-checkbox']//span")
	public WebElement ecommerce_txnCheckbox;

	@FindBy(xpath = "(//div[contains(@class,' ag-cell-first-right-pinned')]//i)[3]")
	public WebElement ecommerce_comment;

	@FindBy(xpath = "//div[contains(text(),'Manage')]/..//i[@class='ui-icon ui-icon-note ng-star-inserted']")
	public WebElement ecommerce_Allcomment;

	@FindBy(id = "txt-deals-accounts-addUpdateAdvice-noteTxt-v1")
	public WebElement ecommerce_note;

	@FindBy(id = "btn-deals-accounts-addUpdateAdvice-ok-v1")
	public WebElement ecommerce_txnok;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement ecommerce_submitBtn;

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement ecommerce_okBtn;

	@FindBy(xpath = "//button[text()='Yes']")
	public WebElement ecommerce_yesBtn;

	@FindBy(xpath = "(//div[@col-id ='warnings'])[2]")
	public WebElement ecommerce_warning;

	// Object for E-commerce verifier

	@FindBy(xpath = "//a[@href='/ecommerce/transactions/ECOMMERCETRANSACTIONVERIFIER']")
	public WebElement ecommerce_txnVerifier;

	@FindBy(xpath = "(//div[contains(text(),'Deal Id')]/../../../../..//input[@class='ag-floating-filter-input'])[2]")
	public WebElement ecommerce_dealIdInline;

	@FindBy(xpath = "//div[@col-id='_id' and contains(@class,'ag-cell-not-inline-editing')]")
	public WebElement ecommerce_TxnIdRecord;

	@FindBy(xpath = "//span[@class='ag-overlay-no-rows-center']")
	public WebElement noRows;
}
