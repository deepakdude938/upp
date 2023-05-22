package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Audit.EditLiveDeal_AddBudget;
import com.upp.pagemodules.Audit.ModifyLiveDeal;
import com.upp.pagemodules.Audit.Verify_Audit_Deal;
import com.upp.pagemodules.Audit.Verify_Budget_Details;

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

public class TS29 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	DealPage dp;
	public String tsid = "";
	public String dealid;
	Payment payment;
	ModifyLiveDeal modify;
	Verify_Audit_Deal verify;
	EditLiveDeal_AddBudget budget;
	Verify_Budget_Details vb;

	public TS29() {

		this.dm = new DashBoard_Module();
		payment = new Payment();
		modify = new ModifyLiveDeal();
		verify = new Verify_Audit_Deal();
		budget = new EditLiveDeal_AddBudget();
		vb = new Verify_Budget_Details();

	}

	@Then("Edit the Live deal and add add the Party Tab with given {string}")
	public void edit_the_Live_deal_and_add_edit_the_Party_Tab_with_given(String string) throws Exception {

		tsid = string;
		modify.editLivedeal_and_modify_PartyTab_With_new_party(TS06.dealId, tsid, this);
	}

	@And("Verfiy In Audit Tab that update log is Updated with new party")
	public void verfiy_In_Audit_Tab_that_update_log_is_Updated_with_new_party() throws Exception {
		verify.verify_Audit_Tab_Update_Log(TS06.dealId);
	}

	@Then("Edit the Live deal and add Budget")
	public void edit_the_Live_deal_and_add_Budget() throws Exception {
		budget.editLivedeal_and_Budget_and_Payment(TS06.dealId);
	}

	@And("Create Payment with budget with given {string}")
	public void create_Payment_with_budget_with_given(String string) throws Exception {
		modify.create_payment_with_budget(string, dp.sourceAccountNo, dp.toaccountNo);
	}

	@Then("submit and approve the deal")
	public void submit_and_approve_the_deal() throws Exception {
		modify.submit_and_approveDeal();
	}

	@Then("Verify In Audit Budget Tab whether Budget details are Correct with given {string}")
	public void verify_In_Audit_Budget_Tab_whether_Budget_details_are_Correct_with_given(String string) throws Exception {
       vb.verify_budget_details_In_AuditTab(TS06.dealId, string);
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
