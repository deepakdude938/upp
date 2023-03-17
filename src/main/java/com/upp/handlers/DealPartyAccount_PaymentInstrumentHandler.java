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

public class DealPartyAccount_PaymentInstrumentHandler extends BaseClass  implements ICallback {
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	DropDown dropdown;

	public static ScrollTypes scroll;
	public static JavascriptClick jsClick;
	public static Object_Transactions tm ;

	public DealPartyAccount_PaymentInstrumentHandler() {
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

	public void handle_BT_PaymentInstrument(String TSID) throws Exception {
		
		System.out.println("inside BT");
		applyExplicitWaitsUntilElementClickable(od.parties_PaymentSystem_BT, Duration.ofSeconds(5));
		od.parties_PaymentSystem_BT.click();
		
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_Amount, Duration.ofSeconds(5));
		od.parties_Accounts_Amount.sendKeys(externalData.getFieldData(TSID, "Party", "Amount"));
		
		scroll.scrollInToView(od.parties_beneficiaryBankBic);
		applyExplicitWaitsUntilElementClickable(od.parties_beneficiaryBankBic, Duration.ofSeconds(5));
		od.parties_beneficiaryBankBic.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Bank Bic"));
		
		scroll.scrollInToView(od.parties_Accounts_beneficiaryCountryOfIncorporation);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryCountryOfIncorporation, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_Accounts_beneficiaryCountryOfIncorporation,
				externalData.getFieldData(TSID, "Party", "Beneficiary Country Of Incorporation"));
		
		scroll.scrollInToView(od.parties_BeneficiaryCountry);
		applyExplicitWaitsUntilElementClickable(od.parties_BeneficiaryCountry, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_BeneficiaryCountry,
				externalData.getFieldData(TSID, "Party", "Beneficiary Country"));
		
		scroll.scrollInToView(od.parties_paymentTo);
		applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
		od.parties_paymentTo.sendKeys(externalData.getFieldData(TSID, "Party", "To"));
		
		scroll.scrollInToView(od.parties_beneficiaryCurrency);
		od.parties_beneficiaryCurrency.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Currency"));
		applyExplicitWaitsUntilElementClickable(od.parties_partyAccountsAddButton, Duration.ofSeconds(5));
		od.parties_partyAccountsAddButton.click();
		
	}
	
	
public void handle_LTTest_PaymentInstrument(String TSID) throws Exception {
		
	    scroll.scrollInToView(od.parties_Account_LTTest);
		applyExplicitWaitsUntilElementClickable(od.parties_Account_LTTest, Duration.ofSeconds(8));
		od.parties_Account_LTTest.click();
		applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
		od.parties_paymentTo.sendKeys(externalData.getFieldData(TSID, "Party", "To"));
		applyExplicitWaitsUntilElementClickable(od.parties_Account_accountType, Duration.ofSeconds(5));
		od.parties_Account_accountType.sendKeys(externalData.getFieldData(TSID, "Party", "accountType"));
		od.parties_Account_beneficiaryCountryOfIncorporation.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Country Of Incorporation"));
		od.parties_Account_beneficiaryCountry.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Country"));
		scroll.scrollInToView(od.parties_Account_werwer);
		applyExplicitWaitsUntilElementClickable(od.parties_Account_werwer, Duration.ofSeconds(5));
		od.parties_Account_werwer.sendKeys(externalData.getFieldData(TSID, "Party", "werwer"));
		od.parties_Account_trteo.sendKeys(externalData.getFieldData(TSID, "Party", "Trteo"));
		
		applyExplicitWaitsUntilElementClickable(od.parties_partyAccountsAddButton, Duration.ofSeconds(5));
		od.parties_partyAccountsAddButton.click();
		
	}


	
}
