package com.upp.pagemodules.Deal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pagemodules.PartyDetails.Party_Accounts;
import com.upp.pagemodules.PartyDetails.Party_BasicDetails;
import com.upp.pagemodules.PartyDetails.Party_Contacts;
import com.upp.pagemodules.PartyDetails.Party_Documents;
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
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DealPartiesCreator extends BaseClass {

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
	DealPartiesHandler partyHandler = new DealPartiesHandler();
	Party_BasicDetails basic_details;
	Party_Accounts party_account;
	Party_Contacts party_contacts;
	Party_Documents party_documents;

	public DealPartiesCreator() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		basic_details = new Party_BasicDetails();
		party_account = new Party_Accounts();
		party_contacts = new Party_Contacts();
		party_documents = new Party_Documents();

	}

	public void createParties(String TSID, ICallback icallback) throws Exception, IOException {
		applyExplicitWaitsUntilElementClickable(od.parties_icon, Duration.ofSeconds(20));
		try {
			od.parties_icon.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_icon);
		}
		od.parties_GetStarted.click();
		String partyHandle = externalData.getFieldData(TSID, "Party", "Add a new Party");
		System.out.println(partyHandle);
		if (partyHandle.equalsIgnoreCase("Yes")) {
			partyHandler.handleAddNewParty(TSID, icallback);
		} else {
			partyHandler.handleLinkedExistingParty(TSID);
		}
	}

	public void createPeatiesWith1DebitorAnd2Creditor(String TSID, ICallback icallback) throws Exception {
		createParties(TSID, icallback);
		od.accountBackbtn.click();
		createParties(TSID, icallback);
	}

	public void createParty_With_BasicDetails(String TSID, ICallback icallback) throws Exception, IOException {
		applyExplicitWaitsUntilElementClickable(od.parties_icon, Duration.ofSeconds(20));
		try {
			od.parties_icon.click();
		} catch (Exception e) {
			handleElementClickException(od.parties_icon);
		}
		od.parties_GetStarted.click();

		basic_details.Create_Party_BasicDetails(TSID, icallback);

	}

	public void createParty_With_Contacts(String TSID, ICallback icallback) throws Exception, IOException {

		party_contacts.Create_Party_Contacts(TSID, icallback);

	}

	public void createParty_With_Accounts(String TSID, ICallback icallback) throws Exception, IOException {

		party_account.Create_Party_Accounts(TSID, icallback);

	}
	
	public void createParty_With_Documents(String TSID, ICallback icallback) throws Exception, IOException {

		party_documents.Create_Party_Document(TSID, icallback);

	}

}
