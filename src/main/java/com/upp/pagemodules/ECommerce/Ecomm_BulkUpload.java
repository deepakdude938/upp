package com.upp.pagemodules.ECommerce;

import java.time.Duration;

import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_Ecommerce;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Transactions;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;

public class Ecomm_BulkUpload extends BaseClass{
	public static Object_Ecommerce ecomm;
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	String excelFilePath;
	
	public Ecomm_BulkUpload() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		ecomm = new Object_Ecommerce();

	}

	public void bulkUploadEcommTransaction() throws Exception {
		
		ecomm.ecommerce_SideMenuIcon.click();
		ecomm.ecommerce_Txnmaker.click();
		Thread.sleep(2000);
		try {
		ecomm.ecommerce_bulkUpload.click();
	}
	catch(Exception e) {
		handleElementClickException(ecomm.ecommerce_bulkUpload);
	}
		ecomm.ecommerce_Browse.sendKeys(excelFilePath);
		dropdown.selectByVisibleText(ecomm.ecommerce_selectSheet, "Sheet");
		ecomm.ecommerce_UploadFileButton.click();
		ecomm.ecommerce_OkButton.click();
		driver.navigate().refresh();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Notifications, Duration.ofSeconds(20));
		try {
			ecomm.ecommerce_Notifications.click();
			}
			catch(Exception e) {
				handleElementClickException(ecomm.ecommerce_Notifications);
			}
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_NotificationMessage, Duration.ofSeconds(20));
		Thread.sleep(2000);
		ecomm.ecommerce_NotificationMessage.click();
		ecomm.ecommerce_NextButton.click();
		ecomm.ecommerce_AllRecordsCheckBox.click();
		ecomm.ecommerce_SubmitBtn.click();
		ecomm.ecommerce_ok.click();
		applyExplicitWaitsUntilElementClickable(ecomm.ecommerce_Notifications, Duration.ofSeconds(10));
		try {
		ecomm.ecommerce_Notifications.click();
		}
		catch(Exception e) {
			handleElementClickException(ecomm.ecommerce_Notifications);
		}
		applyExplicitWaitsUntilElementVisible(ecomm.ecommerce_NotificationCheckerMessage, Duration.ofSeconds(60));
	
	}

	public void updateExcelFile() throws Exception {
		String transactionName = "TS28EcommTransaction_"+java.util.UUID.randomUUID().toString().substring(0, 6);
		String transactionName1 = "TS28EcommTransaction_"+java.util.UUID.randomUUID().toString().substring(0, 6);
		 excelFilePath = System.getProperty("user.dir")
				+ "/src/main/resources/BulkEcomm.xlsx";
		String tomorrowDate = new DateUtils().getCurrentDate();
		externalData.writeDataToExcel(excelFilePath, "Sheet",1,"dealRefId", dealId);
		externalData.writeDataToExcel(excelFilePath, "Sheet",1,"transactionName", transactionName);
//		externalData.writeDataToExcel(excelFilePath, "Sheet",2,"dealRefId", dealId);
//		externalData.writeDataToExcel(excelFilePath, "Sheet",2,"transactionName", transactionName1);
		externalData.writeDataToExcel(excelFilePath, "Sheet",1,"executeOn", tomorrowDate);
//		externalData.writeDataToExcel(excelFilePath, "Sheet",2,"executeOn", tomorrowDate);
		
		
	}	
}
