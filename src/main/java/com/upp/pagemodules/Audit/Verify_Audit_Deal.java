package com.upp.pagemodules.Audit;

import java.time.Duration;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
import com.upp.pageobjects.Object_Audit;
import com.upp.pageobjects.Object_DealLifecycle;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;
import com.upp.utils.CommonUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

import com.upp.utils.DateUtils;

public class Verify_Audit_Deal extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public JavascriptClick js;
	public static DateUtils dateutil;
	Object_Parties op;
	Object_Audit audit;

	public Verify_Audit_Deal() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
		op = new Object_Parties();
		audit = new Object_Audit();
	}

	public void verify_Audit_Tab_Update_Log(String dealId) throws Exception {
		
        scroll.scrollInToView(audit.Audit_Icon);
		applyExplicitWaitsUntilElementClickable(audit.Audit_Icon, Duration.ofSeconds(50));
		try {
			audit.Audit_Icon.click();
		}
		catch(Exception e) {
			handleElementClickException(audit.Audit_Icon);
		}
		
		applyExplicitWaitsUntilElementClickable(audit.Audit_Deals, Duration.ofSeconds(5));
		audit.Audit_Deals.click();
		applyExplicitWaitsUntilElementClickable(audit.Audit_DealId, Duration.ofSeconds(5));
		audit.Audit_DealId.sendKeys(dealId);
		Thread.sleep(2000);
		jsClick.click(audit.Audit_Deal_Party_icon);
		Thread.sleep(4000);
		if (!(commonutils.isElementDisplayed(audit.Audit_DealPartyaccountsadded, 1))) {
			Assert.fail("party added not updated in log");
		}

		if (!(commonutils.isElementDisplayed(audit.Audit_DealPartyaccountsadded, 1))) {
			Assert.fail("party accounts not added  updated in log");
		}
		
		if (!(commonutils.isElementDisplayed(audit.Audit_Deal_contacts_added, 1))) {
			Assert.fail("party contacts not added  updated in log");
		}
		if (!(commonutils.isElementDisplayed(audit.Audit_Deal_documents_added, 1))) {
			Assert.fail("party documents notadded  updated in log");
		}
	}

}
