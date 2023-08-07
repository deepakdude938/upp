Feature: TS71_Assertion


@Assertion @TS71
Scenario Outline: Budget_Consolidated_Yearly Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify in Report that one Transaction is Settled and the other is Rejected with given "<TSID>"
Then Verify in Budget tab Utilized Budget Amount with given "<TSID>"
Examples:
      |TSID|
      |TS71|