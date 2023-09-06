package com.upp.pagemodules.Parties_Maker_Checker;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.upp.Api.utils.PartyApi;
import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.utils.CommonUtils;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;
import com.upp.pageobjects.Object_Transactions;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import freemarker.template.utility.DateUtil;
import net.bytebuddy.description.ModifierReviewable.ForMethodDescription;

import java.text.SimpleDateFormat;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import callbackInterfaces.ICallback;

public class Party_Verify_Update_Party_API extends BaseClass {

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
	CommonUtils util;
	PartyApi partyapi;

	public Party_Verify_Update_Party_API() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		op = new Object_Parties();
		util = new CommonUtils(driver);
		partyapi = new PartyApi();

	}

	public void Verify_Update_PartyApi_In_UI(String dealId) throws Exception {
		applyExplicitWaitsUntilElementClickable(od.deal_SideMenuIcon, Duration.ofSeconds(15));
		od.deal_SideMenuIcon.click();
		applyExplicitWaitsUntilElementClickable(od.liveDealIcon, Duration.ofSeconds(15));
		od.liveDealIcon.click();
		applyExplicitWaitsUntilElementClickable(od.dealChecker_searchSelect, Duration.ofSeconds(25));
		dropdown.selectByVisibleText(od.dealChecker_searchSelect, "Deal Id");
		applyExplicitWaitsUntilElementClickable(od.dealChecker_searchBar, Duration.ofSeconds(15));
		od.dealChecker_searchBar.sendKeys(dealId);
		Thread.sleep(4000);
		od.dealChecker_searchButton.click();
		Thread.sleep(3000);

		applyExplicitWaitsUntilElementClickable(od.dealChecker_showMenu, Duration.ofSeconds(30));
		od.dealChecker_showMenu.click();
		applyExplicitWaitsUntilElementClickable(od.deal_EditIcon, Duration.ofSeconds(30));
		od.deal_EditIcon.click();
		Thread.sleep(1000);
		if (util.isElementDisplayed(od.deal_Edit_Yes_Button, 2)) {
			od.deal_Edit_Yes_Button.click();
		}

		try {
			applyExplicitWaitsUntilElementClickable(od.parties_icon, Duration.ofSeconds(15));
			od.parties_icon.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_icon);
		}
		applyExplicitWaitsUntilElementClickable(od.party_EditPencilButton, Duration.ofSeconds(15));
		jsClick.click(od.party_EditPencilButton);

		Thread.sleep(3000);

		applyExplicitWaitsUntilElementVisible(od.parties_PartyName, Duration.ofSeconds(10));
		String partyname = od.parties_PartyName.getAttribute("value");
		applyExplicitWaitsUntilElementVisible(od.parties_Remarks, Duration.ofSeconds(5));
		String remarks = od.parties_Remarks.getAttribute("value");
		applyExplicitWaitsUntilElementVisible(od.parties_ParticipantId, Duration.ofSeconds(5));
		String ParticipantID = od.parties_ParticipantId.getAttribute("value");
		applyExplicitWaitsUntilElementVisible(od.party_responsibility, Duration.ofSeconds(5));
		String party_responsibility = od.party_responsibility.getAttribute("value");

		System.out.println("The partyname is:" + partyname);
		System.out.println("The remarks  is:" + remarks);
		System.out.println("The ParticipantID  is:" + ParticipantID);
		System.out.println("The party_responsibility  is:" + party_responsibility);

		Assert.assertEquals(partyname,"Shiva");
		Assert.assertEquals(partyapi.partyRefId, ParticipantID);
		Assert.assertEquals(remarks, "testing");
		Assert.assertEquals(party_responsibility, "Acquirer");

		od.parties_BasicNextButton.click();
		applyExplicitWaitsUntilElementVisible(od.ecommerce_status, Duration.ofSeconds(20));
		String status = od.ecommerce_status.getAttribute("value");

	
		System.out.println("The Status is:" + status);

		Assert.assertEquals(status, "Inactive");

		scroll.scrollInToView(od.party_executionpolicy_rightIcon);
		Thread.sleep(2000);
		jsClick.click(od.party_executionpolicy_rightIcon);
		applyExplicitWaitsUntilElementVisible(od.party_Hold_Execution_Dropdown, Duration.ofSeconds(10));
		String On_Party_Deactivation = od.party_Hold_Execution_Dropdown.getAttribute("value");
		Assert.assertEquals(On_Party_Deactivation, "holdExecutions");
		
		
		jsClick.click(od.ecommerceSave);
		System.out.println("save button click");
		Thread.sleep(2000);
		
		
		applyExplicitWaitsUntilElementClickable(od.party_Contacts_Showmenu, Duration.ofSeconds(20));
		od.party_Contacts_Showmenu.click();
		
		applyExplicitWaitsUntilElementClickable(od.party_Contacts_Edit, Duration.ofSeconds(20));
		od.party_Contacts_Edit.click();
		
		String contactname=od.parties_ContactName.getAttribute("value");
		String email=od.parties_Email.getAttribute("value");
		
		System.out.println("contactname"+contactname);
		System.out.println("email"+email);
		
		Assert.assertEquals(contactname, "Merchant293");
		Assert.assertEquals(email, "jane.doe@gmail.com");
        
		applyExplicitWaitsUntilElementClickable(od.party_Contacts_Close, Duration.ofSeconds(20));
		jsClick.click(od.party_Contacts_Close);
		
		try {
			applyExplicitWaitsUntilElementClickable(od.parties_AccountsTab, Duration.ofSeconds(30));
			od.parties_AccountsTab.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_AccountsTab);
		}
		
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(od.party_Accounts_Showmenu, Duration.ofSeconds(20));
		od.party_Accounts_Showmenu.click();
		
		applyExplicitWaitsUntilElementClickable(od.party_Contacts_Edit, Duration.ofSeconds(20));
		od.party_Contacts_Edit.click();
		
		Thread.sleep(1000);

		
		applyExplicitWaitsUntilElementVisible(od.parties_Accounts_beneficiaryName, Duration.ofSeconds(20));
		String benname=od.parties_Accounts_beneficiaryName.getAttribute("value");
		System.out.println("Beneficiary name:"+benname);
		
		Assert.assertEquals(benname, "SCB");
		
		scroll.scrollInToView(od.parties_Accounts_beneficiaryAddressLine1);
		applyExplicitWaitsUntilElementVisible(od.parties_Accounts_beneficiaryAddressLine1, Duration.ofSeconds(5));
		String addressLine1=od.parties_Accounts_beneficiaryAddressLine1.getAttribute("value");
		Assert.assertEquals(addressLine1, "BeneAdd1");

	}

}
