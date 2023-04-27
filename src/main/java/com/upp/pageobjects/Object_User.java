package com.upp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_User extends BaseClass {

	public Object_User() {

		PageFactory.initElements(driver, this);
	}

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

	@FindBy(xpath = "//div[contains(@class,'icon-edit')]")
	public WebElement userMaker_editIcon;

	@FindBy(xpath = "//div[contains(@class,'icon-add')]")
	public WebElement userMaker_addIcon;

	@FindBy(xpath = "//input[@id='txt-perms-users-onBoardUpdate-phone-v1']")
	public WebElement userMaker_phoneNumberTxt;

	@FindBy(xpath = "//select[@id='sel-deals-basicDetails-country-v1']")
	public WebElement userMaker_country;

	@FindBy(xpath = "//select[@id='sel-perms-users-onBoardUpdate-roles-v1']")
	public WebElement userMaker_Role;

	@FindBy(xpath = "//input[@id='txt-generic-autocomplete-input-v1']")
	public WebElement userMaker_processingunit;

	@FindBy(xpath = "//button[@id='btn-perms-users-onBoardUpdate-updateOnboard-v1']")
	public WebElement userMaker_onboard;

	// objects for user checker

	@FindBy(xpath = "//a[contains(text(),'User Checker')]")
	public WebElement userChecker;

	@FindBy(xpath = "(//td[2])[2]//div//div//span")
	public WebElement userChecker_user;

}
