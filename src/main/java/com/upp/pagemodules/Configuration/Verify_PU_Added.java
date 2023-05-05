package com.upp.pagemodules.Configuration;

import java.time.Duration;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_Configuration;
import com.upp.pageobjects.Object_DealLifecycle;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.CommonUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import com.upp.utils.DateUtils;

public class Verify_PU_Added extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public JavascriptClick js;
	public static DateUtils dateutil;
	Object_Configuration oc;

	public Verify_PU_Added() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
		oc = new Object_Configuration();

	}

	public void Verify_PU_Added_In_BasicDetailsPage(String TSID,String UniquePU) throws Exception {
      
		od.deal_SideMenuIcon.click();
		od.newDealButton.click();
		od.newDeal.sendKeys(externalData.getFieldData(TSID, "Basic Details", "Deal Name"));
		String productName = externalData.getFieldData(TSID, "Basic Details", "Product");

		if (!(productName.equals("1.0"))) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			dropdown.selectByVisibleText(od.deal_Product, productName);

		}
		dropdown.selectByVisibleText(od.businessSegmentDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Business Segment"));

		dropdown.selectByVisibleText(od.countryIndiaDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Country"));

		String input = externalData.getFieldData(TSID, "Basic Details", "Transactions to non-registered beneficiaries");
		if ((input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes"))) {
			od.beneficiariesCheckBox.click();
		}

		od.deals_ProcessingUnits.click();
		od.deals_selectAll.click();
		od.deals_ProcessingUnitsSearch.sendKeys(UniquePU);
		By ProcessingUnit = By.xpath("//div[contains(text(),'" + UniquePU + "')]");
		if(!(commonutils.isElementDisplayed(driver.findElement(ProcessingUnit), 2)))
		{
			Assert.fail("Processing unit is not displayed/available");
		}
		else
		{
			driver.findElement(ProcessingUnit).click();
			System.out.println("Processing unit is displayed/available");
		}
		

	}

}
