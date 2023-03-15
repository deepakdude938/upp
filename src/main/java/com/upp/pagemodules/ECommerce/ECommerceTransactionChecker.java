package com.upp.pagemodules.ECommerce;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_Ecommerce;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;
import freemarker.template.utility.DateUtil;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ECommerceTransactionChecker extends BaseClass {

	public static Object_Ecommerce ecomm;
	DropDown dropdown;
	public ExcelReader externalData;
	ScrollTypes scroll;

	public ECommerceTransactionChecker() {

		ecomm = new Object_Ecommerce();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
		scroll = new ScrollTypes(driver);
	}

	public void addDealAsEcommerceTxn(String dealId, String TSID, String srcAccount, String toAccount)
			throws Exception {
		ecomm.ecommerce_SideMenuIcon.click();
		ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(3000);
		ecomm.ecommerce_TxnSearch.click();

		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_addNewmaker, Duration.ofSeconds(5));
		ecomm.ecommerce_addNewmaker.click();
		ecomm.ecommerce_dealId.sendKeys("REF1678771759732");
		By dealId_Option = By.xpath("//div[contains(text(),'" + "REF1678771759732" + "')]");
		driver.findElement(dealId_Option).click();
		ecomm.ecommerce_participantIdtxt.click();
		ecomm.ecommerce_participantId.click();
		ecomm.ecommerce_SubmitBtn.click();
		addBasicDetailsToEcommerceTxn(TSID, srcAccount, toAccount);
		addSubInstructionToEcommerceTxn(TSID);
		addDocument(TSID);
	}

	public void addBasicDetailsToEcommerceTxn(String TSID, String srcAccount, String toAccount) throws Exception {
		ecomm.ecommerce_purpose.click();
		System.out.println(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		ecomm.ecommerce_purpose.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		dropdown.selectByVisibleText(ecomm.ecommerce_purpose,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Purpose"));
		ecomm.ecommerce_participantAccount.click();
		ecomm.ecommerce_firstParticipantAccount.click();
		dropdown.selectByVisibleText(ecomm.ecommerce_balanceConsideration,
				externalData.getFieldData(TSID, "Ecomm Txn Maker", "Balance Consideration"));
		ecomm.ecommerce_saveAndContinue.click();
	}

	public void addSubInstructionToEcommerceTxn(String TSID) throws Exception {
		ecomm.ecommerce_creatorParticipant.click();
		ecomm.ecommerce_creatorParticipantIdOpt.click();
		Select creatorAcc = new Select(ecomm.ecommerce_creatorAccount);
		creatorAcc.selectByIndex(1);
		ecomm.ecommerce_ParticipantId.click();
		ecomm.ecommerce_ParticipantIdOpt.click();
		ecomm.ecommerce_PlatformRefNumber.sendKeys("testsnjna");
		ecomm.ecommerce_FragmentPlatformRefNumber.sendKeys("tessatnmj");

		scroll.scrollDown();

//		String inst = "BT";
//		// externalData.getFieldData(TSID, "Ecomm Txn Maker", "Instrument");
//		By instrument = By
//				.xpath("//li[@id='lbl-generic-autocomplete-listItemOption1']//div[contains(text(),'" + inst + "')]");
//		driver.findElement(instrument).click();
		String input = externalData.getFieldData(TSID, "Ecomm Txn Maker", "Instrument category");
		ecomm.ecommerce_txnCategory.sendKeys(input);
		By transaction_Category_Option = By.xpath("//div[contains(text(),'" + input + "')]");
		driver.findElement(transaction_Category_Option).click();
		ecomm.ecommerce_amount.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn Maker", "Amount"));
		// ecomm.ecommerce_bankBic.sendKeys(externalData.getFieldData(TSID, "Ecomm Txn
		// Maker", "Beneficiary Bank Bic"));
		String country = externalData.getFieldData(TSID, "Ecomm Txn Maker", "Beneficiary Country Of Incorporation");
		dropdown.selectByVisibleText(ecomm.ecommerce_beneficiaryCountry, country);
		ecomm.ecommerce_txnSubCategory.click();
		ecomm.ecommerce_txnSubCategoryValue.sendKeys("1");
		ecomm.ecommerce_addSubInstruction.click();
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
		ecomm.ecommerce_yes.click();
		ecomm.ecommerce_ok.click();

	}
}
