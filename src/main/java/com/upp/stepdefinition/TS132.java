package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.LogOutApi;
import com.upp.base.BaseClass;
import com.upp.base.Constants;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.CommonResponsibilityHandler;
import com.upp.handlers.DealGroupAttributesHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.handlers.DealPartyAccount_PaymentInstrumentHandler;
import com.upp.handlers.TransactionMaker_PaymentInstrumentHandler;
import com.upp.odp.utils.Create_ODP_Account_Api;
import com.upp.odp.utils.Logout_ODP_Api;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Account.EditOBOResponsibilty;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.pagemodules.ECommerce.ECommerceTransactionChecker;
import com.upp.pagemodules.ECommerce.ECommerceTransactionMaker;
import com.upp.pagemodules.ECommerce.ECommerceTransactionVerifier;
import com.upp.pagemodules.Login.LoginAPI_ODP;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS132 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	ECommerceTransactionMaker ecommTxn;
	ECommerceTransactionChecker ecommChecker;
	ECommerceTransactionVerifier ecommVerifier;
	public static String dealId;
	public String sourceAccount = new DealPage(dm).sourceAccountNo;
	public String toAccount = new DealPage(dm).toaccountNo;
	public String tsid;
	LoginAPI_UPP login_UPP;
	LogOutApi logout_UPP;
	EditOBOResponsibilty edit;
	LoginAPI_ODP login;
	Create_ODP_Account_Api createAcc;
	Logout_ODP_Api logout;

	public TS132(DashBoard_Module dm) {
		this.ecommTxn = new ECommerceTransactionMaker();
		this.dm = new DashBoard_Module();
		this.ecommChecker = new ECommerceTransactionChecker();
		this.ecommVerifier = new ECommerceTransactionVerifier();
		login_UPP = new LoginAPI_UPP();
		logout_UPP = new LogOutApi();
		login = new LoginAPI_ODP();
		edit = new EditOBOResponsibilty();
	}

	@Given("Verify Name as mandatory with given {string}")
	public void verify_Name_as_mandatory_with_given(String string) throws Exception {
		ecommTxn.verifyNameAsMandatoryOption(string);
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
