package com.upp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upp.base.*;

public class Object_DealLifecycle extends BaseClass{
	
	public Object_DealLifecycle() {
		PageFactory.initElements(driver, this);
	}


	
	@FindBy(xpath="//div[contains(text(),'close')]")
	public WebElement Close_icon;
	
	@FindBy(xpath="//button[contains(text(),'Yes')]")
	public WebElement Yes_Icon;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	public WebElement Ok_Icon;
	
	@FindBy(xpath="//label[normalize-space()='DEAL LIFECYCLE']")
	public WebElement Deal_Lifecycle;
	
	@FindBy(xpath="//a[normalize-space()='Lifecycle Maker']")
	public WebElement LifecycleMakerIcon;
	
	@FindBy(xpath="(//input[@ref='eFloatingFilterText'])[1]")
	public WebElement LifecycleMaker_Dealid;
	
	@FindBy(xpath="(//i[@class='ui-icon ic ic-doc_submit_icon'])[1]")
	public WebElement LifecycleMaker_submit;
	
	@FindBy(xpath="//span[@class='ui-align-right ui-icon ui-icon-add ui-circle ui-relative ui-ripple ng-star-inserted']")
	public WebElement LifecycleMaker_plusIcon;
	
	@FindBy(xpath="(//select[@class='ui-dropdown-select'])[1]")
	public WebElement LifecycleMaker_DocumentType;
	
	@FindBy(xpath="(//select[@class='ui-dropdown-select'])[2]")
	public WebElement LifecycleMaker_DocumentNature;
	
	@FindBy(xpath="(//button[normalize-space()='Add'])[1]")
	public WebElement LifecycleMaker_ADDButton;
	
	@FindBy(xpath="//input[@id='txt-generic-fileUpload-fileInput-v1']")
	public WebElement LifecycleMaker_FileInput;
	
	@FindBy(xpath="//button[normalize-space()='submit']")
	public WebElement LifecycleMaker_SubmitButton;
	
	@FindBy(xpath="//a[normalize-space()='Lifecycle Checker']")
	public WebElement LifecycleCheckerIcon;
	
	@FindBy(xpath="//textarea[@placeholder='Add your comments here ...']")
	public WebElement AddYourComments;
	
	@FindBy(xpath="//button[normalize-space()='Approve']")
	public WebElement Appprove_Button;
	
	@FindBy(xpath="(//dd[@class='truncate status'])[1]")
	public WebElement Deal_Status;
	
}
