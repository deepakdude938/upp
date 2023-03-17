package com.upp.stepdefinition;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Budget;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Holiday;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.Then;

public class TS10 extends BaseClass  implements ICallback{
	
	public Holiday hd;
	
	public TS10() {
		this.hd = new Holiday();
	}
	
	
	@Then("Create Holiday {string}")
	public void holiday_Combination(String ID) throws Exception, IOException {
	    hd.createNewHoliday(ID);
	}

	@Then("Approve Holiday")
	public void approve_Holiday() throws Exception {
	    hd.approveHoliday();
	}
	
	
	

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		
		
	}

}
