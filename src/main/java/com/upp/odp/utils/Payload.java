package com.upp.odp.utils;


import java.io.IOException;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.upp.base.BaseClass;
import com.upp.stepdefinition.DealPage;
import com.upp.utils.*;


public class Payload extends BaseClass{
	
	DealPage dp;
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
		String accountBalance=externalData.getFieldData(TSID,"Accounts","Account Balance");
		
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
		+ "            \"amount\": "+accountBalance+",\r\n"
		+ "            \"currency\":\""+currency+"\",\r\n"
		+ "            \"indicator\": \"Credit\",\r\n"
		+ "            \"lastUpdatedOn\": \"2023-01-20T18:30:00.000Z\"\r\n"
		+ "        },\r\n"
		+ "        \"eod\": {\r\n"
		+ "            \"amount\": 150000,\r\n"
		+ "            \"currency\":\""+currency+"\",\r\n"
		+ "            \"indicator\": \"Credit\",\r\n"
		+ "            \"lastUpdatedOn\": \"2023-01-20T18:30:00.000Z\"\r\n"
		+ "        },\r\n"
		+ "        \"ledger\": {\r\n"
		+ "            \"amount\": 150000,\r\n"
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
		+ "        \"testAttribute\": \"test\",\r\n"
		+ "        \"vehicleNUmber\": null,\r\n"
		+ "        \"motherMaidenName\": \"TestName\",\r\n"
		+ "        \"oboConsideration\": \"NonOBO\",\r\n"
		+ "        \"oboResponsibilty\": null,\r\n"
		+ "        \"oboFlag\": \"N\",\r\n"
		+ "        \"oboName\": null,\r\n"
		+ "        \"oboAddressLine1\": null,\r\n"
		+ "        \"oboAddressLine2\": null,\r\n"
		+ "        \"oboCountry\": null\r\n"
		+ "    },\r\n"
		+ "    \"status\": \"ACTIVE\",\r\n"
		+ "    \"name\": \"ACC1212\",\r\n"
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
	
	public static String editAccount(String TSID,int amount,String accountno) throws Exception
	{
		
		externalData = new ExcelReader();
		String payLoadString =externalData.getFieldData(TSID, "ODP Api", "Payload");
	
		 DocumentContext jsonContext = JsonPath.parse(payLoadString);
		 jsonContext.set("$._id",accountno);
		 jsonContext.set("$.accountNumber",accountno);
	     jsonContext.set("$.balances.available.amount", amount);
	     jsonContext.set("$.balances.eod.amount", amount);
	     jsonContext.set("$.balances.ledger.amount", amount);
	    
	       String modifiedJsonString = jsonContext.jsonString();
	       
		return modifiedJsonString;
		
	}
	
	public static String createAccountFromExcelSheet(String TSID) throws Exception
	{
		
		externalData = new ExcelReader();
		
		String country=externalData.getFieldData(TSID,"Accounts","Country Code");
		String currency=externalData.getFieldData(TSID,"Accounts","Currency");
 		String accountIdentifierKey=externalData.getFieldData(TSID,"Accounts","Physical");
		
		 long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		 String accountno = Long.toString(number);
		 physical_Account_Number=accountno;
		if((accountIdentifierKey.equalsIgnoreCase("Yes"))||(accountIdentifierKey.equalsIgnoreCase("Y")))
		{
			accountIdentifierKey="physical";
		}
		else
		{
			accountIdentifierKey="virtual";
		}
		
		if(TSID.equals("TS87") || TSID.equals("TS84_1")) {
			accountIdentifierKey=externalData.getFieldData(TSID,"Accounts","Physical");
			if((accountIdentifierKey.equalsIgnoreCase("Yes"))||(accountIdentifierKey.equalsIgnoreCase("Y")))
			{
				accountIdentifierKey="Physical";
			}
			else
			{
				accountIdentifierKey="Virtual";
			}
			
		}
		
		String payLoadString =externalData.getFieldData(TSID, "ODP Api", "Payload");
		
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		 jsonContext.set("$._id",accountno);
		 jsonContext.set("$.accountNumber",accountno);
	     jsonContext.set("$.currency", currency);
	     jsonContext.set("$.country", country);
	     jsonContext.set("$.accountIdentifierKey", accountIdentifierKey);
	    String modifiedJsonString = jsonContext.jsonString();
	      
		return modifiedJsonString ;
	}
	
	public static String createVirtualAccountFromExcelSheet(String TSID) throws Exception
	{
		
		externalData = new ExcelReader();
		
		String country=externalData.getFieldData(TSID,"Accounts","Country Code");
		String currency=externalData.getFieldData(TSID,"Accounts","Currency");
		 long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		 String accountno = Long.toString(number);
		 virtual_Account_Number = accountno;
		String payLoadString =externalData.getFieldData(TSID, "ODP Api", "Payload");
		
		String physicalAccountNumber=DealPage.AccountNo1;
		
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		 jsonContext.set("$._id",accountno);
		 jsonContext.set("$.accountNumber",accountno);
	     jsonContext.set("$.currency", currency);
	     jsonContext.set("$.country", country);
	     jsonContext.set("$.accountIdentifierKey", "virtual");
	     jsonContext.set("$.extendedAttributes.physicalAccountNumber",physicalAccountNumber);
	    String modifiedJsonString = jsonContext.jsonString();
	      
		return modifiedJsonString ;
	}
}
