Feature: TS122_Retention_and_Payment_Retention_with_GB_Account_Assertion

@Assertion @TS122 @Regression @ScbRegression @MonWedFri
Scenario Outline: TS122_Retention_and_Payment_Retention_with_GB_Account_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify in Report that one Transaction is Settled and the other is Rejected with given "<TSID>"
Examples:
      |TSID |
      |TS122|