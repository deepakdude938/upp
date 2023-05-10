package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_Parties extends BaseClass{
	
	public Object_Parties() {
		PageFactory.initElements(driver, this);
	}


	
	@FindBy(xpath="//label[contains(text(),'Parties')]")
	public WebElement parties_Icon;
	
	@FindBy(xpath="//a[normalize-space()='Party Maker']")
	public WebElement partyMaker_Icon;
	
	@FindBy(xpath="//div[contains(text(),'Add New')]")
	public WebElement PartyMaker_AddNewButon;
	
	@FindBy(xpath="//label[@class='ui-text-m ui-text-semi ui-align-left']")
	public WebElement PartyMaker_internalOrExternal;
	
	@FindBy(xpath="//span[@class='slider round']")
	public WebElement PartyMaker_internal_slider;
	
	
	@FindBy(id="txt-config-party-partyBasicDetails-customerId-v1")
	public WebElement PartyMaker_customerId;
	
	@FindBy(id="txt-config-party-partyBasicDetails-name-v1")
	public WebElement PartyMaker_partyName;
	
	
	@FindBy(id="txt-generic-autocomplete-search")
	public WebElement PartyMaker_PUSearch;
	
	@FindBy(id="sel-parties-basicDetails-remarks-v1")
	public WebElement PartyMaker_partyRemarks;
	
	
	@FindBy(id="btn-config-party-partyBasicDetails-next-v1")
	public WebElement PartyMaker_party_nextButton;
	
	@FindBy(xpath="(//select[@id='sel-parties-basicDetails-countrys-v1'])[1]")
	public WebElement PartyMaker_country;
	
	@FindBy(xpath="//button[contains(text(),'OK')]")
	public WebElement PartyMaker_OKButton;
	
	@FindBy(id="ic-generic-partyAccounts-getStarted-v1")
	public WebElement PartyMaker_partyAccountsAddButton;
	
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[1]")
	public WebElement PartyMaker_PaymentSystem;
	
	@FindBy(id ="ic-generic-partyAccounts-partyDocuments-v1")
	public WebElement PartyMaker_AddDocument;

	@FindBy(id ="txt-generic-autocomplete-input-v1")
	public WebElement PartyMaker_DocumentType;
	
	@FindBy(id ="txt-generic-partyDocuments-description-v1")
	public WebElement PartyMaker_DocumentDescription;


	@FindBy(xpath="//p[contains(text(),'Summary')]")
	public WebElement PartyMaker_SummaryTab;
	
	@FindBy(xpath="//button[contains(text(),'Submit')]")
	public WebElement PartyMaker_SubmitButton;
	
	@FindBy(xpath="//button[contains(text(),'Yes')]")
	public WebElement PartyMaker_YesButton;
	
	@FindBy(xpath="//a[normalize-space()='Party Checker']")
	public WebElement PartyChecker_Icon;
	
	@FindBy(xpath="(//input[@ref='eFloatingFilterText'])[1]")
	public WebElement PartyChecker_CustomerID_SearchBox;
	
	@FindBy(xpath="(//i[@class='ui-icon ic ic-edit_blue'])[1]")
	public WebElement PartyChecker_EditIcon;
	
	@FindBy(xpath="//button[contains(text(),'Approve')]")
	public WebElement PartyChecker_ApproveButton;
	
	@FindBy(xpath="(//textarea[@placeholder='Add your comments here ...'])[1]")
	public WebElement PartyChecker_AddComment;
	
	@FindBy(id="ic-parties-partiesList-addParty-v1")
	public WebElement PartyMaker_addPartyPlusIcon;
	
	@FindBy(id ="btn-parties-addLinkParty-linkExistingParty-v1")
	public WebElement Party_linkExistingParty;
	
	@FindBy(id ="txt-config-party-linkPartyDetails-customerId-v1")
	public WebElement Party_linkPartyDetails_customerId;
	
	@FindBy(id ="btn-parties-linkParty-search-v1")
	public WebElement Party_linkPartyDetails_searchButton;
	
	@FindBy(xpath="(//input[@name='selectParty'])[1]")
	public WebElement Party_selectPartyCircle;
	
	@FindBy(id ="btn-parties-linkParty-add-v1")
	public WebElement Party_linkPartyDetails_checkBoxIcon;
	
	@FindBy(xpath="(//dd[@class='ng-star-inserted'])[2]")
	public WebElement Party_PartiesNumber;
	
	@FindBy(xpath="(//div[normalize-space()='Inactive'])[1]")
	public WebElement PartyMaker_Status_Inactive;
	
	
}
