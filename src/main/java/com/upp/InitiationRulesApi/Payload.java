package com.upp.InitiationRulesApi;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.upp.stepdefinition.DealPage;
import com.upp.utils.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class Payload {

	public static ExcelReader externalData;

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

}
