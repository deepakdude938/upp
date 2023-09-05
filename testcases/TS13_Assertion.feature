Feature: TS13_AdhocTransaction_with_Non_Registered_Beneficiary_Checkbox_Checked_Assertion


@Assertion @TS13
Scenario Outline: TS13_AdhocTransaction_with_Non_Registered_Beneficiary_Checkbox_Checked_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled with given "<TSID>"
Examples:
      |TSID|
      |TS13|