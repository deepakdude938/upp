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

public class DocumentChecker extends BaseClass {

	public static Object_Document od;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;

	public DocumentChecker() {

		od = new Object_Document();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);

	}

	public void manageDocument(String dealid) throws Exception {
		applyExplicitWaitsUntilElementClickable(od.requiredDoc_DocumentChecker, Duration.ofSeconds(20));
		od.requiredDoc_DocumentChecker.click();
		od.requiredDoc_dealId.sendKeys(dealid);
		applyExplicitWaitsUntilElementClickable(od.requiredDoc_manageDoc, Duration.ofSeconds(20));
		od.requiredDoc_manageDoc.click();
		od.requiredDoc_comment.sendKeys("ok approved");
		od.requiredDoc_approveBtn.click();
		od.requiredDoc_yesBtn.click();
	}

	public void verifyStatus(String dealid) throws Exception {
		Thread.sleep(4000);
		od.requiredDoc_requiredDocSchedule.click();
		Thread.sleep(4000);
		applyExplicitWaitsUntilElementClickable(od.requiredDoc_dealId, Duration.ofSeconds(20));
		od.requiredDoc_dealId.sendKeys(dealid);
		System.out.println(od.requiredDoc_docStatus.getText());
		Thread.sleep(2000);
		String status = od.requiredDoc_docStatus.getText();
		System.out.println(status);
		Assert.assertEquals(status, "Completed");
	}

}
