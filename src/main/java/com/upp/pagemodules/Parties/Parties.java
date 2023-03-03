package com.upp.pagemodules.Parties;

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
public class Parties extends BaseClass {

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
	public Parties() {

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

	
	public void createPartyFromPartyMaker(String TSID,ICallback icallback) throws Exception
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
		
		applyExplicitWaitsUntilElementVisible(op.PartyMaker_customerId,Duration.ofSeconds(5));
		
		op.PartyMaker_customerId.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Customer Id"));
		op.PartyMaker_partyName.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Party Name"));
		
		 String ProcessingUnits=externalData.getFieldData(TSID,"Parties-Maker","Processing Units");
				 od.deals_ProcessingUnits.click();
				 op.PartyMaker_PUSearch.sendKeys(ProcessingUnits);
				 By ProcessingUnit = By.xpath("//div[contains(text(),'"+ProcessingUnits+"')]");
				 driver.findElement(ProcessingUnit).click();
		op.PartyMaker_partyRemarks.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Remarks"));
	    dropdown.selectByVisibleText(op.PartyMaker_country,externalData.getFieldData(TSID, "Parties-Maker", "Country"));
	    applyExplicitWaitsUntilElementClickable(op.PartyMaker_party_nextButton,Duration.ofSeconds(5));
	    jsClick.click(op.PartyMaker_party_nextButton);
	    
	    applyExplicitWaitsUntilElementClickable( od.parties_AddContact,Duration.ofSeconds(10));
	    od.parties_AddContact.click();
		od.parties_ContactName.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Contact Name"));
		od.parties_Email.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Email"));
		od.parties_AddButton.click();
		
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_OKButton, Duration.ofSeconds(5));
		op.PartyMaker_OKButton.click();
		
		od.parties_AccountsTab.click();
		op.PartyMaker_partyAccountsAddButton.click();		
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_PaymentSystem, Duration.ofSeconds(5));
		op.PartyMaker_PaymentSystem.click();
		
		String paymentInstrumentdata=externalData.getFieldData(TSID, "Parties-Maker", "Accounts-Payment System");
		 By paymentInstrument = By.xpath("(//div[contains(text(),'"+paymentInstrumentdata+"')])[1]");
		 applyExplicitWaitsUntilElementVisible(paymentInstrument,3);
		 driver.findElement(paymentInstrument).click();
		 
		icallback.handleCallback("PARTIES_MAKER_PAYMENT_INSTRUMENT",paymentInstrumentdata);
		
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_OKButton, Duration.ofSeconds(5));
		op.PartyMaker_OKButton.click();
		
		od.parties_DocumentsTab.click();
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_AddDocument, Duration.ofSeconds(5));
		op.PartyMaker_AddDocument.click();
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_DocumentType, Duration.ofSeconds(5));
		op.PartyMaker_DocumentType.click();
		
		if (externalData.getFieldData(TSID, "Parties-Maker", "Document Type").equalsIgnoreCase("Blueprint")) {
			applyExplicitWaitsUntilElementClickable(od.parties_DocumentsType_Blueprint, Duration.ofSeconds(5));
			od.parties_DocumentsType_Blueprint.click();
			dropdown.selectByVisibleText(od.parties_DocumentNature1,
					externalData.getFieldData(TSID, "Parties-Maker", "Document Nature"));
			 op.PartyMaker_DocumentDescription.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Description"));
			od.parties_DocumentsAddButton.click();
		}

		if (externalData.getFieldData(TSID, "Parties-Maker", "Document Type").equalsIgnoreCase("Architect certificate")) {
			applyExplicitWaitsUntilElementClickable(od.parties_DocumentsType_Architect_certificate,
					Duration.ofSeconds(5));
			od.parties_DocumentsType_Architect_certificate.click();
			dropdown.selectByVisibleText(od.parties_DocumentNature1,
					externalData.getFieldData(TSID, "Parties-Maker", "Document Nature"));
			applyExplicitWaitsUntilElementClickable(od.payments_ExecutionDate, Duration.ofSeconds(5));
			od.payments_ExecutionDate.click();
			String day = dateutil.getDay();
			By excecutionDay = By.xpath("(//a[normalize-space()='"+day+"'])[1]");
			applyExplicitWaitsUntilElementVisible(excecutionDay, 5);
			driver.findElement(excecutionDay).click();
			op.PartyMaker_DocumentDescription.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Description"));
			od.parties_DocumentsAddButton.click();
		}
		
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_OKButton, Duration.ofSeconds(5));
		op.PartyMaker_OKButton.click();
		
		op.PartyMaker_SummaryTab.click();
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_SubmitButton, Duration.ofSeconds(5));
		op.PartyMaker_SubmitButton.click();
		applyExplicitWaitsUntilElementClickable(op.PartyMaker_YesButton, Duration.ofSeconds(10));
		op.PartyMaker_YesButton.click();
		Thread.sleep(5000);
	
	}
	
	
	public void approvePartyFromPartyChecker(String TSID) throws Exception
	{
		
		
		applyExplicitWaitsUntilElementClickable(op.parties_Icon,Duration.ofSeconds(30));
		op.parties_Icon.click();
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_Icon,Duration.ofSeconds(15));
		op.PartyChecker_Icon.click();
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_CustomerID_SearchBox,Duration.ofSeconds(20));
		op.PartyChecker_CustomerID_SearchBox.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Customer Id"));
		Thread.sleep(2000);
		op.PartyChecker_EditIcon.click();
		op.PartyMaker_SummaryTab.click();
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_AddComment,Duration.ofSeconds(10));
		op.PartyChecker_AddComment.sendKeys(externalData.getFieldData(TSID, "Parties-Checker", "Summary - Add your comments here"));
		op.PartyChecker_ApproveButton.click();
		op.PartyMaker_YesButton.click();
		Thread.sleep(4000);
		
	}
	
	public void add_Existing_Party_with_given_DealId(String TSID,String dealId) throws Exception
	{
		 applyExplicitWaitsUntilElementClickable(od.deal_SideMenuIcon,Duration.ofSeconds(15));
		 od.deal_SideMenuIcon.click();
		 applyExplicitWaitsUntilElementClickable(od.liveDealIcon,Duration.ofSeconds(15));
		 od.liveDealIcon.click();
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_searchSelect,Duration.ofSeconds(25));
		 dropdown.selectByVisibleText(od.dealChecker_searchSelect,"Deal Id");
		 applyExplicitWaitsUntilElementClickable(od.dealChecker_searchBar,Duration.ofSeconds(15));
		 od.dealChecker_searchBar.sendKeys(dealId);
		 Thread.sleep(4000);
		 od.dealChecker_searchButton.click();
		 Thread.sleep(3000);
		 applyExplicitWaitsUntilElementClickable( od.dealChecker_showMenu,Duration.ofSeconds(30));
		 od.dealChecker_showMenu.click();
		 applyExplicitWaitsUntilElementClickable(od.deal_EditIcon,Duration.ofSeconds(20));
		 od.deal_EditIcon.click();
		 applyExplicitWaitsUntilElementClickable(od.parties_icon,Duration.ofSeconds(10));
		 od.parties_icon.click();
		 applyExplicitWaitsUntilElementClickable(op.PartyMaker_addPartyPlusIcon,Duration.ofSeconds(10));
		 op.PartyMaker_addPartyPlusIcon.click();
		 partyHandler.handleLinkedExistingParty(TSID);

	}
	
	
	

}
