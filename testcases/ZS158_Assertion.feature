Feature: TS158_AdhocPayment_Assertion

@Assertion @TS158 @Regression
Scenario Outline: TS158_AdhocPayment_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled for all Two Transactions with given "<TSID>"
Examples:
      |TSID |
      |TS158|