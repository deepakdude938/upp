Feature: TS70_Budget_Consolidated_Yearly Assertion


@Assertion @TS70 @Regression
Scenario Outline: Budget_Consolidated_Yearly Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify in Report that one Transaction is Settled and the other is Rejected with given "<TSID>"
Then Verify in Budget tab Utilized Budget Available Budget Amount with given "<TSID>"
Examples:
      |TSID|
      |TS70|