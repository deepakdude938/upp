package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_User extends BaseClass {

	public Object_User() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(@class,'stat-active')]")
	public WebElement usersActive;
	
	@FindBy(xpath = "//label[(text() = ' USERS ' or . = ' USERS ')]")
	public WebElement usersTab;

	@FindBy(xpath = "//a[contains(text(),'User Maker')]")
	public WebElement userMaker;

	@FindBy(xpath = "//div[@id='tab-perms-users-management-directory-v1']")
	public WebElement userMaker_directory;

	@FindBy(id = "sel-generic-searchBar-searchInput-v1")
	public WebElement userMaker_search;

	@FindBy(id = "btn-generic-searchBar-search-v1")
	public WebElement userMaker_searchIcon;

	@FindBy(xpath="(//td[@class='ui-grid-cell ng-star-inserted'])[1]//div//span")
	public WebElement userMaker_userList;
	
	@FindBy(xpath = "//div[contains(@class,'icon-edit')]")
	public WebElement userMaker_editIcon;

	@FindBy(xpath = "//div[contains(@class,'icon-add')]")
	public WebElement userMaker_addIcon;

	@FindBy(xpath = "//input[@id='txt-perms-users-onBoardUpdate-phone-v1']")
	public WebElement userMaker_phoneNumberTxt;

	@FindBy(xpath = "//div[@id='sel-deals-basicDetails-country-v1']//select")
	public WebElement userMaker_country;

	@FindBy(xpath = "//div[@id='sel-perms-users-onBoardUpdate-roles-v1']//select")
	public WebElement userMaker_Role;
	
	@FindBy(xpath = "(//ui-dropdown[@placeholder='Select roles']//select)[2]")
	public WebElement userMaker_Role1;
	

	@FindBy(xpath = "//input[@id='txt-generic-autocomplete-input-v1']")
	public WebElement userMaker_processingunit;
	
	@FindBy(xpath = "(//input[@id='txt-generic-autocomplete-input-v1'])[2]")
	public WebElement userMaker_processingunit1;

	@FindBy(xpath = "//button[@id='btn-perms-users-onBoardUpdate-updateOnboard-v1']")
	public WebElement userMaker_onboard;

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement userMaker_ok;

	@FindBy(xpath = "//div[contains(@class,'ui-remove-icon')]")
	public WebElement userMaker_remove;
	
	@FindBy(xpath = "//label[text()=' Permissions ']")
	public WebElement userPermissions;
	
	@FindBy(xpath = "//a[@href='/user/onboarded']")
	public WebElement onBoardusers;
	
	@FindBy(xpath = "//span[contains(@class,'icon-plus')]")
	public WebElement plusIcon;
	

	// objects for user checker

	@FindBy(xpath = "//a[contains(text(),'User Checker')]")
	public WebElement userChecker;

	@FindBy(xpath = "//tr//td[2]//span[contains(@class,'ui-text')]")
	public List<WebElement> userChecker_user;

	@FindBy(id = "ic-generic-menu-showMenu-v1")
	public WebElement userChecker_menu;

	@FindBy(xpath = "//div[contains(text(),'Approve ')]")
	public WebElement userChecker_approve;

	@FindBy(xpath = "//input[@id='swal-input1']")
	public WebElement userChecker_approveMessage;

	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	public WebElement userchecker_Yes;

	@FindBy(xpath = "//div[contains(@id,'menu-generic-sidemenu-Deals')]")
	public WebElement userchecker_dealList;

	@FindBy(xpath = "//div[contains(@id,'menu-generic-sidemenu-Deals')]")
	public List<WebElement> user_dealList;

	// object for deactivate user
	@FindBy(xpath = "//button[contains(@class,'stat-inactive')]//span")
	public WebElement userchecker_inactive;
	
	
	//verify user objects
	@FindBy(xpath = "(//div[@class='ui-icon ui-circle ui-icon-edit ui-ripple ng-star-inserted'])[1]")
	public WebElement usermaker_editIcon;
	
	@FindBy(id = "txt-perms-users-onBoardUpdate-email-v1")
	public WebElement usermaker_email;
	
	@FindBy(id = "txt-perms-users-onBoardUpdate-phone-v1")
	public WebElement usermaker_phone;
	
	@FindBy(xpath = "//div[@id='sel-perms-users-onBoardUpdate-roles-v1']//select")
	public WebElement usermaker_role;
	
	@FindBy(xpath = "//button[contains(@class,'stat-active')]")
	public WebElement usermaker_active;
	
	@FindBy(id = "swal2-content")
	public WebElement user_deleted_message;
	
	@FindBy(id = "txt-perms-users-onBoardUpdate-userName-v1")
	public WebElement user_Username;
	
	@FindBy(id = "ic-perms-users-onBoardUpdate-close-v1")
	public WebElement user_CloseIcon;
	
	@FindBy(xpath = "(//div[@id='sel-perms-users-onBoardUpdate-roles-v1']//select)[2]")
	public WebElement User_roles_dropdown;
}
