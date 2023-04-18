package com.upp.stepdefinition;

import com.upp.pagemodules.notifications.Notifications;
import com.upp.pagemodules.payment.Payment;
import com.upp.pagemodules.payment.Payment_Summary;
import com.upp.utils.ExcelReader;

import io.cucumber.java.en.Then;

public class TS19 {
	public Notifications nt;
	
	public TS19() {
		
	nt = new Notifications();
	
	}
	
	@Then("Update contacts in Successful scheduled option in notification tab")
	public void update_contacts_in_Successful_scheduled_option_in_notification_tab() throws Exception {
	   nt.updateContacts();
	}

}
