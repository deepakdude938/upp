package com.upp.pagemodules.Deal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.upp.base.BaseClass;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;
import freemarker.template.utility.DateUtil;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.upp.utils.CommonUtils;

public class DealBasicDetailCreators extends BaseClass {

	public static Object_Deal od;
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
	public static CommonUtils commonutils;
	public static Object_NewDeal ond;
	
	public DealBasicDetailCreators() {

		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		commonutils = new CommonUtils(driver);
		ond = new Object_NewDeal();

	}

	public void createDealBasicDetails(String TSID, ICallback icallback) throws Exception {
		Thread.sleep(3000);
		click(od.deal_SideMenuIcon);
		click(od.newDealButton);
		od.newDeal.sendKeys(externalData.getFieldData(TSID, "Basic Details", "Deal Name")+"_"+DateUtils.getCurrentDateTime());

		productName = externalData.getFieldData(TSID, "Basic Details", "Product");

		if (!(productName.equals("1.0"))) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			if(TSID.equalsIgnoreCase("TS25")||TSID.equalsIgnoreCase("TS74")||TSID.equalsIgnoreCase("TS79")) {
				od.deal_Product.sendKeys(config_productName);
				dropdown.selectByVisibleText(od.deal_Product, config_productName);
			}
			else {
			od.deal_Product.sendKeys(productName);
			dropdown.selectByVisibleText(od.deal_Product, productName);
			}
			// dropdown.selectByValue(od.deal_Product, "T1142");
			icallback.handleCallback("PRODUCT_NAME", productName);
		}
		
		try {
			String endDate=externalData.getFieldData(TSID, "Basic Details", "Ends On");
				if(endDate.equalsIgnoreCase("Default Today")) {
					
				od.basicDetails_EndDate.click();
				String day = dateutil.getDay();
				By excecutionDay = By.xpath(
						"//td[contains(@class,today) and not(contains(@class,'ui-calendar-outFocus'))]//a[normalize-space()='"
								+ day + "']");
				applyExplicitWaitsUntilElementVisible(excecutionDay, 5);
				driver.findElement(excecutionDay).click();
				}
			}
			catch(NullPointerException e) {
				
				}
		dropdown.selectByVisibleText(od.businessSegmentDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Business Segment"));

		dropdown.selectByVisibleText(od.countryIndiaDropDown,
				externalData.getFieldData(TSID, "Basic Details", "Country"));

		String input = externalData.getFieldData(TSID, "Basic Details", "Transactions to non-registered beneficiaries");
		if ((input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes"))) {
			od.beneficiariesCheckBox.click();
		}

		String ProcessingUnits = externalData.getFieldData(TSID, "Basic Details", "Processing Units");
		if (ProcessingUnits.equalsIgnoreCase("Select All")) {
			try {
			applyExplicitWaitsUntilElementClickable(od.deals_ProcessingUnitsSelectedText, Duration.ofSeconds(30));
			String text = od.deals_ProcessingUnitsSelectedText.getText();
			System.out.println(text);
			if(!text.equals("All selected")) {
				od.deals_ProcessingUnits.click();
				Thread.sleep(1000);
				applyExplicitWaitsUntilElementClickable(od.deals_selectAll, Duration.ofSeconds(20));
				od.deals_selectAll.click();
			}
		}
		catch(Exception e) {
		}
		}
		else {
			od.deals_ProcessingUnits.click();
			applyExplicitWaitsUntilElementClickable(od.deals_ProcessingUnitsSearch, Duration.ofSeconds(5));
			od.deals_ProcessingUnitsSearch.sendKeys(ProcessingUnits);
			By ProcessingUnit = By.xpath("//div[contains(text(),'" + ProcessingUnits + "')]");
			driver.findElement(ProcessingUnit).click();
		}

		input = externalData.getFieldData(TSID, "Basic Details", "Transaction Categories");
		od.transactionCategory.click();
		od.transactionCategoryInput.sendKeys(input);
		By transaction_Category_Option = By.xpath("(//div[contains(text(),'" + input + "')])[1]");
		// applyExplicitWaitsUntilElementVisible(transaction_Category_Option, 10);
		driver.findElement(transaction_Category_Option).click();
		if (!od.basicDetails_SaveButton_List.isEmpty()) {
			od.saveButton.click();
		}
		input = externalData.getFieldData(TSID, "Basic Details", "Party Responsibilities");
		 String[] responsibilityArray = input.split(",");
		 ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(responsibilityArray));
		 
			 for(String responsibility:arrayList)
			 {
				 Thread.sleep(1000);
					od.partyResponsibility.click();
					Thread.sleep(1500);
				od.partyResponsibilityinput.sendKeys(responsibility);
				Thread.sleep(1000);
				By party_Responsibility_Option = By.xpath("//div[contains(text(),'" + responsibility + "')]");
				try {
				applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 5);
				}
				catch(Exception e) {
					od.partyResponsibilityinput.clear();
					od.partyResponsibilityinput.sendKeys(responsibility);
					 party_Responsibility_Option = By.xpath("//div[contains(text(),'" + responsibility + "')]");
					 applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 5);
				}
			
