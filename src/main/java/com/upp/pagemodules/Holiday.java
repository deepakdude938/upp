package com.upp.pagemodules;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_Configuration;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

public class Holiday extends BaseClass{
	
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int rowNum;
	public static JavascriptClick jsClick;
	public static DateUtils dateutil;
	public static ScrollTypes scroll;
	public static String productName;
	public static Object_Configuration hd;
	public JavascriptClick js ;
	public String ID;
	

	public Holiday() {
		od = new Object_NewDeal();
		hd=new Object_Configuration();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		js=new JavascriptClick(driver);
	}

	public void createNewHoliday(String iD) throws InvalidFormatException, IOException, Exception {
		ID =iD;
		 hd.configurationButton.click();
		 hd.configuration_HolidayButton.click();
		 hd.configuration_HolidayAddButton.click();
		 hd.configuration_HolidayInputDate.click();
		 LocalDate now = new LocalDate();
		 LocalDate friday = now.withDayOfWeek(DateTimeConstants.FRIDAY);
		 String day = (Integer.parseInt(friday.toString().split("[/-]")[2])/1)+"";
		 System.out.println(day);
		 By excecutionDay = By.xpath("//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"+day+"']");
		 driver.findElement(excecutionDay).click();
		 String holdayApplicableForInput =  externalData.getFieldData(iD,"Holidays and Holiday Drafts","Applicable For").trim();

		 hd.configuration_HolidayApplicableFor.sendKeys(holdayApplicableForInput);
		 By holidayApplicableFor = By.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and normalize-space()='"+holdayApplicableForInput+"']");
		 applyExplicitWaitsUntilElementVisible(holidayApplicableFor, 10);
		 driver.findElement(holidayApplicableFor).click();
		 hd.configuration_HolidayName.sendKeys(externalData.getFieldData(iD,"Holidays and Holiday Drafts","Holiday").trim());
		 hd.configuration_Country.sendKeys(externalData.getFieldData("TS10","Holidays and Holiday Drafts","All countries").trim());
		 String country = externalData.getFieldData("TS10","Holidays and Holiday Drafts","All countries").trim();
		 driver.findElement(By.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and normalize-space()='"+country+"']")).click();
		 hd.configuration_HolidayNextButton.click();
		 hd.configuration_HolidayNextButton2.click();
		 hd.configuration_HolidaySaveButton.click();
		 hd.configuration_HolidayOkButton.click();
		 
}
	
	public void createNewHoliday(String iD,String date) throws InvalidFormatException, IOException, Exception {
		 ID =iD;
		 hd.configurationButton.click();
		 hd.configuration_HolidayButton.click();
		 hd.configuration_HolidayAddButton.click();
		 hd.configuration_HolidayInputDate.click();
		 String day = date.split("[/-]")[0];
		 By excecutionDay = By.xpath("//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"+day+"']");
		 driver.findElement(excecutionDay).click();
		 String holdayApplicableForInput =  externalData.getFieldData(iD,"Holidays and Holiday Drafts","Applicable For").trim();

		 hd.configuration_HolidayApplicableFor.sendKeys(holdayApplicableForInput);
		 By holidayApplicableFor = By.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and normalize-space()='"+holdayApplicableForInput+"']");
		 applyExplicitWaitsUntilElementVisible(holidayApplicableFor, 10);
		 driver.findElement(holidayApplicableFor).click();
		 hd.configuration_HolidayName.sendKeys(externalData.getFieldData(iD,"Holidays and Holiday Drafts","Holiday").trim());
		 hd.configuration_Country.sendKeys(externalData.getFieldData("TS10","Holidays and Holiday Drafts","All countries").trim());
		 String country = externalData.getFieldData("TS10","Holidays and Holiday Drafts","All countries").trim();
		 driver.findElement(By.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and normalize-space()='"+country+"']")).click();
		 hd.configuration_HolidayNextButton.click();
		 hd.configuration_HolidayNextButton2.click();
		 hd.configuration_HolidaySaveButton.click();
		 hd.configuration_HolidayOkButton.click();
		 
}

	public void approveHoliday() throws Exception, IOException {
		
		 hd. configuration_HolidayDraftsButton.click();
		 hd. configuration_HolidaySearchBox.sendKeys(externalData.getFieldData(ID,"Holidays and Holiday Drafts","Holiday").trim());;
		 hd. configuration_HolidaySearchBoxButton.click();
		 Thread.sleep(1000);
		 hd. configuration_HolidayKebabMenu.click();
		 hd. configuration_HolidayEditTab.click();
		 hd.configuration_HolidayNextButton2.click();
		 Thread.sleep(1000);
		 hd.configuration_HolidayNextButton2.click();
		 hd.configuration_HolidayApproveButton.click();
		 hd.configuration_HolidayOkButton.click();
	 
	}

}
