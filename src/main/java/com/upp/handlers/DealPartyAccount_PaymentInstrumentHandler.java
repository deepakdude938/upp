package com.upp.handlers;

import java.time.Duration;

import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Transactions;
import com.upp.utils.CommonUtils;
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
	CommonUtils commonutils;

	public DealPartyAccount_PaymentInstrumentHandler() {
		od = new Object_NewDeal();
		dropdown = new DropDown(BaseClass.driver);
		scroll=new ScrollTypes(driver);
		jsClick=new JavascriptClick(driver);
		tm=new Object_Transactions();
		externalData = new ExcelReader();
		commonutils=new CommonUtils(driver);
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
	
public void handleBT_IN_PaymentInstrument(String TSID) throws Exception {
		
		System.out.println("inside BT_IN");
		scroll.scrollInToView(od.parties_PaymentSystem_BT_IN);
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(od.parties_PaymentSystem_BT_IN, Duration.ofSeconds(10));
		od.parties_PaymentSystem_BT_IN.click();
		
		applyExplicitWaitsUntilElementClickable(od.parties_PaymentType, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_PaymentType,"BT");
		
		System.out.println(externalData.getFieldData(TSID, "Party", "Beneficiary bank IFSC code"));
		od.parties_Accounts_beneficiaryBankIfscCode.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary bank IFSC code"));
		od.parties_Accounts_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Name"));
		
		scroll.scrollInToView(od.parties_Accounts_accountOrIban);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_accountOrIban, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_Accounts_accountOrIban,externalData.getFieldData(TSID, "Party", "Select Account/IBAN"));
		
		scroll.scrollInToView(od.parties_paymentTo);
		if(TSID.equals("TS107")||TSID.equals("TS107_1")||TSID.equals("TS123")||TSID.equals("TS123_1")||TSID.equals("TS124")||TSID.equals("TS124_1")||TSID.equals("TS127")||TSID.equals("TS127_1")||TSID.equals("TS131")||TSID.equals("TS131_1")) {
			if(accountList.contains(debitAccount)) {
				accountList.remove(debitAccount);
				System.out.println(accountList);
			}
			applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
			String accountKey = externalData.getFieldData(TSID, "Party", "Beneficiary Account Key");
			accountMap.put(accountKey, accountList.get(0));
			od.parties_paymentTo.sendKeys(accountList.get(0));
			accountList.remove(0);
		}
		else if(TSID.equals("TS136")||TSID.equals("TS136_1")||TSID.equals("TS136_2")) {
			if(accountList.contains(debitAccount)) {
				accountList.remove(debitAccount);
				System.out.println(accountList);
			}
			applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
			String accountNo = externalData.getFieldData(TSID, "Party", "Beneficiary Account Number / IBAN");
			accountMap.put(accountNo, accountList.get(0));
			od.parties_paymentTo.sendKeys(accountList.get(0));
			accountList.remove(0);
		}
		else {
		applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
		od.parties_paymentTo.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Account Number / IBAN"));
		}
		
		scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine1);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryAddressLine1, Duration.ofSeconds(5));
		od.parties_Accounts_beneficiaryAddressLine1.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Address Line 1"));
		

		scroll.scrollInToView(od.parties_BeneficiaryCountry);
		applyExplicitWaitsUntilElementClickable(od.parties_BeneficiaryCountry, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_BeneficiaryCountry,
				externalData.getFieldData(TSID, "Party", "Beneficiary Country"));
		
		if(externalData.getFieldData(TSID, "Party", "Is BAC").equals("Y")) {
			scroll.scrollInToView(od.parties_BeneficiaryAccountKey);
			applyExplicitWaitsUntilElementClickable(od.parties_BeneficiaryAccountKey, Duration.ofSeconds(5));
			od.parties_BeneficiaryAccountKey.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Account Key"));
		}
		
		scroll.scrollInToView(od.parties_Accounts_beneficiaryCountryOfIncorporation);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryCountryOfIncorporation, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_Accounts_beneficiaryCountryOfIncorporation,
				externalData.getFieldData(TSID, "Party", "Beneficiary Country Of Incorporation"));
		
		scroll.scrollInToView(od.parties_Accounts_beneficiaryCurrency);
		applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryCurrency, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(od.parties_Accounts_beneficiaryCurrency,externalData.getFieldData(TSID, "Party", "Beneficiary Currency"));
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementClickable(od.parties_partyAccountsAddButton, Duration.ofSeconds(7));
		jsClick.click(od.parties_partyAccountsAddButton);
		
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

