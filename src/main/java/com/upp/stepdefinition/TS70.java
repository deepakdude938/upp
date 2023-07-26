package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.Budget;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Deal.DealAccountCreator;
import io.cucumber.java.en.Then;

public class TS70 extends BaseClass{
	
	
	
	public Budget budget;
	public String tsid;
	public static String sourceAccountNo = "";
	public static String toAccountNo = "";
	public DashBoard_Module dashboard;


	public TS70() {
		budget = new Budget();
		dashboard = new DashBoard_Module();

	}
	
	@Then("Create Budget_Consolidated_Yearly with given {string}")
	public void create_Budget_Consolidated_Yearly_with_given(String string) throws Exception {
	 budget.CreateBudget_Consolidated_Yearly(string,DealPage.sourceAccountNo,DealPage.toaccountNo);
	}
	
	
	
}
