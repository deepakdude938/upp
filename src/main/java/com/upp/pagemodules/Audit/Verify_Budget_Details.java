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

public class Verify_Budget_Details extends BaseClass {

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

	public Verify_Budget_Details() {
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

	public void verify_budget_details_In_AuditTab(String dealId,String TSID) throws Exception {
		
        scroll.scrollInToView(audit.Audit_Icon);
		applyExplicitWaitsUntilElementClickable(audit.Audit_Icon, Duration.ofSeconds(20));
		try {
			audit.Audit_Icon.click();
		}
		catch(Exception e) {
			handleElementClickException(audit.Audit_Icon);
		}
		try {
			audit.Audit_Budget.click();
		}
		catch(Exception e) {
			handleElementClickException(audit.Audit_Budget);
		}
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(audit.Budget_DealId, Duration.ofSeconds(10));
		audit.Budget_DealId.sendKeys(dealId);
		
		By DealId = By.xpath("//div[contains(text(),'"+dealId+"')]");
		applyExplicitWaitsUntilElementVisible(DealId, 3);
	    driver.findElement(DealId).click();
	    
	    dropdown.selectByVisibleText(audit.Budget_budgetDropdown,TSID);
	    audit.Budget_submitButton.click();
	    Thread.sleep(1500);
	    js.click(audit.Budget_Total_Allocated_Budget);
	    audit.Budget_Total_Allocated_Budget.click();
	   
	    applyExplicitWaitsUntilElementClickable(audit.Budget_BasicDetails, Duration.ofSeconds(10));
	    audit.Budget_BasicDetails.click();
	    
	    String interval=audit.Budget_Monthly.getText();		
		String Total_Budget_Allocated=audit.Budget_Total_Budget_Allocated_Amount.getText();
		String Amount_Allocated=audit.Budget_Amount_Allocated.getText();
	    
		Assert.assertEquals(interval,externalData.getFieldData(TSID,"Audit","Budget Interval"));
		Assert.assertEquals(Total_Budget_Allocated,externalData.getFieldData(TSID,"Audit","Total Budget Allocated"));
		Assert.assertEquals(Amount_Allocated,externalData.getFieldData(TSID,"Audit","Amount Allocated"));
	    
	    
	    
	}

}
