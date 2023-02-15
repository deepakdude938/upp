package com.upp.stepdefinition;


import com.upp.base.BaseClass;
import com.upp.pagemodules.DashBoard_Module;
import io.cucumber.java.en.*;

public class TS04 extends BaseClass{
	DashBoard_Module dm;
	public static String sourceAccountNo = "";
	public static String toaccountNo = "";
	
	public TS04(DashBoard_Module dm) {
		
		this.dm =new DashBoard_Module ();
	}
	

@Then("Added Onboarding Account with given  {string}.")
public void added_Onboarding_Account_with_given(String TSID) {
	
	try {
		sourceAccountNo = dm.createNewAccount(TSID);
		System.out.println("source = " + sourceAccountNo);
		toaccountNo = dm.createNewAccount(TSID);
		System.out.println("To Account " + toaccountNo);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

@Then("Added Parties with given  {string}.")
public void added_Parties_with_given(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("create Linked Instruction Payment with given  {string}.")
public void create_Linked_Instruction_Payment_with_given(String TSID) {
	try {
		dm.createNewLinkedAccount(TSID, sourceAccountNo, toaccountNo);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
