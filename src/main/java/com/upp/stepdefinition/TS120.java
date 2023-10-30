package com.upp.stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Parties_Maker_Checker.Party_Maker_Accounts;
import com.upp.pageobjects.Object_Parties;
import com.upp.utils.ExcelReader;

import io.cucumber.java.en.Then;

public class TS120 extends BaseClass {
	Party_Maker_Accounts pma;
	String TSID;
	public static Object_Parties op;
	public static ExcelReader externalData;
	
	public TS120() {
		externalData = new ExcelReader();
		pma=new Party_Maker_Accounts();
		op=new Object_Parties();
	}
	
	@Then("Click on OK button")
	public void click_on_OK_button() throws MalformedURLException {
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_OKButton, Duration.ofSeconds(5));
		op.PartyMaker_OKButton.click();
	}

	@Then("Verify Account is created {string}")
	public void verify_Account_is_created(String TSID) throws InvalidFormatException, IOException {
		String accountNo=externalData.getFieldData(TSID, "Parties-Maker", "Beneficiary Account Number / IBAN");
		By a = By.xpath(" //div[normalize-space()='"+accountNo+"']/ancestor::tr");
		applyExplicitWaitsUntilElementVisible(a, 10);
	   WebElement accountRecord = driver.findElement(a);
	   boolean status = isWebElementDisplayed(accountRecord);
	   Assert.assertTrue(status);
	}

}
