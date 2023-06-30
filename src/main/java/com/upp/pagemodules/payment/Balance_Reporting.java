package com.upp.pagemodules.payment;

import java.time.Duration;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.CommonUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import com.upp.utils.DateUtils;

public class Balance_Reporting extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public JavascriptClick js;
	public static DateUtils dateutil;

	public Balance_Reporting() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
	}

	public void Balance_Reporting_Tnx(String TSID) throws Exception {

		applyExplicitWaitsUntilElementClickable(od.payments_ScheduledInstructionIcon, Duration.ofSeconds(5));
		od.payments_ScheduledInstructionIcon.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.PaymentsPlusIcon, Duration.ofSeconds(5));
		od.PaymentsPlusIcon.click();
		Thread.sleep(1000);
		String InstructionType = externalData.getFieldData(TSID, "Balance Reporting", "Select Instruction Type");
		System.out.println("Instruction type is " + InstructionType);
		By InstructionButton = By
				.xpath("//div[@class='ui-align-left ui-relative ui-inline-block ui-label'][normalize-space()='"
						+ InstructionType + "']");
		applyExplicitWaitsUntilElementVisible(InstructionButton, 10);
		driver.findElement(InstructionButton).click();
		applyExplicitWaitsUntilElementClickable(od.payments_Proceed, Duration.ofSeconds(5));
		od.payments_Proceed.click();
		od.Balance_Reporting_SourceAccounts.click();
		od.Balance_Reporting_SelectAll.click();
		od.Balance_Reporting_AddPlusIcon.click();
		Thread.sleep(1500);
		for (WebElement contact : od.notifications_ContactList) {
			contact.click();
		}
		od.Balance_Reporting_UpadteButton.click();
		od.Balance_Reporting_NextButton.click();
		od.Balance_Reporting_StartDate.click();

		String startdate = dateutil.getDay();
		By excecutionDay = By.xpath(
				"//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"
						+ startdate + "']");
		applyExplicitWaitsUntilElementVisible(excecutionDay, 5);
		driver.findElement(excecutionDay).click();

		applyExplicitWaitsUntilElementClickable(od.Balance_Reporting_EndDate, Duration.ofSeconds(5));
		od.Balance_Reporting_EndDate.click();

		String enddate = dateutil.getDay();
		int day_int = Integer.parseInt(enddate) + 2;
		enddate = Integer.toString(day_int);
		By excecutionEndDay = By.xpath(
				"//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"
						+ enddate + "']");
		Thread.sleep(1000);
		try {
			driver.findElement(excecutionEndDay).click();
			}
			catch(Exception e)
			{   
			if(Integer.parseInt(DateUtils.getDay())>=29)
			{
				excecutionEndDay = By.xpath("//td[contains(@class,'ui-calendar-outFocus') and normalize-space()='2'] ");
				driver.findElement(excecutionEndDay).click();
			}
			}

		dropdown.selectByVisibleText(od.Balance_Reporting_ScheduleAt,
				externalData.getFieldData(TSID, "Balance Reporting", "Schedule At"));
		
		od.Balance_Reporting_Frequency.click();
		
		if(externalData.getFieldData(TSID, "Balance Reporting", "Frequency").equalsIgnoreCase("Days"))
		{
			od.Balance_Reporting_Days.click();
			
		}
		Thread.sleep(2000);
		od.Balance_Reporting_NextSummaryArrow.click();
		Thread.sleep(2000);

	}
}
