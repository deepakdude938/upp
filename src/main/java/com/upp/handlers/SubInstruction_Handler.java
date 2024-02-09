package com.upp.handlers;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;

public class SubInstruction_Handler extends BaseClass{
	
	public static Object_NewDeal od;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	
		public SubInstruction_Handler() {
			od = new Object_NewDeal();
			externalData = new ExcelReader();
			dropdown = new DropDown(driver);
		}

		public void handleFrequency_Days() throws Exception {
			od.payment_repeatEvery.clear();
			od.payment_repeatEvery.sendKeys((int)Double.parseDouble(externalData. getFieldData(tsid, "Scheduled", "Repeat Every"))+"");
		}

		public void handleFrequency_Weekly() throws Exception, InvalidFormatException, IOException {
			od.payment_repeatEvery.clear();
			od.payment_repeatEvery.sendKeys((int)Double.parseDouble(externalData. getFieldData(tsid, "Scheduled", "Repeat Every"))+"");
			String daysOfWeek = externalData. getFieldData(tsid, "Scheduled", "Days of week");
			System.out.println(daysOfWeek);
			String[] daysArray = daysOfWeek.replaceAll("\\s+", "").split(",");
			for (String day : daysArray) {
	            System.out.println(day);
	            WebElement day1 = driver.findElement(By.xpath("//div[@id='grid-instructions-weekSelect-list-v1']//div[normalize-space()='"+day+"']"));
	            click(day1);
			}
		}

		public void handleFrequency_Monthly() throws Exception, InvalidFormatException, IOException {
			
			od.payment_repeatEvery.clear();
			od.payment_repeatEvery.sendKeys((int)Double.parseDouble(externalData. getFieldData(tsid, "Scheduled", "Repeat Every"))+"");
			dropdown.selectByVisibleText(od.payment_subType, externalData. getFieldData(tsid, "Scheduled", "SubType"));
			dropdown.selectByVisibleText(od.payment_week,(int)Double.parseDouble(externalData. getFieldData(tsid, "Scheduled", "Week"))+"");
			System.out.println(externalData. getFieldData(tsid, "Scheduled", "Week")+"");
			String daysOfWeek = externalData. getFieldData(tsid, "Scheduled", "Days of week");
			System.out.println(daysOfWeek);
			String[] daysArray = daysOfWeek.replaceAll("\\s+", "").split(",");
			for (String day : daysArray) {
	            System.out.println(day);
	            WebElement day1 = driver.findElement(By.xpath("//div[@id='grid-instructions-weekSelect-list-v1']//div[normalize-space()='"+day+"']"));
	            click(day1);
			}
		}
}