				driver.findElement(party_Responsibility_Option).click();

				try {
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
					if (od.responsibilityAttributePopup.isDisplayed()) {
						applyExplicitWaitsUntilElementClickable(od.saveButton, Duration.ofSeconds(10));
						od.saveButton.click();
					}
				} catch (Exception e) {
					System.out.println("Normal flow ");
				}
				
				od.deals_partyResponsibilitiesText.click();
			 }	 

			 if(TSID.equalsIgnoreCase("TS121")||TSID.equalsIgnoreCase("TS125")) {
				 input = "Seller";
				 String[] responsibilityArray1 = input.split(",");
				 ArrayList<String> arrayList1 = new ArrayList<>(Arrays.asList(responsibilityArray1));
				 
					 for(String responsibility:arrayList1)
					 {
						 Thread.sleep(1000);
							od.partyResponsibility.click();
							Thread.sleep(1500);
						od.partyResponsibilityinput.sendKeys(responsibility);
						Thread.sleep(1000);
						By party_Responsibility_Option = By.xpath("//div[contains(text(),'" + responsibility + "')]");
						applyExplicitWaitsUntilElementVisible(party_Responsibility_Option, 5);
						driver.findElement(party_Responsibility_Option).click();

						try {
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
							if (od.responsibilityAttributePopup.isDisplayed()) {
								applyExplicitWaitsUntilElementClickable(od.saveButton, Duration.ofSeconds(10));
								od.saveButton.click();
							}
						} catch (Exception e) {
							System.out.println("Normal flow ");
						}
						
						od.deals_partyResponsibilitiesText.click();
					 }	 

			 }
		try {
			input = externalData.getFieldData(TSID, "Basic Details", "Contact");

			if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes")) {

				od.deals_contactConfigure.click();
				String contactName = externalData.getFieldData(TSID, "Basic Details", "Contact-Name");
				System.out.println(contactName);
				od.deals_contactNameTextBox.sendKeys(contactName);
				od.deals_contactNameSearch.click();
				if(TSID.equals("TS138")) {
					applyExplicitWaitsUntilElementClickable(od.deals_loanOfficer_ContactCheckBox, Duration.ofSeconds(10));
					od.deals_loanOfficer_ContactCheckBox.click();
				}
				else {
					applyExplicitWaitsUntilElementClickable(od.deals_contactCheckBox, Duration.ofSeconds(10));
					od.deals_contactCheckBox.click();
				}
				od.deals_contactUpdate.click();
			}
		} catch (NullPointerException e) {

		}
		
		try {
			String context=externalData.getFieldData(TSID, "Basic Details", "Contextualize");
				if(context.equalsIgnoreCase("Yes")||context.equalsIgnoreCase("Y")) {
					
					String PaymentRule=TSID+"_DEAL_LEVEL_RULE";
					System.out.println(PaymentRule);
					icallback.handleCallback("DEAL_LEVEL_RULE",PaymentRule);
					
				}
			}
			catch(NullPointerException e) {
				
				}
		applyExplicitWaitsUntilElementClickable(od.nextBtn, Duration.ofSeconds(15));
		od.nextBtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}
	
	
	public void editDeal() throws Exception {
		 applyExplicitWaitsUntilElementClickable(ond.deal_SideMenuIcon,Duration.ofSeconds(15));
		 ond.deal_SideMenuIcon.click();
		 applyExplicitWaitsUntilElementClickable(ond.liveDealIcon,Duration.ofSeconds(15));
		 ond.liveDealIcon.click();
		 applyExplicitWaitsUntilElementClickable(ond.dealChecker_searchSelect,Duration.ofSeconds(25));
		 dropdown.selectByVisibleText(ond.dealChecker_searchSelect,"Deal Id");
		 applyExplicitWaitsUntilElementClickable(ond.dealChecker_searchBar,Duration.ofSeconds(15));
		 ond.dealChecker_searchBar.sendKeys(dealId);
		 Thread.sleep(4000);
		 ond.dealChecker_searchButton.click();
		 Thread.sleep(3000);
		 applyExplicitWaitsUntilElementClickable( ond.dealChecker_showMenu,Duration.ofSeconds(30));
		 ond.dealChecker_showMenu.click();
		 applyExplicitWaitsUntilElementClickable(ond.deal_EditIcon,Duration.ofSeconds(20));
		 ond.deal_EditIcon.click();
		 if(commonutils.isElementDisplayed(ond.deal_Edit_Yes_Button,2))
		 {
			 ond.deal_Edit_Yes_Button.click();
		 }
				 
		 if(commonutils.isElementDisplayed(ond.AlreadyExistPopup,2))
		 {
			 applyExplicitWaitsUntilElementClickable(ond.account_OK_Button,Duration.ofSeconds(15));
			 ond.account_OK_Button.click();
			 try {
				 applyExplicitWaitsUntilElementClickable(ond.deal_SideMenuIcon,Duration.ofSeconds(15));
				 ond.deal_SideMenuIcon.click();
			 }
			 catch(Exception e) {
					handleElementClickException(ond.deal_SideMenuIcon);
			     }
			 
			 applyExplicitWaitsUntilElementClickable(ond.DealDraftsIcon,Duration.ofSeconds(15));
			 ond.DealDraftsIcon.click();
			 applyExplicitWaitsUntilElementClickable(ond.dealChecker_searchSelect,Duration.ofSeconds(25));
			 dropdown.selectByVisibleText(ond.dealChecker_searchSelect,"Deal Id");
			 applyExplicitWaitsUntilElementClickable(ond.dealChecker_searchBar,Duration.ofSeconds(15));
			 ond.dealChecker_searchBar.sendKeys(dealId);
			 Thread.sleep(4000);
			 ond.dealChecker_searchButton.click();
			 Thread.sleep(3000);
			 ond.dealChecker_searchButton.click();
			Thread.sleep(5000);
			applyExplicitWaitsUntilElementClickable(ond.dealChecker_showMenu, Duration.ofSeconds(20));
			ond.dealChecker_showMenu.click();
			 applyExplicitWaitsUntilElementClickable(ond.DealDraftsOpen,Duration.ofSeconds(20));
			 ond.DealDraftsOpen.click();
			 
		 }

	}

	public void createTransactionFromLiveDeal(String tSID) throws Exception {
		 applyExplicitWaitsUntilElementClickable(ond.liveDealIcon,Duration.ofSeconds(15));
		 click(ond.liveDealIcon);
		 applyExplicitWaitsUntilElementClickable(ond.dealChecker_searchSelect,Duration.ofSeconds(25));
		 dropdown.selectByVisibleText(ond.dealChecker_searchSelect,"Deal Id");
		 applyExplicitWaitsUntilElementClickable(ond.dealChecker_searchBar,Duration.ofSeconds(15));
		 ond.dealChecker_searchBar.sendKeys(dealId);
		click( ond.dealChecker_searchButton);
		 applyExplicitWaitsUntilElementClickable( ond.dealChecker_showMenu,Duration.ofSeconds(30));
		click( ond.dealChecker_showMenu);
		click( ond.dealChecker_addTransaction);
		click( ond.dealChecker_addTransaction_Adhoc);
		click(ond.dealChecker_addTransaction_Adhoc_Payment);

		
		
	}

}
