package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.CommonResponsibilityHandler;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.handlers.DealPartyAccount_PaymentInstrumentHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.ECommerce.ECommerceTransactionChecker;
import com.upp.pagemodules.ECommerce.ECommerceTransactionMaker;
import com.upp.pagemodules.ECommerce.ECommerceTransactionVerifier;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS07 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	ECommerceTransactionMaker ecommTxn;
	ECommerceTransactionChecker ecommChecker;
	ECommerceTransactionVerifier ecommVerifier;
	public static String dealId;
	public String sourceAccount = new DealPage(dm).sourceAccountNo;
	public String toAccount = new DealPage(dm).toaccountNo;
	public String tsid;

	public TS07(DashBoard_Module dm) {
		this.ecommTxn = new ECommerceTransactionMaker();
		this.dm = new DashBoard_Module();
		this.ecommChecker = new ECommerceTransactionChecker();
		this.ecommVerifier = new ECommerceTransactionVerifier();
	}

//	@Then("Create two eCommerce  Parties in the Parties Tab with given {string}")
//	public void create_two_eCommerce_Parties_in_the_Parties_Tab_with_given(String TSID) throws Exception {
//		tsid = TSID;
//		dealId = dm.twoEcommerceParties(TSID, this);
//
//	}

	@Then("Create two eCommerce  Parties in the Parties Tab with given {string} and {string}")
	public void create_two_eCommerce_Parties_in_the_Parties_Tab_with_given_and(String TSID, String string2)
			throws Exception {
		tsid = TSID;
		dealId = dm.twoEcommerceParties(TSID, string2, this);
	}

	@Then("Add deal in ecommerce transaction maker queue {string}")
	public void add_deal_in_ecommerce_transaction_maker_queue(String TSID) throws Exception {
		ecommTxn.addDealAsEcommerceTxn(dealId, TSID, sourceAccount, toAccount, this);
	}

	@Then("Submit the deal to ecommerce transaction checker")
	public void submit_the_deal_to_ecommerce_transaction_checker() throws Exception {
		ecommChecker.ecommChecker_SubmitDeal(dealId);
	}

	@Then("Ecommerce transaction verifier approve the deal")
	public void ecommerce_transaction_verifier_approve_the_deal() throws Exception {
		ecommVerifier.txnVerifier_ApproveDeal(dealId);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub
		new CommonResponsibilityHandler().handleResponsibility(callbackid, data);

		if (callbackid.equalsIgnoreCase("DEAL_PARTY_ACCONT_PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;

			if (paymentInstrument.equalsIgnoreCase("BT_IN")) {
				DealPartyAccount_PaymentInstrumentHandler handler = new DealPartyAccount_PaymentInstrumentHandler();
				handler.handleBT_IN_PaymentInstrument(tsid);

			}
		}
		if (callbackid.equalsIgnoreCase("PRODUCT_NAME")) {
			String productName = (String) data;
			if (productName.equalsIgnoreCase("flexible funding")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleFlexibleFundding();
			}
			if (productName.equalsIgnoreCase("1.0")) {
				DealGroupAttributesHandler handleAttribute = new DealGroupAttributesHandler();
				handleAttribute.handleoneProduct();
			}

		}

		if (callbackid.equalsIgnoreCase("PAYMENT_INSTRUMENT")) {
			String paymentInstrument = (String) data;
			if (paymentInstrument.equalsIgnoreCase("BT")) {
				TransactionMaker_PaymentInstrumentHandler instrumentHandler = new TransactionMaker_PaymentInstrumentHandler();

				instrumentHandler.handleBTPaymentInstrument(tsid, DealPage.sourceAccountNo, DealPage.toaccountNo);
			}

			/*
			 * if (paymentInstrument.equalsIgnoreCase("LT_IN")) {
			 * TransactionMaker_PaymentInstrumentHandler instrumentHandler = new
			 * TransactionMaker_PaymentInstrumentHandler(); String
			 * checkbox=externalData.getFieldData(tsid, "Basic Details",
			 * "Transactions to non-registered beneficiaries");
			 * 
			 * if(checkbox.equalsIgnoreCase("N")) { instrumentHandler.
			 * handleLT_INPaymentInstrumentFor_Non_Registered_Beneficiary_WithCheckbox_Unchecked
			 * (TSID,DealPage.sourceAccountNo,DealPage.toaccountNo); }
			 */

		}

	}
}
