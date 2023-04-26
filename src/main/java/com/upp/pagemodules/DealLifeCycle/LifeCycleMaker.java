package com.upp.pagemodules.DealLifeCycle;

import java.time.Duration;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_DealLifecycle;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.CommonUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import com.upp.utils.DateUtils;

public class LifeCycleMaker extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static ScrollTypes scroll;
	public static CommonUtils commonutils;
	public JavascriptClick js;
	public static DateUtils dateutil;
	Object_DealLifecycle dl;

	public LifeCycleMaker() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
		dl = new Object_DealLifecycle();
	}

	public void CreateLifecycleMakerWorkitem(String dealId, String TSID) throws Exception {
		applyExplicitWaitsUntilElementClickable(dl.Deal_Lifecycle, Duration.ofSeconds(5));
		dl.Deal_Lifecycle.click();
		applyExplicitWaitsUntilElementClickable(dl.LifecycleMakerIcon, Duration.ofSeconds(5));
		dl.LifecycleMakerIcon.click();
		applyExplicitWaitsUntilElementClickable(dl.LifecycleMaker_Dealid, Duration.ofSeconds(5));
		dl.LifecycleMaker_Dealid.sendKeys(dealId);
		Thread.sleep(1000);
		js.click(dl.LifecycleMaker_submit);
		Thread.sleep(1000);
		js.click(dl.LifecycleMaker_plusIcon);
		applyExplicitWaitsUntilElementClickable(dl.LifecycleMaker_DocumentType, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(dl.LifecycleMaker_DocumentType,
				externalData.getFieldData(TSID, "Deal Lifecycle", "Document Type"));
		applyExplicitWaitsUntilElementClickable(dl.LifecycleMaker_DocumentNature, Duration.ofSeconds(5));
		dropdown.selectByVisibleText(dl.LifecycleMaker_DocumentNature,
				externalData.getFieldData(TSID, "Deal Lifecycle", "Document Nature"));
		applyExplicitWaitsUntilElementClickable(dl.LifecycleMaker_ADDButton, Duration.ofSeconds(5));
		dl.LifecycleMaker_ADDButton.click();
		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\upp-automation-testdata.xlsx";
		dl.LifecycleMaker_FileInput.sendKeys(filePath);
        scroll.scrollInToView(dl.LifecycleMaker_SubmitButton);
		applyExplicitWaitsUntilElementClickable(dl.LifecycleMaker_SubmitButton, Duration.ofSeconds(5));
		dl.LifecycleMaker_SubmitButton.click();
		applyExplicitWaitsUntilElementClickable(dl.Yes_Icon, Duration.ofSeconds(5));
		dl.Yes_Icon.click();

	}

}
