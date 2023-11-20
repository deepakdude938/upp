package com.upp.pagemodules.Transactions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Transactions;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import freemarker.template.utility.DateUtil;

import java.text.SimpleDateFormat;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import callbackInterfaces.ICallback;

public class Transactions_Maker_Sub_Instruction extends BaseClass {

	public static Object_NewDeal od;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int rowNum;
	public static OdpApi odpAccount;
	public static AccountDetails accDetails;
	DateUtils dateTime = new DateUtils();
	public static JavascriptClick jsClick;
	public static int waitingTime = 5;
	public static DateUtils dateutil;
	public static ScrollTypes scroll;
	public static String productName;
	public static Object_Transactions tm;
	CommonUtils util;

	public Transactions_Maker_Sub_Instruction() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		tm = new Object_Transactions();
		util = new CommonUtils(driver);

	}

	public void Non_Registered_Beneficiary(String toaccount) throws InterruptedException {
		int flag = 0;
		By toAccount;
		try {
			toAccount = By.xpath("//span[@class='truncate ng-star-inserted']");
			if (driver.findElement(toAccount).isDisplayed()) {
				flag = 1;
			}
		} catch (Exception e) {
			Thread.sleep(3000);
			toAccount = By.xpath("//span[@class='truncate ng-star-inserted']");
			if (driver.findElement(toAccount).isDisplayed()) {
				flag = 1;
			}

		}

		Assert.assertEquals(flag, 1);
		od.payments_NextArrowButtonTransferSubInstruction.click();
	}

	public void Transaction_Maker_Sub_Instruction(String TSID, ICallback icallback) throws Exception {
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(tm.transactions_Instrument, Duration.ofSeconds(15));
		if (!((TSID.equalsIgnoreCase("TS110")) || (TSID.equalsIgnoreCase("TS113")))) {
			tm.transactions_Instrument.click();

		} else {
			tm.transactions_Instrument_when_budget_purpose_enabled.click();
		}
		Thread.sleep(1000);
		if (!((TSID.equalsIgnoreCase("TS110")) || (TSID.equalsIgnoreCase("TS113")))) {
			tm.transactions_Instrument.click();
		} else {
			tm.transactions_Instrument_when_budget_purpose_enabled.click();
		}
		String paymentInstrumentdata = externalData.getFieldData(TSID, "Txn Maker", "Sub Instruction - Instrument");
		System.out.println("Excel data in payment = " + paymentInstrumentdata);
		By paymentInstrument = By.xpath("(//div[contains(text(),'" + paymentInstrumentdata + "')])[1]");
		applyExplicitWaitsUntilElementVisible(paymentInstrument, 15);
		driver.findElement(paymentInstrument).click();
		System.out.println("the to data is " + externalData.getFieldData(TSID, "Txn Maker", "to"));
		icallback.handleCallback("PAYMENT_INSTRUMENT", paymentInstrumentdata);
		Thread.sleep(3000);
		if (util.isElementDisplayed(od.deal_Edit_Yes_Button, 2)) {
			od.deal_Edit_Yes_Button.click();
		}
	}

	public void Transaction_Maker_Sub_InstructionPayment_Currency(String TSID, ICallback icallback) throws Exception {
		System.out.println(TSID);
		scroll.scrollInToView(tm.basicDetails);
		Thread.sleep(3000);
		jsClick.click(tm.SubInstructionArrow);
		Thread.sleep(3000);
		jsClick.click(tm.addSubInstructionArrow);
		Transaction_Maker_Sub_Instruction(TSID, icallback);
	}

	public void Transaction_Maker_Sub_InstructionPayment_Currency_Debit(String TSID, ICallback icallback)
			throws Exception {
		scroll.scrollInToView(tm.basicDetails);
		Thread.sleep(3000);
		jsClick.click(tm.SubInstructionArrow);
		Thread.sleep(3000);
		jsClick.click(tm.addSubInstructionArrow);
		Transaction_Maker_Sub_Instruction(TSID, icallback);
	}

	public void verifyDebitTotalCurrency() throws Exception {
		Thread.sleep(3000);
		scroll.scrollInToView(tm.basicDetails);
		Thread.sleep(3000);
		jsClick.click(tm.SubInstructionArrow);
		Thread.sleep(3000);
		jsClick.click(tm.addSubInstructionArrow);

		String debitCurreny = tm.debitCurrencyTotal.getText();
		String str = "₹160.00";
		String[] arrOfStr = debitCurreny.split("₹", 2);

		for (int i = 0; i < arrOfStr.length; i++) {
			// System.out.println(arrOfStr[0]);
			System.out.println(arrOfStr[1]);
			String str1 = arrOfStr[1];
			String[] arrOfStr1 = str1.split(".00");
			for (int j = 0; j < arrOfStr1.length; j++) {
				// System.out.println(arrOfStr[0]);
				System.out.println(arrOfStr1[0]);
				int num = Integer.parseInt(arrOfStr1[0]);
				Assert.assertEquals(num, 160);
			}

			String paymentCurrency = tm.paymentCurrencyTotal.getText();

			String[] arrofStr1 = paymentCurrency.split("₹", 2);

			for (int i1 = 0; i1 < arrofStr1.length; i1++) {
				// System.out.println(arrofStr1[0]);
				System.out.println(arrofStr1[1]);
				String str11 = arrofStr1[1];
				String[] arrofStr21 = str11.split(".00");
				for (int j1 = 0; j1 < arrofStr21.length; j1++) {
					// System.out.println(arrofStr1[0]);
					System.out.println(arrofStr21[0]);
					int num1 = Integer.parseInt(arrofStr21[0]);
					Assert.assertEquals(num1, 100);
				}
			}
//		System.out.println(tm.paymentCurrencyTotal.getText());
		}
	}

}
