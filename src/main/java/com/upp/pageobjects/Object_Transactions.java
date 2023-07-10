package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_Transactions extends BaseClass {

	public Object_Transactions() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[contains(text(),'TRANSACTIONS')]")
	public WebElement transactions_TransactionIcon;

	@FindBy(xpath = "//a[@href='/transactions/TRANSACTIONMAKER']")
	public WebElement transactions_TransactionMaker;

	@FindBy(xpath = "//div[contains(text(),'Add New')]")
	public WebElement transactions_AddNewButon;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[1]")
	public WebElement transactions_DealId;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement transactions_SourceAccNo;

	@FindBy(css = ".ui-btn-primary.ui-align-left.w-a")
	public WebElement transactions_SubmitButton;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement transactions_Instrument;

	@FindBy(id = "payment-debitAccountBicCode-txt-v1")
	public WebElement transactions_debitAccountBicCode;

	@FindBy(xpath = "(//select[@id='payment-to-sel-v1'])[1]")
	public WebElement transactions_ToAccountDropdown;

	@FindBy(xpath = "(//select[@class='ui-dropdown-select'])[1]")
	public WebElement transactions_DocumentTypeDropdown;

	@FindBy(xpath = "(//select[@class='ui-dropdown-select'])[2]")
	public WebElement transactions_FileType;

	@FindBy(xpath = "(//input[@type='url'])[1]")
	public WebElement transactions_EnterUrl;

	@FindBy(xpath = "(//textarea[@type='url'])[1]")
	public WebElement transactions_Description;

	@FindBy(xpath = "(//button[normalize-space()='Add'])[1]")
	public WebElement transactions_DocumentsAddButton;

	@FindBy(id = "ic-deals-instruction-fundTransferSubInstruction-back-v1")
	public WebElement transactions_ProceedToSummary;

	@FindBy(xpath = "//button[normalize-space()='submit']")
	public WebElement transactions_SummarySubmitButton;

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement transactions_Ok;

	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	public WebElement transactions_YesButton;

	@FindBy(xpath = "//a[@href='/transactions/TRANSACTIONCHECKER']")
	public WebElement transactions_TransactionChecker;

	@FindBy(xpath = "//h2[@id='swal2-title']")
	public WebElement transactions_TransactionsId;

	@FindBy(xpath = "(//input[@ref='eFloatingFilterText'])[1]")
	public WebElement transactions_TransactionIdSearchBox;

	@FindBy(xpath = "//i[@class='ui-icon ic ic-edit_blue ng-star-inserted']")
	public WebElement transactions_TransactionEditButton;

	@FindBy(xpath = "//div[@title='Summary']")
	public WebElement transactions_SummaryTab;

	@FindBy(xpath = "(//textarea[@placeholder='Add your comments here ...'])[1]")
	public WebElement transactions_Checker_Add_comments;

	@FindBy(id = "btn-tranaction-summary-approve-v1")
	public WebElement transactions_ApproveButton;

	@FindBy(id = "btn-tranaction-summary-submit-v1")
	public WebElement transactions_Checker_SubmitButton;

	@FindBy(xpath = "//a[@href='/transactions/TRANSACTIONVERIFIER']")
	public WebElement transactions_TransactionVerifier;

	@FindBy(xpath = "//label[contains(text(),'Reports')]")
	public WebElement reports_ReportsIcon;

	@FindBy(xpath = "//div[@col-id='Sub-Instruction Type' and contains(@class,'ag-cell-not-inline-editing')]")
	public List<WebElement> reports_SubInstructions;

	@FindBy(xpath = "//div[@class='ag-header-cell-label' and normalize-space()='Sub-Instruction Type']")
	public WebElement reports_SubInstructionType;

	@FindBy(xpath = "//a[contains(text(),'INTERNAL')]")
	public WebElement reports_ReportsInternal;

	@FindBy(id = "txt-generic-autocomplete-input-v1")
	public WebElement reports_searchBox;

	@FindBy(xpath = "//div[contains(text(),'Execution Report')]")
	public WebElement reports_ExecutionReport;

	@FindBy(xpath = "(//input[@class='ag-floating-filter-input'])[2]")
	public WebElement reports_DealId;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement reports_SubmitButton;

	@FindBy(xpath = "(//div[@col-id='SCROE Status'])[2]")
	public WebElement reports_ScroeStatus;

	@FindBy(xpath = "//div[@ref='eCenterContainer']//div[@role='row']")
	public List<WebElement> reports_RecordStatus;

	// object for LT-IN
	@FindBy(xpath = "//ui-autocomplete[contains(@id,'instruction-fundTransferSubInstruction-paymentInstrument')]")
	public WebElement transactions_instruments;

	@FindBy(xpath = "//input[contains(@id,'payment-amount-txt')]")
	public WebElement transactions_amount;

	@FindBy(xpath = "//input[contains(@id,'payment-beneficiaryBankIfscCode')]")
	public WebElement transactions_bankIFSCCode;

	@FindBy(xpath = "//input[contains(@id,'payment-beneficiaryName')]")
	public WebElement transactions_beneficiaryName;

	@FindBy(xpath = "//select[contains(@id,'payment-accountOrIban')]")
	public WebElement transactions_accountOrIban;

	@FindBy(xpath = "//input[contains(@id,'beneficiaryAddressLine1')]")
	public WebElement transactions_address;

	@FindBy(xpath = "//select[contains(@id,'beneficiaryCountry')]")
	public WebElement transactions_country;

	@FindBy(xpath = "//select[contains(@id,'beneficiaryCountryOfIncorporation')]")
	public WebElement transactions_beneficiaryIncorporation;

	@FindBy(id = "sel-deals-basicDetails-processingUnit-v1")
	public WebElement transactions_beneficiaryaccountNumber;

	@FindBy(xpath = "//ui-autocomplete[@id='sel-deals-basicDetails-processingUnit-v1']//input")
	public WebElement transactions_beneficiaryaccountNumberInput;

	@FindBy(id = "btn-deals-instruction-fundTransferSubInstruction-add-v1")
	public WebElement transactions_addSubInstruction;

	@FindBy(xpath = "(//div[contains(text(),'eComm Executions')])[3]")
	public WebElement reports_eCommExecutions;

	@FindBy(xpath = "//div[starts-with(text(),'eComm Executions')]")
	public WebElement reports_eCommExecutionsList;
	
	@FindBy(xpath = "//div[contains(text(),'eComm Payments')]")
	public WebElement reports_eCommPaymentsList;

	@FindBy(xpath = "(//input[@class='ag-floating-filter-input'])[2]")
	public WebElement reports_endToendId;

	
//	@FindBy(xpath="(//span[normalize-space()='dealRefId']/../../../../..//input[@class='ag-floating-filter-input'])[1]")
//	@FindBy(xpath="(//span[normalize-space()='Deal Id']/../../../../..//input[@class='ag-floating-filter-input'])[2]")

	@FindBy(xpath = "(//span[normalize-space()='dealRefId']/../../../../..//input[@class='ag-floating-filter-input'])[1]")
	public WebElement reports_dealId;

	@FindBy(xpath = "(//input[@class='ag-floating-filter-input'])[1]")
	public WebElement reports_dealId1;
	
//	@FindBy(xpath="//span[normalize-space()='Deal ID']/../../../../..//input[@class='ag-floating-filter-input']")
//	public WebElement reports_dealId1;
	
//	@FindBy(xpath="//div[@col-id='Amount']")
	@FindBy(xpath = "//div[@col-id='Status' and contains(@class,'ag-cell-not-inline-editing')]")
	public List<WebElement> reports_EcommRecordStatus;

	@FindBy(xpath = "//div[@col-id='Amount']")
	public WebElement reports_Amount;

	@FindBy(xpath = "(//div[@class='ag-body-horizontal-scroll-viewport'])[1]")
	public WebElement reports_horizontalWindow;

	@FindBy(xpath = "(//div[@col-id='Status' and @role ='gridcell'])[1]")
	public WebElement reports_FirstTxnStatus;

	@FindBy(xpath = "(//div[@col-id='Status' and @role ='gridcell'])[2]")
	public WebElement reports_SecondTxnStatus;
	
//	@FindBy(xpath="//div[@class='ag-center-cols-viewport']")
//	public WebElement reports_horizontalWindow1;
	
//	@FindBy(xpath="(//div[@col-id='Sub-Instruction Type'])[2]")

	@FindBy(xpath = "//div[@class='ag-center-cols-viewport']")
	public WebElement reports_horizontalWindow1;
	
	@FindBy(xpath = "(//div[@col-id='Sub-Instruction Type'])[2]")
	public WebElement reports_instructiontype_payment;

	@FindBy(xpath = "(//div[@col-id='Sub-Instruction Type'])[3]")
	public WebElement reports_instructiontype_retention;

	@FindBy(xpath = "(//div[@col-id='SCROE Status'])[3]")
	public WebElement reports_ScroeStatus2ndRow;

	@FindBy(xpath = "(//div[@col-id='Instruction Type'])[2]")
	public WebElement reports_InstructionName;
	
	@FindBy(xpath = "(//div[@col-id='Instruction Name'])[2]")
	public WebElement reports_InstructionName1;

	@FindBy(xpath = "(//div[@col-id='Instruction Name'])[3]")
	public WebElement reports_InstructionName2;

	@FindBy(xpath = "(//div[@col-id='Instruction Name'])[4]")
	public WebElement reports_InstructionName3;

// Object for transaction 
	@FindBy(xpath = "(//input[@id='payment-beneficiaryBankBic-txt-v1'])[1]")
	public WebElement transactions_beneficiaryBankBic;

	@FindBy(xpath = "(//input[@id='payment-senderPop-txt-v1'])[1]")
	public WebElement transactions_senderPop;

// Object for bulk transaction 	
	@FindBy(xpath = "//div[contains(@class,'bulk_upload')]")
	public WebElement transactionMaker_bulk;

	@FindBy(xpath = "//input[@type='file']")
	public WebElement transactionMaker_browseButton;

	@FindBy(xpath = "//button[text()=' Upload File ']")
	public WebElement transactionMaker_uploadButton;

	@FindBy(xpath = "//select[@class='ui-dropdown-select']")
	public WebElement transactionMaker_sheetName;

	@FindBy(xpath = "(//div[@class='card valid']//div)[2]")
	public WebElement transactionMaker_validRecord;

	@FindBy(xpath = "//button[text()=' Next ']")
	public WebElement transactionMaker_nextBtn;

	@FindBy(xpath = "//div[@class='customHeaderLabel']//span")
	public WebElement transactionMaker_allRecord;

	@FindBy(xpath = "//button[text()=' Submit ']")
	public WebElement transactionMaker_submit;

	@FindBy(xpath = "//div[@class='customHeaderLabel']//i")
	public WebElement transactionMaker_message;

	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	public WebElement transactionMaker_messageOk;

	@FindBy(xpath = "(//input[@ref='eFloatingFilterText'])[2]")
	public WebElement transactionMaker_dealSearch;

	// reports
	@FindBy(xpath = "//span[@class='ic ic-cancel_black ic-sm ng-star-inserted']")
	public WebElement cancelIcon;
	
	@FindBy(xpath = "//span[normalize-space()='Deal ID']")
	public WebElement reports_dealIDText;
	
	@FindBy(xpath = "//div[@col-id='Original Amount' and not(contains(@class,'ag-header-cell'))]")
	public WebElement reports_OriginalAmountValue;
	
	
	@FindBy(xpath = "//div[@col-id='Original Amount' and contains(@class,'ag-cell-not-inline-editing')]")
	public List<WebElement> reports_OriginalAmount;
	
	@FindBy(xpath = "//span[normalize-space()='Original Amount']")
	public WebElement reports_OriginalAmountColoumnName;
	
	@FindBy(xpath = "//div[@col-id='Transfer Info' and contains(@class,'ag-cell-not-inline-editing')]")
	public List<WebElement> reports_TransferInfo;
	
	@FindBy(xpath = "//div[@class='ag-header-cell-label' and normalize-space()='Original Amount']")
	public WebElement reports_OriginalAmountColumn;
	
	@FindBy(xpath = "//div[@class='ag-header-cell-label' and normalize-space()='Transfer Info']")
	public WebElement reports_TransferInfoColumn;
	
	@FindBy(xpath = "(//input[@class='ag-floating-filter-input'])[2] | (//input[@ref='eFloatingFilterText'])[5]")
	public WebElement reports_End_To_End_common;
	
	
	//Entitlements 
	
	
	@FindBy(xpath = "//i[contains(@class,'deal-entitlement')]")
	public WebElement entitlementsIcon;
	
	@FindBy(xpath = "//td[3]//div[contains(@title,'Name:')]")
	public WebElement initiatingContact;
	
	//Ecomm Payments
	
	@FindBy(xpath = "//a[contains(text(),'ECOMMPAYMENT')]")
	public WebElement ecommPaymentLink;
	
	@FindBy(xpath = "//div[contains(text(),'ECOMMBATCH')]")
	public WebElement ecommBatch;
	
	@FindBy(xpath = "(//input[@class='ag-floating-filter-input'])[1]")
	public WebElement ecommPayments_PaymentId;
	
}

