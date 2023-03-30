package com.upp.handlers;

import java.time.Duration;

import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class DealLinkedInstruction_PaymentInstrumentHandler extends BaseClass implements ICallback {
	public static Object_NewDeal od;
	DropDown dropdown;
	public ScrollTypes scroll;
	public static ExcelReader externalData;

	public DealLinkedInstruction_PaymentInstrumentHandler() {
		od = new Object_NewDeal();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
		scroll = new ScrollTypes(driver);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

	}

	public void handlePartyMakerBT_INPaymentInstrument(String TSID, String toaccountNo) throws Exception {

		od.linkedInstruction_Instrumentddl.sendKeys(externalData.getFieldData(TSID, "Linked", "Instrument"));
		String inst = externalData.getFieldData(TSID, "Linked", "Instrument");
		By instrument = By.xpath("//div[contains(text(),'" + inst + "')]");
		driver.findElement(instrument).click();
		od.linkedInstruction_ToAccountddl.sendKeys(toaccountNo);
		By toaccountselect = By.xpath("//div[contains(text(),'" + toaccountNo + "')]");
		System.out.println(toaccountselect);
		driver.findElement(toaccountselect).click();
		applyExplicitWaitsUntilElementClickable(od.linkedInstruction_accountOrIban, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.linkedInstruction_accountOrIban, " ACCOUNTNUMBER ");
		od.linkedInstruction_Amount.sendKeys(externalData.getFieldData(TSID, "Linked", "Amount"));
		od.linkedInstruction_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Linked", "Beneficiary Name"));
		od.parties_Accounts_beneficiaryBankIfscCode
				.sendKeys(externalData.getFieldData(TSID, "Linked", "Beneficiary bank IFSC code"));
		od.linkedInstruction_beneficiaryAddressl
				.sendKeys(externalData.getFieldData(TSID, "Linked", "Beneficiary Address Line 1"));
		dropdown.selectByVisibleText(od.linkedInstruction_beneficiaryCountry, "IN");
		dropdown.selectByVisibleText(od.linkedInstruction_Incorporationddl,
				externalData.getFieldData(TSID, "Linked", "Beneficiary Country Of Incorporation"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

}
