package com.upp.pagemodules.ECommerce;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pagemodules.Transactions.Transactions_Maker_Sub_Instruction;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_Ecommerce;
import com.upp.pageobjects.Object_Transactions;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;
import freemarker.template.utility.DateUtil;
import junit.framework.Assert;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ECommerceTransactionMaker extends BaseClass {

	public static Object_Ecommerce ecomm;
	DropDown dropdown;
	public ExcelReader externalData;
	ScrollTypes scroll;
	Transactions_Maker_Sub_Instruction tm;
	public static JavascriptClick jsClick;
	Object_Transactions tm1;

	public ECommerceTransactionMaker() {

		ecomm = new Object_Ecommerce();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
		scroll = new ScrollTypes(driver);
		tm = new Transactions_Maker_Sub_Instruction();
		jsClick = new JavascriptClick(driver);
		tm1 = new Object_Transactions();

	}

	public void addDealAsEcommerceTxn(String dealId, String TSID, String srcAccount, String toAccount,
			ICallback icallback) throws Exception {
		Thread.sleep(3000);
		ecomm.ecommerce_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Txnmaker, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_Txnmaker);
		// ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_TxnSearch, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_TxnSearch);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_addNewmaker, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_addNewmaker);
		ecomm.ecommerce_dealId.sendKeys(dealId);
		Thread.sleep(3000);
		By dealId_Option = By.xpath("//div[contains(text(),'" + dealId + "')]");
		driver.findElement(dealId_Option).click();
		Thread.sleep(3000);
		jsClick.click(ecomm.ecommerce_participantIdtxt);
		ecomm.ecommerce_participantId.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_SearchBtn, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_SearchBtn);
		Thread.sleep(2000);
		jsClick.click(ecomm.ecommerce_debitAccount);
		ecomm.ecommerce_SubmitBtn.click();
		addBasicDetailsToEcommerceTxn(TSID, srcAccount, toAccount);
		addSubInstructionToEcommerceTxn(TSID);
		// tm.Transaction_Maker_Sub_Instruction(TSID, icallback);
		addDocument(TSID);
	}

	public void addBasicDetailsToEcommerceTxn(String TSID, String srcAccount, String toAccount) throws Exception {
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.click();
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		dropdown.selectByVisibleText(ecomm.ecommerce_purpose,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(3000);
		ecomm.ecommerce_participantAccount.click();
		ecomm.ecommerce_firstParticipantAccount.click();
		Thread.sleep(2000);
		dropdown.selectByVisibleText(ecomm.ecommerce_balanceConsideration,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Balance Consideration"));
		Thread.sleep(2000);
		ecomm.ecommerce_saveAndContinue.click();
	}

	public void addSubInstructionToEcommerceTxn(String TSID) throws Exception {

		try {
			ecomm.ecommerce_creatorParticipant.click();
		} catch (Exception e) {
			handleElementClickException(ecomm.ecommerce_creatorParticipant);
		}
		Thread.sleep(2000);
		ecomm.ecommerce_creatorParticipantIdOpt.click();
		Select creatorAcc = new Select(ecomm.ecommerce_creatorAccount);
		creatorAcc.selectByIndex(1);
		ecomm.ecommerce_ParticipantId.click();
		ecomm.ecommerce_ParticipantIdOpt.click();
		ecomm.ecommerce_PlatformRefNumber.sendKeys("Test098");
		ecomm.ecommerce_FragmentPlatformRefNumber.sendKeys("Test099");

		scroll.scrollDown();

//		String inst = "BT";
//		// externalData.getFieldData(TSID, "Ecomm Txn Maker", "Instrument");
//		By instrument = By
//				.xpath("//li[@id='lbl-generic-autocomplete-listItemOption1']//div[contains(text(),'" + inst + "')]");
//		driver.findElement(instrument).click();
		String input = externalData.getFieldData(TSID, "Ecomm Txn Maker", "Instrument - category");
		ecomm.ecommerce_txnCategory.sendKeys(input);
		By transaction_Category_Option = By.xpath("//div[contains(text(),'" + input + "')]");
		driver.findElement(transaction_Category_Option).click();
		ecomm.ecommerce_amount.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Amount"));
		// ecomm.ecommerce_bankBic.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn
		// Maker", "Beneficiary Bank Bic"));
		String country = externalData.getFieldData(TSID, "Ecomm Txn Maker", "Beneficiary Country Of Incorporation");
		dropdown.selectByVisibleText(ecomm.ecommerce_beneficiaryCountry, country);
		ecomm.ecommerce_txnSubCategory.click();
//		ecomm.ecommerce_txnSubCategoryValue.sendKeys("1");
//		ecomm.ecommerce_addSubInstruction.click();
		ecomm.ecommerce_addSubInstruction.click();
		ecomm.ecommerce_nextSubInstruction.click();

	}

	public void addDocument(String TSID) throws Exception {
		dropdown.selectByVisibleText(ecomm.ecommerce_docType,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Documents-Document Type"));
		dropdown.selectByVisibleText(ecomm.ecommerce_fileType,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "File Type"));
		// ecomm.ecommerce_description.sendKeys(externalData.getFieldData(TSID, "Ecomm
		// Txn Maker", "Description"));
		ecomm.ecommerce_proceed.click();
		Thread.sleep(2000);
		ecomm.ecommerce_submit.click();
		Thread.sleep(3000);
		ecomm.ecommerce_yes.click();
		Thread.sleep(3000);
		ecomm.ecommerce_ok.click();
		Thread.sleep(2000);
//		ecomm.ecommerce_submit.click();
//		ecomm.ecommerce_yes.click();
//		ecomm.ecommerce_ok.click();
//		Thread.sleep(2000);

	}

	public void addDealAsEcommerceTxn1(String dealId, String TSID, String srcAccount, String toAccount,
			ICallback icallback) throws Exception {
		Thread.sleep(3000);
		ecomm.ecommerce_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Txnmaker, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_Txnmaker);
		// ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_TxnSearch, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_TxnSearch);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_addNewmaker, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_addNewmaker);
		ecomm.ecommerce_dealId.sendKeys(dealId);
		By dealId_Option = By.xpath("//div[contains(text(),'" + dealId + "')]");
		driver.findElement(dealId_Option).click();
		jsClick.click(ecomm.ecommerce_participantIdtxt);
		ecomm.ecommerce_participantIdtxt.sendKeys("Participant1");
		Thread.sleep(3000);
		ecomm.ecommerce_participantId1.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_SearchBtn, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_SearchBtn);
		Thread.sleep(3000);
		jsClick.click(ecomm.ecommerce_debitAccount);
		ecomm.ecommerce_SubmitBtn.click();
		addBasicDetailsWithUnlimitedDeditorToEcommerceTxn(TSID, srcAccount, toAccount);

	}

	public void addBasicDetailsWithUnlimitedDeditorToEcommerceTxn(String TSID, String srcAccount, String toAccount)
			throws Exception {
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.click();
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		dropdown.selectByVisibleText(ecomm.ecommerce_purpose,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(3000);
		ecomm.ecommerce_participantAccount.click();
		ecomm.ecommerce_firstParticipantAccount.click();
		Thread.sleep(2000);
		dropdown.selectByVisibleText(ecomm.ecommerce_balanceConsideration,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Balance Consideration"));
		Thread.sleep(2000);
		ecomm.unlimitDebtor.click();
		ecomm.participantRefId.click();
		ecomm.participantRefId.sendKeys(externalData.getFieldData("TS121_Participant1", "Party", "Party Name"));
		ecomm.ref1.click();
		Thread.sleep(2000);
		try {
			if (ecomm.warningMsgPopup.isDisplayed()) {
				String message = ecomm.warningMsg.getText();
				Assert.assertEquals(message, "OBO Responsibility for selected participant is not same as Debtor");
			}
		} catch (Exception e) {
			System.out.println("Failed");
		}
//				ecomm.ecommerce_saveAndContinue.click();
	}

	public void verifyDealRefIdAndParticipantRefId(String TSID) throws Exception {
		int flag = 0;
		Thread.sleep(3000);
		ecomm.ecommerce_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Txnmaker, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_Txnmaker);
		// ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_TxnSearch, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_TxnSearch);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_addNewmaker, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_addNewmaker);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_dealId, Duration.ofSeconds(100));
		ecomm.ecommerce_dealId.sendKeys(dealId);
		By dealId_Option = By.xpath("//div[contains(text(),'" + dealId + "')]");
		driver.findElement(dealId_Option).click();
		jsClick.click(ecomm.ecommerce_participantIdtxt);
		ecomm.ecommerce_participantId1.click();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_SearchBtn, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_SearchBtn);
		Thread.sleep(3000);
		jsClick.click(ecomm.ecommerce_debitAccount);
		ecomm.ecommerce_SubmitBtn.click();
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.click();
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		dropdown.selectByVisibleText(ecomm.ecommerce_purpose,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(3000);
		ecomm.ecommerce_participantAccount.click();
		ecomm.ecommerce_firstParticipantAccount.click();
		Thread.sleep(2000);
		dropdown.selectByVisibleText(ecomm.ecommerce_balanceConsideration,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Balance Consideration"));
		Thread.sleep(2000);
		ecomm.unlimitDebtor.click();
		try {
			if (ecomm.participantRefId.isDisplayed()) {
				if (ecomm.dealRefId.isDisplayed()) {
					flag = 1;
				}
			}

		} catch (Exception e) {
			flag = 0;
			// TODO: handle exception
		}
		Assert.assertEquals(flag, 1);
//				ecomm.ecommerce_saveAndContinue.click();
	}

	public void addDealAsEcommerceTxnForDebitAndPaymentCurrency(String dealId, String TSID, String srcAccount,
			String toAccount, ICallback icallback) throws Exception {
		Thread.sleep(3000);
		ecomm.ecommerce_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Txnmaker, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_Txnmaker);
		// ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_TxnSearch, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_TxnSearch);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_addNewmaker, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_addNewmaker);
		Thread.sleep(2000);
		ecomm.ecommerce_dealId.sendKeys(dealId);
		By dealId_Option = By.xpath("//div[contains(text(),'" + dealId + "')]");
		driver.findElement(dealId_Option).click();
		jsClick.click(ecomm.ecommerce_participantIdtxt);
		ecomm.ecommerce_participantIdtxt.sendKeys("Participant1");
		Thread.sleep(3000);
		ecomm.ecommerce_participantId.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_SearchBtn, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_SearchBtn);
		Thread.sleep(3000);
		jsClick.click(ecomm.ecommerce_debitAccount);
				ecomm.ecommerce_SubmitBtn.click();
		addBasicDetailsToEcommerceTxn(TSID, srcAccount, toAccount);
		addDebitAndPaymentCurrency(TSID);
	}

	public void addDebitAndPaymentCurrency(String TSID) throws Exception {
		try {
			ecomm.ecommerce_creatorParticipant.click();
		} catch (Exception e) {
			handleElementClickException(ecomm.ecommerce_creatorParticipant);
		}
		Thread.sleep(2000);
		ecomm.ecommerce_creatorParticipantIdOpt.click();
		Select creatorAcc = new Select(ecomm.ecommerce_creatorAccount);
		creatorAcc.selectByIndex(1);
		Thread.sleep(2000);
		ecomm.ecommerce_ParticipantId.click();
		ecomm.ecommerce_ParticipantIdOpt.click();
		ecomm.ecommerce_PlatformRefNumber.sendKeys("Test098");
		ecomm.ecommerce_FragmentPlatformRefNumber.sendKeys("Test099");
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm1.paymentCountry, Duration.ofSeconds(7));
		jsClick.click(tm1.paymentCountry);
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "beneficiaryCurrency"));
		dropdown.selectByVisibleText(tm1.paymentCountry,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "beneficiaryCurrency"));
		String paymentCurrency = externalData.getFieldData(TSID, "Ecomm Txn Maker", "beneficiaryCurrency");
		System.out.println("currency = " + paymentCurrency);
		if (!paymentCurrency.equalsIgnoreCase("INR")) {
			applyExplicitWaitsUntilElementClickable(tm1.paymentCountryType, Duration.ofSeconds(7));
			jsClick.click(tm1.paymentCountryType);
			dropdown.selectByVisibleText(tm1.paymentCountryType, "Debit Currency");
			System.out.println("Debit Currency set");
		}
		scroll.scrollDown();
