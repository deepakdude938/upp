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
	
	@FindBy(xpath = "//a[@href='/transactions/futuredated']")
	public WebElement transactions_TransactionFutureDated;

	@FindBy(xpath = "//div[contains(text(),'Add New')]")
	public WebElement transactions_AddNewButon;
	
	@FindBy(xpath = "//div[contains(text(),'SCHD')]")
	public List<WebElement> transactions_future_dated_SCHD;
	
	@FindBy(xpath = "(//span[@class='ui-pseudo-checkbox'])[2]")
	public WebElement transactions_future_dated_Checkbox1;
	
	@FindBy(xpath = "(//button[@class='ui-btn-primary ui-ripple'][normalize-space()='Cancel'])[1]")
	public WebElement transactions_future_dated_Cancel1;
	
	@FindBy(xpath = "//button[normalize-space()='Proceed']")
	public WebElement transactions_future_dated_Proceed;
	
	@FindBy(xpath = "//input[@aria-label='Deal Id Filter Input']")
	public WebElement transactions_future_dated_DealId;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[1]")
	public WebElement transactions_DealId;
	
	@FindBy(xpath = "(//span[@class='ui-pseudo-checkbox'])[1]")
	public WebElement transactions_ExecuteLater;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement transactions_SourceAccNo;

	@FindBy(css = ".ui-btn-primary.ui-align-left.w-a")
	public WebElement transactions_SubmitButton;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement transactions_Instrument;
	
	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[4]")
	public WebElement transactions_Instrument_when_budget_purpose_enabled;

	@FindBy(id = "payment-debitAccountBicCode-txt-v1")
	public WebElement transactions_debitAccountBicCode;

	@FindBy(xpath = "//div[@id='payment-to-sel-v1']//select")
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
	
	@FindBy(xpath = "//button[normalize-space()='Next']")
	public WebElement transactions_NextButton;
	
	@FindBy(xpath = "//button[normalize-space()='next']")
	public WebElement transactions_NextButton1;
	
	@FindBy(xpath = "//div[contains(@class,'ui-tree-grid-view-ct')]")
	public WebElement transactions_MappingScreenScrollBar;
	
	@FindBy(xpath = "//input[@placeholder='Select Destination Account' and @id='txt-generic-autocomplete-input-v1']")
	public WebElement transactions_DestinationAccount;
	
	@FindBy(xpath = "//li[@id='lbl-generic-autocomplete-listItemOption1']//div[normalize-space()='to']")
	public WebElement transactions_toAccount;
	
	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	public WebElement transactions_YesButton;
	
	@FindBy(xpath = "//h2[@id='swal2-title' and normalize-space()='Error(s)']")
	public List<WebElement> transactions_TransactionCheckerError;
	
	@FindBy(xpath = "//a[@href='/transactions/TRANSACTIONCHECKER']")
	public WebElement transactions_TransactionChecker;
	
	@FindBy(xpath = "//input[@aria-label='Deal Id Filter Input']")
	public WebElement transactions_Checker_DealID;
	
	@FindBy(xpath = "(//span[@class='ui-pseudo-checkbox'])[3]")
	public WebElement transactions_Checker_Checkbox1;

	@FindBy(xpath = "//h2[@id='swal2-title']")
	public WebElement transactions_TransactionsId;

	@FindBy(xpath = "//input[contains(@aria-label,'Txn Id Filter Input')]")
	public WebElement transactions_TransactionIdSearchBox;

	@FindBy(xpath = "//i[@class='ui-icon ic ic-edit_blue ng-star-inserted']")
	public WebElement transactions_TransactionEditButton;

	@FindBy(xpath = "//div[@title='Summary']")
	public WebElement transactions_SummaryTab;
	
	@FindBy(xpath = "//div[contains(@class,'xcro-loader')]")
	public WebElement transactions_Loader;
	

	@FindBy(xpath = "(//textarea[@placeholder='Add your comments here ...'])[1]")
	public WebElement transactions_Checker_Add_comments;

	@FindBy(id = "btn-tranaction-summary-approve-v1")
	public WebElement transactions_ApproveButton;

	@FindBy(id = "btn-tranaction-summary-submit-v1")
	public WebElement transactions_Checker_SubmitButton;
	
	@FindBy(xpath = "//div[contains(@class,'ui-overlay ui-sidebar m')]")
	public WebElement transactions_SideBar;

	@FindBy(xpath = "//a[@href='/transactions/TRANSACTIONVERIFIER']")
	public WebElement transactions_TransactionVerifier;

	@FindBy(xpath = "//label[contains(text(),'Reports')]")
	public WebElement reports_ReportsIcon;

	@FindBy(xpath = "//div[@col-id='Sub-Instruction Type' and contains(@class,'ag-cell-not-inline-editing')]")
	public List<WebElement> reports_SubInstructions;

	@FindBy(xpath = "//div[@col-id='Settled Amount' and contains(@class,'ag-cell-not-inline-editing')]")
	public List<WebElement> reports_SettledAmountList;
	
	@FindBy(xpath = "//div[@col-id='Utilized Budget' and contains(@class,'ag-cell-not-inline-editing')]")
	public WebElement reports_Utilized_Budget_Amount;
	
	@FindBy(xpath = "(//div[@col-id='ScheduledOn' and contains(@class,'ag-cell-not-inline-editing')])[2]")
	public WebElement reports_RescheduledDate;
	
	@FindBy(xpath = "//div[@col-id='Utilized Budget' and contains(@class,'ag-cell-not-inline-editing')]")
	public List<WebElement> reports_Utilized_Budget_Amount_List;
	
	@FindBy(xpath = "//div[@col-id='SCROE Status' and not(contains(@class,'header'))] | //div[@col-id='Settled Status' and not(contains(@class,'header')) and text()]")
	public List<WebElement> reports_ScroeStatusRecords;
	
	@FindBy(xpath = "//div[@col-id='SCROE Status' and contains(@class,'header')] | //div[@col-id='Settled Status' and contains(@class,'header')]")
	public WebElement reports_ScroeStatusColumnName;
	
	@FindBy(xpath = "//div[@col-id='Original Amount' and contains(@class,'header')]")
	public WebElement reports_OriginalAmountColumnName;
	
	@FindBy(xpath = "//div[@col-id='Settled Amount' and contains(@class,'header')]")
	public WebElement reports_SettledAmountColumnName;
	
	@FindBy(xpath = "//div[@col-id='Original Amount' and not(contains(@class,'header'))]")
	public List<WebElement> reports_OriginalAmountRecords;
	
	@FindBy(xpath = "//div[@col-id='Settled Amount' and not(contains(@class,'header'))]")
	public List<WebElement> reports_SettledAmountRecords;
	
	
	@FindBy(xpath = "//div[@class='ag-header-cell-label' and normalize-space()='Sub-Instruction Type']")
	public WebElement reports_SubInstructionType;

	@FindBy(xpath = "//a[contains(text(),'INTERNAL')]")
	public WebElement reports_ReportsInternal;

	@FindBy(id = "txt-generic-autocomplete-input-v1")
	public WebElement reports_searchBox;

	@FindBy(xpath = "//div[starts-with(text(),'Execution Report')]")
	public WebElement reports_ExecutionReport;
	
	@FindBy(xpath = "//div[contains(text(),'Budget Utilization Report')]")
	public WebElement reports_BUDGET_UTILIZATION_REPORT;

	@FindBy(xpath = "//input[@aria-label='Deal Id Filter Input']")
