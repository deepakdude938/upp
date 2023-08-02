package com.upp.pagemodules.payment;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Payment;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

public class Payment_Alerts extends BaseClass {

	public static Object_NewDeal od;
	public static Object_Payment op;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public String toAccountNo = "";
	public static DateUtils dateutil;
	public static String day = "";

	public Payment_Alerts() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		dateutil = new DateUtils();
		op = new Object_Payment();
	}

	public void createPaymentsAlert(String TSID) throws Exception {

		toAccountNo = DealPage.toaccountNo;
		applyExplicitWaitsUntilElementClickable(od.payments_ScheduledInstructionIcon, Duration.ofSeconds(5));
		od.payments_ScheduledInstructionIcon.click();
		applyExplicitWaitsUntilElementClickable(od.payments_GetStarted, Duration.ofSeconds(5));
		od.payments_GetStarted.click();
		String InstructionType = externalData.getFieldData(TSID, "Alerts", "Select Instruction Type");
		By InstructionButton = By
				.xpath("//div[@class='ui-align-left ui-relative ui-inline-block ui-label'][normalize-space()='"
						+ InstructionType + "']");
		applyExplicitWaitsUntilElementVisible(InstructionButton, 10);
		driver.findElement(InstructionButton).click();
		applyExplicitWaitsUntilElementClickable(od.payments_Proceed, Duration.ofSeconds(5));
		od.payments_Proceed.click();
		op.Alert_message.sendKeys(externalData.getFieldData(TSID, "Alerts", "Message"));
		op.Alerts_addContacts.click();
		String contactName = externalData.getFieldData(TSID, "Basic Details", "Contact-Name");
		System.out.println(contactName);
		op.Alerts_contactNameTextBox.sendKeys(contactName);
		op.Alerts_contactNameSearch.click();
		applyExplicitWaitsUntilElementClickable(op.Alerts_contactCheckBox, Duration.ofSeconds(10));
		op.Alerts_contactCheckBox.click();
		op.Alerts_contactUpdate.click();
		op.Alerts_basicDetailsNext.click();
		Thread.sleep(4000);
		op.Alerts_startsDate.click();
		op.Alerts_todaysDate.click();
		op.Alerts_Frequency.click();
		op.Alerts_daysFrequency.click();
		Thread.sleep(4000);
		op.Alerts_endDate.click();
		Thread.sleep(4000);
		op.Alerts_nextEndDate.click();
		Thread.sleep(4000);
		op.Alerts_scheduleAt.click();
		dropdown.selectByVisibleText(op.Alerts_scheduleAt, "At specific time");
		String time1 = DateUtils.getCurrentTimeUTC();
		op.Alerts_scheduleTime.sendKeys(time1);
		Thread.sleep(4000);
		op.Alerts_nextBtn.click();
		Thread.sleep(4000);
		}

}