//		String inst = "BT";
//		// externalData.getFieldData(TSID, "Ecomm Txn Maker", "Instrument");
//		By instrument = By
//				.xpath("//li[@id='lbl-generic-autocomplete-listItemOption1']//div[contains(text(),'" + inst + "')]");
//		driver.findElement(instrument).click();
		Thread.sleep(2000);
		String input = externalData.getFieldData(TSID, "Ecomm Txn Maker", "Instrument - category");
		ecomm.ecommerce_txnCategory.sendKeys(input);
		Thread.sleep(2000);
		By transaction_Category_Option = By.xpath("//div[contains(text(),'" + input + "')]");
		driver.findElement(transaction_Category_Option).click();
		Thread.sleep(2000);
		ecomm.ecommerce_amount.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Amount"));
		// ecomm.ecommerce_bankBic.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn
		// Maker", "Beneficiary Bank Bic"));
		String country = externalData.getFieldData(TSID, "Ecomm Txn Maker", "Beneficiary Country Of Incorporation");
		dropdown.selectByVisibleText(ecomm.ecommerce_beneficiaryCountry, country);
		ecomm.ecommerce_txnSubCategory.click();
//		ecomm.ecommerce_txnSubCategoryValue.sendKeys("1");
//		ecomm.ecommerce_addSubInstruction.click();
		ecomm.ecommerce_addSubInstruction.click();
		ecomm.ecommerce_nextSubInstruction.click();
	}

	public void Transaction_Maker_Sub_InstructionPayment_Currency(String TSID) throws Exception {
		System.out.println(TSID);
		scroll.scrollInToView(tm1.basicDetails);
		Thread.sleep(3000);
		jsClick.click(tm1.SubInstructionArrow);
		Thread.sleep(3000);
		jsClick.click(tm1.addSubInstructionArrow);
		try {
			ecomm.ecommerce_creatorParticipant.click();
		} catch (Exception e) {
			handleElementClickException(ecomm.ecommerce_creatorParticipant);
		}
		Thread.sleep(2000);
		ecomm.ecommerce_creatorParticipantIdOpt.click();
		Select creatorAcc = new Select(ecomm.ecommerce_creatorAccount);
		creatorAcc.selectByIndex(1);
		Thread.sleep(2000);
//		ecomm.ecommerce_ParticipantId.click();
//		ecomm.ecommerce_ParticipantIdOpt.click();
//		ecomm.ecommerce_PlatformRefNumber.sendKeys("Test098");
		ecomm.ecommerce_FragmentPlatformRefNumber.sendKeys("Test099");
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm1.paymentCountry, Duration.ofSeconds(7));
		jsClick.click(tm1.paymentCountry);
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "beneficiaryCurrency"));
		dropdown.selectByVisibleText(tm1.paymentCountry,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "beneficiaryCurrency"));
		String paymentCurrency = externalData.getFieldData(TSID, "Ecomm Txn Maker", "beneficiaryCurrency");
		System.out.println("currency = " + paymentCurrency);
		if (!paymentCurrency.equalsIgnoreCase("INR")) {
			applyExplicitWaitsUntilElementClickable(tm1.paymentCountryType, Duration.ofSeconds(7));
			jsClick.click(tm1.paymentCountryType);
			dropdown.selectByVisibleText(tm1.paymentCountryType, "Debit Currency");
			System.out.println("Debit Currency set");
		}
		scroll.scrollDown();
