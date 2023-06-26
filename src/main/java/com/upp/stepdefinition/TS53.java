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
import com.upp.pagemodules.Deal.DealEntitlements;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.payment.Payment;
import io.cucumber.java.en.*;

public class TS53 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public String tsid = "";
	public String dealid;
	Payment payment;
	Verify_Audit_Transaction audit;
	Verify_Audit_Transaction_ExcelReport audit_excel;
	DealEntitlements de;

	public TS53() {

		this.dm = new DashBoard_Module();
		this.de = new DealEntitlements();

	}

	@Then("Add entitlements for deal with given {string}")
	public void add_entitlements_for_deal_with_given(String string) throws Exception {
		de.createDealEntitlements();
	}

	@Then("Verify Entitlement in transaction {string}")
	public void verify_Entitlement_in_transaction(String string) throws Exception {
		de.verifyEntitlementsInTransaction(string, TS06.dealId, DealPage.sourceAccountNo);
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
