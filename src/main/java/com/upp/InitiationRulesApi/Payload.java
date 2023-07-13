package com.upp.InitiationRulesApi;

import java.io.IOException;
import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.*;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class Payload extends BaseClass {

	public static ExcelReader externalData;
	public static String modifiedJsonString = "";

	public Payload() {
		externalData = new ExcelReader();
	}

	public static String Rule_Non_OBO(String dealId, String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";

		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.accountNumber", DealPage.AccountNo1);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);

		String modifiedJsonString = jsonContext.jsonString();

		return modifiedJsonString;

	}

	public String updateJsonFilePartyEnrichDebtorRule(String TSID) throws Exception, IOException {
		String jsonEnrichDebtor = externalData.getFieldData(TSID, "Initiation Rules", "Payload");
		String platformRefNo = "Party" + generateRandomString(6);
		String tomorow = DateUtils.getDate(1) + "T11:28:00Z";
		DocumentContext jsonContext = JsonPath.parse(jsonEnrichDebtor);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.accountNumber", DealPage.toaccountNo);
		jsonContext.set("$.paymentInfo.platformRefNo", platformRefNo);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", tomorow);
		modifiedJsonString = jsonContext.jsonString();
		System.out.println(modifiedJsonString);
		return modifiedJsonString;
	}

	public String rule_ParticipantOBO(String TSID, String dealId) throws Exception, IOException {
		String jsonEnrichDebtor = externalData.getFieldData(TSID, "Initiation Rules", "Payload");
		String platformRefNo = "Party" + generateRandomString(6);
		String tomorow = DateUtils.getDate(1) + "T11:28:00Z";
		DocumentContext jsonContext = JsonPath.parse(jsonEnrichDebtor);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.accountNumber", DealPage.AccountNo1);
		jsonContext.set("$.paymentInfo.platformRefNo", platformRefNo);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", tomorow);
		modifiedJsonString = jsonContext.jsonString();
		System.out.println(modifiedJsonString);
		return modifiedJsonString;
	}

	public static String rule_static_obo(String TSID, String dealId, String accountNo) throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.paymentInfo.accountNumber", accountNo);
		jsonContext.set("$.ultimateDebtor.name", TSID);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);

		// jsonContext.set("$.creditTransactionInfo[1].requestedExecutionOn",
		// utctimeEod);
		String modifiedJsonString1 = jsonContext.jsonString();
		System.out.println(modifiedJsonString1);
		return modifiedJsonString1;

	}

	public static String Rule_OBODetails_Null_OBO(String dealId, String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";

		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.accountNumber", DealPage.AccountNo1);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);

		String modifiedJsonString = jsonContext.jsonString();

		return modifiedJsonString;

	}

	public static String Rule_OBOParticipant_OBO_Info_Not_Null(String dealId, String TSID)
			throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";

		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.accountNumber", DealPage.AccountNo1);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);

		String modifiedJsonString = jsonContext.jsonString();

		return modifiedJsonString;

	}

	public static String rule_ParticipantId_OBODetails(String TSID, String dealId, String accountNo)
			throws IOException, Exception {
		externalData = new ExcelReader();
		System.out.println("Account number = "+DealPage.AccountNo1);
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");
		System.out.println(payLoadString);
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.paymentInfo.accountNumber", DealPage.AccountNo1);
		jsonContext.set("$.ultimateDebtor.name", "");
//		jsonContext.set("$.ultimateDebtor.country", "");
//		jsonContext.set("$.ultimateDebtor.addressLine1", "");
//		jsonContext.set("$.ultimateDebtor.addressLine2", "");
//		jsonContext.set("$.ultimateDebtor.partyRefId", "");
		jsonContext.set("$.creditTransactionInfo[0].participant.partyRefId", "");
		jsonContext.set("$.creditTransactionInfo[0].participant.beneficiaryCountry", "");
		jsonContext.set("$.creditTransactionInfo[0].participant.beneficiaryCurrency", "");
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);

		String modifiedJsonString1 = jsonContext.jsonString();
		return modifiedJsonString1;

	}

	public static String rule_EnrichParty_UD1(String TSID, String dealId, String accountNo)
			throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.paymentInfo.accountNumber", accountNo);
		jsonContext.set("$.ultimateDebtor.partyRefId", TSID);
		jsonContext.set("$.ultimateDebtor.dealRefId", "");
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);

		// jsonContext.set("$.creditTransactionInfo[1].requestedExecutionOn",
		// utctimeEod);
		String modifiedJsonString1 = jsonContext.jsonString();
		System.out.println(modifiedJsonString1);
		return modifiedJsonString1;

	}

	public static String rule_EnrichParty_UD(String TSID, String dealId, String accountNo)
			throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");
		String partyref = externalData.getFieldData(TSID, "Party", "Party Name");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.paymentInfo.accountNumber", accountNo);
		jsonContext.set("$.ultimateDebtor.partyRefId", partyref);
		jsonContext.set("$.ultimateDebtor.dealRefId", TSID);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);

		// jsonContext.set("$.creditTransactionInfo[1].requestedExecutionOn",
		// utctimeEod);
		String modifiedJsonString1 = jsonContext.jsonString();
		System.out.println(modifiedJsonString1);
		return modifiedJsonString1;

	}

	public static String Rule_OBOPartyResponsibility_PartyId(String dealId, String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";

		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.accountNumber", DealPage.AccountNo1);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);

		String modifiedJsonString = jsonContext.jsonString();

		return modifiedJsonString;

	}

	public static String Rule_OBOPartyResponsibility_PartyId_DealRefId(String dealId, String TSID)
			throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";

		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.accountNumber", DealPage.AccountNo1);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);
		jsonContext.set("$.ultimateDebtor.dealRefId", dealId);

		String modifiedJsonString = jsonContext.jsonString();

		return modifiedJsonString;

	}

	public static String rule_DealRefId_V3_UC(String TSID, String dealId, String accountNo)
			throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Initiation Rules", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.paymentInfo.accountNumber", accountNo);
		jsonContext.set("$.ultimateDebtor.name", TSID);
		jsonContext.set("$.creditTransactionInfo[0].ultimateCreditor.dealRefId", "Test");
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);

		// jsonContext.set("$.creditTransactionInfo[1].requestedExecutionOn",
		// utctimeEod);
		String modifiedJsonString1 = jsonContext.jsonString();
		System.out.println(modifiedJsonString1);
		return modifiedJsonString1;

	}
}
