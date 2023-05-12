package com.upp.pagemodules.Transactions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
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

public class Transactions_Maker_Bulkupload extends BaseClass {

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

	public Transactions_Maker_Bulkupload() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		tm = new Object_Transactions();

	}

	public void bulkUpload(String srcAcc, String desAcc) throws Exception{
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniqueTransactionName = "tran" + random;
		String uniqueTransactionName2 = "transaction1" + random;
		String excelFilePath = System.getProperty("user.dir")
				+ "\\src\\main\\resources\\Bulk_ConventionalTransactions_File.xlsx";
		externalData.writeDataToExcel(excelFilePath, "Sheet",1,"transactionName", uniqueTransactionName);
		externalData.writeDataToExcel(excelFilePath, "Sheet",2,"transactionName", uniqueTransactionName2);
		externalData.writeDataToExcel(excelFilePath, "Sheet",1,"sourceAccount", "6543234");
		externalData.writeDataToExcel(excelFilePath, "Sheet",2,"sourceAccount", "76567894");
	}

}
