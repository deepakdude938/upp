package com.upp.handlers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_Transactions;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class TransactionMaker_PaymentInstrumentHandler extends BaseClass implements ICallback {
	public static Object_Deal od;
	public static ExcelReader externalData;
	DropDown dropdown;

	public static ScrollTypes scroll;
	public static JavascriptClick jsClick;
	public static Object_Transactions tm;
	public static CommonUtils commonutils;

	public TransactionMaker_PaymentInstrumentHandler() {
		od = new Object_Deal();
		dropdown = new DropDown(BaseClass.driver);
		scroll = new ScrollTypes(driver);
		jsClick = new JavascriptClick(driver);
		tm = new Object_Transactions();
		externalData = new ExcelReader();
		commonutils = new CommonUtils(driver);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

	}

	public void handleBTPaymentInstrument(String TSID, String sourceAccountno, String toaccountNo) throws Exception {

		scroll.scrollInToView(tm.transactions_ToAccountDropdown);
		applyExplicitWaitsUntilElementClickable(tm.transactions_ToAccountDropdown, Duration.ofSeconds(7));
		jsClick.click(tm.transactions_ToAccountDropdown);
		dropdown.selectByVisibleText(tm.transactions_ToAccountDropdown,
				externalData.getFieldData(TSID, "Txn Maker", "to"));
		System.out.println("the to data is " + externalData.getFieldData(TSID, "Txn Maker", "to"));

		scroll.scrollInToView(od.payments_beneficiaryCountryOfIncorporationDropdown);
		applyExplicitWaitsUntilElementClickable(od.payments_beneficiaryCountryOfIncorporationDropdown,
				Duration.ofSeconds(7));
		od.payments_beneficiaryCountryOfIncorporationDropdown
				.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Beneficiary Country Of Incorporation"));

		applyExplicitWaitsUntilElementClickable(od.payments_Amount, Duration.ofSeconds(5));
		scroll.scrollInToView(od.payments_Amount);
		od.payments_Amount.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Amount"));

		scroll.scrollInToView(od.payments_AddSubInstructionButton);
		od.payments_AddSubInstructionButton.click();
		scroll.scrollInToView(od.payments_NextArrowButtonTransferSubInstruction);
		applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonTransferSubInstruction,
				Duration.ofSeconds(10));
		od.payments_NextArrowButtonTransferSubInstruction.click();

	}

	public void handleLT_INPaymentInstrumentFor_Non_Registered_Beneficiary_WithCheckbox_Unchecked(String TSID,
			String sourceAccountno, String toaccountNo) throws Exception {
		System.out.println("Inside LT_IN");
		scroll.scrollInToView(tm.transactions_ToAccountDropdown);
		applyExplicitWaitsUntilElementClickable(tm.transactions_ToAccountDropdown, Duration.ofSeconds(7));
		jsClick.click(tm.transactions_ToAccountDropdown);
		dropdown.selectByVisibleText(tm.transactions_ToAccountDropdown, sourceAccountno);

		tm.transactions_bankIFSCCode
				.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Beneficiary bank IFSC code"));
		tm.transactions_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Beneficiary Name"));
		dropdown.selectByValue(tm.transactions_accountOrIban,
				externalData.getFieldData(TSID, "Txn Maker", "Select Account/IBAN"));

		tm.transactions_address.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Beneficiary Address Line 1"));
		tm.transactions_amount.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Amount"));

		if ((commonutils.isElementDisplayed(tm.transactions_beneficiaryaccountNumberInput, 1))) {
			Assert.fail("The non registered Beneficiary_WithCheckbox_Unchecked should accept only registered accounts");
		}

		scroll.scrollInToView(tm.transactions_beneficiaryIncorporation);
		dropdown.selectByValue(tm.transactions_beneficiaryIncorporation,
				externalData.getFieldData(TSID, "Txn Maker", "Beneficiary Country Of Incorporation"));
		applyExplicitWaitsUntilElementClickable(od.payments_AddSubInstructionButton, Duration.ofSeconds(10));
		scroll.scrollInToView(od.payments_AddSubInstructionButton);
		od.payments_AddSubInstructionButton.click();
		scroll.scrollInToView(od.payments_NextArrowButtonTransferSubInstruction);
		applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonTransferSubInstruction,
				Duration.ofSeconds(10));
		jsClick.click(od.payments_NextArrowButtonTransferSubInstruction);

	}

	public void handleBT_UAEPaymentInstrument(String TSID, String sourceAccountno, String toaccountNo)
			throws Exception {

		scroll.scrollInToView(tm.transactions_ToAccountDropdown);
		applyExplicitWaitsUntilElementClickable(tm.transactions_ToAccountDropdown, Duration.ofSeconds(7));
		jsClick.click(tm.transactions_ToAccountDropdown);
		dropdown.selectByVisibleText(tm.transactions_ToAccountDropdown, toaccountNo);
		tm.transactions_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Beneficiary Name"));
		dropdown.selectByValue(tm.transactions_accountOrIban,
				externalData.getFieldData(TSID, "Txn Maker", "Select Account/IBAN"));

		tm.transactions_address.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Beneficiary Address Line 1"));
		tm.transactions_amount.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Amount"));
		tm.transactions_beneficiaryBankBic.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Beneficiary BIC"));
		scroll.scrollInToView(tm.transactions_beneficiaryIncorporation);
		dropdown.selectByValue(tm.transactions_beneficiaryIncorporation,
				externalData.getFieldData(TSID, "Txn Maker", "Beneficiary Country Of Incorporation"));
		tm.transactions_senderPop.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Sender POP"));
		applyExplicitWaitsUntilElementClickable(od.payments_AddSubInstructionButton, Duration.ofSeconds(10));
		scroll.scrollInToView(od.payments_AddSubInstructionButton);
		od.payments_AddSubInstructionButton.click();
		scroll.scrollInToView(od.payments_NextArrowButtonTransferSubInstruction);
		applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonTransferSubInstruction,
				Duration.ofSeconds(10));
		jsClick.click(od.payments_NextArrowButtonTransferSubInstruction);

	}

	public void handleLT_INPaymentInstrumentFor_Non_Registered_Beneficiary_WithCheckbox_checked(String tsid,
			String sourceAccount, String toAccount) throws Exception {
		System.out.println("Checkbox check");

		tm.transactions_beneficiaryaccountNumber.click();
		tm.transactions_beneficiaryaccountNumberInput.sendKeys(toAccount);

		tm.transactions_amount.sendKeys(externalData.getFieldData(tsid, "Txn Maker", "Amount"));
		tm.transactions_bankIFSCCode
				.sendKeys(externalData.getFieldData(tsid, "Txn Maker", "Beneficiary bank IFSC code"));
		tm.transactions_beneficiaryName.sendKeys(externalData.getFieldData(tsid, "Txn Maker", "Beneficiary Name"));
		dropdown.selectByValue(tm.transactions_accountOrIban,
				externalData.getFieldData(tsid, "Txn Maker", "Select Account/IBAN"));

		tm.transactions_address.sendKeys(externalData.getFieldData(tsid, "Txn Maker", "Beneficiary Address Line 1"));
		dropdown.selectByValue(tm.transactions_country,
				externalData.getFieldData(tsid, "Txn Maker", "beneficiaryCountry"));
		dropdown.selectByValue(tm.transactions_beneficiaryIncorporation,
				externalData.getFieldData(tsid, "Txn Maker", "Beneficiary Country Of Incorporation"));
		tm.transactions_addSubInstruction.click();

	}

	public void handleLT_INPaymentInstrumentForAdhocTransaction(String TSID, String sourceAccountno, String toaccountNo)
			throws Exception {
		System.out.println("Inside Adhoc transaction");

		System.out.println("Inside LT_IN");
//		scroll.scrollInToView(tm.toAccount);
//		applyExplicitWaitsUntilElementClickable(tm.toAccount, Duration.ofSeconds(7));
//		jsClick.click(tm.toAccount);
//		dropdown.selectByVisibleText(tm.toAccount, sourceAccountno);

		tm.transactions_bankIFSCCode
				.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Beneficiary bank IFSC code"));
		tm.transactions_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Beneficiary Name"));
		dropdown.selectByValue(tm.transactions_accountOrIban,
				externalData.getFieldData(TSID, "Txn Maker", "Select Account/IBAN"));

		tm.transactions_address.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Beneficiary Address Line 1"));
		tm.transactions_amount.sendKeys(externalData.getFieldData(TSID, "Txn Maker", "Amount"));

//		if ((commonutils.isElementDisplayed(tm.transactions_beneficiaryaccountNumberInput, 1))) {
//			Assert.fail("The non registered Beneficiary_WithCheckbox_Unchecked should accept only registered accounts");
//		}

		scroll.scrollInToView(tm.transactions_country);
		dropdown.selectByValue(tm.transactions_country,
				externalData.getFieldData(TSID, "Txn Maker", "beneficiaryCountry"));
		
		scroll.scrollInToView(tm.transactions_beneficiaryaccountNumberInput);
		tm.transactions_beneficiaryaccountNumberInput.sendKeys(sourceAccountno);
		dropdown.selectByValue(tm.transactions_beneficiaryaccountNumberInput,sourceAccountno);
		
		scroll.scrollInToView(tm.transactions_beneficiaryIncorporation);
		dropdown.selectByValue(tm.transactions_beneficiaryIncorporation,
				externalData.getFieldData(TSID, "Txn Maker", "Beneficiary Country Of Incorporation"));
		
		applyExplicitWaitsUntilElementClickable(od.payments_AddSubInstructionButton, Duration.ofSeconds(10));
		scroll.scrollInToView(od.payments_AddSubInstructionButton);
		od.payments_AddSubInstructionButton.click();
		scroll.scrollInToView(od.payments_NextArrowButtonTransferSubInstruction);
		applyExplicitWaitsUntilElementClickable(od.payments_NextArrowButtonTransferSubInstruction,
				Duration.ofSeconds(10));
		jsClick.click(od.payments_NextArrowButtonTransferSubInstruction);

	}
}
