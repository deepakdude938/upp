Feature: TS126_Retention_and_Payment_Retention_with_GB_Account_Assertion

@Assertion @TS126 @Regression @ScbRegression
Scenario Outline: TS126_Retention_and_Payment_Retention_with_GB_Account_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify in Report that both Transactions are settled with given "<TSID>"
Examples:
      |TSID |
      |TS126|