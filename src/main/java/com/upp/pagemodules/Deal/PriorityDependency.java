package com.upp.pagemodules.Deal;

import java.time.Duration;
import com.upp.base.BaseClass;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

public class PriorityDependency extends BaseClass {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static JavascriptClick jsClick;
	public static DateUtils dateutil;
	public static ScrollTypes scroll;

	public PriorityDependency() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
	}

	public void addSameDayDependency() throws Exception {
		applyExplicitWaitsUntilElementClickable(od.PriorityDependencyIcon, Duration.ofSeconds(10));
		od.PriorityDependencyIcon.click();
		applyExplicitWaitsUntilElementClickable(od.Priority_EditIcon, Duration.ofSeconds(5));
		od.Priority_EditIcon.click();
		applyExplicitWaitsUntilElementClickable(od.Priority_Select_Checkbox, Duration.ofSeconds(5));
		od.Priority_Select_Checkbox.click();
		scroll.scrollInToView(od.Priority_SameDay);
		Thread.sleep(1500);
		jsClick.click(od.Priority_SameDay);
		applyExplicitWaitsUntilElementClickable(od.Priority_SaveButton, Duration.ofSeconds(5));
		od.Priority_SaveButton.click();

	}

}