//	@FindBy(xpath = "(//input[@ref='eFloatingFilterText'])[2]")
	public WebElement reports_DealId;
	//input[@aria-label='Deal Id Filter Input']
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement reports_SubmitButton;

	@FindBy(xpath = "(//div[@col-id='SCROE Status'])[2] | (//div[@col-id='Settled Status'])[2]")
	public WebElement reports_ScroeStatus;
	
	@FindBy(xpath = "//div[contains(@class,'ag-row-position-absolute ag-row-first')]")
	public WebElement reports_ScroeStatusRecordFirst;

	@FindBy(xpath = "//div[@ref='eCenterContainer']//div[@role='row']")
	public List<WebElement> reports_RecordStatus;

	@FindBy(xpath = "//ui-dropdown[@placeholder='Select Currency']//select")
	public WebElement paymentCountry;
	
	@FindBy(xpath = "//ui-dropdown[@placeholder='Select Currency Type']//select")
	public WebElement paymentCountryType;
	
	@FindBy(xpath = "(//i[@class='ic ic-lg ic-right'])[1]")
	public WebElement SubInstructionArrow;
	
	@FindBy(id = "ic-deals-instruction-fundTransferSubInstruction-hideSubIns-v1")
	public WebElement addSubInstructionArrow;
	
	
	@FindBy(xpath = "//div[text()=' Basic Details ']")
	public WebElement basicDetails;
	
	@FindBy(xpath = "(//label[text()=' Debit ccy control sum ']/following-sibling::span)[1]")
	public WebElement debitCurrencyTotal;
	
	@FindBy(xpath = "(//label[text()=' Debit ccy control sum ']/following-sibling::span)[2]")
	public WebElement paymentCurrencyTotal;
	
	// object for LT-IN
	@FindBy(xpath = "//ui-autocomplete[contains(@id,'instruction-fundTransferSubInstruction-paymentInstrument')]")
	public WebElement transactions_instruments;

	@FindBy(xpath = "//input[contains(@id,'payment-amount-txt')]")
	public WebElement transactions_amount;

	@FindBy(xpath = "//input[contains(@id,'payment-beneficiaryBankIfscCode')]")
	public WebElement transactions_bankIFSCCode;

	@FindBy(xpath = "//input[contains(@id,'payment-beneficiaryName')]")
	public WebElement transactions_beneficiaryName;

	@FindBy(xpath = "//div[contains(@id,'payment-accountOrIban')]//select")
	public WebElement transactions_accountOrIban;

	@FindBy(xpath = "//input[contains(@id,'beneficiaryAddressLine1')]")
	public WebElement transactions_address;

	@FindBy(xpath = "//div[contains(@id,'beneficiaryCountry')]//select")
	public WebElement transactions_country;

	@FindBy(xpath = "//div[contains(@id,'beneficiaryCountryOfIncorporation')]//select")
	public WebElement transactions_beneficiaryIncorporation;

	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[3]")
	public WebElement transactions_beneficiaryaccountNumber;

	@FindBy(xpath = "//ui-autocomplete[@id='sel-deals-basicDetails-processingUnit-v1']//input")
	public WebElement transactions_beneficiaryaccountNumberInput;
	
	@FindBy(xpath = "//div[@id='payment-to-sel-v1']//select")
	public WebElement transactions_beneficiaryaccountNumberInputDropDown;
	
	@FindBy(xpath = "//input[@id='payment-to-txt-v1']")
	public WebElement transactions_beneficiaryaccountNumberInputTextBox;

	@FindBy(id = "btn-deals-instruction-fundTransferSubInstruction-add-v1")
	public WebElement transactions_addSubInstruction;

	@FindBy(id = "payment-beneficiaryBankCode-txt-v1")
	public WebElement transactions_beneficiaryBankCode;

	@FindBy(xpath = "(//div[contains(text(),'eComm Executions')])[3]")
	
	public WebElement reports_eCommExecutions;

	@FindBy(xpath = "//div[starts-with(text(),'eComm Executions')]")
	public WebElement reports_eCommExecutionsList;
	
	@FindBy(xpath = "//div[contains(text(),'eComm Payments')]")
	public WebElement reports_eCommPaymentsList;

	@FindBy(xpath = "//input[@aria-label='End to End ID Filter Input']")
	public WebElement reports_endToendId;

	
