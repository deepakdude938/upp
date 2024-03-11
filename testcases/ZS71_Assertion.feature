Feature: TS71_Budget_Purpose_HalfYearly_Assertion


@Assertion @TS71 @Regression @TuesThurs
Scenario Outline: Budget_Purpose_HalfYearly Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify in Report that one Transaction is Settled and the other is Rejected with given "<TSID>"
Then Verify in Budget tab Utilized Budget Amount with given "<TSID>"
Examples:
      |TSID|
      |TS71|