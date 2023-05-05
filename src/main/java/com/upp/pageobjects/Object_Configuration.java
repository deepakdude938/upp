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
	
	//Holiday
	
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
	
	//Product

	@FindBy(id="menu-generic-sidemenu-Configuration-MANAGE_CONFIGS-v1")
	public WebElement configuration_ManageConfigs;
	
	@FindBy(xpath="//div[@class='ui-align-right card-text ui-text-semi ui-text-xs blue' and normalize-space()='Product']")
	public WebElement configuaration_Product;
	
	@FindBy(xpath="//div[contains(@class,'ui-icon-add') and normalize-space()='Add New']")
	public WebElement configurationProduct_AddNew;
	
	@FindBy(id="txt_productConfigDetails_name")
	public WebElement configurationProduct_Name;
	
	@FindBy(id="txt_productConfigDetails_description")
	public WebElement configurationProduct_Description;
	
	@FindBy(xpath="//label[normalize-space()='Select Tabs']/following-sibling::ui-autocomplete/child::div/child::input")
	public WebElement configurationProduct_SelectTabs;
	
	@FindBy(xpath="//label[normalize-space()='Select Tabs']")
	public WebElement configurationProduct_SelectTabsText;
	
	
	@FindBy(xpath="//label[normalize-space()='Schedule Instruction Types']/following-sibling::ui-autocomplete/child::div/child::input")
	public WebElement configurationProduct_ScheduleInstructionType;
	
	@FindBy(xpath="//label[normalize-space()='Schedule Instruction Types']")
	public WebElement configurationProduct_ScheduleInstructionTypeText;
	
	@FindBy(xpath="//label[normalize-space()='Linked Instruction Types']/following-sibling::ui-autocomplete/child::div/child::input")
	public WebElement configurationProduct_LinkedInstructionTypes;
	
	@FindBy(xpath="//label[normalize-space()='Linked Instruction Types']")
	public WebElement configurationProduct_LinkedInstructionTypesText;
	
	@FindBy(xpath="//label[normalize-space()='Fee Instruction Types']/following-sibling::ui-autocomplete/child::div/child::input")
	public WebElement configurationProduct_FeeInstructionTypes;
	
	@FindBy(xpath="//label[normalize-space()='Fee Instruction Types']")
	public WebElement configurationProduct_FeeInstructionTypesText;
	
	@FindBy(xpath="//label[normalize-space()='Notifications']/following-sibling::ui-autocomplete/child::div/child::input")
	public WebElement configurationProduct_Notifications;
	
	@FindBy(xpath="(//label[normalize-space()='Notifications'])[2]")
	public WebElement configurationProduct_NotificationsText;
	
	@FindBy(xpath="//label[normalize-space()='Transaction Purpose(s)']//following::input[@id='input_productConfigDetails_purpose_1']")
	public WebElement configurationProduct_TransactionPurpose;
	
	
	@FindBy(xpath="//button[@class='ui-btn-primary pull-right ng-star-inserted' and normalize-space()='Submit']")
	public WebElement configurationProduct_Submit;
	
	@FindBy(xpath="//button[normalize-space()='Admin Checker']")
	public WebElement configurationProduct_AdminChecker;
	
	@FindBy(id="comment")
	public WebElement configurationProduct_Comment;
	
	@FindBy(xpath="//button[@class='ui-btn-primary w-a pull-right ng-star-inserted' and normalize-space()='Approve']")
	public WebElement configurationProduct_Approve;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	public WebElement configurationProduct_OkButton;
	
	
	
	
	
	
	
}
