package com.upp.stepdefinition;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Budget;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Holiday;
import com.upp.pagemodules.payment.Payment;
import com.upp.pagemodules.payment.Payment_Summary;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class TS10 extends BaseClass  implements ICallback{
	
	public Holiday hd;
	public Payment pm;
	public Payment_Summary ps;

	
	public TS10() {
		this.hd = new Holiday();
		this.pm = new Payment();
		this.ps = new Payment_Summary();
	
	}
	
	
	@Then("Create Holiday {string}")
	public void holiday_Combination(String ID) throws Exception, IOException {
	    hd.createNewHoliday(ID);
	}

	@And("Approve Holiday")
	public void approve_Holiday() throws Exception {
	    hd.approveHoliday();
	}
	
	@Then("Create Payments in the Scheduled Instructions on Friday {string}")
	public void create_Payments_in_the_Scheduled_Instructions_on_Friday(String id) throws Exception {
	  pm.createPaymentsInScheduledInstructionsOnFriday(id,DealPage.sourceAccountNo,DealPage.toaccountNo);
	}
	
	@Then("Check schedule payment next business day")
	public void check_schedule_payment_next_business_day() throws Exception {
	    ps.checkSchedulePaymentforNextBusinessDay();
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		
		
	}

}
