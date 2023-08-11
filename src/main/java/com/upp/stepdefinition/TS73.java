package com.upp.stepdefinition;

import com.upp.pagemodules.Budget;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;

import io.cucumber.java.en.Then;

public class TS73 {
	public Budget budget;
	
	public TS73() {
		budget = new Budget();
	}
	
	@Then("Verify Budget carry forward amount")
	public void verify_Budget_carry_forward_amount() {
	   
	}
}
