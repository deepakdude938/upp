package com.upp.stepdefinition;

import com.upp.pageobjects.Object_NewDeal;
import com.upp.utils.ExcelReader;

import io.cucumber.java.en.Then;

public class TS107 {

	public static Object_NewDeal od;
	public static ExcelReader externalData;
	
	public TS107() {
		od = new Object_NewDeal();
		externalData = new ExcelReader();
	}
	
	@Then("Hit Back button")
	public void hit_Back_button() {
		od.parties_backButtton.click();
	}
}
