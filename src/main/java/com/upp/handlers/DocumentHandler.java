package com.upp.handlers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;

import com.upp.base.BaseClass;
import com.upp.odp.utils.AccountDetails;
import com.upp.odp.utils.OdpApi;
import com.upp.pageobjects.Object_Deal;
import com.upp.pageobjects.Object_Document;
import com.upp.utils.DateUtils;
import com.upp.utils.DropDown;
import com.upp.utils.ExcelReader;
import com.upp.utils.JavascriptClick;
import com.upp.utils.ScrollTypes;

import callbackInterfaces.ICallback;

public class DocumentHandler extends BaseClass {
	public static Object_Document od;
	DropDown dropdown;
	public ExcelReader externalData;
	public static JavascriptClick jsClick;
	public static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	public static final int RANDOM_STRING_LENGTH = 10;

	public DocumentHandler() {
		od = new Object_Document();
		dropdown = new DropDown(driver);
		externalData = new ExcelReader();
		jsClick = new JavascriptClick(driver);
	}

	public void handleBlueprintDocument(String TSID) throws Exception {
		
	}

}
