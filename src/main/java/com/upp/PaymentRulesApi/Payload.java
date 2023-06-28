package com.upp.PaymentRulesApi;

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

	public static String Rule_IN_BT(String dealId, String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Payment Profiles", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePlatformRefNo = "PlatformRef" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";

		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.paymentInfo.platformRefNo", uniquePlatformRefNo);
		jsonContext.set("$.dealRefId", dealId);
		jsonContext.set("$.paymentInfo.accountNumber", DealPage.sourceAccountNo);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);

		String modifiedJsonString = jsonContext.jsonString();

		return modifiedJsonString;

	}

}
