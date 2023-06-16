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
import com.upp.stepdefinition.TS06;
import com.upp.utils.CommonUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

import com.upp.utils.DateUtils;

public class Verify_Audit_Transaction_ExcelReport extends BaseClass {

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

	public Verify_Audit_Transaction_ExcelReport() {
		od = new Object_NewDeal();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
		op = new Object_Parties();
		audit = new Object_Audit();
		externalData = new ExcelReader();
	}

	public void verify_Audit_Transaction_Excel_Report(String dealId,String instruction_version) throws Exception {
		System.out.println("th change in excel path"+externalData.getFieldData("TS50", "Party", "Responsibility"));
		String excelname=dealId+"_AuditReport.xlsx";
		String path=System.getProperty("user.dir")+"\\downloadedFiles\\"+excelname;
		System.out.println("The path is:"+path);
		externalData = new ExcelReader();
		ExcelReader.excelFilePath=path;
		System.out.println("Instruction version:"+instruction_version);
		String amount=externalData.getFieldData(instruction_version, "Transaction Instruction", "NAME");
		String instructionId=externalData.getFieldData(instruction_version, "Transaction Sub Instruction", "CURRENCY");
		System.out.println("amount in Audit Report"+amount);
		System.out.println("instructionId in Audit Report"+instructionId);
		ExcelReader.excelFilePath=System.getProperty("user.dir")+"\\src\\main\\resources\\upp-automation-testdata.xlsx";
		System.out.println("th change in excel path"+externalData.getFieldData("TS50", "Party", "Responsibility"));
		

	}

}