public void handle_LT_IN_PaymentInstrument(String TSID) throws Exception {
	scroll.scrollInToView(od.parties_PaymentSystem_LT_IN);
	applyExplicitWaitsUntilElementClickable(od.parties_PaymentSystem_LT_IN, Duration.ofSeconds(5));
	Thread.sleep(2000);
	od.parties_PaymentSystem_LT_IN.click();
	
	od.parties_Accounts_beneficiaryBankIfscCode.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary bank IFSC code"));
	od.parties_Accounts_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Name"));
  
	scroll.scrollInToView(od.parties_Accounts_accountOrIban);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_accountOrIban, Duration.ofSeconds(5));
	dropdown.selectByVisibleText(od.parties_Accounts_accountOrIban,externalData.getFieldData(TSID, "Party", "Select Account/IBAN"));
	
	scroll.scrollInToView(od.parties_paymentTo);
	applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
	od.parties_paymentTo.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Account Number / IBAN"));
	
	scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine1);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryAddressLine1, Duration.ofSeconds(5));
	od.parties_Accounts_beneficiaryAddressLine1.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Address Line 1"));
	

	scroll.scrollInToView(od.parties_BeneficiaryCountry);
	applyExplicitWaitsUntilElementClickable(od.parties_BeneficiaryCountry, Duration.ofSeconds(5));
	dropdown.selectByVisibleText(od.parties_BeneficiaryCountry,
			externalData.getFieldData(TSID, "Party", "Beneficiary Country"));
	
	
	scroll.scrollInToView(od.parties_Accounts_beneficiaryCountryOfIncorporation);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryCountryOfIncorporation, Duration.ofSeconds(5));
	System.out.println("Beneficiary Country Of Incorporation"+externalData.getFieldData(TSID, "Party", "Beneficiary Country Of Incorporation"));
	dropdown.selectByVisibleText(od.parties_Accounts_beneficiaryCountryOfIncorporation,
			externalData.getFieldData(TSID, "Party", "Beneficiary Country Of Incorporation"));

	Thread.sleep(1000);
	applyExplicitWaitsUntilElementClickable(od.parties_partyAccountsAddButton, Duration.ofSeconds(7));
	jsClick.click(od.parties_partyAccountsAddButton);
	
}
public void handle_SC_Payment_Profile_PaymentInstrument(String TSID) throws Exception {
	scroll.scrollInToView(od.party_SC_PaymentProfile);
	applyExplicitWaitsUntilElementClickable(od.party_SC_PaymentProfile, Duration.ofSeconds(15));
	Thread.sleep(2000);
	od.party_SC_PaymentProfile.click();
	
	od.parties_Accounts_beneficiaryName.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Name"));
  
	scroll.scrollInToView(od.parties_Accounts_Type);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_Type, Duration.ofSeconds(5));
	dropdown.selectByVisibleText(od.parties_Accounts_Type,externalData.getFieldData(TSID, "Party", "Account Type"));
	
	scroll.scrollInToView(od.parties_paymentTo);
	applyExplicitWaitsUntilElementClickable(od.parties_paymentTo, Duration.ofSeconds(5));
	od.parties_paymentTo.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Account Number / IBAN"));
	
	
	scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine1);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryAddressLine1, Duration.ofSeconds(5));
	od.parties_Accounts_beneficiaryAddressLine1.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Address Line 1"));
	
	scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine2);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryAddressLine2, Duration.ofSeconds(5));
	od.parties_Accounts_beneficiaryAddressLine2.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Address Line 2"));
	
	scroll.scrollInToView(od.parties_SC_Payment_beneficiaryCountryOfIncorporation);
	applyExplicitWaitsUntilElementClickable(od.parties_SC_Payment_beneficiaryCountryOfIncorporation, Duration.ofSeconds(5));
	od.parties_SC_Payment_beneficiaryCountryOfIncorporation.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Country Of Incorporation"));
	
	scroll.scrollInToView(od.parties_Accounts_beneficiaryCurrency);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryCurrency, Duration.ofSeconds(5));
	dropdown.selectByVisibleText(od.parties_Accounts_beneficiaryCurrency,externalData.getFieldData(TSID, "Party", "Beneficiary Currency"));
	
	
	scroll.scrollInToView(od.parties_Accounts_beneficiaryBankCode);
	applyExplicitWaitsUntilElementClickable(od.parties_Accounts_beneficiaryBankCode, Duration.ofSeconds(5));
	od.parties_Accounts_beneficiaryBankCode.sendKeys(externalData.getFieldData(TSID, "Party", "Beneficiary Bank Code"));
	
	scroll.scrollInToView(od.parties_BeneficiaryCountry);
	applyExplicitWaitsUntilElementClickable(od.parties_BeneficiaryCountry, Duration.ofSeconds(15));
	dropdown.selectByVisibleText(od.parties_BeneficiaryCountry,
			externalData.getFieldData(TSID, "Party", "Beneficiary Country"));
	
	if (commonutils.isElementDisplayed(od.payment_account_test1, 1)) {
		scroll.scrollInToView(od.payment_account_test1);
		Thread.sleep(500);
		od.payment_account_test1.sendKeys("test1");
	}

	Thread.sleep(1000);
	applyExplicitWaitsUntilElementClickable(od.parties_partyAccountsAddButton, Duration.ofSeconds(7));
	jsClick.click(od.parties_partyAccountsAddButton);
	
}
}
