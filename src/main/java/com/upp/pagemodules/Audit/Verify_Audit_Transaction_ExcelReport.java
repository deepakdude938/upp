package com.upp.pagemodules.Audit;

import java.io.File;
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
import com.upp.stepdefinition.DealPage;
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
    private String audit_path="";
	public Verify_Audit_Transaction_ExcelReport() {
		od = new Object_NewDeal();
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		commonutils = new CommonUtils(driver);
		js = new JavascriptClick(driver);
		dateutil = new DateUtils();
		op = new Object_Parties();
		audit = new Object_Audit();
	}

	public void verify_Audit_Transaction_Excel_Report(String dealId,String action,String TSID) throws Exception {
		
		String excelname=dealId+"_AuditReport.xlsx";
		if (System.getProperty("os.name").equals("Linux")) {
			audit_path=System.getProperty("user.dir")+"//"+excelname;
			System.out.println("the audit path:"+audit_path);
			
			}
			else
			{
				audit_path= System.getProperty("user.dir")+"\\downloadedFiles\\"+excelname;
			}
		
		System.out.println("The path is:"+audit_path);
		externalData = new ExcelReader();
		String name=externalData.getFieldData_From_DownloadedExcel(audit_path,action,"Transaction Instruction", "NAME");
		String currency=externalData.getFieldData_From_DownloadedExcel(audit_path,action,"Transaction Instruction", "CURRENCY");
		System.out.println("name:"+name);
		System.out.println("currency"+currency);
		Assert.assertEquals(name,externalData.getFieldData(TSID,"Txn Maker","Name"));
		Assert.assertEquals(currency,externalData.getFieldData(TSID, "Party", "Beneficiary Currency"));
		Thread.sleep(1000);
		
		File file = new File(audit_path);
		if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
	}

}
