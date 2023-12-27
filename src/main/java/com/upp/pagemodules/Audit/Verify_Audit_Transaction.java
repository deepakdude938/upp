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

public class Verify_Audit_Transaction extends BaseClass {

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

	public Verify_Audit_Transaction() {
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

	public void verify_Audit_Transaction(String dealId) throws Exception {

		Thread.sleep(10000);
		scroll.scrollInToView(audit.Audit_Icon);
		applyExplicitWaitsUntilElementClickable(audit.Audit_Icon, Duration.ofSeconds(50));
		try {
			audit.Audit_Icon.click();
		} catch (Exception e) {
			handleElementClickException(audit.Audit_Icon);
		}
		scroll.scrollInToView(audit.audit_TransactionIcon);
		applyExplicitWaitsUntilElementClickable(audit.audit_TransactionIcon, Duration.ofSeconds(10));
		try {
			audit.audit_TransactionIcon.click();
		} catch (Exception e) {
			handleElementClickException(audit.audit_TransactionIcon);
		}
		applyExplicitWaitsUntilElementClickable(audit.audit_Transaction_DealId, Duration.ofSeconds(5));
		audit.audit_Transaction_DealId.sendKeys(dealId);
		By DealId = By.xpath("//div[contains(text(),'"+dealId+"')]");
		applyExplicitWaitsUntilElementVisible(DealId, 3);
	    driver.findElement(DealId).click();
	    applyExplicitWaitsUntilElementClickable(audit.audit_Transaction_Submit, Duration.ofSeconds(5));
		js.click(audit.audit_Transaction_Submit);
		applyExplicitWaitsUntilElementClickable(audit.audit_Transaction_Ok, Duration.ofSeconds(25));
		audit.audit_Transaction_Ok.click();
		Thread.sleep(30000);
		js.click(audit.audit_Transaction_Notification);
		applyExplicitWaitsUntilElementClickable(audit.audit_Transaction_Reload, Duration.ofSeconds(25));
		js.click(audit.audit_Transaction_Reload);
        Thread.sleep(16000);
        applyExplicitWaitsUntilElementClickable(audit.audit_Transaction_Submit, Duration.ofSeconds(5));
      		js.click(audit.audit_Transaction_Submit);
		/*
		 * System.out.println("The dealid is:"+dealId); By Report =
		 * By.xpath("//span[contains(text(),'" + dealId + "')]"); try {
		 * applyExplicitWaitsUntilElementVisible(Report,40);
		 * driver.findElement(Report).click(); } catch (Exception e) {
		 * handleElementClickException(driver.findElement(Report)); }
		 */
		Thread.sleep(15000);

	}

}
