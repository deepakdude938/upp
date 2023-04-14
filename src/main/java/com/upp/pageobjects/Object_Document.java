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

	@FindBy(xpath =  "//div[@title='Documents']/i")
	public WebElement documentTab;
	
	//Required document
	
	@FindBy(xpath = "//label[text() = ' Required Documents ']/following-sibling::span//span[@id='ic-deals-dealDocuments-addOtherDocuments-v1']")
	public WebElement requiredDoc_addBtn;
	
	@FindBy(id = "tab-deals-documentWizard-v1")
	public WebElement requiredDoc_docTypeDDl;
	
	@FindBy(xpath = "//input[@id='txt-generic-autocomplete-input-v1']")
	public WebElement requiredDoc_docTypetxt;
	
	
	
	
}