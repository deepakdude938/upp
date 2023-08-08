package com.upp.pagemodules.payment;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

public class Payment_BasicDetails extends BaseClass{
	
	
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public String toAccountNo="";
	public static DateUtils dateutil;
	public static String day = "";
	public static JavascriptClick js;

	public Payment_BasicDetails() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);	
		scroll = new ScrollTypes(driver);
		commonutils=new CommonUtils(driver);
		dateutil = new DateUtils();
		js=new JavascriptClick(driver);
	}
	
	
	public void createPayments_BasicDetails(String TSID, String sourceAccountno, String toaccountNo) throws Exception {
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.payments_ScheduledInstructionIcon, Duration.ofSeconds(5));
		od.payments_ScheduledInstructionIcon.click();
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.PaymentsPlusIcon, Duration.ofSeconds(5));
		od.PaymentsPlusIcon.click();
		Thread.sleep(1000);
		String InstructionType = externalData.getFieldData(TSID, "Scheduled", "Select Instruction Type");
		By InstructionButton = By
				.xpath("//div[@class='ui-align-left ui-relative ui-inline-block ui-label'][normalize-space()='"
						+ InstructionType + "']");
		applyExplicitWaitsUntilElementVisible(InstructionButton, 10);
		driver.findElement(InstructionButton).click();
		applyExplicitWaitsUntilElementClickable(od.payments_Proceed, Duration.ofSeconds(5));
		od.payments_Proceed.click();
		od.payments_BasicName.clear();
		od.payments_BasicName.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Basic Details Name"));
		dropdown.selectByVisibleText(od.payments_Purpose, externalData.getFieldData(TSID, "Scheduled", "Purpose"));
		od.payments_SourceAccount.sendKeys(sourceAccountno);
		By sourceaccountselect = By.xpath("//div[contains(text(),'" + sourceAccountno + "')]");
		driver.findElement(sourceaccountselect).click();

		dropdown.selectByVisibleText(od.payments_BalanceConsideration,
				externalData.getFieldData(TSID, "Scheduled", "Balance Consideration"));
		if (((externalData.getFieldData(TSID, "Scheduled", "Split")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Split")).equalsIgnoreCase("Yes"))) {
			od.payments_SplitBalanceSlider.click();
			applyExplicitWaitsUntilElementClickable(od.payment_specifyAmountAs1, Duration.ofSeconds(4));
			dropdown.selectByVisibleText(od.payment_specifyAmountAs1,externalData.getFieldData(TSID, "Scheduled", "Specify amount as"));
			applyExplicitWaitsUntilElementClickable(od.payment_value1, Duration.ofSeconds(4));
			od.payment_value1.sendKeys(externalData.getFieldData(TSID, "Scheduled", "value"));
			Thread.sleep(1500);
			
			
		}
		if (((externalData.getFieldData(TSID, "Scheduled", "Partial Payment")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Partial Payment")).equalsIgnoreCase("Yes"))) {
			od.payments_PartialpaymentSlider.click();
		}

		Thread.sleep(500);
		try {
			od.payments_NextArrowButtonTransferBasic.click();
		} catch (Exception e) {
			handleElementClickException(od.payments_NextArrowButtonTransferBasic);
		}
	}
}
