package com.upp.stepdefinition;

import com.upp.base.BaseClass;
import com.upp.pagemodules.Budget;
import com.upp.pagemodules.DashBoard_Module;
import com.upp.pagemodules.ECommerce.ECommerceTransactionChecker;
import com.upp.pagemodules.ECommerce.ECommerceTransactionVerifier;
import com.upp.pagemodules.ECommerce.Ecomm_BulkUpload;
import com.upp.pagemodules.Transactions.Reports_ExecutionReport;
import com.upp.pagemodules.Transactions.Transactions_Maker_Bulkupload;

import callbackInterfaces.ICallback;
import io.cucumber.java.en.Then;

public class TS28 extends BaseClass implements ICallback{
	
	public Ecomm_BulkUpload bulk;
	public ECommerceTransactionChecker ecomChecker;
	public ECommerceTransactionVerifier ecomVerifier;
	public Reports_ExecutionReport execReport;
	public Budget dm;
	
	public TS28() {

//		this.dm = new DashBoard_Module();
		bulk = new Ecomm_BulkUpload();
		ecomChecker=new ECommerceTransactionChecker();
		ecomVerifier=new ECommerceTransactionVerifier();
		execReport = new Reports_ExecutionReport();
		this.dm = new Budget();
	}
	
	@Then("Bulk upload Ecomm Transaction")
	public void bulk_upload_Ecomm_Transaction() throws Exception {
	    bulk.bulkUploadEcommTransaction();
	}
	
	@Then("Update excel file according to data")
	public void update_excel_file_according_to_data() throws Exception {
	    bulk.updateExcelFile();
	}
	
	@Then("Validate ecomm transaction in Reports")
	public void validate_ecomm_transaction_in_Reports() throws Exception {
		execReport.validateEcommTransaction();
	}
	
	@Then("Approve ecomm transaction from Ecommerce Txn Checker")
	public void approve_ecomm_transaction_from_Ecommerce_Txn_Checker() throws Exception {
		ecomChecker.approveRecordFromEcommTxnChecker();
	}

	@Then("Approve ecomm transaction from Ecommerce Txn Verifier")
	public void approve_ecomm_transaction_from_Ecommerce_Txn_Verifier() throws Exception {
		ecomVerifier.approveRecordFromEcommTxnVerifier();
	}
	
	@Then("Create jsonPayload file for ODP record {string}")
	public void create_jsonPayload_file_for_ODP_record(String string) throws Exception {
	  dm.createJsonFile(string);
	}
	
	
	@Override
	public void handleCallback(String callbackid, Object data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
