package com.upp.Api.utils;


import java.io.IOException;
import com.upp.utils.*;


public class Payload {
	
	
	public static ExcelReader externalData;

	public static String createParty(String dealId) throws IOException
	{
		 long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		 String random = Long.toString(number);
		 String uniquePartyName="Party"+random;
		 String uniquePartyRefId="Party"+random;
		 	
		return "{\r\n"
				+ "        \"party\": {\r\n"
				+ "                \"dealRefId\":\""+dealId+"\",\r\n"
				+ "                \"name\":\""+uniquePartyName+"\",\r\n"
				+ "                \"partyRefId\":\""+uniquePartyRefId+"\",\r\n"
				+ "                \"country\": \"IN\",\r\n"
				+ "                \"status\": \"Active\",\r\n"
				+ "                \"kycCompleted\": true,\r\n"
				+ "                \"validFrom\": \"2021-09-17\",\r\n"
				+ "                \"validUntil\": \"2023-12-31\",\r\n"
				+ "                \"responsibility\": \"Acquiree\",\r\n"
				+ "                \r\n"
				+ "\r\n"
				+ "\"type\":\"company\",\r\n"
				+ "\"company\":{\r\n"
				+ "\"incorporationDate\":\"2021-09-17\",\r\n"
				+ "\"registrationNumber\":\"REG123432\",\r\n"
				+ "\"taxId\":\"\",\r\n"
				+ "\"taxType\":\"\",\r\n"
				+ "\"businessCategory\":\"\",\r\n"
				+ "\"businessType\":\"\",\r\n"
				+ "\"listingAuthority\":\"\"\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "                \"executionPolicies\": {\r\n"
				+ "                        \"onPartyActivate\": \"doNothing\",\r\n"
				+ "                        \"onPartyDeactivate\": \"holdExecutions\"\r\n"
				+ "                },\r\n"
				+ "                \"contacts\": [{\r\n"
				+ "                        \"name\": \"Merchant33\",\r\n"
				+ "                        \"designation\": \"Manager\",\r\n"
				+ "                        \"authorizedSignatory\": false,\r\n"
				+ "                        \"enableNotifications\": false,\r\n"
				+ "                        \"workPhone\": \"+91 92392 29932\",\r\n"
				+ "                        \"mobilePhone\": \"+91 92392 29932\",\r\n"
				+ "                        \"email\": \"jane.doe@gmail.com\",\r\n"
				+ "                        \"address\": {\r\n"
				+ "                                \"town\": \"Bengaluru Urban\",\r\n"
				+ "                                \"street\": \"M G Road\",\r\n"
				+ "                                \"zip_pin\": \"4000340\",\r\n"
				+ "                                \"state\": \"KA\",\r\n"
				+ "                                \"country\": \"IN\"\r\n"
				+ "                        }\r\n"
				+ "                }],\r\n"
				+ "                \"accounts\": [{\r\n"
				+ "                        \"paymentInstrumentId\": \"BT\",\r\n"
				+ "                        \"description\": \"Payout Account\",\r\n"
				+ "                        \"paymentDetails\": {\r\n"
				+ "                        \"beneficiaryBankBic\": \"BIC456\",\r\n"
				+ "                        \"beneficiaryCountry\":\"IN\",\r\n"
				+ "                        \"beneficiaryCurrency\":\"INR\",\r\n"
				+ "                        \"to\":\"SBI98765\",\r\n"
				+ "                        \"beneficiaryCountryOfIncorporation\":\"IN\",\r\n"
				+ "                        \"amount\":1000\r\n"
				+ "                        }\r\n"
				+ "            \r\n"
				+ "                        \r\n"
				+ "                }]\r\n"
				+ "        }\r\n"
				+ "}";
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
}
