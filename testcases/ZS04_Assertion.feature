Feature: TS04_Linked_Instruction_Assertion


@Assertion @TS04 @Regression @Daily
Scenario Outline: TS04_Linked_Instruction_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled with given "<TSID>"
Examples:
      |TSID|
      |TS04|