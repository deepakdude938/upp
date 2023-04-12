package com.upp.odp.utils;


import java.io.IOException;
import com.upp.utils.*;


public class Payload {
	
	
	public static ExcelReader externalData;

	public static String Login() throws IOException
	{
		
		String Odp_userName=Property.getProperty("Odp_bot_user");
		String Odp_password=Property.getProperty("Odp_bot_password");
		
		return "{\r\n"
				+ "    \"username\":\""+Odp_userName+"\",\r\n"
				+ "    \"password\":\""+Odp_password+"\"\r\n"
				+ "}";
	}

	public static String createAccount(String TSID) throws Exception
	{
		
		externalData = new ExcelReader();
		
		String country=externalData.getFieldData(TSID,"Accounts","Country Code");
		String currency=externalData.getFieldData(TSID,"Accounts","Currency");
		String accountIdentifierKey=externalData.getFieldData(TSID,"Accounts","Physical");
		
		 long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		 String accno = Long.toString(number);
		 
		if((accountIdentifierKey.equalsIgnoreCase("Yes"))||(accountIdentifierKey.equalsIgnoreCase("Y")))
		{
			accountIdentifierKey="physical";
		}
		else
		{
			accountIdentifierKey="virtual";
		}
		
		return "{\r\n"
		+ "    \"_id\":\""+accno+"\",\r\n"
		+ "    \"accountNumber\":\""+accno+"\" ,\r\n"
		+ "    \"balances\": {\r\n"
		+ "        \"available\": {\r\n"
		+ "            \"amount\": 150000,\r\n"
		+ "            \"currency\":\""+currency+"\",\r\n"
		+ "            \"indicator\": \"Credit\",\r\n"
		+ "            \"lastUpdatedOn\": \"2023-01-20T18:30:00.000Z\"\r\n"
		+ "        },\r\n"
		+ "        \"eod\": {\r\n"
		+ "            \"amount\": 100000,\r\n"
		+ "            \"currency\":\""+currency+"\",\r\n"
		+ "            \"indicator\": \"Credit\",\r\n"
		+ "            \"lastUpdatedOn\": \"2023-01-20T18:30:00.000Z\"\r\n"
		+ "        },\r\n"
		+ "        \"ledger\": {\r\n"
		+ "            \"amount\": 100000,\r\n"
		+ "            \"currency\":\""+currency+"\",\r\n"
		+ "            \"indicator\": \"Credit\",\r\n"
		+ "            \"lastUpdatedOn\": null\r\n"
		+ "        }\r\n"
		+ "    },\r\n"
		+ "    \"closedOn\": null,\r\n"
		+ "    \"currency\":\""+currency+"\",\r\n"
		+ "    \"country\":\""+country+"\",\r\n"
		+ "    \"customerId\": \"12345\",\r\n"
		+ "    \"extendedAttributes\": {\r\n"
		+ "        \"pan\": null,\r\n"
		+ "        \"numberDecimal\": null,\r\n"
		+ "        \"dropdown\": null,\r\n"
		+ "        \"date\": null,\r\n"
		+ "        \"aadharNo\": \"123132\",\r\n"
		+ "        \"ebbsShortName\": \"john\",\r\n"
		+ "            \"idNo\": 10178,\r\n"
		+ "           \"testIdMandatory\": 10178,\r\n"
		+ "        \"vehicleNUmber\": null,\r\n"
		+ "        \"mothersMaidenName\": \"TestName\",\r\n"
		+ "        \"oboConsideration\": \"NonOBO\",\r\n"
		+ "        \"testIdMandatory\": 1245,\r\n"
		+ "        \"oboResponsibility\": null,\r\n"
		+ "        \"oboFlag\": \"N\",\r\n"
		+ "        \"oboName\": null,\r\n"
		+ "        \"oboAddressLine1\": null,\r\n"
		+ "        \"oboAddressLine2\": null,\r\n"
		+ "        \"oboCountry\": null\r\n"
		+ "    },\r\n"
		+ "    \"status\": \"ACTIVE\",\r\n"
		+ "    \"name\": \"ACC\",\r\n"
		+ "    \"test1233\": null,\r\n"
		+ "    \"helperNotes\": {\r\n"
		+ "        \"withdrawals\": null,\r\n"
		+ "        \"deposits\": null,\r\n"
		+ "        \"priorityShortfall\": null,\r\n"
		+ "        \"additional\": null\r\n"
		+ "    },\r\n"
		+ "    \"openedOn\": \"2023-01-20T18:30:00.000Z\",\r\n"
		+ "    \"accountIdentifierKey\":\""+accountIdentifierKey+"\"\r\n"
		+ "}";
	}
}
