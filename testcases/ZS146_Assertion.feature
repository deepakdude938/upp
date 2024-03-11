Feature: TS146_Retention-Surplus_Assertion

@Assertion @TS146 @Regression  @Daily
Scenario Outline: TS146_Retention-Surplus_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled for all Two Transactions with given "<TSID>"
Examples:
      |TSID |
      |TS146|