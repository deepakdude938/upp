Feature: TS110_Scheduled_Payment_with GB Account_Split_By_Amount_Assertion

@Assertion @TS110 @Regression @ScbRegression  @TuesThurs
Scenario Outline: TS110_Budget_Regression_Using_GB_Account_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify in Report that one Transaction is Settled and the other is Rejected with given "<TSID>"
Examples:
      |TSID |
      |TS110|