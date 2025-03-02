package com.upp.pagemodules.document;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Document;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;
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

public class AddDealWithDocument extends BaseClass {

	public static Object_Document od;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;

	public AddDealWithDocument() {

		od = new Object_Document();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);

	}

	public void addDocuments(String TSID, ICallback icallback) throws Exception {
		String documents = externalData.getFieldData(TSID, "Document", "Documents");
		if (documents.equalsIgnoreCase("Required Documents")) {
			addDealWithRequiredDocument(TSID, icallback);
		}
	}

	public void addDealWithRequiredDocument(String TSID, ICallback icallback) throws Exception {
		String docType = "Blueprint";
		od.documentTab.click();
		Thread.sleep(2000);
		try {
		od.requiredDoc_addBtn.click();
		}
		catch(Exception e ) {
			handleElementClickException(od.requiredDoc_addBtn);
		}
		String documentType = externalData.getFieldData(TSID, "Document", "Document Type");
		icallback.handleCallback("Document_Type", documentType);
	}

	public void scheduleReminder(String TSID) throws Exception {
		applyExplicitWaitsUntilElementClickable(od.requiredDoc_schedule, Duration.ofSeconds(20));
		try {
		od.requiredDoc_schedule.click();
		}
		catch(Exception po) {
			handleElementClickException(od.requiredDoc_schedule);
		}
		applyExplicitWaitsUntilElementClickable(od.requiredDoc_scheduleAtEod, Duration.ofSeconds(20));
		od.requiredDoc_scheduleAtEod.click();		
		od.requiredDoc_frequencyOnce.click();
		od.requiredDoc_startDateCalenderIcon.click();
		od.requiredDoc_todaysDate.click();
		od.requiredDoc_reminderDays.click();
		od.requiredDoc_repeat.sendKeys("1");
		od.requiredDoc_scheduleBtn.click();
		Thread.sleep(3000);
		String reminderdate = od.requiredDoc_reminderDate.getText();
		System.out.println(reminderdate);
		// Assert.assertNotEquals(reminderdate,"");
	}

}
