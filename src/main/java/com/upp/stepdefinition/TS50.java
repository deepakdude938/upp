package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Audit.ModifyLiveDeal;
import com.upp.pagemodules.Audit.Verify_Audit_Deal;
import com.upp.pagemodules.Audit.Verify_Audit_Transaction;
import com.upp.pagemodules.Audit.Verify_Audit_Transaction_ExcelReport;

import callbackInterfaces.ICallback;

import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.CommonResponsibilityHandler;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealPartyAccount_PaymentInstrumentHandler;
import com.upp.handlers.EcommerceHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.utils.SwitchWindow;

import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.payment.Payment;
import io.cucumber.java.en.*;

public class TS50 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public String tsid = "";
	public String dealid;
	Payment payment;
	Verify_Audit_Transaction audit;
	Verify_Audit_Transaction_ExcelReport audit_excel;


	public TS50() {

		this.dm = new DashBoard_Module();
		audit=new Verify_Audit_Transaction();
		audit_excel=new Verify_Audit_Transaction_ExcelReport();
 
	}
	
	@And("verify Audit Transcation with given {string}")
	public void verify_Audit_Transcation_with_given(String string) throws Exception {
	   audit.verify_Audit_Transaction(TS06.dealId);
	}
	
	@Then("verify the downloaded Audit Report with instruction version {string}")
	public void verify_the_downloaded_Audit_Report_with_instruction_version(String string) throws Exception {
	//	audit_excel.verify_Audit_Transaction_Excel_Report(TS06.dealId, string);
		audit_excel.verify_Audit_Transaction_Excel_Report("REF1686806168098", "Create");
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {

		new CommonProductHandler().handleProduct(callbackid, data);
		new CommonResponsibilityHandler().handleResponsibility(callbackid, data);

		if (callbackid.equalsIgnoreCase("ecommerce")) {
			String responsibility = (String) data;
			if (responsibility.equalsIgnoreCase("Y")) {
				EcommerceHandler ecommerce = new EcommerceHandler();
				ecommerce.handleEcommerce(tsid);
			}
		}

		if (callbackid.equalsIgnoreCase("DEAL_PARTY_ACCONT_PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;

			if (paymentInstrument.equalsIgnoreCase("BT")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_BT_PaymentInstrument(tsid);

			}

			if (paymentInstrument.equalsIgnoreCase("LTTest")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_LTTest_PaymentInstrument(tsid);

			}

			if (paymentInstrument.equalsIgnoreCase("BT_IN")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handleBT_IN_PaymentInstrument(tsid);

			}

			if (paymentInstrument.equalsIgnoreCase("LT_IN")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handle_LT_IN_PaymentInstrument(tsid);

			}
		}
	}
}
