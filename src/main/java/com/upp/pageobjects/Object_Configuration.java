package com.upp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.BaseClass;

public class Object_Configuration extends BaseClass {

	public Object_Configuration() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//label[contains(@class,'opr_icon sidemenu_icon ng-star-inserted')]")
	public WebElement configurationButton;
	
	@FindBy(id="menu-generic-sidemenu-Configuration-holidays-v1")
	public WebElement configuration_HolidayButton;
	
	@FindBy(id="btn-config-holidays-master-add-v1")
	public WebElement configuration_HolidayAddButton;
	
	@FindBy(id="txt-generic-datePicker-input-v1")
	public WebElement configuration_HolidayInputDate;
	
	@FindBy(id="txt-generic-autocomplete-input-v1")
	public WebElement configuration_HolidayApplicableFor;
	
	@FindBy(id="txt-config-holidays-addUpdate-name-v1")
	public WebElement configuration_HolidayName;
	
	@FindBy(xpath="//button[@class='ui-btn-primary ng-star-inserted']")
	public WebElement configuration_HolidayNextButton;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[3]")
	public WebElement configuration_Country;
	
	@FindBy(xpath="//button[contains(@class,'ui-btn-primary ui-align-right btn-preference') and normalize-space()='Next']")
	public WebElement configuration_HolidayNextButton2;
	
	@FindBy(xpath="//button[contains(@class,'ui-btn-primary ui-align-right btn-preference') and normalize-space()='Save']")
	public WebElement configuration_HolidaySaveButton;
	
	@FindBy(xpath="//button[contains(@class,'swal2-confirm')]")
	public WebElement configuration_HolidayOkButton;
	
	@FindBy(xpath="//div[@id='menu-generic-sidemenu-Configuration-HD-v1']")
	public WebElement configuration_HolidayDraftsButton;
	
	@FindBy(id="sel-generic-searchBar-searchInput-v1")
	public WebElement configuration_HolidaySearchBox;
	
	@FindBy(id="btn-generic-searchBar-search-v1")
	public WebElement configuration_HolidaySearchBoxButton;
	
	@FindBy(xpath="//div[@id='ic-generic-menu-showMenu-v1']")
	public WebElement configuration_HolidayKebabMenu;
	
	@FindBy(xpath="//div[@id='popup-menu']")
	public WebElement configuration_HolidayEditTab;
	
	@FindBy(xpath="//button[contains(@class,'ui-btn-primary ui-align-right btn-preference') and normalize-space()='Approve']")
	public WebElement configuration_HolidayApproveButton;
	
	@FindBy(xpath="//a[normalize-space()='Manage Configs']")
	public WebElement configuration_ManageConfigButton1;
	
	@FindBy(xpath="//div[contains(text(),'Processing Units')]")
	public WebElement configuration_ProcessingUnitButton;
	
	@FindBy(xpath="//div[contains(text(),'Add New')]")
	public WebElement configuration_AddNewButton;
	
	@FindBy(xpath="(//input[@id='txt-deals-puDetails-name-v1'])[1]")
	public WebElement PU_Name;
	
	@FindBy(xpath="(//input[@id='txt-deals-puDetails-description-v1'])[1]")
	public WebElement PU_Description;
	
	@FindBy(xpath="(//input[@id='txt-generic-autocomplete-input-v1'])[1]")
	public WebElement PU_Countries;
	
	@FindBy(xpath="(//div[@class='list ng-star-inserted'])[1]")
	public WebElement PU_Countries_Select;
	
	@FindBy(xpath="//button[normalize-space()='Submit']")
	public WebElement PU_SubmitButton;
	
	@FindBy(xpath="//button[normalize-space()='Admin Checker']")
	public WebElement PU_AdminChecker;
	
	@FindBy(xpath="(//i[@class='ui-icon ic ic-edit_blue ng-star-inserted'])[1]")
	public WebElement PU_EditIcon;
	
	@FindBy(xpath="//textarea[@id='comment']")
	public WebElement PU_Comment;
	
	@FindBy(xpath="//button[normalize-space()='Approve']")
	public WebElement PU_ApproveButton;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	public WebElement PU_OkButton;
	

}
