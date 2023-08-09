Feature: TS72_Assertion Budget_PurposeandDestination_DateRange_Assertion


@Assertion @TS72
Scenario Outline: Budget_PurposeandDestination_DateRange_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled with given "<TSID>"
Then Verify in Budget tab Utilized Budget Available Budget Amount with given "<TSID>"
Examples:
      |TSID|
      |TS72|