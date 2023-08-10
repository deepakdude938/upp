package com.upp.Api.utils;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.upp.base.BaseClass;
import com.upp.utils.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class Payload extends BaseClass{

	public static ExcelReader externalData;
	public static String MappersDealId="";
	

	public static String createParty(String dealId, String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "PArty API", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePartyName = "Party" + random;
		String uniquePartyRefId = "Party" + random;

		// Used Jackson library to modify Json values
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(payLoadString);
		JsonNode nodeToModify = rootNode.path("party");

		((ObjectNode) nodeToModify).put("dealRefId", dealId);
		((ObjectNode) nodeToModify).put("name", uniquePartyName);
		((ObjectNode) nodeToModify).put("partyRefId", uniquePartyRefId);

		String modifiedJsonString = objectMapper.writeValueAsString(rootNode);

		return modifiedJsonString;

	}

	public static String loginToUPP() throws IOException {

		String api_userName = Property.getProperty("api_username");
		String api_password =api_Password;

		return "{\r\n" + "    \"username\":\"" + api_userName + "\",\r\n" + "    \"password\":\"" + api_password
				+ "\"\r\n" + "}";
	}

	public static String updateParty(String dealId, String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "PArty API", "Payload");

		return payLoadString;

	}

	public static String createTransaction(String dealId, String TSID, String participant11, String participant2)
			throws IOException, Exception {
		externalData = new ExcelReader();
		String modifiedJsonString;
		System.out.println("TSID = " + TSID);
		String payLoadString = externalData.getFieldData(TSID, "Ecommerce Tnx Api", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String UniqueplatformRefNo = "cplate" + random;

		String utcdate = DateUtils.getCurrentDateUTC();
		String utctime = DateUtils.getCurrentTimeUTC();

		String utctimeEod = utcdate + "T" + utctime;

		// Used Jackson library to modify Json values

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(payLoadString);
		JsonNode mainNode = rootNode.deepCopy();
		System.out.println(rootNode);
		JsonNode modifiedNode = rootNode.deepCopy();
		((ObjectNode) modifiedNode).put("dealRefId", dealId);
		((ObjectNode) modifiedNode.at("/initiatingParty")).put("partyRefId",
				externalData.getFieldData(TSID, "Party", "Participant Id"));
		((ObjectNode) modifiedNode.at("/paymentInfo")).put("partyRefId",
				externalData.getFieldData(TSID, "Party", "Participant Id"));
		((ObjectNode) modifiedNode.at("/paymentInfo")).put("country",
				externalData.getFieldData(TSID, "Party", "Beneficiary Country"));
		((ObjectNode) modifiedNode.at("/paymentInfo")).put("currency",
				externalData.getFieldData(TSID, "Party", "Beneficiary Currency"));
		((ObjectNode) modifiedNode.at("/paymentInfo")).put("instructedControlSum", 100);
		((ObjectNode) modifiedNode.at("/paymentInfo")).put("platformRefNo", UniqueplatformRefNo);

		ObjectNode newTransaction = objectMapper.createObjectNode();
		newTransaction.put("fragmentPlatformRefNo",
				externalData.getFieldData(participant11, "Party", "Participant Id"));
		newTransaction.put("amount", 50);
		ObjectNode participant = objectMapper.createObjectNode();
		participant.put("partyRefId", externalData.getFieldData(participant11, "Party", "Participant Id"));
		participant.put("beneficiaryCountry", externalData.getFieldData(participant11, "Party", "Beneficiary Country"));
		participant.put("beneficiaryCurrency", "");
		newTransaction.set("participant", participant);
		newTransaction.put("requestedExecutionOn", utctimeEod);
		newTransaction.putArray("transactionAttributes");
		ArrayNode transactionArrayNode = (ArrayNode) modifiedNode.get("creditTransactionInfo");
		transactionArrayNode.remove(0);
		transactionArrayNode.add(newTransaction);

		ObjectNode newTransaction1 = objectMapper.createObjectNode();
		newTransaction1.put("fragmentPlatformRefNo",
				externalData.getFieldData(participant2, "Party", "Participant Id"));
		newTransaction1.put("amount", 50);
		ObjectNode participant1 = objectMapper.createObjectNode();
		participant1.put("partyRefId", externalData.getFieldData(participant2, "Party", "Participant Id"));
		participant1.put("beneficiaryCountry", externalData.getFieldData(participant2, "Party", "Beneficiary Country"));
		participant1.put("beneficiaryCurrency", "");
		newTransaction1.set("participant", participant1);
		newTransaction1.put("requestedExecutionOn", utctimeEod);
		newTransaction1.putArray("transactionAttributes");
		ArrayNode transactionArrayNode1 = (ArrayNode) modifiedNode.get("creditTransactionInfo");
		transactionArrayNode1.remove(0);
		transactionArrayNode1.add(newTransaction1);
		modifiedJsonString = objectMapper.writeValueAsString(modifiedNode);
		System.out.println("String" + modifiedJsonString);
		return modifiedJsonString;

	}

	public static String createEcommerceTnx(String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "Ecommerce Tnx Api", "Payload");
		
		if(prop.getProperty("env").equalsIgnoreCase("qa")) {
			MappersDealId="";	
		}
		if(prop.getProperty("env").equalsIgnoreCase("sit")) {
			MappersDealId="REF1691047172720";	
		}

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);

		String UniqueplatformRefNo = "cplate" + random;

		String utcdate = DateUtils.getCurrentDateUTC();

		String utctimeEod = utcdate + "T" + "14:30:00Z";

		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.dealRefId",MappersDealId);
		jsonContext.set("$.paymentInfo.platformRefNo", UniqueplatformRefNo);
		jsonContext.set("$.creditTransactionInfo[0].requestedExecutionOn", utctimeEod);
		jsonContext.set("$.creditTransactionInfo[1].requestedExecutionOn", utctimeEod);
		String modifiedJsonString1 = jsonContext.jsonString();

		return modifiedJsonString1;

	}

	public static String createPartyUsingExcel(String dealId, String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "PArty API", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePartyName = "Party" + random;
		String uniquePartyRefId = "Party" + random;

		// Used Jackson library to modify Json values
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(payLoadString);
		JsonNode nodeToModify = rootNode.path("party");

		((ObjectNode) nodeToModify).put("dealRefId", dealId);
		((ObjectNode) nodeToModify).put("name", externalData.getFieldData(TSID, "Party", "Participant Id"));
		((ObjectNode) nodeToModify).put("partyRefId", externalData.getFieldData(TSID, "Party", "Participant Id"));

		String modifiedJsonString = objectMapper.writeValueAsString(rootNode);

		return modifiedJsonString;

	}

	

	
}
