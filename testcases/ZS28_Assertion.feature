Feature: TS28_Assertion


@Assertion @TS28 @Regression  @MonWedFri
Scenario Outline: TS28_Bulk Upload_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled for ecomm transaction with given "<TSID>"
Examples:
      |TSID|
      |TS28|