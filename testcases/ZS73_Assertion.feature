Feature: TS73_Budget_Assertion


@Assertion @TS73 @Regression
Scenario Outline: TS73_Budget_Carryforward_Assertion

Given Open browser and enter url 
Then Login to the application as "deal_maker"
Then Fetch record from ODP with "<TSID>"
Then Validate record to be settled in execution report "<TSID>"
Then Verify in Budget tab Utilized Budget Available Budget Amount with given "<TSID>"
Then Verify Budget carry forward amount

Examples:
      |TSID|
      |TS73|