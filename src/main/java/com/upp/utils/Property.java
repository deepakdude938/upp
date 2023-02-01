package com.upp.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.upp.base.BaseClass;
import com.upp.base.Constants;

public class Property extends BaseClass{
	
	public static String getProperty(String key){
		Properties prop=new Properties();
		
		try {
			FileInputStream file=new FileInputStream(Constants.PROJECT_PATH+Constants.PROPERTY_FILE_PATH);
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value=prop.getProperty(key);
		return value;
		
	}

}
