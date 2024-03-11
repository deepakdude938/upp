Feature: TS15_Payment-Surplus_Assertion


@Assertion @TS15 @Regression  @Daily
Scenario Outline: Payment-Surplus_Assertion

Given Open browser and enter url 
Then Login to the application as "deal_maker"
Then Fetch record from ODP with "<TSID>"
Then Validate Triggered or Setteled status for multiple records in execution report for "<TSID>"

Examples:
      |TSID|
      |TS15|