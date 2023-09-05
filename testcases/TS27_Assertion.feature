Feature: TS27_Bulk_Upload_Adhoc_Transaction_Assertion


@Assertion @TS27
Scenario Outline: TS27_Bulk_Upload_Adhoc_Transaction_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled with given "<TSID>"
Examples:
      |TSID|
      |TS27|