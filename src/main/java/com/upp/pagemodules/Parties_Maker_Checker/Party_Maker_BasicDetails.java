package com.upp.pagemodules.Parties_Maker_Checker;



import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;
import com.upp.pageobjects.Object_Transactions;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import freemarker.template.utility.DateUtil;

import java.text.SimpleDateFormat;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import callbackInterfaces.ICallback;
public class Party_Maker_BasicDetails extends BaseClass {

	public static Object_NewDeal od;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;
	public static int rowNum;
	public static OdpApi odpAccount;
	public static AccountDetails accDetails;
	DateUtils dateTime = new DateUtils();
	public static JavascriptClick jsClick;
	public static int waitingTime = 5;
	public static DateUtils dateutil;
	public static ScrollTypes scroll;
	public static String productName;
	public static Object_Parties op;
	DealPartiesHandler partyHandler = new DealPartiesHandler();
	public Party_Maker_BasicDetails() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		op=new Object_Parties();

	}

	
	public void PartyMaker_BasicDetails(String TSID) throws Exception
	{
		applyExplicitWaitsUntilElementClickable(op.parties_Icon,Duration.ofSeconds(30));
		scroll.scrollInToView(op.parties_Icon);
		jsClick.click(op.parties_Icon);
		applyExplicitWaitsUntilElementClickable(op.partyMaker_Icon,Duration.ofSeconds(20));
		scroll.scrollInToView(op.partyMaker_Icon);
		jsClick.click(op.partyMaker_Icon);
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_AddNewButon,Duration.ofSeconds(15));
		op.PartyMaker_AddNewButon.click();
		applyExplicitWaitsUntilElementVisible(op.PartyMaker_internalOrExternal,Duration.ofSeconds(10));
		String internalOrExternal=op.PartyMaker_internalOrExternal.getText();
		if(internalOrExternal.equalsIgnoreCase("Internal")) {
			Thread.sleep(2000);
		op.PartyMaker_internal_slider.click();
		}
		
		 long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		 String accno = Long.toString(number);
		 
		
		String customerID=externalData.getFieldData(TSID, "Parties-Maker", "Customer Id");
		String UniquecustomerID=customerID.concat(accno);
		String partyName=externalData.getFieldData(TSID, "Parties-Maker", "Party Name");
		String UniquepartyName=partyName.concat(accno);
		
		applyExplicitWaitsUntilElementVisible(op.PartyMaker_customerId,Duration.ofSeconds(5));
		op.PartyMaker_customerId.sendKeys(UniquecustomerID);
		op.PartyMaker_partyName.sendKeys( UniquepartyName);
		
		 String ProcessingUnits=externalData.getFieldData(TSID,"Parties-Maker","Processing Units");
				 od.deals_ProcessingUnits.click();
				 op.PartyMaker_PUSearch.sendKeys(ProcessingUnits);
				 By ProcessingUnit = By.xpath("//div[contains(text(),'"+ProcessingUnits+"')]");
				 driver.findElement(ProcessingUnit).click();
		op.PartyMaker_partyRemarks.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Remarks"));
	    dropdown.selectByVisibleText(op.PartyMaker_country,externalData.getFieldData(TSID, "Parties-Maker", "Country"));
	    applyExplicitWaitsUntilElementClickable(op.PartyMaker_party_nextButton,Duration.ofSeconds(5));
	    jsClick.click(op.PartyMaker_party_nextButton);
	    
	}
}
