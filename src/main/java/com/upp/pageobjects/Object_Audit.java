package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_Audit extends BaseClass{
	
	public Object_Audit() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//label[normalize-space()='Audit']")
	public WebElement Audit_Icon;
	
	@FindBy(xpath="(//a[normalize-space()='Deals'])[1]")
	public WebElement Audit_Deals;
	
	@FindBy(xpath="(//input[@ref='eFloatingFilterText'])[1]")
	public WebElement Audit_DealId;
	
	@FindBy(xpath="//li[@class='ui-chip active ng-star-inserted'][2]")
	public WebElement Audit_Deal_Party_icon;
	
	@FindBy(xpath="//span[contains(text(),'dealParties added:')]")
	public WebElement Audit_DealPartyadded;
	
	@FindBy(xpath="//span[contains(text(),'dealPartyAccounts added:')]")
	public WebElement Audit_DealPartyaccountsadded;
	
	@FindBy(xpath="//span[contains(text(),'dealPartyDocuments added:')]")
	public WebElement Audit_Deal_documents_added;
	
	@FindBy(xpath="//span[contains(text(),'dealPartyContacts added:')]")
	public WebElement Audit_Deal_contacts_added;
	
	@FindBy(xpath="//a[normalize-space()='Budget']")
	public WebElement Audit_Budget;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[1]")
	public WebElement Budget_DealId;

	@FindBy(xpath="(//select[@class='ui-dropdown-select'])[1]")
	public WebElement Budget_budgetDropdown;
	
	@FindBy(xpath="//button[normalize-space()='Submit']")
	public WebElement Budget_submitButton;
	
	@FindBy(xpath="//td[starts-with(@title, 'Total Allocated Budget')]")
	public WebElement Budget_Total_Allocated_Budget;
	
	@FindBy(xpath="//label[normalize-space()='Basic Details']")
	public WebElement Budget_BasicDetails;
	
	@FindBy(xpath="//div[contains(text(),'monthly')]")
	public WebElement Budget_Monthly;
	
	@FindBy(xpath="(//div[contains(text(),'1,000')])[1]")
	public WebElement Budget_Amount_Allocated;
	
	@FindBy(xpath="(//div[contains(text(),'1,000')])[2]")
	public WebElement Budget_Total_Budget_Allocated_Amount;
	
	
	
	
	
	
}
