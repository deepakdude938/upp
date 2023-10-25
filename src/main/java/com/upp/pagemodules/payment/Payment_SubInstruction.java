package com.upp.pagemodules.payment;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

public class Payment_SubInstruction extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public String toAccountNo = "";
	public static DateUtils dateutil;
	public static String day = "";
	public static JavascriptClick js;

	public Payment_SubInstruction() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		dateutil = new DateUtils();
		js = new JavascriptClick(driver);
	}

	public void createPayments_Subinstruction(String TSID, String sourceAccountno, String toaccountNo)
			throws Exception {

		applyExplicitWaitsUntilElementClickable(od.payments_Instrument, Duration.ofSeconds(5));

		try {
			od.payments_Instrument.click();
		} catch (Exception e) {
			handleElementClickException(od.payments_Instrument);
		}
		Thread.sleep(500);
		String paymentInstrumentdata = externalData.getFieldData(TSID, "Scheduled", "Instrument");
		By paymentInstrument = By.xpath("//div[contains(text(),'" + paymentInstrumentdata + "')]");
		driver.findElement(paymentInstrument).click();

		if (TSID.equalsIgnoreCase("TS72")) {
			String budget = externalData.getFieldData(TSID, "Scheduled", "Budget Purpose");
			System.out.println(budget);
			od.payments_budgetPurpose.sendKeys(budget);
			By budgetPurpose = By
					.xpath("//div[contains(@class,'ng-star-inserted') and contains(text(),'" + budget + "')]");
			applyExplicitWaitsUntilElementVisible(budgetPurpose, 10);
			driver.findElement(budgetPurpose).click();
		}

		if (commonutils.isElementDisplayed(od.payments_ToAccountDropdown, 2)) {

			if ((externalData.getFieldData(TSID, "Scheduled", "to").equalsIgnoreCase("toaccountNo"))) {
				applyExplicitWaitsUntilElementClickable(od.payments_ToAccountDropdown, Duration.ofSeconds(5));
				scroll.scrollInToView(od.payments_ToAccountDropdown);
				dropdown.selectByVisibleText(od.payments_ToAccountDropdown, toaccountNo);

			} else {
				applyExplicitWaitsUntilElementClickable(od.payments_ToAccountDropdown, Duration.ofSeconds(5));
				scroll.scrollInToView(od.payments_ToAccountDropdown);
				dropdown.selectByVisibleText(od.payments_ToAccountDropdown,
						(externalData.getFieldData(TSID, "Scheduled", "to")));
			}
		}

		if (commonutils.isElementDisplayed(od.Payment_Beneficiaryaccno, 1)) {
			if (!((TSID.equalsIgnoreCase("TS70")) || (TSID.equalsIgnoreCase("TS72")))) {

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
					By accno = By.xpath(
							"//div[contains(text(),'" + externalData.getFieldData(TSID, "Scheduled", "to") + "')]");
					driver.findElement(accno).click();
				}
			}
		}
		if (commonutils.isElementDisplayed(od.payments_beneficiaryBankBic, 1)) {
			scroll.scrollInToView(od.payments_beneficiaryBankBic);
			od.payments_beneficiaryBankBic
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Bank Bic"));
		}
		if (commonutils.isElementDisplayed(od.payments_beneficiaryCountryOfIncorporationDropdown, 1)) {
			scroll.scrollInToView(od.payments_beneficiaryCountryOfIncorporationDropdown);
			od.payments_beneficiaryCountryOfIncorporationDropdown
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Country Of Incorporation"));
		}

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
		applyExplicitWaitsUntilElementClickable(od.payments_Amount, Duration.ofSeconds(5));
		od.payments_Amount.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Amount"));

		if (commonutils.isElementDisplayed(od.parties_Accounts_beneficiaryBankIfscCode, 1)) {
			od.parties_Accounts_beneficiaryBankIfscCode
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary bank IFSC code"));
		}

		od.payments_AddSubInstructionButton.click();
		Thread.sleep(1500);
	}

	public void createPayments_Subinstruction_without_Party_Added(String TSID, String sourceAccountno,
			String toaccountNo) throws Exception {
		System.out.println(TSID);
		
		Thread.sleep(2000);
		
		if (TSID.equalsIgnoreCase("TS108")) {

			if (commonutils.isElementDisplayed(od.payments_Split_By_percentage_Text, 2)) {
				System.out.println("inside if condition of TS108 2");
				od.payments_Split_By_percentage_Slider.click();
				Thread.sleep(2500);
			}
		}

		applyExplicitWaitsUntilElementClickable(od.payments_Instrument, Duration.ofSeconds(5));
		try {
			od.payments_Instrument.click();
		} catch (Exception e) {
			handleElementClickException(od.payments_Instrument);
		}
		Thread.sleep(500);
		
		String paymentInstrumentdata = externalData.getFieldData(TSID, "Scheduled", "Instrument");
		By paymentInstrument = By.xpath("//div[contains(text(),'" + paymentInstrumentdata + "')]");
		driver.findElement(paymentInstrument).click();

		
		if (TSID.equalsIgnoreCase("TS110") || TSID.equalsIgnoreCase("TS113")) {
			applyExplicitWaitsUntilElementClickable(od.Payment_Beneficiaryaccno_with_budget, Duration.ofSeconds(5));
			od.Payment_Beneficiaryaccno_with_budget.sendKeys(externalData.getFieldData(TSID, "Scheduled", "to"));

			}
		
		if (TSID.startsWith("TS105") || TSID.startsWith("TS108")) {
			
			applyExplicitWaitsUntilElementClickable(od.Payment_Beneficiaryaccno_without_Budget_Purpose, Duration.ofSeconds(5));
			od.Payment_Beneficiaryaccno_without_Budget_Purpose.sendKeys(externalData.getFieldData(TSID, "Scheduled", "to"));

			}
		

		if (TSID.equalsIgnoreCase("TS72")) {
			String budget = externalData.getFieldData(TSID, "Scheduled", "Budget Purpose");
			System.out.println(budget);
			od.payments_budgetPurpose.sendKeys(budget);
			By budgetPurpose = By
					.xpath("//div[contains(@class,'ng-star-inserted') and contains(text(),'" + budget + "')]");
			applyExplicitWaitsUntilElementVisible(budgetPurpose, 10);
			driver.findElement(budgetPurpose).click();
		}

		if (commonutils.isElementDisplayed(od.payments_ToAccountDropdown, 2)) {

			if ((externalData.getFieldData(TSID, "Scheduled", "to").equalsIgnoreCase("toaccountNo"))) {
				applyExplicitWaitsUntilElementClickable(od.payments_ToAccountDropdown, Duration.ofSeconds(5));
				scroll.scrollInToView(od.payments_ToAccountDropdown);
				dropdown.selectByVisibleText(od.payments_ToAccountDropdown, toaccountNo);

			} else {
				applyExplicitWaitsUntilElementClickable(od.payments_ToAccountDropdown, Duration.ofSeconds(5));
				scroll.scrollInToView(od.payments_ToAccountDropdown);
				dropdown.selectByVisibleText(od.payments_ToAccountDropdown,
						(externalData.getFieldData(TSID, "Scheduled", "to")));
			}
		}

		if (commonutils.isElementDisplayed(od.Payment_Beneficiaryaccno, 1)) {
			if (!((TSID.equalsIgnoreCase("TS70")) || (TSID.equalsIgnoreCase("TS72")) || (TSID.equalsIgnoreCase("TS113")))) {

				if ((externalData.getFieldData(TSID, "Scheduled", "to").equalsIgnoreCase("toaccountNo"))) {
					applyExplicitWaitsUntilElementClickable(od.Payment_Beneficiaryaccno, Duration.ofSeconds(5));
					scroll.scrollInToView(od.Payment_Beneficiaryaccno);
					od.Payment_Beneficiaryaccno.sendKeys(toaccountNo);

				} else {
					applyExplicitWaitsUntilElementClickable(od.Payment_Beneficiaryaccno, Duration.ofSeconds(5));
					scroll.scrollInToView(od.Payment_Beneficiaryaccno);
					od.Payment_Beneficiaryaccno.sendKeys(externalData.getFieldData(TSID, "Scheduled", "to"));
					scroll.scrollInToView(od.Payment_debitRemittanceInformation);
					applyExplicitWaitsUntilElementClickable(od.Payment_debitRemittanceInformation, Duration.ofSeconds(5));
					od.Payment_debitRemittanceInformation.click();
					Thread.sleep(2500);

				}
			}
		}
		if (commonutils.isElementDisplayed(od.payments_beneficiaryBankBic, 1)) {
			scroll.scrollInToView(od.payments_beneficiaryBankBic);
			od.payments_beneficiaryBankBic
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Bank Bic"));
		}
		if (externalData.getFieldData(TSID, "Scheduled", "BeneficiaryCountry") != null) {
			if (commonutils.isElementDisplayed(od.Payment_beneficiaryCountry, 1)) {
				scroll.scrollInToView(od.Payment_beneficiaryCountry);
				od.Payment_beneficiaryCountry
						.sendKeys(externalData.getFieldData(TSID, "Scheduled", "BeneficiaryCountry"));
			}
		}
		if (commonutils.isElementDisplayed(od.payments_beneficiaryCountryOfIncorporationDropdown, 1)) {
			scroll.scrollInToView(od.payments_beneficiaryCountryOfIncorporationDropdown);
			od.payments_beneficiaryCountryOfIncorporationDropdown
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Country Of Incorporation"));
		}

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
		applyExplicitWaitsUntilElementClickable(od.payments_Amount, Duration.ofSeconds(5));
		od.payments_Amount.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Amount"));

		if (commonutils.isElementDisplayed(od.parties_Accounts_beneficiaryBankIfscCode, 1)) {
			od.parties_Accounts_beneficiaryBankIfscCode
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary bank IFSC code"));
		}

		od.payments_AddSubInstructionButton.click();
		Thread.sleep(1500);
	}

	public void createPayments_Subinstruction_For_PurposeBudget(String TSID, String sourceAccountno, String toaccountNo)
			throws Exception {

		applyExplicitWaitsUntilElementClickable(od.payments_Instrument1, Duration.ofSeconds(5));

		try {
			od.payments_Instrument.click();
		} catch (Exception e) {
			handleElementClickException(od.payments_Instrument);
		}
		Thread.sleep(5000);
		String paymentInstrumentdata = externalData.getFieldData(TSID, "Scheduled", "Instrument");
		System.out.println("payment = " + paymentInstrumentdata);
		By paymentInstrument = By.xpath("//div[contains(text(),'" + paymentInstrumentdata + "')]");
		driver.findElement(paymentInstrument).click();
		System.out.println("Test case id = " + TSID);

		if (TSID.equalsIgnoreCase("TS71") || TSID.equalsIgnoreCase("TS71_1")) {

			String budget = externalData.getFieldData(TSID, "Scheduled", "Budget Purpose");
			System.out.println("Budget == " + budget);
			od.payments_budgetPurpose.sendKeys(budget);
			By budgetPurpose = By
					.xpath("//div[contains(@class,'ng-star-inserted') and contains(text(),'" + budget + "')]");
			applyExplicitWaitsUntilElementVisible(budgetPurpose, 10);
			driver.findElement(budgetPurpose).click();
		}

		if (commonutils.isElementDisplayed(od.payments_ToAccountDropdown, 2)) {

			if (!(externalData.getFieldData(TSID, "Scheduled", "to").equalsIgnoreCase(toaccountNo))) {
				applyExplicitWaitsUntilElementClickable(od.payments_ToAccountDropdown, Duration.ofSeconds(5));
				scroll.scrollInToView(od.payments_ToAccountDropdown);
				dropdown.selectByVisibleText(od.payments_ToAccountDropdown, toaccountNo);

			} else {
				applyExplicitWaitsUntilElementClickable(od.payments_ToAccountDropdown, Duration.ofSeconds(5));
				scroll.scrollInToView(od.payments_ToAccountDropdown);
				dropdown.selectByVisibleText(od.payments_ToAccountDropdown,
						(externalData.getFieldData(TSID, "Scheduled", "to")));
			}
		}

		if (commonutils.isElementDisplayed(od.Payment_Beneficiaryaccno, 1)) {
			if(((TSID.equalsIgnoreCase("TS71"))||((TSID.equalsIgnoreCase("TS71_1"))))) {

			if (!(externalData.getFieldData(TSID, "Scheduled", "to").equalsIgnoreCase(toaccountNo))) {
				applyExplicitWaitsUntilElementClickable(od.Payment_Beneficiaryaccno, Duration.ofSeconds(5));
				scroll.scrollInToView(od.PaymentBudget_Beneficiaryaccno);
				od.Payment_Beneficiaryaccno.sendKeys(toaccountNo);
				By accno = By.xpath("//div[contains(text(),'" + toaccountNo + "')]");
				driver.findElement(accno).click();
			} else {
				applyExplicitWaitsUntilElementClickable(od.Payment_Beneficiaryaccno, Duration.ofSeconds(5));
				scroll.scrollInToView(od.PaymentBudget_Beneficiaryaccno);
				String accountNo = externalData.getFieldData(TSID, "Scheduled", "to");
				od.Payment_Beneficiaryaccno.sendKeys(accountNo);
				By accno = By
						.xpath("//div[contains(text(),'" + externalData.getFieldData(TSID, "Scheduled", "to") + "')]");
				driver.findElement(accno).click();
			}
			}
		}
		scroll.scrollInToView(od.parties_Accounts_accountOrIban);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_accountOrIban, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_Accounts_accountOrIban,
				externalData.getFieldData(TSID, "Scheduled", "Select Account/IBAN"));
		if (commonutils.isElementDisplayed(od.payments_beneficiaryBankBic, 1)) {
			scroll.scrollInToView(od.payments_beneficiaryBankBic);
			od.payments_beneficiaryBankBic
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Bank Bic"));
		}
		if (commonutils.isElementDisplayed(od.payments_beneficiaryCountryOfIncorporationDropdown, 1)) {
			scroll.scrollInToView(od.payments_beneficiaryCountryOfIncorporationDropdown);
			od.payments_beneficiaryCountryOfIncorporationDropdown
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Country Of Incorporation"));
		}

		if (commonutils.isElementDisplayed(od.payments_senderPop, 1)) {
			scroll.scrollInToView(od.payments_senderPop);
			od.payments_senderPop.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Sender POP"));
		}

		scroll.scrollInToView(od.parties_Accounts_beneficiaryName);
		od.parties_Accounts_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Name"));

		scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine1);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryAddressLine1, Duration.ofSeconds(5));

		od.parties_Accounts_beneficiaryAddressLine1
				.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Address Line 1"));

		od.PaymentBudget_BeneficiaryaccnoPurpose
				.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Address Line 1"));
		scroll.scrollInToView(od.payments_Amount);
		applyExplicitWaitsUntilElementClickable(od.payments_Amount, Duration.ofSeconds(5));
		od.payments_Amount.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Amount"));

		if (commonutils.isElementDisplayed(od.parties_Accounts_beneficiaryBankIfscCode, 1)) {
			od.parties_Accounts_beneficiaryBankIfscCode
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary bank IFSC code"));
		}
		if (commonutils.isElementDisplayed(od.PaymentBudget_BeneficiaryCountry, 1)) {
			od.PaymentBudget_BeneficiaryCountry
					.sendKeys(externalData.getFieldData(TSID, "Scheduled", "Beneficiary Country Of Incorporation"));
		}

		Thread.sleep(1500);
		od.payments_AddSubInstructionButton.click();
		Thread.sleep(1500);
	}

	public void editSubInstruction(String tSID) throws Exception {
		applyExplicitWaitsUntilElementClickable(od.payments_DealsummaryIcon, Duration.ofSeconds(15));
		try {
			od.payments_DealsummaryIcon.click();
		} catch (Exception e) {
			handleElementClickException(od.payments_DealsummaryIcon);
		}
		applyExplicitWaitsUntilElementClickable(od.payments_ScheduledInstructionIcon, Duration.ofSeconds(5));
		try {
			od.payments_ScheduledInstructionIcon.click();
		} catch (Exception e) {
			handleElementClickException(od.payments_ScheduledInstructionIcon);
		}
		applyExplicitWaitsUntilElementClickable(od.payments_KebabScheduledInstructionIcon, Duration.ofSeconds(5));
		try {
			od.payments_KebabScheduledInstructionIcon.click();
		} catch (Exception e) {
			handleElementClickException(od.payments_KebabScheduledInstructionIcon);
		}
		applyExplicitWaitsUntilElementClickable(od.payments_KebabScheduledInstructionIconEdit, Duration.ofSeconds(5));
		od.payments_KebabScheduledInstructionIconEdit.click();
		Thread.sleep(3000);

		od.payments_SpecifyAmountValue.clear();
		od.payments_SpecifyAmountValue.sendKeys(Keys.CONTROL + "a");
		od.payments_SpecifyAmountValue.sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		od.payments_SpecifyAmountValue.sendKeys("600");
		od.payments_NextArrowButtonTransferBasic.click();
		Thread.sleep(2000);
		od.payments_NextArrowButtonTransferSchedule.click();
		Thread.sleep(2000);
		od.payments_KebabScheduledInstructionIcon.click();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(od.payments_KebabScheduledInstructionIconEdit, Duration.ofSeconds(5));
		od.payments_KebabScheduledInstructionIconEdit.click();
		Thread.sleep(2000);
		od.payments_Amount.clear();
		Thread.sleep(2000);
		od.payments_Amount.sendKeys("600");
		Thread.sleep(2000);
		od.payments_Address.clear();
		int textLength = od.payments_Address.getAttribute("value").length();
		System.out.println(textLength);
		for (int i = 0; i < textLength; i++) {
			od.payments_Address.sendKeys("\b");
		}
		od.payments_Address.sendKeys("Pune");
		od.payments_UpdateSubInstruction.click();
	}
}
