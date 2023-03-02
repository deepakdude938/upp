package com.upp.pageobjects;

import java.util.List;

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
	
	@FindBy(xpath="//select[@id='sel-deals-basicDetails-product-v1']")
	public WebElement basicDetails_ProductDropDown;	
	
	@FindBy(xpath="//select[@id='sel-deals-basicDetails-country-v1']")
	public WebElement countryIndiaDropDown;
	
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
	
	@FindBy(xpath="//i[@class='ui-fab ui-wizard-icon ui-icon-deal-parties ng-tns-c408-8']")
	public WebElement parties_icon;
	
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
	
	
}
