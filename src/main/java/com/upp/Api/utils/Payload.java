package com.upp.Api.utils;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.upp.utils.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Payload {

	public static ExcelReader externalData;

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
		String api_password = Property.getProperty("api_password");

		return "{\r\n" + "    \"username\":\"" + api_userName + "\",\r\n" + "    \"password\":\"" + api_password
				+ "\"\r\n" + "}";
	}

	public static String updateParty(String dealId, String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		String payLoadString = externalData.getFieldData(TSID, "PArty API", "Payload");

		return payLoadString;

	}

	public static String createTransaction(String dealId, String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		String modifiedJsonString;
		System.out.println("TSID = " + TSID);
		String payLoadString = externalData.getFieldData(TSID, "Ecommerce Tnx Api", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String random = Long.toString(number);
		String uniquePartyName = "Party" + random;
		String uniquePartyRefId = "Party" + random;

		// Used Jackson library to modify Json values

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(payLoadString);
		JsonNode mainNode = rootNode.deepCopy();
		System.out.println(rootNode);
		JsonNode modifiedNode = rootNode.deepCopy();
		((ObjectNode) modifiedNode).put("dealRefId", "REF1681189261171");
		((ObjectNode) modifiedNode.at("/initiatingParty")).put("partyRefId","SA001");
		((ObjectNode) modifiedNode.at("/paymentInfo")).put("partyRefId","SA001");
		((ObjectNode) modifiedNode.at("/paymentInfo")).put("country","IN");
		((ObjectNode) modifiedNode.at("/paymentInfo")).put("currency", "INR");
		((ObjectNode) modifiedNode.at("/paymentInfo")).put("instructedControlSum", "100");
		((ObjectNode) modifiedNode.at("/paymentInfo")).put("platformRefNo", uniquePartyRefId);
		
		ObjectNode newTransaction = objectMapper.createObjectNode();
		newTransaction.put("fragmentPlatformRefNo", "DA001");
		newTransaction.put("amount", 50);
		ObjectNode participant = objectMapper.createObjectNode();
		participant.put("partyRefId", "DA001");
		participant.put("beneficiaryCountry", "IN");
		participant.put("beneficiaryCurrency", "");
		newTransaction.set("participant", participant);
		newTransaction.put("requestedExecutionOn", "2023-04-26T06:15:00Z");
		newTransaction.putArray("transactionAttributes");
		ArrayNode transactionArrayNode = (ArrayNode) modifiedNode.get("creditTransactionInfo");
		transactionArrayNode.remove(0);
		transactionArrayNode.add(newTransaction);
		
		//System.out.println(modifiedNode.toPrettyString());
		
	//	((ArrayNode) modifiedNode.path("creditTransactionInfo")).add(newTransaction);
		
		ObjectNode newTransaction1 = objectMapper.createObjectNode();
		newTransaction1.put("fragmentPlatformRefNo", "DA002");
		newTransaction1.put("amount", 50);
		ObjectNode participant1 = objectMapper.createObjectNode();
		participant1.put("partyRefId", "DA002");
		participant1.put("beneficiaryCountry", "IN");
		participant1.put("beneficiaryCurrency", "");
		newTransaction1.set("participant", participant1);
		newTransaction1.put("requestedExecutionOn", "2023-04-26T06:15:00Z");
		newTransaction1.putArray("transactionAttributes");
		ArrayNode transactionArrayNode1 = (ArrayNode) modifiedNode.get("creditTransactionInfo");
		transactionArrayNode1.remove(0);
		transactionArrayNode1.add(newTransaction1);
		//((ArrayNode) modifiedNode.path("creditTransactionInfo")).add(newTransaction1);
		
				modifiedJsonString = objectMapper.writeValueAsString(modifiedNode);
		System.out.println("String" + modifiedJsonString);
		return modifiedJsonString;

	}
}
