Feature: TS108_Scheduled_Payment_with GB Account_Split_By_Amount_Assertion

@Assertion @TS108 @Regression
Scenario Outline: TS108_Scheduled_Payment_with GB Account_Split_By_Amount_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
And Check the Transaction status of all 6 Transactions is Settled/Triggered and Settled amount in execution report with given "<TSID>"
Examples:
      |TSID |
      |TS108|