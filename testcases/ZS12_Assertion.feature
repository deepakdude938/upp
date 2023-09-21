Feature: TS12_AdhocTransaction_with_Non_Registered_Beneficiary_Checkbox_Unckecked_Assertion


@Assertion @TS12 @Regression
Scenario Outline: TS12_AdhocTransaction_with_Non_Registered_Beneficiary_Checkbox_Unckecked_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled with given "<TSID>"
Examples:
      |TSID|
      |TS12|