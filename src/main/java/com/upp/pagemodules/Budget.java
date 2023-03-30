package com.upp.pagemodules;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.By;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.ScrollTypes;

public class Budget extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int waitingTime = 5;
	public static DateUtils dateutil;
	public static ScrollTypes scroll;
	public static String productName;
	public static CommonUtils commonutils;

	public Budget() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		commonutils = new CommonUtils(driver);
	}

	public void createBudget(String TSID, String sourceAccountNo, String toAccountNo) throws Exception, IOException {
		od.budget_BudgetIcon.click();
		od.budget_CreateBudget.click();
		od.budget_AddBudgetName.sendKeys(externalData.getFieldData(TSID, "Budget", "BudgetName"));
		od.budget_BudgetSourceAccount.sendKeys(sourceAccountNo);
		By sourceAccountNoDropDown = By
				.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and contains(normalize-space(),'"
						+ sourceAccountNo + "')]");
		applyExplicitWaitsUntilElementVisible(sourceAccountNoDropDown, 10);
		driver.findElement(sourceAccountNoDropDown).click();
		od.budget_AddBudget.click();
		od.budget_budgetDetailsAddBudget.click();
		od.budget_Purpose.sendKeys(externalData.getFieldData(TSID, "Budget", "Purpose"));
		Thread.sleep(1000);
		System.out.println(toAccountNo);
		od.budget_budgetDestination.sendKeys(toAccountNo);

		By destination = By.xpath("//div[@id='grid-generic-destinationSearch-panelClick-v1']//li[contains(text(),'"
				+ toAccountNo + "')]");
		applyExplicitWaitsUntilElementVisible(destination, 10);
		driver.findElement(destination).click();
		dropdown.selectByVisibleText(od.budget_Interval, externalData.getFieldData(TSID, "Budget", "Interval"));
		dropdown.selectByIndex(od.budget_Duration, 2);
		od.budget_allocatedAmount.sendKeys(externalData.getFieldData(TSID, "Budget", "Allocated Budget Amount"));
		od.budget_AddButton.click();
	}

	public String createBudget_Payments(String TSID, String sourceAccountno, String toAccountNo) throws Exception {

		applyExplicitWaitsUntilElementClickable(od.payments_ScheduledInstructionIcon, Duration.ofSeconds(10));
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
		od.payments_SweepinNextButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_ExecutionDate, Duration.ofSeconds(5));
		od.payments_ExecutionDate.click();
		String day = DateUtils.getDay();
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
		String time = dateutil.getTimeAfterMins(5);
		od.payments_ScheduleTime.clear();
		od.payments_ScheduleTime.sendKeys(time);
		od.payments_NextArrowButtonTransferSchedule.click();
		applyExplicitWaitsUntilElementClickable(od.payments_Instrument, Duration.ofSeconds(20));
		od.payments_Instrument.click();
		String paymentInstrumentdata = externalData.getFieldData(TSID, "Scheduled", "Instrument");
		By paymentInstrument = By.xpath("//div[contains(text(),'" + paymentInstrumentdata + "')]");
		driver.findElement(paymentInstrument).click();

		String budget = externalData.getFieldData(TSID, "Scheduled", "Budget Purpose");
		od.payments_budgetPurpose.sendKeys(budget);

		By budgetPurpose = By.xpath("//div[contains(@class,'ng-star-inserted') and contains(text(),'" + budget + "')]");
		applyExplicitWaitsUntilElementVisible(budgetPurpose, 10);
		driver.findElement(budgetPurpose).click();

		driver.findElement(
				By.xpath("//div[contains(@class,'ui-autocomplete-list-item-div') and normalize-space()='Phone Bill']"));
		scroll.scrollInToView(od.payments_ToAccountInputBox);
		od.payments_ToAccountInputBox.sendKeys(toAccountNo);
		By acoount = By.xpath(
				"//div[contains(@class,'ui-autocomplete-list-item-div') and normalize-space()='" + toAccountNo + "']");
		driver.findElement(acoount).click();
		scroll.scrollInToView(od.parties_Accounts_accountOrIban);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_accountOrIban, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_Accounts_accountOrIban,
				externalData.getFieldData(TSID, "Scheduled", "Select Account/IBAN"));

		scroll.scrollInToView(od.payments_Amount);
		od.payments_Amount.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Amount"));

		if (commonutils.isElementDisplayed(od.parties_Accounts_beneficiaryBankIfscCode, 1)) {
			od.parties_Accounts_beneficiaryBankIfscCode
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary bank IFSC code"));
		}

		scroll.scrollInToView(od.parties_Accounts_beneficiaryName);
		od.parties_Accounts_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Name"));
		scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine1);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryAddressLine1, Duration.ofSeconds(5));
		od.parties_Accounts_beneficiaryAddressLine1
				.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Address Line 1"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		scroll.scrollInToView(od.payments_beneficiaryCountryOfIncorporationDropdown);
		od.payments_beneficiaryCountryOfIncorporationDropdown
				.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Country Of Incorporation"));

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

		applyExplicitWaitsUntilElementClickable(od.payments_DealsummaryIcon, Duration.ofSeconds(5));
		od.payments_DealsummaryIcon.click();
		applyExplicitWaitsUntilElementClickable(od.deals_SummaryRefId, Duration.ofSeconds(5));
		String dealRefId = od.deals_SummaryRefId.getText();
		String url = driver.getCurrentUrl();
		String dealID = url.split("[/]")[url.split("/").length - 1];

		HashMap map = new HashMap<>();
		map.put("Origin TC ID", TSID);
		map.put("Deal ID", dealID);
		map.put("Deal Ref ID", dealRefId);
//		 map.put("TC Data", map);
		String json = new ObjectMapper().writeValueAsString(map);

		scroll.scrollInToView(od.payments_DealSubmitButton);
		applyExplicitWaitsUntilElementClickable(od.payments_DealSubmitButton, Duration.ofSeconds(10));
		od.payments_DealSubmitButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealYesButton, Duration.ofSeconds(10));
		od.payments_DealYesButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealOkButton, Duration.ofSeconds(10));
		od.payments_DealOkButton.click();

		return dealRefId;

	}

}
