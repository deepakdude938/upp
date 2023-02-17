package com.upp.pagemodules;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;

import freemarker.template.utility.DateUtil;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DashBoard_Module extends BaseClass {

	public static Object_Deal od;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int rowNum;
	public static OdpApi odpAccount;
	public static AccountDetails accDetails;
	DateUtils dateTime = new DateUtils();
	public static JavascriptClick jsClick;
	public static int waitingTime = 5;
	public DashBoard_Module() {

		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);

	}

	public void loginToUPP(String userName, String Password) {

		od.username.sendKeys(userName);
		od.password.sendKeys(Password);
		od.loginIn.click();

	}

	public void createNewDeal(String TSID) throws Exception {

		od.deal_SideMenuIcon.click();
		od.newDealButton.click();
		od.newDeal.sendKeys(externalData.getFieldData(TSID, "Basic Details", "Deal Name"));
		dropdown.selectByVisibleText(od.businessSegmentDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Business Segment"));
		dropdown.selectByVisibleText(od.countryIndiaDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Country"));
		String input = externalData.getFieldData(TSID, "Basic Details", "Transactions to non-registered beneficiaries");
		if ((input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes")) && !od.beneficiariesCheckBox.isSelected()) {
			od.beneficiariesCheckBox.click();
		}

		String ProcessingUnits = externalData.getFieldData(TSID, "Basic Details", "Processing Units");

		if (!(ProcessingUnits.equalsIgnoreCase("Select All"))) {
			od.deals_ProcessingUnits.click();
			od.deals_selectAll.click();
			By ProcessingUnit = By.xpath(
					"//div[contains(@class,'ng-tns-c92-7 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"
							+ ProcessingUnits + "']");
			driver.findElement(ProcessingUnit).click();
		}

		input = externalData.getFieldData(TSID, "Basic Details", "Transaction Categories");
		od.transactionCategory.click();
		By transaction_Category_Option = By.xpath(
				"//div[contains(@class,'ng-tns-c92-5 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"
						+ input + "']");
		applyExplicitWaitsUntilElementVisible(transaction_Category_Option, 10);
		driver.findElement(transaction_Category_Option).click();
		od.saveButton.click();

		input = externalData.getFieldData(TSID, "Basic Details", "Party Responsibilities");
		od.partyResponsibility.click();
		By party_Responsibility_Option = By.xpath(
				"//div[contains(@class,'ng-tns-c92-6 ui-autocomplete-list-item-option ng-star-inserted') and normalize-space()='"
						+ input + "']");
		applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 10);
		driver.findElement(party_Responsibility_Option).click();
		od.saveButton.click();
		od.nextBtn.click();

	}

	public String createNewAccount(String TSID) throws Exception {

		odpAccount.createAccount(TSID);
		accDetails = odpAccount.popelmnt(OdpApi.stack1);
		System.out.println("the account no is" + accDetails.getAccno());
		String accountNo = accDetails.getAccno();
		dropdown.selectByVisibleText(od.country, externalData.getFieldData(TSID, "Accounts", "Country"));
		dropdown.selectByVisibleText(od.currency, externalData.getFieldData(TSID, "Accounts", "Currency"));
		String physicalYesOrNo = externalData.getFieldData(TSID, "Accounts", "Physical");
		if (physicalYesOrNo.equalsIgnoreCase("Yes")) {
			dropdown.selectByVisibleText(od.physical, "Physical");
		} else {
			dropdown.selectByVisibleText(od.physical, " Virtual ");
		}
		od.searchTextBox.sendKeys(accountNo);
		od.searchButton.click();
		applyExplicitWaitsUntilElementClickable(od.accounts_addAccount, Duration.ofSeconds(5));
		od.accounts_addAccount.click();
		Thread.sleep(2000);

		return accountNo;
	}

	public String createNewLinkedAccount(String TSID, String sourceAccountno, String toaccountNo) throws Exception {

		od.linkedInstruction_linkedBtn.click();
		od.linkedInstruction_addAccountBtn.click();

		String pay = externalData.getFieldData(TSID, "Linked", "Select Instruction Type");
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
		dropdown.selectByVisibleText(od.linkedInstruction_Balanceddl, " Available Balance ");
		od.linkedInstruction_NextBtn.click();
		od.linkedInstruction_LinkedConfigNextBtn.click();
		od.linkedInstruction_Executiondate.click();
		od.linkedInstruction_Todaydate.click();

		dropdown.selectByVisibleText(od.linkedInstruction_schedule,
				externalData.getFieldData(TSID, "Linked", "Schedule At"));
		dropdown.selectByVisibleText(od.linkedInstruction_HolidayAction,
				externalData.getFieldData(TSID, "Linked", "Holiday Action"));
//		String timem = dateTime.getTimeAfterMins(waitingTime);
//		od.linkedInstruction_TimePicker.clear();
//		od.linkedInstruction_TimePicker.sendKeys(timem);
		od.linkedInstruction_ScheduleNextBtn.click();
		od.linkedInstruction_Instrumentddl.sendKeys(externalData.getFieldData(TSID, "Linked", "Instrument"));
		String inst = externalData.getFieldData(TSID, "Linked", "Instrument");
		By instrument = By
				.xpath("//li[@id='lbl-generic-autocomplete-listItemOption1']//div[contains(text(),'" + inst + "')]");
		driver.findElement(instrument).click();
		od.linkedInstruction_Amount.sendKeys(externalData.getFieldData(TSID, "Linked", "Amount"));
		od.linkedInstruction_ToAccountddl.sendKeys(toaccountNo);
		By toaccountselect = By.xpath("//div[contains(text(),'" + toaccountNo + "')]");
		driver.findElement(toaccountselect).click();
		dropdown.selectByVisibleText(od.linkedInstruction_Incorporationddl,
				externalData.getFieldData(TSID, "Linked", "Beneficiary Country Of Incorporation"));
		od.linkedInstruction_BankBic.sendKeys(externalData.getFieldData(TSID, "Linked", "Beneficiary Bank Bic"));
		od.linkedInstruction_AddBtn.click();
		od.linkedInstruction_Summary.click();
		String dealid = od.deals_SummaryRefId.getText();
		od.linkedInstruction_Submit.click();
		od.linkedInstruction_YesBtn.click();
		od.linkedInstruction_OkBtn.click();

		System.out.println("Deal id = " + dealid);
//		approveDealFromDealChecker(dealid);
//		linkedDealWithTransaction(dealid);
		return dealid;

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
		applyExplicitWaitsUntilElementClickable(od.payments_DealYesButton, Duration.ofSeconds(10));
		od.payments_DealYesButton.click();
		applyExplicitWaitsUntilElementClickable(od.payments_DealOkButton, Duration.ofSeconds(10));
		od.payments_DealOkButton.click();
		TimeUnit.SECONDS.sleep(5);

	}

	public void logout() throws Exception {
		try {
			applyExplicitWaitsUntilElementClickable(od.logout, Duration.ofSeconds(30));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		od.logout.click();
		TimeUnit.SECONDS.sleep(7);
	}

	public void txnMaker_SubmitDeal(String dealid) throws Exception {
		System.out.println("deal id in maker = " + dealid);
		TimeUnit.SECONDS.sleep(waitingTime);
		od.TxnMaker_Transaction.click();
		od.TxnMaker_TrasactionMaker.click();
		
		od.TxnMaker_searchDealId.sendKeys(dealid);
		
		od.TxnMaker_txnCheckbox.click();
		od.TxnMaker_submitBtn.click();
		od.TxnMaker_okBtn.click();

	}

	public void txnChecker_SubmitDeal(String dealId) {
		// TODO Auto-generated method stub
		od.TxnChecker_Transaction.click();
		od.TxnChecker_TrasactionChecker.click();
		od.TxnChecker_searchDealId.sendKeys(dealId);
		od.TxnChecker_comment.click();
		od.TxnChecker_note.sendKeys("Ok");
		od.TxnChecker_ok.click();
		od.TxnChecker_txnCheckbox.click();
		od.TxnChecker_submitBtn.click();
		od.TxnChecker_okBtn.click();
	}
}
