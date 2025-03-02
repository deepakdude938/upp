package com.upp.UsersApi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.Create_ODP_Virtual_Account_Api;
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

	public static String CreateUser(String TSID, Properties prop) throws IOException, Exception {
		externalData = new ExcelReader();
		String excelFilePath = System.getProperty("user.dir") + "//src//main//resources//UserList.xlsx";

		String filePath = System.getProperty("user.dir") + "//src//test//resources//usercount.properties";
		System.out.println("test case id = " + TSID);
		String payLoadString = externalData.getFieldData(TSID, "API Testcases", "Payload");

		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

		try (FileInputStream fis = new FileInputStream(filePath)) {
			Properties properties = new Properties();
			properties.load(fis);

			// Get the current count from the properties file
			usercount2 = Integer.parseInt(properties.getProperty("usercount1", "0"));
			System.out.println("Count = " + usercount2);
			// Increment the count
			usercount2++;
		} catch (IOException e) {
			e.printStackTrace();
		}
		String username = externalData.getFieldData(excelFilePath, "Sheet", "UserName", usercount2);
		String password = externalData.getFieldData(excelFilePath, "Sheet", "Password", usercount2);
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.name", username);

		jsonContext.set("$.username", password);
		String modifiedJsonString = jsonContext.jsonString();
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			Properties properties = new Properties();
			String countdata = Integer.toString(usercount2);
			properties.setProperty("usercount1", countdata);
			properties.store(fos, "Testing");
			System.out.println("count in prop = " + countdata);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(modifiedJsonString);
		return modifiedJsonString;

	}

	public static String UpdateUser(String TSID, Properties prop) throws IOException, Exception {
		externalData = new ExcelReader();
		String excelFilePath = System.getProperty("user.dir") + "//src//main//resources//UserList.xlsx";

		// System.out.println("test case id = "+TSID);
		String payLoadString = externalData.getFieldData(TSID, "API Testcases", "Payload");
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.status", "SUSPENDED");

		// jsonContext.set("$.username", password);
		String modifiedJsonString = jsonContext.jsonString();

		System.out.println(modifiedJsonString);
		return modifiedJsonString;

	}

	public static String UpdateUserAttribute(String TSID) throws IOException, Exception {
//		externalData = new ExcelReader();
//		String excelFilePath = System.getProperty("user.dir") + "//src//main//resources//UserList.xlsx";
		externalData = new ExcelReader();
		String payload = externalData.getFieldData(TSID, "API Testcases", "Payload");
		
		DocumentContext jsonContext = JsonPath.parse(payload);
		
		jsonContext.set("$.attributes.district", "Pune");
		
		modifiedJsonString = jsonContext.jsonString();
		System.out.println(modifiedJsonString);
		return modifiedJsonString;
		

	}

	public static String deleteRole(String TSID) throws IOException, Exception {
		externalData = new ExcelReader();
		//String excelFilePath = System.getProperty("user.dir") + "//src//main//resources//UserList.xlsx";

		// System.out.println("test case id = "+TSID);
		String payLoadString = externalData.getFieldData(TSID, "API Testcases", "Payload");
		DocumentContext jsonContext = JsonPath.parse(payLoadString);
		jsonContext.set("$.roles[0].role", "DEALCHECKER");

		// jsonContext.set("$.username", password);
		String modifiedJsonString = jsonContext.jsonString();

		System.out.println(modifiedJsonString);
		return modifiedJsonString;

	}

}
