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
			if(!(TSID.equalsIgnoreCase("TS70"))) {

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
				By accno = By
						.xpath("//div[contains(text(),'" + externalData.getFieldData(TSID, "Scheduled", "to") + "')]");
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
		Thread.sleep(1000);
	}
}
