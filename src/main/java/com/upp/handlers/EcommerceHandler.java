package com.upp.handlers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_Deal;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class EcommerceHandler extends BaseClass {
	public static Object_Deal od;
	DropDown dropdown;
	public ExcelReader externalData;
	public static JavascriptClick jsClick;
	public static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	public static final int RANDOM_STRING_LENGTH = 10;
	ScrollTypes scroll;
	public static CommonUtils commonutils;

	public EcommerceHandler() {
		od = new Object_Deal();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
	}

	public void handleEcommerce(String TSID) throws Exception {
		String debitorFalg;
		new Actions(driver).moveToElement(od.parties_eCommerceCheckbox);
		od.parties_eCommerceCheckbox.click();
		StringBuffer StrBuffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = random.nextInt(CHAR_LIST.length());
			;
			char ch = CHAR_LIST.charAt(number);
			StrBuffer.append(ch);
		}
		String PraticipantId = externalData.getFieldData(TSID, "Party", "Participant Id");
		System.out.println(PraticipantId);
		od.parties_ParticipantId.sendKeys(PraticipantId);

		od.parties_BasicNextButton.click();
		Thread.sleep(1000);
		if(	commonutils.isElementDisplayed(od.parties_ConflictMessage, 10)) {
			click(od.parties_ConflictMessage_CreateNew);
		}
		
		dropdown.selectByVisibleText(od.ecommerce_status, externalData.getFieldData(TSID, "Party", "Party_Status"));

		// od.ecommerce_validFrom.click();
//		od.startDate.click();

		// String status=externalData.getFieldData(TSID,"Party","Party_Status");
		// dropdown.selectByVisibleText(od.ecommerce_status, status);
		// od.ecommerce_validFrom.click();
		// od.startDate.click();
		debitorFalg = externalData.getFieldData(TSID, "Party", "Debit Accounts");
		System.out.println(debitorFalg);

		if (debitorFalg.equalsIgnoreCase("Yes") || debitorFalg.equalsIgnoreCase("Y")) {

			if (TSID.equals("TS78") || TSID.equals("TS76")) {
				Thread.sleep(5000);
				WebElement s = driver.findElement(By.xpath("//span[@title='" + virtual_Account_Number + "']/../.."));
				s.click();
			}
			if (TSID.equals("TS119")) {
				Thread.sleep(5000);
				String accountnumber1 = DealPage.AccountNo1;
				String accountnumber2 = DealPage.AccountNo2;
				System.out.println("The Second account number is" + accountnumber2);
//				WebElement s = driver.findElement(By.xpath("//span[@title='"+accountnumber2+"']/../.."));
				WebElement s = driver
						.findElement(By.xpath("//span[normalize-space()='" + accountnumber2 + "']/../..//input"));

				s.click();

				System.out.println("The First account number is" + accountnumber1);
//				WebElement s2 = driver.findElement(By.xpath("//span[@title='"+accountnumber1+"']/../.."));
				WebElement s2 = driver
						.findElement(By.xpath("//span[normalize-space()='" + accountnumber1 + "']/../..//input"));

				s2.click();
			} else if (TSID.equalsIgnoreCase("TS121")) {
				jsClick.click(od.ecommerceFirstAccount);
				jsClick.click(od.ecommerceSecondAccount);
			} else {
				String hiddenClass = od.accountNumbers.getAttribute("class");
				System.out.println(hiddenClass);
				if (!(hiddenClass.contains("ag-hidden"))) {
					Thread.sleep(500);
					debitAccount = od.ecommerceFirstAccountNo.getText();
					System.out.println("Debit account is " + debitAccount);
					jsClick.click(od.ecommerceFirstAccount);
					Thread.sleep(2500);
					System.out.println("First = " + od.accountNumbers.getAttribute("class"));
				} else {
					System.out.println(od.ecommerceSecondAccount.isSelected());
					{
						jsClick.click(od.ecommerceSecondAccount);
					}
					Thread.sleep(2500);
				}
			}
		}

		// od.ecommerce_validTill.click();
		// od.endDate.click();
		if (TSID.equalsIgnoreCase("TS08")) {
			od.exePolicies.click();
			od.exePoliciesdropdown.sendKeys(" Hold Execution(s) ");
			od.exePoliciesdropdown.sendKeys(Keys.ENTER);
			dropdown.deSelectByVisibleText(od.exePoliciesdropdown, " Hold Execution(s) ");
		}
		scroll.scrollInToView(od.ecommerceSave);
		Thread.sleep(1500);
		jsClick.click(od.ecommerceSave);
		System.out.println("save button click");
		Thread.sleep(2000);

	}

}
