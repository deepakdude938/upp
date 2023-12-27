package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_Document extends BaseClass {

	public Object_Document() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@title='Documents']/i")
	public WebElement documentTab;

	// Required document

	@FindBy(xpath = "//label[text() = ' Required Documents ']/following-sibling::span//span[@id='ic-deals-dealDocuments-addOtherDocuments-v1']")
	public WebElement requiredDoc_addBtn;

	@FindBy(id = "tab-deals-documentWizard-v1")
	public WebElement requiredDoc_docTypeDDl;

	@FindBy(xpath = "//input[@id='txt-generic-autocomplete-input-v1']")
	public WebElement requiredDoc_docTypetxt;

	@FindBy(xpath = "//select[@class='ui-dropdown-select']")
	public WebElement requiredDoc_docNature;

	@FindBy(id = "btn-deals-dealDocuments-otherDocuments-addDocument-v1")
	public WebElement requiredDoc_addDocBtn;

	@FindBy(xpath = "//label[text()=' Required Documents ']")
	public WebElement requiredDoc_label;

	@FindBy(xpath = "//span[@class ='schedule']")
	public WebElement requiredDoc_schedule;

	@FindBy(xpath = "(//li[contains(@class,'Schedule_li')])[1]")
	public WebElement requiredDoc_scheduleAtEod;

	@FindBy(xpath = "(//li[contains(@class,'frequency_li')])[1]")
	public WebElement requiredDoc_frequencyOnce;

	@FindBy(id = "ic-generic-datePicker-calender-v1")
	public WebElement requiredDoc_startDateCalenderIcon;

	@FindBy(xpath = "//td[contains(@class,'today')]")
	public WebElement requiredDoc_todaysDate;

	@FindBy(xpath = "(//li[contains(@class,'reminder_li')])[1]")
	public WebElement requiredDoc_reminderDays;

	@FindBy(id = "txt-deals-dealDocuments-scheduler-repeatEvery-v1")
	public WebElement requiredDoc_repeat;

	@FindBy(xpath = "//button[text()=' Schedule ']")
	public WebElement requiredDoc_scheduleBtn;

	@FindBy(xpath = "//table/tbody/tr/td[4]")
	public WebElement requiredDoc_reminderDate;

	@FindBy(xpath = "//label[text()=' DOCUMENT TRACKER ']")
	public WebElement DocumentTracker;

	@FindBy(xpath = "//input[@formcontrolname='name']")
	public WebElement schedulerName;

	// object for required document maker

	@FindBy(xpath = "//a[@href='/document-tracker/required-document/DEALMAKER']")
	public WebElement requiredDoc_DocumentMaker;

	@FindBy(xpath = "//div[@col-id='manage']//div[@class='grid-cell']//i")
	public WebElement requiredDoc_manageDoc;

	@FindBy(xpath = "//input[@id='txt-generic-fileUpload-fileInput-v1']")
	public WebElement requiredDoc_uploadFile;

	@FindBy(xpath = "//button[text()='submit']")
	public WebElement requiredDoc_submit;

	@FindBy(xpath = "//button[text()=' submit ']")
	public WebElement requiredDoc1_submit;
	
	@FindBy(xpath = "//button[text()='Yes']")
	public WebElement requiredDoc_yesBtn;

	// object for required document schedule

	@FindBy(xpath = "//a[@href ='/document-tracker/required-document']")
	public WebElement requiredDoc_requiredDocSchedule;

//	@FindBy(xpath = "(//div[@role='columnheader']//input)[1]")  documentdb
	@FindBy(xpath = "//input[@aria-label='Deal Id Filter Input']")
	public WebElement requiredDoc_dealId;
	
	@FindBy(xpath = "//i[@title='Create Workitem']")
	public WebElement requiredDoc_createItem;

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement requiredDoc_OkBtn;

//	@FindBy(xpath = "//div[@role ='gridcell' and @aria-colindex='5']")
	@FindBy(xpath = "(//div[@col-id='status'])[2]")
	public WebElement requiredDoc_docStatus;
	
	// object for document checker

	@FindBy(xpath = "//a[@href='/document-tracker/required-document/DEALCHECKER']")
	public WebElement requiredDoc_DocumentChecker;

	@FindBy(xpath = "//div[@class='review-container ng-star-inserted']//textarea")
	public WebElement requiredDoc_comment;
	
	@FindBy(xpath = "//button[contains(text(),'Approve')]")
	public WebElement requiredDoc_approveBtn;

}
