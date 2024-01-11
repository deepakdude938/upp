package com.upp.pagemodules.Parties_Maker_Checker;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import com.upp.base.BaseClass;
import com.upp.handlers.DealPartiesHandler;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_NewDeal;
import com.upp.pageobjects.Object_Parties;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import callbackInterfaces.ICallback;
public class Party_Checker extends BaseClass {

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
	public static Object_Deal od1;
	DealPartiesHandler partyHandler = new DealPartiesHandler();
	public Party_Checker() {

		od = new Object_NewDeal();
		externalData = new ExcelReader();
		dropdown = new DropDown(driver);
		odpAccount = new OdpApi();
		accDetails = new AccountDetails();
		jsClick = new JavascriptClick(driver);
		scroll = new ScrollTypes(driver);
		dateutil = new DateUtils();
		op=new Object_Parties();
		od1 = new Object_Deal();

	}

	public void PartyChecker(String TSID) throws Exception
	{
		applyExplicitWaitsUntilElementClickable(op.parties_Icon,Duration.ofSeconds(30));
		op.parties_Icon.click();
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_Icon,Duration.ofSeconds(15));
		op.PartyChecker_Icon.click();
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_CustomerID_SearchBox,Duration.ofSeconds(20));
		op.PartyChecker_CustomerID_SearchBox.sendKeys(externalData.getFieldData(TSID, "Parties-Maker", "Customer Id"));
		Thread.sleep(7000);
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_EditIcon,Duration.ofSeconds(10));
		jsClick.click(op.PartyChecker_EditIcon);
		op.PartyMaker_SummaryTab.click();
		applyExplicitWaitsUntilElementClickable(op.PartyChecker_AddComment,Duration.ofSeconds(10));
		op.PartyChecker_AddComment.sendKeys(externalData.getFieldData(TSID, "Parties-Checker", "Summary - Add your comments here"));
		op.PartyChecker_ApproveButton.click();
		op.PartyMaker_YesButton.click();
		Thread.sleep(4000);
	}

	public void verifyResponsibilityAttributes() throws Exception {
		SoftAssert assert1 = new SoftAssert();
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementClickable(od.payments_DealsummaryIcon, Duration.ofSeconds(5));
		try {
			od.payments_DealsummaryIcon.click();
		} catch (Exception w) {
			handleElementClickException(od.payments_DealsummaryIcon);
		}
		od.payments_DealsummaryIcon.click();
		Thread.sleep(2000);
		ScrollTypes.scrollInsideWindow1(od.dealSummary_LeftVerticalScroller, 500);
		od.dealSummary_LeftVerticalPartyResponsibilityOption.click();
		Thread.sleep(1000);
		od.dealSummary_partyResponsibilityOption.click();
		List<String> o = new ArrayList();
		for (WebElement a : od.dealSummary_partyResponsibilityOptions) {
			o.add(a.getText().trim());
		}
		o.sort(null);
		boolean t = object.equals(o);
		assert1.assertTrue(t);
		od.dealSummary_CloseButton.click();

		ScrollTypes.scrollInsideWindowTillWebElementPresent(od.dealSummary_CustomHeaderLabel_Attributes,
				od.dealSummary_HorizontalSlider, 5, 500);
		click(od.dealSummary_CustomHeaderLabel_AttributesValue);
		List<String> o1 = new ArrayList();
		for (WebElement ele : od.dealSummary_partyResponsibilityOption_Attributes) {
			o1.add(ele.getText().trim());
		}
		o1.sort(null);
		t = object.equals(o1);
		assert1.assertTrue(t);
		od.dealSummary_CloseButton.click();

		od.parties_icon.click();
		applyExplicitWaitsUntilElementClickable(od.parties_recordRow, Duration.ofSeconds(20));
		ScrollTypes.scrollInsideWindowTillWebElementPresent(od.parties_recordEditButton, od.parties_HorizontalSlider, 5,
				500);
		od.parties_recordEditButton.click();
		ScrollTypes.scrollInsideWindow1(od.parties_verticalSlider, 1000);
		String partyName = od1.party_basic_details_automationAttribute_partyname.getAttribute("value");
		String panNo = od1.party_basic_details_automationAttribute_panno.getAttribute("value");
		String dob = od1.party_basic_details_automationAttribute_dob.getAttribute("value");
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		String formattedDate = currentDate.format(formatter);
		assert1.assertEquals(partyName, "Party");
		assert1.assertEquals(panNo, "12345543");
		assert1.assertEquals(dob, formattedDate);
		assert1.assertAll();
	}
}
