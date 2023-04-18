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

public class Payment extends BaseClass{
	
	
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public String toAccountNo="";
	public static DateUtils dateutil;
	public static String day = "";

	public Payment() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);	
		scroll = new ScrollTypes(driver);
		commonutils=new CommonUtils(driver);
		dateutil = new DateUtils();
	}
	
	
	public void createPaymentsInScheduledInstructionsOnFriday(String TSID, String sourceAccountno, String toaccountNo) throws Exception {

		toAccountNo=DealPage.toaccountNo;
		applyExplicitWaitsUntilElementClickable(od.payments_ScheduledInstructionIcon, Duration.ofSeconds(5));
		od.payments_ScheduledInstructionIcon.click();
		applyExplicitWaitsUntilElementClickable(od.payments_GetStarted, Duration.ofSeconds(5));
		od.payments_GetStarted.click();
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
		}
		if (((externalData.getFieldData(TSID, "Scheduled", "Partial Payment")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Partial Payment")).equalsIgnoreCase("Yes"))) {
			od.payments_PartialpaymentSlider.click();
		}

		od.payments_NextArrowButtonTransferBasic.click();

		if (((externalData.getFieldData(TSID, "Scheduled", "Schedule - Repeating")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Schedule - Repeating")).equalsIgnoreCase("Yes"))) {
			od.payments_PartialpaymentSlider.click();
		}

		if (((externalData.getFieldData(TSID, "Scheduled", "Sweep in")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Sweep in")).equalsIgnoreCase("Yes"))) {
			od.payments_SweepInSlider.click();
		}
//		od.payments_SweepinNextButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_ExecutionDate, Duration.ofSeconds(5));
		od.payments_ExecutionDate.click();
		String day="";
		if(TSID.equals("TS10")) {
		LocalDate now = new LocalDate();
	    LocalDate friday = now.withDayOfWeek(DateTimeConstants.FRIDAY);
		day =friday.toString().split("[/-]")[2];
		}
		else {
			day= DateUtils.getDay();
		}
		By excecutionDay = By.xpath("//td[contains(@class,'ui-day') and not(contains(@class,'ui-calendar-invalid')) and not(contains(@class,'ui-calendar-outFocus')) and normalize-space()='"+day+"']");
		applyExplicitWaitsUntilElementVisible(excecutionDay, 5);
		driver.findElement(excecutionDay).click();

		applyExplicitWaitsUntilElementClickable(od.payments_ScheduleAt, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.payments_ScheduleAt,
				externalData.getFieldData(TSID, "Scheduled", "Schedule At"));
		dropdown.selectByVisibleText(od.payments_HolidayAction,
				externalData.getFieldData(TSID, "Scheduled", "Holiday Action"));
	if(externalData.getFieldData(TSID, "Scheduled", "Schedule At").trim().equalsIgnoreCase("At specific time")) {
		String time = dateutil.getTimeAfterMins(10);
		od.payments_ScheduleTime.clear();
		od.payments_ScheduleTime.sendKeys(time);
	}
		od.payments_NextArrowButtonTransferSchedule.click();
		applyExplicitWaitsUntilElementClickable(od.payments_Instrument, Duration.ofSeconds(5));

		od.payments_Instrument.click();

		String paymentInstrumentdata = externalData.getFieldData(TSID, "Scheduled", "Instrument");
		By paymentInstrument = By.xpath("//div[contains(text(),'" + paymentInstrumentdata + "')]");
		driver.findElement(paymentInstrument).click();
		
		scroll.scrollInToView(od.schedule_IBAN);
		System.out.println(DealPage.sourceAccountNo);
		System.out.println(DealPage.toaccountNo);
		od.schedule_IBAN.sendKeys(toAccountNo);
		By account = By.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and normalize-space()='" + toAccountNo + "']");
		driver.findElement(account).click();
		
		scroll.scrollInToView(od.parties_Accounts_accountOrIban);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_accountOrIban, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_Accounts_accountOrIban,externalData.getFieldData(TSID, "Scheduled", "Select Account/IBAN"));
		
		scroll.scrollInToView(od.payments_Amount);
		od.payments_Amount.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Amount"));
		
		if(commonutils.isElementDisplayed(od.parties_Accounts_beneficiaryBankIfscCode,1)) {
			od.parties_Accounts_beneficiaryBankIfscCode.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary bank IFSC code"));
			}
		
		scroll.scrollInToView(od.parties_Accounts_beneficiaryName);
		od.parties_Accounts_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Name"));
		
		scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine1);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryAddressLine1, Duration.ofSeconds(5));
		od.parties_Accounts_beneficiaryAddressLine1.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Address Line 1"));
		
		od.payments_beneficiaryCountry.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Country Of Incorporation"));

		if(commonutils.isElementDisplayed(od.payments_beneficiaryBankBic,1)) {
		scroll.scrollInToView(od.payments_beneficiaryBankBic);
		od.payments_beneficiaryBankBic.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Bank Bic"));
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		if(commonutils.isElementDisplayed(od.payments_senderPop,1)) {
		scroll.scrollInToView(od.payments_senderPop);
		od.payments_senderPop.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Sender POP"));
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		scroll.scrollInToView(od.payments_beneficiaryCountryOfIncorporationDropdown);
		od.payments_beneficiaryCountryOfIncorporationDropdown.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Country Of Incorporation"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		od.payments_AddSubInstructionButton.click();
		od.payments_NextArrowButtonTransferSubInstruction.click();

		if (((externalData.getFieldData(TSID, "Scheduled", "Retry-Enable Auto Retry")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Retry-Enable Auto Retry")).equalsIgnoreCase("Yes"))) {
			od.payments_RetrySlider.click();
		}
		od.payments_NextArrowButtonRetryMechanism.click();

		if (((externalData.getFieldData(TSID, "Scheduled", "Notification-Notification Alerts")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Notification-Notification Alerts"))
						.equalsIgnoreCase("Yes"))) {
			od.payments_NotificationAlertSlider.click();
		}
    }


	public void createPaymentSurplus(String TSID) throws Exception, IOException {
		od.paymentSurplus_SurplusButton.click();
		dropdown.selectByVisibleText(od.paymentSurplus_Purpose,externalData.getFieldData(TSID, "Payment-Surplus", "Purpose"));
		od.paymentSurplus_Remarks.sendKeys(externalData.getFieldData(TSID, "Payment-Surplus", "Remarks"));;
		od.paymentSurplus_NextButton.click();
			
		od.paymentSurplus_Instrument.click();
		String paymentInstrumentdata = externalData.getFieldData(TSID, "Payment-Surplus", "Instrument");
		System.out.println(paymentInstrumentdata);
		By paymentInstrument = By.xpath("(//div[contains(text(),'" + paymentInstrumentdata + "')])[2]");
		applyExplicitWaitsUntilElementVisible(paymentInstrument, 10);
		driver.findElement(paymentInstrument).click();
		
		scroll.scrollInToView(od.schedule_IBAN);
		System.out.println(DealPage.sourceAccountNo);
		System.out.println(DealPage.toaccountNo);
		od.schedule_IBAN.sendKeys(toAccountNo);
		By account = By.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and normalize-space()='" + toAccountNo + "']");
		driver.findElement(account).click();
		
		scroll.scrollInToView(od.parties_Accounts_accountOrIban);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_accountOrIban, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_Accounts_accountOrIban,externalData.getFieldData(TSID, "Payment-Surplus", "Select Account/IBAN"));
		
		scroll.scrollInToView(od.payments_Amount);
		od.payments_Amount.sendKeys(externalData.getFieldData(TSID, "Payment-Surplus", "Amount"));
		
		if(commonutils.isElementDisplayed(od.parties_Accounts_beneficiaryBankIfscCode,1)) {
			od.parties_Accounts_beneficiaryBankIfscCode.sendKeys(externalData.getFieldData(TSID, "Payment-Surplus", "Beneficiary bank IFSC code"));
			}
		
		scroll.scrollInToView(od.parties_Accounts_beneficiaryName);
		od.parties_Accounts_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Payment-Surplus", "Beneficiary Name"));
		
		scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine1);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryAddressLine1, Duration.ofSeconds(5));
		od.parties_Accounts_beneficiaryAddressLine1.sendKeys(externalData.getFieldData(TSID, "Payment-Surplus", "Beneficiary Address Line 1"));
		
		od.payments_beneficiaryCountry.sendKeys(externalData.getFieldData(TSID, "Payment-Surplus", "Beneficiary Country Of Incorporation"));

		if(commonutils.isElementDisplayed(od.payments_beneficiaryBankBic,1)) {
		scroll.scrollInToView(od.payments_beneficiaryBankBic);
		od.payments_beneficiaryBankBic.sendKeys(externalData.getFieldData(TSID, "Payment-Surplus", "Beneficiary Bank Bic"));
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		if(commonutils.isElementDisplayed(od.payments_senderPop,1)) {
		scroll.scrollInToView(od.payments_senderPop);
		od.payments_senderPop.sendKeys(externalData.getFieldData(TSID, "Payment-Surplus", "Sender POP"));
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		scroll.scrollInToView(od.payments_beneficiaryCountryOfIncorporationDropdown);
		od.payments_beneficiaryCountryOfIncorporationDropdown.sendKeys(externalData.getFieldData(TSID, "Payment-Surplus", "Beneficiary Country Of Incorporation"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		od.paymentsSurplus_AddSubInstructionButton.click();
		
		applyExplicitWaitsUntilElementClickable(od.payments_DealsummaryIcon, Duration.ofSeconds(5));
		od.payments_DealsummaryIcon.click();
		applyExplicitWaitsUntilElementClickable(od.deals_SummaryRefId, Duration.ofSeconds(5));
		dealId = od.deals_SummaryRefId.getText();
		scroll.scrollInToView(od.payments_DealSubmitButton);
		applyExplicitWaitsUntilElementClickable(od.payments_DealSubmitButton, Duration.ofSeconds(10));
		od.payments_DealSubmitButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealYesButton, Duration.ofSeconds(10));
		od.payments_DealYesButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealOkButton, Duration.ofSeconds(10));
		od.payments_DealOkButton.click();
		
	}
	
	public void createPayments(String TSID, String sourceAccountno, String toaccountNo) throws Exception {
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
		}
		if (((externalData.getFieldData(TSID, "Scheduled", "Partial Payment")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Partial Payment")).equalsIgnoreCase("Yes"))) {
			od.payments_PartialpaymentSlider.click();
		}

		od.payments_NextArrowButtonTransferBasic.click();

		if (((externalData.getFieldData(TSID, "Scheduled", "Schedule - Repeating")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Schedule - Repeating")).equalsIgnoreCase("Yes"))) {
			od.payments_PartialpaymentSlider.click();
		}

		if (((externalData.getFieldData(TSID, "Scheduled", "Sweep in")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Sweep in")).equalsIgnoreCase("Yes"))) {
			od.payments_SweepInSlider.click();
		}
		if (commonutils.isElementDisplayed(od.payments_SweepinNextButton, 1)) {
			od.payments_SweepinNextButton.click();
		}
		applyExplicitWaitsUntilElementClickable(od.payments_ExecutionDate, Duration.ofSeconds(5));
		od.payments_ExecutionDate.click();

		if (InstructionType.equalsIgnoreCase("Payment - Retention")) {
			day = dateutil.getDay();
			int day_int = Integer.parseInt(day) + 2;
			day = Integer.toString(day_int);
		}

		if (InstructionType.equalsIgnoreCase("Payment")) {
			day = dateutil.getDay();
		}

		By excecutionDay = By.xpath(
				"//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"
						+ day + "']");
		applyExplicitWaitsUntilElementVisible(excecutionDay, 5);
		driver.findElement(excecutionDay).click();

		applyExplicitWaitsUntilElementClickable(od.payments_ScheduleAt, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.payments_ScheduleAt,
				externalData.getFieldData(TSID, "Scheduled", "Schedule At"));
		dropdown.selectByVisibleText(od.payments_HolidayAction,
				externalData.getFieldData(TSID, "Scheduled", "Holiday Action"));
		od.payments_NextArrowButtonTransferSchedule.click();
		applyExplicitWaitsUntilElementClickable(od.payments_Instrument, Duration.ofSeconds(5));

		od.payments_Instrument.click();

		String paymentInstrumentdata = externalData.getFieldData(TSID, "Scheduled", "Instrument");
		By paymentInstrument = By.xpath("//div[contains(text(),'" + paymentInstrumentdata + "')]");
		driver.findElement(paymentInstrument).click();
		if (commonutils.isElementDisplayed(od.payments_ToAccountDropdown, 1)) {

			if ((externalData.getFieldData(TSID, "Scheduled", "to").equalsIgnoreCase("toaccountNo"))) {
				applyExplicitWaitsUntilElementClickable(od.payments_ToAccountDropdown, Duration.ofSeconds(5));
				scroll.scrollInToView(od.payments_ToAccountDropdown);
				dropdown.selectByVisibleText(od.payments_ToAccountDropdown,toaccountNo);
	
			}
		 else {
			applyExplicitWaitsUntilElementClickable(od.payments_ToAccountDropdown, Duration.ofSeconds(5));
			scroll.scrollInToView(od.payments_ToAccountDropdown);
			dropdown.selectByVisibleText(od.payments_ToAccountDropdown,(externalData.getFieldData(TSID, "Scheduled", "to")));
		}
		}

		if (commonutils.isElementDisplayed(od.Payment_Beneficiaryaccno, 1)) {
			
			if ((externalData.getFieldData(TSID, "Scheduled", "to").equalsIgnoreCase("toaccountNo"))) {
				applyExplicitWaitsUntilElementClickable(od.Payment_Beneficiaryaccno, Duration.ofSeconds(5));
				scroll.scrollInToView(od.Payment_Beneficiaryaccno);
				od.Payment_Beneficiaryaccno.sendKeys(toaccountNo);
				By accno = By.xpath("//div[contains(text(),'" + toaccountNo + "')]");
				driver.findElement(accno).click();
			} else {
				applyExplicitWaitsUntilElementClickable(od.Payment_Beneficiaryaccno, Duration.ofSeconds(5));
				scroll.scrollInToView(od.Payment_Beneficiaryaccno);
				od.Payment_Beneficiaryaccno.sendKeys(externalData.getFieldData(TSID, "Scheduled", "to"));
				By accno = By.xpath("//div[contains(text(),'" + externalData.getFieldData(TSID, "Scheduled", "to") + "')]");
				driver.findElement(accno).click();
			}

		}
		if (commonutils.isElementDisplayed(od.payments_beneficiaryBankBic, 1)) {
			scroll.scrollInToView(od.payments_beneficiaryBankBic);
			od.payments_beneficiaryBankBic
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Bank Bic"));
		}

		scroll.scrollInToView(od.payments_beneficiaryCountryOfIncorporationDropdown);
		od.payments_beneficiaryCountryOfIncorporationDropdown
				.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Country Of Incorporation"));

		if (commonutils.isElementDisplayed(od.payments_senderPop, 1)) {
			scroll.scrollInToView(od.payments_senderPop);
			od.payments_senderPop.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Sender POP"));
		}

		scroll.scrollInToView(od.parties_Accounts_beneficiaryName);
		od.parties_Accounts_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Name"));

		scroll.scrollInToView(od.parties_Accounts_accountOrIban);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_accountOrIban, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_Accounts_accountOrIban,
				externalData.getFieldData(TSID, "Scheduled", "Select Account/IBAN"));

		scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine1);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryAddressLine1, Duration.ofSeconds(5));

		od.parties_Accounts_beneficiaryAddressLine1
				.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Address Line 1"));

		scroll.scrollInToView(od.payments_Amount);
		od.payments_Amount.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Amount"));

		if (commonutils.isElementDisplayed(od.parties_Accounts_beneficiaryBankIfscCode, 1)) {
			od.parties_Accounts_beneficiaryBankIfscCode
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary bank IFSC code"));
		}

		od.payments_AddSubInstructionButton.click();
		od.payments_NextArrowButtonTransferSubInstruction.click();

		if (((externalData.getFieldData(TSID, "Scheduled", "Retry-Enable Auto Retry")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Retry-Enable Auto Retry")).equalsIgnoreCase("Yes"))) {
			od.payments_RetrySlider.click();
		}
		od.payments_NextArrowButtonRetryMechanism.click();

		if (((externalData.getFieldData(TSID, "Scheduled", "Notification-Notification Alerts")).equalsIgnoreCase("Y")
				|| (externalData.getFieldData(TSID, "Scheduled", "Notification-Notification Alerts"))
						.equalsIgnoreCase("Yes"))) {
			od.payments_NotificationAlertSlider.click();
		}

	}
}
