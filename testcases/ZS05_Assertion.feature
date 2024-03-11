Feature: TS05_Budget_Assertion


@Assertion @TS05 @Regression @TuesThurs
Scenario Outline: TS05_Budget_Assertion

Given Open browser and enter url 
Then Login to the application as "deal_maker"
Then Fetch record from ODP with "<TSID>"
Then Validate record to be settled in execution report "<TSID>"
Then Verify in Budget tab Utilized Budget Available Budget Amount with given "<TSID>"

Examples:
      |TSID|
      |TS05|