//	@FindBy(xpath="(//span[normalize-space()='dealRefId']/../../../../..//input[@class='ag-floating-filter-input'])[1]")
//	@FindBy(xpath="(//span[normalize-space()='Deal Id']/../../../../..//input[@class='ag-floating-filter-input'])[2]")

	@FindBy(xpath = "(//span[normalize-space()='dealRefId']/../../../../..//input[@class='ag-floating-filter-input'])[1]")
	public WebElement reports_dealId;

//	@FindBy(xpath = "(//input[@ref='eInput'])[9]")
	@FindBy(xpath = "//input[@aria-label='Deal ID Filter Input']")
	public WebElement reports_dealId1;
	
	@FindBy(xpath = "//button[contains(@class,'ui-btn-primary') and normalize-space()='Set Preference']")
	public WebElement reports_setPreference;
	
	@FindBy(xpath = "//div[@class='grid-row']//div[normalize-space()='Deal Id']")
	public WebElement reports_setPreference_DealId;
	
	@FindBy(xpath = "//div[contains(text(),'Deal Id')]/..//span[@class='ui-pseudo-checkbox']")
	public WebElement reports_setPreference_DealIdCheckBox;
	
	@FindBy(xpath = "//button[normalize-space()='Apply']")
	public WebElement reports_setPreference_Apply;
	
	@FindBy(id = "cdk-drop-list-1")
	public WebElement reports_setPreference_VerticalScrollBar;
	
