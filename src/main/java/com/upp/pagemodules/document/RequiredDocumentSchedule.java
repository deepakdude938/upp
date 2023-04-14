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

public class RequiredDocumentSchedule extends BaseClass {

	public static Object_Document od;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;

	public RequiredDocumentSchedule() {

		od = new Object_Document();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);

	}

	public void createWorkItem(String dealid) {
		od.DocumentTracker.click();
		od.requiredDoc_requiredDocSchedule.click();
		od.requiredDoc_dealId.sendKeys(dealid);
		od.requiredDoc_createItem.click();
		od.requiredDoc_OkBtn.click();
	}	
}
