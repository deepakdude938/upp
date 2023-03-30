package com.upp.Api.utils;


import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.upp.utils.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Payload {
	
	
	public static ExcelReader externalData;

	public static String createParty(String dealId,String TSID) throws IOException, Exception
	{
		externalData = new ExcelReader();
		String payLoadString =externalData.getFieldData(TSID, "PArty API", "Payload");
	
		 long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		 String random = Long.toString(number);
		 String uniquePartyName="Party"+random;
		 String uniquePartyRefId="Party"+random;
		 
		 // Used Jackson library to modify Json values
		 ObjectMapper objectMapper = new ObjectMapper();
		 JsonNode rootNode = objectMapper.readTree(payLoadString);
		 JsonNode nodeToModify = rootNode.path("party");
		 
		 ((ObjectNode) nodeToModify).put("dealRefId",dealId);
		 ((ObjectNode) nodeToModify).put("name",uniquePartyName);
		 ((ObjectNode) nodeToModify).put("partyRefId",uniquePartyRefId);
		 
		 String modifiedJsonString = objectMapper.writeValueAsString(rootNode);
		 
return modifiedJsonString;
		 	
		
	}

	public static String loginToUPP() throws IOException
	{
		
		String api_userName=Property.getProperty("api_username");
		String api_password=Property.getProperty("api_password");
		
		return "{\r\n"
				+ "    \"username\":\""+api_userName+"\",\r\n"
				+ "    \"password\":\""+api_password+"\"\r\n"
				+ "}";
	}
	
	public static String updateParty(String dealId,String TSID) throws IOException, Exception
	{
		externalData = new ExcelReader();
		String payLoadString =externalData.getFieldData(TSID, "PArty API", "Payload");
	    
		return payLoadString;
		 
	}
}
