Feature: TS70_Assertion


@Assertion @TS70
Scenario Outline: Budget_Consolidated_Yearly Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled with given "<TSID>"
Examples:
      |TSID|
      |TS70|