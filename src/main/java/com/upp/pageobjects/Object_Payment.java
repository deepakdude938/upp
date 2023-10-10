package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_Payment extends BaseClass {

	public Object_Payment() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath  = "//div[text()=' Alerts ']")
	public WebElement Payment_instructionType;
	
	@FindBy(xpath  = "//label[text()='Message']/following-sibling::textarea")
	public WebElement Alert_message;
	
	@FindBy(xpath  = "//span[contains(@id,'addUpdateContacts')]")
	public WebElement Alerts_addContacts;
	
	@FindBy(xpath = "//input[@id='txt-generic-pickContacts-search-v1']")
	public WebElement Alerts_contactNameTextBox;
	
	@FindBy(xpath = "//button[contains(@class,'ui-filter-search-btn')]")
	public WebElement Alerts_contactNameSearch;
	
	@FindBy(xpath = "//div[contains(@class,'control__indicator dl_con_check')]")
	public WebElement Alerts_contactCheckBox;
	
	@FindBy(xpath = "//button[@id='btn-generic-pickContacts-addContacts-v1']")
	public WebElement Alerts_contactUpdate;
	
	@FindBy(xpath = "//i[@id='ic-deals-instruction-waterfallBasic-next-v1']")
	public WebElement Alerts_basicDetailsNext;
	
	//Alerts schedule
	
	@FindBy(xpath = "(//i[@id='ic-generic-datePicker-calender-v1'])[1]")
	public WebElement Alerts_startsDate;
	
	@FindBy(xpath ="//td[contains(@class,'today')]")
	public WebElement Alerts_todaysDate;
	
	@FindBy(xpath = "//i[@id='ic-generic-datePicker-calender-v1']")
	public WebElement Alerts_endDate;
	
	@FindBy(xpath = "(//td[not(contains(@class,'calendar-invalid'))])[2]")
	public WebElement Alerts_nextEndDate;
	
	@FindBy(xpath = "//input[@id='txt-generic-autocomplete-input-v1']")
	public WebElement Alerts_Frequency;
	
	@FindBy(xpath = "//div[contains(text(),'Days')]")
	public WebElement Alerts_daysFrequency;
	
	@FindBy(xpath="//select[contains(@id,'deals-instruction-fundTransferSchedule-scheduleAt')]")
	public WebElement Alerts_scheduleAt;
	
	@FindBy(xpath = "//input[@id='txt-generic-timePicker-input-v1']")
	public WebElement Alerts_scheduleTime;
	
	@FindBy(xpath = "//div[@class='ui-btn-nav-ct']//i[contains(@class,'ui-fab-next')]")
	public WebElement Alerts_nextBtn;
	
	@FindBy(xpath = "//p[@class='t-c text-overflow']")
	public WebElement Alerts_summaryMessage;
	
	
	@FindBy(xpath = "//b[@class='ng-star-inserted']")
	public WebElement Alerts_summarySchedule;
	
	
	@FindBy(id = "ic-generic-menu-showMenu-v1")
	public WebElement Alerts_Menu;
	
	@FindBy(xpath = "//div[contains(text(),'Edit')]")
	public WebElement Alerts_Edit;
	
	@FindBy(xpath = "//div[contains(text(),'Weekly')]")
	public WebElement Alerts_weeklyFrequency;	
	
	@FindBy(xpath = "//div[contains(text(),' Mon ')]")
	public WebElement Alerts_weeklyDayFrequency;	
	
	
	
	

}