//	@FindBy(xpath="//span[normalize-space()='Deal ID']/../../../../..//input[@class='ag-floating-filter-input']")
//	public WebElement reports_dealId1;
	
//	@FindBy(xpath="//div[@col-id='Amount']")
	@FindBy(xpath = "//div[@col-id='Status' and contains(@class,'ag-cell-not-inline-editing')]")
	public List<WebElement> reports_EcommRecordStatus;
	
	@FindBy(xpath = "//div[@col-id='Status' and contains(@class,'ag-cell-not-inline-editing')]")
	public WebElement reports_EcommRecordStatus1;

	@FindBy(xpath = "//div[@col-id='Amount']")
	public WebElement reports_Amount;
	
	@FindBy(xpath = "//div[@col-id='Settled Amount']")
	public WebElement reports_SettledAmount;
	
	@FindBy(xpath = "//div[@col-id='Utilized Budget']")
	public WebElement reports_Utilized_Budget_ColName;

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

	@FindBy(xpath = "(//div[@col-id='SCROE Status'])[3] | (//div[@col-id='Settled Status'])[3]")
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

	@FindBy(xpath = "//input[@aria-label='Deal Id Filter Input']")
	public WebElement transactionMaker_dealSearch;
	
	@FindBy(xpath = "(//input[@ref='eInput'])[10]")
	public WebElement transactionMaker_dealSearch1;

	// reports
	@FindBy(xpath = "//span[@class='ic ic-cancel_black ic-sm ng-star-inserted']")
	public WebElement cancelIcon;
	
	@FindBy(xpath = "//span[normalize-space()='Deal ID']")
	public WebElement reports_dealIDText;
	
	@FindBy(xpath = "//span[normalize-space()='Deal Id']")
	public WebElement reports_dealIDText1;
	
	@FindBy(xpath = "//div[@col-id='Original Amount' and not(contains(@class,'ag-header-cell'))]")
	public WebElement reports_OriginalAmountValue;
	
	
	@FindBy(xpath = "//div[@col-id='Original Amount' and contains(@class,'ag-cell-not-inline-editing')]")
	public List<WebElement> reports_OriginalAmount;
	
	@FindBy(xpath = "//div[@col-id='SCROE Status' and @role='gridcell']")
	public List<WebElement> reports_AllRecordsScroeStatus;
	
	@FindBy(xpath = "//span[normalize-space()='Original Amount']")
	public WebElement reports_OriginalAmountColoumnName;
	
	@FindBy(xpath = "//span[normalize-space()='Source Account Number']")
	public WebElement reports_SourceAccountNumberColumnName;
	
	@FindBy(xpath = "//div[@col-id='Source Account Number' and not(contains(@class,'ag-header-cell'))]")
	public WebElement reports_SourceAccountNumberValue;
	
	@FindBy(xpath = "//span[normalize-space()='Beneficiary Account Number']")
	public WebElement reports_BeneficiaryAccountNumberColumnName;
	
	@FindBy(xpath = "//div[@col-id='Beneficiary Account Number' and not(contains(@class,'ag-header-cell'))]")
	public WebElement reports_BeneficiaryAccountNumberValue;
	
	@FindBy(xpath = "//div[@col-id='Transfer Info' and contains(@class,'ag-cell-not-inline-editing')]")
	public List<WebElement> reports_TransferInfo;
	
	@FindBy(xpath = "//div[@class='ag-header-cell-label' and normalize-space()='Original Amount']")
	public WebElement reports_OriginalAmountColumn;
	
	@FindBy(xpath = "//div[@class='ag-header-cell-label' and normalize-space()='Transfer Info']")
	public WebElement reports_TransferInfoColumn;
	
	@FindBy(xpath = "//input[@aria-label='End to End ID Filter Input']")
	public WebElement reports_End_To_End_common;
	
	@FindBy(xpath = "//label[contains(@class,'instruction_icon sidemenu_icon')]")
	public WebElement transactions_TransactionIcon1;
	
	@FindBy(xpath = "//ui-autocomplete[@formcontrolname='sourceAccountNumber']//div//input")
	public WebElement transactions_SourceAccNo1;
	
	
	//Entitlements 
	
	
	@FindBy(xpath = "//i[contains(@class,'deal-entitlement')]")
	public WebElement entitlementsIcon;
	
	@FindBy(xpath = "//p[@class='ui-text-s ui-tab' and normalize-space()='Account']")
	public WebElement entitlements_Account;
	
	@FindBy(xpath = "//td[3]//div[contains(@title,'Name:')]")
	public WebElement initiatingContact;
	
	@FindBy(xpath = "//div[text()='View Contact Details']")
	public WebElement contactDetails;
	
	//Ecomm Payments
	
	@FindBy(xpath = "//a[contains(text(),'ECOMMPAYMENT')]")
	public WebElement ecommPaymentLink;
	
	@FindBy(xpath = "//div[contains(text(),'ECOMMBATCH')]")
	public WebElement ecommBatch;
	
	@FindBy(xpath = "//input[@aria-label='Payment ID Filter Input']")
	public WebElement ecommPayments_PaymentId;
	
	@FindBy(xpath = "(//div[@col-id='Status'])[2]")
	public WebElement ecommPaymentStatus;
	
	
	@FindBy(xpath = "//ui-autocomplete[@formcontrolname='refId']")
	public WebElement transactions_DealId1;
	
	@FindBy(xpath = "//span[@class='ag-overlay-no-rows-center']")
	public WebElement noRows;
	
	//Webelement for Adhoc transaction BT_IN
	@FindBy(xpath = "//ui-autocomplete[@id='sel-deals-basicDetails-processingUnit-v1']//input")
	public WebElement toAccount;
	
	@FindBy(xpath = "(//div[@col-id='totalAmount'])[2]")
	public WebElement amount;
	
	@FindBy(xpath = "(//div[@col-id='Errors'])[1]")
	public WebElement Reports_Errors;
	
	@FindBy(xpath = "(//div[@col-id='Source Account Number'])[1]")
	public WebElement Reports_Source_Acc_No_Col;
	
	@FindBy(xpath = "(//div[@col-id='Source Account Number' and @role ='gridcell'])[1]")
	public WebElement Reports_Source_Acc_No_First;
	
	@FindBy(xpath = "//span[normalize-space()='Sub-Instruction Type']")
	public WebElement reports_SubInstructions_Type;
	
	@FindBy(xpath = "(//div[@col-id='SCROE Status'])[4] | (//div[@col-id='Settled Status'])[4]")
	public WebElement reports_ScroeStatus3rdRow;
	
	@FindBy(xpath = "(//div[@col-id='SCROE Status'])[5] | (//div[@col-id='Settled Status'])[5]")
	public WebElement reports_ScroeStatus4thRow;
	
	@FindBy(xpath = "(//div[@col-id='SCROE Status'])[6] | (//div[@col-id='Settled Status'])[6]")
	public WebElement reports_ScroeStatus5thRow;
	
	@FindBy(xpath = "(//div[@col-id='SCROE Status'])[7] | (//div[@col-id='Settled Status'])[7]")
	public WebElement reports_ScroeStatus6thRow;

}

