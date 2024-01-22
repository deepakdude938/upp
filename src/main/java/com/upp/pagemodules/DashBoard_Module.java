package com.upp.pagemodules;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import com.upp.utils.CommonUtils;

import callbackInterfaces.ICallback;
import freemarker.template.utility.DateUtil;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DashBoard_Module extends BaseClass {

	public static Object_NewDeal od;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int rowNum;
	public static OdpApi odpAccount;
	public static AccountDetails accDetails;
	DateUtils dateTime = new DateUtils();
	public static JavascriptClick jsClick;
	public static int waitingTime = 5;
	public static DateUtils dateutil;
	public static ScrollTypes scroll;
	public static String productName;
	public static CommonUtils commonutils;
	public static String day = "";

	public DashBoard_Module() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		commonutils = new CommonUtils(driver);

	}


	public String createNewLinkedAccount(String TSID, String sourceAccountno, String toaccountNo, ICallback icallback)
			throws Exception {
		String dealid;
		od.linkedInstruction_linkedBtn.click();
		od.linkedInstruction_addAccountBtn.click();
		System.out.println(TSID);
		String pay = externalData.getFieldData(TSID, "Linked", "Select Instruction Type");
		System.out.println(pay);
		By payment = By.xpath("//div[contains(text(),'" + pay + "')]");
		driver.findElement(payment).click();
		od.linkedInstruction_proccedbtn.click();
		od.linkedInstruction_basicNameTxt.clear();
		od.linkedInstruction_basicNameTxt.sendKeys(externalData.getFieldData(TSID, "Linked", "Basic Details Name"));
		od.linkedInstruction_purposeddl.click();
		dropdown.selectByVisibleText(od.linkedInstruction_purposeddl,
				externalData.getFieldData(TSID, "Linked", "Purpose"));
		od.linkedInstruction_SourceAccounttxt.click();
		Thread.sleep(2000);
		od.linkedInstruction_Accountvalue.sendKeys(sourceAccountno);
		By sourceaccountselect = By.xpath("//div[contains(text(),'" + sourceAccountno + "')]");
		driver.findElement(sourceaccountselect).click();
		dropdown.selectByVisibleText(od.linkedInstruction_Balanceddl,
				externalData.getFieldData(TSID, "Linked", "Balance Consideration"));
		od.linkedInstruction_NextBtn.click();
		od.linkedInstruction_LinkedConfigNextBtn.click();
	//	od.linkedInstruction_sweepNext.click();
		od.linkedInstruction_Executiondate.click();
		od.linkedInstruction_Todaydate.click();

		dropdown.selectByVisibleText(od.linkedInstruction_schedule,
				externalData.getFieldData(TSID, "Linked", "Schedule At"));
		dropdown.selectByVisibleText(od.linkedInstruction_HolidayAction,
				externalData.getFieldData(TSID, "Linked", "Holiday Action"));
		String timem = dateTime.getTimeAfterMins(waitingTime);
		System.out.println("time = "+timem);
		od.linkedInstruction_TimePicker.clear();
		od.linkedInstruction_TimePicker.sendKeys(timem);
		od.linkedInstruction_ScheduleNextBtn.click();
		String instrumentName = externalData.getFieldData(TSID, "Linked", "Instrument");
		icallback.handleCallback("Instrument_NAME", instrumentName);
		Thread.sleep(3000);
		od.linkedInstruction_AddBtn.click();
		Thread.sleep(3000);
		od.linkedInstruction_Summary.click();
		dealid = od.deals_SummaryRefId.getText();
		od.linkedInstruction_Submit.click();
		od.linkedInstruction_YesBtn.click();
		od.linkedInstruction_OkBtn.click();
		System.out.println("Deal id = " + dealid);
		return dealid;

	}

	public void createNewLinkedAccount1(String TSID, String sourceAccountno, String toaccountNo, ICallback icallback)
			throws Exception {
		
		od.linkedInstruction_linkedBtn.click();
		od.linkedInstruction_addAccountBtn.click();
		System.out.println(TSID);
		String pay = externalData.getFieldData(TSID, "Linked", "Select Instruction Type");
		System.out.println(pay);
		By payment = By.xpath("//div[contains(text(),'" + pay + "')]");
		driver.findElement(payment).click();
		od.linkedInstruction_proccedbtn.click();
		od.linkedInstruction_basicNameTxt.clear();
		od.linkedInstruction_basicNameTxt.sendKeys(externalData.getFieldData(TSID, "Linked", "Basic Details Name"));
		od.linkedInstruction_purposeddl.click();
		dropdown.selectByVisibleText(od.linkedInstruction_purposeddl,
				externalData.getFieldData(TSID, "Linked", "Purpose"));
		od.linkedInstruction_SourceAccounttxt.click();
		od.linkedInstruction_Accountvalue.sendKeys(sourceAccountno);
		By sourceaccountselect = By.xpath("//div[contains(text(),'" + sourceAccountno + "')]");
		driver.findElement(sourceaccountselect).click();
		dropdown.selectByVisibleText(od.linkedInstruction_Balanceddl,
				externalData.getFieldData(TSID, "Linked", "Balance Consideration"));
		od.linkedInstruction_NextBtn.click();
		od.linkedInstruction_LinkedConfigNextBtn.click();
	//	od.linkedInstruction_sweepNext.click();
		od.linkedInstruction_Executiondate.click();
		od.linkedInstruction_Todaydate.click();

		dropdown.selectByVisibleText(od.linkedInstruction_schedule,
				externalData.getFieldData(TSID, "Linked", "Schedule At"));
		dropdown.selectByVisibleText(od.linkedInstruction_HolidayAction,
				externalData.getFieldData(TSID, "Linked", "Holiday Action"));
		String timem = dateTime.getTimeAfterMins(waitingTime);
		od.linkedInstruction_TimePicker.clear();
		od.linkedInstruction_TimePicker.sendKeys(timem);
		if ((TSID.equalsIgnoreCase("TS113"))) {

			dropdown.selectByVisibleText(od.payments_TimeZone, "Asia/Calcutta (GMT+05:30)");
			String time = dateutil.getTimeAfterMins(3);

			od.payments_ScheduleTime.clear();
			od.payments_ScheduleTime.sendKeys(time);
			Thread.sleep(3000);

		}
		od.linkedInstruction_ScheduleNextBtn.click();
		String instrumentName = externalData.getFieldData(TSID, "Linked", "Instrument");
		icallback.handleCallback("Instrument_NAME", instrumentName);
		Thread.sleep(3000);
		od.linkedInstruction_AddBtn.click();
		Thread.sleep(3000);
	

	}
	public void approveDealFromDealChecker(String dealId) throws Exception {
		System.out.println("Approval Screen");
		applyExplicitWaitsUntilElementClickable(od.dealChecker_Button, Duration.ofSeconds(20));
		TimeUnit.SECONDS.sleep(5);
		od.dealChecker_Button.click();
		TimeUnit.SECONDS.sleep(5);
		dropdown.selectByVisibleText(od.dealChecker_searchSelect, "Deal Id");
		od.dealChecker_searchBar.sendKeys(dealId);
		System.out.println("Search Approval Screen");
		applyExplicitWaitsUntilElementClickable(od.dealChecker_searchButton, Duration.ofSeconds(5));
		od.dealChecker_searchButton.click();
		applyExplicitWaitsUntilElementClickable(od.dealChecker_showMenu, Duration.ofSeconds(5));
		od.dealChecker_showMenu.click();
		od.dealChecker_Open.click();
		od.dealChecker_addComments.click();
		od.dealChecker_addNote.sendKeys("Ok approved");
		jsClick.click(od.dealChecker_okCommentbutton);
		jsClick.click(od.dealChecker_approveAllRadioButton);
		od.dealChecker_ApproveButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealYesButton, Duration.ofSeconds(20));
		od.payments_DealYesButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealOkButton, Duration.ofSeconds(20));
		od.payments_DealOkButton.click();
		TimeUnit.SECONDS.sleep(5);

	}

	public void logout() throws Exception {
		applyExplicitWaitsUntilElementClickable(od.logout, Duration.ofSeconds(40));
		jsClick.click(od.logout);
	}

	public String submitDeal() throws Exception {
		applyExplicitWaitsUntilElementClickable(od.payments_DealsummaryIcon, Duration.ofSeconds(15));
		try {
			od.payments_DealsummaryIcon.click();
		}
		catch(Exception e) {
			handleElementClickException(od.payments_DealsummaryIcon);
		}
		applyExplicitWaitsUntilElementClickable(od.deals_SummaryRefId, Duration.ofSeconds(50));
		String dealId = od.deals_SummaryRefId.getText();
		Thread.sleep(1000);
		scroll.scrollInToView(od.payments_DealSubmitButton);
		applyExplicitWaitsUntilElementClickable(od.payments_DealSubmitButton, Duration.ofSeconds(5));
		Thread.sleep(3000);
		od.payments_DealSubmitButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealYesButton, Duration.ofSeconds(10));
		od.payments_DealYesButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealOkButton, Duration.ofSeconds(10));
		od.payments_DealOkButton.click();
		System.out.println(dealId);
		return dealId;
	}
	
	public void logOutOld() throws Exception {
		applyExplicitWaitsUntilElementClickable(od.logOutIcon, Duration.ofSeconds(40));
		jsClick.click(od.logOutIcon);
	}

	public void approveDealFromDealChecker_Old(String dealId) throws Exception {

		System.out.println("The deal id is " + dealId);

		applyExplicitWaitsUntilElementClickable(od.deal_SideMenuIcon, Duration.ofSeconds(15));
		try {
		od.deal_SideMenuIcon.click();
		}
		catch(ElementClickInterceptedException e) {
			handleElementClickException(od.deal_SideMenuIcon);
		}
		Thread.sleep(4000);
		jsClick.click(od.dealChecker_Button1);
		applyExplicitWaitsUntilElementClickable(od.dealChecker_searchSelect, Duration.ofSeconds(25));
		dropdown.selectByVisibleText(od.dealChecker_searchSelect, "Deal Id");
		applyExplicitWaitsUntilElementClickable(od.dealChecker_showMenu, Duration.ofSeconds(40));
		od.dealChecker_searchBar.sendKeys(dealId);
		applyExplicitWaitsUntilElementClickable(od.dealChecker_searchButton, Duration.ofSeconds(10));
		od.dealChecker_searchButton.click();
		Thread.sleep(5000);
		od.dealChecker_searchButton.click();
		Thread.sleep(9000);
		od.dealChecker_searchButton.click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(od.dealChecker_showMenu, Duration.ofSeconds(20));
		od.dealChecker_showMenu.click();
		applyExplicitWaitsUntilElementClickable(od.dealChecker_Open, Duration.ofSeconds(25));
		od.dealChecker_Open.click();
		Thread.sleep(3000);
		applyExplicitWaitsUntilElementClickable(od.dealChecker_addComments, Duration.ofSeconds(10));
		jsClick.click(od.dealChecker_addComments);
		od.dealChecker_addNote.sendKeys("Ok approved");
		od.dealChecker_okCommentbutton.click();
		jsClick.click(od.dealChecker_approveAllRadioButton);
		Thread.sleep(3000);
		od.dealChecker_ApproveButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealYesButton, Duration.ofSeconds(40));
		od.payments_DealYesButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealOkButton, Duration.ofSeconds(120));
		od.payments_DealOkButton.click();
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
		scroll.scrollInToView(od.payments_ToAccountInputBox);
		od.payments_ToAccountInputBox.sendKeys(toAccountNo);

		Thread.sleep(1000);
		By toAccount = By.xpath("//div[contains(@class,'ng-star-inserted') and contains(text(),'" + toAccountNo
				+ "')]/parent::div/parent::li");
		applyExplicitWaitsUntilElementVisible(toAccount, 10);
		driver.findElement(toAccount).click();
		;
		scroll.scrollInToView(od.payments_beneficiaryBankBic);
		od.payments_beneficiaryBankBic.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Bank Bic"));
		od.payments_beneficiaryCountryOfIncorporationDropdown
				.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Country Of Incorporation"));
		od.payments_Amount.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Amount"));
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
		String dealId = od.deals_SummaryRefId.getText();
		scroll.scrollInToView(od.payments_DealSubmitButton);
		applyExplicitWaitsUntilElementClickable(od.payments_DealSubmitButton, Duration.ofSeconds(10));
		od.payments_DealSubmitButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealYesButton, Duration.ofSeconds(10));
		od.payments_DealYesButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealOkButton, Duration.ofSeconds(10));
		od.payments_DealOkButton.click();

		return dealId;
	}

	public String twoEcommerceParties(String TSID, String Party_id, ICallback icallback) throws Exception {
//		String deal;
		DealPartiesCreator creator = new DealPartiesCreator();
		creator.createParties(TSID, icallback);
		Thread.sleep(3000);
		od.accountBackbtn.click();
		creator.createParties(Party_id, icallback);
		Thread.sleep(4000);
		dealId = submitDeal();
		approveDealFromDealChecker_Old(dealId);
		return dealId;
	}


	public String send_Introductory_Mail() throws Exception {
		Thread.sleep(3000);
		applyExplicitWaitsUntilElementClickable(od.payments_DealsummaryIcon, Duration.ofSeconds(15));
		try {
			od.payments_DealsummaryIcon.click();
		}
		catch(Exception e) {
			handleElementClickException(od.payments_DealsummaryIcon);
		}
		applyExplicitWaitsUntilElementClickable(od.deals_SummaryRefId, Duration.ofSeconds(50));
		String dealId = od.deals_SummaryRefId.getText();
		System.out.println(dealId);
		Thread.sleep(1000);
		scroll.scrollInToView(od.payments_DealSubmitButton);
		applyExplicitWaitsUntilElementClickable(od.payments_DealSubmitButton, Duration.ofSeconds(5));
		Thread.sleep(3000);
		od.payments_DealSubmitButton.click();
		applyExplicitWaitsUntilElementClickable(od.deal_AddNote, Duration.ofSeconds(10));
		od.deal_AddNote.sendKeys("Ok");
		applyExplicitWaitsUntilElementClickable(od.deal_DealOkButton, Duration.ofSeconds(10));
		od.deal_DealOkButton.click();
		System.out.println(dealId);
		return dealId;
	
	}
}
