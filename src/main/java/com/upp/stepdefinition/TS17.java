package com.upp.stepdefinition;

import java.io.IOException;

import com.upp.Api.utils.LogOutApi;
import com.upp.Api.utils.PartyApi;
import com.upp.Api.utils.TransactionApi;
import com.upp.base.BaseClass;
import com.upp.handlers.CommonDocumentTypeHandler;
import com.upp.handlers.CommonProductHandler;
import com.upp.handlers.CommonResponsibilityHandler;
import com.upp.handlers.DealPartiesHandler;
import com.upp.handlers.DealPartyAccount_PaymentInstrumentHandler;
import com.upp.handlers.DealResponsibilityHandler;
import com.upp.handlers.EcommerceHandler;
import com.upp.handlers.PartyMaker_PaymentInstrumentHandler;
import com.upp.pagemodules.BasicDetails;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.Login.LoginAPI_UPP;
import com.upp.pagemodules.Login.LoginToApplication;
import com.upp.pagemodules.Parties.Party_Edit_LiveDeal;
import com.upp.pagemodules.Parties.Party_Verify_PartyApiAdded;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.document.AddDealWithDocument;
import com.upp.pagemodules.document.DocumentChecker;
import com.upp.pagemodules.document.DocumentMaker;
import com.upp.pagemodules.document.RequiredDocumentSchedule;
import com.upp.pagemodules.Deal.DealAccountCreator;
import com.upp.pagemodules.Deal.DealBasicDetailCreators;
import com.upp.pagemodules.Deal.DealPartiesCreator;
import com.upp.utils.SwitchWindow;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.*;

public class TS17 extends BaseClass implements ICallback {
	DashBoard_Module dm;
	public String tsid;
	AddDealWithDocument addDoc;
	RequiredDocumentSchedule reqSchedule;
	DocumentMaker docMaker;
	DocumentChecker docCheck;
	
	public TS17(DashBoard_Module dm) {
		this.dm = new DashBoard_Module();
		this.addDoc = new AddDealWithDocument();
		this.reqSchedule = new RequiredDocumentSchedule();
		this.docMaker = new DocumentMaker();
		this.docCheck = new DocumentChecker();
	}

	@Given("Create document using data given in  {string}")
	public void create_document_using_data_given_in(String string) throws Exception {
		tsid = string;
		addDoc.addDocuments(string, this);
	}

	@Then("Schedule reminder for required Document with {string}")
	public void schedule_reminder_for_required_Document_with(String string) throws Exception {
		addDoc.scheduleReminder(tsid);
	}

	@Then("Create workItem for required document schedules")
	public void create_workItem_for_required_document_schedules() throws Exception {
		System.out.println(TS06.dealId);
		reqSchedule.createWorkItem(TS06.dealId);
	}

	@Then("Document Maker upload document and submit document to checker")
	public void document_Maker_upload_document_and_submit_document_to_checker() throws Exception {
		docMaker.uploaddocument(TS06.dealId);

	}

	@Given("Verify status os document")
	public void verify_status_os_document() throws Exception {
		docMaker.verifyStatus(TS06.dealId);
	}

	@Given("Document Checker upload document and submit document")
	public void document_Checker_upload_document_and_submit_document() throws Exception{
		docCheck.manageDocument(TS06.dealId);
	}

	@Given("Verify final status os document")
	public void verify_final_status_os_document()throws Exception {
		docCheck.verifyStatus(TS06.dealId);
	}

	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {

		new CommonProductHandler().handleProduct(callbackid, data);
		new CommonResponsibilityHandler().handleResponsibility(callbackid, data);
		new CommonDocumentTypeHandler().handleDocumentType(callbackid, data, tsid);

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
