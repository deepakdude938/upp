Feature: TS105_Scheduled_Payment_with GB Account_Split_By_Percentage_Assertion

@Assertion @TS105 @Regression @ScbRegression
Scenario Outline: TS105_Scheduled_Payment_with GB Account_Split_By_Percentage_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
And Check the Transaction status of all 6 Transactions is Settled/Triggered and Settled amount in execution report with given "<TSID>"
Examples:
      |TSID |
      |TS105|