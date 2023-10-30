Feature: TS113_Multiple_Transactions_Regression_Using_GB_Account_Assertion

@Assertion @TS113 @Regression
Scenario Outline: TS113_Multiple_Transactions_Regression_Using_GB_Account_Account_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify the Utilized Budget Records for Multiple Transaction in Utilization Report with "<TSID>"
Examples:
      |TSID |
      |TS113|