//		String inst = "BT";
//		// externalData.getFieldData(TSID, "Ecomm Txn Maker", "Instrument");
//		By instrument = By
//				.xpath("//li[@id='lbl-generic-autocomplete-listItemOption1']//div[contains(text(),'" + inst + "')]");
//		driver.findElement(instrument).click();
		Thread.sleep(2000);
//		String input = externalData.getFieldData(TSID, "Ecomm Txn Maker", "Instrument - category");
//		ecomm.ecommerce_txnCategory.sendKeys(input);
//		Thread.sleep(2000);
//		By transaction_Category_Option = By.xpath("//div[contains(text(),'" + input + "')]");
//		driver.findElement(transaction_Category_Option).click();
//		Thread.sleep(2000);
		ecomm.ecommerce_amount.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Amount"));
		// ecomm.ecommerce_bankBic.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn
		// Maker", "Beneficiary Bank Bic"));
		String country = externalData.getFieldData(TSID, "Ecomm Txn Maker", "Beneficiary Country Of Incorporation");
		dropdown.selectByVisibleText(ecomm.ecommerce_beneficiaryCountry, country);
		ecomm.ecommerce_txnSubCategory.click();
//		ecomm.ecommerce_txnSubCategoryValue.sendKeys("1");
//		ecomm.ecommerce_addSubInstruction.click();
		ecomm.ecommerce_addSubInstruction.click();
		ecomm.ecommerce_nextSubInstruction.click();
	}

	public void verifyNameAndAddressLine(String TSID) throws Exception {
		int flag =0;
		Thread.sleep(3000);
		ecomm.ecommerce_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Txnmaker, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_Txnmaker);
		// ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_TxnSearch, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_TxnSearch);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_addNewmaker, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_addNewmaker);
		ecomm.ecommerce_dealId.sendKeys(dealId);
		By dealId_Option = By.xpath("//div[contains(text(),'" + dealId + "')]");
		driver.findElement(dealId_Option).click();
		Thread.sleep(3000);
		jsClick.click(ecomm.ecommerce_participantIdtxt);
		ecomm.ecommerce_participantId1.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_SearchBtn, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_SearchBtn);
		Thread.sleep(3000);
		jsClick.click(ecomm.ecommerce_debitAccount);
		Thread.sleep(3000);
		ecomm.ecommerce_SubmitBtn.click();
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.click();
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		dropdown.selectByVisibleText(ecomm.ecommerce_purpose,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(3000);
		ecomm.ecommerce_participantAccount.click();
		ecomm.ecommerce_firstParticipantAccount.click();
		Thread.sleep(2000);
		dropdown.selectByVisibleText(ecomm.ecommerce_balanceConsideration,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Balance Consideration"));
		Thread.sleep(2000);
		ecomm.unlimitDebtor.click();
		List<WebElement> ol = ecomm.debtorDetails;
		ArrayList<String> accountNoList = new ArrayList<>();
		for (WebElement q : ol) {
			String account = q.getText().trim();
			System.out.println(account);
			accountNoList.add(account);
			try {
				if (accountNoList.contains("Name") || accountNoList.contains("Address Line")) {

					flag = 1;

				}

			} catch (Exception e) {
				flag = 0;
				// TODO: handle exception
			}

		}
		System.out.println("flag = " + flag);
		Assert.assertEquals(flag, 1);
//				ecomm.ecommerce_saveAndContinue.click();
	}

	public void verifyNameAndAddressLineDealRefParticipantRef(String TSID) throws Exception {
		int flag = 0;
		Thread.sleep(3000);
		ecomm.ecommerce_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Txnmaker, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_Txnmaker);
		// ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_TxnSearch, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_TxnSearch);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_addNewmaker, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_addNewmaker);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_dealId, Duration.ofSeconds(100));
		ecomm.ecommerce_dealId.sendKeys(dealId);
		By dealId_Option = By.xpath("//div[contains(text(),'" + dealId + "')]");
		driver.findElement(dealId_Option).click();
		jsClick.click(ecomm.ecommerce_participantIdtxt);
		ecomm.ecommerce_participantId.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_SearchBtn, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_SearchBtn);
		Thread.sleep(3000);
		jsClick.click(ecomm.ecommerce_debitAccount);
		Thread.sleep(3000);
		ecomm.ecommerce_SubmitBtn.click();
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.click();
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		dropdown.selectByVisibleText(ecomm.ecommerce_purpose,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(3000);
		ecomm.ecommerce_participantAccount.click();
		ecomm.ecommerce_firstParticipantAccount.click();
		Thread.sleep(2000);
		dropdown.selectByVisibleText(ecomm.ecommerce_balanceConsideration,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Balance Consideration"));
		Thread.sleep(2000);
		ecomm.unlimitDebtor.click();
		List<WebElement> ol = ecomm.debtorDetails;
		ArrayList<String> accountNoList = new ArrayList<>();
		for (WebElement q : ol) {
			String account = q.getText().trim();
			System.out.println(account);
			accountNoList.add(account);
			try {
				if (accountNoList.contains("Name") || accountNoList.contains("Address Line")
						|| accountNoList.contains("Deal RefId") || accountNoList.contains("Participant RefId")) {

					flag = 1;

				}

			} catch (Exception e) {
				flag = 0;
				// TODO: handle exception
			}

		}
		System.out.println("flag = " + flag);
		Assert.assertEquals(flag, 1);
//				ecomm.ecommerce_saveAndContinue.click();
	}

	public void verifyNameAsMandatoryOption(String TSID) throws Exception {
		int flag = 0;
		Thread.sleep(3000);
		ecomm.ecommerce_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Txnmaker, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_Txnmaker);
		// ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_TxnSearch, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_TxnSearch);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_addNewmaker, Duration.ofSeconds(100));
		jsClick.click(ecomm.ecommerce_addNewmaker);
		ecomm.ecommerce_dealId.sendKeys(dealId);
		By dealId_Option = By.xpath("//div[contains(text(),'" + dealId + "')]");
		driver.findElement(dealId_Option).click();
		jsClick.click(ecomm.ecommerce_participantIdtxt);
		ecomm.ecommerce_participantId.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_SearchBtn, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_SearchBtn);
		jsClick.click(ecomm.ecommerce_debitAccount);
		Thread.sleep(3000);
		ecomm.ecommerce_SubmitBtn.click();
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.click();
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		dropdown.selectByVisibleText(ecomm.ecommerce_purpose,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(3000);
		ecomm.ecommerce_participantAccount.click();
		ecomm.ecommerce_firstParticipantAccount.click();
		Thread.sleep(2000);
		dropdown.selectByVisibleText(ecomm.ecommerce_balanceConsideration,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Balance Consideration"));
		Thread.sleep(2000);
		ecomm.unlimitDebtor.click();
		List<WebElement> ol = ecomm.debtorDetails;
		Thread.sleep(2000);
		ecomm.addressline1.sendKeys("Pune");
		ecomm.addressline2.sendKeys("Maharastra");
		ecomm.addressline3.sendKeys("Test1");
		ecomm.addressline4.sendKeys("Test2");
		ecomm.country.click();
		dropdown.selectByVisibleText(ecomm.country, "India");
		Thread.sleep(2000);
		ecomm.ecommerce_saveAndContinue.click();
		Thread.sleep(2000);
		Actions actions = new Actions(driver);

		try {
			if (ecomm.errorIcon.isDisplayed()) {
				Thread.sleep(2000);
				actions.moveToElement(ecomm.errorIcon).perform();
				String errorMsg = ecomm.errorMessage.getAttribute("requiredmsg");
				System.out.println("Error msg = " + errorMsg);
				Thread.sleep(2000);
				Assert.assertEquals(errorMsg, "Name is required");

			}
		} catch (Exception e) {
			Assert.fail();
		}
	}

	public void verifyUltimateDebtorCheckboxDisable(String TSID) throws Exception {
		int flag = 0;
		Thread.sleep(3000);
		ecomm.ecommerce_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Txnmaker, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_Txnmaker);
		// ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_TxnSearch, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_TxnSearch);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_addNewmaker, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_addNewmaker);
		ecomm.ecommerce_dealId.sendKeys(dealId);
		By dealId_Option = By.xpath("//div[contains(text(),'" + dealId + "')]");
		driver.findElement(dealId_Option).click();
		jsClick.click(ecomm.ecommerce_participantIdtxt);
		ecomm.ecommerce_participantId.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_SearchBtn, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_SearchBtn);
		Thread.sleep(3000);
		jsClick.click(ecomm.ecommerce_debitAccount);
		Thread.sleep(3000);
		ecomm.ecommerce_SubmitBtn.click();
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.click();
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		dropdown.selectByVisibleText(ecomm.ecommerce_purpose,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(3000);
		ecomm.ecommerce_participantAccount.click();
		ecomm.ecommerce_firstParticipantAccount.click();
		Thread.sleep(2000);
		dropdown.selectByVisibleText(ecomm.ecommerce_balanceConsideration,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Balance Consideration"));
		Thread.sleep(2000);
		System.out.println("disable = " + ecomm.unlimitDebtor.getAttribute("class"));
	}

	public void verifyDebtorOption(String TSID) throws Exception {
		int flag = 0;
		Thread.sleep(3000);
		ecomm.ecommerce_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Txnmaker, Duration.ofSeconds(25));
		jsClick.click(ecomm.ecommerce_Txnmaker);
		// ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(5000);
//		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_TxnSearch, Duration.ofSeconds(25));
//		jsClick.click(ecomm.ecommerce_TxnSearch);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_addNewmaker, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_addNewmaker);
		// dealId = "REF1703845408075";
		ecomm.ecommerce_dealId.sendKeys(dealId);
		By dealId_Option = By.xpath("//div[contains(text(),'" + dealId + "')]");
		driver.findElement(dealId_Option).click();
		jsClick.click(ecomm.ecommerce_participantIdtxt);
		ecomm.ecommerce_participantId.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_SearchBtn, Duration.ofSeconds(5));
		jsClick.click(ecomm.ecommerce_SearchBtn);
		Thread.sleep(3000);
		try {
			ecomm.ecommerce_debitAccount.click();
		} catch (Exception e) {
			handleElementClickException(ecomm.ecommerce_debitAccount);
		}

		ecomm.ecommerce_SubmitBtn.click();
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.click();
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(2000);
		ecomm.ecommerce_purpose.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		dropdown.selectByVisibleText(ecomm.ecommerce_purpose,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		Thread.sleep(3000);
		ecomm.ecommerce_participantAccount.click();
		ecomm.ecommerce_firstParticipantAccount.click();
		Thread.sleep(2000);
		dropdown.selectByVisibleText(ecomm.ecommerce_balanceConsideration,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Balance Consideration"));
		Thread.sleep(2000);
		ecomm.ecommerce_saveAndContinue.click();
		try {
			ecomm.ecommerce_creatorParticipant.click();
		} catch (Exception e) {
			handleElementClickException(ecomm.ecommerce_creatorParticipant);
		}
		Thread.sleep(2000);
		ecomm.ecommerce_creatorParticipantIdOpt.click();
		Select creatorAcc = new Select(ecomm.ecommerce_creatorAccount);
		creatorAcc.selectByIndex(1);
		Thread.sleep(2000);
		ecomm.ecommerce_ParticipantId.click();
		ecomm.ecommerce_ParticipantIdOpt.click();
		ecomm.ecommerce_PlatformRefNumber.sendKeys("Test098");
		ecomm.ecommerce_FragmentPlatformRefNumber.sendKeys("Test099");
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm1.paymentCountry, Duration.ofSeconds(7));
		jsClick.click(tm1.paymentCountry);
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "beneficiaryCurrency"));
		dropdown.selectByVisibleText(tm1.paymentCountry,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "beneficiaryCurrency"));
		String paymentCurrency = externalData.getFieldData(TSID, "Ecomm Txn Maker", "beneficiaryCurrency");
		System.out.println("currency = " + paymentCurrency);
		applyExplicitWaitsUntilElementClickable(tm1.paymentCountryType, Duration.ofSeconds(7));
		jsClick.click(tm1.paymentCountryType);

		for (int i = 0; i < 2; i++) {
			List<String> s = dropdown.getAllValue(tm1.paymentCountryType);
			System.out.println("List = " + s);
			if (s.contains("Debit Currency")) {
				flag = 1;
				break;
			}
		}
		Assert.assertEquals(flag, 1);

	}
}
