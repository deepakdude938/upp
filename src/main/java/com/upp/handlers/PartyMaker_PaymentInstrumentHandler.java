package com.upp.handlers;

import java.time.Duration;

import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Transactions;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class PartyMaker_PaymentInstrumentHandler extends BaseClass  implements ICallback {
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	DropDown dropdown;

	public static ScrollTypes scroll;
	public static JavascriptClick jsClick;
	public static Object_Transactions tm ;

	public PartyMaker_PaymentInstrumentHandler() {
		od = new Object_NewDeal();
		dropdown = new DropDown(BaseClass.driver);
		scroll=new ScrollTypes(driver);
		jsClick=new JavascriptClick(driver);
		tm=new Object_Transactions();
		externalData = new ExcelReader();
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub

	}

	public void handlePartyMakerBT_PaymentInstrument(String TSID) throws Exception {
		
		
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_Amount, Duration.ofSeconds(5));
		od.parties_Accounts_Amount.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Amount"));
		
		scroll.scrollInToView(od.parties_beneficiaryBankBic);
		applyExplicitWaitsUntilElementClickable(od.parties_beneficiaryBankBic, Duration.ofSeconds(5));
		od.parties_beneficiaryBankBic.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary Bank Bic"));
		
		scroll.scrollInToView(od.parties_Accounts_beneficiaryCountryOfIncorporation);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryCountryOfIncorporation, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_Accounts_beneficiaryCountryOfIncorporation,
				externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary Country Of Incorporation"));
		
		scroll.scrollInToView(od.parties_BeneficiaryCountry);
		applyExplicitWaitsUntilElementClickable(od.parties_BeneficiaryCountry, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_BeneficiaryCountry,
				externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary Country"));
		
		scroll.scrollInToView(od.parties_paymentTo);
		applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
		od.parties_paymentTo.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "To"));
		
		scroll.scrollInToView(od.parties_beneficiaryCurrency);
		od.parties_beneficiaryCurrency.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary Currency"));
		applyExplicitWaitsUntilElementClickable(od.parties_partyAccountsAddButton, Duration.ofSeconds(5));
		od.parties_partyAccountsAddButton.click();
		
	}
	
public void handlePartyMakerBT_INPaymentInstrument(String TSID) throws Exception {
		

	
	applyExplicitWaitsUntilElementClickable(od.parties_PaymentType, Duration.ofSeconds(5));
	dropdown.selectByVisibleText(od.parties_PaymentType,"BT");
	
	od.parties_Accounts_beneficiaryBankIfscCode.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary bank IFSC code"));
	od.parties_Accounts_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary Name"));
	
	scroll.scrollInToView(od.parties_Accounts_accountOrIban);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_accountOrIban, Duration.ofSeconds(5));
	dropdown.selectByVisibleText(od.parties_Accounts_accountOrIban,externalData.getFieldData(TSID, "Parties-Maker", "Select Account/IBAN"));
	
	scroll.scrollInToView(od.parties_paymentTo);
	applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
	od.parties_paymentTo.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary Account Number / IBAN"));
	
	scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine1);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryAddressLine1, Duration.ofSeconds(5));
	od.parties_Accounts_beneficiaryAddressLine1.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary Address Line 1"));
	

	scroll.scrollInToView(od.parties_BeneficiaryCountry);
	applyExplicitWaitsUntilElementClickable(od.parties_BeneficiaryCountry, Duration.ofSeconds(5));
	dropdown.selectByVisibleText(od.parties_BeneficiaryCountry,
			externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary Country"));
	
	scroll.scrollInToView(od.parties_Accounts_beneficiaryCountryOfIncorporation);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryCountryOfIncorporation, Duration.ofSeconds(5));
	dropdown.selectByVisibleText(od.parties_Accounts_beneficiaryCountryOfIncorporation,
			externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary Country Of Incorporation"));

	od.parties_partyAccountsAddButton.click();
	
		
	}
	


	
}
