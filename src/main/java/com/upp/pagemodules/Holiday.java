package com.upp.pagemodules;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
		 String friday1=friday+"";
		String today=DateUtils.getDate(0);
		 String day = (Integer.parseInt(friday.toString().split("[/-]")[2])/1)+"";
		 System.out.println(day);
		 By excecutionDay = By.xpath("//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"+day+"']");
		 if(Integer.parseInt(today.split("-")[1])!=Integer.parseInt(friday1.split("-")[1])) {
			 excecutionDay = By.xpath("//td[contains(@class,today) and contains(@class,'ui-calendar-outFocus')]//a[normalize-space()='"+day+"']");
		 }
	
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
	public void createNewHoliday_For_GB_Account(String TSID) throws InvalidFormatException, IOException, Exception {
		
		Thread.sleep(5000);
	   	ID =TSID;
	    applyExplicitWaitsUntilElementClickable(hd.configurationButton,Duration.ofSeconds(20));
		 hd.configurationButton.click();
		 hd.configuration_HolidayButton.click();
		 hd.configuration_HolidayAddButton.click();
		 hd.configuration_HolidayInputDate.click();
		 String holiday_name=dateutil.getCurrentDate_and_Month();
		 String holiday_name1="TS126-"+holiday_name;
		 System.out.println("the holiday name:"+holiday_name1);
		 String day = dateutil.getDay();
		 int day_int = Integer.parseInt(day) + 1;
			
		 System.out.println(day_int);
		 By excecutionDay = By.xpath("//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"+day_int+"']");
		 driver.findElement(excecutionDay).click();
		 String holdayApplicableForInput =  externalData.getFieldData(TSID,"Holidays and Holiday Drafts","Applicable For").trim();

		 hd.configuration_HolidayApplicableFor.sendKeys(holdayApplicableForInput);
		 By holidayApplicableFor = By.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and normalize-space()='"+holdayApplicableForInput+"']");
		 applyExplicitWaitsUntilElementVisible(holidayApplicableFor, 10);
		 driver.findElement(holidayApplicableFor).click();
	//	 hd.configuration_HolidayName.sendKeys(externalData.getFieldData(TSID,"Holidays and Holiday Drafts","Holiday").trim());
		 hd.configuration_HolidayName.sendKeys(holiday_name1);
		 hd.configuration_Country.sendKeys(externalData.getFieldData(TSID,"Holidays and Holiday Drafts","All countries").trim());
		 String country = externalData.getFieldData(TSID,"Holidays and Holiday Drafts","All countries").trim();
		 driver.findElement(By.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and normalize-space()='"+country+"']")).click();
		 hd.configuration_HolidayNextButton.click();
		 hd.configuration_HolidayNextButton2.click();
		 hd.configuration_HolidaySaveButton.click();
		 hd.configuration_HolidayOkButton.click();
		 
}
	
	public void delete_Tomorrow_Holiday(String TSID) throws InvalidFormatException, IOException, Exception {
	   	ID =TSID;
	   	Thread.sleep(5000);
	    applyExplicitWaitsUntilElementClickable( hd.configurationButton,Duration.ofSeconds(20));
		 hd.configurationButton.click();
		 Thread.sleep(1500);
		 hd.configuration_HolidayButton.click();
		 Thread.sleep(1500);
		 String holiday_name=dateutil.getCurrentDate_and_Month();
		 String holiday_name1="TS126-"+holiday_name;
		 System.out.println("The holiday_name is:"+holiday_name1);
		 hd.holiday_searchBox.sendKeys(holiday_name1);

//		 List<WebElement> holidays= driver.findElements(By.xpath("//div[@col-id='name' and normalize-space()='"+holiday_name+"']"));
//		 System.out.println(holidays.size());

			 Thread.sleep(1500);
			 applyExplicitWaitsUntilElementClickable( hd.configuration_HolidayKebabMenu,Duration.ofSeconds(20));
			 hd.configuration_HolidayKebabMenu.click();
			 Thread.sleep(1500);
			 applyExplicitWaitsUntilElementClickable( hd.configuration_Holiday_Delete,Duration.ofSeconds(20));
			 hd.configuration_Holiday_Delete.click();
			 Thread.sleep(1500);
			 applyExplicitWaitsUntilElementClickable( hd.configuration_Holiday_Description,Duration.ofSeconds(20));
			 hd.configuration_Holiday_Description.sendKeys("ok");
			 Thread.sleep(1500);
			 hd.PU_SubmitButton.click();
			 Thread.sleep(1500);
			 applyExplicitWaitsUntilElementClickable(hd.PU_OkButton,Duration.ofSeconds(20));
			 hd.PU_OkButton.click();
			 Thread.sleep(1500);
//			 hd. configuration_HolidayDraftsButton.click();
			 handleElementClickException(hd. configuration_HolidayDraftsButton);
			 Thread.sleep(1500);
			 hd. configuration_HolidaySearchBox.sendKeys(holiday_name1);
			 Thread.sleep(1500);
			 hd. configuration_HolidaySearchBoxButton.click();
			 Thread.sleep(1500);
			 hd.configuration_HolidayKebabMenu.click();
			 Thread.sleep(1500);
			 hd.configuration_HolidayEditTab.click();
			 Thread.sleep(1500);
			 hd.configuration_HolidayNextButton2.click();
			 Thread.sleep(1000);
			 hd.configuration_HolidayNextButton2.click();
			 Thread.sleep(1500);
			 hd.configuration_HolidayApproveButton.click();
			 Thread.sleep(1500);
			 hd.configuration_HolidayOkButton.click();
			 Thread.sleep(1500);
		 
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
		
		 click(hd. configuration_HolidayDraftsButton);
		 hd. configuration_HolidaySearchBox.sendKeys(externalData.getFieldData(ID,"Holidays and Holiday Drafts","Holiday").trim());;
		 hd. configuration_HolidaySearchBoxButton.click();
		 Thread.sleep(1000);
		 hd. configuration_HolidayKebabMenu.click();
		 Thread.sleep(1000);
		 hd. configuration_HolidayEditTab.click();
		 hd.configuration_HolidayNextButton2.click();
		 Thread.sleep(1000);
		 hd.configuration_HolidayNextButton2.click();
		 Thread.sleep(5000);
		 hd.configuration_HolidayApproveButton.click();
		 Thread.sleep(1000);
		 hd.configuration_HolidayOkButton.click();
	 
	}
	public void approveHoliday_with_TSID(String TSID) throws Exception, IOException {
		
		 hd. configuration_HolidayDraftsButton.click();
		 hd. configuration_HolidaySearchBox.sendKeys(externalData.getFieldData(TSID,"Holidays and Holiday Drafts","Holiday").trim());;
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
	
	public void approveHoliday_For_GB_Account(String TSID) throws Exception, IOException {
		
		ScrollTypes.scrollVerticalInsideWindowTillWebElementPresent(hd. configuration_HolidayDraftsButton, hd.configuration_LeftSideScrollBar, 9, 1000);
		 hd. configuration_HolidayDraftsButton.click();
		 String holiday_name=dateutil.getCurrentDate_and_Month();
		 String holiday_name1="TS126-"+holiday_name;
		 hd. configuration_HolidaySearchBox.sendKeys(holiday_name1);
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
