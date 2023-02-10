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
	
}
